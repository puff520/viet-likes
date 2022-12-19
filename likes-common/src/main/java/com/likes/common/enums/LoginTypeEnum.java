package com.likes.common.enums;

/**
 * 登录类型枚举
 */
public enum LoginTypeEnum {
    QQ(1, "QQ"),
    WEICHAT(2, "微信"),
    WEIBO(3, "微博"),
    PHONE(4, "手机号"),
    ACCOUNT(5, "账号");

    LoginTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static boolean isThirdLoginType(int code) {
        return LoginTypeEnum.QQ.getCode() == code
                || LoginTypeEnum.WEICHAT.getCode() == code
                || LoginTypeEnum.WEIBO.getCode() == code;
    }

    private int code;
    private String value;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
