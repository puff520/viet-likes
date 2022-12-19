package com.likes.common.mybatis.entity;

import java.util.Date;

public class OrgBankaccount {
    private Long bankaccid;

    private Integer accounttype;

    private String bankname;

    private String accountname;

    private String accountno;

    private String accidcardno;

    private String idcardpic1;

    private String idcardpic2;

    private Integer checkstatus;

    private String checknote;

    private String emailaddress;

    private Integer isdelete;

    private String createuser;

    private Date createdate;

    private String modifyuser;

    private Date modifydate;

    public Long getBankaccid() {
        return bankaccid;
    }

    public void setBankaccid(Long bankaccid) {
        this.bankaccid = bankaccid;
    }

    public Integer getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Integer accounttype) {
        this.accounttype = accounttype;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getAccidcardno() {
        return accidcardno;
    }

    public void setAccidcardno(String accidcardno) {
        this.accidcardno = accidcardno;
    }

    public String getIdcardpic1() {
        return idcardpic1;
    }

    public void setIdcardpic1(String idcardpic1) {
        this.idcardpic1 = idcardpic1;
    }

    public String getIdcardpic2() {
        return idcardpic2;
    }

    public void setIdcardpic2(String idcardpic2) {
        this.idcardpic2 = idcardpic2;
    }

    public Integer getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Integer checkstatus) {
        this.checkstatus = checkstatus;
    }

    public String getChecknote() {
        return checknote;
    }

    public void setChecknote(String checknote) {
        this.checknote = checknote;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}