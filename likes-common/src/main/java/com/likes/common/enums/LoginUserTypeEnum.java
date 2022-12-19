package com.likes.common.enums;

/**
 * 登录账号类型枚举
 */
public enum LoginUserTypeEnum {

    /**
     * 普通会员
     */
    ORDINARY(1, "普通会员"),
    /**
     * 主播
     */
    ANCHOR(2, "主播"),
    /**
     * 家族长
     */
    FAMILYHEAD(3, "家族长"),
    /**
     * 第三方登录
     */
    THIRD(7, "第三方登录"),
    /**
     * 运营后台管理员
     */
    BACKADMIN(8, "运营后台管理员"),
    /**
     * 服务注册中心管理员
     */
    EUREKA(9, "服务注册中心管理员"),

    /**
     * 代充会员
     */
    AGENT(4, "代充人"),
    /**
     * 聚合站点后台管理员
     */
    AGGREGATION(10, "聚合站点后台管理员"),
    /**
     * 试玩账号
     */
    TRIALACCOUNT(11, "普通会员");

    private Integer code;
    private String value;

    LoginUserTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 是否为客户端类型用户
     */
    public static boolean isClientUser(Integer type) {
        return ORDINARY.getCode().equals(type) || ANCHOR.getCode().equals(type);
    }

    /**
     * 是否为用户
     */
    public static boolean isUser(Integer type) {
        return ORDINARY.getCode().equals(type) || TRIALACCOUNT.getCode().equals(type);
    }

    /**
     * 是否为主播端用户类型
     */
    public static boolean isUserType(Integer type) {
        return ORDINARY.getCode().equals(type) || ANCHOR.getCode().equals(type) || FAMILYHEAD.getCode().equals(type) || TRIALACCOUNT.getCode().equals(type);
    }

    /**
     * 是否为管理员用户
     */
    public static boolean isManageUser(Integer type) {
        return BACKADMIN.getCode().equals(type);
    }

    /**
     * 是否为主播用户
     */
    public static boolean isAnchor(Integer type) {
        return ANCHOR.getCode().equals(type);
    }

    /**
     * 是否为主播用户
     */
    public static boolean isTrailAccount(Integer type) {
        return TRIALACCOUNT.getCode().equals(type);
    }

    public static boolean isTrailAccountString(String type) {
        return TRIALACCOUNT.getCode().equals(Integer.parseInt(type));
    }

    /**
     * 是否为家族长
     */
    public static boolean isFamilyhead(Integer type) {
        return FAMILYHEAD.getCode().equals(type);
    }

    /**
     * 是否为代充人
     */
    public static boolean isRepayUser(Integer type) {
        return AGENT.getCode().equals(type);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
