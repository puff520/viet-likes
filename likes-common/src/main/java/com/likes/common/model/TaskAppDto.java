package com.likes.common.model;

import java.math.BigDecimal;
import java.util.Date;

public class TaskAppDto {

    /**
     * id
     */
    private Long taskId;

    /**
     *  任务分类名称
     */
    private  String categoryName;
    private  String taskLevelIds;

    /**
     * 需求方手机号
     */
    private  String mobile;


    /**
     * 需求方
     */
    private  String createUser;

    /**
     * 任务价格
     */
    private  BigDecimal price;

    /**
     * 剩余数量
     */
    private  Integer surplusNum;

    /**
     * 任务要求
     */
    private  String taskRequire;

    /**
     * 任务等级
     */
    private  String level;

    private  Integer levelSeq;



    /**
     * 任务图标
     */
    private  String taskIcon;


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTaskIcon() {
        return taskIcon;
    }

    public void setTaskIcon(String taskIcon) {
        this.taskIcon = taskIcon;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getLevelSeq() {
        return levelSeq;
    }

    public void setLevelSeq(Integer levelSeq) {
        this.levelSeq = levelSeq;
    }

    public String getTaskLevelIds() {
        return taskLevelIds;
    }

    public void setTaskLevelIds(String taskLevelIds) {
        this.taskLevelIds = taskLevelIds;
    }
}
