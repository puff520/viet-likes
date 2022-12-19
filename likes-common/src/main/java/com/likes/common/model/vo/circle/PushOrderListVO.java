package com.likes.common.model.vo.circle;

import java.math.BigDecimal;
import java.util.Date;

public class PushOrderListVO {
    private Integer userId; // 用户id
    private String heads; // 头像
    private String nickname; // 昵称，没有的话展示用户名
    private String showRate; // 胜率、盈利率、连中

    private BigDecimal betAmount; // 投注金额
    private Integer lotteryId; // 彩种id
    private String lotteryName; // 彩种名称
    private String issue; // 期号
    private String godAnalyze; // 大神分析
    private String playName;// 玩法名称

    private Integer pushOrderId;
    private Integer godId;
    private BigDecimal ensureOdds;
    private BigDecimal bonusScale;
    private Integer secretStatus;
    private String betNumber; // 投注号码 secretStatus为1时返回

    private Integer gdCount; // 跟单人数

    private Integer isRecord; // 是否跟单

    private String btState; // 状态

    private String lotteryRecord; // 彩种10期战绩记录

    private double gdTotalAmount; // 跟单总金额


    public double getGdTotalAmount() {
        return gdTotalAmount;
    }

    public void setGdTotalAmount(double gdTotalAmount) {
        this.gdTotalAmount = gdTotalAmount;
    }

    private Date endTime; // 截止时间

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLotteryRecord() {
        return lotteryRecord;
    }

    public void setLotteryRecord(String lotteryRecord) {
        this.lotteryRecord = lotteryRecord;
    }

    public String getBtState() {
        return btState;
    }

    public void setBtState(String btState) {
        this.btState = btState;
    }

    public Integer getIsRecord() {
        return isRecord;
    }

    public void setIsRecord(Integer isRecord) {
        this.isRecord = isRecord;
    }

    private BigDecimal numberMoney;// 每注金额

    private Integer settingId;

    public BigDecimal getNumberMoney() {
        return numberMoney;
    }

    public void setNumberMoney(BigDecimal numberMoney) {
        this.numberMoney = numberMoney;
    }

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHeads() {
        return heads;
    }

    public void setHeads(String heads) {
        this.heads = heads;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getShowRate() {
        return showRate;
    }

    public void setShowRate(String showRate) {
        this.showRate = showRate;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Integer getGdCount() {
        return gdCount;
    }

    public void setGdCount(Integer gdCount) {
        this.gdCount = gdCount;
    }

    public Integer getPushOrderId() {
        return pushOrderId;
    }

    public void setPushOrderId(Integer pushOrderId) {
        this.pushOrderId = pushOrderId;
    }

    public Integer getGodId() {
        return godId;
    }

    public void setGodId(Integer godId) {
        this.godId = godId;
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

    public Integer getSecretStatus() {
        return secretStatus;
    }

    public void setSecretStatus(Integer secretStatus) {
        this.secretStatus = secretStatus;
    }

    public String getGodAnalyze() {
        return godAnalyze;
    }

    public void setGodAnalyze(String godAnalyze) {
        this.godAnalyze = godAnalyze;
    }

    public String getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(String betNumber) {
        this.betNumber = betNumber;
    }

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }
}
