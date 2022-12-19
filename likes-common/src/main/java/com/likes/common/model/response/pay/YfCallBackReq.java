package com.likes.common.model.response.pay;

/**
 * @ClassName YfCallBackReq
 * @Description 运
 * @Version 1.0
 **/
public class YfCallBackReq extends BaseCallBackReq {
    private String appId; //商户号
    private String tradeType; //交易类型
    private String passTradeNo;//微信/支付宝订单号
    private String tradeTime;//交易时间
    private String random;//随机字符串
    private String sign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
