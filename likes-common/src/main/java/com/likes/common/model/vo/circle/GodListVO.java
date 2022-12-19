package com.likes.common.model.vo.circle;

import java.math.BigDecimal;

public class GodListVO {
    private Integer godId;      //大神id
    private Integer userId;    //用户id
    private String heads;   //头像
    private String nickname;    //昵称，没有的话展示用户名
    private Integer isFocus;    //是否关注  0否1是
    private String showRate;    //胜率、盈利率、连中
    private BigDecimal totalMoney;    //累计金额

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getGodId() {
        return godId;
    }

    public void setGodId(Integer godId) {
        this.godId = godId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHeads() {
        return heads;
    }

    public void setHeads(String heads) {
        this.heads = heads;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(Integer isFocus) {
        this.isFocus = isFocus;
    }

    public String getShowRate() {
        return showRate;
    }

    public void setShowRate(String showRate) {
        this.showRate = showRate;
    }
}
