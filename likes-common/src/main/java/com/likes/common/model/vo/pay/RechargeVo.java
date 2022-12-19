package com.likes.common.model.vo.pay;

import java.util.ArrayList;
import java.util.List;

/**
 * 充值 vo
 */
public class RechargeVo {

    /** 用户id */
    private Integer userId;

    /** 层级id */
    private Long payLevelId;

    /** 最小金额 */
    private Integer minMoney;

    /** 最大金额：0表示不限制 */
    private Integer maxMoney;

    /** 在线支付项列表 */
    private List<PayWayVo> onlinePays;

    /** 银行账户支付项列表 */
    private List<PayWayVo> bankAccounts;

    /** 快捷支付金额 */
    private List<Integer> quickMoneys;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getPayLevelId() {
        return payLevelId;
    }

    public void setPayLevelId(Long payLevelId) {
        this.payLevelId = payLevelId;
    }

    public Integer getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(Integer minMoney) {
        this.minMoney = minMoney;
    }

    public Integer getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(Integer maxMoney) {
        this.maxMoney = maxMoney;
    }

    public List<PayWayVo> getOnlinePays() {
        return null == onlinePays ? new ArrayList<>() : onlinePays;
    }

    public void setOnlinePays(List<PayWayVo> onlinePays) {
        this.onlinePays = onlinePays;
    }

    public List<PayWayVo> getBankAccounts() {
        return null == bankAccounts ? new ArrayList<>() : bankAccounts;
    }

    public void setBankAccounts(List<PayWayVo> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<Integer> getQuickMoneys() {
        return quickMoneys;
    }

    public void setQuickMoneys(List<Integer> quickMoneys) {
        this.quickMoneys = quickMoneys;
    }
}
