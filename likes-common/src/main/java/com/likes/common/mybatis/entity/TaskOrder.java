package com.likes.common.mybatis.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "`dz_task_order`")
public class TaskOrder implements Serializable {

    /**
     * id
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单号
     */
    @Column(name = "`order_no`")
    private String orderNo;

    /**
     * 会员no
     */
    @Column(name = "`mem_no`")
    private String memNo;

    @Column(name = "`mobile`")
    private String mobile;

    /**
     * 任务id
     */
    @Column(name = "`task_id`")
    private Long taskId;

    @Column(name = "`receive_level`")
    private String receiveLevel;

    @Column(name = "`receive_price`")
    private BigDecimal receivePrice;

    /**
     * 任务类型 0 常规任务 1 必领任务
     */
    @Column(name = "`task_type`")
    private Integer taskType;

    @Column(name = "`submit_sample`")
    private String submitSample;

    /**
     * 排序字段
     */
    @Column(name = "`sort`")
    private Integer sort;

    /**
     * 任务状态1 进行中  2 审核中 3 已完成 4 已失败 5 恶意 6 已放弃
     */
    @Column(name = "`status`")
    private Integer status;

    @Column(name = "`send_status`")
    private Integer sendStatus;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更吓人
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 任务到期时间
     */
    @Column(name = "`end_time`")
    private Date endTime;

    @Column(name = "`create_user`")
    private String createUser;

    /**
     * 更新人
     */
    @Column(name = "`update_user`")
    private String updateUser;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

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
     * 获取订单号
     *
     * @return order_no - 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单号
     *
     * @param orderNo 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取会员no
     *
     * @return mem_no - 会员no
     */
    public String getMemNo() {
        return memNo;
    }

    /**
     * 设置会员no
     *
     * @param memNo 会员no
     */
    public void setMemNo(String memNo) {
        this.memNo = memNo;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取任务id
     *
     * @return task_id - 任务id
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * 设置任务id
     *
     * @param taskId 任务id
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取任务类型 0 常规任务 1 必领任务
     *
     * @return task_type - 任务类型 0 常规任务 1 必领任务
     */
    public Integer getTaskType() {
        return taskType;
    }

    /**
     * 设置任务类型 0 常规任务 1 必领任务
     *
     * @param taskType 任务类型 0 常规任务 1 必领任务
     */
    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    /**
     * @return submit_sample
     */
    public String getSubmitSample() {
        return submitSample;
    }

    /**
     * @param submitSample
     */
    public void setSubmitSample(String submitSample) {
        this.submitSample = submitSample;
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
     * 获取任务状态1 进行中  2 审核中 3 已完成 4 已失败 5 恶意 6 已放弃
     *
     * @return status - 任务状态1 进行中  2 审核中 3 已完成 4 已失败 5 恶意 6 已放弃
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置任务状态1 进行中  2 审核中 3 已完成 4 已失败 5 恶意 6 已放弃
     *
     * @param status 任务状态1 进行中  2 审核中 3 已完成 4 已失败 5 恶意 6 已放弃
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更吓人
     *
     * @return update_time - 更吓人
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更吓人
     *
     * @param updateTime 更吓人
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取任务到期时间
     *
     * @return end_time - 任务到期时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置任务到期时间
     *
     * @param endTime 任务到期时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取更新人
     *
     * @return update_user - 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置更新人
     *
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public BigDecimal getReceivePrice() {
        return receivePrice;
    }

    public void setReceivePrice(BigDecimal receivePrice) {
        this.receivePrice = receivePrice;
    }

    public String getReceiveLevel() {
        return receiveLevel;
    }

    public void setReceiveLevel(String receiveLevel) {
        this.receiveLevel = receiveLevel;
    }
}
