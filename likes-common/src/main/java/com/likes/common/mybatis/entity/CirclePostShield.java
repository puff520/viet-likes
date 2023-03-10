package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class CirclePostShield implements Serializable {
    /**
     * 字段: circle_post_shield.id<br/>
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
     * 字段: circle_post_shield.from_uid<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Integer fromUid;

    /**
     * 字段: circle_post_shield.to_uid<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Integer toUid;

    /**
     * 字段: circle_post_shield.on_off<br/>
     * 必填: false<br/>
     * 缺省: 1<br/>
     * 长度: 10<br/>
     * 说明: 开关状态：1打开2关闭
     *
     * @mbggenerated
     */
    private Integer onOff;

    /**
     * 字段: circle_post_shield.update_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table circle_post_shield
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return circle_post_shield.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: circle_post_shield.id<br/>
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
     * @return circle_post_shield.from_uid: 
     *
     * @mbggenerated
     */
    public Integer getFromUid() {
        return fromUid;
    }

    /**
     * 字段: circle_post_shield.from_uid<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    /**
     * @return circle_post_shield.to_uid: 
     *
     * @mbggenerated
     */
    public Integer getToUid() {
        return toUid;
    }

    /**
     * 字段: circle_post_shield.to_uid<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    /**
     * @return circle_post_shield.on_off: 开关状态：1打开2关闭
     *
     * @mbggenerated
     */
    public Integer getOnOff() {
        return onOff;
    }

    /**
     * 字段: circle_post_shield.on_off<br/>
     * 必填: false<br/>
     * 缺省: 1<br/>
     * 长度: 10<br/>
     * 说明: 开关状态：1打开2关闭
     *
     * @mbggenerated
     */
    public void setOnOff(Integer onOff) {
        this.onOff = onOff;
    }

    /**
     * @return circle_post_shield.update_time: 
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: circle_post_shield.update_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_post_shield
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
        CirclePostShield other = (CirclePostShield) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFromUid() == null ? other.getFromUid() == null : this.getFromUid().equals(other.getFromUid()))
            && (this.getToUid() == null ? other.getToUid() == null : this.getToUid().equals(other.getToUid()))
            && (this.getOnOff() == null ? other.getOnOff() == null : this.getOnOff().equals(other.getOnOff()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_post_shield
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFromUid() == null) ? 0 : getFromUid().hashCode());
        result = prime * result + ((getToUid() == null) ? 0 : getToUid().hashCode());
        result = prime * result + ((getOnOff() == null) ? 0 : getOnOff().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_post_shield
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
        sb.append(", fromUid=").append(fromUid);
        sb.append(", toUid=").append(toUid);
        sb.append(", onOff=").append(onOff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}