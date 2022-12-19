package com.likes.common.model.vo.money;

import java.math.BigDecimal;

public class BenefitsBetVo {

    private  int sort;
    private  String nickname;
    private  String account;
    private  Integer uid;
    private  String realName;
    private  Integer betnum;
    private  Double winrate;
    private  String winratestr;
    private BigDecimal totalBet;
    private  String play;
    private BigDecimal winBet;
    private  int paynum;
    private BigDecimal totalpay;
    private BigDecimal balance;
    private  String soure;
    private  String loginip;
    private  String lotteryname;
    private  String playname;

    public String getLotteryname() {
        return lotteryname;
    }

    public void setLotteryname(String lotteryname) {
        this.lotteryname = lotteryname;
    }

    public String getPlayname() {
        return playname;
    }

    public void setPlayname(String playname) {
        this.playname = playname;
    }

    public String getWinratestr() {
        return winratestr;
    }

    public void setWinratestr(String winratestr) {
        this.winratestr = winratestr;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getBetnum() {
        return betnum;
    }

    public void setBetnum(Integer betnum) {
        this.betnum = betnum;
    }

    public Double getWinrate() {
        return winrate;
    }

    public void setWinrate(Double winrate) {
        this.winrate = winrate;
    }

    public BigDecimal getTotalBet() {
        return totalBet;
    }

    public void setTotalBet(BigDecimal totalBet) {
        this.totalBet = totalBet;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public BigDecimal getWinBet() {
        return winBet;
    }

    public void setWinBet(BigDecimal winBet) {
        this.winBet = winBet;
    }

    public int getPaynum() {
        return paynum;
    }

    public void setPaynum(int paynum) {
        this.paynum = paynum;
    }

    public BigDecimal getTotalpay() {
        return totalpay;
    }

    public void setTotalpay(BigDecimal totalpay) {
        this.totalpay = totalpay;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getSoure() {
        return soure;
    }

    public void setSoure(String soure) {
        this.soure = soure;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

}
