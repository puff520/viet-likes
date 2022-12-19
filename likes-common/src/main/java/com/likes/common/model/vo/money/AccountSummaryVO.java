package com.likes.common.model.vo.money;

import java.math.BigDecimal;

public class AccountSummaryVO {

    /** 充值 线上入款 */
    private BigDecimal incomeRecharge;

    /** 充值 公司入款 */
    private BigDecimal incomeRechargeCompany;

    /** 充值 代理充值 */
    private BigDecimal incomeAgency;

    /** 活动 (充值附值，邀请用户，发视频奖励，赠送，签到奖励,代理结算) */
    private BigDecimal incomeActivity;

    /** 返水 (彩种返水、AG返水、开元返水、电竞返水、AE返水) */
    private BigDecimal incomeReturnWater;

    /** 异常补偿入款 */
    private BigDecimal incomeAbnormal;

    /** 会员出款 */
    private BigDecimal outgoMember;

    /** 家族礼物分成 */
    private BigDecimal outgoFamilyGift;

    /** 家族投注分成 */
    private BigDecimal outgoFamilyBet;

    /** 异常补偿出款 */
    private BigDecimal outgoAbnormal;

    public BigDecimal getIncomeRecharge() {
        return incomeRecharge;
    }

    public void setIncomeRecharge(BigDecimal incomeRecharge) {
        this.incomeRecharge = incomeRecharge;
    }

    public BigDecimal getIncomeRechargeCompany() {
        return incomeRechargeCompany;
    }

    public void setIncomeRechargeCompany(BigDecimal incomeRechargeCompany) {
        this.incomeRechargeCompany = incomeRechargeCompany;
    }

    public BigDecimal getIncomeAgency() {
        return incomeAgency;
    }

    public void setIncomeAgency(BigDecimal incomeAgency) {
        this.incomeAgency = incomeAgency;
    }

    public BigDecimal getIncomeActivity() {
        return incomeActivity;
    }

    public void setIncomeActivity(BigDecimal incomeActivity) {
        this.incomeActivity = incomeActivity;
    }

    public BigDecimal getIncomeReturnWater() {
        return incomeReturnWater;
    }

    public void setIncomeReturnWater(BigDecimal incomeReturnWater) {
        this.incomeReturnWater = incomeReturnWater;
    }

    public BigDecimal getIncomeAbnormal() {
        return incomeAbnormal;
    }

    public void setIncomeAbnormal(BigDecimal incomeAbnormal) {
        this.incomeAbnormal = incomeAbnormal;
    }

    public BigDecimal getOutgoMember() {
        return outgoMember;
    }

    public void setOutgoMember(BigDecimal outgoMember) {
        this.outgoMember = outgoMember;
    }

    public BigDecimal getOutgoFamilyGift() {
        return outgoFamilyGift;
    }

    public void setOutgoFamilyGift(BigDecimal outgoFamilyGift) {
        this.outgoFamilyGift = outgoFamilyGift;
    }

    public BigDecimal getOutgoFamilyBet() {
        return outgoFamilyBet;
    }

    public void setOutgoFamilyBet(BigDecimal outgoFamilyBet) {
        this.outgoFamilyBet = outgoFamilyBet;
    }

    public BigDecimal getOutgoAbnormal() {
        return outgoAbnormal;
    }

    public void setOutgoAbnormal(BigDecimal outgoAbnormal) {
        this.outgoAbnormal = outgoAbnormal;
    }
}
