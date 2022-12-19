package com.likes.common.enums;

/**
 * member_balance_change表的type字段对应的值
 */
public enum PayTypeEnum {

    OFFLINE(1, "线下支付"),
    MANUAL(2, "人工入款"),
    ONLINE(3, "线上支付"),
    ACTIVE(4, "活动礼金");

    private final Integer value;
    private final String name;

    PayTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
