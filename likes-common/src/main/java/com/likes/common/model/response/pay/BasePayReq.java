package com.likes.common.model.response.pay;

import java.io.Serializable;

/**
 * @ClassName BaseReq
 * @Description 支付参数基类
 * @Author eric
 * @Version 1.0
 **/
public class BasePayReq implements Serializable {
    private String orderNumber; //商户订单号
    private String channelType; //支付通道
    private Integer amount; //支付金额
    private String merchantNo; //商户号
    private String currency; //货币类型
    private String notifyUrl; //异步回调地址
    private String redirectUrl; //支付结束跳转地址
    private String paySetId;//标识
    private String accno; //用户标识
    private String wayType;//方式类型 NETBANK 网银转账  WECHAT 微信收款  ALIPAY 支付宝支付
    private Long providerId;//商户ID

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getPaySetId() {
        return paySetId;
    }

    public void setPaySetId(String paySetId) {
        this.paySetId = paySetId;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getWayType() {
        return wayType;
    }

    public void setWayType(String wayType) {
        this.wayType = wayType;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }
}
