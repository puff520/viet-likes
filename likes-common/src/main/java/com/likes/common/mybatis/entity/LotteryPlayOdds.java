package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class LotteryPlayOdds implements Serializable {
    /**
     * 字段: lottery_play_odds.id<br/>
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
     * 字段: lottery_play_odds.setting_id<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 玩法id
     *
     * @mbggenerated
     */
    private Integer settingId;

    /**
     * 字段: lottery_play_odds.name<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * 说明: 名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 字段: lottery_play_odds.total_count<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 总柱数
     *
     * @mbggenerated
     */
    private String totalCount;

    /**
     * 字段: lottery_play_odds.win_count<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 中奖柱数
     *
     * @mbggenerated
     */
    private String winCount;

    /**
     * 字段: lottery_play_odds.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: lottery_play_odds.easy_import_flag<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: Excel导入标识(新增或更新）
     *
     * @mbggenerated
     */
    private Integer easyImportFlag;

    /**
     * 字段: lottery_play_odds.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: lottery_play_odds.update_time<br/>
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
     * This field corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return lottery_play_odds.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: lottery_play_odds.id<br/>
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
     * @return lottery_play_odds.setting_id: 玩法id
     *
     * @mbggenerated
     */
    public Integer getSettingId() {
        return settingId;
    }

    /**
     * 字段: lottery_play_odds.setting_id<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 玩法id
     *
     * @mbggenerated
     */
    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    /**
     * @return lottery_play_odds.name: 名称
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 字段: lottery_play_odds.name<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * 说明: 名称
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return lottery_play_odds.total_count: 总柱数
     *
     * @mbggenerated
     */
    public String getTotalCount() {
        return totalCount;
    }

    /**
     * 字段: lottery_play_odds.total_count<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 总柱数
     *
     * @mbggenerated
     */
    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return lottery_play_odds.win_count: 中奖柱数
     *
     * @mbggenerated
     */
    public String getWinCount() {
        return winCount;
    }

    /**
     * 字段: lottery_play_odds.win_count<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 中奖柱数
     *
     * @mbggenerated
     */
    public void setWinCount(String winCount) {
        this.winCount = winCount;
    }

    /**
     * @return lottery_play_odds.is_delete: 是否删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 字段: lottery_play_odds.is_delete<br/>
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
     * @return lottery_play_odds.easy_import_flag: Excel导入标识(新增或更新）
     *
     * @mbggenerated
     */
    public Integer getEasyImportFlag() {
        return easyImportFlag;
    }

    /**
     * 字段: lottery_play_odds.easy_import_flag<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: Excel导入标识(新增或更新）
     *
     * @mbggenerated
     */
    public void setEasyImportFlag(Integer easyImportFlag) {
        this.easyImportFlag = easyImportFlag;
    }

    /**
     * @return lottery_play_odds.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: lottery_play_odds.create_time<br/>
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
     * @return lottery_play_odds.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: lottery_play_odds.update_time<br/>
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
     * This method corresponds to the database table lottery_play_odds
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
        LotteryPlayOdds other = (LotteryPlayOdds) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSettingId() == null ? other.getSettingId() == null : this.getSettingId().equals(other.getSettingId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTotalCount() == null ? other.getTotalCount() == null : this.getTotalCount().equals(other.getTotalCount()))
            && (this.getWinCount() == null ? other.getWinCount() == null : this.getWinCount().equals(other.getWinCount()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getEasyImportFlag() == null ? other.getEasyImportFlag() == null : this.getEasyImportFlag().equals(other.getEasyImportFlag()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSettingId() == null) ? 0 : getSettingId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTotalCount() == null) ? 0 : getTotalCount().hashCode());
        result = prime * result + ((getWinCount() == null) ? 0 : getWinCount().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getEasyImportFlag() == null) ? 0 : getEasyImportFlag().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
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
        sb.append(", settingId=").append(settingId);
        sb.append(", name=").append(name);
        sb.append(", totalCount=").append(totalCount);
        sb.append(", winCount=").append(winCount);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", easyImportFlag=").append(easyImportFlag);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}