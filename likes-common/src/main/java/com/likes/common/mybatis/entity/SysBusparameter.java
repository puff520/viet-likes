package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class SysBusparameter implements Serializable {
    /**
     * 字段: sys_busparameter.busparamid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 业务参数ID
     *
     * @mbggenerated
     */
    private Long busparamid;

    /**
     * 字段: sys_busparameter.busparamcode<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 业务参数代码
     *
     * @mbggenerated
     */
    private String busparamcode;

    /**
     * 字段: sys_busparameter.pbusparamcode<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 业务参数父代码
     *
     * @mbggenerated
     */
    private String pbusparamcode;

    /**
     * 字段: sys_busparameter.busparamname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 业务参数名称
     *
     * @mbggenerated
     */
    private String busparamname;

    /**
     * 字段: sys_busparameter.remark<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 参数说明
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 字段: sys_busparameter.status<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 系统参数启用状态0启用9未启用
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 字段: sys_busparameter.sortby<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 排序权重
     *
     * @mbggenerated
     */
    private Integer sortby;

    /**
     * 字段: sys_busparameter.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: sys_busparameter.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 字段: sys_busparameter.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: sys_busparameter.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * 字段: sys_busparameter.update_time<br/>
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
     * This field corresponds to the database table sys_busparameter
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return sys_busparameter.busparamid: 业务参数ID
     *
     * @mbggenerated
     */
    public Long getBusparamid() {
        return busparamid;
    }

    /**
     * 字段: sys_busparameter.busparamid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 业务参数ID
     *
     * @mbggenerated
     */
    public void setBusparamid(Long busparamid) {
        this.busparamid = busparamid;
    }

    /**
     * @return sys_busparameter.busparamcode: 业务参数代码
     *
     * @mbggenerated
     */
    public String getBusparamcode() {
        return busparamcode;
    }

    /**
     * 字段: sys_busparameter.busparamcode<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 业务参数代码
     *
     * @mbggenerated
     */
    public void setBusparamcode(String busparamcode) {
        this.busparamcode = busparamcode;
    }

    /**
     * @return sys_busparameter.pbusparamcode: 业务参数父代码
     *
     * @mbggenerated
     */
    public String getPbusparamcode() {
        return pbusparamcode;
    }

    /**
     * 字段: sys_busparameter.pbusparamcode<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 业务参数父代码
     *
     * @mbggenerated
     */
    public void setPbusparamcode(String pbusparamcode) {
        this.pbusparamcode = pbusparamcode;
    }

    /**
     * @return sys_busparameter.busparamname: 业务参数名称
     *
     * @mbggenerated
     */
    public String getBusparamname() {
        return busparamname;
    }

    /**
     * 字段: sys_busparameter.busparamname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 业务参数名称
     *
     * @mbggenerated
     */
    public void setBusparamname(String busparamname) {
        this.busparamname = busparamname;
    }

    /**
     * @return sys_busparameter.remark: 参数说明
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 字段: sys_busparameter.remark<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 参数说明
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return sys_busparameter.status: 系统参数启用状态0启用9未启用
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 字段: sys_busparameter.status<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 系统参数启用状态0启用9未启用
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return sys_busparameter.sortby: 排序权重
     *
     * @mbggenerated
     */
    public Integer getSortby() {
        return sortby;
    }

    /**
     * 字段: sys_busparameter.sortby<br/>
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
     * @return sys_busparameter.is_delete: 是否删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 字段: sys_busparameter.is_delete<br/>
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
     * @return sys_busparameter.create_user: 创建人
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 字段: sys_busparameter.create_user<br/>
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
     * @return sys_busparameter.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: sys_busparameter.create_time<br/>
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
     * @return sys_busparameter.update_user: 最后修改人
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 字段: sys_busparameter.update_user<br/>
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
     * @return sys_busparameter.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: sys_busparameter.update_time<br/>
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
     * This method corresponds to the database table sys_busparameter
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
        SysBusparameter other = (SysBusparameter) that;
        return (this.getBusparamid() == null ? other.getBusparamid() == null : this.getBusparamid().equals(other.getBusparamid()))
            && (this.getBusparamcode() == null ? other.getBusparamcode() == null : this.getBusparamcode().equals(other.getBusparamcode()))
            && (this.getPbusparamcode() == null ? other.getPbusparamcode() == null : this.getPbusparamcode().equals(other.getPbusparamcode()))
            && (this.getBusparamname() == null ? other.getBusparamname() == null : this.getBusparamname().equals(other.getBusparamname()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getSortby() == null ? other.getSortby() == null : this.getSortby().equals(other.getSortby()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_busparameter
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBusparamid() == null) ? 0 : getBusparamid().hashCode());
        result = prime * result + ((getBusparamcode() == null) ? 0 : getBusparamcode().hashCode());
        result = prime * result + ((getPbusparamcode() == null) ? 0 : getPbusparamcode().hashCode());
        result = prime * result + ((getBusparamname() == null) ? 0 : getBusparamname().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
     * This method corresponds to the database table sys_busparameter
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", busparamid=").append(busparamid);
        sb.append(", busparamcode=").append(busparamcode);
        sb.append(", pbusparamcode=").append(pbusparamcode);
        sb.append(", busparamname=").append(busparamname);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
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