package com.likes.common.enums;

/**
 * 返水状态
 */
public enum BackwaterStatusEnum {
    ENABLE(0, "不允许"),
    DISABLE(1, "允许");
    private final int code;
    private final String desc;

    BackwaterStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static boolean isEnableStatus(int status) {
        return BackwaterStatusEnum.ENABLE.code == status;
    }

    public static boolean isDisableStatus(int status) {
        return BackwaterStatusEnum.DISABLE.code == status;
    }

    public boolean isEnableStatus() {
        return BackwaterStatusEnum.isEnableStatus(this.code);
    }

    public boolean isDisableStatus() {
        return BackwaterStatusEnum.isDisableStatus(this.code);
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
