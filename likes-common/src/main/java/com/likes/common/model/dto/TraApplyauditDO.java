package com.likes.common.model.dto;

import com.likes.common.mybatis.entity.TraApplyaudit;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TraApplyauditDO extends TraApplyaudit {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderdate;

    private BigDecimal sumamt;

    private BigDecimal realamt;

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public BigDecimal getSumamt() {
        return sumamt;
    }

    public void setSumamt(BigDecimal sumamt) {
        this.sumamt = sumamt;
    }

    public BigDecimal getRealamt() {
        return realamt;
    }

    public void setRealamt(BigDecimal realamt) {
        this.realamt = realamt;
    }

}
