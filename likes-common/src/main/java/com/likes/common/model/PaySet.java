package com.likes.common.model;

public class PaySet {
    Long paysetid;
    // 入款优惠频率 1首次充值优惠 2每次充值优惠
    Integer rechargetype;
    // 额外赠送率 %
    Double giftrate;
    // 优惠上线
    Double maxgift;
    // 常态性稽核（百分比）%
    Double auditper;
    // 常态性稽核放宽额度（元）
    Double auditfree;
    // 常态性稽核行政费率（百分值）%
    Double administrative;

    private Integer settype;

    public Integer getSettype() {
        return settype;
    }

    public void setSettype(Integer settype) {
        this.settype = settype;
    }

    public Long getPaysetid() {
        return paysetid;
    }

    public void setPaysetid(Long paysetid) {
        this.paysetid = paysetid;
    }

    public Integer getRechargetype() {
        return rechargetype;
    }

    public void setRechargetype(Integer rechargetype) {
        this.rechargetype = rechargetype;
    }

    public Double getGiftrate() {
        return giftrate;
    }

    public void setGiftrate(Double giftrate) {
        this.giftrate = giftrate;
    }

    public Double getMaxgift() {
        return maxgift;
    }

    public void setMaxgift(Double maxgift) {
        this.maxgift = maxgift;
    }

    public Double getAuditper() {
        return auditper;
    }

    public void setAuditper(Double auditper) {
        this.auditper = auditper;
    }

    public Double getAuditfree() {
        return auditfree;
    }

    public void setAuditfree(Double auditfree) {
        this.auditfree = auditfree;
    }

    public Double getAdministrative() {
        return administrative;
    }

    public void setAdministrative(Double administrative) {
        this.administrative = administrative;
    }

}
