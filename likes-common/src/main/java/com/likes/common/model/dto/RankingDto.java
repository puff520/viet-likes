package com.likes.common.model.dto;

public class RankingDto {

    private  Integer taskNum;
    private  String userName;
    private  Double taskAmount;
    private  String image;

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getTaskAmount() {
        return taskAmount;
    }

    public void setTaskAmount(Double taskAmount) {
        this.taskAmount = taskAmount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
