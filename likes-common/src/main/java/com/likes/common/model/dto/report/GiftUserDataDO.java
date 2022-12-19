package com.likes.common.model.dto.report;

import java.math.BigDecimal;


public class GiftUserDataDO {


    /**
     * 金额
     */
    private BigDecimal giftamt;

    private String userid;

    public BigDecimal getGiftamt() {
        return giftamt;
    }

    public void setGiftamt(BigDecimal giftamt) {
        this.giftamt = giftamt;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}