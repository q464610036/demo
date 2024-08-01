package org.jeecg.modules.common.enums;

import lombok.Getter;

/**
 * 保养工单节点操作状态枚举
 */
public enum UpkeepOrderOperateStatusEnum {
    /**
     * 操作状态：1=取消保养，2=通过，3=确定保养，8=驳回，9=不通过
     */
    CANCEL("1", "取消保养"),
    APPROVE("2", "通过"),
    CONFIRM_CANCEL("3", "确定取消"),
    DENY_CANCEL("8", "驳回"),
    ;

    UpkeepOrderOperateStatusEnum(String code, String message) {
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
    public static UpkeepOrderOperateStatusEnum getByCode(String code) {
        for (UpkeepOrderOperateStatusEnum e : UpkeepOrderOperateStatusEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
