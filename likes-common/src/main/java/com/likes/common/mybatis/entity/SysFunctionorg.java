package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class SysFunctionorg implements Serializable {
    /**
     * 字段: sys_functionorg.sfunid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 功能id
     *
     * @mbggenerated
     */
    private Long sfunid;

    /**
     * 字段: sys_functionorg.parsfunid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 父功能id
     *
     * @mbggenerated
     */
    private Long parsfunid;

    /**
     * 字段: sys_functionorg.ofsystem<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 所属系统  live-manage运营管理后台
     *
     * @mbggenerated
     */
    private String ofsystem;

    /**
     * 字段: sys_functionorg.sfuntype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 功能类别  menu菜单   button按钮   tabTAB
     *
     * @mbggenerated
     */
    private String sfuntype;

    /**
     * 字段: sys_functionorg.sfunname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 功能名称
     *
     * @mbggenerated
     */
    private String sfunname;

    /**
     * 字段: sys_functionorg.sfunstatus<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 功能状态 0正常   9停用
     *
     * @mbggenerated
     */
    private Integer sfunstatus;

    /**
     * 字段: sys_functionorg.sfunurl<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 56<br/>
     * 说明: 功能url或参数
     *
     * @mbggenerated
     */
    private String sfunurl;

    /**
     * 字段: sys_functionorg.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: sys_functionorg.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 字段: sys_functionorg.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: sys_functionorg.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * 字段: sys_functionorg.update_time<br/>
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
     * This field corresponds to the database table sys_functionorg
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return sys_functionorg.sfunid: 功能id
     *
     * @mbggenerated
     */
    public Long getSfunid() {
        return sfunid;
    }

    /**
     * 字段: sys_functionorg.sfunid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 功能id
     *
     * @mbggenerated
     */
    public void setSfunid(Long sfunid) {
        this.sfunid = sfunid;
    }

    /**
     * @return sys_functionorg.parsfunid: 父功能id
     *
     * @mbggenerated
     */
    public Long getParsfunid() {
        return parsfunid;
    }

    /**
     * 字段: sys_functionorg.parsfunid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 父功能id
     *
     * @mbggenerated
     */
    public void setParsfunid(Long parsfunid) {
        this.parsfunid = parsfunid;
    }

    /**
     * @return sys_functionorg.ofsystem: 所属系统  live-manage运营管理后台
     *
     * @mbggenerated
     */
    public String getOfsystem() {
        return ofsystem;
    }

    /**
     * 字段: sys_functionorg.ofsystem<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 所属系统  live-manage运营管理后台
     *
     * @mbggenerated
     */
    public void setOfsystem(String ofsystem) {
        this.ofsystem = ofsystem;
    }

    /**
     * @return sys_functionorg.sfuntype: 功能类别  menu菜单   button按钮   tabTAB
     *
     * @mbggenerated
     */
    public String getSfuntype() {
        return sfuntype;
    }

    /**
     * 字段: sys_functionorg.sfuntype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 功能类别  menu菜单   button按钮   tabTAB
     *
     * @mbggenerated
     */
    public void setSfuntype(String sfuntype) {
        this.sfuntype = sfuntype;
    }

    /**
     * @return sys_functionorg.sfunname: 功能名称
     *
     * @mbggenerated
     */
    public String getSfunname() {
        return sfunname;
    }

    /**
     * 字段: sys_functionorg.sfunname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 功能名称
     *
     * @mbggenerated
     */
    public void setSfunname(String sfunname) {
        this.sfunname = sfunname;
    }

    /**
     * @return sys_functionorg.sfunstatus: 功能状态 0正常   9停用
     *
     * @mbggenerated
     */
    public Integer getSfunstatus() {
        return sfunstatus;
    }

    /**
     * 字段: sys_functionorg.sfunstatus<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 功能状态 0正常   9停用
     *
     * @mbggenerated
     */
    public void setSfunstatus(Integer sfunstatus) {
        this.sfunstatus = sfunstatus;
    }

    /**
     * @return sys_functionorg.sfunurl: 功能url或参数
     *
     * @mbggenerated
     */
    public String getSfunurl() {
        return sfunurl;
    }

    /**
     * 字段: sys_functionorg.sfunurl<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 56<br/>
     * 说明: 功能url或参数
     *
     * @mbggenerated
     */
    public void setSfunurl(String sfunurl) {
        this.sfunurl = sfunurl;
    }

    /**
     * @return sys_functionorg.is_delete: 是否删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 字段: sys_functionorg.is_delete<br/>
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
     * @return sys_functionorg.create_user: 创建人
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 字段: sys_functionorg.create_user<br/>
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
     * @return sys_functionorg.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: sys_functionorg.create_time<br/>
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
     * @return sys_functionorg.update_user: 最后修改人
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 字段: sys_functionorg.update_user<br/>
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
     * @return sys_functionorg.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: sys_functionorg.update_time<br/>
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
     * This method corresponds to the database table sys_functionorg
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
        SysFunctionorg other = (SysFunctionorg) that;
        return (this.getSfunid() == null ? other.getSfunid() == null : this.getSfunid().equals(other.getSfunid()))
            && (this.getParsfunid() == null ? other.getParsfunid() == null : this.getParsfunid().equals(other.getParsfunid()))
            && (this.getOfsystem() == null ? other.getOfsystem() == null : this.getOfsystem().equals(other.getOfsystem()))
            && (this.getSfuntype() == null ? other.getSfuntype() == null : this.getSfuntype().equals(other.getSfuntype()))
            && (this.getSfunname() == null ? other.getSfunname() == null : this.getSfunname().equals(other.getSfunname()))
            && (this.getSfunstatus() == null ? other.getSfunstatus() == null : this.getSfunstatus().equals(other.getSfunstatus()))
            && (this.getSfunurl() == null ? other.getSfunurl() == null : this.getSfunurl().equals(other.getSfunurl()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_functionorg
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSfunid() == null) ? 0 : getSfunid().hashCode());
        result = prime * result + ((getParsfunid() == null) ? 0 : getParsfunid().hashCode());
        result = prime * result + ((getOfsystem() == null) ? 0 : getOfsystem().hashCode());
        result = prime * result + ((getSfuntype() == null) ? 0 : getSfuntype().hashCode());
        result = prime * result + ((getSfunname() == null) ? 0 : getSfunname().hashCode());
        result = prime * result + ((getSfunstatus() == null) ? 0 : getSfunstatus().hashCode());
        result = prime * result + ((getSfunurl() == null) ? 0 : getSfunurl().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_functionorg
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sfunid=").append(sfunid);
        sb.append(", parsfunid=").append(parsfunid);
        sb.append(", ofsystem=").append(ofsystem);
        sb.append(", sfuntype=").append(sfuntype);
        sb.append(", sfunname=").append(sfunname);
        sb.append(", sfunstatus=").append(sfunstatus);
        sb.append(", sfunurl=").append(sfunurl);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}