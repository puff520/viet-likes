package com.likes.common.model.vo.member;

/**
 * 系统用户详情信息！
 * @author lucien
 * @create 2020/7/16 11:47
 */
public class BdUserDetailVO {


    private Long loginid;

    private String acclogin;

    private String password;

    private String accno;

    private String phoneno;

    private String wechat;

    private String email;

    private String bdusername;

    private Long sysroleid;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

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

    public String getBdusername() {
        return bdusername;
    }

    public void setBdusername(String bdusername) {
        this.bdusername = bdusername;
    }

    public Long getSysroleid() {
        return sysroleid;
    }

    public void setSysroleid(Long sysroleid) {
        this.sysroleid = sysroleid;
    }


}
