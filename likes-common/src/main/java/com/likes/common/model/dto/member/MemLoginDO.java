package com.likes.common.model.dto.member;

import com.likes.common.mybatis.entity.MemLogin;

public class MemLoginDO extends MemLogin {

    private String bdusername;
    private String wechat;
    private String phoneno;
    private String email;
    private Long sysroleid;

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSysroleid() {
        return sysroleid;
    }

    public void setSysroleid(Long sysroleid) {
        this.sysroleid = sysroleid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getBdusername() {
        return bdusername;
    }

    public void setBdusername(String bdusername) {
        this.bdusername = bdusername;
    }

}
