package com.likes.common.model.dto.member;

import java.math.BigDecimal;

/**
 * @author puff
 */
public class MemAgentDetailResp {

    private String accno;


    private String agentLevel;

    private String email;

    private BigDecimal rechargeAmount;

    private BigDecimal withdrawalAmount;

    private BigDecimal buyVipAmount;

    private String updateTime;

    private String buyLevel;

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public BigDecimal getBuyVipAmount() {
        return buyVipAmount;
    }

    public void setBuyVipAmount(BigDecimal buyVipAmount) {
        this.buyVipAmount = buyVipAmount;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getBuyLevel() {
        return buyLevel;
    }

    public void setBuyLevel(String buyLevel) {
        this.buyLevel = buyLevel;
    }


    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }
}
