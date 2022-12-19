package com.likes.common.model.dto.money;

import java.util.Arrays;

public class OfflinePaymentDTO extends MoneyBaseDTO {
    private Integer userId;
    private Integer status;
    private String userName;
    private String realName;
    private String checkUser;
    private String fuyan;
    private Integer[] channelId;  //公司收款账号
    private String bankAccountLastFour;
    private String bankName;

    private Integer pid;
    private Integer way;

    private Integer extra;
    private String reserve;

    public String getChannelIdString() {
        if (null == this.channelId || this.channelId.length == 0) {
            return "";
        }
        return Arrays.toString(this.channelId);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getFuyan() {
        return fuyan;
    }

    public void setFuyan(String fuyan) {
        this.fuyan = fuyan;
    }

    public Integer[] getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer[] channelId) {
        this.channelId = channelId;
    }

    public String getBankAccountLastFour() {
        return bankAccountLastFour;
    }

    public void setBankAccountLastFour(String bankAccountLastFour) {
        this.bankAccountLastFour = bankAccountLastFour;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public Integer getExtra() {
        return extra;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

}
