package com.likes.common.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class MemRepayuserReq {
    private Long repaymemid;

    private String accno;

    private String nickname;

    private String qq;

    private String webchat;

    private String alipay;

    private String mobileno;
    /**
     * 开始时间 HH:mm
     */
    private String startTime;
    /**
     * 结束时间 HH:mm
     */
    private String endTime;

    private Long repaynums;

    private BigDecimal discountrag;

    private Integer status;

    private String createuser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    private String modifyuser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifydate;

    public Long getRepaymemid() {
        return repaymemid;
    }

    public void setRepaymemid(Long repaymemid) {
        this.repaymemid = repaymemid;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWebchat() {
        return webchat;
    }

    public void setWebchat(String webchat) {
        this.webchat = webchat;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public Long getRepaynums() {
        return repaynums;
    }

    public void setRepaynums(Long repaynums) {
        this.repaynums = repaynums;
    }

    public BigDecimal getDiscountrag() {
        return discountrag;
    }

    public void setDiscountrag(BigDecimal discountrag) {
        this.discountrag = discountrag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

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
}
