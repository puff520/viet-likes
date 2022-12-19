package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 九州回调对象
 * @author 瑞夫
 * @version 1.0
 * @date 2020/6/15
 */
@Scope("prototype")
@Component
public class JiuzhouNotify extends Notify {

    private String orderid;
    //0:支付成功，非0为支付失败
    private String opstate;

    private String ovalue;

    private String sysorderid;

    private String systime;

    private String msg;
    private String attach;
    private String sign;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOpstate() {
        return opstate;
    }

    public void setOpstate(String opstate) {
        this.opstate = opstate;
    }

    public String getOvalue() {
        return ovalue;
    }

    public void setOvalue(String ovalue) {
        this.ovalue = ovalue;
    }

    public String getSysorderid() {
        return sysorderid;
    }

    public void setSysorderid(String sysorderid) {
        this.sysorderid = sysorderid;
    }

    public String getSystime() {
        return systime;
    }

    public void setSystime(String systime) {
        this.systime = systime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
