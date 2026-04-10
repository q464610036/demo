package com.attendance.util;

import com.attendance.constant.AttendanceConstant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 考勤异常判断工具类
 * 工作时间：上午 8:30-11:30，下午 13:30-17:30
 */
public class AttendanceUtil {

    private static final LocalTime MORNING_START = AttendanceConstant.MORNING_START;
    private static final LocalTime MORNING_END = AttendanceConstant.MORNING_END;
    private static final LocalTime AFTERNOON_START = AttendanceConstant.AFTERNOON_START;
    private static final LocalTime AFTERNOON_END = AttendanceConstant.AFTERNOON_END;

    /**
     * 考勤时间段对象
     */
    public static class AttendanceRecord {
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public AttendanceRecord(LocalDateTime startTime, LocalDateTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public AttendanceRecord() {}

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }
    }

    /**
     * 考勤结果枚举
     */
    public enum AttendanceStatus {
        NORMAL("正常"),
        LATE("迟到"),
        EARLY_LEAVE("早退"),
        ABSENT_MORNING("上午缺勤"),
        ABSENT_AFTERNOON("下午缺勤"),
        ABNORMAL("异常");

        private final String description;

        AttendanceStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 考勤结果
     */
    public static class AttendanceResult {
        private AttendanceStatus status;
        private String message;
        private int missingMinutes; // 缺失分钟数

        public AttendanceResult(AttendanceStatus status, String message) {
            this(status, message, 0);
        }

        public AttendanceResult(AttendanceStatus status, String message, int missingMinutes) {
            this.status = status;
            this.message = message;
            this.missingMinutes = missingMinutes;
        }

        public AttendanceStatus getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public int getMissingMinutes() {
            return missingMinutes;
        }

        public boolean isNormal() {
            return status == AttendanceStatus.NORMAL;
        }
    }

    /**
     * 判断考勤是否异常
     * @param records 考勤记录列表（包含开始时间和结束时间）
     * @return 考勤结果
     */
    public static AttendanceResult checkAttendance(List<AttendanceRecord> records) {
        if (records == null || records.isEmpty()) {
            return new AttendanceResult(AttendanceStatus.ABSENT_MORNING, "无考勤记录");
        }

        // 计算上午和下午的实际出勤时间
        int morningMinutes = 0;
        int afternoonMinutes = 0;

        for (AttendanceRecord record : records) {
            LocalDateTime start = record.getStartTime();
            LocalDateTime end = record.getEndTime();

            if (start == null || end == null || start.isAfter(end)) {
                continue;
            }

            LocalTime startTime = start.toLocalTime();
            LocalTime endTime = end.toLocalTime();

            // 计算上午段的出勤时间
            morningMinutes += calculateMinutesInRange(startTime, endTime, MORNING_START, MORNING_END);
            // 计算下午段的出勤时间
            afternoonMinutes += calculateMinutesInRange(startTime, endTime, AFTERNOON_START, AFTERNOON_END);
        }

        // 应出勤时长（分钟）
        int requiredMorningMinutes = 180; // 8:30-11:30 = 3小时
        int requiredAfternoonMinutes = 240; // 13:30-17:30 = 4小时

        // 检查上午考勤
        if (morningMinutes == 0) {
            return new AttendanceResult(AttendanceStatus.ABSENT_MORNING, "上午缺勤");
        }

        // 检查是否有迟到（首次签到晚于8:30）
        LocalDateTime firstCheckIn = records.stream()
                .map(AttendanceRecord::getStartTime)
                .filter(t -> t != null)
                .min(LocalDateTime::compareTo)
                .orElse(null);

        if (firstCheckIn != null && firstCheckIn.toLocalTime().isAfter(MORNING_START)) {
            int lateMinutes = (int) java.time.Duration.between(MORNING_START, firstCheckIn.toLocalTime()).toMinutes();
            return new AttendanceResult(AttendanceStatus.LATE,
                    String.format("迟到%d分钟", lateMinutes), lateMinutes);
        }

        // 检查下午考勤
        if (afternoonMinutes == 0) {
            return new AttendanceResult(AttendanceStatus.ABSENT_AFTERNOON, "下午缺勤");
        }

        // 检查早退（最后一次签退早于17:30）
        LocalDateTime lastCheckOut = records.stream()
                .map(AttendanceRecord::getEndTime)
                .filter(t -> t != null)
                .max(LocalDateTime::compareTo)
                .orElse(null);

        if (lastCheckOut != null && lastCheckOut.toLocalTime().isBefore(AFTERNOON_END)) {
            int earlyMinutes = (int) java.time.Duration.between(lastCheckOut.toLocalTime(), AFTERNOON_END).toMinutes();
            return new AttendanceResult(AttendanceStatus.EARLY_LEAVE,
                    String.format("早退%d分钟", earlyMinutes), earlyMinutes);
        }

        // 检查出勤时长是否足够
        int totalMissing = 0;
        if (morningMinutes < requiredMorningMinutes) {
            totalMissing += (requiredMorningMinutes - morningMinutes);
        }
        if (afternoonMinutes < requiredAfternoonMinutes) {
            totalMissing += (requiredAfternoonMinutes - afternoonMinutes);
        }

        if (totalMissing > 0) {
            return new AttendanceResult(AttendanceStatus.ABNORMAL,
                    String.format("出勤时长不足，缺失%d分钟", totalMissing), totalMissing);
        }

        return new AttendanceResult(AttendanceStatus.NORMAL, "考勤正常");
    }

    /**
     * 计算某个时间段在工作时段内的有效分钟数
     */
    private static int calculateMinutesInRange(LocalTime recordStart, LocalTime recordEnd,
                                                LocalTime periodStart, LocalTime periodEnd) {
        if (recordEnd.isBefore(periodStart) || recordStart.isAfter(periodEnd)) {
            return 0;
        }

        LocalTime effectiveStart = recordStart.isAfter(periodStart) ? recordStart : periodStart;
        LocalTime effectiveEnd = recordEnd.isBefore(periodEnd) ? recordEnd : periodEnd;

        if (effectiveStart.isBefore(effectiveEnd) || effectiveStart.equals(effectiveEnd)) {
            return (int) java.time.Duration.between(effectiveStart, effectiveEnd).toMinutes();
        }

        return 0;
    }
}
