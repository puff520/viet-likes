package com.likes.common.enums;

/**
 * ClassName:    MsgTypeEnum
 * Package:    com.likes.common.enums
 * Description:
 * Datetime:    2020/5/15   17:20
 * Author:   木鱼
 */
public enum MsgTypeEnum {

    /**
     * 短信登陆
     */
    LOGIN(1, "短信登陆"),
    /**
     * 找回密码
     */
    FINDPASSWORD(2,"找回密码"),
    /**
     * 注册
     */
    REGISTER(3,"注册"),
    /**
     * 修改密码
     */
    CHANGEPASSWORD(4,"修改密码"),
    /**
     * 提醒
     */
    TIPS(8,"提醒"),
    /**
     * 其他普通短信
     */
    OTHER(9,"其他普通短信");

    private final Integer value;
    private final String name;

    MsgTypeEnum(Integer value, String name) {
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
