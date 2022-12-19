package com.likes.common.enums.pay;

/**
 * 充值类型
 */
public enum RechargeTypeEnum {
    RECHARGE_MANUAL(1, "人工充值"),
    RECHARGE_ONLINE(2, "在线充值"),
    RECHARGE_THIRD(3, "第三方充值");

    private final int code;
    private final String name;

    RechargeTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
