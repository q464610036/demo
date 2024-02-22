package com.poi;

import com.util.FileUtil;
import com.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.*;

/**
 * 便条
 * 根据幼儿园分组生成便条
 * @author 陈孟飞
 * @date 2022/7/8
 */
public class CreateNoteExcel {
    private static String excelFolder = "D://test/note/excel/";
    private static String outFolder = "D://test/note/out/";

    public static void main(String[] args) throws Exception {
        List<Note> list = CreateNoteExcel.readExcel();
        FileUtil.delFolder(outFolder);
        TreeMap<Integer, List<Note>> map = new TreeMap<>();
        Map<String, String> indexMap = new HashMap<>();
        //有些index为空，同公司则index相同
        for (Note note : list) {
            if(!StringUtil.isEmpty(note.getIndex()) && !StringUtil.isEmpty(note.getCompany())){
                indexMap.put(note.getCompany().trim(), note.getIndex());
            }
        }
        for (Note note : list) {
            note.setIndex(indexMap.get(note.getCompany()));
            System.out.println("index:"+note.getIndex()
                    +",company:"+note.getCompany()
                    +",name:"+note.getName()
                    +",phoneNo:"+note.getPhoneNo()
                    +",number:"+note.getNumber()
                    +",names:"+note.getNames()
                    +",nature:"+note.getNature()
                    +",class1:"+note.getClass1()
                    +",classroom:"+note.getClassroom()
            );
            if (StringUtil.isEmpty(note.getCompany())) {
                continue;
            }
            //公司去空格
            note.setCompany(note.getCompany().trim());
//            note.setCompany(note.getCompany().replaceAll("\\s", ""));
            List<Note> studentList = map.get(Integer.parseInt(note.getIndex()));
            if (CollectionUtils.isEmpty(studentList)) {
                studentList = new ArrayList<>();
                map.put(Integer.parseInt(note.getIndex()), studentList);
            }
            studentList.add(note);
        }
        createExcel(map);
    }

    private static List<Note> readExcel() throws Exception {
        //读取文件
        List<Note> list = new ArrayList<>();
        File folder = new File(excelFolder);
        if(!folder.exists()){
            System.out.println("文件不存在");
        } else {
            for (File file : folder.listFiles()) {
                list.addAll(ExcelUtils.readExcel(new FileInputStream(file), Note.class));
            }
        }
        return list;
    }

    /**
     * 1级标题样式
     * @param wb
     * @return
     */
    private static CellStyle getFirstTitleStyle(HSSFWorkbook wb){
        // 创建字体样式
        Font font = wb.createFont();
        font.setFontName("宋体"); // 设置字体名称
        font.setFontHeightInPoints((short) 25); // 设置字体大小
//            font.setBold(true); // 设置为粗体
        // 创建单元格样式并应用字体
        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        //水平剧中
        style.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //边框
        style.setBorderTop(BorderStyle.THIN); // 顶部边框
        style.setBorderBottom(BorderStyle.THIN); // 底部边框
        style.setBorderLeft(BorderStyle.THIN); // 左边边框
        style.setBorderRight(BorderStyle.THIN); // 右边边框
        //自动换行
        style.setWrapText(true);
        return style;
    }

    /**
     * 2级标题样式
     * @param wb
     * @return
     */
    private static CellStyle getSecondTitleStyle(HSSFWorkbook wb){
        // 创建字体样式
        Font font = wb.createFont();
        font.setFontName("宋体"); // 设置字体名称
        font.setFontHeightInPoints((short) 12); // 设置字体大小
//            font.setBold(true); // 设置为粗体
        // 创建单元格样式并应用字体
        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        //水平剧中
        style.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //边框
        style.setBorderTop(BorderStyle.THIN); // 顶部边框
        style.setBorderBottom(BorderStyle.THIN); // 底部边框
        style.setBorderLeft(BorderStyle.THIN); // 左边边框
        style.setBorderRight(BorderStyle.THIN); // 右边边框
        //自动换行
        style.setWrapText(true);
        return style;
    }

    /**
     * 内容样式
     * @param wb
     * @return
     */
    private static CellStyle getValueStyle(HSSFWorkbook wb, String fontName){
        // 创建字体样式
        Font font = wb.createFont();
        font.setFontName(fontName); // 设置字体名称
        font.setFontHeightInPoints((short) 11); // 设置字体大小
//            font.setBold(true); // 设置为粗体
        // 创建单元格样式并应用字体
        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        //水平剧中
        style.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //边框
        style.setBorderTop(BorderStyle.THIN); // 顶部边框
        style.setBorderBottom(BorderStyle.THIN); // 底部边框
        style.setBorderLeft(BorderStyle.THIN); // 左边边框
        style.setBorderRight(BorderStyle.THIN); // 右边边框
        //自动换行
        style.setWrapText(true);
        return style;
    }

    // 将字符数转换为Excel列宽的单位
    public static int charactersToWidth(double characters) {
        Double d = (characters + 0.78) * 256;
        return Integer.valueOf(d.intValue());
    }

