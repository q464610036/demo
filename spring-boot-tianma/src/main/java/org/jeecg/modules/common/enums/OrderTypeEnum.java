package org.jeecg.modules.common.enums;

import lombok.Getter;

/**
 * 保养工单类型枚举
 */
@Getter
public enum OrderTypeEnum {
    /**
     * 工单类型：1=保养单，2=取消保养单
     */
    UPKEEP("1", "保养单"),
    CANCEL_UPKEEP("2", "取消保养单"),
    ;

    OrderTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static OrderTypeEnum getByCode(String code){
        for (OrderTypeEnum value : values()) {
            if(value.code.equals(code)){
                return value;
            }
        }
        throw new RuntimeException("can`t find OrderOperateStatusEnum by code[" +code+"]");
    }

    private final String code;

    private final String message;
}
