package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class UserInviteCode implements Serializable {
    /**
     * 字段: user_invite_code.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 主键
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 字段: user_invite_code.code<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 6<br/>
     * 说明: 唯一随机码
     *
     * @mbggenerated
     */
    private String code;

    /**
     * 字段: user_invite_code.status<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 状态（0：未使用；1：已使用；2：使用中，一般为预加载到redis中）即程序只能使用状态为0的随机码，且状态为2的使用完后要更新为1
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 字段: user_invite_code.created_at<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createdAt;

    /**
     * 字段: user_invite_code.update_at<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     *
     * @mbggenerated
     */
    private Date updateAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return user_invite_code.id: 主键
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: user_invite_code.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 主键
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user_invite_code.code: 唯一随机码
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * 字段: user_invite_code.code<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 6<br/>
     * 说明: 唯一随机码
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return user_invite_code.status: 状态（0：未使用；1：已使用；2：使用中，一般为预加载到redis中）即程序只能使用状态为0的随机码，且状态为2的使用完后要更新为1
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 字段: user_invite_code.status<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 状态（0：未使用；1：已使用；2：使用中，一般为预加载到redis中）即程序只能使用状态为0的随机码，且状态为2的使用完后要更新为1
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return user_invite_code.created_at: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 字段: user_invite_code.created_at<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return user_invite_code.update_at: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * 字段: user_invite_code.update_at<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     *
     * @mbggenerated
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
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
        UserInviteCode other = (UserInviteCode) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdateAt() == null ? other.getUpdateAt() == null : this.getUpdateAt().equals(other.getUpdateAt()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdateAt() == null) ? 0 : getUpdateAt().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
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
        sb.append(", code=").append(code);
        sb.append(", status=").append(status);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updateAt=").append(updateAt);
        sb.append("]");
        return sb.toString();
    }
}