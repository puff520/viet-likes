package com.likes.common.model.dto.report;

public class ThirdProviderDO {
    private Long tpaysetid;

    private String sumamtSum;

    private String peopleNum;

    private String renci;
    private String successNum;
    private String failNum;

    public Long getTpaysetid() {
        return tpaysetid;
    }

    public void setTpaysetid(Long tpaysetid) {
        this.tpaysetid = tpaysetid;
    }

    public String getSumamtSum() {
        return sumamtSum;
    }

    public void setSumamtSum(String sumamtSum) {
        this.sumamtSum = sumamtSum;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getRenci() {
        return renci;
    }

    public void setRenci(String renci) {
        this.renci = renci;
    }

    public String getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(String successNum) {
        this.successNum = successNum;
    }

    public String getFailNum() {
        return failNum;
    }

    public void setFailNum(String failNum) {
        this.failNum = failNum;
    }
}
