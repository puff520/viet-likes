package com.likes.common.model.common;
/**
 * 支付方式 枚举
 * @author 瑞夫
 * @version 1.0
 * @date 2020/5/7
 */
public enum PaytypeEnum {
    NETBANK(1,"NETBANK","银行卡转账"),
    WECHAT(2,"WECHAT","微信"),
    ALIPAY(3,"ALIPAY","支付宝"),
    ;
    private Integer code;
    private String type;
    private String dec;

    PaytypeEnum(Integer code, String type, String dec) {
        this.code = code;
        this.type = type;
        this.dec = dec;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    /**
     * 根据type获取dec描述
     * @param type
     * @return
     */
    public static String getDecByType(String type) {
        for (PaytypeEnum orderTypeEnum : PaytypeEnum.values()) {
            if (orderTypeEnum.getType().equals(type)) {
                return orderTypeEnum.getDec();
            }
        }
        return null;
    }

    /**
     * 根据code获取type
     * @param code
     * @return
     */
    public static String getTypeByCode(Integer code) {
        for (PaytypeEnum orderTypeEnum : PaytypeEnum.values()) {
            if (orderTypeEnum.getCode().equals(code)) {
                return orderTypeEnum.getType();
            }
        }
        return null;
    }
}
