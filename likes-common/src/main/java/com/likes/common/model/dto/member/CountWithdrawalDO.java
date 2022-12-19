package com.likes.common.model.dto.member;

import java.math.BigDecimal;

public class CountWithdrawalDO {

    /**
     * 首次充值金额
     */
    private BigDecimal userTotal;

    /**
     * 代理下的所有人的累计金额
     */
    private BigDecimal sumrealamt;

    public BigDecimal getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(BigDecimal userTotal) {
        this.userTotal = userTotal;
    }

    public BigDecimal getSumrealamt() {
        return sumrealamt;
    }

    public void setSumrealamt(BigDecimal sumrealamt) {
        this.sumrealamt = sumrealamt;
    }
}
