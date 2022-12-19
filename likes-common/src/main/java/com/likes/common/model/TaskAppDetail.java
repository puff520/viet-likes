package com.likes.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TaskAppDetail {

    /**
     * id
     */
    private Long id;

    /**
     * 任务分类id
     */
    private Integer categoryId;


    /**
     * 任务单价
     */
    private BigDecimal price;


    /**
     * 任务链接
     */
    private String taskUrl;



    /**
     * 审核样例url
     */
    private String sampleUrl;

    /**
     * 任务描述
     */
    private String info;

    /**
     * 头像
     */
    private String headimg;


    /**
     *  需求方手机号
     */
    private String createMobile;

    /**
     *  需求方手机号
     */
    private String createUser;

    /**
     * 剩余数量
     */
    private Integer surplusNum;

    /**
     * 任务要求
     */
    private String taskRequire;


    /**
     * 任务步骤。保存json数据
     */
    private String stepInfo;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 已赚到到人数
     */
    private  Integer completeTaskNum;

    /**
     * 任务条件
     */
    private  String condition;


    /**
     * 任务来源 1 app创建 2后台创建
     */
    private  Integer taskSource;


    /**
     * status
     */
    private  Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private  Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getCreateMobile() {
        return createMobile;
    }

    public void setCreateMobile(String createMobile) {
        this.createMobile = createMobile;
    }

    public Integer getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(Integer surplusNum) {
        this.surplusNum = surplusNum;
    }

    public String getTaskRequire() {
        return taskRequire;
    }

    public void setTaskRequire(String taskRequire) {
        this.taskRequire = taskRequire;
    }

    public String getStepInfo() {
        return stepInfo;
    }

    public void setStepInfo(String stepInfo) {
        this.stepInfo = stepInfo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCompleteTaskNum() {
        return completeTaskNum;
    }

    public void setCompleteTaskNum(Integer completeTaskNum) {
        this.completeTaskNum = completeTaskNum;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getTaskSource() {
        return taskSource;
    }

    public void setTaskSource(Integer taskSource) {
        this.taskSource = taskSource;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
