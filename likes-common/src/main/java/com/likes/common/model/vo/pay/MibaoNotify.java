package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 米宝回调对象
 * @author 瑞夫
 * @version 1.0
 * @date 2020/6/4
 */
@Scope("prototype")
@Component
public class MibaoNotify extends Notify {

    private String mcode;

    private String orderid;
    //实际支付金额 单位元,保留两位小数
    private String amt;
    //0：未支付；1：支付成功
    private String status;
    //提交金额
    private String sourceamt;

    private String systemorderid;

    //完成时间 yyyymmddHHmmss
    private String completetime;
    //扩展
    private String msgext;

    private String sign;

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSourceamt() {
        return sourceamt;
    }

    public void setSourceamt(String sourceamt) {
        this.sourceamt = sourceamt;
    }

    public String getCompletetime() {
        return completetime;
    }

    public void setCompletetime(String completetime) {
        this.completetime = completetime;
    }

    public String getMsgext() {
        return msgext;
    }

    public void setMsgext(String msgext) {
        this.msgext = msgext;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSystemorderid() {
        return systemorderid;
    }

    public void setSystemorderid(String systemorderid) {
        this.systemorderid = systemorderid;
    }
}
