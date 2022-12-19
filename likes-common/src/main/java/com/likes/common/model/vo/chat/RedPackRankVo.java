package com.likes.common.model.vo.chat;

import java.math.BigDecimal;

public class RedPackRankVo {
    private String nickname;
    private BigDecimal balance;
    private String realName;
    private String account;
    private Integer userId;
    private Integer number;
    private BigDecimal totalMomey;
    private BigDecimal totalPayMomey;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigDecimal getTotalMomey() {
        return totalMomey;
    }

    public void setTotalMomey(BigDecimal totalMomey) {
        this.totalMomey = totalMomey;
    }

    public BigDecimal getTotalPayMomey() {
        return totalPayMomey;
    }

    public void setTotalPayMomey(BigDecimal totalPayMomey) {
        this.totalPayMomey = totalPayMomey;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
