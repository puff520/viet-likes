package com.likes.common.model.response.pay;

import java.math.BigDecimal;

/**
 * @ClassName CsCallBackReq
 * @Description Fy
 * @Version 1.0
 **/
public class DdCallBackReq extends BaseCallBackReq {

    private String message; //成功则返回”000000”,失败返回原因
    private BigDecimal payAmount;//实际支付金额
    private String orderNo;//流水号
    private String payTime;//用户实际支付时间
    private String attachData;//应用ID
    private String userId;
    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAttachData() {
        return attachData;
    }

    public void setAttachData(String attachData) {
        this.attachData = attachData;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
