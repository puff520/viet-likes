package com.likes.common.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class TaskRequest extends BaseRequest {

    /**
     * id
     */
    private Long id;

    /**
     * id
     */
    private Long taskId;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 分类id
     */
    private Integer levelSeq;

    private String levelSeqStr;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 任务单价
     */
    private BigDecimal price;

    /**
     * 任务总价
     */
    private BigDecimal totalPrice;

    /**
     * 每人能领取次数
     */
    private Integer apieceNum = 1;

    /**
     * 任务类型 1 必须任务 2 常规 任务
     */
    private Integer taskType;

    /**
     * 任务链接
     */
    private String taskUrl;

    /**
     * 任务等级
     */
    private Set<Integer> taskLevel;



    /**
     * 认证条件,号分隔
     */
    private String condition;

    /**
     * 样例url
     */
    private String sampleUrl;

    /**
     * 任务描述
     */
    private String info;

    private Date createTime;

    private String updateUser;

    private String createUser;

    private Integer taskSource;

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 任务到期时间
     */
    private Date updateTime;


    private Date finishDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Integer status;

    private String statusStr;


    /**
     * 语言
     */
    private String language;

    /**
     * 可接任务人数
     */
    private Integer maxNum;

    /**
     * 已申请人数
     */
    private Integer applyNum;

    /**
     * 任务要求
     */
    private String taskRequire;

    /**
     * 视频图链接
     */
    private String videoUrl;

    private String accno;

    /**
     * 任务步骤。保存json数据
     */
    private String stepInfo;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getApieceNum() {
        return apieceNum;
    }

    public void setApieceNum(Integer apieceNum) {
        this.apieceNum = apieceNum;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getTaskUrl() {
        return taskUrl;
    }

    public void setTaskUrl(String taskUrl) {
        this.taskUrl = taskUrl;
    }

    public Set<Integer> getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(Set<Integer> taskLevel) {
        this.taskLevel = taskLevel;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public String getTaskRequire() {
        return taskRequire;
    }

    public void setTaskRequire(String taskRequire) {
        this.taskRequire = taskRequire;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getStepInfo() {
        return stepInfo;
    }

    public void setStepInfo(String stepInfo) {
        this.stepInfo = stepInfo;
    }

    public Integer getLevelSeq() {
        return levelSeq;
    }

    public void setLevelSeq(Integer levelSeq) {
        this.levelSeq = levelSeq;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskSource() {
        return taskSource;
    }

    public void setTaskSource(Integer taskSource) {
        this.taskSource = taskSource;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevelSeqStr() {
        return levelSeqStr;
    }

    public void setLevelSeqStr(String levelSeqStr) {
        this.levelSeqStr = levelSeqStr;
    }
}
