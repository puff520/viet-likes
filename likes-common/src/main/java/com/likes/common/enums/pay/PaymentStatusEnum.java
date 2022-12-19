package com.likes.common.enums.pay;

/**
 * 充值状态
 */
public enum PaymentStatusEnum {
    DISABLE(0, "禁止充值"),
    ENABLE(1, "允许充值");
    private final int code;
    private final String desc;

    PaymentStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static boolean isEnableStatus(int status) {
        return PaymentStatusEnum.ENABLE.code == status;
    }

    public static boolean isDisableStatus(int status) {
        return PaymentStatusEnum.DISABLE.code == status;
    }

    public boolean isEnableStatus() {
        return PaymentStatusEnum.isEnableStatus(this.code);
    }

    public boolean isDisableStatus() {
        return PaymentStatusEnum.isDisableStatus(this.code);
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
