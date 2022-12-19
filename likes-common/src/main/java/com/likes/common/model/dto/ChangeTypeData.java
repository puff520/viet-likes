package com.likes.common.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class ChangeTypeData implements Serializable {

    private Integer changeType;
    private BigDecimal amount;

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
