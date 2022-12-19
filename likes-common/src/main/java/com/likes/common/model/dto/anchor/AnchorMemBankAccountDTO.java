package com.likes.common.model.dto.anchor;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class AnchorMemBankAccountDTO {
    private Integer accounttype;

    private String accounttypename;

    private String bankaddress;

    private String bankname;

    private String banknamealias;

    private String accountname;

    private String accountno;

    private String apycstatus;

    private String ordernote;
    private Long orderid;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getOrdernote() {
        return ordernote;
    }

    public void setOrdernote(String ordernote) {
        this.ordernote = ordernote;
    }

    public String getApycstatus() {
        return apycstatus;
    }

    public void setApycstatus(String apycstatus) {
        this.apycstatus = apycstatus;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date apycdate;

    private BigDecimal apycamt;

    private BigDecimal apycgold;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paydate;

    private BigDecimal shouxufei;

    public BigDecimal getApycgold() {
        return apycgold;
    }

    public void setApycgold(BigDecimal apycgold) {
        this.apycgold = apycgold;
    }

    public BigDecimal getShouxufei() {
        return shouxufei;
    }

    public void setShouxufei(BigDecimal shouxufei) {
        this.shouxufei = shouxufei;
    }

    public String getBanknamealias() {
        return banknamealias;
    }

    public void setBanknamealias(String banknamealias) {
        this.banknamealias = banknamealias;
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

    public Date getApycdate() {
        return apycdate;
    }

    public void setApycdate(Date apycdate) {
        this.apycdate = apycdate;
    }

    public BigDecimal getApycamt() {
        return apycamt;
    }

    public void setApycamt(BigDecimal apycamt) {
        this.apycamt = apycamt;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }


}
