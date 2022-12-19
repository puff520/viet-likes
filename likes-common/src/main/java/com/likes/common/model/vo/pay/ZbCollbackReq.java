package com.likes.common.model.vo.pay;

import com.likes.common.model.response.pay.BaseCallBackReq;

import java.io.Serializable;

public class ZbCollbackReq extends BaseCallBackReq {

    private String orderid;

    private String result;


    private String systemorderid;

    private String completetime;

    private String notifytime;

    private String attach;

    private Double sourceamount;

    private String sign;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public String getSystemorderid() {
        return systemorderid;
    }

    public void setSystemorderid(String systemorderid) {
        this.systemorderid = systemorderid;
    }

    public String getCompletetime() {
        return completetime;
    }

    public void setCompletetime(String completetime) {
        this.completetime = completetime;
    }

    public String getNotifytime() {
        return notifytime;
    }

    public void setNotifytime(String notifytime) {
        this.notifytime = notifytime;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Double getSourceamount() {
        return sourceamount;
    }

    public void setSourceamount(Double sourceamount) {
        this.sourceamount = sourceamount;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
