package com.likes.common.enums;

/**
 * ClassName:    MessageTypeEnum
 * Package:    com.likes.common.enums
 * Description:
 * Datetime:    2020/5/8   20:28
 * Author:   木鱼
 */
public enum MessageTypeEnum {

    /**
     * 直播间滚动消息
     */
    ROLL(1, "滚动消息"),
    APP_START(2, "首页公告");


    private final Integer value;
    private final String name;

    MessageTypeEnum(Integer value, String name) {
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
