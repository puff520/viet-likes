package com.likes.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.likes.common.model.common.PageBaseInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class AgentEveryData implements Serializable {

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT-5")
    private Date createTime;

    private Integer addVipNum = 0;
    private BigDecimal rechargeAmount = new BigDecimal(0.00);
    private BigDecimal withdrawAmount = new BigDecimal(0.00);
    private BigDecimal subTaskBrokerage = new BigDecimal(0.00);
    private BigDecimal buyVipAmount = new BigDecimal(0.00);
    private BigDecimal taskAmount = new BigDecimal(0.00);

    private String dat;

    private BigDecimal cz;
    private BigDecimal tx;

    private BigDecimal rgcz;

    private BigDecimal vip;

    private BigDecimal task1;

    private BigDecimal task2;

    private BigDecimal task3;

    private BigDecimal rw;


    public Integer getAddVipNum() {
        return addVipNum;
    }

    public void setAddVipNum(Integer addVipNum) {
        this.addVipNum = addVipNum;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public BigDecimal getSubTaskBrokerage() {
        return subTaskBrokerage;
    }

    public void setSubTaskBrokerage(BigDecimal subTaskBrokerage) {
        this.subTaskBrokerage = subTaskBrokerage;
    }

    public BigDecimal getBuyVipAmount() {
        return buyVipAmount;
    }

    public void setBuyVipAmount(BigDecimal buyVipAmount) {
        this.buyVipAmount = buyVipAmount;
    }

    public BigDecimal getTaskAmount() {
        return taskAmount;
    }

    public void setTaskAmount(BigDecimal taskAmount) {
        this.taskAmount = taskAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public BigDecimal getCz() {
        return cz;
    }

    public void setCz(BigDecimal cz) {
        this.cz = cz;
    }

    public BigDecimal getTx() {
        return tx;
    }

    public void setTx(BigDecimal tx) {
        this.tx = tx;
    }

    public BigDecimal getRgcz() {
        return rgcz;
    }

    public void setRgcz(BigDecimal rgcz) {
        this.rgcz = rgcz;
    }

    public BigDecimal getVip() {
        return vip;
    }

    public void setVip(BigDecimal vip) {
        this.vip = vip;
    }

    public BigDecimal getTask1() {
        return task1;
    }

    public void setTask1(BigDecimal task1) {
        this.task1 = task1;
    }

    public BigDecimal getTask2() {
        return task2;
    }

    public void setTask2(BigDecimal task2) {
        this.task2 = task2;
    }

    public BigDecimal getTask3() {
        return task3;
    }

    public void setTask3(BigDecimal task3) {
        this.task3 = task3;
    }

    public BigDecimal getRw() {
        return rw;
    }

    public void setRw(BigDecimal rw) {
        this.rw = rw;
    }
}
