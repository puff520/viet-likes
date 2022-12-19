package com.likes.common.model.dto.report;

public class ProviderSetDO {
    private String provider;
    private Long providerid;
    private Long tpaysetid;
    private String tpayname;
    private String paytype;

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Long getProviderid() {
        return providerid;
    }

    public void setProviderid(Long providerid) {
        this.providerid = providerid;
    }

    public Long getTpaysetid() {
        return tpaysetid;
    }

    public void setTpaysetid(Long tpaysetid) {
        this.tpaysetid = tpaysetid;
    }

    public String getTpayname() {
        return tpayname;
    }

    public void setTpayname(String tpayname) {
        this.tpayname = tpayname;
    }
}
