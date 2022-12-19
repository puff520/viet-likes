package com.likes.common.enums;

/**
 * ClassName:    GoldchangeEnum
 * Package:    com.likes.common.constant
 * Description: 直播服务器来源
 * Datetime:    2020/4/16   18:29
 * Author:   木鱼
 */
public enum ServerSourceEnum {

    TENCENT(0, "腾讯");

    private final Integer value;
    private final String name;

    ServerSourceEnum(Integer value, String name) {
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
        ServerSourceEnum balanceChangeEnum = valueOf(value);
        return null == balanceChangeEnum ? "" : balanceChangeEnum.getName();
    }

    public static ServerSourceEnum valueOf(Integer value) {
        if (null == value) {
            return null;
        }
        ServerSourceEnum[] values = ServerSourceEnum.values();
        for (ServerSourceEnum balanceChangeEnum : values) {
            if (value.equals(balanceChangeEnum.getValue())) {
                return balanceChangeEnum;
            }
        }
        return null;
    }

}
