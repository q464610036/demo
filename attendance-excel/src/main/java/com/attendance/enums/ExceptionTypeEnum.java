package com.attendance.enums;

import lombok.Getter;

/**
 * 考勤异常类型枚举
 * 旷工/打卡奇数次/迟到/早退
 */
@Getter
public enum ExceptionTypeEnum {
    ABSENT("旷工"),
    LESS_CHECK("打卡奇数次"),
    LATE("迟到"),
    EARLY("早退");

    // 异常描述
    private final String desc;

    ExceptionTypeEnum(String desc) {
        this.desc = desc;
    }
}