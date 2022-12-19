package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud
 * @description: 大盛付支付回调对象
 * @author: 芥黄
 * @create: 2020-07-08 18:15
 **/
@Scope("prototype")
@Component
public class DashengfuNotify extends Notify {

    private String pay_time;

    private String money;

    private String pay_money;

    private String order_sn;

    private String sys_order_sn;

    private String sign;

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getSys_order_sn() {
        return sys_order_sn;
    }

    public void setSys_order_sn(String sys_order_sn) {
        this.sys_order_sn = sys_order_sn;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
