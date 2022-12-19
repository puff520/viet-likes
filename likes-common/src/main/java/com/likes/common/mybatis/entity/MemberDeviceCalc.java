package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class MemberDeviceCalc implements Serializable {
    /**
     * 字段: member_device_calc.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 字段: member_device_calc.total_count<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 总人数
     *
     * @mbggenerated
     */
    private Integer totalCount;

    /**
     * 字段: member_device_calc.android_count<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 安卓在线人数
     *
     * @mbggenerated
     */
    private Integer androidCount;

    /**
     * 字段: member_device_calc.ios_count<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: ios人数
     *
     * @mbggenerated
     */
    private Integer iosCount;

    /**
     * 字段: member_device_calc.h5_count<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: h5人数
     *
     * @mbggenerated
     */
    private Integer h5Count;

    /**
     * 字段: member_device_calc.web_count<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: pc人数
     *
     * @mbggenerated
     */
    private Integer webCount;

    /**
     * 字段: member_device_calc.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: member_device_calc.update_time<br/>
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
     * This field corresponds to the database table member_device_calc
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return member_device_calc.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: member_device_calc.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return member_device_calc.total_count: 总人数
     *
     * @mbggenerated
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * 字段: member_device_calc.total_count<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 总人数
     *
     * @mbggenerated
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return member_device_calc.android_count: 安卓在线人数
     *
     * @mbggenerated
     */
    public Integer getAndroidCount() {
        return androidCount;
    }

    /**
     * 字段: member_device_calc.android_count<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 安卓在线人数
     *
     * @mbggenerated
     */
    public void setAndroidCount(Integer androidCount) {
        this.androidCount = androidCount;
    }

    /**
     * @return member_device_calc.ios_count: ios人数
     *
     * @mbggenerated
     */
    public Integer getIosCount() {
        return iosCount;
    }

    /**
     * 字段: member_device_calc.ios_count<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: ios人数
     *
     * @mbggenerated
     */
    public void setIosCount(Integer iosCount) {
        this.iosCount = iosCount;
    }

    /**
     * @return member_device_calc.h5_count: h5人数
     *
     * @mbggenerated
     */
    public Integer getH5Count() {
        return h5Count;
    }

    /**
     * 字段: member_device_calc.h5_count<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: h5人数
     *
     * @mbggenerated
     */
    public void setH5Count(Integer h5Count) {
        this.h5Count = h5Count;
    }

    /**
     * @return member_device_calc.web_count: pc人数
     *
     * @mbggenerated
     */
    public Integer getWebCount() {
        return webCount;
    }

    /**
     * 字段: member_device_calc.web_count<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: pc人数
     *
     * @mbggenerated
     */
    public void setWebCount(Integer webCount) {
        this.webCount = webCount;
    }

    /**
     * @return member_device_calc.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: member_device_calc.create_time<br/>
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
     * @return member_device_calc.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: member_device_calc.update_time<br/>
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
     * This method corresponds to the database table member_device_calc
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
        MemberDeviceCalc other = (MemberDeviceCalc) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTotalCount() == null ? other.getTotalCount() == null : this.getTotalCount().equals(other.getTotalCount()))
            && (this.getAndroidCount() == null ? other.getAndroidCount() == null : this.getAndroidCount().equals(other.getAndroidCount()))
            && (this.getIosCount() == null ? other.getIosCount() == null : this.getIosCount().equals(other.getIosCount()))
            && (this.getH5Count() == null ? other.getH5Count() == null : this.getH5Count().equals(other.getH5Count()))
            && (this.getWebCount() == null ? other.getWebCount() == null : this.getWebCount().equals(other.getWebCount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_device_calc
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTotalCount() == null) ? 0 : getTotalCount().hashCode());
        result = prime * result + ((getAndroidCount() == null) ? 0 : getAndroidCount().hashCode());
        result = prime * result + ((getIosCount() == null) ? 0 : getIosCount().hashCode());
        result = prime * result + ((getH5Count() == null) ? 0 : getH5Count().hashCode());
        result = prime * result + ((getWebCount() == null) ? 0 : getWebCount().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_device_calc
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", totalCount=").append(totalCount);
        sb.append(", androidCount=").append(androidCount);
        sb.append(", iosCount=").append(iosCount);
        sb.append(", h5Count=").append(h5Count);
        sb.append(", webCount=").append(webCount);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}