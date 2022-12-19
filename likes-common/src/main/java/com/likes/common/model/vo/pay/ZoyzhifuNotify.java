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
public class ZoyzhifuNotify extends Notify{

    private String merchNo;

    private String netway;

    private String orderNo;

    private String amount;

    private String product;

    private String statusCode;

    private String payDate;

    private String sign;

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getNetway() {
        return netway;
    }

    public void setNetway(String netway) {
        this.netway = netway;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
