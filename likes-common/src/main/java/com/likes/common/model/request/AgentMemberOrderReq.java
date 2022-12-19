package com.likes.common.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AgentMemberOrderReq {
    @ApiModelProperty(value = "用户账号")
    private String email;
    private String accno;
    @ApiModelProperty(value = "用户Uid")
    private String uniqueId;
    @ApiModelProperty(value = "推荐人Uid")
    private String superiorId;
    @ApiModelProperty(value = "等级")
    private Integer memlevel;
    @ApiModelProperty(value = "账号")
    private String acclogin;
    @ApiModelProperty(value = "注册时间")
    private String registerdate;
    @ApiModelProperty(value = "注册ip")
    private String registerip;
    @ApiModelProperty(value = "开始金额")
    private Integer beginAmount;
    @ApiModelProperty(value = "截止金额")
    private Integer endAmount;
    @ApiModelProperty(value = "账号状态 1正常 9禁止登陆")
    private Integer accstatus;
    @ApiModelProperty(value = "开始时间")
    private String beginTime;
    @ApiModelProperty(value = "截止时间")
    private String endTime;

    @ApiModelProperty(value = "上级邀请码")
    private String suprecomcode;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(String superiorId) {
        this.superiorId = superiorId;
    }

    public Integer getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(Integer memlevel) {
        this.memlevel = memlevel;
    }

    public String getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate;
    }

    public String getRegisterip() {
        return registerip;
    }

    public void setRegisterip(String registerip) {
        this.registerip = registerip;
    }

    public Integer getBeginAmount() {
        return beginAmount;
    }

    public void setBeginAmount(Integer beginAmount) {
        this.beginAmount = beginAmount;
    }

    public Integer getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(Integer endAmount) {
        this.endAmount = endAmount;
    }

    public Integer getAccstatus() {
        return accstatus;
    }

    public void setAccstatus(Integer accstatus) {
        this.accstatus = accstatus;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public String getSuprecomcode() {
        return suprecomcode;
    }

    public void setSuprecomcode(String suprecomcode) {
        this.suprecomcode = suprecomcode;
    }
}
