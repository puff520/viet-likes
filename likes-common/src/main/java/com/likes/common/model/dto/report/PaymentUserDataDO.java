package com.likes.common.model.dto.report;

import java.math.BigDecimal;

public class PaymentUserDataDO {

    /**
     * 类型 3 会员提现  4 家族提现
     */
    private Integer paymenttype;

    /**
     * 金额
     */
    private BigDecimal paymentamt;

    private String userid;
    private Long orderid;


    public Integer getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(Integer paymenttype) {
        this.paymenttype = paymenttype;
    }

    public BigDecimal getPaymentamt() {
        return paymentamt;
    }

    public void setPaymentamt(BigDecimal paymentamt) {
        this.paymentamt = paymentamt;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }
}