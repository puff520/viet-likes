package com.likes.common.model.response;

import com.likes.common.model.SubAndTaskDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class AppTeamResponse {

    @ApiModelProperty(value = "会员标识号")
    private String accno;
    @ApiModelProperty(value = "用户名")
    private String email;
    @ApiModelProperty(value = "充值金额")
    private BigDecimal recAmount;
    @ApiModelProperty(value = "充值人数")
    private Integer recNum;
    @ApiModelProperty(value = "充值次数")
    private Integer recCount;
    @ApiModelProperty(value = "提现金额")
    private BigDecimal cashAmount;
    @ApiModelProperty(value = "团队人数")
    private Integer teamNum;
    @ApiModelProperty(value = "任务数量")
    private Integer taskNum;

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

    public BigDecimal getRecAmount() {
        return recAmount;
    }

    public void setRecAmount(BigDecimal recAmount) {
        this.recAmount = recAmount;
    }

    public Integer getRecNum() {
        return recNum;
    }

    public void setRecNum(Integer recNum) {
        this.recNum = recNum;
    }

    public Integer getRecCount() {
        return recCount;
    }

    public void setRecCount(Integer recCount) {
        this.recCount = recCount;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Integer getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(Integer teamNum) {
        this.teamNum = teamNum;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }
}
