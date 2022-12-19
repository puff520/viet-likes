package com.likes.common.model.dto;

import com.likes.common.model.common.PageBaseInfo;

import java.io.Serializable;

/**
 * @ClassName AgentMemberDTO
 * @Description TODO
 * @Author yeezy
 * @Date 2019/11/22 13:54
 * @Version 1.0
 **/
public class RechargeDTO extends PageBaseInfo implements Serializable {

    private String registerdate;
    private String registerip;
    private String clintipadd;
    private String lastlogindate;
    private Integer onlinestatus;
    private Integer subnum;
    private String suprecomcode;

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

    public Integer getSubnum() {
        return subnum;
    }

    public void setSubnum(Integer subnum) {
        this.subnum = subnum;
    }

    public String getSuprecomcode() {
        return suprecomcode;
    }

    public void setSuprecomcode(String suprecomcode) {
        this.suprecomcode = suprecomcode;
    }
}
