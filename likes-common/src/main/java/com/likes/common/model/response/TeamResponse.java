package com.likes.common.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class TeamResponse {

    @ApiModelProperty(value = "会员标识号")
    private String accno;
    @ApiModelProperty(value = "用户名")
    private String email;
    @ApiModelProperty(value = "下级数量")
    private Integer subNum;
    @ApiModelProperty(value = "新增人数")
    private Integer addMemNum;
    @ApiModelProperty(value = "注册ip")
    private String registerIp;
    @ApiModelProperty(value = "新增任务完成订单数")
    private Integer addTaskNum;
    @ApiModelProperty(value = "今日下单数")
    private Integer todayTaskNum;
    @ApiModelProperty(value = "注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-5")
    private Date createTime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSubNum() {
        return subNum;
    }

    public void setSubNum(Integer subNum) {
        this.subNum = subNum;
    }

    public Integer getAddMemNum() {
        return addMemNum;
    }

    public void setAddMemNum(Integer addMemNum) {
        this.addMemNum = addMemNum;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public Integer getAddTaskNum() {
        return addTaskNum;
    }

    public void setAddTaskNum(Integer addTaskNum) {
        this.addTaskNum = addTaskNum;
    }

    public Integer getTodayTaskNum() {
        return todayTaskNum;
    }

    public void setTodayTaskNum(Integer todayTaskNum) {
        this.todayTaskNum = todayTaskNum;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
