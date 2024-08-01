package org.jeecg.modules.common.enums;

import lombok.Getter;

/**
 * 保养工单审批状态枚举
 */
public enum UpkeepOrderApproveStatusEnum {
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

    UpkeepOrderApproveStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Getter
    private final String code;

    @Getter
    private final String message;

    /**
     * 根据code获得枚举
     * @param code 枚举code
     * @return 枚举
     */
    public static UpkeepOrderApproveStatusEnum getByCode(String code) {
        for (UpkeepOrderApproveStatusEnum e : UpkeepOrderApproveStatusEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
