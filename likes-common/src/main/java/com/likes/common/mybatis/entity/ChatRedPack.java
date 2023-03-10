package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ChatRedPack implements Serializable {
    /**
     * 字段: chat_red_pack.id<br/>
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
     * 字段: chat_red_pack.user_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 发送用户ID
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * 字段: chat_red_pack.nickname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 用户呢称
     *
     * @mbggenerated
     */
    private String nickname;

    /**
     * 字段: chat_red_pack.head<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 头像
     *
     * @mbggenerated
     */
    private String head;

    /**
     * 字段: chat_red_pack.send_number<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 发送数量
     *
     * @mbggenerated
     */
    private Integer sendNumber;

    /**
     * 字段: chat_red_pack.money<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 发送金额
     *
     * @mbggenerated
     */
    private BigDecimal money;

    /**
     * 字段: chat_red_pack.receive_money<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 剩余金额
     *
     * @mbggenerated
     */
    private BigDecimal receiveMoney;

    /**
     * 字段: chat_red_pack.hold_number<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 领取个数
     *
     * @mbggenerated
     */
    private Integer holdNumber;

    /**
     * 字段: chat_red_pack.status<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 0 开启 1 结束
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 字段: chat_red_pack.create_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: chat_red_pack.end_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 截止时间
     *
     * @mbggenerated
     */
    private Date endTime;

    /**
     * 字段: chat_red_pack.is_del<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否删除 0 否 1 是
     *
     * @mbggenerated
     */
    private Integer isDel;

    /**
     * 字段: chat_red_pack.is_back<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否撤回已领取红包
     *
     * @mbggenerated
     */
    private Integer isBack;

    /**
     * 字段: chat_red_pack.gid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 聊天室ID
     *
     * @mbggenerated
     */
    private Integer gid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table chat_red_pack
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return chat_red_pack.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: chat_red_pack.id<br/>
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
     * @return chat_red_pack.user_id: 发送用户ID
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 字段: chat_red_pack.user_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 发送用户ID
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return chat_red_pack.nickname: 用户呢称
     *
     * @mbggenerated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 字段: chat_red_pack.nickname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 用户呢称
     *
     * @mbggenerated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return chat_red_pack.head: 头像
     *
     * @mbggenerated
     */
    public String getHead() {
        return head;
    }

    /**
     * 字段: chat_red_pack.head<br/>
     * 必填: false<br/>
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
     * @return chat_red_pack.send_number: 发送数量
     *
     * @mbggenerated
     */
    public Integer getSendNumber() {
        return sendNumber;
    }

    /**
     * 字段: chat_red_pack.send_number<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 发送数量
     *
     * @mbggenerated
     */
    public void setSendNumber(Integer sendNumber) {
        this.sendNumber = sendNumber;
    }

    /**
     * @return chat_red_pack.money: 发送金额
     *
     * @mbggenerated
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 字段: chat_red_pack.money<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 发送金额
     *
     * @mbggenerated
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * @return chat_red_pack.receive_money: 剩余金额
     *
     * @mbggenerated
     */
    public BigDecimal getReceiveMoney() {
        return receiveMoney;
    }

    /**
     * 字段: chat_red_pack.receive_money<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 剩余金额
     *
     * @mbggenerated
     */
    public void setReceiveMoney(BigDecimal receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    /**
     * @return chat_red_pack.hold_number: 领取个数
     *
     * @mbggenerated
     */
    public Integer getHoldNumber() {
        return holdNumber;
    }

    /**
     * 字段: chat_red_pack.hold_number<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 领取个数
     *
     * @mbggenerated
     */
    public void setHoldNumber(Integer holdNumber) {
        this.holdNumber = holdNumber;
    }

    /**
     * @return chat_red_pack.status: 0 开启 1 结束
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 字段: chat_red_pack.status<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 0 开启 1 结束
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return chat_red_pack.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: chat_red_pack.create_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return chat_red_pack.end_time: 截止时间
     *
     * @mbggenerated
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 字段: chat_red_pack.end_time<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 截止时间
     *
     * @mbggenerated
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return chat_red_pack.is_del: 是否删除 0 否 1 是
     *
     * @mbggenerated
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 字段: chat_red_pack.is_del<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否删除 0 否 1 是
     *
     * @mbggenerated
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * @return chat_red_pack.is_back: 是否撤回已领取红包
     *
     * @mbggenerated
     */
    public Integer getIsBack() {
        return isBack;
    }

    /**
     * 字段: chat_red_pack.is_back<br/>
     * 必填: true<br/>
     * 缺省: 0<br/>
     * 长度: 10<br/>
     * 说明: 是否撤回已领取红包
     *
     * @mbggenerated
     */
    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }

    /**
     * @return chat_red_pack.gid: 聊天室ID
     *
     * @mbggenerated
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * 字段: chat_red_pack.gid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 聊天室ID
     *
     * @mbggenerated
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_red_pack
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
        ChatRedPack other = (ChatRedPack) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getHead() == null ? other.getHead() == null : this.getHead().equals(other.getHead()))
            && (this.getSendNumber() == null ? other.getSendNumber() == null : this.getSendNumber().equals(other.getSendNumber()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getReceiveMoney() == null ? other.getReceiveMoney() == null : this.getReceiveMoney().equals(other.getReceiveMoney()))
            && (this.getHoldNumber() == null ? other.getHoldNumber() == null : this.getHoldNumber().equals(other.getHoldNumber()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getIsDel() == null ? other.getIsDel() == null : this.getIsDel().equals(other.getIsDel()))
            && (this.getIsBack() == null ? other.getIsBack() == null : this.getIsBack().equals(other.getIsBack()))
            && (this.getGid() == null ? other.getGid() == null : this.getGid().equals(other.getGid()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_red_pack
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getHead() == null) ? 0 : getHead().hashCode());
        result = prime * result + ((getSendNumber() == null) ? 0 : getSendNumber().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getReceiveMoney() == null) ? 0 : getReceiveMoney().hashCode());
        result = prime * result + ((getHoldNumber() == null) ? 0 : getHoldNumber().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        result = prime * result + ((getIsBack() == null) ? 0 : getIsBack().hashCode());
        result = prime * result + ((getGid() == null) ? 0 : getGid().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_red_pack
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
        sb.append(", nickname=").append(nickname);
        sb.append(", head=").append(head);
        sb.append(", sendNumber=").append(sendNumber);
        sb.append(", money=").append(money);
        sb.append(", receiveMoney=").append(receiveMoney);
        sb.append(", holdNumber=").append(holdNumber);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", isDel=").append(isDel);
        sb.append(", isBack=").append(isBack);
        sb.append(", gid=").append(gid);
        sb.append("]");
        return sb.toString();
    }
}