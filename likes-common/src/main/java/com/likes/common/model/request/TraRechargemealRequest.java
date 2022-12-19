package com.likes.common.model.request;

import java.math.BigDecimal;

public class TraRechargemealRequest {
    private BigDecimal price;
    private String payuser;
    private String paynote;
    private Long bankid;
    private Long buyVip;

    public String getPayuser() {
        return payuser;
    }

    public void setPayuser(String payuser) {
        this.payuser = payuser;
    }

    public String getPaynote() {
        return paynote;
    }

    public void setPaynote(String paynote) {
        this.paynote = paynote;
    }

    public Long getBankid() {
        return bankid;
    }

    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getBuyVip() {
        return buyVip;
    }

    public void setBuyVip(Long buyVip) {
        this.buyVip = buyVip;
    }
}
