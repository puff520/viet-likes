package com.likes.common.enums;

/**
 * controller 方法拦截校验错误类型
 */
public enum ActionCheckErrorTypeEnum {

    TOKEN_IS_NULL(0, "token为空"),
    TOKEN_EXPIRES(1, "用户登录状态已过期"),
    USER_FREEZE(2, "用户冻结");

    private final int value;
    private final String desc;

    ActionCheckErrorTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static ActionCheckErrorTypeEnum valueOfCode(Integer value) {
        if (null == value || value < 0) {
            return null;
        }
        ActionCheckErrorTypeEnum[] types = ActionCheckErrorTypeEnum.values();
        for (ActionCheckErrorTypeEnum type : types) {
            if (value == type.getValue()) {
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
