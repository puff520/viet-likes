package com.likes.common.model.dto.report;

import java.math.BigDecimal;


public class ActivityUserDataDO {


    /**
     * 金额
     */
    private BigDecimal activityamt;

    private String userid;

    public BigDecimal getActivityamt() {
        return activityamt;
    }

    public void setActivityamt(BigDecimal activityamt) {
        this.activityamt = activityamt;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}