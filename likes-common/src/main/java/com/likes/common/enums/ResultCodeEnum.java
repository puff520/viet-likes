package com.likes.common.enums;

/**
 * 注册码类型枚举
 */
public enum ResultCodeEnum {
    REGISTER(3, "注册"),
    UPDATE_PASSWORD(4, "修改密码"),
    RETRIEVE_PASSWORD(1, "找回密码");

    private final int value;
    private final String desc;

    ResultCodeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
