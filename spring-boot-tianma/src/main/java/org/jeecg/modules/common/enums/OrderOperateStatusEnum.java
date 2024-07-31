package org.jeecg.modules.common.enums;

import lombok.Getter;

/**
 * 保养工单节点操作状态枚举
 */
@Getter
public enum OrderOperateStatusEnum {
    /**
     * 操作状态：1=取消保养，2=通过，3=确定保养，8=驳回，9=不通过
     */
    CANCEL("1", "取消保养"),
    APPROVE("2", "通过"),
    CONFIRM_CANCEL("3", "确定取消"),
    DENY_CANCEL("8", "驳回"),
    ;

    OrderOperateStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static OrderOperateStatusEnum getByCode(String code){
        for (OrderOperateStatusEnum value : values()) {
            if(value.code.equals(code)){
                return value;
            }
        }
        throw new RuntimeException("can`t find OrderOperateStatusEnum by code[" +code+"]");
    }

    private final String code;

    private final String message;
}
