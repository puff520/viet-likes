package com.likes.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`mem_related_bind`")
public class MemRelatedBind {
    /**
     * 绑定id
     */
    @Id
    @Column(name = "`bind_id`")
    private Integer bindId;

    /**
     * 任务分类id
     */
    @Column(name = "`category_id`")
    private Integer categoryId;

    /**
     * 用户标识
     */
    @Column(name = "`mem_accno`")
    private String memAccno;

    /**
     * 创建人
     */
    @Column(name = "`create_user`")
    private String createUser;

    /**
     * 更新人
     */
    @Column(name = "`update_user`")
    private String updateUser;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;


    /**
     * 更新时间
     */
    @Column(name = "`category_no`")
    private String categoryNo;


    /**
     * 获取绑定id
     *
     * @return bind_id - 绑定id
     */
    public Integer getBindId() {
        return bindId;
    }

    /**
     * 设置绑定id
     *
     * @param bindId 绑定id
     */
    public void setBindId(Integer bindId) {
        this.bindId = bindId;
    }

    /**
     * 获取任务分类id
     *
     * @return category_id - 任务分类id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置任务分类id
     *
     * @param categoryId 任务分类id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取用户标识
     *
     * @return mem_accno - 用户标识
     */
    public String getMemAccno() {
        return memAccno;
    }

    /**
     * 设置用户标识
     *
     * @param memAccno 用户标识
     */
    public void setMemAccno(String memAccno) {
        this.memAccno = memAccno;
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

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }
}