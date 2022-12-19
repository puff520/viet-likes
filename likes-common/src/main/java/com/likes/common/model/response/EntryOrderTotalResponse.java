package com.likes.common.model.response;

import java.math.BigDecimal;

public class EntryOrderTotalResponse {

    private BigDecimal allPay;
    private BigDecimal allSend;
    private BigDecimal allIn;

    public BigDecimal getAllPay() {
        return allPay;
    }

    public void setAllPay(BigDecimal allPay) {
        this.allPay = allPay;
    }

    public BigDecimal getAllSend() {
        return allSend;
    }

    public void setAllSend(BigDecimal allSend) {
        this.allSend = allSend;
    }

    public BigDecimal getAllIn() {
        return allIn;
    }

    public void setAllIn(BigDecimal allIn) {
        this.allIn = allIn;
    }
}
