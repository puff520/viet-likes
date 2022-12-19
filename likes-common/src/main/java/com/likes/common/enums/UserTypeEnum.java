package com.likes.common.enums;

/**
 * 原 CPT 用户类型枚举
 */
public enum UserTypeEnum {
    VISITOR_REGISTER(1, "注册游客，未绑定手机号"),
    VISITOR_RECHARGE(2, "充值游客，未绑定手机号，有充值"),
    REGISTER(3, "注册用户，绑定手机号"),
    RECHARGE(4, "充值用户，绑定手机号，充过值，平台赠送彩金也算充值"),
    TEST(5, "测试用户");

    private final int value;
    private final String desc;

    UserTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static boolean isVisitorRegisterUser(Integer type) {
        return null != type && (VISITOR_REGISTER.getValue() == type || VISITOR_RECHARGE.getValue() == type);
    }

    public static boolean isRegisterUser(Integer type) {
        return null != type && REGISTER.getValue() == type;
    }

    public static UserTypeEnum valueOfCode(Integer value) {
        if (null == value) {
            return null;
        }
        for (UserTypeEnum type : UserTypeEnum.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
