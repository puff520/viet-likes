package com.likes.common.enums;

/**
 * ClassName:    RankTypeEnum
 * Package:    com.likes.common.enums
 * Description:
 * Datetime:    2020/5/8   19:28
 * Author:   木鱼
 */
public enum RankTypeEnum {

    /**
     * 日榜
     */
    DAY(1, "日榜"),
    /**
     * 周榜
     */
    WEEK(2, "周榜"),
    /**
     * 月榜
     */
    MONTH(3,"月榜");


    private final Integer value;
    private final String name;

    RankTypeEnum(Integer value, String name) {
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
