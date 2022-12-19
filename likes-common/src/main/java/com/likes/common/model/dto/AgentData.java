package com.likes.common.model.dto;

import com.likes.common.model.common.PageBaseInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName AgentMemberDTO
 * @Description TODO
 * @Author yeezy
 * @Date 2019/11/22 13:54
 * @Version 1.0
 **/
@ApiModel
public class AgentData extends PageBaseInfo implements Serializable {
    @ApiModelProperty(value = "新增用户")
    private Integer memNum= 0;
    @ApiModelProperty(value = "新增任务完成数")
    private Integer memTodayTaskNum = 0;
    @ApiModelProperty(value = "新增Vip")
    private Integer vipNum= 0;
    @ApiModelProperty(value = "任务完成数量")
    private Integer taskNum= 0;
    @ApiModelProperty(value = "充值笔数")
    private Integer recNum= 0;
    @ApiModelProperty(value = "提现笔数")
    private Integer cashNum= 0;
    @ApiModelProperty(value = "充值金额")
    private String recAmount ="0.00";
    @ApiModelProperty(value = "提现金额")
    private String cashAmount ="0.00";

    @ApiModelProperty(value = "会员余额")
    private BigDecimal balance;

    public Integer getMemNum() {
        return memNum;
    }

    public void setMemNum(Integer memNum) {
        this.memNum = memNum;
    }

    public Integer getVipNum() {
        return vipNum;
    }

    public void setVipNum(Integer vipNum) {
        this.vipNum = vipNum;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public Integer getRecNum() {
        return recNum;
    }

    public void setRecNum(Integer recNum) {
        this.recNum = recNum;
    }

    public Integer getCashNum() {
        return cashNum;
    }

    public void setCashNum(Integer cashNum) {
        this.cashNum = cashNum;
    }

    public String getRecAmount() {
        return recAmount;
    }

    public void setRecAmount(String recAmount) {
        this.recAmount = recAmount;
    }

    public String getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(String cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Integer getMemTodayTaskNum() {
        return memTodayTaskNum;
    }

    public void setMemTodayTaskNum(Integer memTodayTaskNum) {
        this.memTodayTaskNum = memTodayTaskNum;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
