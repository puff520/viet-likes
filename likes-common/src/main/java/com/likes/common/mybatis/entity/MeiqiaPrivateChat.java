package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class MeiqiaPrivateChat implements Serializable {
    /**
     * 字段: meiqia_private_chat.id<br/>
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
     * 字段: meiqia_private_chat.head<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 头像
     *
     * @mbggenerated
     */
    private String head;

    /**
     * 字段: meiqia_private_chat.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: meiqia_private_chat.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * 字段: meiqia_private_chat.nick_name<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 昵称
     *
     * @mbggenerated
     */
    private String nickName;

    /**
     * 字段: meiqia_private_chat.mq_id<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 美恰Id
     *
     * @mbggenerated
     */
    private String mqId;

    /**
     * 字段: meiqia_private_chat.sort<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 排序
     *
     * @mbggenerated
     */
    private Integer sort;

    /**
     * 字段: meiqia_private_chat.work<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否启用
     *
     * @mbggenerated
     */
    private Integer work;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table meiqia_private_chat
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return meiqia_private_chat.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: meiqia_private_chat.id<br/>
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
     * @return meiqia_private_chat.head: 头像
     *
     * @mbggenerated
     */
    public String getHead() {
        return head;
    }

    /**
     * 字段: meiqia_private_chat.head<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 头像
     *
     * @mbggenerated
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * @return meiqia_private_chat.create_time: 
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: meiqia_private_chat.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return meiqia_private_chat.update_time: 
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: meiqia_private_chat.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return meiqia_private_chat.nick_name: 昵称
     *
     * @mbggenerated
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 字段: meiqia_private_chat.nick_name<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 昵称
     *
     * @mbggenerated
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return meiqia_private_chat.mq_id: 美恰Id
     *
     * @mbggenerated
     */
    public String getMqId() {
        return mqId;
    }

    /**
     * 字段: meiqia_private_chat.mq_id<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 美恰Id
     *
     * @mbggenerated
     */
    public void setMqId(String mqId) {
        this.mqId = mqId;
    }

    /**
     * @return meiqia_private_chat.sort: 排序
     *
     * @mbggenerated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 字段: meiqia_private_chat.sort<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 排序
     *
     * @mbggenerated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return meiqia_private_chat.work: 是否启用
     *
     * @mbggenerated
     */
    public Integer getWork() {
        return work;
    }

    /**
     * 字段: meiqia_private_chat.work<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否启用
     *
     * @mbggenerated
     */
    public void setWork(Integer work) {
        this.work = work;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meiqia_private_chat
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
        MeiqiaPrivateChat other = (MeiqiaPrivateChat) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getHead() == null ? other.getHead() == null : this.getHead().equals(other.getHead()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getMqId() == null ? other.getMqId() == null : this.getMqId().equals(other.getMqId()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getWork() == null ? other.getWork() == null : this.getWork().equals(other.getWork()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meiqia_private_chat
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getHead() == null) ? 0 : getHead().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getMqId() == null) ? 0 : getMqId().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getWork() == null) ? 0 : getWork().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meiqia_private_chat
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
        sb.append(", head=").append(head);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", nickName=").append(nickName);
        sb.append(", mqId=").append(mqId);
        sb.append(", sort=").append(sort);
        sb.append(", work=").append(work);
        sb.append("]");
        return sb.toString();
    }
}