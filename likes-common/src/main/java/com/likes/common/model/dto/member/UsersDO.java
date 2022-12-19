package com.likes.common.model.dto.member;

import com.likes.common.mybatis.entity.MemBaseinfo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class UsersDO extends MemBaseinfo {

    private String acclogin;
    private Long loginid;
    private Integer logintype;
    private Integer accstatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-5")
    private Date lastlogindate;

    private Integer loginnum;

    private String clintipadd;

    private Long roomid;

    private Long channelid;

    private String roomname;

    private String recomcode;

    private String channelname;

    private Integer roomstatus;

    private Long gold;

    private String memlevel;

    private String register;

    public String getRefacclogin() {
        return refacclogin;
    }

    public void setRefacclogin(String refacclogin) {
        this.refacclogin = refacclogin;
    }

    /**
     * 代理账号
     */
    private String refacclogin;

    /**
     * 是否在线(0离线，1在线)
     */
    private String online;

    /**
     * 银行名称
     */
    private String bankname;

    /**
     * 开户行-地址
     */
    private String bankaddress;

    /**
     * 银行卡号
     */
    private String accountno;

    /**
     * 家族名称
     */
    private String familyname;

    /**
     * 昨日礼物收入
     */
    private BigDecimal yesterdayGold;

    /**
     * 总礼物收入
     */
    private BigDecimal totalGold;

    /**
     * 总在线时长
     */
    private Double totalOnlineTime;
    /**
     * 昨日总在线时长
     */
    private Double yesterdayOnlineTime;

    public Double getYesterdayOnlineTime() {
        return yesterdayOnlineTime;
    }

    public void setYesterdayOnlineTime(Double yesterdayOnlineTime) {
        this.yesterdayOnlineTime = yesterdayOnlineTime;
    }

    public Double getTotalOnlineTime() {
        return totalOnlineTime;
    }

    public void setTotalOnlineTime(Double totalOnlineTime) {
        this.totalOnlineTime = totalOnlineTime;
    }

    public BigDecimal getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(BigDecimal totalGold) {
        this.totalGold = totalGold;
    }

    public BigDecimal getYesterdayGold() {
        return yesterdayGold;
    }

    public void setYesterdayGold(BigDecimal yesterdayGold) {
        this.yesterdayGold = yesterdayGold;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public Integer getRoomstatus() {
        return roomstatus;
    }

    public void setRoomstatus(Integer roomstatus) {
        this.roomstatus = roomstatus;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public Long getChannelid() {
        return channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public Long getLoginid() {
        return loginid;
    }

    public void setLoginid(Long loginid) {
        this.loginid = loginid;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public Integer getLogintype() {
        return logintype;
    }

    public void setLogintype(Integer logintype) {
        this.logintype = logintype;
    }

    public Integer getAccstatus() {
        return accstatus;
    }

    public void setAccstatus(Integer accstatus) {
        this.accstatus = accstatus;
    }

    public Date getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public Integer getLoginnum() {
        return loginnum;
    }

    public void setLoginnum(Integer loginnum) {
        this.loginnum = loginnum;
    }

    public String getClintipadd() {
        return clintipadd;
    }

    public void setClintipadd(String clintipadd) {
        this.clintipadd = clintipadd;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    @Override
    public String getRecomcode() {
        return recomcode;
    }

    @Override
    public void setRecomcode(String recomcode) {
        this.recomcode = recomcode;
    }
}
