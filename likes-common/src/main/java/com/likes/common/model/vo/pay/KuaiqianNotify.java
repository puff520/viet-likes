package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud
 * @description: 贝多支付回调对象
 * @author: 芥黄
 * @create: 2020-06-20 14:55
 **/
@Scope("prototype")
@Component
public class KuaiqianNotify extends Notify{

    private String pay_order;

    private String pay_thisorder;

    private String pay_amount;

    private String status;

    private String pay_payment;

    private String pay_merchid;

    private String pay_mark;

    private String sign;

    public String getPay_order() {
        return pay_order;
    }

    public void setPay_order(String pay_order) {
        this.pay_order = pay_order;
    }

    public String getPay_thisorder() {
        return pay_thisorder;
    }

    public void setPay_thisorder(String pay_thisorder) {
        this.pay_thisorder = pay_thisorder;
    }

    public String getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(String pay_amount) {
        this.pay_amount = pay_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPay_payment() {
        return pay_payment;
    }

    public void setPay_payment(String pay_payment) {
        this.pay_payment = pay_payment;
    }

    public String getPay_merchid() {
        return pay_merchid;
    }

    public void setPay_merchid(String pay_merchid) {
        this.pay_merchid = pay_merchid;
    }

    public String getPay_mark() {
        return pay_mark;
    }

    public void setPay_mark(String pay_mark) {
        this.pay_mark = pay_mark;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
