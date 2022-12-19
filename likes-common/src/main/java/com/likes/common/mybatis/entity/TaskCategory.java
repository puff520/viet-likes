package com.likes.common.mybatis.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "`dz_task_category`")
public class TaskCategory {
    /**
     * id
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分类名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 分类说明
     */
    @Column(name = "`description`")
    private String description;

    @Column(name = "`en_name`")
    private String enName;

    @Column(name = "`en_description`")
    private String enDescription;

    @Column(name = "`sp_name`")
    private String spName;

    @Column(name = "`sp_description`")
    private String spDescription;

    @Column(name = "`ab_name`")
    private String abName;

    @Column(name = "`ab_description`")
    private String abDescription;

    @Column(name = "`fn_name`")
    private String fnName;

    @Column(name = "`fn_description`")
    private String fnDescription;

    @Column(name = "`in_name`")
    private String inName;

    @Column(name = "`in_description`")
    private String inDescription;

    /**
     * 排序字段
     */
    @Column(name = "`sort`")
    private Integer sort;

    /**
     * 图标
     */
    @Column(name = "`icon`")
    private String icon;

    /**
     * 0-关闭 1-开启
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 任务到期时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

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
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分类说明
     *
     * @return description - 分类说明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置分类说明
     *
     * @param description 分类说明
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return en_name
     */
    public String getEnName() {
        return enName;
    }

    /**
     * @param enName
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * @return en_description
     */
    public String getEnDescription() {
        return enDescription;
    }

    /**
     * @param enDescription
     */
    public void setEnDescription(String enDescription) {
        this.enDescription = enDescription;
    }

    /**
     * @return sp_name
     */
    public String getSpName() {
        return spName;
    }

    /**
     * @param spName
     */
    public void setSpName(String spName) {
        this.spName = spName;
    }

    /**
     * @return sp_description
     */
    public String getSpDescription() {
        return spDescription;
    }

    /**
     * @param spDescription
     */
    public void setSpDescription(String spDescription) {
        this.spDescription = spDescription;
    }

    /**
     * @return ab_name
     */
    public String getAbName() {
        return abName;
    }

    /**
     * @param abName
     */
    public void setAbName(String abName) {
        this.abName = abName;
    }

    /**
     * @return ab_description
     */
    public String getAbDescription() {
        return abDescription;
    }

    /**
     * @param abDescription
     */
    public void setAbDescription(String abDescription) {
        this.abDescription = abDescription;
    }

    /**
     * @return fn_name
     */
    public String getFnName() {
        return fnName;
    }

    /**
     * @param fnName
     */
    public void setFnName(String fnName) {
        this.fnName = fnName;
    }

    /**
     * @return fn_description
     */
    public String getFnDescription() {
        return fnDescription;
    }

    /**
     * @param fnDescription
     */
    public void setFnDescription(String fnDescription) {
        this.fnDescription = fnDescription;
    }

    /**
     * @return in_name
     */
    public String getInName() {
        return inName;
    }

    /**
     * @param inName
     */
    public void setInName(String inName) {
        this.inName = inName;
    }

    /**
     * @return in_description
     */
    public String getInDescription() {
        return inDescription;
    }

    /**
     * @param inDescription
     */
    public void setInDescription(String inDescription) {
        this.inDescription = inDescription;
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
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取0-关闭 1-开启
     *
     * @return status - 0-关闭 1-开启
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0-关闭 1-开启
     *
     * @param status 0-关闭 1-开启
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
     * 获取任务到期时间
     *
     * @return update_time - 任务到期时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置任务到期时间
     *
     * @param updateTime 任务到期时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return create_user
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
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
}
