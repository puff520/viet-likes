package com.likes.common.model.dto.report;

import java.math.BigDecimal;


public class GodPlanRewardDataDO {


    private String userid;
    /**
     * 金额
     */
    private BigDecimal godplanrewardamt;



    public BigDecimal getGodplanrewardamt() {
        return godplanrewardamt;
    }

    public void setGodplanrewardamt(BigDecimal godplanrewardamt) {
        this.godplanrewardamt = godplanrewardamt;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}