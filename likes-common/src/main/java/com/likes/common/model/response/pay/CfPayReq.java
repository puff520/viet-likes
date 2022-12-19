package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 超凡支付参数
 * @Version 1.0
 **/
public class CfPayReq extends BasePayReq {
    private Integer userId; //支付用户
    private String url; //请求的地址
    private String source; //请求来源
    private String sign; //签名
    private String md5Key; //MD5key
    private String signMethod  = "MD5";//签名算法，当前仅支持MD5
    private String txnTime;//订单发送时间 yyyyMMddHHmmss
    private String orderSubject;//订单标题,

    public String getOrderSubject() {
        return orderSubject;
    }

    public void setOrderSubject(String orderSubject) {
        this.orderSubject = orderSubject;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getSendIp() {
        return sendIp;
    }

    public void setSendIp(String sendIp) {
        this.sendIp = sendIp;
    }

    private String sendIp; //IP

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
