package com.likes.common.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TraRepayorder {
    private Long reorderid;

    private Long orderid;

    private String refaccno;

    private String refaccount;

    private String accno;

    private String memaccount;

    private String nickname;

    private BigDecimal realamt;

    private String orderstatus;

    private String note;

    private Boolean isDelete;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    public Long getReorderid() {
        return reorderid;
    }

    public void setReorderid(Long reorderid) {
        this.reorderid = reorderid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getRefaccno() {
        return refaccno;
    }

    public void setRefaccno(String refaccno) {
        this.refaccno = refaccno;
    }

    public String getRefaccount() {
        return refaccount;
    }

    public void setRefaccount(String refaccount) {
        this.refaccount = refaccount;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }


    public String getMemaccount() {
        return memaccount;
    }

    public void setMemaccount(String memaccount) {
        this.memaccount = memaccount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigDecimal getRealamt() {
        return realamt;
    }

    public void setRealamt(BigDecimal realamt) {
        this.realamt = realamt;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
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