package com.likes.common.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class TeamAdminResponse {

    @ApiModelProperty(value = "会员标识号")
    private String accno;

    @ApiModelProperty(value = "新增vip")
    private Integer vipNum;
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
    @ApiModelProperty(value = "完成任务总数")
    private Integer theDayTaskNum;

    private String createTime;
    private String headAccno;


    @ApiModelProperty(value = "充值笔数")
    private Integer recNum= 0;
    @ApiModelProperty(value = "提现笔数")
    private Integer cashNum= 0;
    @ApiModelProperty(value = "充值金额")
    private BigDecimal recAmount = new BigDecimal(0.00);
    @ApiModelProperty(value = "提现金额")
    private BigDecimal cashAmount = new BigDecimal(0.00) ;


    @ApiModelProperty(value = "提现人数")
    private Integer cashMemNum= 0;
    @ApiModelProperty(value = "充值人数")
    private Integer recMemAmount;
    @ApiModelProperty(value = "会员余额")
    private BigDecimal goldnum;

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

    public Integer getTheDayTaskNum() {
        return theDayTaskNum;
    }

    public void setTheDayTaskNum(Integer theDayTaskNum) {
        this.theDayTaskNum = theDayTaskNum;
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

    public BigDecimal getRecAmount() {
        return recAmount;
    }

    public void setRecAmount(BigDecimal recAmount) {
        this.recAmount = recAmount;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Integer getVipNum() {
        return vipNum;
    }

    public void setVipNum(Integer vipNum) {
        this.vipNum = vipNum;
    }

    public Integer getCashMemNum() {
        return cashMemNum;
    }

    public void setCashMemNum(Integer cashMemNum) {
        this.cashMemNum = cashMemNum;
    }

    public Integer getRecMemAmount() {
        return recMemAmount;
    }

    public void setRecMemAmount(Integer recMemAmount) {
        this.recMemAmount = recMemAmount;
    }

    public BigDecimal getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(BigDecimal goldnum) {
        this.goldnum = goldnum;
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
}
