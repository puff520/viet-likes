package com.likes.common.model.response.pay;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName BaseCallBackReq
 * @Description 支付回调参数基类
 * @Author
 * @Date 
 * @Version 1.0
 **/
public class BaseCallBackReq implements Serializable {

    private String notifyIp; // 回调ip
    private String orderNumber; //商户订单号(商户生成)
    private String channelType; //支付通道
    private BigDecimal amount; //实付金额
    private String currency; //货币类型：CNY
    private String tradeOrderNo; //支付订单号(第三方支付生成)
    private String status; //支付状态

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTradeOrderNo() {
        return tradeOrderNo;
    }

    public void setTradeOrderNo(String tradeOrderNo) {
        this.tradeOrderNo = tradeOrderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotifyIp() {
        return notifyIp;
    }

    public void setNotifyIp(String notifyIp) {
        this.notifyIp = notifyIp;
    }
}
