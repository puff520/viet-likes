package com.likes.common.model.response;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class AgentAdminResponse {

    @ApiModelProperty(value = "会员标识号")
    private String accno;
    @ApiModelProperty(value = "代理账号")
    private String email;
    @ApiModelProperty(value = "邀请码")
    private String recomcode;

    @ApiModelProperty(value = "团队人数")
    private Integer teamNum;

    @ApiModelProperty(value = "直推人数")
    private Integer directNum;

    @ApiModelProperty(value = "充值金额")
    private BigDecimal rechargeAmount;

    @ApiModelProperty(value = "提现金额")
    private BigDecimal withdrawAmount;

    @ApiModelProperty(value = "下级返点")
    private BigDecimal subRebate;

    @ApiModelProperty(value = "下级返点")
    private BigDecimal buyVipAmount;

    @ApiModelProperty(value = "任务奖励")
    private BigDecimal taskAmount;
    private Integer subNum;

    private String createTime;
    private String headAccno;
    private Long relaid;


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

    public String getRecomcode() {
        return recomcode;
    }

    public void setRecomcode(String recomcode) {
        this.recomcode = recomcode;
    }

    public Integer getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(Integer teamNum) {
        this.teamNum = teamNum;
    }

    public Integer getDirectNum() {
        return directNum;
    }

    public void setDirectNum(Integer directNum) {
        this.directNum = directNum;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public BigDecimal getSubRebate() {
        return subRebate;
    }

    public void setSubRebate(BigDecimal subRebate) {
        this.subRebate = subRebate;
    }

    public BigDecimal getBuyVipAmount() {
        return buyVipAmount;
    }

    public void setBuyVipAmount(BigDecimal buyVipAmount) {
        this.buyVipAmount = buyVipAmount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHeadAccno() {
        return headAccno;
    }

    public void setHeadAccno(String headAccno) {
        this.headAccno = headAccno;
    }

    public BigDecimal getTaskAmount() {
        return taskAmount;
    }

    public void setTaskAmount(BigDecimal taskAmount) {
        this.taskAmount = taskAmount;
    }

    public Integer getSubNum() {
        return subNum;
    }

    public void setSubNum(Integer subNum) {
        this.subNum = subNum;
    }

    public Long getRelaid() {
        return relaid;
    }

    public void setRelaid(Long relaid) {
        this.relaid = relaid;
    }
}
