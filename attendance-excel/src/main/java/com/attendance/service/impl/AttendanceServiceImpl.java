package com.attendance.service.impl;

import com.attendance.enums.ExceptionTypeEnum;
import com.attendance.service.AttendanceService;
import com.attendance.util.AttendanceUtil;
import com.attendance.util.DateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * 考勤业务实现类
 * 核心：Excel解析、考勤规则计算、颜色标记、异常判断、Excel导出
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Override
    public void processAttendance(MultipartFile checkFile, MultipartFile leaveFile, MultipartFile outFile, MultipartFile tripFile, MultipartFile overtimeFile, HttpServletResponse response) throws Exception {
        // 1. 读取打卡原始Excel（保留原样式/表头）
        InputStream checkIs = checkFile.getInputStream();
        Workbook workbook = new XSSFWorkbook(checkIs);
        Sheet sheet = workbook.getSheetAt(0);

        // 读取月份（第一行第一列，格式：打卡时间 统计日期：2026-03-01 至 2026-03-31）
        String month = "";
        Cell monthCell = sheet.getRow(0).getCell(0);
        if (monthCell != null) {
            String cellValue = monthCell.toString().trim();
            if (cellValue.contains("统计日期：")) {
                String datePart = cellValue.split("统计日期：")[1].split(" ")[0];
                month = datePart.substring(0, 7);
            }
        }

        // 2. 读取请假/外出/出差Excel，转换为内存Map（只返回当月数据）
        List<Map<String, String>> leaveList = readExcelToMap(leaveFile, 0);
        List<Map<String, String>> outList = readExcelToMap(outFile, 0);
        List<Map<String, String>> tripList = readExcelToMap(tripFile, 1);
        List<Map<String, String>> overtimeList = readExcelToMap(overtimeFile, 1);

        int monthInt = Integer.parseInt(month.substring(5, 7));
        // 3. 构建「员工姓名_日期」→ 考勤记录的映射（含同行人）
        Map<String, List<Map<String, String>>> attendanceRecordMap = buildAttendanceRecordMap(monthInt, leaveList, outList, tripList);

        // 4. 统计每人本月的病假/事假/调休/年假时长（单位：小时）
        Map<String, Map<String, Double>> leaveStats = calculateLeaveStats(month, leaveList);

        // 5. 统计每人本月的加班时长（单位：小时）
        Map<String, Double> overtimeStats = calculateOvertimeStats(month, overtimeList);

        // 6. 统计每人本月的迟到时长（单位：分钟）
        Map<String, Integer> lateStats = calculateLateStats(sheet, attendanceRecordMap, month);

        // 7. 核心处理：遍历Excel，逐单元格标记颜色、补充详情、判断异常
        handleExcelSheet(sheet, attendanceRecordMap, leaveStats, overtimeStats, lateStats, workbook, month);

        // 6. 导出处理后的Excel（自动下载）
        exportProcessedExcel(workbook, response);
    }

    /**
     * 读取Excel文件转换为List<Map>，表头为key，行数据为value（只返回当月数据）
     */
    private List<Map<String, String>> readExcelToMap(MultipartFile file, int titleIndex) throws Exception {
        List<Map<String, String>> dataList = new ArrayList<>();
        if (file == null || file.isEmpty()) {
            return dataList;
        }
        InputStream is = file.getInputStream();
        Workbook book = new XSSFWorkbook(is);
        Sheet sheet = book.getSheetAt(0);
        Row headRow = sheet.getRow(titleIndex); // 表头行
        if (headRow == null) {
            book.close();
            return dataList;
        }

        // 遍历数据行
        for (int i = titleIndex + 1; i <= sheet.getLastRowNum(); i++) {
            Row dataRow = sheet.getRow(i);
            if (dataRow == null) {
                continue;
            }
            Map<String, String> rowMap = new HashMap<>();
            // 遍历单元格，表头为key，单元格值为value
            for (int j = 0; j < headRow.getLastCellNum(); j++) {
                Cell headCell = headRow.getCell(j);
                Cell dataCell = dataRow.getCell(j);
                if (headCell == null || dataCell == null) {
                    continue;
                }
                String headKey = headCell.toString().trim();
                String dataValue = dataCell.toString().trim();
                rowMap.put(headKey, dataValue);
            }
            dataList.add(rowMap);
        }
        book.close();
        return dataList;
    }

    /**
     * 构建考勤记录映射：key=员工姓名_日期（如：张三_15），value=该员工该日期的所有考勤记录
     * 自动处理同行人：出差/外出的同行人也会生成对应记录
     */
    private Map<String, List<Map<String, String>>> buildAttendanceRecordMap(int month, List<Map<String, String>>... lists) {
        Map<String, List<Map<String, String>>> recordMap = new HashMap<>();
        for (List<Map<String, String>> dataList : lists) {
            for (Map<String, String> rowMap : dataList) {
                //针对出差的开始时间结束时间转义
                String startTimeStr = rowMap.get("开始时间");
                if (startTimeStr != null) {
                    if (startTimeStr.contains("上午")) {
                        startTimeStr = startTimeStr.replace("上午", "08:30");
                        rowMap.put("开始时间", startTimeStr);
                    }
                    if (startTimeStr.contains("下午")) {
                        startTimeStr = startTimeStr.replace("下午", "13:30");
                        rowMap.put("开始时间", startTimeStr);
                    }
                }
                String endTimeStr = rowMap.get("结束时间");
                if (endTimeStr != null) {
                    if (endTimeStr.contains("上午")) {
                        endTimeStr = endTimeStr.replace("上午", "11:30");
                        rowMap.put("结束时间", endTimeStr);
                    }
                    if (endTimeStr.contains("下午")) {
                        endTimeStr = endTimeStr.replace("下午", "17:30");
                        rowMap.put("结束时间", endTimeStr);
                    }
                }
                // 获取创建人、开始时间、同行人
                String creator = rowMap.getOrDefault("创建人", "").trim();
                String startTime = rowMap.getOrDefault("开始时间", "").trim();
                String companion = rowMap.getOrDefault("同行人", "").trim();
                if (creator.isEmpty() || startTime.isEmpty()) {
                    continue;
                }

                // 解析开始和结束日期，处理跨天情况
                String datePart = startTime.split(" ")[0]; // 如 2026-03-15
                LocalDate startDate = LocalDate.parse(datePart);
                String endTimeTrimmed = rowMap.getOrDefault("结束时间", "").trim();
                LocalDate endDate = startDate;
                if (!endTimeTrimmed.isEmpty() && endTimeTrimmed.contains("-")) {
                    String endDatePart = endTimeTrimmed.split(" ")[0];
                    endDate = LocalDate.parse(endDatePart);
                }

                // 给创建人添加记录（跨天则每天都要添加，只添加当月数据）
                for (LocalDate d = startDate; !d.isAfter(endDate); d = d.plusDays(1)) {
                    if (d.getMonthValue() != month) {
                        continue;
                    }
                    String day = String.valueOf(d.getDayOfMonth());
                    addRecordToMap(recordMap, creator, day, rowMap);
                }
                // 给同行人添加记录（逗号/顿号分隔，跨天则每天都要添加，只添加当月数据）
                if (!companion.isEmpty() && !companion.equals("nan")) {
                    String[] companions = companion.split("[,，]");
                    for (String c : companions) {
                        for (LocalDate d = startDate; !d.isAfter(endDate); d = d.plusDays(1)) {
                            if (d.getMonthValue() != month) {
                                continue;
                            }
                            String day = String.valueOf(d.getDayOfMonth());
                            addRecordToMap(recordMap, c.trim(), day, rowMap);
                        }
                    }
                }
            }
        }
        return recordMap;
    }

    /**
     * 向映射中添加考勤记录
     */
    private void addRecordToMap(Map<String, List<Map<String, String>>> recordMap, String name, String day, Map<String, String> rowMap) {
        String key = name + "_" + day;
        if (!recordMap.containsKey(key)) {
            recordMap.put(key, new ArrayList<>());
        }
        recordMap.get(key).add(rowMap);
    }

    /**
     * 统计每人本月的病假/事假/调休时长（单位：小时）
     * @param month 要统计的月份（如"2026-04"）
     * @param leaveList 请假记录列表
     * @return Map<员工名, Map<类型, 小时数>>，类型为"病假"、"事假"、"调休"
     */
    private Map<String, Map<String, Double>> calculateLeaveStats(String month, List<Map<String, String>> leaveList) {
        Map<String, Map<String, Double>> stats = new HashMap<>();
        // 计算当月的起始和结束日期
        int year = Integer.parseInt(month.substring(0, 4));
        int monthNum = Integer.parseInt(month.substring(5, 7));
        LocalDate monthStart = LocalDate.of(year, monthNum, 1);
        LocalDate monthEnd = monthStart.plusMonths(1).minusDays(1);

        for (Map<String, String> record : leaveList) {
            String creator = record.getOrDefault("创建人", "").trim();
            if (creator.isEmpty()) {
                continue;
            }
            String startTime = record.getOrDefault("开始时间", "").trim();
            String endTime = record.getOrDefault("结束时间", "").trim();
            if (startTime.isEmpty()) {
                continue;
            }
            // 解析请假时间段
            LocalDate startDate = LocalDate.parse(startTime.split(" ")[0]);
            LocalDate endDate = startDate;
            if (!endTime.isEmpty() && endTime.contains("-")) {
                endDate = LocalDate.parse(endTime.split(" ")[0]);
            }
            // 计算与当月的重叠区间
            LocalDate overlapStart = startDate.isAfter(monthStart) ? startDate : monthStart;
            LocalDate overlapEnd = endDate.isBefore(monthEnd) ? endDate : monthEnd;
            if (overlapStart.isAfter(overlapEnd)) {
                continue; // 不在当月范围内
            }
            // 计算重叠天数
            long overlapDays = java.time.temporal.ChronoUnit.DAYS.between(overlapStart, overlapEnd) + 1;
            // 计算请假总天数
            long totalLeaveDays = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
            // 获取时长（天），乘以8转为小时，再按重叠天数比例分摊
            String durationStr = record.getOrDefault("时长", "0");
            double totalDays;
            if (durationStr.contains("天")) {
                totalDays = parseDouble(durationStr.replace("天", ""));
            } else if (durationStr.contains("小时")) {
                totalDays = parseDouble(durationStr.replace("小时", "")) / 8.0;
            } else {
                totalDays = parseDouble(durationStr);
            }
            double hours = totalDays * 8 * overlapDays / totalLeaveDays;
            // 获取请假类型：病假/事假/调休/年假（分开统计）
            String leaveType = record.getOrDefault("请假类型", "").trim();
            String statKey;
            if (leaveType.contains("病假")) {
                statKey = "病假";
            } else if (leaveType.contains("事假")) {
                statKey = "事假";
            } else if (leaveType.contains("年假")) {
                statKey = "年假";
            } else if (leaveType.contains("调休")) {
                statKey = "调休";
            } else {
                continue;
            }

            stats.putIfAbsent(creator, new HashMap<>());
            Map<String, Double> empStats = stats.get(creator);
            empStats.put(statKey, empStats.getOrDefault(statKey, 0.0) + hours);
        }
        return stats;
    }

    /**
     * 统计每人本月的加班时长（单位：小时）
     * @param month 要统计的月份（如"2026-04"）
     * @param overtimeList 加班记录列表
     * @return Map<员工名, 加班小时数>
     */
    private Map<String, Double> calculateOvertimeStats(String month, List<Map<String, String>> overtimeList) {
        Map<String, Double> stats = new HashMap<>();
        if (overtimeList == null || overtimeList.isEmpty()) {
            return stats;
        }
        // 计算当月的起始和结束日期
        int year = Integer.parseInt(month.substring(0, 4));
        int monthNum = Integer.parseInt(month.substring(5, 7));
        LocalDate monthStart = LocalDate.of(year, monthNum, 1);
        LocalDate monthEnd = monthStart.plusMonths(1).minusDays(1);

        for (Map<String, String> record : overtimeList) {
            String creator = record.getOrDefault("创建人", "").trim();
            if (creator.isEmpty()) {
                continue;
            }
            String startTime = record.getOrDefault("开始时间", "").trim();
            if (startTime.isEmpty()) {
                continue;
            }
            // 解析加班日期
            LocalDate startDate = LocalDate.parse(startTime.split(" ")[0]);
            // 筛选当月数据
            if (startDate.isBefore(monthStart) || startDate.isAfter(monthEnd)) {
                continue;
            }
            // 获取加班时长（小时）
            String durationStr = record.getOrDefault("时长", "0");
            double hours;
            if (durationStr.contains("小时")) {
                hours = parseDouble(durationStr.replace("小时", ""));
            } else if (durationStr.contains("天")) {
                hours = parseDouble(durationStr.replace("天", "")) * 8;
            } else {
                hours = parseDouble(durationStr);
            }
            stats.put(creator, stats.getOrDefault(creator, 0.0) + hours);
        }
        return stats;
    }

    /**
     * 统计每人本月的迟到时长（单位：分钟）
     * @param sheet 打卡记录的Sheet
     * @param month 要统计的月份（如"2026-04"）
     * @return Map<员工名, 迟到分钟数>
     */
    private Map<String, Integer> calculateLateStats(Sheet sheet, Map<String, List<Map<String, String>>> recordMap, String month) {
        Map<String, Integer> stats = new HashMap<>();
        LocalTime MORNING_START = LocalTime.of(8, 30);
        // 读取日期列
        Row dateRow = sheet.getRow(1);
        List<Integer> dateColIndices = new ArrayList<>();
        List<String> dateDays = new ArrayList<>();
        for (int j = 1; j < dateRow.getLastCellNum(); j++) {
            Cell dateCell = dateRow.getCell(j);
            if (dateCell == null) continue;
            String dateValue = dateCell.toString().trim();
            if (dateValue.matches("\\d+")) {
                dateColIndices.add(j);
                dateDays.add(dateValue);
            }
        }
        // 遍历员工行
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            Row employeeRow = sheet.getRow(i);
            if (employeeRow == null) continue;
            Cell nameCell = employeeRow.getCell(0);
            if (nameCell == null) continue;
            String employeeName = nameCell.toString().trim();
            if (employeeName.isEmpty()) continue;
            int totalLateMinutes = 0;
            // 遍历该员工每天的单元格
            for (int idx = 0; idx < dateColIndices.size(); idx++) {
                int col = dateColIndices.get(idx);
                String day = dateDays.get(idx);
                Cell cell = employeeRow.getCell(col);
                if (cell == null) continue;
                String cellValue = cell.toString().trim();
                // 获取该员工该日期的考勤记录
                List<Map<String, String>> records = recordMap.getOrDefault(employeeName + "_" + day, new ArrayList<>());
                // 检查是否有考勤异常
                String exceptionInfo = checkAttendanceException(cellValue, records, month, day);
                // 只统计有异常的天数中的迟到时长
                if (exceptionInfo.contains("迟到") || exceptionInfo.contains("打卡基数次")) {
                    String[] timeStrs = cellValue.split("\\s+|\n");
                    LocalTime AFTERNOON_END = LocalTime.of(17, 30);
                    for (String timeStr : timeStrs) {
                        if (timeStr.matches("\\d{2}:\\d{2}")) {
                            LocalTime checkTime = LocalTime.parse(timeStr);
                            // 17:30及之后算下班卡，不统计迟到
                            if (!checkTime.isBefore(AFTERNOON_END)) {
                                continue;
                            }
                            if (checkTime.isAfter(MORNING_START)) {
                                int lateMinutes = (int) java.time.Duration.between(MORNING_START, checkTime).toMinutes();
                                totalLateMinutes += lateMinutes;
                            }
                            break; // 只看17:30之前的第一卡
                        }
                    }
                }
            }
            stats.put(employeeName, totalLateMinutes);
        }
        return stats;
    }

    private Double parseDouble(String str) {
        try {
            return Double.parseDouble(str.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    /**
     * 核心处理Excel：标记颜色、补充详情、判断考勤异常、设置红字
     */
    private void handleExcelSheet(Sheet sheet, Map<String, List<Map<String, String>>> recordMap, Map<String, Map<String, Double>> leaveStats, Map<String, Double> overtimeStats, Map<String, Integer> lateStats, Workbook workbook, String month) {
        // 读取Excel中的日期列（第二行是日期：2、3、4...31）
        Row dateRow = sheet.getRow(1);
        List<Integer> dateColIndices = new ArrayList<>(); // 日期列的列索引
        List<String> dateDays = new ArrayList<>(); // 日期列的日期值（如：2、3、4）
        for (int j = 1; j < dateRow.getLastCellNum(); j++) {
            Cell dateCell = dateRow.getCell(j);
            if (dateCell == null) {
                continue;
            }
            String dateValue = dateCell.toString().trim();
            if (dateValue.matches("\\d+")) { // 是数字日期
                dateColIndices.add(j);
                dateDays.add(dateValue);
            }
        }

        // 在日期列后添加统计列的表头
        int lastDateCol = dateColIndices.isEmpty() ? 1 : dateColIndices.get(dateColIndices.size() - 1);
        int statColStart = lastDateCol + 1;
        String[] statHeaders = {"病假(h)", "事假(h)", "调休(h)", "年假(h)", "加班(h)", "迟到(分钟)"};
        for (int k = 0; k < 6; k++) {
            Cell headerCell = dateRow.createCell(statColStart + k);
            headerCell.setCellValue(statHeaders[k]);
            sheet.setColumnWidth(statColStart + k, 6 * 256); // 列宽约6个字符
        }

        // 遍历员工行（第三行及以后是员工打卡记录）
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            Row employeeRow = sheet.getRow(i);
            if (employeeRow == null) {
                continue;
            }
            // 获取员工姓名（第一列是姓名）
            Cell nameCell = employeeRow.getCell(0);
            if (nameCell == null) {
                continue;
            }
            String employeeName = nameCell.toString().trim();
            if (employeeName.isEmpty()) {
                continue;
            }

            // 遍历该员工的所有日期单元格，逐单元格处理
            for (int idx = 0; idx < dateColIndices.size(); idx++) {
                int col = dateColIndices.get(idx); // 日期列索引
                String day = dateDays.get(idx);     // 日期（如：15）
                Cell cell = employeeRow.getCell(col);
                if (cell == null) {
                    cell = employeeRow.createCell(col); // 新建空单元格
                }
                String originalValue = cell.toString().trim(); // 原始打卡记录
                if (employeeName.equals("廖福冰") && day.equals("2")) {
                    System.out.println();
                }
                // 获取该员工该日期的所有考勤记录（请假/外出/出差）
                List<Map<String, String>> records = recordMap.getOrDefault(employeeName + "_" + day, new ArrayList<>());
                boolean hasLeave = false, hasTrip = false, hasOut = false;
                StringBuilder detailInfo = new StringBuilder(); // 要补充的详情信息

                // 遍历考勤记录，判断类型、拼接详情（外出/请假/出差详情都写到批注中）
                StringBuilder commentContent = new StringBuilder();
                for (Map<String, String> record : records) {
                    for (String key : record.keySet()) {
                        if (key.contains("请假类型")) {
                            hasLeave = true;
                            if (commentContent.length() > 0) {
                                commentContent.append("\n");
                            }
                            commentContent.append("请假：").append(record.get("开始时间")).append("至").append(record.get("结束时间"));
                        } else if (key.contains("出差事由")) {
                            hasTrip = true;
                            if (commentContent.length() > 0) {
                                commentContent.append("\n");
                            }
                            commentContent.append("出差：").append(record.get("出差事由"))
                                    .append(" ").append(record.get("开始时间")).append("至").append(record.get("结束时间"));
                        } else if (key.contains("外出地点及事由")) {
                            hasOut = true;
                            if (commentContent.length() > 0) {
                                commentContent.append("\n");
                            }
                            commentContent.append("外出：").append(record.get("开始时间")).append("至").append(record.get("结束时间"));
                        }
                    }
                }

                // 判断考勤异常，拼接异常信息
                String exceptionInfo = checkAttendanceException(originalValue, records, month, day);
                if (!exceptionInfo.isEmpty()) {
                    if (commentContent.length() > 0) {
                        commentContent.append("\n");
                    }
                    commentContent.append("【").append(exceptionInfo).append("】");
                }

                // 为所有考勤记录创建批注
                if (commentContent.length() > 0) {
                    CreationHelper creationHelper = workbook.getCreationHelper();
                    Drawing<?> drawing = sheet.createDrawingPatriarch();
                    ClientAnchor anchor = creationHelper.createClientAnchor();
                    anchor.setCol1(col);
                    anchor.setCol2(col + 2);
                    anchor.setRow1(i);
                    anchor.setRow2(i + 3);
                    Comment comment = drawing.createCellComment(anchor);
                    comment.setString(creationHelper.createRichTextString(commentContent.toString()));
                    cell.setCellComment(comment);
                }

                // 构建单元格样式：自动换行、垂直置顶、请假/出差/外出背景色
                CellStyle cellStyle = buildCellStyle(workbook, hasLeave, hasTrip, hasOut);
                // 考勤异常则设置红字
                if (!exceptionInfo.isEmpty()) {
                    Font redFont = workbook.createFont();
                    redFont.setColor(IndexedColors.RED.getIndex());
                    cellStyle.setFont(redFont);
                }

                // 设置单元格值：考勤类型显示
                String finalCellValue = originalValue;
                if (originalValue.isEmpty()) {
                    // 未打卡时，根据考勤类型显示
                    if (hasLeave) {
                        finalCellValue = "请假";
                    } else if (hasTrip) {
                        finalCellValue = "出差";
                    } else if (hasOut) {
                        finalCellValue = "外出";
                    }
                } else {
                    finalCellValue = originalValue;
                }
                cell.setCellValue(finalCellValue);
                cell.setCellStyle(cellStyle);
            }

            // 在日期列后添加病假/事假/调休/年假/加班/迟到时长统计
            Map<String, Double> empStats = leaveStats.getOrDefault(employeeName, new HashMap<>());
            double sickLeave = empStats.getOrDefault("病假", 0.0);
            double personalLeave = empStats.getOrDefault("事假", 0.0);
            double adjustLeave = empStats.getOrDefault("调休", 0.0);
            double annualLeave = empStats.getOrDefault("年假", 0.0);
            double overtimeLeave = overtimeStats.getOrDefault(employeeName, 0.0);
            int lateMinutes = lateStats.getOrDefault(employeeName, 0);
            employeeRow.createCell(statColStart).setCellValue(sickLeave);
            employeeRow.createCell(statColStart + 1).setCellValue(personalLeave);
            employeeRow.createCell(statColStart + 2).setCellValue(adjustLeave);
            employeeRow.createCell(statColStart + 3).setCellValue(annualLeave);
            employeeRow.createCell(statColStart + 4).setCellValue(overtimeLeave);
            employeeRow.createCell(statColStart + 5).setCellValue(lateMinutes);
        }
    }

    /**
     * 构建单元格样式：根据考勤类型设置背景色、自动换行/垂直置顶
     */
    private CellStyle buildCellStyle(Workbook workbook, boolean hasLeave, boolean hasTrip, boolean hasOut) {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true); // 自动换行
        style.setVerticalAlignment(VerticalAlignment.TOP); // 垂直置顶
        style.setAlignment(HorizontalAlignment.LEFT); // 水平左对齐

        // 背景色标记优先级：请假（浅灰）> 出差（浅黄）> 外出（浅绿）
        if (hasLeave) {
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        } else if (hasTrip) {
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        } else if (hasOut) {
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        }
        return style;
    }

    /**
     * 构建字体颜色：请假（蓝色）> 出差（橙色）> 外出（绿色）
     */
    private Font buildColorFont(Workbook workbook, boolean hasLeave, boolean hasTrip, boolean hasOut) {
        Font font = workbook.createFont();
        if (hasLeave) {
            font.setColor(IndexedColors.BLUE.getIndex());
        } else if (hasTrip) {
            font.setColor(IndexedColors.ORANGE.getIndex());
        } else if (hasOut) {
            font.setColor(IndexedColors.GREEN.getIndex());
        }
        return font;
    }

    /**
     * 考勤异常判断（严格按需求规则）
     * @param month 月份（如：2026-03）
     * @return 异常信息，无异常返回空字符串
     */
    private String checkAttendanceException(String originalValue, List<Map<String, String>> records, String month, String day) {
        if (day.length() == 1) {
            day = "0"+day;
        }
        //当天所有打卡时间
        List<AttendanceUtil.AttendanceRecord> timeList = new ArrayList<>();
        // 1. 提取原始打卡时间（如：08:05 17:32 → 解析为LocalTime）
        List<LocalTime> checkTimes = new ArrayList<>();
        String[] timeStrs = originalValue.split("\\s+|\n"); // 按空格/换行分割
        int i=0;
        AttendanceUtil.AttendanceRecord pickAttendanceRecord = new AttendanceUtil.AttendanceRecord();
        for (String timeStr : timeStrs) {
            if (timeStr.matches("\\d{2}:\\d{2}")) { // 匹配时间格式
                checkTimes.add(LocalTime.parse(timeStr));
                LocalDateTime dateTime = DateUtil.parseLocalDateTime(month+"-"+day+" "+timeStr, "yyyy-MM-dd HH:mm");
                if (i%2 == 0) {
                    pickAttendanceRecord.setStartTime(dateTime);
                } else {
                    pickAttendanceRecord.setEndTime(dateTime);
                }
                i++;
            }
            if (pickAttendanceRecord.getEndTime() != null) {
                timeList.add(pickAttendanceRecord);
                pickAttendanceRecord = new AttendanceUtil.AttendanceRecord();
            }
        }
        if (pickAttendanceRecord.getEndTime() != null && pickAttendanceRecord.getStartTime() != null) {
            timeList.add(pickAttendanceRecord);
        }
        // 打卡次数不是2次 → 打卡基数次
        if (checkTimes.size() % 2 != 0) {
            return ExceptionTypeEnum.LESS_CHECK.getDesc();
        }

        for (Map<String, String> record : records) {
            LocalDateTime startTime = null;
            LocalDateTime endTime = null;
//            //获取时长
//            String duration = null;
//            boolean durationIsDay = false;
//            for (String key : record.keySet()) {
//                if (key.equals("时长")) {
//                    duration =  record.get(key);
//                    break;
//                }
//            }
//            if (duration.contains("天")) {
//                durationIsDay = true;
//            }
            for (String key : record.keySet()) {
                if (key.equals("开始时间")) {
                    String startTimeStr = record.get(key);
                    if (startTimeStr.length() < 16) {
                        startTimeStr += " 08:30";
                    }
                    startTime = DateUtil.parseLocalDateTime(startTimeStr, "yyyy-MM-dd HH:mm");
                } else if (key.equals("结束时间")) {
                    String endTimeStr = record.get(key);
                    if (endTimeStr.length() < 16) {
                        endTimeStr += " 17:30";
                    }
                    endTime = DateUtil.parseLocalDateTime(endTimeStr, "yyyy-MM-dd HH:mm");
                }
            }
            if (startTime != null && endTime != null) {
                AttendanceUtil.AttendanceRecord attendanceRecord = new AttendanceUtil.AttendanceRecord(startTime, endTime);
                timeList.add(attendanceRecord);
            }
        }
        AttendanceUtil.AttendanceResult result = AttendanceUtil.checkAttendance(timeList);
        if (!result.getStatus().getDescription().equals("正常") ) {
            return result.getStatus().getDescription()+":"+result.getMessage();
        }
        return "";
    }
    /*
    private String checkAttendanceException(String originalValue, List<Map<String, String>> records) {
        // 1. 提取原始打卡时间（如：08:05 17:32 → 解析为LocalTime）
        List<LocalTime> checkTimes = new ArrayList<>();
        String[] timeStrs = originalValue.split("\\s+|\n"); // 按空格/换行分割
        for (String timeStr : timeStrs) {
            if (timeStr.matches("\\d{2}:\\d{2}")) { // 匹配时间格式
                try {
                    checkTimes.add(LocalTime.parse(timeStr));
                } catch (Exception e) {
                    continue;
                }
            }
        }

        boolean hasSpecialRecord = !records.isEmpty(); // 是否有请假/外出/出差记录
        // 规则1：未打卡且无特殊记录 → 旷工
        if (checkTimes.isEmpty() && !hasSpecialRecord) {
            return ExceptionTypeEnum.ABSENT.getDesc();
        }
        // 规则2：打卡次数不是2次 → 打卡基数次
        if (checkTimes.size() != 2) {
            return ExceptionTypeEnum.LESS_CHECK.getDesc();
        }
        // 有特殊记录，无需判断迟到早退
        if (hasSpecialRecord) {
            return "";
        }

        // 3. 无特殊记录，判断迟到/早退
        LocalTime firstCheck = checkTimes.get(0); // 首次打卡
        LocalTime lastCheck = checkTimes.get(1);  // 末次打卡
        LocalTime workStart = LocalTime.of(8, 30); // 上班时间
        LocalTime workEnd = LocalTime.of(17, 30);  // 下班时间

        // 规则3：首次打卡晚于上班时间 → 迟到
        if (firstCheck.isAfter(workStart)) {
            return ExceptionTypeEnum.LATE.getDesc() + "（应到" + workStart + "，实到" + firstCheck + "）";
        }
        // 规则4：末次打卡早于下班时间 → 早退
        if (lastCheck.isBefore(workEnd)) {
            return ExceptionTypeEnum.EARLY.getDesc() + "（应到" + workEnd + "，实到" + lastCheck + "）";
        }

        // 无异常
        return "";
    }
*/
    /**
     * 导出处理后的Excel文件，浏览器自动下载
     */
    private void exportProcessedExcel(Workbook workbook, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=attendance_processed_" + System.currentTimeMillis() + ".xlsx");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");

        OutputStream os = response.getOutputStream();
        try {
            workbook.write(os);
            os.flush();
        } finally {
            workbook.close();
            os.close();
        }
    }
}