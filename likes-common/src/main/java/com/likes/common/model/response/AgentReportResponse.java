package com.likes.common.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class AgentReportResponse {

    private Integer addMem;
    private String recAmount;
    private String takeCashAmount;
    private String buyVipAmount;
    private Integer taskNum;
    private String taskTotalPrice;


    public Integer getAddMem() {
        return addMem;
    }

    public void setAddMem(Integer addMem) {
        this.addMem = addMem;
    }

    public String getRecAmount() {
        return recAmount;
    }

    public void setRecAmount(String recAmount) {
        this.recAmount = recAmount;
    }

    public String getTakeCashAmount() {
        return takeCashAmount;
    }

    public void setTakeCashAmount(String takeCashAmount) {
        this.takeCashAmount = takeCashAmount;
    }

    public String getBuyVipAmount() {
        return buyVipAmount;
    }

    public void setBuyVipAmount(String buyVipAmount) {
        this.buyVipAmount = buyVipAmount;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public String getTaskTotalPrice() {
        return taskTotalPrice;
    }

    public void setTaskTotalPrice(String taskTotalPrice) {
        this.taskTotalPrice = taskTotalPrice;
    }
}
