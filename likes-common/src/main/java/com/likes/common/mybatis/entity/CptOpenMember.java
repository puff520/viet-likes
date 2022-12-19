package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CptOpenMember implements Serializable {
    /**
     * 字段: cpt_open_member.id<br/>
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
     * 字段: cpt_open_member.user_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: CPT用户uid
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * 字段: cpt_open_member.username<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 第三方登入账号(随机生成)
     *
     * @mbggenerated
     */
    private String username;

    /**
     * 字段: cpt_open_member.password<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 第三方登入密码(随机生成)
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 字段: cpt_open_member.balance<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 第三方余额（元）
     *
     * @mbggenerated
     */
    private BigDecimal balance;

    /**
     * 字段: cpt_open_member.type<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 账号类型()
     *
     * @mbggenerated
     */
    private String type;

    /**
     * 字段: cpt_open_member.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: cpt_open_member.login_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 登入时间
     *
     * @mbggenerated
     */
    private Date loginTime;

    /**
     * 字段: cpt_open_member.layer_no<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 用户分层编号（代理扩展）
     *
     * @mbggenerated
     */
    private String layerNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cpt_open_member
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return cpt_open_member.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: cpt_open_member.id<br/>
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
     * @return cpt_open_member.user_id: CPT用户uid
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 字段: cpt_open_member.user_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: CPT用户uid
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return cpt_open_member.username: 第三方登入账号(随机生成)
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * 字段: cpt_open_member.username<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 第三方登入账号(随机生成)
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return cpt_open_member.password: 第三方登入密码(随机生成)
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * 字段: cpt_open_member.password<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 第三方登入密码(随机生成)
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return cpt_open_member.balance: 第三方余额（元）
     *
     * @mbggenerated
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 字段: cpt_open_member.balance<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 第三方余额（元）
     *
     * @mbggenerated
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @return cpt_open_member.type: 账号类型()
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * 字段: cpt_open_member.type<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 账号类型()
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return cpt_open_member.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: cpt_open_member.create_time<br/>
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
     * @return cpt_open_member.login_time: 登入时间
     *
     * @mbggenerated
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 字段: cpt_open_member.login_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 登入时间
     *
     * @mbggenerated
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * @return cpt_open_member.layer_no: 用户分层编号（代理扩展）
     *
     * @mbggenerated
     */
    public String getLayerNo() {
        return layerNo;
    }

    /**
     * 字段: cpt_open_member.layer_no<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 用户分层编号（代理扩展）
     *
     * @mbggenerated
     */
    public void setLayerNo(String layerNo) {
        this.layerNo = layerNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cpt_open_member
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
        CptOpenMember other = (CptOpenMember) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLoginTime() == null ? other.getLoginTime() == null : this.getLoginTime().equals(other.getLoginTime()))
            && (this.getLayerNo() == null ? other.getLayerNo() == null : this.getLayerNo().equals(other.getLayerNo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cpt_open_member
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLoginTime() == null) ? 0 : getLoginTime().hashCode());
        result = prime * result + ((getLayerNo() == null) ? 0 : getLayerNo().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cpt_open_member
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
        sb.append(", userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", balance=").append(balance);
        sb.append(", type=").append(type);
        sb.append(", createTime=").append(createTime);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", layerNo=").append(layerNo);
        sb.append("]");
        return sb.toString();
    }
}