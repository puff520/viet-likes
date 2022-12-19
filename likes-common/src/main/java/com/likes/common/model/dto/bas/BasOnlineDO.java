package com.likes.common.model.dto.bas;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class BasOnlineDO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirydatee;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirydates;

    /**
     * 字段: bas_online.onlineid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 在线id
     *
     * @mbggenerated
     */
    private Long onlineid;

    /**
     * 字段: bas_online.roomid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 主播房间id
     *
     * @mbggenerated
     */
    private Long roomid;

    /**
     * 字段: bas_online.accno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 会员标识号
     *
     * @mbggenerated
     */
    private String accno;

    /**
     * 字段: bas_online.nickname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 呢称
     *
     * @mbggenerated
     */
    private String nickname;

    /**
     * 字段: bas_online.clientid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 客户端id
     *
     * @mbggenerated
     */
    private String clientid;

    /**
     * 字段: bas_online.server<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 当前直播服务器host
     *
     * @mbggenerated
     */
    private String server;

    /**
     * 字段: bas_online.ismanage<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 房间管理员 0是 9否
     *
     * @mbggenerated
     */
    private Integer ismanage;

    /**
     * 字段: bas_online.isforbidden<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 是否禁言 0是 9否
     *
     * @mbggenerated
     */
    private Integer isforbidden;

    /**
     * 字段: bas_online.status<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 状态 0在线 9下线
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 字段: bas_online.onlinedate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 上线时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date onlinedate;

    /**
     * 字段: bas_online.offlinedate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 下线时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date offlinedate;

    /**
     * 房间禁止类型 0解除 1禁言 2 禁止进入直播间
     */
    private Integer forbidtype;

    /**
     * 用户余额
     */
    private BigDecimal goldnum;

    /**
     * 会员禁止类型 0正常 1禁言 2禁入
     */
    private Integer memForbidType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 当前在线会员本次直播礼物消费总金额
     */
    private double totalGiftAmt;

    /**
     * 当前在线会员本次直播投注总金额
     */
    private double totalBetAmt;
    /**
     * 账号
     */
    private String accLogin;
    /**
     * 等级
     */
    private Integer level;

    public Long getOnlineid() {
        return onlineid;
    }

    public void setOnlineid(Long onlineid) {
        this.onlineid = onlineid;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
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

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getIsmanage() {
        return ismanage;
    }

    public void setIsmanage(Integer ismanage) {
        this.ismanage = ismanage;
    }

    public Integer getIsforbidden() {
        return isforbidden;
    }

    public void setIsforbidden(Integer isforbidden) {
        this.isforbidden = isforbidden;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOnlinedate() {
        return onlinedate;
    }

    public void setOnlinedate(Date onlinedate) {
        this.onlinedate = onlinedate;
    }

    public Date getOfflinedate() {
        return offlinedate;
    }

    public void setOfflinedate(Date offlinedate) {
        this.offlinedate = offlinedate;
    }

    public Date getExpirydates() {
        return expirydates;
    }

    public void setExpirydates(Date expirydates) {
        this.expirydates = expirydates;
    }

    public Date getExpirydatee() {
        return expirydatee;
    }

    public void setExpirydatee(Date expirydatee) {
        this.expirydatee = expirydatee;
    }

    public Integer getForbidtype() {
        return forbidtype;
    }

    public void setForbidtype(Integer forbidtype) {
        this.forbidtype = forbidtype;
    }

    public BigDecimal getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(BigDecimal goldnum) {
        this.goldnum = goldnum;
    }

    public Integer getMemForbidType() {
        return memForbidType;
    }

    public void setMemForbidType(Integer memForbidType) {
        this.memForbidType = memForbidType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public double getTotalGiftAmt() {
        return totalGiftAmt;
    }

    public void setTotalGiftAmt(double totalGiftAmt) {
        this.totalGiftAmt = totalGiftAmt;
    }

    public double getTotalBetAmt() {
        return totalBetAmt;
    }

    public void setTotalBetAmt(double totalBetAmt) {
        this.totalBetAmt = totalBetAmt;
    }

    public String getAccLogin() {
        return accLogin;
    }

    public void setAccLogin(String accLogin) {
        this.accLogin = accLogin;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
