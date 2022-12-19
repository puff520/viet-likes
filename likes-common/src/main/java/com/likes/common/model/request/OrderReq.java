package com.likes.common.model.request;

public class OrderReq {
    //@NotNull
    private String copyno;
    private String orderno;

    private String paywechat;

    private String paytype;

    /**
     * 是否成功: true 成功，false 失败
     */
    private Boolean beSucceed;
    /**
     * 原因
     */
    private String reason;

    public Boolean getBeSucceed() {
        return beSucceed;
    }

    public void setBeSucceed(Boolean beSucceed) {
        this.beSucceed = beSucceed;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPaywechat() {
        return paywechat;
    }

    public void setPaywechat(String paywechat) {
        this.paywechat = paywechat;
    }


    public String getCopyno() {
        return copyno;
    }

    public void setCopyno(String copyno) {
        this.copyno = copyno;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

}
