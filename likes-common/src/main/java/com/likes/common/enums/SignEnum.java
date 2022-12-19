package com.likes.common.enums;

/**
 * @author abu
 */
public enum SignEnum {
    SIGN("SIGN", "访问超级后台token header"),
    SIGNTOKEN("SIGNTOKEN", "访问站点访问token header"),
    SECRETKEY("SECRETKEY", "访问站点访问加密key header");

    private String name;
    private String value;

    SignEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
