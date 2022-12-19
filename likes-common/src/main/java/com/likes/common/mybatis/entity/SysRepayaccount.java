package com.likes.common.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SysRepayaccount {
    private Long bankid;

    private String nickname;

    private String bankname;

    private String accountno;

    private String bankaddress;

    private String accountname;

    private BigDecimal minamt;

    private BigDecimal maxamt;

    private BigDecimal stopamt;

    private Integer status;

    private Boolean isDelete;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    public Long getBankid() {
        return bankid;
    }

    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public BigDecimal getMinamt() {
        return minamt;
    }

    public void setMinamt(BigDecimal minamt) {
        this.minamt = minamt;
    }

    public BigDecimal getMaxamt() {
        return maxamt;
    }

    public void setMaxamt(BigDecimal maxamt) {
        this.maxamt = maxamt;
    }

    public BigDecimal getStopamt() {
        return stopamt;
    }

    public void setStopamt(BigDecimal stopamt) {
        this.stopamt = stopamt;
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