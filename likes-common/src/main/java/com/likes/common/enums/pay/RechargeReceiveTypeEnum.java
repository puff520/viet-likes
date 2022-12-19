package com.likes.common.enums.pay;

import java.util.ArrayList;
import java.util.List;

/**
 * 充值收款方式枚举
 */
public enum RechargeReceiveTypeEnum {

    ALIPAY2BANK(1, "银行卡转银行卡", true),
    WECHART2BANK(2, "微信转银行卡", false),
    BANK2BANK(3, "支付宝转银行卡", true);

    private final int id;
    private final String name;
    private final boolean isWork;

    RechargeReceiveTypeEnum(int id, String name, boolean isWork) {
        this.name = name;
        this.id = id;
        this.isWork = isWork;
    }

    public static List<RechargeReceiveTypeEnum> getReceiveTypes() {
        List<RechargeReceiveTypeEnum> result = new ArrayList<>();
        for (RechargeReceiveTypeEnum item : RechargeReceiveTypeEnum.values()) {
            if (item.isWork) {
                result.add(item);
            }
        }
        return result;
    }

    public static RechargeReceiveTypeEnum valueOfId(int id) {
        for (RechargeReceiveTypeEnum type : RechargeReceiveTypeEnum.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public static String fetchNameOfId(int id) {
        for (RechargeReceiveTypeEnum type : RechargeReceiveTypeEnum.values()) {
            if (type.getId() == id) {
                return type.getName();
            }
        }
        return "";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
