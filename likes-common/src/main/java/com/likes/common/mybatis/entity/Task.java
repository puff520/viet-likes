package com.likes.common.mybatis.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "`dz_task`")
public class Task {
    /**
     * id
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类id
     */
    @Column(name = "`category_id`")
    private Integer categoryId;

    /**
     * 语言
     */
    @Column(name = "`language`")
    private String language;

    /**
     * 任务标题
     */
    @Column(name = "`title`")
    private String title;

    /**
     * 排序字段
     */
    @Column(name = "`sort`")
    private Integer sort;

    /**
     * 任务单价
     */
    @Column(name = "`price`")
    private BigDecimal price;

    @Column(name = "`pump_price`")
    private BigDecimal pumpPrice;

    /**
     * 任务总价
     */
    @Column(name = "`total_price`")
    private BigDecimal totalPrice;

    /**
     * 每人能领取次数
     */
    @Column(name = "`apiece_num`")
    private Integer apieceNum;

    /**
     * 任务类型 1 必须任务 2 常规 任务
     */
    @Column(name = "`task_type`")
    private Integer taskType;

    /**
     * 任务链接
     */
    @Column(name = "`task_url`")
    private String taskUrl;

    /**
     * 任务等级
     */
    @Column(name = "`task_level`")
    private Integer taskLevel;

    @Column(name = "`task_level_ids`")
    private String taskLevelIds;

    /**
     * 认证条件,号分隔
     */
    @Column(name = "`condition`")
    private String condition;

    @Column(name = "`send_sdk_url`")
    private String sendSdkUrl;

    /**
     * 样例url
     */
    @Column(name = "`sample_url`")
    private String sampleUrl;

    /**
     * 任务描述
     */
    @Column(name = "`info`")
    private String info;

    @Column(name = "`create_mobile`")
    private String createMobile;

    @Column(name = "`create_user`")
    private String createUser;

    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 任务到期时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    @Column(name = "`finish_date`")
    private Date finishDate;

    /**
     * 任务状态 1 审核中 2 未通过 3 进行中 4 已撤销 5 已完成
     */
    @Column(name = "`status`")
    private Boolean status;

    /**
     * 可接任务人数
     */
    @Column(name = "`max_num`")
    private Integer maxNum;

    /**
     * 已申请人数
     */
    @Column(name = "`apply_num`")
    private Integer applyNum;

    @Column(name = "`surplus_num`")
    private Integer surplusNum;

    /**
     * 任务要求
     */
    @Column(name = "`task_require`")
    private String taskRequire;

    /**
     * 视频图链接
     */
    @Column(name = "`video_url`")
    private String videoUrl;

    /**
     * 任务步骤。保存json数据
     */
    @Column(name = "`step_Info`")
    private String stepInfo;

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
     * @return category_id - 分类id
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
     * @return pump_price
     */
    public BigDecimal getPumpPrice() {
        return pumpPrice;
    }

    /**
     * @param pumpPrice
     */
    public void setPumpPrice(BigDecimal pumpPrice) {
        this.pumpPrice = pumpPrice;
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
     * @return apiece_num - 每人能领取次数
     */
    public Integer getApieceNum() {
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

    /**
     * 获取任务等级
     *
     * @return task_level - 任务等级
     */
    public Integer getTaskLevel() {
        return taskLevel;
    }

    /**
     * 设置任务等级
     *
     * @param taskLevel 任务等级
     */
    public void setTaskLevel(Integer taskLevel) {
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
     * @return send_sdk_url
     */
    public String getSendSdkUrl() {
        return sendSdkUrl;
    }

    /**
     * @param sendSdkUrl
     */
    public void setSendSdkUrl(String sendSdkUrl) {
        this.sendSdkUrl = sendSdkUrl;
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
     * @return create_mobile
     */
    public String getCreateMobile() {
        return createMobile;
    }

    /**
     * @param createMobile
     */
    public void setCreateMobile(String createMobile) {
        this.createMobile = createMobile;
    }

    /**
     * @return create_user
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置任务状态 1 审核中 2 未通过 3 进行中 4 已撤销 5 已完成
     *
     * @param status 任务状态 1 审核中 2 未通过 3 进行中 4 已撤销 5 已完成
     */
    public void setStatus(Boolean status) {
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
     * @return surplus_num
     */
    public Integer getSurplusNum() {
        return surplusNum;
    }

    /**
     * @param surplusNum
     */
    public void setSurplusNum(Integer surplusNum) {
        this.surplusNum = surplusNum;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTaskLevelIds() {
        return taskLevelIds;
    }

    public void setTaskLevelIds(String taskLevelIds) {
        this.taskLevelIds = taskLevelIds;
    }
}
