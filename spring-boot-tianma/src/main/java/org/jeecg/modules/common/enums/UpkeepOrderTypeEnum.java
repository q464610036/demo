package org.jeecg.modules.common.enums;

import lombok.Getter;

/**
 * 保养工单类型枚举
 */
@Getter
public enum UpkeepOrderTypeEnum {
    /**
     * 工单类型：1=保养单，2=取消保养单
     */
    UPKEEP("1", "保养单"),
    CANCEL_UPKEEP("2", "取消保养单"),
    ;

    UpkeepOrderTypeEnum(String code, String message) {
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
    public static UpkeepOrderTypeEnum getByCode(String code) {
        for (UpkeepOrderTypeEnum e : UpkeepOrderTypeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
