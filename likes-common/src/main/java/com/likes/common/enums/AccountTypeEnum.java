package com.likes.common.enums;

/**
 * 提现账号类型枚举
 */
public enum AccountTypeEnum {
    /**
     * 支付宝
     */
    ALIPAY(1, "支付宝"),
    /**
     * 微信
     */
    WECHAT(2, "微信"),
    /**
     * 银联
     */
    UNIONPAY(3, "银联");

    private int code;
    private String value;

    AccountTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

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
