package com.likes.common.mybatis.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`inf_sysnotice`")
public class InfSysnotice {
    /**
     * 公告id
     */
    @Id
    @Column(name = "`noticeid`")
    private Long noticeid;

    /**
     * 消息类型 1直播间滚动消息 2弹窗公告 3首页公告 4短视讯公告
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 会员标识号
     */
    @Column(name = "`accno`")
    private String accno;

    /**
     * 消息标题
     */
    @Column(name = "`title`")
    private String title;

    /**
     * 消息内容
     */
    @Column(name = "`notebody`")
    private String notebody;

    @Column(name = "`en_notice`")
    private String enNotice;

    @Column(name = "`sp_notice`")
    private String spNotice;

    @Column(name = "`ab_notice`")
    private String abNotice;

    @Column(name = "`fn_notice`")
    private String fnNotice;

    /**
     * 消息参数
     */
    @Column(name = "`params`")
    private String params;

    /**
     * 有效期起
     */
    @Column(name = "`expirydates`")
    private Date expirydates;

    /**
     * 有效期止
     */
    @Column(name = "`expirydatee`")
    private Date expirydatee;

    /**
     * 排序权重
     */
    @Column(name = "`sortby`")
    private Integer sortby;

    /**
     * 0 关闭  1 开启
     */
    @Column(name = "`work_status`")
    private Boolean workStatus;

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
     * 获取公告id
     *
     * @return noticeid - 公告id
     */
    public Long getNoticeid() {
        return noticeid;
    }

    /**
     * 设置公告id
     *
     * @param noticeid 公告id
     */
    public void setNoticeid(Long noticeid) {
        this.noticeid = noticeid;
    }

    /**
     * 获取消息类型 1直播间滚动消息 2弹窗公告 3首页公告 4短视讯公告
     *
     * @return type - 消息类型 1直播间滚动消息 2弹窗公告 3首页公告 4短视讯公告
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置消息类型 1直播间滚动消息 2弹窗公告 3首页公告 4短视讯公告
     *
     * @param type 消息类型 1直播间滚动消息 2弹窗公告 3首页公告 4短视讯公告
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取会员标识号
     *
     * @return accno - 会员标识号
     */
    public String getAccno() {
        return accno;
    }

    /**
     * 设置会员标识号
     *
     * @param accno 会员标识号
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * 获取消息标题
     *
     * @return title - 消息标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置消息标题
     *
     * @param title 消息标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取消息内容
     *
     * @return notebody - 消息内容
     */
    public String getNotebody() {
        return notebody;
    }

    /**
     * 设置消息内容
     *
     * @param notebody 消息内容
     */
    public void setNotebody(String notebody) {
        this.notebody = notebody;
    }

    public String getEnNotice() {
        return enNotice;
    }

    public void setEnNotice(String enNotice) {
        this.enNotice = enNotice;
    }

    public String getSpNotice() {
        return spNotice;
    }

    public void setSpNotice(String spNotice) {
        this.spNotice = spNotice;
    }

    public String getAbNotice() {
        return abNotice;
    }

    public void setAbNotice(String abNotice) {
        this.abNotice = abNotice;
    }

    public String getFnNotice() {
        return fnNotice;
    }

    public void setFnNotice(String fnNotice) {
        this.fnNotice = fnNotice;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    /**
     * 获取消息参数
     *
     * @return params - 消息参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置消息参数
     *
     * @param params 消息参数
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 获取有效期起
     *
     * @return expirydates - 有效期起
     */
    public Date getExpirydates() {
        return expirydates;
    }

    /**
     * 设置有效期起
     *
     * @param expirydates 有效期起
     */
    public void setExpirydates(Date expirydates) {
        this.expirydates = expirydates;
    }

    /**
     * 获取有效期止
     *
     * @return expirydatee - 有效期止
     */
    public Date getExpirydatee() {
        return expirydatee;
    }

    /**
     * 设置有效期止
     *
     * @param expirydatee 有效期止
     */
    public void setExpirydatee(Date expirydatee) {
        this.expirydatee = expirydatee;
    }

    /**
     * 获取排序权重
     *
     * @return sortby - 排序权重
     */
    public Integer getSortby() {
        return sortby;
    }

    /**
     * 设置排序权重
     *
     * @param sortby 排序权重
     */
    public void setSortby(Integer sortby) {
        this.sortby = sortby;
    }

    /**
     * 获取0 关闭  1 开启
     *
     * @return work_status - 0 关闭  1 开启
     */
    public Boolean getWorkStatus() {
        return workStatus;
    }

    /**
     * 设置0 关闭  1 开启
     *
     * @param workStatus 0 关闭  1 开启
     */
    public void setWorkStatus(Boolean workStatus) {
        this.workStatus = workStatus;
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
