package com.likes.common.model.response.pay;

/**
 * @ClassName CsCallBackReq
 * @Description Fy
 * @Version 1.0
 **/
public class FyCallBackReq extends BaseCallBackReq {

    private String tradeType; //交易类型
    private String passTradeNo;//通道交易号
    private String tradeTime;//交易时间
    private String random;//随机字符串
    private String appId;//应用ID
    private String sign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPassTradeNo() {
        return passTradeNo;
    }

    public void setPassTradeNo(String passTradeNo) {
        this.passTradeNo = passTradeNo;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

}
