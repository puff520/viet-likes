package com.likes.common.model.dto.member;

public class MemBankResponse {
    private Long bankaccid;
    private Integer accounttype;

    private String accounttypename;

    private String bankaddress;

    private String bankname;

    private String banknamealias;

    private String accountname;

    private String accountno;

    private String reaccountno;

    public String getReaccountno() {
        return reaccountno;
    }

    public void setReaccountno(String reaccountno) {
        this.reaccountno = reaccountno;
    }

    public String getBanknamealias() {
        return banknamealias;
    }

    public void setBanknamealias(String banknamealias) {
        this.banknamealias = banknamealias;
    }

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

    public String getAccounttypename() {
        return accounttypename;
    }

    public void setAccounttypename(String accounttypename) {
        this.accounttypename = accounttypename;
    }

    public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
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
}
