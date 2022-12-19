package com.likes.common.model.vo.money;


import com.likes.common.mybatis.entity.PaymentSummary;

import java.math.BigDecimal;

public class HandPaymentVO extends PaymentSummary {
    private String userName;
    private BigDecimal balance;
    private BigDecimal ketibalance;

    public BigDecimal getKetibalance() {
        return ketibalance;
    }

    public void setKetibalance(BigDecimal ketibalance) {
        this.ketibalance = ketibalance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
