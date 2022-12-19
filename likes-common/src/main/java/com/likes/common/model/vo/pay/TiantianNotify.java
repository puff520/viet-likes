package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud
 * @description: 天虎支付回调对象
 * @author: 芥黄
 * @create: 2020-06-20 14:55
 **/
@Scope("prototype")
@Component
public class TiantianNotify extends Notify {

    private String merchantId;

    private String outTradeNo;

    private String unitAmt;

    private String orderId;

    private String payOpt;

    private String createdTime;

    private String appId;

    private String confirmTime;

    private String nonceStr;

    private String sign;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getUnitAmt() {
        return unitAmt;
    }

    public void setUnitAmt(String unitAmt) {
        this.unitAmt = unitAmt;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayOpt() {
        return payOpt;
    }

    public void setPayOpt(String payOpt) {
        this.payOpt = payOpt;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
