package com.likes.common.model.dto;

import com.likes.common.model.common.PageBaseInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class AgentMemberDTO extends PageBaseInfo implements Serializable {

    @ApiModelProperty(value = "注册时间")
    private String registerdate;
    @ApiModelProperty(value = "注册ip")
    private String registerip;
    @ApiModelProperty(value = "登录ip")
    private String clintipadd;
    @ApiModelProperty(value = "最后登录时间")
    private String lastlogindate;
    @ApiModelProperty(value = "在线状态")
    private Integer onlinestatus;
    @ApiModelProperty(value = "上级邀请码")
    private String suprecomcode;
    @ApiModelProperty(value = "会员标识号")
    private String accno;
    @ApiModelProperty(value = "会员uid")
    private String uniqueId;
    @ApiModelProperty(value = "会员等级")
    private String memlevel;
    @ApiModelProperty(value = "上级uid")
    private String superiorId;
    @ApiModelProperty(value = "余额")
    private BigDecimal goldnum;
    @ApiModelProperty(value = "用户状态 1 正常 9 禁止登录")
    private Integer accstatus;
    @ApiModelProperty(value = "邮箱（用户名）")
    private String email;
    @ApiModelProperty(value = "下级数量")
    private Integer subNum;

    @ApiModelProperty(value = "上级邮箱")
    private String subEmail;

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

    public String getClintipadd() {
        return clintipadd;
    }

    public void setClintipadd(String clintipadd) {
        this.clintipadd = clintipadd;
    }

    public String getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(String lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public Integer getOnlinestatus() {
        return onlinestatus;
    }

    public void setOnlinestatus(Integer onlinestatus) {
        this.onlinestatus = onlinestatus;
    }


    public String getSuprecomcode() {
        return suprecomcode;
    }

    public void setSuprecomcode(String suprecomcode) {
        this.suprecomcode = suprecomcode;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSubNum() {
        return subNum;
    }

    public void setSubNum(Integer subNum) {
        this.subNum = subNum;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public String getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(String superiorId) {
        this.superiorId = superiorId;
    }

    public BigDecimal getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(BigDecimal goldnum) {
        this.goldnum = goldnum;
    }

    public Integer getAccstatus() {
        return accstatus;
    }

    public void setAccstatus(Integer accstatus) {
        this.accstatus = accstatus;
    }

    public String getSubEmail() {
        return subEmail;
    }

    public void setSubEmail(String subEmail) {
        this.subEmail = subEmail;
    }
}
