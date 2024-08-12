package org.jeecg.modules.common.enums;

import lombok.Getter;

/**
 * 保养结果枚举
 */
@Getter
public enum UpkeepResultEnum {
    /**
     * 保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养异常
     */
    WAIT("0", "待保养"),
    FINISH("1", "完成"),
    NOT_DONE("2", "未保养"),
    INCOMPLETE("3", "残件"),
    FAIL("9", "保养异常"),
    ;

    UpkeepResultEnum(String code, String message) {
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
    public static UpkeepResultEnum getByCode(String code) {
        for (UpkeepResultEnum e : UpkeepResultEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
