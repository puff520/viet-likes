package com.likes.common.model.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;

public class ExpFundsSummaryDO {
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
     * 站点维护费
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal operateamt;
    /**
     * 应缴平台账款
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal payamt;

    /**
     * 彩票盈利
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal lotprofit;
    /**
     * 游戏盈利
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal gameprofit;

    /**
     * 账单明细id
     */
    private String dateStr;

    @JsonIgnore
    private String excelDateStr;

    private Long firstrecharge;


    @JsonIgnore
    private BigDecimal lotamt;
    @JsonIgnore
    private BigDecimal lotawardamt;

    @JsonIgnore
    private BigDecimal gameamt;

    @JsonIgnore
    private BigDecimal gameawardamt;


    @JsonIgnore
    private BigDecimal giftfamilyamt;


    @JsonIgnore
    private BigDecimal giftsumamt;


    @JsonIgnore
    private String lotamtAwardamt;
    @JsonIgnore
    private String gameamtAwardamt;

    @JsonIgnore
    private String giftamtAwardamt;

    public String getGiftamtAwardamt() {
        return giftamtAwardamt;
    }

    public void setGiftamtAwardamt(String giftamtAwardamt) {
        this.giftamtAwardamt = giftamtAwardamt;
    }

    public String getLotamtAwardamt() {
        return lotamtAwardamt;
    }

    public void setLotamtAwardamt(String lotamtAwardamt) {
        this.lotamtAwardamt = lotamtAwardamt;
    }

    public String getGameamtAwardamt() {
        return gameamtAwardamt;
    }

    public void setGameamtAwardamt(String gameamtAwardamt) {
        this.gameamtAwardamt = gameamtAwardamt;
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

    public BigDecimal getGiftfamilyamt() {
        return giftfamilyamt;
    }

    public void setGiftfamilyamt(BigDecimal giftfamilyamt) {
        this.giftfamilyamt = giftfamilyamt;
    }

    public BigDecimal getGiftsumamt() {
        return giftsumamt;
    }

    public void setGiftsumamt(BigDecimal giftsumamt) {
        this.giftsumamt = giftsumamt;
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

    public BigDecimal getLotprofit() {
        return lotprofit;
    }

    public void setLotprofit(BigDecimal lotprofit) {
        this.lotprofit = lotprofit;
    }

    public BigDecimal getGameprofit() {
        return gameprofit;
    }

    public void setGameprofit(BigDecimal gameprofit) {
        this.gameprofit = gameprofit;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getExcelDateStr() {
        return excelDateStr;
    }

    public void setExcelDateStr(String excelDateStr) {
        this.excelDateStr = excelDateStr;
    }

    public Long getFirstrecharge() {
        return firstrecharge;
    }

    public void setFirstrecharge(Long firstrecharge) {
        this.firstrecharge = firstrecharge;
    }


    public static ExpFundsSummaryDO getDefault(String yearmonth) {
        ExpFundsSummaryDO expFundsSummaryDO = new ExpFundsSummaryDO();
        BigDecimal zero = new BigDecimal(0);
        expFundsSummaryDO.setDateStr(yearmonth);
        expFundsSummaryDO.setRechargeamt(zero);
        expFundsSummaryDO.setPaymentamt(zero);
        expFundsSummaryDO.setFirstrecharge(0L);
        expFundsSummaryDO.setLotamt(zero);
        expFundsSummaryDO.setLotawardamt(zero);
        expFundsSummaryDO.setLotprofit(zero);
        expFundsSummaryDO.setGameamt(zero);
        expFundsSummaryDO.setGameawardamt(zero);
        expFundsSummaryDO.setGameprofit(zero);
        expFundsSummaryDO.setGiftsumamt(zero);
        expFundsSummaryDO.setGiftfamilyamt(zero);
        expFundsSummaryDO.setGiftamt(zero);
        expFundsSummaryDO.setProfitamt(zero);
        expFundsSummaryDO.setPlatformamt(zero);
        expFundsSummaryDO.setOperateamt(zero);
        expFundsSummaryDO.setPayamt(zero);
        return expFundsSummaryDO;

    }
}