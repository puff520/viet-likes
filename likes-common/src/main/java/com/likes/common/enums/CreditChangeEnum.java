package com.likes.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public enum CreditChangeEnum {

    RECHARGE(1, "充值"),

    TASK(2, "领取任务"),

    RECOMMEND(3, "推荐用户"),

    RECOMMEND_RECHARGE(4, "推荐充值");


    private final Integer value;
    private final String name;

    CreditChangeEnum(Integer value, String name) {
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
        CreditChangeEnum balanceChangeEnum = valueOf(value);
        return null == balanceChangeEnum ? "" : balanceChangeEnum.getName();
    }

    public static CreditChangeEnum valueOf(Integer value) {
        if (null == value) {
            return null;
        }
        CreditChangeEnum[] values = CreditChangeEnum.values();
        for (CreditChangeEnum balanceChangeEnum : values) {
            if (value.equals(balanceChangeEnum.getValue())) {
                return balanceChangeEnum;
            }
        }
        return null;
    }



}
