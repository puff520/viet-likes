package com.likes.common.model.dto.order;

import com.likes.common.mybatis.entity.AgBetOrder;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AgBetOrderDTO extends AgBetOrder {

    private static final long serialVersionUID = 1L;

    private String account;
    private Integer memberId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date betTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String gameName;
    private String roundName;
    private String payName;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getRoundName() {
        return roundName;
    }

    public void setRoundName(String roundName) {
        this.roundName = roundName;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Override
    public Date getBetTime() {
        return betTime;
    }

    @Override
    public void setBetTime(Date betTime) {
        this.betTime = betTime;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