    private static void createExcel(TreeMap<Integer, List<Note>> map) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet();
        sheet.setColumnWidth(0,charactersToWidth(5.05));
        sheet.setColumnWidth(1,charactersToWidth(28.5));
        sheet.setColumnWidth(2,charactersToWidth(7.2));
        sheet.setColumnWidth(3,charactersToWidth(11.6));
        sheet.setColumnWidth(4, charactersToWidth(5.0));
        sheet.setColumnWidth(5, charactersToWidth(6.1));
        sheet.setColumnWidth(6, charactersToWidth(10.5));
        sheet.setColumnWidth(7, charactersToWidth(41.9));
        sheet.setColumnWidth(8, charactersToWidth(9.4));
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        /* 开始逐行遍历 */
        int i = 0;
        int j = 0;
        for (Integer key : map.keySet()) {
            List<Note> list = map.get(key);
            //写入大标题
            //合并单元格
            CellRangeAddress cellRangeAddress = new CellRangeAddress(i, i, 0, 8);
            sheet.addMergedRegion(cellRangeAddress);
            // 创建一个单元格并写入数据
            Row row = sheet.createRow(i);
            row.setHeight((short)760);
            //创建单元格
            Cell cell = row.createCell(0);
            // 应用样式到单元格
            cell.setCellStyle(getFirstTitleStyle(wb));
            //单元格填写内容
            cell.setCellValue("2024春【产教融合】园所班级分配表");
            i++;
            j=0;
            //写入小标题
            // 应用样式到单元格
            row = sheet.createRow(i);
            row.setHeight((short)680);
            Cell c1 = row.createCell(j);
            c1.setCellStyle(getSecondTitleStyle(wb));
            c1.setCellValue("序号");
            j++;
            Cell c2 = row.createCell(j);
            c2.setCellStyle(getSecondTitleStyle(wb));
            c2.setCellValue("园所名称");
            j++;
            Cell c3 = row.createCell(j);
            c3.setCellStyle(getSecondTitleStyle(wb));
            c3.setCellValue("负责人");
            j++;
            Cell c4 = row.createCell(j);
            c4.setCellStyle(getSecondTitleStyle(wb));
            c4.setCellValue("联系方式");
            j++;
            Cell c5 = row.createCell(j);
            c5.setCellStyle(getSecondTitleStyle(wb));
            c5.setCellValue("人数");
            j++;
            Cell c6 = row.createCell(j);
            c6.setCellStyle(getSecondTitleStyle(wb));
            c6.setCellValue("性质");
            j++;
            Cell c7 = row.createCell(j);
            c7.setCellStyle(getSecondTitleStyle(wb));
            c7.setCellValue("所在班级");
            j++;
            Cell c8 = row.createCell(j);
            c8.setCellStyle(getSecondTitleStyle(wb));
            c8.setCellValue("名单");
            j++;
            Cell c9 = row.createCell(j);
            c9.setCellStyle(getSecondTitleStyle(wb));
            c9.setCellValue("集中教室");
            i++;
            j=0;
            //写入数据
            for(Note note : list){
                row = sheet.createRow(i);
                row.setHeight((short)680);
                Cell cv1 = row.createCell(j);
                cv1.setCellStyle(getValueStyle(wb, "Tahoma"));
                cv1.setCellValue(note.getIndex());
                j++;
                Cell cv2 = row.createCell(j);
                cv2.setCellStyle(getValueStyle(wb, "宋体"));
                cv2.setCellValue(note.getCompany());
                j++;
                Cell cv3 = row.createCell(j);
                cv3.setCellStyle(getValueStyle(wb, "宋体"));
                cv3.setCellValue(note.getName());
                j++;
                Cell cv4 = row.createCell(j);
                cv4.setCellStyle(getValueStyle(wb, "宋体"));
                cv4.setCellValue(note.getPhoneNo());
                j++;
                Cell cv5 = row.createCell(j);
                cv5.setCellStyle(getValueStyle(wb, "Tahoma"));
                cv5.setCellValue(note.getNumber());
                j++;
                Cell cv6 = row.createCell(j);
                cv6.setCellStyle(getValueStyle(wb, "宋体"));
                cv6.setCellValue(note.getNature());
                j++;
                Cell cv7 = row.createCell(j);
                cv7.setCellStyle(getValueStyle(wb, "宋体"));
                cv7.setCellValue(note.getClass1());
                j++;
                Cell cv8 = row.createCell(j);
                cv8.setCellStyle(getValueStyle(wb, "宋体"));
                cv8.setCellValue(note.getNames());
                j++;
                Cell cv9 = row.createCell(j);
                cv9.setCellStyle(getValueStyle(wb, "宋体"));
                cv9.setCellValue(note.getClassroom());
                i++;
                j=0;
            }
        }
        // 第六步，将文件存到指定位置
        try {
            String outputFile =  outFolder+"1.xls";
            FileUtil.createFile(outputFile);
            FileOutputStream fout = new FileOutputStream(outputFile);
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static boolean isNumeric(String str){
        for (int i = 0; i < str.length(); i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
