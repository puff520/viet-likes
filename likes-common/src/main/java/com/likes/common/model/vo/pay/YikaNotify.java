package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 亿咖支付回调对象
 *
 * @author 瑞夫
 * @version 1.0
 * @date 2020/7/3
 */
@Scope("prototype")
@Component
public class YikaNotify extends Notify {

    private String order_no;
    private String amount;
    private String tran_id;
    private String init_amount;
    private String mch_id;
    private String sign;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTran_id() {
        return tran_id;
    }

    public void setTran_id(String tran_id) {
        this.tran_id = tran_id;
    }

    public String getInit_amount() {
        return init_amount;
    }

    public void setInit_amount(String init_amount) {
        this.init_amount = init_amount;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
