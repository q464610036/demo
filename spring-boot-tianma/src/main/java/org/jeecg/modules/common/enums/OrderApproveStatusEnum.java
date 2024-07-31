package org.jeecg.modules.common.enums;

import lombok.Getter;

/**
 * 保养工单审批状态枚举
 */
@Getter
public enum OrderApproveStatusEnum {
    /**
     * 审核状态：01=待生产审核，02=待设备确认，03=待执行，04=执行中，05=待填报，06=保养完成，07=取消保养，08=保养动作完成
     */
    PLAIN("01", "待生产审核"),
    UNIT("02", "待设备确认"),
    WAIT("03", "待执行"),
    EXECUTING("04", "执行中"),
    WAIT_RESULT("05", "待填报"),
    FINISH("06", "保养完成"),
    CANCEL("07", "取消保养"),
    ACTION_FINISH("08", "保养动作完成"),
    ;

    OrderApproveStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static OrderApproveStatusEnum getByCode(String code){
        for (OrderApproveStatusEnum value : values()) {
            if(value.code.equals(code)){
                return value;
            }
        }
        throw new RuntimeException("can`t find OrderOperateStatusEnum by code[" +code+"]");
    }

    private final String code;

    private final String message;
}
