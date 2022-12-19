package com.likes.common.model.request;

import com.likes.common.util.DateUtils;
import org.springframework.data.annotation.Transient;

import java.util.Date;

public class IncarnateOrderReq {
    private String orderno;
    private String orderstatus;
    private Integer accounttype;
    private String nickname;
    private String accno;
    private String uniqueId;
    private String timeType;
    private String startDate;
    private String endDate;
    private String mobileno;
    private String email;

    private String moneyAddress;

    private Long levelSeq;


    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    /**
     * 是否成功，true 成功 false 失败
     */
    private Boolean beSucceed;

    /**
     * 失败原因
     */
    private String reason;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

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

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
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

    public Integer getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Integer accounttype) {
        this.accounttype = accounttype;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoneyAddress() {
        return moneyAddress;
    }

    public void setMoneyAddress(String moneyAddress) {
        this.moneyAddress = moneyAddress;
    }

    public Long getLevelSeq() {
        return levelSeq;
    }

    public void setLevelSeq(Long levelSeq) {
        this.levelSeq = levelSeq;
    }
}
