package com.likes.common.model.dto;

import java.math.BigDecimal;

public class RechargeStatDTO {

    private Integer userId;
    private BigDecimal rechargeAmount;
    private Integer rechargeAccount;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getRechargeAmount() {
        return null == rechargeAmount ? BigDecimal.ZERO : rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Integer getRechargeAccount() {
        return rechargeAccount;
    }

    public void setRechargeAccount(Integer rechargeAccount) {
        this.rechargeAccount = rechargeAccount;
    }
}
