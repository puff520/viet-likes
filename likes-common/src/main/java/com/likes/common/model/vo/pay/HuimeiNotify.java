package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 【汇美支付】
 *
 * @Author 芥黄
 * @Description HuimeiNotify
 * @Date 2020/7/11-9:51
 **/
@Scope("prototype")
@Component
public class HuimeiNotify extends Notify {


    private String orderid;
    //0 成功， 其他失败
    private String restate;

    private String ovalue;

    private String attach;
    private String sign;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getRestate() {
        return restate;
    }

    public void setRestate(String restate) {
        this.restate = restate;
    }

    public String getOvalue() {
        return ovalue;
    }

    public void setOvalue(String ovalue) {
        this.ovalue = ovalue;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
