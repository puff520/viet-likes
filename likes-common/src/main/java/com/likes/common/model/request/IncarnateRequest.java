package com.likes.common.model.request;

import java.math.BigDecimal;

public class IncarnateRequest {
    private Long bankaccid;
    private BigDecimal apycamt;
    //支付密码
    private String paypassword;
    private String moneyAddress;

    private String coinName;

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword;
    }

    public Long getBankaccid() {
        return bankaccid;
    }

    public void setBankaccid(Long bankaccid) {
        this.bankaccid = bankaccid;
    }

    public BigDecimal getApycamt() {
        return apycamt;
    }

    public void setApycamt(BigDecimal apycamt) {
        this.apycamt = apycamt;
    }

    public String getMoneyAddress() {
        return moneyAddress;
    }

    public void setMoneyAddress(String moneyAddress) {
        this.moneyAddress = moneyAddress;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }
}
