//package com.likes.common.mybatis.entity;
//
//import java.io.Serializable;
//import java.util.Date;
//
//public class PayLevelOpLog implements Serializable {
//    /**
//     * 字段: pay_level_op_log.id<br/>
//     * 主键: 自动增长<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    private Integer id;
//
//    /**
//     * 字段: pay_level_op_log.type<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明: 操作类型（1：添加；2：修改；3：删除；4：手动设置用户层级）
//     *
//     * @mbggenerated
//     */
//    private Integer type;
//
//    /**
//     * 字段: pay_level_op_log.user_id<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明: 用户id
//     *
//     * @mbggenerated
//     */
//    private Integer userId;
//
//    /**
//     * 字段: pay_level_op_log.user<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 32<br/>
//     * 说明: 操作用户
//     *
//     * @mbggenerated
//     */
//    private String user;
//
//    /**
//     * 字段: pay_level_op_log.pay_level_id<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 19<br/>
//     * 说明: 支付层级id
//     *
//     * @mbggenerated
//     */
//    private Long payLevelId;
//
//    /**
//     * 字段: pay_level_op_log.content<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 操作描述
//     *
//     * @mbggenerated
//     */
//    private String content;
//
//    /**
//     * 字段: pay_level_op_log.last_time<br/>
//     * 必填: true<br/>
//     * 缺省: CURRENT_TIMESTAMP<br/>
//     * 长度: 19<br/>
//     * 说明: 最后操作时间
//     *
//     * @mbggenerated
//     */
//    private Date lastTime;
//
//    /**
//     * 字段: pay_level_op_log.ip<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 128<br/>
//     * 说明: 操作IP
//     *
//     * @mbggenerated
//     */
//    private String ip;
//
//    /**
//     * This field was generated by MyBatis Generator.
//     * This field corresponds to the database table pay_level_op_log
//     *
//     * @mbggenerated
//     */
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * @return pay_level_op_log.id:
//     *
//     * @mbggenerated
//     */
//    public Integer getId() {
//        return id;
//    }
//
//    /**
//     * 字段: pay_level_op_log.id<br/>
//     * 主键: 自动增长<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    /**
//     * @return pay_level_op_log.type: 操作类型（1：添加；2：修改；3：删除；4：手动设置用户层级）
//     *
//     * @mbggenerated
//     */
//    public Integer getType() {
//        return type;
//    }
//
//    /**
//     * 字段: pay_level_op_log.type<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明: 操作类型（1：添加；2：修改；3：删除；4：手动设置用户层级）
//     *
//     * @mbggenerated
//     */
//    public void setType(Integer type) {
//        this.type = type;
//    }
//
//    /**
//     * @return pay_level_op_log.user_id: 用户id
//     *
//     * @mbggenerated
//     */
//    public Integer getUserId() {
//        return userId;
//    }
//
//    /**
//     * 字段: pay_level_op_log.user_id<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明: 用户id
//     *
//     * @mbggenerated
//     */
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    /**
//     * @return pay_level_op_log.user: 操作用户
//     *
//     * @mbggenerated
//     */
//    public String getUser() {
//        return user;
//    }
//
//    /**
//     * 字段: pay_level_op_log.user<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 32<br/>
//     * 说明: 操作用户
//     *
//     * @mbggenerated
//     */
//    public void setUser(String user) {
//        this.user = user;
//    }
//
//    /**
//     * @return pay_level_op_log.pay_level_id: 支付层级id
//     *
//     * @mbggenerated
//     */
//    public Long getPayLevelId() {
//        return payLevelId;
//    }
//
//    /**
//     * 字段: pay_level_op_log.pay_level_id<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 19<br/>
//     * 说明: 支付层级id
//     *
//     * @mbggenerated
//     */
//    public void setPayLevelId(Long payLevelId) {
//        this.payLevelId = payLevelId;
//    }
//
//    /**
//     * @return pay_level_op_log.content: 操作描述
//     *
//     * @mbggenerated
//     */
//    public String getContent() {
//        return content;
//    }
//
//    /**
//     * 字段: pay_level_op_log.content<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 操作描述
//     *
//     * @mbggenerated
//     */
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    /**
//     * @return pay_level_op_log.last_time: 最后操作时间
//     *
//     * @mbggenerated
//     */
//    public Date getLastTime() {
//        return lastTime;
//    }
//
//    /**
//     * 字段: pay_level_op_log.last_time<br/>
//     * 必填: true<br/>
//     * 缺省: CURRENT_TIMESTAMP<br/>
//     * 长度: 19<br/>
//     * 说明: 最后操作时间
//     *
//     * @mbggenerated
//     */
//    public void setLastTime(Date lastTime) {
//        this.lastTime = lastTime;
//    }
//
//    /**
//     * @return pay_level_op_log.ip: 操作IP
//     *
//     * @mbggenerated
//     */
//    public String getIp() {
//        return ip;
//    }
//
//    /**
//     * 字段: pay_level_op_log.ip<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 128<br/>
//     * 说明: 操作IP
//     *
//     * @mbggenerated
//     */
//    public void setIp(String ip) {
//        this.ip = ip;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table pay_level_op_log
//     *
//     * @mbggenerated
//     */
//    @Override
//    public boolean equals(Object that) {
//        if (this == that) {
//            return true;
//        }
//        if (that == null) {
//            return false;
//        }
//        if (getClass() != that.getClass()) {
//            return false;
//        }
//        PayLevelOpLog other = (PayLevelOpLog) that;
//        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
//            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
//            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
//            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
//            && (this.getPayLevelId() == null ? other.getPayLevelId() == null : this.getPayLevelId().equals(other.getPayLevelId()))
//            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
//            && (this.getLastTime() == null ? other.getLastTime() == null : this.getLastTime().equals(other.getLastTime()))
//            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()));
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table pay_level_op_log
//     *
//     * @mbggenerated
//     */
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
//        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
//        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
//        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
//        result = prime * result + ((getPayLevelId() == null) ? 0 : getPayLevelId().hashCode());
//        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
//        result = prime * result + ((getLastTime() == null) ? 0 : getLastTime().hashCode());
//        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
//        return result;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table pay_level_op_log
//     *
//     * @mbggenerated
//     */
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", type=").append(type);
//        sb.append(", userId=").append(userId);
//        sb.append(", user=").append(user);
//        sb.append(", payLevelId=").append(payLevelId);
//        sb.append(", content=").append(content);
//        sb.append(", lastTime=").append(lastTime);
//        sb.append(", ip=").append(ip);
//        sb.append("]");
//        return sb.toString();
//    }
//}