package com.likes.common.model.request;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class UserRequest {

    private String operataccno;

    private String accno;

    private String username;

    private String mobileno;

    /**
     * 登录账号
     */
    private String acclogin;

    /**
     * 账户类型  普通会员1      主播2   家族长3   运营后台管理员8    第三方登录7   服务注册中心管理员9  聚合站点后台管理员10
     */
    private Integer logintype;

    private Integer accstatus;

    private Double goldnum;

    /**
     * 等级
     */
    private Long memlevel;

    /**
     * nickname
     */
    private String nickname;

    /**
     * 家族名称
     */
    private String familyname;

    /**
     * 直播状态
     */
    private Integer roomstatus;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 会员唯一id
     */
    private String uniqueId;

    private String registerIp;

    private String headAgent;
    private String headAccno;


    @ApiModelProperty(value = "开始金额")
    private BigDecimal beginAmount;
    @ApiModelProperty(value = "截止金额")
    private BigDecimal endAmount;

    public Integer getRoomstatus() {
        return roomstatus;
    }

    public void setRoomstatus(Integer roomstatus) {
        this.roomstatus = roomstatus;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(Long memlevel) {
        this.memlevel = memlevel;
    }

    public Double getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(Double goldnum) {
        this.goldnum = goldnum;
    }

    public Integer getAccstatus() {
        return accstatus;
    }

    public void setAccstatus(Integer accstatus) {
        this.accstatus = accstatus;
    }

    public String getOperataccno() {
        return operataccno;
    }

    public void setOperataccno(String operataccno) {
        this.operataccno = operataccno;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
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

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public BigDecimal getBeginAmount() {
        return beginAmount;
    }

    public void setBeginAmount(BigDecimal beginAmount) {
        this.beginAmount = beginAmount;
    }

    public BigDecimal getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(BigDecimal endAmount) {
        this.endAmount = endAmount;
    }

    public String getHeadAgent() {
        return headAgent;
    }

    public void setHeadAgent(String headAgent) {
        this.headAgent = headAgent;
    }

    public String getHeadAccno() {
        return headAccno;
    }

    public void setHeadAccno(String headAccno) {
        this.headAccno = headAccno;
    }
}
