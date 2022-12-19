package com.likes.common.model.vo.pay;

import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud
 * @description: 超跑支付回调对象
 * @author: 芥黄
 * @create: 2020-06-17 09:53
 **/
@Component
public class AeightNotify extends Notify {
    private String merchant_no;
    private String order_no;
    private String platform_order_no;
    private String order_money;
    private String pay_time;
    private String pay_money;
    private String order_state;
    private String extend;
    private String sign;

    public String getMerchant_no() {
        return merchant_no;
    }

    public void setMerchant_no(String merchant_no) {
        this.merchant_no = merchant_no;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getPlatform_order_no() {
        return platform_order_no;
    }

    public void setPlatform_order_no(String platform_order_no) {
        this.platform_order_no = platform_order_no;
    }

    public String getOrder_money() {
        return order_money;
    }

    public void setOrder_money(String order_money) {
        this.order_money = order_money;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
