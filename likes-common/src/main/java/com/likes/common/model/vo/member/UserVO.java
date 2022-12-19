package com.likes.common.model.vo.member;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 后台会员列表页专用
 */
public class UserVO {

    /**
     * 说明: 用户id
     */
    private Long memid;

    /**
     * 说明: 会员ID
     */
    private String uniqueId;

    /**
     * 所属代理
     */
    private String refUniqueId;

    /**
     * 说明: 会员标识号
     */
    private String accno;

    /**
     * 说明: 呢称
     */
    private String nickname;

    /**
     * 会员等级
     */
    private String memlevel;

    /**
     * 说明: 用户乐币数量
     */
    private BigDecimal goldnum;

    /**
     * 说明: 总消费(打码量)
     */
    private BigDecimal consumeAmount;

    /**
     * 说明: 不可提现金额(元)
     */
    private BigDecimal noWithdrawalAmount;

    public String getClintipadd() {
        return clintipadd;
    }

    public void setClintipadd(String clintipadd) {
        this.clintipadd = clintipadd;
    }

    private String clintipadd;

    /**
     * 说明: 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-5")
    private Date lastlogindate;


    /**
     * 是否在线(0离线，1在线)
     */
    private String online;

    /**
     * 账号状态 1正常 9禁止登陆
     */
    private Integer accstatus;

    /**
     * 说明: 备注
     */
    private String remark;
    private String recomcode;
    private String topAgent;

    /**
     * 说明: 登录账号
     */
    private String acclogin;
    private String registerIp;
    private String registerdate;

    private String createUser;

    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    public String getRefUniqueId() {
        return refUniqueId;
    }

    public void setRefUniqueId(String refUniqueId) {
        this.refUniqueId = refUniqueId;
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

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public BigDecimal getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(BigDecimal goldnum) {
        this.goldnum = goldnum;
    }

    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public BigDecimal getNoWithdrawalAmount() {
        return noWithdrawalAmount;
    }

    public void setNoWithdrawalAmount(BigDecimal noWithdrawalAmount) {
        this.noWithdrawalAmount = noWithdrawalAmount;
    }

    public Date getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public Integer getAccstatus() {
        return accstatus;
    }

    public void setAccstatus(Integer accstatus) {
        this.accstatus = accstatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public String getRecomcode() {
        return recomcode;
    }

    public void setRecomcode(String recomcode) {
        this.recomcode = recomcode;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate;
    }

    public String getTopAgent() {
        return topAgent;
    }

    public void setTopAgent(String topAgent) {
        this.topAgent = topAgent;
    }
}
