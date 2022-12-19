package com.likes.common.model.vo.activity;

import com.likes.common.mybatis.entity.CpActivityInfo;

import java.math.BigDecimal;

public class CpActivityInfoVo extends CpActivityInfo {

    private static final long serialVersionUID = 1L;

    private BigDecimal receiveAmount;

    public BigDecimal getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiveAmount(BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }}
