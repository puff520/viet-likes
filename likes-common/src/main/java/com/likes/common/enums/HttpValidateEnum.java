package com.likes.common.enums;

/**
 * http签名访问相关
 */
public enum HttpValidateEnum {

    SECRETKEY_401("401", "no Access secretkey"),
    NOSIGN_401("401", "no Access signature"),
    ERRSIGN_401("401", "Access signature error"),
    NO_ACCNO_401("401", "因为一股来自东方的神秘力量导致无法识别您的身份，不如重新登录试试~"),
    SINGLE_LOGIN_401("401", "您的帐号已在另一个设备上登录_401"),
    NO_ACCTOKEN_401("401", "权限不足"),
    NO_MANAGE_401("401", "请使用管理账号登录"),
    TOO_MANYERR_401("401", "非法访问"),
    SIGN_ERR_COUNT("SIGN_ERR_COUNT", "http签名访问最大错误次数"),
    SIGN_ERR_RECORD_PREFIX("SIGN_ERR_RECORD_PREFIX", "当前http访问次数缓存前缀");

    private String key;
    private String value;

    HttpValidateEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
