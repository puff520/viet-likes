package com.likes.common.model.vo.pay;

import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud
 * @description: 超跑支付回调对象
 * @author: 芥黄
 * @create: 2020-06-17 09:53
 **/
@Component
public class ChaopaoNotify extends Notify {
    private String msg;
    private String code;
    private String context;
    private String sign;
    private String orderNo;
    private String merchNo;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }
}
