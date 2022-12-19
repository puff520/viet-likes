package com.likes.common.model.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;

public class SuperExpFundsSummaryDO {
    /**
     * 充值总额
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal rechargeamt;

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
     * 直播总收入
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal giftsumamt;

    /**
     * 站点总盈利
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal profitamt;
    /**
     * 账单明细id
     */
    private String dateStr;


    public BigDecimal getRechargeamt() {
        return rechargeamt;
    }

    public void setRechargeamt(BigDecimal rechargeamt) {
        this.rechargeamt = rechargeamt;
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

    public BigDecimal getGiftsumamt() {
        return giftsumamt;
    }

    public void setGiftsumamt(BigDecimal giftsumamt) {
        this.giftsumamt = giftsumamt;
    }

    public BigDecimal getProfitamt() {
        return profitamt;
    }

    public void setProfitamt(BigDecimal profitamt) {
        this.profitamt = profitamt;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public static SuperExpFundsSummaryDO getDefault(String yearmonth) {
        SuperExpFundsSummaryDO expFundsSummaryDO = new SuperExpFundsSummaryDO();
        BigDecimal zero = new BigDecimal(0);
        expFundsSummaryDO.setDateStr(yearmonth);
        expFundsSummaryDO.setRechargeamt(zero);
        expFundsSummaryDO.setLotprofit(zero);
        expFundsSummaryDO.setGameprofit(zero);
        expFundsSummaryDO.setGiftsumamt(zero);
        expFundsSummaryDO.setProfitamt(zero);
        return expFundsSummaryDO;

    }
}