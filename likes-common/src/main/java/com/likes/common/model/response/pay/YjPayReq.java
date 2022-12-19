package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 一亿伽支付参数
 * @Version 1.0
 **/
public class YjPayReq extends BasePayReq {
    private Integer userId; //支付用户
    private String url; //请求的地址
    private String source; //请求来源
    private String sign; //签名
    private String md5Key; //MD5key
    private String payType  = "2";//1或者2；1为旧版本，不用转payChannel参数；2为新版本，需要传payChannel，同一个商户可以开多个通道，建议使用2
    private String orderTime;//订单发送时间 yyyyMMddHHmmss
    private String subject = "zf";//订单名称

    public Integer getUserId() {
        return userId;
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

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
