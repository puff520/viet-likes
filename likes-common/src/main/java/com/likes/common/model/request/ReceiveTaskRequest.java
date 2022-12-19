package com.likes.common.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.likes.common.enums.task.TaskOrderTypeEnum;

import java.util.Date;
import java.util.List;

public class ReceiveTaskRequest {


    private List<String>  submitSample;

    public List<String> getSubmitSample() {
        return submitSample;
    }

    public void setSubmitSample(List<String> submitSample) {
        this.submitSample = submitSample;
    }

    /**
     * id
     */
    private Integer id;


    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 会员no
     */
    private String memNo;


    /**
     * 会员no
     */
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 任务id
     */
    private Long taskId;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * 任务类型 0 常规任务 1 必领任务
     */
    private Boolean taskType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 任务状态1 进行中  2 审核中 3 已完成 4 已失败 5 恶意 6 已放弃
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更吓人
     */
    private Date updateTime;



    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 备注
     */
    private String remark;

    TaskOrderTypeEnum taskOrderStatus;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
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
     * 获取任务类型 0 常规任务 1 必领任务
     *
     * @return task_type - 任务类型 0 常规任务 1 必领任务
     */
    public Boolean getTaskType() {
        return taskType;
    }

    /**
     * 设置任务类型 0 常规任务 1 必领任务
     *
     * @param taskType 任务类型 0 常规任务 1 必领任务
     */
    public void setTaskType(Boolean taskType) {
        this.taskType = taskType;
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
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置任务状态1 进行中  2 审核中 3 已完成 4 已失败 5 恶意 6 已放弃
     *
     * @param status 任务状态1 进行中  2 审核中 3 已完成 4 已失败 5 恶意 6 已放弃
     */
    public void setStatus(Boolean status) {
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

    public TaskOrderTypeEnum getTaskOrderStatus() {
        return taskOrderStatus;
    }

    public void setTaskOrderStatus(TaskOrderTypeEnum taskOrderStatus) {
        this.taskOrderStatus = taskOrderStatus;
    }
}
