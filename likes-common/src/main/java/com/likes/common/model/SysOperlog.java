package com.likes.common.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "`sys_operlog`")
public class SysOperlog {
    /**
     * 日志id
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`acclogin`")
    private String acclogin;

    /**
     * 操作用户
     */
    @Column(name = "`accno`")
    private String accno;

    @Column(name = "`operation_name`")
    private String operationName;

    /**
     * 模块名称
     */
    @Column(name = "`method`")
    private String method;

    @Column(name = "`params`")
    private String params;

    @Column(name = "`ip`")
    private String ip;

    @Column(name = "`create_time`")
    private Date createTime;



    /**
     * 获取日志id
     *
     * @return id - 日志id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置日志id
     *
     * @param id 日志id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return acclogin
     */
    public String getAcclogin() {
        return acclogin;
    }

    /**
     * @param acclogin
     */
    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    /**
     * 获取操作用户
     *
     * @return accno - 操作用户
     */
    public String getAccno() {
        return accno;
    }

    /**
     * 设置操作用户
     *
     * @param accno 操作用户
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * @return operation_name
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * @param operationName
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    /**
     * 获取模块名称
     *
     * @return method - 模块名称
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置模块名称
     *
     * @param method 模块名称
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return params
     */
    public String getParams() {
        return params;
    }

    /**
     * @param params
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
