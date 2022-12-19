package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud
 * @description: 伯乐支付回调对象
 * @author: 芥黄
 * @create: 2020-06-20 14:55
 **/
@Scope("prototype")
@Component
public class BoleNotify extends Notify{

    private String orderNo;

    private String payMoney;

    private String shOrderNo;



    private String sign;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getShOrderNo() {
        return shOrderNo;
    }

    public void setShOrderNo(String shOrderNo) {
        this.shOrderNo = shOrderNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
