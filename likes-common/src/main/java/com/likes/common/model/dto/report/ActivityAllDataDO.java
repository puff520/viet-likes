package com.likes.common.model.dto.report;

import java.math.BigDecimal;


public class ActivityAllDataDO {


    /**
     * 活动收支
     */
    private BigDecimal allactivityamt;

    /**
     * 总人数
     */
    private Long allactivitypeoples;


    /**
     * 活动优惠
     */
    private BigDecimal activitydiscountamt;

    /**
     * 总人数
     */
    private Long activitydiscountpeoples;


    /**
     * 活动回收
     */
    private BigDecimal activityrecoveryamt;

    /**
     * 总人数
     */
    private Long activityrecoverypeoples;

    public BigDecimal getAllactivityamt() {
        return allactivityamt;
    }

    public void setAllactivityamt(BigDecimal allactivityamt) {
        this.allactivityamt = allactivityamt;
    }

    public Long getAllactivitypeoples() {
        return allactivitypeoples;
    }

    public void setAllactivitypeoples(Long allactivitypeoples) {
        this.allactivitypeoples = allactivitypeoples;
    }

    public BigDecimal getActivitydiscountamt() {
        return activitydiscountamt;
    }

    public void setActivitydiscountamt(BigDecimal activitydiscountamt) {
        this.activitydiscountamt = activitydiscountamt;
    }

    public Long getActivitydiscountpeoples() {
        return activitydiscountpeoples;
    }

    public void setActivitydiscountpeoples(Long activitydiscountpeoples) {
        this.activitydiscountpeoples = activitydiscountpeoples;
    }

    public BigDecimal getActivityrecoveryamt() {
        return activityrecoveryamt;
    }

    public void setActivityrecoveryamt(BigDecimal activityrecoveryamt) {
        this.activityrecoveryamt = activityrecoveryamt;
    }

    public Long getActivityrecoverypeoples() {
        return activityrecoverypeoples;
    }

    public void setActivityrecoverypeoples(Long activityrecoverypeoples) {
        this.activityrecoverypeoples = activityrecoverypeoples;
    }


    public static ActivityAllDataDO getDefault() {
        ActivityAllDataDO activityAllDataDO = new ActivityAllDataDO();
        activityAllDataDO.setAllactivityamt(new BigDecimal("0"));
        activityAllDataDO.setAllactivitypeoples(0L);
        activityAllDataDO.setActivitydiscountamt(new BigDecimal("0"));
        activityAllDataDO.setActivitydiscountpeoples(0L);
        activityAllDataDO.setActivityrecoveryamt(new BigDecimal("0"));
        activityAllDataDO.setActivityrecoverypeoples(0L);

        return activityAllDataDO;
    }
}