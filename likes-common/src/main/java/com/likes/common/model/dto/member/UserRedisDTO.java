package com.likes.common.model.dto.member;

public class UserRedisDTO {
    // 用户ID
    private Integer id;
    // 用户昵称
    private String userName;
    // 用户头像
    private String userHeads;
    // 用户二维码
    private String qrCode;
    // 电话
    private String phone;
    // 用户心水权限
    private Integer lhcxsStatus;
    // 冻结权限
    private Integer freezeStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHeads() {
        return userHeads;
    }

    public void setUserHeads(String userHeads) {
        this.userHeads = userHeads;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getLhcxsStatus() {
        return lhcxsStatus;
    }

    public void setLhcxsStatus(Integer lhcxsStatus) {
        this.lhcxsStatus = lhcxsStatus;
    }

    public Integer getFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(Integer freezeStatus) {
        this.freezeStatus = freezeStatus;
    }

}
