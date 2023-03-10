package com.likes.common.mybatis.entity;

import java.io.Serializable;

public class ApiInterfaceLog implements Serializable {
    /**
     * 字段: api_interface_log.id<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 字段: api_interface_log.name<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 接口名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 字段: api_interface_log.member_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 会员id
     *
     * @mbggenerated
     */
    private Integer memberId;

    /**
     * 字段: api_interface_log.account<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 会员账号
     *
     * @mbggenerated
     */
    private String account;

    /**
     * 字段: api_interface_log.api_order<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: api命令
     *
     * @mbggenerated
     */
    private String apiOrder;

    /**
     * 字段: api_interface_log.error_code<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 错误码
     *
     * @mbggenerated
     */
    private String errorCode;

    /**
     * 字段: api_interface_log.goback<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 返回信息
     *
     * @mbggenerated
     */
    private String goback;

    /**
     * 字段: api_interface_log.ip<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: ip
     *
     * @mbggenerated
     */
    private String ip;

    /**
     * 字段: api_interface_log.create_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 请求时间
     *
     * @mbggenerated
     */
    private Long createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table api_interface_log
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return api_interface_log.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: api_interface_log.id<br/>
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
     * @return api_interface_log.name: 接口名称
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 字段: api_interface_log.name<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 接口名称
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return api_interface_log.member_id: 会员id
     *
     * @mbggenerated
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * 字段: api_interface_log.member_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 会员id
     *
     * @mbggenerated
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * @return api_interface_log.account: 会员账号
     *
     * @mbggenerated
     */
    public String getAccount() {
        return account;
    }

    /**
     * 字段: api_interface_log.account<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 会员账号
     *
     * @mbggenerated
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return api_interface_log.api_order: api命令
     *
     * @mbggenerated
     */
    public String getApiOrder() {
        return apiOrder;
    }

    /**
     * 字段: api_interface_log.api_order<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: api命令
     *
     * @mbggenerated
     */
    public void setApiOrder(String apiOrder) {
        this.apiOrder = apiOrder;
    }

    /**
     * @return api_interface_log.error_code: 错误码
     *
     * @mbggenerated
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 字段: api_interface_log.error_code<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 错误码
     *
     * @mbggenerated
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return api_interface_log.goback: 返回信息
     *
     * @mbggenerated
     */
    public String getGoback() {
        return goback;
    }

    /**
     * 字段: api_interface_log.goback<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 返回信息
     *
     * @mbggenerated
     */
    public void setGoback(String goback) {
        this.goback = goback;
    }

    /**
     * @return api_interface_log.ip: ip
     *
     * @mbggenerated
     */
    public String getIp() {
        return ip;
    }

    /**
     * 字段: api_interface_log.ip<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: ip
     *
     * @mbggenerated
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return api_interface_log.create_time: 请求时间
     *
     * @mbggenerated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 字段: api_interface_log.create_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 请求时间
     *
     * @mbggenerated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_interface_log
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
        ApiInterfaceLog other = (ApiInterfaceLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getApiOrder() == null ? other.getApiOrder() == null : this.getApiOrder().equals(other.getApiOrder()))
            && (this.getErrorCode() == null ? other.getErrorCode() == null : this.getErrorCode().equals(other.getErrorCode()))
            && (this.getGoback() == null ? other.getGoback() == null : this.getGoback().equals(other.getGoback()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_interface_log
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getApiOrder() == null) ? 0 : getApiOrder().hashCode());
        result = prime * result + ((getErrorCode() == null) ? 0 : getErrorCode().hashCode());
        result = prime * result + ((getGoback() == null) ? 0 : getGoback().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_interface_log
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
        sb.append(", name=").append(name);
        sb.append(", memberId=").append(memberId);
        sb.append(", account=").append(account);
        sb.append(", apiOrder=").append(apiOrder);
        sb.append(", errorCode=").append(errorCode);
        sb.append(", goback=").append(goback);
        sb.append(", ip=").append(ip);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}