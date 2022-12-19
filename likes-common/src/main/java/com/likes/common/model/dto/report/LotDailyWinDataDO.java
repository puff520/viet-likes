package com.likes.common.model.dto.report;

import java.math.BigDecimal;
import java.util.Date;


public class LotDailyWinDataDO {
    /**
     * 日期
     */
    private String date;


    /**
     * 彩票充值金额
     */
    private BigDecimal lotamt;


    /**
     * 彩票赢钱金额
     */
    private BigDecimal lotawardamt;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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


}