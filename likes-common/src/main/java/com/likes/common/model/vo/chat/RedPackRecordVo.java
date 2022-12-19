package com.likes.common.model.vo.chat;

import java.math.BigDecimal;

public class RedPackRecordVo {
    private Integer userId;
    private String createTime;
    private String nickname;
    private String realName;
    private String account;
    private BigDecimal momey;
    private Integer number;
    private String drowTime;
    private BigDecimal drowMomey;
    private String startTime;
    private String endTime;
    private Integer drowUserId;

    public Integer getDrowUserId() {
        return drowUserId;
    }

    public void setDrowUserId(Integer drowUserId) {
        this.drowUserId = drowUserId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getMomey() {
        return momey;
    }

    public void setMomey(BigDecimal momey) {
        this.momey = momey;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDrowTime() {
        return drowTime;
    }

    public void setDrowTime(String drowTime) {
        this.drowTime = drowTime;
    }

    public BigDecimal getDrowMomey() {
        return drowMomey;
    }

    public void setDrowMomey(BigDecimal drowMomey) {
        this.drowMomey = drowMomey;
    }
}
