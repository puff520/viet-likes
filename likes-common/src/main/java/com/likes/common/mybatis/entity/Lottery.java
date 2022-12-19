package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class Lottery implements Serializable {
    /**
     * 字段: lottery.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 字段: lottery.name<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 彩票名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 字段: lottery.icon<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 彩票图标
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * 字段: lottery.category_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 彩票分类id
     *
     * @mbggenerated
     */
    private Integer categoryId;

    /**
     * 字段: lottery.parent_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 开奖号码源彩种
     *
     * @mbggenerated
     */
    private Integer parentId;

    /**
     * 字段: lottery.startlotto_table<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 开奖号码表
     *
     * @mbggenerated
     */
    private String startlottoTable;

    /**
     * 字段: lottery.startlotto_times<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 每天/年开奖期数
     *
     * @mbggenerated
     */
    private Integer startlottoTimes;

    /**
     * 字段: lottery.clearing_tag<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 清算标识
     *
     * @mbggenerated
     */
    private String clearingTag;

    /**
     * 字段: lottery.cache_tag<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 缓存标识
     *
     * @mbggenerated
     */
    private String cacheTag;

    /**
     * 字段: lottery.max_odds<br/>
     * 必填: true<br/>
     * 缺省: 0.00<br/>
     * 长度: 10<br/>
     * 说明: 最大赔率
     *
     * @mbggenerated
     */
    private Double maxOdds;

    /**
     * 字段: lottery.min_odds<br/>
     * 必填: true<br/>
     * 缺省: 0.00<br/>
     * 长度: 10<br/>
     * 说明: 最小赔率
     *
     * @mbggenerated
     */
    private Double minOdds;

    /**
     * 字段: lottery.sort<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 排序
     *
     * @mbggenerated
     */
    private Integer sort;

    /**
     * 字段: lottery.push_source<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 推送号码来源
     *
     * @mbggenerated
     */
    private String pushSource;

    /**
     * 字段: lottery.push_status<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 推送状态;0不推送;1,推送
     *
     * @mbggenerated
     */
    private Integer pushStatus;

    /**
     * 字段: lottery.video_url<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 开奖视频链接
     *
     * @mbggenerated
     */
    private String videoUrl;

    /**
     * 字段: lottery.video_start<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 视频打开方式
     *
     * @mbggenerated
     */
    private Integer videoStart;

    /**
     * 字段: lottery.is_work<br/>
     * 必填: false<br/>
     * 缺省: 1<br/>
     * 长度: 10<br/>
     * 说明: 是否开售
     *
     * @mbggenerated
     */
    private Integer isWork;

    /**
     * 字段: lottery.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: lottery.lottery_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 彩种编号
     *
     * @mbggenerated
     */
    private Integer lotteryId;

    /**
     * 字段: lottery.end_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 封盘时间-app端使用
     *
     * @mbggenerated
     */
    private Integer endTime;

    /**
     * 字段: lottery.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: lottery.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lottery
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return lottery.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: lottery.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return lottery.name: 彩票名称
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 字段: lottery.name<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 彩票名称
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return lottery.icon: 彩票图标
     *
     * @mbggenerated
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 字段: lottery.icon<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 彩票图标
     *
     * @mbggenerated
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return lottery.category_id: 彩票分类id
     *
     * @mbggenerated
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 字段: lottery.category_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 彩票分类id
     *
     * @mbggenerated
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return lottery.parent_id: 开奖号码源彩种
     *
     * @mbggenerated
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 字段: lottery.parent_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 开奖号码源彩种
     *
     * @mbggenerated
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return lottery.startlotto_table: 开奖号码表
     *
     * @mbggenerated
     */
    public String getStartlottoTable() {
        return startlottoTable;
    }

    /**
     * 字段: lottery.startlotto_table<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 开奖号码表
     *
     * @mbggenerated
     */
    public void setStartlottoTable(String startlottoTable) {
        this.startlottoTable = startlottoTable;
    }

    /**
     * @return lottery.startlotto_times: 每天/年开奖期数
     *
     * @mbggenerated
     */
    public Integer getStartlottoTimes() {
        return startlottoTimes;
    }

    /**
     * 字段: lottery.startlotto_times<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 每天/年开奖期数
     *
     * @mbggenerated
     */
    public void setStartlottoTimes(Integer startlottoTimes) {
        this.startlottoTimes = startlottoTimes;
    }

    /**
     * @return lottery.clearing_tag: 清算标识
     *
     * @mbggenerated
     */
    public String getClearingTag() {
        return clearingTag;
    }

    /**
     * 字段: lottery.clearing_tag<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 清算标识
     *
     * @mbggenerated
     */
    public void setClearingTag(String clearingTag) {
        this.clearingTag = clearingTag;
    }

    /**
     * @return lottery.cache_tag: 缓存标识
     *
     * @mbggenerated
     */
    public String getCacheTag() {
        return cacheTag;
    }

    /**
     * 字段: lottery.cache_tag<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 缓存标识
     *
     * @mbggenerated
     */
    public void setCacheTag(String cacheTag) {
        this.cacheTag = cacheTag;
    }

    /**
     * @return lottery.max_odds: 最大赔率
     *
     * @mbggenerated
     */
    public Double getMaxOdds() {
        return maxOdds;
    }

    /**
     * 字段: lottery.max_odds<br/>
     * 必填: true<br/>
     * 缺省: 0.00<br/>
     * 长度: 10<br/>
     * 说明: 最大赔率
     *
     * @mbggenerated
     */
    public void setMaxOdds(Double maxOdds) {
        this.maxOdds = maxOdds;
    }

    /**
     * @return lottery.min_odds: 最小赔率
     *
     * @mbggenerated
     */
    public Double getMinOdds() {
        return minOdds;
    }

    /**
     * 字段: lottery.min_odds<br/>
     * 必填: true<br/>
     * 缺省: 0.00<br/>
     * 长度: 10<br/>
     * 说明: 最小赔率
     *
     * @mbggenerated
     */
    public void setMinOdds(Double minOdds) {
        this.minOdds = minOdds;
    }

    /**
     * @return lottery.sort: 排序
     *
     * @mbggenerated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 字段: lottery.sort<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 排序
     *
     * @mbggenerated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return lottery.push_source: 推送号码来源
     *
     * @mbggenerated
     */
    public String getPushSource() {
        return pushSource;
    }

    /**
     * 字段: lottery.push_source<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 推送号码来源
     *
     * @mbggenerated
     */
    public void setPushSource(String pushSource) {
        this.pushSource = pushSource;
    }

    /**
     * @return lottery.push_status: 推送状态;0不推送;1,推送
     *
     * @mbggenerated
     */
    public Integer getPushStatus() {
        return pushStatus;
    }

    /**
     * 字段: lottery.push_status<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 推送状态;0不推送;1,推送
     *
     * @mbggenerated
     */
    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }

    /**
     * @return lottery.video_url: 开奖视频链接
     *
     * @mbggenerated
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 字段: lottery.video_url<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 开奖视频链接
     *
     * @mbggenerated
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * @return lottery.video_start: 视频打开方式
     *
     * @mbggenerated
     */
    public Integer getVideoStart() {
        return videoStart;
    }

    /**
     * 字段: lottery.video_start<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 视频打开方式
     *
     * @mbggenerated
     */
    public void setVideoStart(Integer videoStart) {
        this.videoStart = videoStart;
    }

    /**
     * @return lottery.is_work: 是否开售
     *
     * @mbggenerated
     */
    public Integer getIsWork() {
        return isWork;
    }

    /**
     * 字段: lottery.is_work<br/>
     * 必填: false<br/>
     * 缺省: 1<br/>
     * 长度: 10<br/>
     * 说明: 是否开售
     *
     * @mbggenerated
     */
    public void setIsWork(Integer isWork) {
        this.isWork = isWork;
    }

    /**
     * @return lottery.is_delete: 是否删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 字段: lottery.is_delete<br/>
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
     * @return lottery.lottery_id: 彩种编号
     *
     * @mbggenerated
     */
    public Integer getLotteryId() {
        return lotteryId;
    }

    /**
     * 字段: lottery.lottery_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 彩种编号
     *
     * @mbggenerated
     */
    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    /**
     * @return lottery.end_time: 封盘时间-app端使用
     *
     * @mbggenerated
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * 字段: lottery.end_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 封盘时间-app端使用
     *
     * @mbggenerated
     */
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    /**
     * @return lottery.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: lottery.create_time<br/>
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
     * @return lottery.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: lottery.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery
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
        Lottery other = (Lottery) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getStartlottoTable() == null ? other.getStartlottoTable() == null : this.getStartlottoTable().equals(other.getStartlottoTable()))
            && (this.getStartlottoTimes() == null ? other.getStartlottoTimes() == null : this.getStartlottoTimes().equals(other.getStartlottoTimes()))
            && (this.getClearingTag() == null ? other.getClearingTag() == null : this.getClearingTag().equals(other.getClearingTag()))
            && (this.getCacheTag() == null ? other.getCacheTag() == null : this.getCacheTag().equals(other.getCacheTag()))
            && (this.getMaxOdds() == null ? other.getMaxOdds() == null : this.getMaxOdds().equals(other.getMaxOdds()))
            && (this.getMinOdds() == null ? other.getMinOdds() == null : this.getMinOdds().equals(other.getMinOdds()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getPushSource() == null ? other.getPushSource() == null : this.getPushSource().equals(other.getPushSource()))
            && (this.getPushStatus() == null ? other.getPushStatus() == null : this.getPushStatus().equals(other.getPushStatus()))
            && (this.getVideoUrl() == null ? other.getVideoUrl() == null : this.getVideoUrl().equals(other.getVideoUrl()))
            && (this.getVideoStart() == null ? other.getVideoStart() == null : this.getVideoStart().equals(other.getVideoStart()))
            && (this.getIsWork() == null ? other.getIsWork() == null : this.getIsWork().equals(other.getIsWork()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getLotteryId() == null ? other.getLotteryId() == null : this.getLotteryId().equals(other.getLotteryId()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getStartlottoTable() == null) ? 0 : getStartlottoTable().hashCode());
        result = prime * result + ((getStartlottoTimes() == null) ? 0 : getStartlottoTimes().hashCode());
        result = prime * result + ((getClearingTag() == null) ? 0 : getClearingTag().hashCode());
        result = prime * result + ((getCacheTag() == null) ? 0 : getCacheTag().hashCode());
        result = prime * result + ((getMaxOdds() == null) ? 0 : getMaxOdds().hashCode());
        result = prime * result + ((getMinOdds() == null) ? 0 : getMinOdds().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getPushSource() == null) ? 0 : getPushSource().hashCode());
        result = prime * result + ((getPushStatus() == null) ? 0 : getPushStatus().hashCode());
        result = prime * result + ((getVideoUrl() == null) ? 0 : getVideoUrl().hashCode());
        result = prime * result + ((getVideoStart() == null) ? 0 : getVideoStart().hashCode());
        result = prime * result + ((getIsWork() == null) ? 0 : getIsWork().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getLotteryId() == null) ? 0 : getLotteryId().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery
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
        sb.append(", name=").append(name);
        sb.append(", icon=").append(icon);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", parentId=").append(parentId);
        sb.append(", startlottoTable=").append(startlottoTable);
        sb.append(", startlottoTimes=").append(startlottoTimes);
        sb.append(", clearingTag=").append(clearingTag);
        sb.append(", cacheTag=").append(cacheTag);
        sb.append(", maxOdds=").append(maxOdds);
        sb.append(", minOdds=").append(minOdds);
        sb.append(", sort=").append(sort);
        sb.append(", pushSource=").append(pushSource);
        sb.append(", pushStatus=").append(pushStatus);
        sb.append(", videoUrl=").append(videoUrl);
        sb.append(", videoStart=").append(videoStart);
        sb.append(", isWork=").append(isWork);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", lotteryId=").append(lotteryId);
        sb.append(", endTime=").append(endTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}