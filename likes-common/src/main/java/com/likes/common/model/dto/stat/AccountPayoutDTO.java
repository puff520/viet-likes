package com.likes.common.model.dto.stat;



import com.likes.common.enums.GoldchangeEnum;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 支出统计
 */
public class AccountPayoutDTO {

    /** 后台出款 */
    private BigDecimal systemPayout;

    /** 会员出款： 提现出款为负数 */
    private BigDecimal vipPayout;

    /** VIP返水 */
    private BigDecimal vipBackwater;

    /** VIP升级奖励 */
    private BigDecimal vipUpgrade;

    /** 活动红包 */
    private BigDecimal envelopeHuodong;

    /** 分享赠送 */
    private BigDecimal giftShareAmount;

    /** 分享返水 */
    private BigDecimal giftShareBackwaterAmount;

    /** 活动赠送 */
    private BigDecimal giftHuodongAmount;

    /** 注册赠送 */
    private BigDecimal giftRegister;

    /** 充值赠送 */
    private BigDecimal giftRecharge;

    /** 领取佣金总额度：分享赠送+分享返水 */
    private BigDecimal commission;

    public AccountPayoutDTO fillData(Map<String, Object> dataMap, Map<String, Object> statWithdrawResult) {
//        Object vipPayout = dataMap.get(AccountStatDTO.PREFIX_BALANCE + BalanceChangeEnum.CASH_ADVANCE.getValue());
//        this.vipPayout = new BigDecimal(null == vipPayout ? "0" : vipPayout.toString());
//        this.vipPayout = BigDecimal.valueOf(Math.abs(this.vipPayout.doubleValue()));

        statWithdrawResult = null == statWithdrawResult ? new HashMap<>() : statWithdrawResult;
        BigDecimal successWithdraw = (BigDecimal) statWithdrawResult.get("success");
        this.vipPayout = BigDecimal.valueOf(Math.abs(null == successWithdraw ? BigDecimal.ZERO.doubleValue() : successWithdraw.doubleValue()));

        Object giftHuodongAmount = dataMap.get(AccountStatDTO.PREFIX_BALANCE + GoldchangeEnum.ACTIVITY_ACCOUNT.getValue());
        this.giftHuodongAmount = new BigDecimal(null == giftHuodongAmount ? "0" : giftHuodongAmount.toString());

        Object giftShareAmount = dataMap.get(AccountStatDTO.PREFIX_BALANCE + GoldchangeEnum.SHARE_AWARD.getValue());
        this.giftShareAmount = new BigDecimal(null == giftShareAmount ? "0" : giftShareAmount.toString());

        Object giftShareBackwaterAmount = dataMap.get(AccountStatDTO.PREFIX_BALANCE + GoldchangeEnum.SHARE_BACK.getValue());
        this.giftShareBackwaterAmount = new BigDecimal(null == giftShareBackwaterAmount ? "0" : giftShareBackwaterAmount.toString());

        Object vipBackwater = dataMap.get(AccountStatDTO.PREFIX_BALANCE + GoldchangeEnum.BET_ORDER_BACK.getValue());
        this.vipBackwater = new BigDecimal(null == vipBackwater ? "0" : vipBackwater.toString());

        Object vipUpgrade = dataMap.get(AccountStatDTO.PREFIX_BALANCE + GoldchangeEnum.VIP_UPGRADE_AWARDS.getValue());
        this.vipUpgrade = new BigDecimal(null == vipUpgrade ? "0" : vipUpgrade.toString());

        Object envelopeHuodong = dataMap.get(AccountStatDTO.PREFIX_BALANCE + GoldchangeEnum.RED_ENVELOP.getValue());
        this.envelopeHuodong = new BigDecimal(null == envelopeHuodong ? "0" : envelopeHuodong.toString());

        Object giftRegister = dataMap.get(AccountStatDTO.PREFIX_BALANCE + GoldchangeEnum.REGISTER_AWARD.getValue());
        this.giftRegister = new BigDecimal(null == giftRegister ? "0" : giftRegister.toString());

        this.commission = this.giftShareAmount.add(this.giftShareBackwaterAmount);

        return this;
    }

    //会员出款+活动赠送+VIP礼金+活动红包+注册赠送+充值赠送总额度+领取佣金总额
    public BigDecimal calcProfitPayout() {
        return this.vipPayout
                .add(this.giftHuodongAmount)
                .add(this.vipUpgrade)
                .add(this.envelopeHuodong)
                .add(this.giftRegister)
                .add(this.commission);
    }

    public BigDecimal getVipPayout() {
        return vipPayout;
    }

    public void setVipPayout(BigDecimal vipPayout) {
        this.vipPayout = vipPayout;
    }

    public BigDecimal getGiftHuodongAmount() {
        return giftHuodongAmount;
    }

    public void setGiftHuodongAmount(BigDecimal giftHuodongAmount) {
        this.giftHuodongAmount = giftHuodongAmount;
    }

    public BigDecimal getSystemPayout() {
        return systemPayout;
    }

    public void setSystemPayout(BigDecimal systemPayout) {
        this.systemPayout = systemPayout;
    }

    public BigDecimal getGiftShareAmount() {
        return giftShareAmount;
    }

    public void setGiftShareAmount(BigDecimal giftShareAmount) {
        this.giftShareAmount = giftShareAmount;
    }

    public BigDecimal getGiftShareBackwaterAmount() {
        return giftShareBackwaterAmount;
    }

    public void setGiftShareBackwaterAmount(BigDecimal giftShareBackwaterAmount) {
        this.giftShareBackwaterAmount = giftShareBackwaterAmount;
    }

    public BigDecimal getVipBackwater() {
        return vipBackwater;
    }

    public void setVipBackwater(BigDecimal vipBackwater) {
        this.vipBackwater = vipBackwater;
    }

    public BigDecimal getVipUpgrade() {
        return vipUpgrade;
    }

    public void setVipUpgrade(BigDecimal vipUpgrade) {
        this.vipUpgrade = vipUpgrade;
    }

    public BigDecimal getEnvelopeHuodong() {
        return envelopeHuodong;
    }

    public void setEnvelopeHuodong(BigDecimal envelopeHuodong) {
        this.envelopeHuodong = envelopeHuodong;
    }

    public BigDecimal getGiftRegister() {
        return giftRegister;
    }

    public void setGiftRegister(BigDecimal giftRegister) {
        this.giftRegister = giftRegister;
    }

    public BigDecimal getGiftRecharge() {
        return giftRecharge;
    }

    public void setGiftRecharge(BigDecimal giftRecharge) {
        this.giftRecharge = giftRecharge;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }
}
