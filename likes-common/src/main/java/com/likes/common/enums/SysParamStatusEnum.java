package com.likes.common.enums;

/**
 * 系统码启停状态（0：启用，9：停用）
 */
public enum SysParamStatusEnum {
    ENABLE(0, "启用"),
    DISABLED(9, "不启用");

    private final int value;
    private final String desc;

    SysParamStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static boolean isEnabled(Integer value) {
        return null != value && SysParamStatusEnum.ENABLE.getValue() == value;
    }

    public static boolean isDisabled(Integer code) {
        return null != code && SysParamStatusEnum.DISABLED.getValue() == code;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}
