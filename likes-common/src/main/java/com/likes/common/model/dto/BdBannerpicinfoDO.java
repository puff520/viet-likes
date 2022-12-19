package com.likes.common.model.dto;

import com.likes.common.mybatis.entity.BdBannerpicinfo;

public class BdBannerpicinfoDO extends BdBannerpicinfo {

    private String seatdesc;
    private String seatname;
    private String seatcode;
    private String investname;
    private String bndispicurl;
    private String bdusername;
    private Integer isfire;

    public Integer getIsfire() {
        return isfire;
    }

    public void setIsfire(Integer isfire) {
        this.isfire = isfire;
    }

    public String getBdusername() {
        return bdusername;
    }

    public void setBdusername(String bdusername) {
        this.bdusername = bdusername;
    }

    public String getSeatname() {
        return seatname;
    }

    public void setSeatname(String seatname) {
        this.seatname = seatname;
    }

    public String getBndispicurl() {
        return bndispicurl;
    }

    public void setBndispicurl(String bndispicurl) {
        this.bndispicurl = bndispicurl;
    }

    public String getSeatcode() {
        return seatcode;
    }

    public void setSeatcode(String seatcode) {
        this.seatcode = seatcode;
    }

    public String getSeatdesc() {
        return seatdesc;
    }

    public void setSeatdesc(String seatdesc) {
        this.seatdesc = seatdesc;
    }

    public String getInvestname() {
        return investname;
    }

    public void setInvestname(String investname) {
        this.investname = investname;
    }
}
