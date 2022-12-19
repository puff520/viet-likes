package com.likes.common.enums;

/**
 * 跳蛋用品狀態
 */
public enum ToolStatusEnum {
    /**
     * 在线
     */
    ON(0, "在线"),
    /**
     * 离线
     */
    DOWN(9, "下线");

    private final Integer value;
    private final String name;

    ToolStatusEnum(Integer value, String name) {
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
