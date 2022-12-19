package com.likes.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class AgentUserDO {

    /**
     * 代充人ID
     */
    private Long repaymemid;

    /**
     * 账号
     */
    private String acclogin;

    /**
     * 代充暱称
     */
    private String nickname;

    /**
     * 代充余额
     */
    private BigDecimal memgold;

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
     * 代充次数
     */
    private Long repaynums;

    /**
     * 最后上线时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastlogindate;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigDecimal getMemgold() {
        return memgold;
    }

    public void setMemgold(BigDecimal memgold) {
        this.memgold = memgold;
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

    public Long getRepaynums() {
        return repaynums;
    }

    public void setRepaynums(Long repaynums) {
        this.repaynums = repaynums;
    }

    public Date getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
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
