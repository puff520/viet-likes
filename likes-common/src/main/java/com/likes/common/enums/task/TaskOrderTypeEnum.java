package com.likes.common.enums.task;

public enum TaskOrderTypeEnum {

    UNDERWAY(1, "进行中"),
    DONE    (2, "已完成");

    private final Integer value;
    private final String desc;

    TaskOrderTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


}
