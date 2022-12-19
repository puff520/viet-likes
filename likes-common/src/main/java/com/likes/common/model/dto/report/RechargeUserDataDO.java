package com.likes.common.model.dto.report;

import java.math.BigDecimal;


public class RechargeUserDataDO {

    /**
     * 类型 1 线上入款 2 公司入款 15 代充
     */
    private Integer rechargetype;
    /**
     * 金额
     */
    private BigDecimal rechargeamt;

    private String userid;
    private Long orderid;

    public BigDecimal getRechargeamt() {
        return rechargeamt;
    }

    public void setRechargeamt(BigDecimal rechargeamt) {
        this.rechargeamt = rechargeamt;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getRechargetype() {
        return rechargetype;
    }

    public void setRechargetype(Integer rechargetype) {
        this.rechargetype = rechargetype;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }
}