package com.likes.common.mybatis.entity;

import java.io.Serializable;

public class InstationMessage implements Serializable {
    /**
     * 字段: instation_message.id<br/>
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
     * 字段: instation_message.title<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 标题
     *
     * @mbggenerated
     */
    private String title;

    /**
     * 字段: instation_message.describe<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 描述
     *
     * @mbggenerated
     */
    private String describe;

    /**
     * 字段: instation_message.message<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 内容
     *
     * @mbggenerated
     */
    private String message;

    /**
     * 字段: instation_message.create_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 发布时间
     *
     * @mbggenerated
     */
    private Long createTime;

    /**
     * 字段: instation_message.account<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 操作员账号
     *
     * @mbggenerated
     */
    private String account;

    /**
     * 字段: instation_message.deleted<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Integer deleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table instation_message
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return instation_message.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: instation_message.id<br/>
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
     * @return instation_message.title: 标题
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * 字段: instation_message.title<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 标题
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return instation_message.describe: 描述
     *
     * @mbggenerated
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 字段: instation_message.describe<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 描述
     *
     * @mbggenerated
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * @return instation_message.message: 内容
     *
     * @mbggenerated
     */
    public String getMessage() {
        return message;
    }

    /**
     * 字段: instation_message.message<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 内容
     *
     * @mbggenerated
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return instation_message.create_time: 发布时间
     *
     * @mbggenerated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 字段: instation_message.create_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 发布时间
     *
     * @mbggenerated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * @return instation_message.account: 操作员账号
     *
     * @mbggenerated
     */
    public String getAccount() {
        return account;
    }

    /**
     * 字段: instation_message.account<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 操作员账号
     *
     * @mbggenerated
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return instation_message.deleted: 
     *
     * @mbggenerated
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 字段: instation_message.deleted<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instation_message
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
        InstationMessage other = (InstationMessage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getDescribe() == null ? other.getDescribe() == null : this.getDescribe().equals(other.getDescribe()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instation_message
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getDescribe() == null) ? 0 : getDescribe().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table instation_message
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
        sb.append(", title=").append(title);
        sb.append(", describe=").append(describe);
        sb.append(", message=").append(message);
        sb.append(", createTime=").append(createTime);
        sb.append(", account=").append(account);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }
}