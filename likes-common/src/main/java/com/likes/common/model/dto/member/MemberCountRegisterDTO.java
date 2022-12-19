package com.likes.common.model.dto.member;

import java.math.BigDecimal;

public class MemberCountRegisterDTO {
    private Long today;
    private Long yesterday;
    private Long month;
    private Long allNum;
    private BigDecimal balance;
    private BigDecimal assets;
    private Long onlineNum;

    public Long getToday() {
        return today;
    }

    public void setToday(Long today) {
        this.today = today;
    }

    public Long getYesterday() {
        return yesterday;
    }

    public void setYesterday(Long yesterday) {
        this.yesterday = yesterday;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public Long getAllNum() {
        return allNum;
    }

    public void setAllNum(Long allNum) {
        this.allNum = allNum;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAssets() {
        return assets;
    }

    public void setAssets(BigDecimal assets) {
        this.assets = assets;
    }

    public Long getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(Long onlineNum) {
        this.onlineNum = onlineNum;
    }
}
