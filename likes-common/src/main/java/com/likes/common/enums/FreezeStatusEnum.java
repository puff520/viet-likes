package com.likes.common.enums;

/**
 * 冻结状态
 */
public enum FreezeStatusEnum {
    ENABLE(0, "不冻结"),
    DISABLE(1, "冻结");
    private final int code;
    private final String desc;

    FreezeStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 是否为非冻结状态
     *
     * @param code
     * @return
     */
    public static boolean isFreeStatus(Integer code) {
        return null != code && FreezeStatusEnum.ENABLE.getCode() == code;
    }

    /**
     * 是否为冻结状态
     *
     * @param code
     * @return
     */
    public static boolean isFreezeStatus(Integer code) {
        return null != code && FreezeStatusEnum.DISABLE.getCode() == code;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
