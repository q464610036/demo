package org.jeecg.modules.common.enums;

import lombok.Getter;

/**
 * 保养工单流程
 */
@Getter
public enum UpkeepOrderFlowEnum {
    /**
     * 保养工单流程
     */
    APPROVE("1", "签核"),
    RESULT("2", "结果记录"),
    TEST("3", "点检"),
    MQC("4", "MQC"),
    FIRST("5", "首件"),
    OUTPUT("6", "量产"),
    ;

    UpkeepOrderFlowEnum(String code, String message) {
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
    public static UpkeepOrderFlowEnum getByCode(String code) {
        for (UpkeepOrderFlowEnum e : UpkeepOrderFlowEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
