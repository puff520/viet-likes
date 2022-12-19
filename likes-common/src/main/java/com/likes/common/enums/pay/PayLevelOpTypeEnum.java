package com.likes.common.enums.pay;

public enum PayLevelOpTypeEnum {

    ADD(1, "添加"),
    UPDATE(2, "修改"),
    DELETE(3, "删除"),
    MANUAL_SET(4, "手动设置用户支付层级");

    private final int value;
    private final String desc;

    PayLevelOpTypeEnum(int value, String desc) {
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
