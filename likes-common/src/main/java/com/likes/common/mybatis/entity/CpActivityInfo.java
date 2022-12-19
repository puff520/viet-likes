package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CpActivityInfo implements Serializable {
    /**
     * 字段: cp_activity_info.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 自增主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 字段: cp_activity_info.act_out_banner<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 200<br/>
     * 说明: 活动表层横幅
     *
     * @mbggenerated
     */
    private String actOutBanner;

    /**
     * 字段: cp_activity_info.act_in_banner<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 200<br/>
     * 说明: 活动内页横幅
     *
     * @mbggenerated
     */
    private String actInBanner;

    /**
     * 字段: cp_activity_info.act_start_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 活动开始时间
     *
     * @mbggenerated
     */
    private Date actStartTime;

    /**
     * 字段: cp_activity_info.act_end_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 活动结束时间
     *
     * @mbggenerated
     */
    private Date actEndTime;

    /**
     * 字段: cp_activity_info.act_title<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 活动标题
     *
     * @mbggenerated
     */
    private String actTitle;

    /**
     * 字段: cp_activity_info.act_guide<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 活动引导
     *
     * @mbggenerated
     */
    private String actGuide;

    /**
     * 字段: cp_activity_info.act_min_amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 活动最小金额（红包）
     *
     * @mbggenerated
     */
    private BigDecimal actMinAmount;

    /**
     * 字段: cp_activity_info.act_max_amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 活动最大金额（红包）
     *
     * @mbggenerated
     */
    private BigDecimal actMaxAmount;

    /**
     * 字段: cp_activity_info.act_min_sham_amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 活动最小伪装金额
     *
     * @mbggenerated
     */
    private BigDecimal actMinShamAmount;

    /**
     * 字段: cp_activity_info.act_max_sham_amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 活动最大伪装金额
     *
     * @mbggenerated
     */
    private BigDecimal actMaxShamAmount;

    /**
     * 字段: cp_activity_info.act_type<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 活动类型0其他，1红包
     *
     * @mbggenerated
     */
    private Integer actType;

    /**
     * 字段: cp_activity_info.act_status<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 活动状态0开启，1关闭
     *
     * @mbggenerated
     */
    private Integer actStatus;

    /**
     * 字段: cp_activity_info.act_into_page<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 进入页面
     *
     * @mbggenerated
     */
    private Integer actIntoPage;

    /**
     * 字段: cp_activity_info.is_popup<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否弹出0是，1否
     *
     * @mbggenerated
     */
    private Integer isPopup;

    /**
     * 字段: cp_activity_info.act_receive_limit_bet_amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 领取条件：打码量
     *
     * @mbggenerated
     */
    private BigDecimal actReceiveLimitBetAmount;

    /**
     * 字段: cp_activity_info.act_receive_limit_amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 领取条件：充值额度
     *
     * @mbggenerated
     */
    private BigDecimal actReceiveLimitAmount;

    /**
     * 字段: cp_activity_info.is_today_charge_hundred<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 领取条件当日充值过百
     *
     * @mbggenerated
     */
    private Integer isTodayChargeHundred;

    /**
     * 字段: cp_activity_info.start_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 启动时间
     *
     * @mbggenerated
     */
    private String startTime;

    /**
     * 字段: cp_activity_info.stop_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 结束时间
     *
     * @mbggenerated
     */
    private String stopTime;

    /**
     * 字段: cp_activity_info.time_type<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 时间类型(默认0为持续，1为定点)
     *
     * @mbggenerated
     */
    private Integer timeType;

    /**
     * 字段: cp_activity_info.start_date<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 启动日期
     *
     * @mbggenerated
     */
    private String startDate;

    /**
     * 字段: cp_activity_info.stop_date<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 结束日期
     *
     * @mbggenerated
     */
    private String stopDate;

    /**
     * 字段: cp_activity_info.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: cp_activity_info.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: cp_activity_info.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 修改时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cp_activity_info
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return cp_activity_info.id: 自增主键
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: cp_activity_info.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 自增主键
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return cp_activity_info.act_out_banner: 活动表层横幅
     *
     * @mbggenerated
     */
    public String getActOutBanner() {
        return actOutBanner;
    }

    /**
     * 字段: cp_activity_info.act_out_banner<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 200<br/>
     * 说明: 活动表层横幅
     *
     * @mbggenerated
     */
    public void setActOutBanner(String actOutBanner) {
        this.actOutBanner = actOutBanner;
    }

    /**
     * @return cp_activity_info.act_in_banner: 活动内页横幅
     *
     * @mbggenerated
     */
    public String getActInBanner() {
        return actInBanner;
    }

    /**
     * 字段: cp_activity_info.act_in_banner<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 200<br/>
     * 说明: 活动内页横幅
     *
     * @mbggenerated
     */
    public void setActInBanner(String actInBanner) {
        this.actInBanner = actInBanner;
    }

    /**
     * @return cp_activity_info.act_start_time: 活动开始时间
     *
     * @mbggenerated
     */
    public Date getActStartTime() {
        return actStartTime;
    }

    /**
     * 字段: cp_activity_info.act_start_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 活动开始时间
     *
     * @mbggenerated
     */
    public void setActStartTime(Date actStartTime) {
        this.actStartTime = actStartTime;
    }

    /**
     * @return cp_activity_info.act_end_time: 活动结束时间
     *
     * @mbggenerated
     */
    public Date getActEndTime() {
        return actEndTime;
    }

    /**
     * 字段: cp_activity_info.act_end_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 活动结束时间
     *
     * @mbggenerated
     */
    public void setActEndTime(Date actEndTime) {
        this.actEndTime = actEndTime;
    }

    /**
     * @return cp_activity_info.act_title: 活动标题
     *
     * @mbggenerated
     */
    public String getActTitle() {
        return actTitle;
    }

    /**
     * 字段: cp_activity_info.act_title<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 活动标题
     *
     * @mbggenerated
     */
    public void setActTitle(String actTitle) {
        this.actTitle = actTitle;
    }

    /**
     * @return cp_activity_info.act_guide: 活动引导
     *
     * @mbggenerated
     */
    public String getActGuide() {
        return actGuide;
    }

    /**
     * 字段: cp_activity_info.act_guide<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 活动引导
     *
     * @mbggenerated
     */
    public void setActGuide(String actGuide) {
        this.actGuide = actGuide;
    }

    /**
     * @return cp_activity_info.act_min_amount: 活动最小金额（红包）
     *
     * @mbggenerated
     */
    public BigDecimal getActMinAmount() {
        return actMinAmount;
    }

    /**
     * 字段: cp_activity_info.act_min_amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 活动最小金额（红包）
     *
     * @mbggenerated
     */
    public void setActMinAmount(BigDecimal actMinAmount) {
        this.actMinAmount = actMinAmount;
    }

    /**
     * @return cp_activity_info.act_max_amount: 活动最大金额（红包）
     *
     * @mbggenerated
     */
    public BigDecimal getActMaxAmount() {
        return actMaxAmount;
    }

    /**
     * 字段: cp_activity_info.act_max_amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 活动最大金额（红包）
     *
     * @mbggenerated
     */
    public void setActMaxAmount(BigDecimal actMaxAmount) {
        this.actMaxAmount = actMaxAmount;
    }

    /**
     * @return cp_activity_info.act_min_sham_amount: 活动最小伪装金额
     *
     * @mbggenerated
     */
    public BigDecimal getActMinShamAmount() {
        return actMinShamAmount;
    }

    /**
     * 字段: cp_activity_info.act_min_sham_amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 活动最小伪装金额
     *
     * @mbggenerated
     */
    public void setActMinShamAmount(BigDecimal actMinShamAmount) {
        this.actMinShamAmount = actMinShamAmount;
    }

    /**
     * @return cp_activity_info.act_max_sham_amount: 活动最大伪装金额
     *
     * @mbggenerated
     */
    public BigDecimal getActMaxShamAmount() {
        return actMaxShamAmount;
    }

    /**
     * 字段: cp_activity_info.act_max_sham_amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 活动最大伪装金额
     *
     * @mbggenerated
     */
    public void setActMaxShamAmount(BigDecimal actMaxShamAmount) {
        this.actMaxShamAmount = actMaxShamAmount;
    }

    /**
     * @return cp_activity_info.act_type: 活动类型0其他，1红包
     *
     * @mbggenerated
     */
    public Integer getActType() {
        return actType;
    }

    /**
     * 字段: cp_activity_info.act_type<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 活动类型0其他，1红包
     *
     * @mbggenerated
     */
    public void setActType(Integer actType) {
        this.actType = actType;
    }

    /**
     * @return cp_activity_info.act_status: 活动状态0开启，1关闭
     *
     * @mbggenerated
     */
    public Integer getActStatus() {
        return actStatus;
    }

    /**
     * 字段: cp_activity_info.act_status<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 活动状态0开启，1关闭
     *
     * @mbggenerated
     */
    public void setActStatus(Integer actStatus) {
        this.actStatus = actStatus;
    }

    /**
     * @return cp_activity_info.act_into_page: 进入页面
     *
     * @mbggenerated
     */
    public Integer getActIntoPage() {
        return actIntoPage;
    }

    /**
     * 字段: cp_activity_info.act_into_page<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 进入页面
     *
     * @mbggenerated
     */
    public void setActIntoPage(Integer actIntoPage) {
        this.actIntoPage = actIntoPage;
    }

    /**
     * @return cp_activity_info.is_popup: 是否弹出0是，1否
     *
     * @mbggenerated
     */
    public Integer getIsPopup() {
        return isPopup;
    }

    /**
     * 字段: cp_activity_info.is_popup<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否弹出0是，1否
     *
     * @mbggenerated
     */
    public void setIsPopup(Integer isPopup) {
        this.isPopup = isPopup;
    }

    /**
     * @return cp_activity_info.act_receive_limit_bet_amount: 领取条件：打码量
     *
     * @mbggenerated
     */
    public BigDecimal getActReceiveLimitBetAmount() {
        return actReceiveLimitBetAmount;
    }

    /**
     * 字段: cp_activity_info.act_receive_limit_bet_amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 领取条件：打码量
     *
     * @mbggenerated
     */
    public void setActReceiveLimitBetAmount(BigDecimal actReceiveLimitBetAmount) {
        this.actReceiveLimitBetAmount = actReceiveLimitBetAmount;
    }

    /**
     * @return cp_activity_info.act_receive_limit_amount: 领取条件：充值额度
     *
     * @mbggenerated
     */
    public BigDecimal getActReceiveLimitAmount() {
        return actReceiveLimitAmount;
    }

    /**
     * 字段: cp_activity_info.act_receive_limit_amount<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 领取条件：充值额度
     *
     * @mbggenerated
     */
    public void setActReceiveLimitAmount(BigDecimal actReceiveLimitAmount) {
        this.actReceiveLimitAmount = actReceiveLimitAmount;
    }

    /**
     * @return cp_activity_info.is_today_charge_hundred: 领取条件当日充值过百
     *
     * @mbggenerated
     */
    public Integer getIsTodayChargeHundred() {
        return isTodayChargeHundred;
    }

    /**
     * 字段: cp_activity_info.is_today_charge_hundred<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 领取条件当日充值过百
     *
     * @mbggenerated
     */
    public void setIsTodayChargeHundred(Integer isTodayChargeHundred) {
        this.isTodayChargeHundred = isTodayChargeHundred;
    }

    /**
     * @return cp_activity_info.start_time: 启动时间
     *
     * @mbggenerated
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 字段: cp_activity_info.start_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 启动时间
     *
     * @mbggenerated
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return cp_activity_info.stop_time: 结束时间
     *
     * @mbggenerated
     */
    public String getStopTime() {
        return stopTime;
    }

    /**
     * 字段: cp_activity_info.stop_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 结束时间
     *
     * @mbggenerated
     */
    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    /**
     * @return cp_activity_info.time_type: 时间类型(默认0为持续，1为定点)
     *
     * @mbggenerated
     */
    public Integer getTimeType() {
        return timeType;
    }

    /**
     * 字段: cp_activity_info.time_type<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 时间类型(默认0为持续，1为定点)
     *
     * @mbggenerated
     */
    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    /**
     * @return cp_activity_info.start_date: 启动日期
     *
     * @mbggenerated
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 字段: cp_activity_info.start_date<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 启动日期
     *
     * @mbggenerated
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return cp_activity_info.stop_date: 结束日期
     *
     * @mbggenerated
     */
    public String getStopDate() {
        return stopDate;
    }

    /**
     * 字段: cp_activity_info.stop_date<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 结束日期
     *
     * @mbggenerated
     */
    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    /**
     * @return cp_activity_info.is_delete: 是否删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 字段: cp_activity_info.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @return cp_activity_info.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: cp_activity_info.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return cp_activity_info.update_time: 修改时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: cp_activity_info.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 修改时间
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_activity_info
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CpActivityInfo other = (CpActivityInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getActOutBanner() == null ? other.getActOutBanner() == null : this.getActOutBanner().equals(other.getActOutBanner()))
            && (this.getActInBanner() == null ? other.getActInBanner() == null : this.getActInBanner().equals(other.getActInBanner()))
            && (this.getActStartTime() == null ? other.getActStartTime() == null : this.getActStartTime().equals(other.getActStartTime()))
            && (this.getActEndTime() == null ? other.getActEndTime() == null : this.getActEndTime().equals(other.getActEndTime()))
            && (this.getActTitle() == null ? other.getActTitle() == null : this.getActTitle().equals(other.getActTitle()))
            && (this.getActGuide() == null ? other.getActGuide() == null : this.getActGuide().equals(other.getActGuide()))
            && (this.getActMinAmount() == null ? other.getActMinAmount() == null : this.getActMinAmount().equals(other.getActMinAmount()))
            && (this.getActMaxAmount() == null ? other.getActMaxAmount() == null : this.getActMaxAmount().equals(other.getActMaxAmount()))
            && (this.getActMinShamAmount() == null ? other.getActMinShamAmount() == null : this.getActMinShamAmount().equals(other.getActMinShamAmount()))
            && (this.getActMaxShamAmount() == null ? other.getActMaxShamAmount() == null : this.getActMaxShamAmount().equals(other.getActMaxShamAmount()))
            && (this.getActType() == null ? other.getActType() == null : this.getActType().equals(other.getActType()))
            && (this.getActStatus() == null ? other.getActStatus() == null : this.getActStatus().equals(other.getActStatus()))
            && (this.getActIntoPage() == null ? other.getActIntoPage() == null : this.getActIntoPage().equals(other.getActIntoPage()))
            && (this.getIsPopup() == null ? other.getIsPopup() == null : this.getIsPopup().equals(other.getIsPopup()))
            && (this.getActReceiveLimitBetAmount() == null ? other.getActReceiveLimitBetAmount() == null : this.getActReceiveLimitBetAmount().equals(other.getActReceiveLimitBetAmount()))
            && (this.getActReceiveLimitAmount() == null ? other.getActReceiveLimitAmount() == null : this.getActReceiveLimitAmount().equals(other.getActReceiveLimitAmount()))
            && (this.getIsTodayChargeHundred() == null ? other.getIsTodayChargeHundred() == null : this.getIsTodayChargeHundred().equals(other.getIsTodayChargeHundred()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getStopTime() == null ? other.getStopTime() == null : this.getStopTime().equals(other.getStopTime()))
            && (this.getTimeType() == null ? other.getTimeType() == null : this.getTimeType().equals(other.getTimeType()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getStopDate() == null ? other.getStopDate() == null : this.getStopDate().equals(other.getStopDate()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_activity_info
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getActOutBanner() == null) ? 0 : getActOutBanner().hashCode());
        result = prime * result + ((getActInBanner() == null) ? 0 : getActInBanner().hashCode());
        result = prime * result + ((getActStartTime() == null) ? 0 : getActStartTime().hashCode());
        result = prime * result + ((getActEndTime() == null) ? 0 : getActEndTime().hashCode());
        result = prime * result + ((getActTitle() == null) ? 0 : getActTitle().hashCode());
        result = prime * result + ((getActGuide() == null) ? 0 : getActGuide().hashCode());
        result = prime * result + ((getActMinAmount() == null) ? 0 : getActMinAmount().hashCode());
        result = prime * result + ((getActMaxAmount() == null) ? 0 : getActMaxAmount().hashCode());
        result = prime * result + ((getActMinShamAmount() == null) ? 0 : getActMinShamAmount().hashCode());
        result = prime * result + ((getActMaxShamAmount() == null) ? 0 : getActMaxShamAmount().hashCode());
        result = prime * result + ((getActType() == null) ? 0 : getActType().hashCode());
        result = prime * result + ((getActStatus() == null) ? 0 : getActStatus().hashCode());
        result = prime * result + ((getActIntoPage() == null) ? 0 : getActIntoPage().hashCode());
        result = prime * result + ((getIsPopup() == null) ? 0 : getIsPopup().hashCode());
        result = prime * result + ((getActReceiveLimitBetAmount() == null) ? 0 : getActReceiveLimitBetAmount().hashCode());
        result = prime * result + ((getActReceiveLimitAmount() == null) ? 0 : getActReceiveLimitAmount().hashCode());
        result = prime * result + ((getIsTodayChargeHundred() == null) ? 0 : getIsTodayChargeHundred().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getStopTime() == null) ? 0 : getStopTime().hashCode());
        result = prime * result + ((getTimeType() == null) ? 0 : getTimeType().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getStopDate() == null) ? 0 : getStopDate().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cp_activity_info
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", actOutBanner=").append(actOutBanner);
        sb.append(", actInBanner=").append(actInBanner);
        sb.append(", actStartTime=").append(actStartTime);
        sb.append(", actEndTime=").append(actEndTime);
        sb.append(", actTitle=").append(actTitle);
        sb.append(", actGuide=").append(actGuide);
        sb.append(", actMinAmount=").append(actMinAmount);
        sb.append(", actMaxAmount=").append(actMaxAmount);
        sb.append(", actMinShamAmount=").append(actMinShamAmount);
        sb.append(", actMaxShamAmount=").append(actMaxShamAmount);
        sb.append(", actType=").append(actType);
        sb.append(", actStatus=").append(actStatus);
        sb.append(", actIntoPage=").append(actIntoPage);
        sb.append(", isPopup=").append(isPopup);
        sb.append(", actReceiveLimitBetAmount=").append(actReceiveLimitBetAmount);
        sb.append(", actReceiveLimitAmount=").append(actReceiveLimitAmount);
        sb.append(", isTodayChargeHundred=").append(isTodayChargeHundred);
        sb.append(", startTime=").append(startTime);
        sb.append(", stopTime=").append(stopTime);
        sb.append(", timeType=").append(timeType);
        sb.append(", startDate=").append(startDate);
        sb.append(", stopDate=").append(stopDate);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}