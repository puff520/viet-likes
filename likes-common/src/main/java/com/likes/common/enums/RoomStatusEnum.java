package com.likes.common.enums;

/**
 * 直播房间状态
 */
public enum RoomStatusEnum {
    /**
     * 在线
     */
    ON(0, "在线"),
    /**
     * 离线
     */
    DOWN(9, "离线");

    private final Integer value;
    private final String name;

    RoomStatusEnum(Integer value, String name) {
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
