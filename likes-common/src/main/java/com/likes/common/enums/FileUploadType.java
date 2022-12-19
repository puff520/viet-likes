package com.likes.common.enums;

/**
 * 文件上传类型
 */
public enum FileUploadType {
    IMAGE("image"),
    VIDEO("video"),
    WORD("word"),
    EXCEL("excel"),
    OTHER("else");

    FileUploadType(String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
