package com.likes.common.model.dto.stat;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 出入账目统计汇总
 */
public class AccountStatDTO {
    public static String PREFIX_BALANCE = "BALANCE_CHANGE_";
    public static String PREFIX_PAYMENT = "PAYMENT_SUMMARY_";

    /** 入款信息 */
    private AccountIncomeDTO income;

    /** 出款信息 */
    private AccountPayoutDTO payout;

    /** 用户余额：用户总余额 */
    private BigDecimal userBalance;

    /** 实际盈亏：（公司入款+线上支付+系统入款）－会员出款 */
    private BigDecimal profitStat;

    /** 账目统计：（公司入款+线上支付+系统入款）－（会员出款+活动赠送+VIP礼金+活动红包+注册赠送+充值赠送总额度+领取佣金总额） */
    private BigDecimal statSummary;

    public AccountStatDTO fillData(Map<String, Object> dataMap, BigDecimal allBalanceAmount, Map<String, Object> statWithdrawResult) {
        AccountIncomeDTO accountIncomeDTO = new AccountIncomeDTO();
        AccountPayoutDTO accountPayoutDTO = new AccountPayoutDTO();

        accountIncomeDTO.fillData(dataMap);
        accountPayoutDTO.fillData(dataMap, statWithdrawResult);
        this.setIncome(accountIncomeDTO);
        this.setPayout(accountPayoutDTO);

        BigDecimal accountIncome = this.income.sum();
        this.profitStat = accountIncome.subtract(this.payout.getVipPayout());
        this.statSummary = accountIncome.subtract(this.payout.calcProfitPayout());
        this.userBalance = null == allBalanceAmount ? BigDecimal.ZERO : allBalanceAmount;
        return this;
    }

    public AccountIncomeDTO getIncome() {
        return income;
    }

    public void setIncome(AccountIncomeDTO income) {
        this.income = income;
    }

    public AccountPayoutDTO getPayout() {
        return payout;
    }

    public void setPayout(AccountPayoutDTO payout) {
        this.payout = payout;
    }

    public BigDecimal getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(BigDecimal userBalance) {
        this.userBalance = userBalance;
    }

    public BigDecimal getProfitStat() {
        return profitStat;
    }

    public void setProfitStat(BigDecimal profitStat) {
        this.profitStat = profitStat;
    }

    public BigDecimal getStatSummary() {
        return statSummary;
    }

    public void setStatSummary(BigDecimal statSummary) {
        this.statSummary = statSummary;
    }
}
