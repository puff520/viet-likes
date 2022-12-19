package com.likes.common.model.dto.godplan;

/**
 * @Author: admin
 * @Description:
 * @Version: 1.0.0
 * @Date; 2020-02-18 15:56
 */
public class GodPlanRewardDTO {
    private Integer userId;
    private String godName;
    private String userName;
    private String lotteryName;
    private Integer money;
    private Integer lotteryId;
    private Integer godPlanId;
    private Integer playTagId;
    private Long planId;
    private Integer createUserId;
    private Integer updateUserId;

    public Integer getPlayTagId() {
        return playTagId;
    }

    public void setPlayTagId(Integer playTagId) {
        this.playTagId = playTagId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getGodPlanId() {
        return godPlanId;
    }

    public void setGodPlanId(Integer godPlanId) {
        this.godPlanId = godPlanId;
    }

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    public String getGodName() {
        return godName;
    }

    public void setGodName(String godName) {
        this.godName = godName;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }
}
