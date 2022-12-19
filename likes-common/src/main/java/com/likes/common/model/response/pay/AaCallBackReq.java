package com.likes.common.model.response.pay;

/**
 * @ClassName AaCallBackReq
 * @Description
 * @Version 1.0
 **/
public class AaCallBackReq extends BaseCallBackReq {
    private String  fxid; //商户号
    private String fxdesc; //商品名称
    private String fxattch;//附加信息
    private String fxstatus;//订单状态
    private String fxtime;//支付时间
    private String sign;

    public String getFxid() {
        return fxid;
    }

    public void setFxid(String fxid) {
        this.fxid = fxid;
    }

    public String getFxdesc() {
        return fxdesc;
    }

    public void setFxdesc(String fxdesc) {
        this.fxdesc = fxdesc;
    }

    public String getFxattch() {
        return fxattch;
    }

    public void setFxattch(String fxattch) {
        this.fxattch = fxattch;
    }

    public String getFxstatus() {
        return fxstatus;
    }

    public void setFxstatus(String fxstatus) {
        this.fxstatus = fxstatus;
    }

    public String getFxtime() {
        return fxtime;
    }

    public void setFxtime(String fxtime) {
        this.fxtime = fxtime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
