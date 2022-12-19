package com.likes.common.model.request;

import java.util.List;

public class EntryOrderReq {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String accno;

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * 订单编号
     */
    private String orderno;
    /**
     * 订单状态
     */
    private String orderstatus;
    /**
     * 支付方式 NETBANK 网银转账  WECHAT 微信收款  ALIPAY 支付宝支付
     */
    private String payType;

    private String uniqueId;

    private List<Long> tpaysetids;

    private String providerid;

    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProviderid() {
        return providerid;
    }

    public void setProviderid(String providerid) {
        this.providerid = providerid;
    }

    public List<Long> getTpaysetids() {
        return tpaysetids;
    }

    public void setTpaysetids(List<Long> tpaysetids) {
        this.tpaysetids = tpaysetids;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
