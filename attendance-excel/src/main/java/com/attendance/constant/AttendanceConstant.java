package com.attendance.constant;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AttendanceConstant {
    public final static LocalTime MORNING_START = LocalTime.of(8, 30);
    public final static LocalTime MORNING_END = LocalTime.of(11, 30);
    public final static LocalTime AFTERNOON_START = LocalTime.of(13, 30);
    public final static LocalTime AFTERNOON_END = LocalTime.of(17, 30);

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    public final static String MORNING_START_STR = MORNING_START.format(formatter);
    public final static String MORNING_END_STR = MORNING_END.format(formatter);
    public final static String AFTERNOON_START_STR = AFTERNOON_START.format(formatter);
    public final static String AFTERNOON_END_STR = AFTERNOON_END.format(formatter);
}
