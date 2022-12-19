package com.likes.common.mybatis.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "`mem_level_config`")
public class MemLevelConfig {
    /**
     * 等级id
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 当前等级
     */
    @Column(name = "`level`")
    private String level;

    /**
     * 等级编号
     */
    @Column(name = "`level_seq`")
    private Integer levelSeq;

    /**
     * 等级图标地址
     */
    @Column(name = "`level_icon_url`")
    private String levelIconUrl;

    @Column(name = "`image_url`")
    private String imageUrl;

    /**
     * 所需购买金额
     */
    @Column(name = "`recharge_amount`")
    private BigDecimal rechargeAmount;

    /**
     * 推广获得金额
     */
    @Column(name = "`promote_amount`")
    private BigDecimal promoteAmount;

    /**
     * 任务次数
     */
    @Column(name = "`do_task_times`")
    private Integer doTaskTimes;

    /**
     * 有效时长 按月 如 12
     */
    @Column(name = "`expire_time`")
    private Integer expireTime;

    /**
     * 是否删除
     */
    @Column(name = "`is_delete`")
    private Integer isDelete;

    /**
     * 创建人
     */
    @Column(name = "`create_user`")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 最后修改人
     */
    @Column(name = "`update_user`")
    private String updateUser;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 获取等级id
     *
     * @return id - 等级id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置等级id
     *
     * @param id 等级id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取当前等级
     *
     * @return level - 当前等级
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置当前等级
     *
     * @param level 当前等级
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取等级编号
     *
     * @return level_seq - 等级编号
     */
    public Integer getLevelSeq() {
        return levelSeq;
    }

    /**
     * 设置等级编号
     *
     * @param levelSeq 等级编号
     */
    public void setLevelSeq(Integer levelSeq) {
        this.levelSeq = levelSeq;
    }

    /**
     * 获取等级图标地址
     *
     * @return level_icon_url - 等级图标地址
     */
    public String getLevelIconUrl() {
        return levelIconUrl;
    }

    /**
     * 设置等级图标地址
     *
     * @param levelIconUrl 等级图标地址
     */
    public void setLevelIconUrl(String levelIconUrl) {
        this.levelIconUrl = levelIconUrl;
    }

    /**
     * @return image_url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取所需购买金额
     *
     * @return recharge_amount - 所需购买金额
     */
    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    /**
     * 设置所需购买金额
     *
     * @param rechargeAmount 所需购买金额
     */
    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    /**
     * 获取推广获得金额
     *
     * @return promote_amount - 推广获得金额
     */
    public BigDecimal getPromoteAmount() {
        return promoteAmount;
    }

    /**
     * 设置推广获得金额
     *
     * @param promoteAmount 推广获得金额
     */
    public void setPromoteAmount(BigDecimal promoteAmount) {
        this.promoteAmount = promoteAmount;
    }

    /**
     * 获取任务次数
     *
     * @return do_task_times - 任务次数
     */
    public Integer getDoTaskTimes() {
        return doTaskTimes;
    }

    /**
     * 设置任务次数
     *
     * @param doTaskTimes 任务次数
     */
    public void setDoTaskTimes(Integer doTaskTimes) {
        this.doTaskTimes = doTaskTimes;
    }

    /**
     * 获取有效时长 按月 如 12
     *
     * @return expire_time - 有效时长 按月 如 12
     */
    public Integer getExpireTime() {
        return expireTime;
    }

    /**
     * 设置有效时长 按月 如 12
     *
     * @param expireTime 有效时长 按月 如 12
     */
    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
     * 获取最后修改人
     *
     * @return update_user - 最后修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置最后修改人
     *
     * @param updateUser 最后修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
