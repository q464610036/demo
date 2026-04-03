package com.attendance.enums;

import lombok.Getter;

/**
 * 考勤类型颜色枚举
 * 请假-浅蓝 / 出差-浅黄 / 外出-浅绿
 */
@Getter
public enum ColorEnum {
    LEAVE("ADD8E6"),      // 请假-浅蓝色16进制
    BUSINESS_TRIP("FFFFE0"), // 出差-淡黄色16进制
    OUT_WORK("90EE90");   // 外出-浅绿色16进制

    // 颜色16进制编码
    private final String color;

    ColorEnum(String color) {
        this.color = color;
    }
}