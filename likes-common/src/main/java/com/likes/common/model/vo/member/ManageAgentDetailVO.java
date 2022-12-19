package com.likes.common.model.vo.member;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ManageAgentDetailVO {

    /**
     * 结算时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdate;

    /**
     * 说明: 下级人数
     */
    private Integer dailyNewUsers;

    /**
     * 说明: 首充人数
     */
    private Integer dailyPayUsers;

    /**
     * 说明: 充值总金额
     */
    private BigDecimal chargeamt;

    /**
     * 说明: 提现总金额
     */
    private BigDecimal dailyWithdrawal;

    /**
     * 说明: 首次充值总金额
     */
    private BigDecimal dailyPayTotal;

    /**
     * 说明: 返点总金额
     */
    private BigDecimal reverseamt;

    private Long cleanid;

    private String accno;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifydate;

    private Long agentid;

    private String agentname;

    private BigDecimal minamt;

    private BigDecimal maxamt;

    private BigDecimal commission;

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getDailyNewUsers() {
        return dailyNewUsers;
    }

    public void setDailyNewUsers(Integer dailyNewUsers) {
        this.dailyNewUsers = dailyNewUsers;
    }

    public Integer getDailyPayUsers() {
        return dailyPayUsers;
    }

    public void setDailyPayUsers(Integer dailyPayUsers) {
        this.dailyPayUsers = dailyPayUsers;
    }

    public BigDecimal getChargeamt() {
        return chargeamt;
    }

    public void setChargeamt(BigDecimal chargeamt) {
        this.chargeamt = chargeamt;
    }

    public BigDecimal getDailyWithdrawal() {
        return dailyWithdrawal;
    }

    public void setDailyWithdrawal(BigDecimal dailyWithdrawal) {
        this.dailyWithdrawal = dailyWithdrawal;
    }

    public BigDecimal getReverseamt() {
        return reverseamt;
    }

    public void setReverseamt(BigDecimal reverseamt) {
        this.reverseamt = reverseamt;
    }

    public Long getCleanid() {
        return cleanid;
    }

    public void setCleanid(Long cleanid) {
        this.cleanid = cleanid;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public Long getAgentid() {
        return agentid;
    }

    public void setAgentid(Long agentid) {
        this.agentid = agentid;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public BigDecimal getMinamt() {
        return minamt;
    }

    public void setMinamt(BigDecimal minamt) {
        this.minamt = minamt;
    }

    public BigDecimal getMaxamt() {
        return maxamt;
    }

    public void setMaxamt(BigDecimal maxamt) {
        this.maxamt = maxamt;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getDailyPayTotal() {
        return dailyPayTotal;
    }

    public void setDailyPayTotal(BigDecimal dailyPayTotal) {
        this.dailyPayTotal = dailyPayTotal;
    }
}
