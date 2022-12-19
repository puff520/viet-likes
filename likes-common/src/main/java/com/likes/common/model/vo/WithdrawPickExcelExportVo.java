package com.likes.common.model.vo;

import java.math.BigDecimal;

public class WithdrawPickExcelExportVo {
    private Integer id;
    private String startTime;
    private String userInfo;
    private String amountInfo;
    private BigDecimal withdrawAmount;
    private String orderNo;
    private String withdrawBankInfo;
    private BigDecimal withdrawTotalAmount;
    private String handleType;
    private String status;
    private String operateInfo;
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getAmountInfo() {
        return amountInfo;
    }

    public void setAmountInfo(String amountInfo) {
        this.amountInfo = amountInfo;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getWithdrawBankInfo() {
        return withdrawBankInfo;
    }

    public void setWithdrawBankInfo(String withdrawBankInfo) {
        this.withdrawBankInfo = withdrawBankInfo;
    }

    public BigDecimal getWithdrawTotalAmount() {
        return withdrawTotalAmount;
    }

    public void setWithdrawTotalAmount(BigDecimal withdrawTotalAmount) {
        this.withdrawTotalAmount = withdrawTotalAmount;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperateInfo() {
        return operateInfo;
    }

    public void setOperateInfo(String operateInfo) {
        this.operateInfo = operateInfo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
