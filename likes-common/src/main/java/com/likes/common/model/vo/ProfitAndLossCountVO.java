package com.likes.common.model.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 盈亏统计VO
 *
 * @author lzy
 * @create 2018-07-14 14:50
 **/
public class ProfitAndLossCountVO {
    //中奖总额
    private BigDecimal totalWins;
    //返点总额
    private BigDecimal totalBackWater;


    //有效注单
    private Integer totalValidBet;
    //ios有效注单
    private Integer iosValidBet;
    //android有效注单
    private Integer androidValidBet;
    //有效投注总额
    private BigDecimal totalValidBets;
    //ios有效投注总额
    private BigDecimal iosValidBets;
    //android有效投注总额
    private BigDecimal androidValidBets;
    //盈亏总额
    private BigDecimal totalChange;
    //ios盈亏总额
    private BigDecimal iosChange;
    //android盈亏总额
    private BigDecimal androidChange;
    //充值总额
    private BigDecimal totalRecharge;
    //充值总数
    private Integer totalRechargeNum;
    //ios充值总额
    private BigDecimal iosRecharge;

    private List<Integer> list;

    private String time;

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getTotalRechargeNum() {
        return totalRechargeNum;
    }

    public void setTotalRechargeNum(Integer totalRechargeNum) {
        this.totalRechargeNum = totalRechargeNum;
    }

    //android充值总额
    private BigDecimal androidRecharge;
    //提现总额
    private BigDecimal totalWithdrawal;
    //ios提现总额
    private BigDecimal iosWithdrawal;

    //android提现总额
    private BigDecimal androidWithdrawal;
    //注册人数
    private Integer registered;

    public BigDecimal getFirstDepositAmount() {
        return firstDepositAmount;
    }

    public void setFirstDepositAmount(BigDecimal firstDepositAmount) {
        this.firstDepositAmount = firstDepositAmount;
    }

    //首充金额
    private BigDecimal firstDepositAmount;

    public Integer getTotalValidBet() {
        return totalValidBet;
    }

    public Integer getIosValidBet() {
        return iosValidBet;
    }

    public Integer getAndroidValidBet() {
        return androidValidBet;
    }

    public void setTotalValidBet(Integer totalValidBet) {
        this.totalValidBet = totalValidBet;
    }

    public void setIosValidBet(Integer iosValidBet) {
        this.iosValidBet = iosValidBet;
    }

    public void setAndroidValidBet(Integer androidValidBet) {
        this.androidValidBet = androidValidBet;
    }

    public BigDecimal getTotalValidBets() {
        return totalValidBets;
    }

    public void setTotalValidBets(BigDecimal totalValidBets) {
        this.totalValidBets = totalValidBets;
    }

    public BigDecimal getIosValidBets() {
        return iosValidBets;
    }

    public void setIosValidBets(BigDecimal iosValidBets) {
        this.iosValidBets = iosValidBets;
    }

    public BigDecimal getAndroidValidBets() {
        return androidValidBets;
    }

    public void setAndroidValidBets(BigDecimal androidValidBets) {
        this.androidValidBets = androidValidBets;
    }



    public BigDecimal getIosChange() {
        return iosChange;
    }

    public void setIosChange(BigDecimal iosChange) {
        this.iosChange = iosChange;
    }

    public BigDecimal getAndroidChange() {
        return androidChange;
    }

    public void setAndroidChange(BigDecimal androidChange) {
        this.androidChange = androidChange;
    }

    public BigDecimal getTotalRecharge() {
        return totalRecharge;
    }

    public void setTotalRecharge(BigDecimal totalRecharge) {
        this.totalRecharge = totalRecharge;
    }

    public BigDecimal getIosRecharge() {
        return iosRecharge;
    }

    public void setIosRecharge(BigDecimal iosRecharge) {
        this.iosRecharge = iosRecharge;
    }

    public BigDecimal getAndroidRecharge() {
        return androidRecharge;
    }

    public void setAndroidRecharge(BigDecimal androidRecharge) {
        this.androidRecharge = androidRecharge;
    }

    public BigDecimal getTotalWithdrawal() {
        return totalWithdrawal;
    }

    public void setTotalWithdrawal(BigDecimal totalWithdrawal) {
        this.totalWithdrawal = totalWithdrawal;
    }

    public BigDecimal getIosWithdrawal() {
        return iosWithdrawal;
    }

    public void setIosWithdrawal(BigDecimal iosWithdrawal) {
        this.iosWithdrawal = iosWithdrawal;
    }

    public BigDecimal getAndroidWithdrawal() {
        return androidWithdrawal;
    }

    public void setAndroidWithdrawal(BigDecimal androidWithdrawal) {
        this.androidWithdrawal = androidWithdrawal;
    }

    public Integer getRegistered() {
        return registered;
    }

    public void setRegistered(Integer registered) {
        this.registered = registered;
    }

    public BigDecimal getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(BigDecimal totalWins) {
        this.totalWins = totalWins;
    }

    public BigDecimal getTotalBackWater() {
        return totalBackWater;
    }

    public void setTotalBackWater(BigDecimal totalBackWater) {
        this.totalBackWater = totalBackWater;
    }

    public BigDecimal getTotalChange() {
        return totalChange;
    }

    public void setTotalChange(BigDecimal totalChange) {
        this.totalChange = totalChange;
    }
}

