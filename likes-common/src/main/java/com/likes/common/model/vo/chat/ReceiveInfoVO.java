package com.likes.common.model.vo.chat;

import java.math.BigDecimal;
import java.util.Date;

public class ReceiveInfoVO {

    /**
     * 头像
     */
    private String head;

    /**
     * 呢称
     */
    private String nickName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 金额
     */
    private BigDecimal money;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

}
