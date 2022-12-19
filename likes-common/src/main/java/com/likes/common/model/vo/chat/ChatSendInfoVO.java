package com.likes.common.model.vo.chat;

import java.math.BigDecimal;

public class ChatSendInfoVO {

    private Integer totalNumber;

    private BigDecimal totalMoney;

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
