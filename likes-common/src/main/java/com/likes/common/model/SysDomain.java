package com.likes.common.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "`sys_domain`")
public class SysDomain {
    /**
     * id
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 域名名称
     */
    @Column(name = "`domain_name`")
    private String domainName;

    /**
     * 域名类型： 1：pc域名 2：手机域名
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * CDN CNAME
     */
    @Column(name = "`invitation_code`")
    private String invitationCode;

    /**
     * 备注说明
     */
    @Column(name = "`note`")
    private String note;

    /**
     * 系统参数启用状态0启用9未启用
     */
    @Column(name = "`status`")
    private Short status;

    /**
     * 是否删除
     */
    @Column(name = "`is_delete`")
    private Boolean isDelete;

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

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * 获取域名类型： 1：pc域名 2：手机域名
     *
     * @return type - 域名类型： 1：pc域名 2：手机域名
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置域名类型： 1：pc域名 2：手机域名
     *
     * @param type 域名类型： 1：pc域名 2：手机域名
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取CDN CNAME
     *
     * @return invitation_code - CDN CNAME
     */
    public String getInvitationCode() {
        return invitationCode;
    }

    /**
     * 设置CDN CNAME
     *
     * @param invitationCode CDN CNAME
     */
    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    /**
     * 获取备注说明
     *
     * @return note - 备注说明
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置备注说明
     *
     * @param note 备注说明
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取系统参数启用状态0启用9未启用
     *
     * @return status - 系统参数启用状态0启用9未启用
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置系统参数启用状态0启用9未启用
     *
     * @param status 系统参数启用状态0启用9未启用
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取是否删除
     *
     * @return is_delete - 是否删除
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除
     *
     * @param isDelete 是否删除
     */
    public void setIsDelete(Boolean isDelete) {
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
