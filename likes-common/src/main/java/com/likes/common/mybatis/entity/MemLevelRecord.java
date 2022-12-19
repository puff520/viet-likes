package com.likes.common.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "`dz_level_record`")
public class MemLevelRecord {
    @Id
    @Column(name = "`level_record_id`")
    private Long levelRecordId;

    @Column(name = "`accno`")
    private String accno;


    @Column(name = "`user_name`")
    private String userName;

    @Column(name = "`head_name`")
    private String headName;

    @Column(name = "`superior_name`")
    private String superiorName;

    @Column(name = "`before_level`")
    private String beforeLevel;

    @Column(name = "`current_level`")
    private String currentLevel;

    @Column(name = "`change_amount`")
    private BigDecimal changeAmount;

    @Column(name = "`change_type`")
    private Integer changeType;

    @Column(name = "`create_time`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-5")
    private Date createTime;

    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * @return level_record_id
     */
    public Long getLevelRecordId() {
        return levelRecordId;
    }

    /**
     * @param levelRecordId
     */
    public void setLevelRecordId(Long levelRecordId) {
        this.levelRecordId = levelRecordId;
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
     * @return head_name
     */
    public String getHeadName() {
        return headName;
    }

    /**
     * @param headName
     */
    public void setHeadName(String headName) {
        this.headName = headName;
    }

    /**
     * @return superior_name
     */
    public String getSuperiorName() {
        return superiorName;
    }

    /**
     * @param superiorName
     */
    public void setSuperiorName(String superiorName) {
        this.superiorName = superiorName;
    }

    public String getBeforeLevel() {
        return beforeLevel;
    }

    public void setBeforeLevel(String beforeLevel) {
        this.beforeLevel = beforeLevel;
    }

    /**
     * @return current_level
     */
    public String getCurrentLevel() {
        return currentLevel;
    }

    /**
     * @param currentLevel
     */
    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * @return change_amount
     */
    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    /**
     * @param changeAmount
     */
    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    /**
     * @return change_type
     */
    public Integer getChangeType() {
        return changeType;
    }

    /**
     * @param changeType
     */
    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
