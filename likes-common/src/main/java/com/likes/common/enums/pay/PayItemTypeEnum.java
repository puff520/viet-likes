package com.likes.common.enums.pay;

/**
 * 支付项操作类型枚举
 */
public enum PayItemTypeEnum {
    /** 添加 */
    ADD("ADD"),
    /** 未启用 */
    DISABLED("DISABLED"),
    /** 已启用 */
    ENABLE("ENABLE");

    private final String value;

    PayItemTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Integer getWorkStatus(String payItemType) {
        Integer receiveType = null;
        PayItemTypeEnum itemTypeEnum = PayItemTypeEnum.valueOf(payItemType);
        switch (itemTypeEnum) {
            case ADD:
            case ENABLE:
                receiveType = RechargeAccountWortEnum.ENABLED.getValue();
                break;
            case DISABLED:
                receiveType = RechargeAccountWortEnum.DISABLED.getValue();
                break;
        }
        return receiveType;
    }

}
