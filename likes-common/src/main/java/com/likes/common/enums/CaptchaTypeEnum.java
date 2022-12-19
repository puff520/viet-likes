package com.likes.common.enums;

/**
 * 注册码类型枚举
 */
public enum CaptchaTypeEnum {
    REGISTER(3, "注册"),
    UPDATE_PASSWORD(4, "修改密码"),
    UPDATE_MOBILE(1, "修改手机号");

    private final int value;
    private final String desc;

    CaptchaTypeEnum(int value, String desc) {
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
