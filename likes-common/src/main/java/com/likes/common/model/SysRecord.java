package com.likes.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`sys_record`")
public class SysRecord {
    /**
     * 记录id
     */
    @Id
    @Column(name = "`recordid`")
    private Long recordid;

    /**
     * 当前操作员
     */
    @Column(name = "`operationer`")
    private String operationer;

    /**
     * 操作时间
     */
    @Column(name = "`operationdate`")
    private Date operationdate;

    /**
     * 事件
     */
    @Column(name = "`recordevent`")
    private Integer recordevent;

    /**
     * 事件备注
     */
    @Column(name = "`recordremark`")
    private String recordremark;

    /**
     * 获取记录id
     *
     * @return recordid - 记录id
     */
    public Long getRecordid() {
        return recordid;
    }

    /**
     * 设置记录id
     *
     * @param recordid 记录id
     */
    public void setRecordid(Long recordid) {
        this.recordid = recordid;
    }

    /**
     * 获取当前操作员
     *
     * @return operationer - 当前操作员
     */
    public String getOperationer() {
        return operationer;
    }

    /**
     * 设置当前操作员
     *
     * @param operationer 当前操作员
     */
    public void setOperationer(String operationer) {
        this.operationer = operationer;
    }

    /**
     * 获取操作时间
     *
     * @return operationdate - 操作时间
     */
    public Date getOperationdate() {
        return operationdate;
    }

    /**
     * 设置操作时间
     *
     * @param operationdate 操作时间
     */
    public void setOperationdate(Date operationdate) {
        this.operationdate = operationdate;
    }

    /**
     * 获取事件
     *
     * @return recordevent - 事件
     */
    public Integer getRecordevent() {
        return recordevent;
    }

    /**
     * 设置事件
     *
     * @param recordevent 事件
     */
    public void setRecordevent(Integer recordevent) {
        this.recordevent = recordevent;
    }

    /**
     * 获取事件备注
     *
     * @return recordremark - 事件备注
     */
    public String getRecordremark() {
        return recordremark;
    }

    /**
     * 设置事件备注
     *
     * @param recordremark 事件备注
     */
    public void setRecordremark(String recordremark) {
        this.recordremark = recordremark;
    }
}