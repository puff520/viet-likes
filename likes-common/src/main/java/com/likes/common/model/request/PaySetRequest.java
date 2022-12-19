package com.likes.common.model.request;

import java.math.BigDecimal;

public class PaySetRequest {

    private String tpayname;
    private Long paysetid;
    private String setname;
    // 单日出款免手续费次数
    private Integer freechargenums;
    // 单笔出款手续费（元） 0为不要手续费
    private BigDecimal servicecharge;
    // 单笔出款上限金额
    private BigDecimal maxchargeamt;
    // 单笔出款下限金额
    private BigDecimal minchargeamt;
    // 线上入款设定
    private String onlinepayset;
    // 公司入款设定
    private String companypayset;
    private Integer status;
    private Integer sortby;
    private String createdate;

    public String getTpayname() {
        return tpayname;
    }

    public void setTpayname(String tpayname) {
        this.tpayname = tpayname;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public Integer getSortby() {
        return sortby;
    }

    public void setSortby(Integer sortby) {
        this.sortby = sortby;
    }

    public Long getPaysetid() {
        return paysetid;
    }

    public void setPaysetid(Long paysetid) {
        this.paysetid = paysetid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSetname() {
        return setname;
    }

    public void setSetname(String setname) {
        this.setname = setname;
    }

    public Integer getFreechargenums() {
        return freechargenums;
    }

    public void setFreechargenums(Integer freechargenums) {
        this.freechargenums = freechargenums;
    }

    public BigDecimal getServicecharge() {
        return servicecharge;
    }

    public void setServicecharge(BigDecimal servicecharge) {
        this.servicecharge = servicecharge;
    }

    public BigDecimal getMaxchargeamt() {
        return maxchargeamt;
    }

    public void setMaxchargeamt(BigDecimal maxchargeamt) {
        this.maxchargeamt = maxchargeamt;
    }

    public BigDecimal getMinchargeamt() {
        return minchargeamt;
    }

    public void setMinchargeamt(BigDecimal minchargeamt) {
        this.minchargeamt = minchargeamt;
    }

    public String getOnlinepayset() {
        return onlinepayset;
    }

    public void setOnlinepayset(String onlinepayset) {
        this.onlinepayset = onlinepayset;
    }

    public String getCompanypayset() {
        return companypayset;
    }

    public void setCompanypayset(String companypayset) {
        this.companypayset = companypayset;
    }

}
