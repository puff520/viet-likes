package com.likes.common.enums;

/**
 * ClassName:    CertStatusEnum
 * Package:    com.likes.common.enums
 * Description:
 * Datetime:    2020/5/1   19:08
 * Author:   木鱼
 */
public enum CertStatusEnum {

    /**
     * 已通过
     */
    PASS(0, "已通过"),
    /**
     * 待审核
     */
    REVIEW(1, "待审核"),
    /**
     * 未认证
     */
    UNAUTHORIZED(8,"未认证"),
    /**
     * 未通过
     */
    NOPASS(9, "未通过");

    private int code;
    private String value;

    CertStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
