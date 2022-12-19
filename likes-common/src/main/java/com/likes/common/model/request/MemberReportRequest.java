package com.likes.common.model.request;

import java.util.Date;

public class MemberReportRequest extends BaseRequest {
    private String memberaccount;
    private String supermemberaccount;
    private Integer rechargenum;
    private Date startTime;
    private Date endTime;
    private String keywords;
    private Integer type;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMemberaccount() {
        return memberaccount;
    }

    public void setMemberaccount(String memberaccount) {
        this.memberaccount = memberaccount;
    }

    public String getSupermemberaccount() {
        return supermemberaccount;
    }

    public void setSupermemberaccount(String supermemberaccount) {
        this.supermemberaccount = supermemberaccount;
    }

    public Integer getRechargenum() {
        return rechargenum;
    }

    public void setRechargenum(Integer rechargenum) {
        this.rechargenum = rechargenum;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
