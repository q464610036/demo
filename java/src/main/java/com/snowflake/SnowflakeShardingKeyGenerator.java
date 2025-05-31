package com.snowflake;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import java.util.Calendar;
import java.util.Properties;

public final class SnowflakeShardingKeyGenerator {
    public static void main(String[] args) {
        long workId = 31;
        long dataCenter = 31;
        Snowflake snowflake = IdUtil.getSnowflake(workId, dataCenter);
        Snowflake snowflake1 = IdUtil.getSnowflake(workId, 1);
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
        System.out.println(snowflake1.nextIdStr());
    }
}
