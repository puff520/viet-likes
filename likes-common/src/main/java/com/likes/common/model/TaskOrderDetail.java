package com.likes.common.model;

import com.likes.common.mybatis.entity.Task;
import com.likes.common.mybatis.entity.TaskOrder;

import java.math.BigDecimal;
import java.util.Date;

public class TaskOrderDetail {

    private Long taskOrderId;

    private String createUser;

    private String mobile;

    private Integer taskType;

    private Date createTime;

    private String submitSample;

    private String categoryName;

    private String categoryDescription;

    private String icon;

    private String remark;

    private Date updateTime;

    private Task task;

    private BigDecimal usdt;


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSubmitSample() {
        return submitSample;
    }

    public void setSubmitSample(String submitSample) {
        this.submitSample = submitSample;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getTaskOrderId() {
        return taskOrderId;
    }

    public void setTaskOrderId(Long taskOrderId) {
        this.taskOrderId = taskOrderId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public BigDecimal getUsdt() {
        return usdt;
    }

    public void setUsdt(BigDecimal usdt) {
        this.usdt = usdt;
    }
}
