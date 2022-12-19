package com.likes.common.model.vo.money;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

public class WithdrawPickDetailsVO {
    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 时间列表
     */
    private JSONObject timeList;

    /**
     * 提现方式
     */
    private String payType;
    /**
     * 银行
     */
    private String bank;

    /**
     * 姓名
     */
    private String account;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 卡号
     */
    private String cardNumber;

    /**
     * 状态
     */
    private int status;

    /**
     * 备注
     */
    private String mark;

    /**
     * 实际金额
     */
    private BigDecimal actualAmount;

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public JSONObject getTimeList() {
        return timeList;
    }

    public void setTimeList(JSONObject timeList) {
        this.timeList = timeList;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
