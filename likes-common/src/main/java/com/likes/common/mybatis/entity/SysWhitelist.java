package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class SysWhitelist implements Serializable {
    /**
     * 字段: sys_whitelist.whiteid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 白名单id
     *
     * @mbggenerated
     */
    private Long whiteid;

    /**
     * 字段: sys_whitelist.syscode<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * 说明: 系统识别码code
     *
     * @mbggenerated
     */
    private String syscode;

    /**
     * 字段: sys_whitelist.sysname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 系统名称
     *
     * @mbggenerated
     */
    private String sysname;

    /**
     * 字段: sys_whitelist.ipaddress<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: ip地址
     *
     * @mbggenerated
     */
    private String ipaddress;

    /**
     * 字段: sys_whitelist.status<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 启用状态0启用9未启用
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 字段: sys_whitelist.remark<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 200<br/>
     * 说明: 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 字段: sys_whitelist.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: sys_whitelist.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 字段: sys_whitelist.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: sys_whitelist.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * 字段: sys_whitelist.update_time<br/>
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
     * This field corresponds to the database table sys_whitelist
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return sys_whitelist.whiteid: 白名单id
     *
     * @mbggenerated
     */
    public Long getWhiteid() {
        return whiteid;
    }

    /**
     * 字段: sys_whitelist.whiteid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 白名单id
     *
     * @mbggenerated
     */
    public void setWhiteid(Long whiteid) {
        this.whiteid = whiteid;
    }

    /**
     * @return sys_whitelist.syscode: 系统识别码code
     *
     * @mbggenerated
     */
    public String getSyscode() {
        return syscode;
    }

    /**
     * 字段: sys_whitelist.syscode<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * 说明: 系统识别码code
     *
     * @mbggenerated
     */
    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    /**
     * @return sys_whitelist.sysname: 系统名称
     *
     * @mbggenerated
     */
    public String getSysname() {
        return sysname;
    }

    /**
     * 字段: sys_whitelist.sysname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 系统名称
     *
     * @mbggenerated
     */
    public void setSysname(String sysname) {
        this.sysname = sysname;
    }

    /**
     * @return sys_whitelist.ipaddress: ip地址
     *
     * @mbggenerated
     */
    public String getIpaddress() {
        return ipaddress;
    }

    /**
     * 字段: sys_whitelist.ipaddress<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: ip地址
     *
     * @mbggenerated
     */
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    /**
     * @return sys_whitelist.status: 启用状态0启用9未启用
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 字段: sys_whitelist.status<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 启用状态0启用9未启用
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return sys_whitelist.remark: 备注
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 字段: sys_whitelist.remark<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 200<br/>
     * 说明: 备注
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return sys_whitelist.is_delete: 是否删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 字段: sys_whitelist.is_delete<br/>
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
     * @return sys_whitelist.create_user: 创建人
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 字段: sys_whitelist.create_user<br/>
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
     * @return sys_whitelist.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: sys_whitelist.create_time<br/>
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
     * @return sys_whitelist.update_user: 最后修改人
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 字段: sys_whitelist.update_user<br/>
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
     * @return sys_whitelist.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: sys_whitelist.update_time<br/>
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
     * This method corresponds to the database table sys_whitelist
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
        SysWhitelist other = (SysWhitelist) that;
        return (this.getWhiteid() == null ? other.getWhiteid() == null : this.getWhiteid().equals(other.getWhiteid()))
            && (this.getSyscode() == null ? other.getSyscode() == null : this.getSyscode().equals(other.getSyscode()))
            && (this.getSysname() == null ? other.getSysname() == null : this.getSysname().equals(other.getSysname()))
            && (this.getIpaddress() == null ? other.getIpaddress() == null : this.getIpaddress().equals(other.getIpaddress()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_whitelist
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWhiteid() == null) ? 0 : getWhiteid().hashCode());
        result = prime * result + ((getSyscode() == null) ? 0 : getSyscode().hashCode());
        result = prime * result + ((getSysname() == null) ? 0 : getSysname().hashCode());
        result = prime * result + ((getIpaddress() == null) ? 0 : getIpaddress().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_whitelist
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", whiteid=").append(whiteid);
        sb.append(", syscode=").append(syscode);
        sb.append(", sysname=").append(sysname);
        sb.append(", ipaddress=").append(ipaddress);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}