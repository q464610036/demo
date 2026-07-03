package com.attendance.enums;

import lombok.Getter;

/**
 * 考勤状态枚举
 * 正常/迟到/早退/旷工/打卡奇数次/上午缺勤/下午缺勤/异常
 */
@Getter
public enum AttendanceStatus {
    NORMAL("正常"),
    LATE("迟到"),
    EARLY("早退"),
    ABSENT("旷工"),
    LESS_CHECK("打卡奇数次"),
    ABSENT_MORNING("上午缺勤"),
    ABSENT_AFTERNOON("下午缺勤"),
    ABNORMAL("异常");

    // 状态描述
    private final String desc;

    AttendanceStatus(String desc) {
        this.desc = desc;
    }

    public String getDescription() {
        return desc;
    }
}
