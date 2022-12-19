package com.likes.common.model.dto.member;

import java.math.BigDecimal;

public class MemberCountStatisticsDTO {
    private Integer orderby;            //排序；
    private String dataDesc;            //统计日期描述
    private BigDecimal Recharge;        //充值
    private BigDecimal withdrawal;      //提现
    private BigDecimal task;            //任务
    private BigDecimal rebate;          //回扣
    private BigDecimal activity;        //活动
    private BigDecimal purchaseMember;  //购买会员
    private BigDecimal promotionReward; //推广奖励
    private BigDecimal pumping;         //抽水
    private BigDecimal cancelTask;      //撤销任务
    private BigDecimal taskFinish;      //任务提成
    private BigDecimal other;           //其他

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public void setDataDesc(String dataDesc) {
        this.dataDesc = dataDesc;
    }

    public BigDecimal getRecharge() {
        return Recharge;
    }

    public void setRecharge(BigDecimal recharge) {
        Recharge = recharge;
    }

    public BigDecimal getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(BigDecimal withdrawal) {
        this.withdrawal = withdrawal;
    }

    public BigDecimal getTask() {
        return task;
    }

    public void setTask(BigDecimal task) {
        this.task = task;
    }

    public BigDecimal getRebate() {
        return rebate;
    }

    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
    }

    public BigDecimal getActivity() {
        return activity;
    }

    public void setActivity(BigDecimal activity) {
        this.activity = activity;
    }

    public BigDecimal getPurchaseMember() {
        return purchaseMember;
    }

    public void setPurchaseMember(BigDecimal purchaseMember) {
        this.purchaseMember = purchaseMember;
    }

    public BigDecimal getPromotionReward() {
        return promotionReward;
    }

    public void setPromotionReward(BigDecimal promotionReward) {
        this.promotionReward = promotionReward;
    }

    public BigDecimal getPumping() {
        return pumping;
    }

    public void setPumping(BigDecimal pumping) {
        this.pumping = pumping;
    }

    public BigDecimal getCancelTask() {
        return cancelTask;
    }

    public void setCancelTask(BigDecimal cancelTask) {
        this.cancelTask = cancelTask;
    }

    public BigDecimal getTaskFinish() {
        return taskFinish;
    }

    public void setTaskFinish(BigDecimal taskFinish) {
        this.taskFinish = taskFinish;
    }

    public BigDecimal getOther() {
        return other;
    }

    public void setOther(BigDecimal other) {
        this.other = other;
    }
}
