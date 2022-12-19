package com.likes.common.enums.withdrawal;

/**
 * 提现操作类型枚举
 */
public enum WithdrawOpTypeEnum {

    ONLINE(1, "线上充值"),
    OFFLINE(2, "线下转账");

    private final int value;
    private final String desc;

    WithdrawOpTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static String getOpTypeTextByType(Integer opType) {
        if (null == opType || opType <= 0) {
            return "";
        }
        if (ONLINE.getValue() == opType) {
            return ONLINE.getDesc();
        }
        if (OFFLINE.getValue() == opType) {
            return OFFLINE.getDesc();
        }
        return "";
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
