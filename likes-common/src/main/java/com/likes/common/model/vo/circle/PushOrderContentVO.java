package com.likes.common.model.vo.circle;

import java.math.BigDecimal;

public class PushOrderContentVO {
    private String playName;    //玩法名称
    private BigDecimal odds;    //赔率
    private String betNumber;  //投注号码
    private BigDecimal betAmount;   //投注金额
    private BigDecimal winAmount; //彩金
    private String lotteryName;  //彩种名称
    private String accout;  //名称
    private BigDecimal ensureOdds;    //保障赔率
    private BigDecimal bonusScale;   //分红比例
    private BigDecimal showProfitRate;  //盈利率 
    private String issue;  //期号
    private Integer godId;  //大神ID
    private Integer orderBetId;  //投注ID
    private String godAnalyze;  //大神分析
    private BigDecimal numberMoney;//每注金额
    private String lotteryRecord;  //彩种10期战绩记录
    private BigDecimal allGodBonus;// 大神分红
    private Integer godPushId;  //推单ID
    private Integer lotteryId;  //彩种名称
    private Integer gdCount;  //跟单人数

    public Integer getGdCount() {
        return gdCount;
    }

    public void setGdCount(Integer gdCount) {
        this.gdCount = gdCount;
    }

    private double gdTotalAmount;  //跟单总金额

    public double getGdTotalAmount() {
        return gdTotalAmount;
    }

    public void setGdTotalAmount(double gdTotalAmount) {
        this.gdTotalAmount = gdTotalAmount;
    }

    private Long endTime;


    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    //保密状态
    public Integer secretStatus;

    public Integer getSecretStatus() {
        return secretStatus;
    }

    public void setSecretStatus(Integer secretStatus) {
        this.secretStatus = secretStatus;
    }

    public Integer getGodPushId() {
        return godPushId;
    }

    public void setGodPushId(Integer godPushId) {
        this.godPushId = godPushId;
    }

    public BigDecimal getAllGodBonus() {
        return allGodBonus;
    }

    public void setAllGodBonus(BigDecimal allGodBonus) {
        this.allGodBonus = allGodBonus;
    }

    public String getGodAnalyze() {
        return godAnalyze;
    }

    public void setGodAnalyze(String godAnalyze) {
        this.godAnalyze = godAnalyze;
    }

    public BigDecimal getNumberMoney() {
        return numberMoney;
    }

    public void setNumberMoney(BigDecimal numberMoney) {
        this.numberMoney = numberMoney;
    }

    public String getLotteryRecord() {
        return lotteryRecord;
    }

    public void setLotteryRecord(String lotteryRecord) {
        this.lotteryRecord = lotteryRecord;
    }

    public Integer getOrderBetId() {
        return orderBetId;
    }

    public void setOrderBetId(Integer orderBetId) {
        this.orderBetId = orderBetId;
    }

    public Integer getGodId() {
        return godId;
    }

    public void setGodId(Integer godId) {
        this.godId = godId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public BigDecimal getEnsureOdds() {
        return ensureOdds;
    }

    public void setEnsureOdds(BigDecimal ensureOdds) {
        this.ensureOdds = ensureOdds;
    }

    public BigDecimal getBonusScale() {
        return bonusScale;
    }

    public void setBonusScale(BigDecimal bonusScale) {
        this.bonusScale = bonusScale;
    }

    public BigDecimal getShowProfitRate() {
        return showProfitRate;
    }

    public void setShowProfitRate(BigDecimal showProfitRate) {
        this.showProfitRate = showProfitRate;
    }

    public String getAccout() {
        return accout;
    }

    public void setAccout(String accout) {
        this.accout = accout;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public BigDecimal getOdds() {
        return odds;
    }

    public void setOdds(BigDecimal odds) {
        this.odds = odds;
    }

    public String getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(String betNumber) {
        this.betNumber = betNumber;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public BigDecimal getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(BigDecimal winAmount) {
        this.winAmount = winAmount;
    }
}
