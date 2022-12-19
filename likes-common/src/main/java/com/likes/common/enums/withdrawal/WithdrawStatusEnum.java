package com.likes.common.enums.withdrawal;

/**
 * 提现状态
 */
public enum WithdrawStatusEnum {

    DISABLE(0, "禁止提现"),
    ENABLE(1, "允许提现");
    private final int code;
    private final String desc;

    WithdrawStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static boolean isEnableStatus(int status) {
        return WithdrawStatusEnum.ENABLE.code == status;
    }

    public static boolean isDisableStatus(int status) {
        return WithdrawStatusEnum.DISABLE.code == status;
    }

    public boolean isEnableStatus() {
        return WithdrawStatusEnum.isEnableStatus(this.code);
    }

    public boolean isDisableStatus() {
        return WithdrawStatusEnum.isDisableStatus(this.code);
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
