package com.likes.common.model.vo.finance;


import java.io.Serializable;
import java.math.BigDecimal;

public class BalanceAdjustmentVO   extends  FinanceMemBaseVo {

    /**
     * 充值金额
     */
    private BigDecimal rechargeAmount;
    /**
     * 提现金额
     */
    private BigDecimal withdrawal;


    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(BigDecimal withdrawal) {
        this.withdrawal = withdrawal;
    }
}
