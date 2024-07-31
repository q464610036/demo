package org.jeecg.modules.common.enums;

import lombok.Getter;

/**
 * 保养结果枚举
 */
@Getter
public enum UpkeepResultEnum {
    /**
     * 保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养失败
     */
    WAIT("0", "待保养"),
    FINISH("1", "完成"),
    NOT_DONE("2", "未保养"),
    INCOMPLETE("3", "残件"),
    FAIL("9", "保养失败"),
    ;

    UpkeepResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static UpkeepResultEnum getByCode(String code){
        for (UpkeepResultEnum value : values()) {
            if(value.code.equals(code)){
                return value;
            }
        }
        throw new RuntimeException("can`t find OrderOperateStatusEnum by code[" +code+"]");
    }

    private final String code;

    private final String message;
}
