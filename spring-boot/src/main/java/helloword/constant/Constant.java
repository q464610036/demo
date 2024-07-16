package helloword.constant;

import java.time.ZoneId;

public interface Constant {


    /**
    * 默认时区
    **/
    String DEFAULT_ZONE = "Asia/Shanghai";

    /**
     * 默认时区
     **/
    ZoneId DEFAULT_ZONE_ID = ZoneId.of("Asia/Shanghai");

    /**
     * 默认时区偏移
     **/
    String DEFAULT_ZONE_OFFSET = "+8";

}
