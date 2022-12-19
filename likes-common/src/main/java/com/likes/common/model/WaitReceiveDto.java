package com.likes.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.likes.common.mybatis.entity.Task;

import java.math.BigDecimal;
import java.util.Date;

public class WaitReceiveDto {

    /**
     * id
     */
    private Long taskId;

    private Long orderId;
    /**
     * 任务要求
     */
    private  String taskRequire;

    /**
     * 任务等级
     */
    private  String taskLevel;

    /**
     * 任务图标
     */
    private  String taskIcon;

    /**
     *  任务分类名称
     */
    private  String categoryName;

    /**
     * 任务价格
     */
    private  BigDecimal price;

    /**
     *  任务分类名称
     */
    private  String createUser;

    /**
     *  任务分类名称
     */
    private  String mobile;

    private  String submitSample;



    private  String info;
    private  String taskUrl;
    private  String sampleUrl;
    private  Integer surplusNum;
    private  Integer completeTaskNum;

    /**
     *  任务分类名称
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /**
     *  任务分类名称
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    private  Integer status;


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskRequire() {
        return taskRequire;
    }

    public void setTaskRequire(String taskRequire) {
        this.taskRequire = taskRequire;
    }

    public String getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(String taskLevel) {
        this.taskLevel = taskLevel;
    }

    public String getTaskIcon() {
        return taskIcon;
    }

    public void setTaskIcon(String taskIcon) {
        this.taskIcon = taskIcon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getSubmitSample() {
        return submitSample;
    }

    public void setSubmitSample(String submitSample) {
        this.submitSample = submitSample;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTaskUrl() {
        return taskUrl;
    }

    public void setTaskUrl(String taskUrl) {
        this.taskUrl = taskUrl;
    }

    public String getSampleUrl() {
        return sampleUrl;
    }

    public void setSampleUrl(String sampleUrl) {
        this.sampleUrl = sampleUrl;
    }

    public Integer getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(Integer surplusNum) {
        this.surplusNum = surplusNum;
    }

    public Integer getCompleteTaskNum() {
        return completeTaskNum;
    }

    public void setCompleteTaskNum(Integer completeTaskNum) {
        this.completeTaskNum = completeTaskNum;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
