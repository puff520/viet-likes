package com.likes.common.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class MemRepayuser {
    private Long repaymemid;

    private String accno;

    private String nickname;

    private String qq;

    private String webchat;

    private String alipay;

    private String mobileno;

    private BigDecimal memgold;
    @JsonFormat(pattern = "HH:mm")
    private Date onlinedates;
    @JsonFormat(pattern = "HH:mm")
    private Date onlinedatee;

    private Long repaynums;

    private BigDecimal discountrag;

    private Integer status;

    /**
     * 字段: mem_baseinfo.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

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

    public BigDecimal getMemgold() {
        return memgold;
    }

    public void setMemgold(BigDecimal memgold) {
        this.memgold = memgold;
    }

    public Date getOnlinedates() {
        return onlinedates;
    }

    public void setOnlinedates(Date onlinedates) {
        this.onlinedates = onlinedates;
    }

    public Date getOnlinedatee() {
        return onlinedatee;
    }

    public void setOnlinedatee(Date onlinedatee) {
        this.onlinedatee = onlinedatee;
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

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
