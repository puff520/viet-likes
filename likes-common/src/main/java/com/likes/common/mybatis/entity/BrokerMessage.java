package com.likes.common.mybatis.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "`dz_broker_message`")
public class BrokerMessage {
    @Id
    @Column(name = "`message_Id`")
    private String messageId;

    @Column(name = "`message`")
    private String message;

    @Column(name = "`try_count`")
    private Integer tryCount;

    @Column(name = "`status`")
    private Integer status;

    @Column(name = "`next_retry`")
    private Date nextRetry;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * @return message_Id
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * @param messageId
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return try_count
     */
    public Integer getTryCount() {
        return tryCount;
    }

    /**
     * @param tryCount
     */
    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return next_retry
     */
    public Date getNextRetry() {
        return nextRetry;
    }

    /**
     * @param nextRetry
     */
    public void setNextRetry(Date nextRetry) {
        this.nextRetry = nextRetry;
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

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
