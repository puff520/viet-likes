package com.likes.common.enums;

/**
 * 文件上传到的文件夹位置
 */
public enum FileUploadCode {
    HEAD("head", ""),
    LH_IMAGE("lh-image", "六合图库"),
    BANK_ICON("bank-icon", "银行图标"),
    AD_PHOTO("ad-photo", "广告活动图片"),
    PUSH_IMAGE("push-image", "推单图片"),
    CHAT_IMAGE("chat-image", "聊天相关图片");

    FileUploadCode(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private final String type;
    private final String desc;

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
