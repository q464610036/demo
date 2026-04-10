package com.attendance.constant;

import java.time.LocalTime;

public class AttendanceConstant {
    public final static LocalTime MORNING_START = LocalTime.of(8, 30);
    public final static LocalTime MORNING_END = LocalTime.of(11, 30);
    public final static LocalTime AFTERNOON_START = LocalTime.of(13, 30);
    public final static LocalTime AFTERNOON_END = LocalTime.of(17, 30);

    public final static String MORNING_START_STR = "08:30";
    public final static String MORNING_END_STR = "11:30";
    public final static String AFTERNOON_START_STR = "13:30";
    public final static String AFTERNOON_END_STR = "17:30";
}
