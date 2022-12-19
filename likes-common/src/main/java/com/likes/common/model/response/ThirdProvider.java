package com.likes.common.model.response;

import java.math.BigDecimal;

public class ThirdProvider {
    private String ThirddivideName;
    private String providerName;
    private String wayType;
    private BigDecimal numPeople;
    private BigDecimal successNum;
    private BigDecimal failNum;
    private String successRatio;
    private BigDecimal depositSum;
    private Long tpaysetid;

    public Long getTpaysetid() {
        return tpaysetid;
    }

    public void setTpaysetid(Long tpaysetid) {
        this.tpaysetid = tpaysetid;
    }

    public String getThirddivideName() {
        return ThirddivideName;
    }

    public void setThirddivideName(String thirddivideName) {
        ThirddivideName = thirddivideName;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getWayType() {
        return wayType;
    }

    public void setWayType(String wayType) {
        this.wayType = wayType;
    }

    public String getSuccessRatio() {
        return successRatio;
    }

    public void setSuccessRatio(String successRatio) {
        this.successRatio = successRatio;
    }

    public BigDecimal getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(BigDecimal numPeople) {
        this.numPeople = numPeople;
    }

    public BigDecimal getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(BigDecimal successNum) {
        this.successNum = successNum;
    }

    public BigDecimal getFailNum() {
        return failNum;
    }

    public void setFailNum(BigDecimal failNum) {
        this.failNum = failNum;
    }

    public BigDecimal getDepositSum() {
        return depositSum;
    }

    public void setDepositSum(BigDecimal depositSum) {
        this.depositSum = depositSum;
    }
}
