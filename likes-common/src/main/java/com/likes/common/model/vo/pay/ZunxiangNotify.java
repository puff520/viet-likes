package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud
 * @description: 尊享支付回调对象
 * @author: 芥黄
 * @create: 2020-06-20 14:55
 **/
@Scope("prototype")
@Component
public class ZunxiangNotify extends Notify{

    private String platform_product_id;

    private String status;

    private String pay_order_id;

    private String out_uid;

    private String out_trade_no;

    private String amount;

    private String real_amount;

    private String cny_rate;

    private String exchange_rate;

    private String notify_time;

    private String sign;

    public String getPlatform_product_id() {
        return platform_product_id;
    }

    public void setPlatform_product_id(String platform_product_id) {
        this.platform_product_id = platform_product_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPay_order_id() {
        return pay_order_id;
    }

    public void setPay_order_id(String pay_order_id) {
        this.pay_order_id = pay_order_id;
    }

    public String getOut_uid() {
        return out_uid;
    }

    public void setOut_uid(String out_uid) {
        this.out_uid = out_uid;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReal_amount() {
        return real_amount;
    }

    public void setReal_amount(String real_amount) {
        this.real_amount = real_amount;
    }

    public String getCny_rate() {
        return cny_rate;
    }

    public void setCny_rate(String cny_rate) {
        this.cny_rate = cny_rate;
    }

    public String getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(String exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
