package com.likes.common.enums.ad;

public enum AdSiteTypeEnum {
    /**
     * 首页活动位置类型
     */
    ANDROID(1),
    IOS(2),
    WEB(3),
    H5(4);

    private int value;

    AdSiteTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
