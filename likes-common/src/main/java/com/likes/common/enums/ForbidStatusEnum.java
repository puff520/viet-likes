package com.likes.common.enums;

/**
 * 禁止状态
 */
public enum ForbidStatusEnum {

    /**
     * 解禁
     */
    RELEASE(0,"解禁"),
    /**
     * 临时
     */
    TEMPORARY(1, "临时"),
    /**
     * 永久
     */
    FOREVER(2, "永久");

    private final Integer value;
    private final String name;

    ForbidStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
