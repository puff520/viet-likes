package com.likes.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class AgentUserDetailDO {

    /**
     * 代充人ID
     */
    private Long repaymemid;

    /**
     * 账号
     */
    private String acclogin;

    /**
     * 密码
     */
    private String password;

    /**
     * 暱称
     */
    private String nickname;

    /**
     * QQ
     */
    private String qq;

    /**
     * 微信
     */
    private String webchat;

    /**
     * 支付宝
     */
    private String alipay;

    /**
     * 手机号
     */
    private String mobileno;

    /**
     * 开店时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date onlinedates;

    /**
     * 关店时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date onlinedatee;

    /**
     * 充值优惠(%)
     */
    private BigDecimal discountrag;

    /**
     * 状态	0-启用 9-禁用
     */
    private Integer status;

    public Long getRepaymemid() {
        return repaymemid;
    }

    public void setRepaymemid(Long repaymemid) {
        this.repaymemid = repaymemid;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWebchat() {
        return webchat;
    }

    public void setWebchat(String webchat) {
        this.webchat = webchat;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public Date getOnlinedates() {
        return onlinedates;
    }

    public void setOnlinedates(Date onlinedates) {
        this.onlinedates = onlinedates;
    }

    public Date getOnlinedatee() {
        return onlinedatee;
    }

    public void setOnlinedatee(Date onlinedatee) {
        this.onlinedatee = onlinedatee;
    }

    public BigDecimal getDiscountrag() {
        return discountrag;
    }

    public void setDiscountrag(BigDecimal discountrag) {
        this.discountrag = discountrag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
