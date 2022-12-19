package com.likes.common.enums;


public enum AssetOperateEnum {

    DEPOSIT(1, "购买理财产品"),

    TAKE(2, "提出到余额宝");

    private final Integer value;
    private final String name;

    AssetOperateEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getNameByValue(int value) {
        AssetOperateEnum balanceChangeEnum = valueOf(value);
        return null == balanceChangeEnum ? "" : balanceChangeEnum.getName();
    }

    public static AssetOperateEnum valueOf(Integer value) {
        if (null == value) {
            return null;
        }
        AssetOperateEnum[] values = AssetOperateEnum.values();
        for (AssetOperateEnum balanceChangeEnum : values) {
            if (value.equals(balanceChangeEnum.getValue())) {
                return balanceChangeEnum;
            }
        }
        return null;
    }


}
