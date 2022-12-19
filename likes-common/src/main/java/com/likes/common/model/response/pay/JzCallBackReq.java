package com.likes.common.model.response.pay;

/**
 * @ClassName CsCallBackReq
 * @Description jz 橘子
 * @Version 1.0
 **/
public class JzCallBackReq extends BaseCallBackReq {
    private String fxid; //商户号
    private String fxdesc; //商品名称
    private String fxtime;//支付成功时的时间
    private String sign;

    public String getFxdesc() {
        return fxdesc;
    }

    public void setFxdesc(String fxdesc) {
        this.fxdesc = fxdesc;
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

    public String getFxid() {
        return fxid;
    }

    public void setFxid(String fxid) {
        this.fxid = fxid;
    }
}
