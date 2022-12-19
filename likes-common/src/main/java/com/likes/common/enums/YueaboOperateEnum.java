package com.likes.common.enums;


public enum YueaboOperateEnum {

    INTO(1, "转入"),

    OUT(2, "转出"),
    EARN(3, "收益"),
    EARN_BACK(4, "收益回退"),
    DEDUCT(5, "扣除");

    private final Integer value;
    private final String name;

    YueaboOperateEnum(Integer value, String name) {
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
        YueaboOperateEnum balanceChangeEnum = valueOf(value);
        return null == balanceChangeEnum ? "" : balanceChangeEnum.getName();
    }

    public static YueaboOperateEnum valueOf(Integer value) {
        if (null == value) {
            return null;
        }
        YueaboOperateEnum[] values = YueaboOperateEnum.values();
        for (YueaboOperateEnum balanceChangeEnum : values) {
            if (value.equals(balanceChangeEnum.getValue())) {
                return balanceChangeEnum;
            }
        }
        return null;
    }


}
