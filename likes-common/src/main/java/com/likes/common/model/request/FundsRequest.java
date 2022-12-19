package com.likes.common.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class FundsRequest{
    @ApiModelProperty(value = "用户Uid")
    private String uniqueId;
    @ApiModelProperty(value = "交易类型")
    private Integer changeType;
    @ApiModelProperty(value = "开始金额")
    private Integer beginAmount;
    @ApiModelProperty(value = "截止金额")
    private Integer endAmount;
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    @ApiModelProperty(value = "开始时间")
    private String beginTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    @ApiModelProperty(value = "用户账号")
    private String email;
    @ApiModelProperty(value = "accno")
    private String accno;
    @ApiModelProperty(value = "账号")
    private String acclogin;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Integer getBeginAmount() {
        return beginAmount;
    }

    public void setBeginAmount(Integer beginAmount) {
        this.beginAmount = beginAmount;
    }

    public Integer getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(Integer endAmount) {
        this.endAmount = endAmount;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }
}
