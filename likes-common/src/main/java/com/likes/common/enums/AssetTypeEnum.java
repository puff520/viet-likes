package com.likes.common.enums;


public enum AssetTypeEnum {

    DAILY(1L, "每日(余额宝本身)"),

    SEVEN_DAY(2L, "七日"),
    TWO_WEEK(3L, "两周"),
    MONTH(4L, "每月");

    private final Long value;
    private final String name;

    AssetTypeEnum(Long value, String name) {
        this.value = value;
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getNameByValue(int value) {
        AssetTypeEnum balanceChangeEnum = valueOf(value);
        return null == balanceChangeEnum ? "" : balanceChangeEnum.getName();
    }

    public static AssetTypeEnum valueOf(Integer value) {
        if (null == value) {
            return null;
        }
        AssetTypeEnum[] values = AssetTypeEnum.values();
        for (AssetTypeEnum balanceChangeEnum : values) {
            if (value.equals(balanceChangeEnum.getValue())) {
                return balanceChangeEnum;
            }
        }
        return null;
    }


}
