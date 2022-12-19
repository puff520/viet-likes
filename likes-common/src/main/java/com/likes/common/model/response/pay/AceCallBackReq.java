package com.likes.common.model.response.pay;

/**
 * @ClassName AaCallBackReq
 * @Description
 * @Version 1.0
 **/
public class AceCallBackReq extends BaseCallBackReq {
    private String orderStatusMsg; //支付状态说明
    private String payTime;//支付时间
    private String sign;


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOrderStatusMsg() {
        return orderStatusMsg;
    }

    public void setOrderStatusMsg(String orderStatusMsg) {
        this.orderStatusMsg = orderStatusMsg;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}
