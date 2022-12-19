package com.likes.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class TaskDetail {

    /**
     * id
     */
    private Long id;

    /**
     * 分类id
     */
    private Integer categoryId;

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
    private Integer apieceNum;

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
    private List<Integer> taskLevel;

    private String taskLevelIds;

    private Integer levelSeq;

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


    private String createUser;

    private String createMobile;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-5")
    private Date createTime;

    /**
     * 任务到期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT-5")
    private Date updateTime;


    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT-5")
    private Date finishDate;

    /**
     * 任务状态 1 审核中 2 未通过 3 进行中 4 已撤销 5 已完成
     */
    private Integer status;

    /**
     * 可接任务人数
     */
    private Integer maxNum;

    /**
     * 已申请人数
     */
    private Integer applyNum;

    /**
     * 剩余数量
     */
    private Integer surplusNum;


    /**
     * 任务要求
     */
    private String taskRequire;

    /**
     * 视频图链接
     */
    private String videoUrl;

    /**
     * 任务步骤。保存json数据
     */
    private String stepInfo;

    /**
     * 分类名称
     */
    private String categoryName;
    private String sendSdkUrl;


    private  Integer completeTaskNum;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取分类id
     *
     * @return cid - 分类id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置分类id
     *
     * @param categoryId 分类id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取任务标题
     *
     * @return title - 任务标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置任务标题
     *
     * @param title 任务标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取排序字段
     *
     * @return sort - 排序字段
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序字段
     *
     * @param sort 排序字段
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取任务单价
     *
     * @return price - 任务单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置任务单价
     *
     * @param price 任务单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取任务总价
     *
     * @return total_price - 任务总价
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置任务总价
     *
     * @param totalPrice 任务总价
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 获取每人能领取次数
     *
     * @return apieceNum - 每人能领取次数
     */
    public Integer getApiecenum() {
        return apieceNum;
    }

    /**
     * 设置每人能领取次数
     *
     * @param apieceNum 每人能领取次数
     */
    public void setApieceNum(Integer apieceNum) {
        this.apieceNum = apieceNum;
    }

    /**
     * 获取任务类型 1 必须任务 2 常规 任务
     *
     * @return task_type - 任务类型 1 必须任务 2 常规 任务
     */
    public Integer getTaskType() {
        return taskType;
    }

    /**
     * 设置任务类型 1 必须任务 2 常规 任务
     *
     * @param taskType 任务类型 1 必须任务 2 常规 任务
     */
    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    /**
     * 获取任务链接
     *
     * @return task_url - 任务链接
     */
    public String getTaskUrl() {
        return taskUrl;
    }

    /**
     * 设置任务链接
     *
     * @param taskUrl 任务链接
     */
    public void setTaskUrl(String taskUrl) {
        this.taskUrl = taskUrl;
    }

    public List<Integer> getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(List<Integer> taskLevel) {
        this.taskLevel = taskLevel;
    }

    /**
     * 获取认证条件,号分隔
     *
     * @return condition - 认证条件,号分隔
     */
    public String getCondition() {
        return condition;
    }

    /**
     * 设置认证条件,号分隔
     *
     * @param condition 认证条件,号分隔
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * 获取样例url
     *
     * @return sample_url - 样例url
     */
    public String getSampleUrl() {
        return sampleUrl;
    }

    /**
     * 设置样例url
     *
     * @param sampleUrl 样例url
     */
    public void setSampleUrl(String sampleUrl) {
        this.sampleUrl = sampleUrl;
    }

    /**
     * 获取任务描述
     *
     * @return info - 任务描述
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置任务描述
     *
     * @param info 任务描述
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取任务到期时间
     *
     * @return update_time - 任务到期时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置任务到期时间
     *
     * @param updateTime 任务到期时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return finish_date
     */
    public Date getFinishDate() {
        return finishDate;
    }

    /**
     * @param finishDate
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * 获取任务状态 1 审核中 2 未通过 3 进行中 4 已撤销 5 已完成
     *
     * @return status - 任务状态 1 审核中 2 未通过 3 进行中 4 已撤销 5 已完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置任务状态 1 审核中 2 未通过 3 进行中 4 已撤销 5 已完成
     *
     * @param status 任务状态 1 审核中 2 未通过 3 进行中 4 已撤销 5 已完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取可接任务人数
     *
     * @return max_num - 可接任务人数
     */
    public Integer getMaxNum() {
        return maxNum;
    }

    /**
     * 设置可接任务人数
     *
     * @param maxNum 可接任务人数
     */
    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    /**
     * 获取已申请人数
     *
     * @return apply_num - 已申请人数
     */
    public Integer getApplyNum() {
        return applyNum;
    }

    /**
     * 设置已申请人数
     *
     * @param applyNum 已申请人数
     */
    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    /**
     * 获取任务要求
     *
     * @return task_require - 任务要求
     */
    public String getTaskRequire() {
        return taskRequire;
    }

    /**
     * 设置任务要求
     *
     * @param taskRequire 任务要求
     */
    public void setTaskRequire(String taskRequire) {
        this.taskRequire = taskRequire;
    }

    /**
     * 获取视频图链接
     *
     * @return video_url - 视频图链接
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 设置视频图链接
     *
     * @param videoUrl 视频图链接
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * 获取任务步骤。保存json数据
     *
     * @return step_Info - 任务步骤。保存json数据
     */
    public String getStepInfo() {
        return stepInfo;
    }

    /**
     * 设置任务步骤。保存json数据
     *
     * @param stepInfo 任务步骤。保存json数据
     */
    public void setStepInfo(String stepInfo) {
        this.stepInfo = stepInfo;
    }

    public Integer getApieceNum() {
        return apieceNum;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateMobile() {
        return createMobile;
    }

    public void setCreateMobile(String createMobile) {
        this.createMobile = createMobile;
    }

    public String getSendSdkUrl() {
        return sendSdkUrl;
    }

    public void setSendSdkUrl(String sendSdkUrl) {
        this.sendSdkUrl = sendSdkUrl;
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
