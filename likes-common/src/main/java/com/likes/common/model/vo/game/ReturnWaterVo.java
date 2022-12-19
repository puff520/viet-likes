package com.likes.common.model.vo.game;

import java.math.BigDecimal;

public class ReturnWaterVo {
    private Integer memerId;
    private BigDecimal amount;

    public ReturnWaterVo() {
    }

    public Integer getMemerId() {
        return this.memerId;
    }

    public void setMemerId(Integer memerId) {
        this.memerId = memerId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
