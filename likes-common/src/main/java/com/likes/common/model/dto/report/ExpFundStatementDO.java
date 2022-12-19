package com.likes.common.model.dto.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.Serializable;
import java.math.BigDecimal;

public class ExpFundStatementDO implements Serializable {
    private Long fundid;
    /**
     * 充值总额
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal rechargeamt;
    /**
     * 出款总额
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal paymentamt;
    /**
     * 首充人数
     */
    private Long firstrecharge;
    /**
     * 彩票投注
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal lotamt;
    /**
     * 彩票中奖金额
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal lotawardamt;
    /**
     * 彩票盈利
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal lotrofit;
    /**
     * 游戏投注
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal gameamt;
    /**
     * 游戏中奖
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal gameawardamt;
    /**
     * 游戏盈利
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal gameprofit;
    /**
     * 直播总收入
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal giftsumamt;
    /**
     * 直播家族分成
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal giftfamilyamt;
    /**
     * 直播礼物平台分成
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal giftamt;
    /**
     * 站点总盈利
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal profitamt;
    /**
     * 平台总抽成
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal platformamt;
    /**
     * 每天的站点维护费
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal operateamt;
    /**
     * 应缴平台账款
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal payamt;


    private String dateStr;

    public Long getFundid() {
        return fundid;
    }

    public void setFundid(Long fundid) {
        this.fundid = fundid;
    }

    public BigDecimal getRechargeamt() {
        return rechargeamt;
    }

    public void setRechargeamt(BigDecimal rechargeamt) {
        this.rechargeamt = rechargeamt;
    }

    public BigDecimal getPaymentamt() {
        return paymentamt;
    }

    public void setPaymentamt(BigDecimal paymentamt) {
        this.paymentamt = paymentamt;
    }

    public Long getFirstrecharge() {
        return firstrecharge;
    }

    public void setFirstrecharge(Long firstrecharge) {
        this.firstrecharge = firstrecharge;
    }

    public BigDecimal getLotamt() {
        return lotamt;
    }

    public void setLotamt(BigDecimal lotamt) {
        this.lotamt = lotamt;
    }

    public BigDecimal getLotawardamt() {
        return lotawardamt;
    }

    public void setLotawardamt(BigDecimal lotawardamt) {
        this.lotawardamt = lotawardamt;
    }

    public BigDecimal getLotrofit() {
        return lotrofit;
    }

    public void setLotrofit(BigDecimal lotrofit) {
        this.lotrofit = lotrofit;
    }

    public BigDecimal getGameamt() {
        return gameamt;
    }

    public void setGameamt(BigDecimal gameamt) {
        this.gameamt = gameamt;
    }

    public BigDecimal getGameawardamt() {
        return gameawardamt;
    }

    public void setGameawardamt(BigDecimal gameawardamt) {
        this.gameawardamt = gameawardamt;
    }

    public BigDecimal getGameprofit() {
        return gameprofit;
    }

    public void setGameprofit(BigDecimal gameprofit) {
        this.gameprofit = gameprofit;
    }

    public BigDecimal getGiftsumamt() {
        return giftsumamt;
    }

    public void setGiftsumamt(BigDecimal giftsumamt) {
        this.giftsumamt = giftsumamt;
    }

    public BigDecimal getGiftfamilyamt() {
        return giftfamilyamt;
    }

    public void setGiftfamilyamt(BigDecimal giftfamilyamt) {
        this.giftfamilyamt = giftfamilyamt;
    }

    public BigDecimal getGiftamt() {
        return giftamt;
    }

    public void setGiftamt(BigDecimal giftamt) {
        this.giftamt = giftamt;
    }

    public BigDecimal getProfitamt() {
        return profitamt;
    }

    public void setProfitamt(BigDecimal profitamt) {
        this.profitamt = profitamt;
    }

    public BigDecimal getPlatformamt() {
        return platformamt;
    }

    public void setPlatformamt(BigDecimal platformamt) {
        this.platformamt = platformamt;
    }

    public BigDecimal getOperateamt() {
        return operateamt;
    }

    public void setOperateamt(BigDecimal operateamt) {
        this.operateamt = operateamt;
    }

    public BigDecimal getPayamt() {
        return payamt;
    }

    public void setPayamt(BigDecimal payamt) {
        this.payamt = payamt;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}