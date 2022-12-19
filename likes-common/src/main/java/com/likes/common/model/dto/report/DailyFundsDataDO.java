package com.likes.common.model.dto.report;

import java.math.BigDecimal;
import java.util.Date;


public class DailyFundsDataDO {

    /**
     * 日期
     */
    private String date;

    /**
     * 充值总金额
     */
    private BigDecimal rechargeamt;

    /**
     * 出款总金额
     */
    private BigDecimal paymentamt;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public static DailyFundsDataDO getDefault() {
        DailyFundsDataDO dailyFundsDataDO = new DailyFundsDataDO();
        dailyFundsDataDO.setRechargeamt(BigDecimal.ZERO);
        dailyFundsDataDO.setPaymentamt(BigDecimal.ZERO);
        return dailyFundsDataDO;

    }

}