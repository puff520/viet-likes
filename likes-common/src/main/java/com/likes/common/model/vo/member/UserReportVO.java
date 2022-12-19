package com.likes.common.model.vo.member;

import java.math.BigDecimal;

/**
 * 后台-用户报表列表页
 */
public class UserReportVO {

    /**
     * 说明: 登录账号
     */
    private String acclogin;

    /**
     * 说明: 用户id
     */
    private Long memid;

    /**
     * 说明: 会员ID
     */
    private String uniqueId;

    /**
     * 说明: 会员标识号
     */
    private String accno;

    /**
     * 说明: 呢称
     */
    private String nickname;

    /**
     * 上级代理昵称
     */
    private String refNickname;

    /**
     * 下级人数
     */
    private String userNum;

    /**
     * 会员等级
     */
    private String memlevel;

    /**
     * 账号状态 1正常 9禁止登陆
     */
    private Integer accstatus;

    /**
     * 说明: 用户乐币数量
     */
    private BigDecimal goldnum;

    /**
     * 说明: 累计充值（元）
     */
    private BigDecimal payAmount;

    /**
     * 说明: 累计充值（元）
     */
    private BigDecimal withdrawalAmount;

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRefNickname() {
        return refNickname;
    }

    public void setRefNickname(String refNickname) {
        this.refNickname = refNickname;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public Integer getAccstatus() {
        return accstatus;
    }

    public void setAccstatus(Integer accstatus) {
        this.accstatus = accstatus;
    }

    public BigDecimal getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(BigDecimal goldnum) {
        this.goldnum = goldnum;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }
}
