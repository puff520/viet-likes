package com.likes.common.enums.pay;

import java.util.ArrayList;
import java.util.List;

/**
 * 充值状态枚举
 */
public enum RechargeAccountWortEnum {

    DISABLED(0, "禁用"),
    ENABLED(1, "启用"),
    AUTO(2, "自动");

    private final int value;
    private final String name;

    RechargeAccountWortEnum(int code, String name) {
        this.value = code;
        this.name = name;
    }

    public static List<RechargeAccountWortEnum> getAll() {
        List<RechargeAccountWortEnum> list = new ArrayList<>();
        for (RechargeAccountWortEnum wortEnum : RechargeAccountWortEnum.values()) {
            list.add(wortEnum);
        }
        return list;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
