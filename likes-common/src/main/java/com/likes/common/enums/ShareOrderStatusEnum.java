package com.likes.common.enums;

/**
 * 晒单状态
 */
public enum ShareOrderStatusEnum {
    DISABLE(0, "禁止晒单"),
    ENABLE(1, "允许晒单");
    private final int code;
    private final String desc;

    ShareOrderStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static boolean isEnableStatus(int status) {
        return ShareOrderStatusEnum.ENABLE.code == status;
    }

    public static boolean isDisableStatus(int status) {
        return ShareOrderStatusEnum.DISABLE.code == status;
    }

    public boolean isEnableStatus() {
        return ShareOrderStatusEnum.isEnableStatus(this.code);
    }

    public boolean isDisableStatus() {
        return ShareOrderStatusEnum.isDisableStatus(this.code);
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
