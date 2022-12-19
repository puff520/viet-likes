package com.likes.common.enums;

/**
 * ClassName:    GiftTypeEnum
 * Package:    com.likes.common.constant
 * Description: 前端礼物类型枚举
 * Datetime:    2020/4/16   18:29
 * Author:   木鱼
 */
public enum GiftTypeEnum {


    ordinary(4,"普通礼物"),
    big(5,"大礼物"),
    svga(2,"SVGA");

    private final Integer value;
    private final String name;

    GiftTypeEnum(Integer value, String name) {
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
        GiftTypeEnum balanceChangeEnum = valueOf(value);
        return null == balanceChangeEnum ? "" : balanceChangeEnum.getName();
    }

    public static GiftTypeEnum valueOf(Integer value) {
        if (null == value) {
            return null;
        }
        GiftTypeEnum[] values = GiftTypeEnum.values();
        for (GiftTypeEnum balanceChangeEnum : values) {
            if (value.equals(balanceChangeEnum.getValue())) {
                return balanceChangeEnum;
            }
        }
        return null;
    }

}
