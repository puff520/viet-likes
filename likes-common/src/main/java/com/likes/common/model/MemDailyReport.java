package com.likes.common.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "`mem_daily_report`")
public class MemDailyReport {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`date_str`")
    private String dateStr;
    @Column(name = "`recharge`")
    private BigDecimal recharge;

    @Column(name = "`withdrawal`")
    private BigDecimal withdrawal;

    @Column(name = "`task_award`")
    private BigDecimal taskAward;

    @Column(name = "`buy_vip`")
    private BigDecimal buyVip;

    @Column(name = "`task_brokerage`")
    private BigDecimal taskBrokerage;

    @Column(name = "`vip_brokerage`")
    private BigDecimal vipBrokerage;

    @Column(name = "`accno`")
    private String accno;

    @Column(name = "`top1_accno`")
    private String top1Accno;

    @Column(name = "`top2_accno`")
    private String top2Accno;

    @Column(name = "`top3_accno`")
    private String top3Accno;

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
     * @return task_award
     */
    public BigDecimal getTaskAward() {
        return taskAward;
    }

    /**
     * @param taskAward
     */
    public void setTaskAward(BigDecimal taskAward) {
        this.taskAward = taskAward;
    }

    /**
     * @return buy_vip
     */
    public BigDecimal getBuyVip() {
        return buyVip;
    }

    /**
     * @param buyVip
     */
    public void setBuyVip(BigDecimal buyVip) {
        this.buyVip = buyVip;
    }

    /**
     * @return task_brokerage
     */
    public BigDecimal getTaskBrokerage() {
        return taskBrokerage;
    }

    /**
     * @param taskBrokerage
     */
    public void setTaskBrokerage(BigDecimal taskBrokerage) {
        this.taskBrokerage = taskBrokerage;
    }

    /**
     * @return vip_brokerage
     */
    public BigDecimal getVipBrokerage() {
        return vipBrokerage;
    }

    /**
     * @param vipBrokerage
     */
    public void setVipBrokerage(BigDecimal vipBrokerage) {
        this.vipBrokerage = vipBrokerage;
    }

    /**
     * @return accno
     */
    public String getAccno() {
        return accno;
    }

    /**
     * @param accno
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * @return top1_accno
     */
    public String getTop1Accno() {
        return top1Accno;
    }

    /**
     * @param top1Accno
     */
    public void setTop1Accno(String top1Accno) {
        this.top1Accno = top1Accno;
    }

    /**
     * @return top2_accno
     */
    public String getTop2Accno() {
        return top2Accno;
    }

    /**
     * @param top2Accno
     */
    public void setTop2Accno(String top2Accno) {
        this.top2Accno = top2Accno;
    }

    /**
     * @return top3_accno
     */
    public String getTop3Accno() {
        return top3Accno;
    }

    /**
     * @param top3Accno
     */
    public void setTop3Accno(String top3Accno) {
        this.top3Accno = top3Accno;
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

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}
