package com.likes.common.model.vo.circle;

import java.math.BigDecimal;
import java.util.Date;

public class PushOrderVO {

    //呢称
    public String nickName;
    //头像
    public String head;
    //盈利率
    public BigDecimal calcProfitRate;
    //累计中奖
    public BigDecimal totalMoney;
    //是否关注  0 否   1 是
    public Integer isFollow;
    //彩种近10期盈亏
    public String lotteryRecord;
    //彩种名称
    public String lotteryName;
    //期号
    public String issue;
    //截止时间
    public Date endTime;
    //专家介绍
    public String personalContent;
    //选号金额
    public BigDecimal betAmount;
    //每注金额
    public BigDecimal numberAmount;
    //分红比例
    public BigDecimal bonusScale;
    // 保障赔率
    public BigDecimal ensureOdds;
    // 跟单人数
    public Integer gdCount;
    //投注号码
    public String betNumber;
    //投注赔率
    public BigDecimal betOdds;
    //最高可中
    public BigDecimal maxMoney;
    //中奖状态
    public String btStatus;
    //总跟单大神分红
    public BigDecimal allGodBonus;
    //是否展示  0 不展示 1展示
    public Integer isShow;
    //保密状态
    public Integer secretStatus;
    //中奖金额
    public BigDecimal winAmount;
    //专家分析
    public String godAnalyze;
    //图片
    public String picture;
    //大神ID
    public Integer godId;
    //总注数
    public Integer betCount;
    //总跟单金额
    public double gdTotalAmount;

    public double getGdTotalAmount() {
        return gdTotalAmount;
    }

    public void setGdTotalAmount(double gdTotalAmount) {
        this.gdTotalAmount = gdTotalAmount;
    }

    public Integer getBetCount() {
        return betCount;
    }

    public void setBetCount(Integer betCount) {
        this.betCount = betCount;
    }

    public Integer getGodId() {
        return godId;
    }

    public void setGodId(Integer godId) {
        this.godId = godId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getGodAnalyze() {
        return godAnalyze;
    }

    public void setGodAnalyze(String godAnalyze) {
        this.godAnalyze = godAnalyze;
    }

    public BigDecimal getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(BigDecimal winAmount) {
        this.winAmount = winAmount;
    }

    public Integer getSecretStatus() {
        return secretStatus;
    }

    public void setSecretStatus(Integer secretStatus) {
        this.secretStatus = secretStatus;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public BigDecimal getCalcProfitRate() {
        return calcProfitRate;
    }

    public void setCalcProfitRate(BigDecimal calcProfitRate) {
        this.calcProfitRate = calcProfitRate;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Integer isFollow) {
        this.isFollow = isFollow;
    }

    public String getLotteryRecord() {
        return lotteryRecord;
    }

    public void setLotteryRecord(String lotteryRecord) {
        this.lotteryRecord = lotteryRecord;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPersonalContent() {
        return personalContent;
    }

    public void setPersonalContent(String personalContent) {
        this.personalContent = personalContent;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public BigDecimal getNumberAmount() {
        return numberAmount;
    }

    public void setNumberAmount(BigDecimal numberAmount) {
        this.numberAmount = numberAmount;
    }

    public BigDecimal getBonusScale() {
        return bonusScale;
    }

    public void setBonusScale(BigDecimal bonusScale) {
        this.bonusScale = bonusScale;
    }

    public BigDecimal getEnsureOdds() {
        return ensureOdds;
    }

    public void setEnsureOdds(BigDecimal ensureOdds) {
        this.ensureOdds = ensureOdds;
    }

    public Integer getGdCount() {
        return gdCount;
    }

    public void setGdCount(Integer gdCount) {
        this.gdCount = gdCount;
    }

    public String getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(String betNumber) {
        this.betNumber = betNumber;
    }

    public BigDecimal getBetOdds() {
        return betOdds;
    }

    public void setBetOdds(BigDecimal betOdds) {
        this.betOdds = betOdds;
    }

    public BigDecimal getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getBtStatus() {
        return btStatus;
    }

    public void setBtStatus(String btStatus) {
        this.btStatus = btStatus;
    }

    public BigDecimal getAllGodBonus() {
        return allGodBonus;
    }

    public void setAllGodBonus(BigDecimal allGodBonus) {
        this.allGodBonus = allGodBonus;
    }
}
