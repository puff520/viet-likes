package com.likes.common.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TraArtrepayorder {
    private Long artorderid;

    private Long orderid;

    private Short logintype;

    private Integer ordertype;

    private String nickname;

    private Long memaccount;

    private String accno;

    private BigDecimal optamt;

    private BigDecimal giftamt;

    private BigDecimal auditper;

    private String note;

    private Boolean isDelete;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    public Long getArtorderid() {
        return artorderid;
    }

    public void setArtorderid(Long artorderid) {
        this.artorderid = artorderid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Short getLogintype() {
        return logintype;
    }

    public void setLogintype(Short logintype) {
        this.logintype = logintype;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getMemaccount() {
        return memaccount;
    }

    public void setMemaccount(Long memaccount) {
        this.memaccount = memaccount;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public BigDecimal getOptamt() {
        return optamt;
    }

    public void setOptamt(BigDecimal optamt) {
        this.optamt = optamt;
    }

    public BigDecimal getGiftamt() {
        return giftamt;
    }

    public void setGiftamt(BigDecimal giftamt) {
        this.giftamt = giftamt;
    }

    public BigDecimal getAuditper() {
        return auditper;
    }

    public void setAuditper(BigDecimal auditper) {
        this.auditper = auditper;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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