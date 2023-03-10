package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class SysTags implements Serializable {
    /**
     * 字段: sys_tags.tagid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 标签id
     *
     * @mbggenerated
     */
    private Long tagid;

    /**
     * 字段: sys_tags.tagname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 标签名称
     *
     * @mbggenerated
     */
    private String tagname;

    /**
     * 字段: sys_tags.tagtype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 标签分类 1系统标签 2图文主题
     *
     * @mbggenerated
     */
    private Integer tagtype;

    /**
     * 字段: sys_tags.sortby<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 排序权重
     *
     * @mbggenerated
     */
    private Integer sortby;

    /**
     * 字段: sys_tags.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: sys_tags.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 字段: sys_tags.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: sys_tags.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * 字段: sys_tags.update_time<br/>
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
     * This field corresponds to the database table sys_tags
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return sys_tags.tagid: 标签id
     *
     * @mbggenerated
     */
    public Long getTagid() {
        return tagid;
    }

    /**
     * 字段: sys_tags.tagid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 标签id
     *
     * @mbggenerated
     */
    public void setTagid(Long tagid) {
        this.tagid = tagid;
    }

    /**
     * @return sys_tags.tagname: 标签名称
     *
     * @mbggenerated
     */
    public String getTagname() {
        return tagname;
    }

    /**
     * 字段: sys_tags.tagname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 标签名称
     *
     * @mbggenerated
     */
    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    /**
     * @return sys_tags.tagtype: 标签分类 1系统标签 2图文主题
     *
     * @mbggenerated
     */
    public Integer getTagtype() {
        return tagtype;
    }

    /**
     * 字段: sys_tags.tagtype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 标签分类 1系统标签 2图文主题
     *
     * @mbggenerated
     */
    public void setTagtype(Integer tagtype) {
        this.tagtype = tagtype;
    }

    /**
     * @return sys_tags.sortby: 排序权重
     *
     * @mbggenerated
     */
    public Integer getSortby() {
        return sortby;
    }

    /**
     * 字段: sys_tags.sortby<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 排序权重
     *
     * @mbggenerated
     */
    public void setSortby(Integer sortby) {
        this.sortby = sortby;
    }

    /**
     * @return sys_tags.is_delete: 是否删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 字段: sys_tags.is_delete<br/>
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
     * @return sys_tags.create_user: 创建人
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 字段: sys_tags.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return sys_tags.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: sys_tags.create_time<br/>
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
     * @return sys_tags.update_user: 最后修改人
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 字段: sys_tags.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return sys_tags.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: sys_tags.update_time<br/>
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
     * This method corresponds to the database table sys_tags
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
        SysTags other = (SysTags) that;
        return (this.getTagid() == null ? other.getTagid() == null : this.getTagid().equals(other.getTagid()))
            && (this.getTagname() == null ? other.getTagname() == null : this.getTagname().equals(other.getTagname()))
            && (this.getTagtype() == null ? other.getTagtype() == null : this.getTagtype().equals(other.getTagtype()))
            && (this.getSortby() == null ? other.getSortby() == null : this.getSortby().equals(other.getSortby()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tags
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTagid() == null) ? 0 : getTagid().hashCode());
        result = prime * result + ((getTagname() == null) ? 0 : getTagname().hashCode());
        result = prime * result + ((getTagtype() == null) ? 0 : getTagtype().hashCode());
        result = prime * result + ((getSortby() == null) ? 0 : getSortby().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tags
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagid=").append(tagid);
        sb.append(", tagname=").append(tagname);
        sb.append(", tagtype=").append(tagtype);
        sb.append(", sortby=").append(sortby);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}