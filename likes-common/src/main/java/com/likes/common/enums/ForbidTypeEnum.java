package com.likes.common.enums;

/**
 * 禁言/禁上线管理状态
 */
public enum ForbidTypeEnum {

    /**
     * 释放
     */
    REALSE(0, "释放"),

    /**
     * 禁言
     */
    SPEAK(1, "禁言"),
    /**
     * 禁止进入直播间
     */
    IN(2, "禁止进入直播间");

    private final Integer value;
    private final String name;

    ForbidTypeEnum(Integer value, String name) {
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
