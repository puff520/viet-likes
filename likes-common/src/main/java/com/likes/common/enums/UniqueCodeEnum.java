package com.likes.common.enums;

/**
 * 随机码枚举
 */
public enum UniqueCodeEnum {

    USER_UNIQUE("user_unique_code", 8, "用户唯一随机码"),

    USER_INVITE("user_invite_code", 6, "用户邀请唯一随机码"),

    ANCHOR_UNIQUE("anchor_user_unique_code", 8, "主播邀请唯一随机码"),

    ANCHOR_INVITE("anchor_user_invite_code", 6, "主播邀请唯一随机码");

    /** 表名 */
    private final String name;

    /** 产生的code长度 */
    private final int length;

    /** 描述 */
    private final String desc;

    UniqueCodeEnum(String name, int length, String desc) {
        this.name = name;
        this.length = length;
        this.desc = desc;
    }

    public static UniqueCodeEnum valueOfName(String name) {
        for (UniqueCodeEnum uniqueCodeEnum : UniqueCodeEnum.values()) {
            if (uniqueCodeEnum.getName().equals(name)) {
                return uniqueCodeEnum;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public String getDesc() {
        return desc;
    }

}
