package com.likes.common.model.dto.member;

public class MemFamilyListReq {

    /**
     * 主播累计时间统计的开始时间
     */
    private String startTime;

    /**
     * 主播累计时间统计的结束时间
     */
    private String endTime;

    /**
     * 家族名称
     */
    private String familyname;
    /**
     * 家族账号
     */
    private String acclogin;

    /**
     * 结算开始时间，通过startTime计算得到的
     */
    private String familyStartTime;

    /**
     * 结算结束时间，通过endTime计算得到的
     */
    private String familyEndTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public String getFamilyStartTime() {
        return familyStartTime;
    }

    public void setFamilyStartTime(String familyStartTime) {
        this.familyStartTime = familyStartTime;
    }

    public String getFamilyEndTime() {
        return familyEndTime;
    }

    public void setFamilyEndTime(String familyEndTime) {
        this.familyEndTime = familyEndTime;
    }
}
