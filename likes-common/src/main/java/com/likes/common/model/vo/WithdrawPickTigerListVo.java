package com.likes.common.model.vo;

import java.math.BigDecimal;

public class WithdrawPickTigerListVo {
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankDot() {
        return bankDot;
    }

    public void setBankDot(String bankDot) {
        this.bankDot = bankDot;
    }

    // 商户ID
    private String merchantId;
    // 订单号
    private String orderNo;
    // 持卡人
    private String name;
    // 卡号
    private String bankCard;
    // 金额
    private BigDecimal amount;
    // 开户行
    private String bank;
    // 开户网点
    private String bankDot;

}
