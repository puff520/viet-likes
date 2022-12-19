package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud
 * @description: 阿里支付回调对象
 * @author: 芥黄
 * @create: 2020-07-14 18:15
 **/
@Scope("prototype")
@Component
public class AlizhifuNotify extends Notify {

    private String merchantNum;

    private String orderNo;

    private String platformOrderNo;

    private String amount;

    private String attch;

    private String state;

    private String payTime;

    private String actualPayAmount;

    private String sign;

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPlatformOrderNo() {
        return platformOrderNo;
    }

    public void setPlatformOrderNo(String platformOrderNo) {
        this.platformOrderNo = platformOrderNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAttch() {
        return attch;
    }

    public void setAttch(String attch) {
        this.attch = attch;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getActualPayAmount() {
        return actualPayAmount;
    }

    public void setActualPayAmount(String actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
