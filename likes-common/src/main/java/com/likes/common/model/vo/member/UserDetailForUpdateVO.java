package com.likes.common.model.vo.member;

import com.likes.common.mybatis.entity.MemBaseinfo;

import java.util.Date;

public class UserDetailForUpdateVO extends MemBaseinfo {

    /**
     * 代理账号
     */
    private String refUniqueId;

    /**
     * 说明: 账号状态 1正常 9禁止登陆
     */
    private Integer accstatus;

    private String acclogin;

    /**
     * 说明: 最后登录时间
     */
    private Date lastlogindate;

    /**
     * 说明: 当前等级
     */
    private String memlevel;

    /**
     * 说明: 0:未锁定,1:锁定
     */
    private Boolean locked;

    /**
     * 说明: 银行卡/支付宝/微信账号
     */
    private String accountno;

    /**
     * 说明: 银行/支付宝/微信开户人姓名
     */
    private String accountname;

    /**
     * 说明: 银行名称标识符 如ICBC
     */
    private String bankname;

    /**
     * 说明: 开户行 如 某某支行
     */
    private String bankaddress;

    public String getRefUniqueId() {
        return refUniqueId;
    }

    public void setRefUniqueId(String refUniqueId) {
        this.refUniqueId = refUniqueId;
    }

    public Integer getAccstatus() {
        return accstatus;
    }

    public void setAccstatus(Integer accstatus) {
        this.accstatus = accstatus;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
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

    public Date getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }
}
