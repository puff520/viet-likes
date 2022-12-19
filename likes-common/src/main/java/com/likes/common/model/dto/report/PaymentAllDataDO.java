package com.likes.common.model.dto.report;

import java.math.BigDecimal;


public class PaymentAllDataDO {

    /**
     * 充值金额
     */
    private BigDecimal allpaymentamt;

    /**
     * 充值人数
     */
    private Long allpaymentpeoples;
    /**
     * 充值笔数
     */
    private Long allpaymenttimes;


    /**
     * 线上
     */
    private BigDecimal memberamt;

    /**
     *
     */
    private Long memberpeoples;


    /**
     * 线下
     */
    private BigDecimal familyamt;

    /**
     *
     */
    private Long familypeoples;

    public BigDecimal getAllpaymentamt() {
        return allpaymentamt;
    }

    public void setAllpaymentamt(BigDecimal allpaymentamt) {
        this.allpaymentamt = allpaymentamt;
    }

    public Long getAllpaymentpeoples() {
        return allpaymentpeoples;
    }

    public void setAllpaymentpeoples(Long allpaymentpeoples) {
        this.allpaymentpeoples = allpaymentpeoples;
    }

    public Long getAllpaymenttimes() {
        return allpaymenttimes;
    }

    public void setAllpaymenttimes(Long allpaymenttimes) {
        this.allpaymenttimes = allpaymenttimes;
    }

    public BigDecimal getMemberamt() {
        return memberamt;
    }

    public void setMemberamt(BigDecimal memberamt) {
        this.memberamt = memberamt;
    }

    public Long getMemberpeoples() {
        return memberpeoples;
    }

    public void setMemberpeoples(Long memberpeoples) {
        this.memberpeoples = memberpeoples;
    }

    public BigDecimal getFamilyamt() {
        return familyamt;
    }

    public void setFamilyamt(BigDecimal familyamt) {
        this.familyamt = familyamt;
    }

    public Long getFamilypeoples() {
        return familypeoples;
    }

    public void setFamilypeoples(Long familypeoples) {
        this.familypeoples = familypeoples;
    }

    public static PaymentAllDataDO getDefault() {
        PaymentAllDataDO paymentAllDataDO = new PaymentAllDataDO();
        paymentAllDataDO.setAllpaymentamt(new BigDecimal("0").setScale(3, BigDecimal.ROUND_DOWN));
        paymentAllDataDO.setAllpaymentpeoples(0L);
        paymentAllDataDO.setAllpaymenttimes(0L);
        paymentAllDataDO.setMemberamt(new BigDecimal("0").setScale(3, BigDecimal.ROUND_DOWN));
        paymentAllDataDO.setMemberpeoples(0L);
        paymentAllDataDO.setFamilyamt(new BigDecimal("0").setScale(3, BigDecimal.ROUND_DOWN));
        paymentAllDataDO.setFamilypeoples(0L);


        return paymentAllDataDO;
    }
}