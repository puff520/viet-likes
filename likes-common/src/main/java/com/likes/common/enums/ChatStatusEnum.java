package com.likes.common.enums;

/**
 * 聊天状态
 */
public enum ChatStatusEnum {
    ENABLE(0, "不允许聊天"),
    DISABLE(1, "允许聊天");
    private final int code;
    private final String desc;

    ChatStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static boolean isEnableStatus(int status) {
        return ChatStatusEnum.ENABLE.code == status;
    }

    public static boolean isDisableStatus(int status) {
        return ChatStatusEnum.DISABLE.code == status;
    }

    public boolean isEnableStatus() {
        return ChatStatusEnum.isEnableStatus(this.code);
    }

    public boolean isDisableStatus() {
        return ChatStatusEnum.isDisableStatus(this.code);
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
