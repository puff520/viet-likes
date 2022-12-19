package com.likes.common.model.vo.member;

import java.math.BigDecimal;

/**
 * @Author: admin
 * @Description:
 * @Version: 1.0.0
 * @Date; 2017-12-18 15:55
 */
public class MemberVO {

    //用户id
    private Integer uid;
    //用户账号
    private String account;
    //用户昵称
    private String nickname;
    //头像url
    private String heads;
    /** 我方生成的唯一标识（验证登录使用） */
    private String token;
    private BigDecimal balance;
    /** 标识修改昵称 */
//    private Boolean checkRename;
    //手机号
    private String phone;

    private String promotionCode;

    private int userType;

    private Integer lhcxsStatus;

    private Integer freezeStatus;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeads() {
        return heads;
    }

    public void setHeads(String heads) {
        this.heads = heads;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

//    public boolean isCheckRename() {
//        return checkRename;
//    }
//
//    public void setCheckRename(Boolean checkRename) {
//        this.checkRename = checkRename;
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
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

    @Override
    public String toString() {
        return "MemberVO [uid=" + uid + ", account=" + account + ", nickname=" + nickname + ", heads=" + heads
                + ", token=" + token + ", balance=" + balance + ", phone=" + phone
                + ", promotionCode=" + promotionCode + ", userType=" + userType + ", lhcxsStatus=" + lhcxsStatus
                + ", freezeStatus=" + freezeStatus + "]";
    }
}
