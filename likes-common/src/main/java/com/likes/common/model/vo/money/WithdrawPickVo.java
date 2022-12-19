package com.likes.common.model.vo.money;


import com.likes.common.enums.withdrawal.WithdrawHandleStatusEnum;
import com.likes.common.enums.withdrawal.WithdrawOpTypeEnum;
import com.likes.common.mybatis.entity.WithdrawPick;
import com.likes.common.util.ViewUtil;

import java.math.BigDecimal;

public class WithdrawPickVo extends WithdrawPick {

    private Integer vipId;
    private String realName;
    private String loginIp;
    //余额
    private BigDecimal balance;
    //总充值
    private BigDecimal payAmount;
    //总提现
    private BigDecimal withdrawalAmount;
    //总下注
    private BigDecimal betAmount;
    //充值次数
    private Integer rechargeCount;
    //提现次数
    private Integer withdrawCount;

    public String getOpTypeText() {
        return WithdrawOpTypeEnum.getOpTypeTextByType(this.getOpType());
    }

    //做页面端金额颜色显示用
    public String getAmountColor() {
        return ViewUtil.getAmountColor(this.getMoney());
    }

    public String getStatusText() {
        return WithdrawHandleStatusEnum.getHandleStatusByValue(this.getStatus());
    }

    public String getStatusColor() {
        return WithdrawHandleStatusEnum.getStatusColorByValue(this.getStatus());
    }

    public Integer getVipId() {
        return null == vipId || vipId < 0 ? 0 : vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public Integer getRechargeCount() {
        return rechargeCount;
    }

    public void setRechargeCount(Integer rechargeCount) {
        this.rechargeCount = rechargeCount;
    }

    public Integer getWithdrawCount() {
        return withdrawCount;
    }

    public void setWithdrawCount(Integer withdrawCount) {
        this.withdrawCount = withdrawCount;
    }

}
