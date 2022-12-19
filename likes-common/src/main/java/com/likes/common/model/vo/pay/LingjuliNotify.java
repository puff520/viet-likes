package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud
 * @description: 零距离支付回调参数
 * @author: Bobi
 * @create: 2020-06-13 17:21
 **/
@Scope("prototype")
@Component
public class LingjuliNotify extends Notify {
    /**
     * 【商户编号】
     **/
    private String customer_no;

    /**
     * 【订单号】
     **/
    private String customer_order;

    /**
     * 【订单金额】
     **/
    private String amount;

    /**
     * 【交易流水号】
     **/
    private String trading_num;

    /**
     * 【交易时间】
     **/
    private String trading_time;
    /**
     * 【交易状态】
     *
     **/
    private String trading_code;
    /**
     * 【扩展返回】
     *
     **/
    private String append_fields;

    private String sign_md5;

    public String getCustomer_no() {
        return customer_no;
    }

    public void setCustomer_no(String customer_no) {
        this.customer_no = customer_no;
    }

    public String getCustomer_order() {
        return customer_order;
    }

    public void setCustomer_order(String customer_order) {
        this.customer_order = customer_order;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTrading_num() {
        return trading_num;
    }

    public void setTrading_num(String trading_num) {
        this.trading_num = trading_num;
    }

    public String getTrading_time() {
        return trading_time;
    }

    public void setTrading_time(String trading_time) {
        this.trading_time = trading_time;
    }

    public String getTrading_code() {
        return trading_code;
    }

    public void setTrading_code(String trading_code) {
        this.trading_code = trading_code;
    }

    public String getAppend_fields() {
        return append_fields;
    }

    public void setAppend_fields(String append_fields) {
        this.append_fields = append_fields;
    }

    public String getSign_md5() {
        return sign_md5;
    }

    public void setSign_md5(String sign_md5) {
        this.sign_md5 = sign_md5;
    }
}
