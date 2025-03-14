package com.example.legend.common.util;

import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    /**
     * 读取开始时间
     *
     * @param timePath
     * @return
     */
    public static Date readStartTime(String timePath) throws Exception {
        String str = FileUtil.readLastLine(timePath);
        if (StringUtil.isEmpty(str)) {
            return DateUtil.addDate(new Date(), -1, Calendar.SECOND);
        }
        return DateUtil.parse(str);
    }
}
