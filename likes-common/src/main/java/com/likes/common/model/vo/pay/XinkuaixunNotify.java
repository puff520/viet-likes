package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 新意支付
 *
 * @author 瑞夫
 * @version 1.0
 * @date 2020/7/22
 */
@Scope("prototype")
@Component
public class XinkuaixunNotify extends Notify {

    /**
     * 商户订单ID
     */
    private String id;
    /**
     * 商户ID
     */
    private String mchId;
    /**
     * 平台订单号
     */
    private String transactionId;
    /**
     * 商户订单号
     */
    private String channel;
    /**
     * 订单金额
     */
    private String amount;
    /**
     * 支付通道
     */
    private String status;
    /**
     * 异步通知URL
     */
    private String userPayAmount;

    /**
     * md5签名串
     */
    private String sign;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserPayAmount() {
        return userPayAmount;
    }

    public void setUserPayAmount(String userPayAmount) {
        this.userPayAmount = userPayAmount;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
