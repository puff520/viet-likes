package com.likes.common.mybatis.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "`every_day_report`")
public class EveryDayReport {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`create_date`")
    private String createDate;

    @Column(name = "`recharge`")
    private BigDecimal recharge;

    @Column(name = "`withdrawal`")
    private BigDecimal withdrawal;

    @Column(name = "`add_vip_num`")
    private Integer addVipNum;

    @Column(name = "`sub_task_amount`")
    private BigDecimal subTaskAmount;

    @Column(name = "`task_amount`")
    private BigDecimal taskAmount;

    @Column(name = "`vip_amount`")
    private BigDecimal vipAmount;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return create_date
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return recharge
     */
    public BigDecimal getRecharge() {
        return recharge;
    }

    /**
     * @param recharge
     */
    public void setRecharge(BigDecimal recharge) {
        this.recharge = recharge;
    }

    /**
     * @return withdrawal
     */
    public BigDecimal getWithdrawal() {
        return withdrawal;
    }

    /**
     * @param withdrawal
     */
    public void setWithdrawal(BigDecimal withdrawal) {
        this.withdrawal = withdrawal;
    }

    /**
     * @return add_vip_num
     */
    public Integer getAddVipNum() {
        return addVipNum;
    }

    /**
     * @param addVipNum
     */
    public void setAddVipNum(Integer addVipNum) {
        this.addVipNum = addVipNum;
    }

    /**
     * @return sub_task_amount
     */
    public BigDecimal getSubTaskAmount() {
        return subTaskAmount;
    }

    /**
     * @param subTaskAmount
     */
    public void setSubTaskAmount(BigDecimal subTaskAmount) {
        this.subTaskAmount = subTaskAmount;
    }

    /**
     * @return task_amount
     */
    public BigDecimal getTaskAmount() {
        return taskAmount;
    }

    /**
     * @param taskAmount
     */
    public void setTaskAmount(BigDecimal taskAmount) {
        this.taskAmount = taskAmount;
    }

    /**
     * @return vip_amount
     */
    public BigDecimal getVipAmount() {
        return vipAmount;
    }

    /**
     * @param vipAmount
     */
    public void setVipAmount(BigDecimal vipAmount) {
        this.vipAmount = vipAmount;
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
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
