package com.likes.common.model.request;

public class BankRequest {

    /**
     * 账号id
     */
    private Long memid;

    /**
     * 银行名称标识符 如ICBC
     */
    private String bankname;

    /**
     * 开户行 如 某某支行
     */
    private String bankaddress;

    /**
     * 卡号
     */
    private String accountno;

    /**
     * 账号启用停用
     */
    private Integer accstatus;

    /**
     * 真实姓名--用户表
     */
    private String memname;

    /**
     * 昵称--用户表
     */
    private String nickname;

    /**
     * 用户等级
     */
    private String memlevel;

    /**
     * 用户等级锁定
     */
    private Boolean locked;

    /**
     * 个人签名
     */
    private String describes;

    /**
     * 是否管理员(是否隐藏手机号码)
     */
    private Boolean manage;

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
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

    public Integer getAccstatus() {
        return accstatus;
    }

    public void setAccstatus(Integer accstatus) {
        this.accstatus = accstatus;
    }

    public String getMemname() {
        return memname;
    }

    public void setMemname(String memname) {
        this.memname = memname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public Boolean getManage() {
        return manage;
    }

    public void setManage(Boolean manage) {
        this.manage = manage;
    }
}
