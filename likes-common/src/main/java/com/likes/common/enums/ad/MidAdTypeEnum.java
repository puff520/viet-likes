package com.likes.common.enums.ad;

public enum MidAdTypeEnum {
    /**
     * 首页中间位置
     */
    MID_LOCATION(1, "首页中间位置");

    private int value;
    private String desc;

    MidAdTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
