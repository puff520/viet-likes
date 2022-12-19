package com.likes.common.model.vo.circle;

import java.math.BigDecimal;
import java.util.Date;

public class PushHistoryListVo {
	/**
	 * 推单ID
	 */
	public Integer  pushId;
	
	/**
	 * 彩种名
	 */
	public  String  lotteryName;
	/**
	 * 期号
	 */
	public  String  issue;
	/**
	 * 呢称
	 */
	public String nickName;
	/**
	 * 订单号
	 */
	public String orderSn;
	/**
	 * 每注金额
	 */
	public  BigDecimal  numberMoney;
	/**
	 * 分红比例
	 */
	public  BigDecimal bonusScale;
	/**
	 * 保障赔率
	 */
	public  BigDecimal ensureOdds;
	/**
	 * 跟单注数
	 */
	public Integer  betCount;
	/**
	 * 跟单总金额
	 */
	public BigDecimal totalAmount;
	/**
	 * 专家分红
	 */
	public BigDecimal  expertBonus;
	/**
	 * 中奖状态
	 */
	public String tbStatus;
	
	/**
	 * 跟单人数
	 */
	public Integer gdCount;
	/**
	 * 开奖时间
	 */
	public Date  endTime;
	
	/**
	 * 彩种ID
	 */
	public Integer lotteryId;
	
	/**
	 * 中奖金额
	 */
	public BigDecimal winAmount;
	
	/**
	 * 用户ID
	 */
	public Integer  userId;
	
	/**
	 * 总跟单 金额
	 */
	public Double gdTotalAmount;
	
	public Double getGdTotalAmount() {
		return gdTotalAmount;
	}
	public void setGdTotalAmount(Double gdTotalAmount) {
		this.gdTotalAmount = gdTotalAmount;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLotteryId() {
		return lotteryId;
	}
	public void setLotteryId(Integer lotteryId) {
		this.lotteryId = lotteryId;
	}
	
	public Integer getPushId() {
		return pushId;
	}
	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}
	public BigDecimal getWinAmount() {
		return winAmount;
	}
	public void setWinAmount(BigDecimal winAmount) {
		this.winAmount = winAmount;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public BigDecimal getNumberMoney() {
		return numberMoney;
	}
	public void setNumberMoney(BigDecimal numberMoney) {
		this.numberMoney = numberMoney;
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
	public Integer getBetCount() {
		return betCount;
	}
	public void setBetCount(Integer betCount) {
		this.betCount = betCount;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getExpertBonus() {
		return expertBonus;
	}
	public void setExpertBonus(BigDecimal expertBonus) {
		this.expertBonus = expertBonus;
	}
	public String getTbStatus() {
		return tbStatus;
	}
	public void setTbStatus(String tbStatus) {
		this.tbStatus = tbStatus;
	}
	public Integer getGdCount() {
		return gdCount;
	}
	public void setGdCount(Integer gdCount) {
		this.gdCount = gdCount;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}