package com.likes.common.model.vo.circle;

import java.math.BigDecimal;

public class ChatOrderInfoVo {
    // 订单ID
    private Integer orderId;
    // 截止时间
    private Long endTime;
    // 地址
    private String url;
    // 单个金额
    private BigDecimal numberMomey;

    //总投注额
    private BigDecimal totalMomey;
    // 彩种名称
    private String lotteryName;
    // 期号
    private String issue;
    // 投注内容
    private String betNumber;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(String betNumber) {
        this.betNumber = betNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getNumberMomey() {
        return numberMomey;
    }

    public void setNumberMomey(BigDecimal numberMomey) {
        this.numberMomey = numberMomey;
    }

    public BigDecimal getTotalMomey() {
        return totalMomey;
    }

    public void setTotalMomey(BigDecimal totalMomey) {
        this.totalMomey = totalMomey;
    }
}

