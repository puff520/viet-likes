package com.likes.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TaskOrderReviewDetail {

    private String taskName; //任务名称
    private String createUser;//创建人
    private String info;//任务简介
    private String taskUrl;//链接
    private String mobile;//手机号

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date   createTime;//创建时间
    private Integer status;//状态
    private String sampleUrl;//审核样例
    private String submitSample;//提交样例
    private String remark;


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSampleUrl() {
        return sampleUrl;
    }

    public void setSampleUrl(String sampleUrl) {
        this.sampleUrl = sampleUrl;
    }

    public String getSubmitSample() {
        return submitSample;
    }

    public void setSubmitSample(String submitSample) {
        this.submitSample = submitSample;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
