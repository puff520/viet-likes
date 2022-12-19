package com.likes.common.model.dto.member;

import java.math.BigDecimal;

public class MemFamilyResponse {
    private Long familyid;
    private String accno;
    private Integer accounttype;
    private String accounttypename;
    // 银行名称 ICBC
    private String bankname;
    private String bankaddress;
    // 中国工商银行
    private String banknamealias;
    private String accountname;

    private String accountno;

    private String idcardno;

    private String telephone;

    private String familyname;

    private String familyman;

    private Integer memnums;

    private BigDecimal royaltypercent;
    private BigDecimal bettingpercentage; //投注分成百分比

    public BigDecimal getBettingpercentage() {
        return bettingpercentage;
    }

    public void setBettingpercentage(BigDecimal bettingpercentage) {
        this.bettingpercentage = bettingpercentage;
    }

    private String acclogin;
    private String passwordmd5;

    /**
     * 邀请码
     */
    private String recomcode;

    /**
     * 主播总时长
     */
    private Integer timeTotal;
    /**
     * 主播总时长
     */
    private String timeTotalStr;
    /**
     * 礼物分成总数
     */
    private BigDecimal familyGiftTotal;
    /**
     * 投注分成总数
     */
    private BigDecimal familyBetTotal;

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public String getPasswordmd5() {
        return passwordmd5;
    }

    public void setPasswordmd5(String passwordmd5) {
        this.passwordmd5 = passwordmd5;
    }

    public BigDecimal getRoyaltypercent() {
        return royaltypercent;
    }

    public void setRoyaltypercent(BigDecimal royaltypercent) {
        this.royaltypercent = royaltypercent;
    }

    public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
    }

    public Integer getMemnums() {
        return memnums;
    }

    public void setMemnums(Integer memnums) {
        this.memnums = memnums;
    }

    public Long getFamilyid() {
        return familyid;
    }

    public void setFamilyid(Long familyid) {
        this.familyid = familyid;
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

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBanknamealias() {
        return banknamealias;
    }

    public void setBanknamealias(String banknamealias) {
        this.banknamealias = banknamealias;
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

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getFamilyman() {
        return familyman;
    }

    public void setFamilyman(String familyman) {
        this.familyman = familyman;
    }

    public String getRecomcode() {
        return recomcode;
    }

    public void setRecomcode(String recomcode) {
        this.recomcode = recomcode;
    }

    public Integer getTimeTotal() {
        return timeTotal;
    }

    public void setTimeTotal(Integer timeTotal) {
        this.timeTotal = timeTotal;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public BigDecimal getFamilyGiftTotal() {
        return familyGiftTotal;
    }

    public void setFamilyGiftTotal(BigDecimal familyGiftTotal) {
        this.familyGiftTotal = familyGiftTotal;
    }

    public BigDecimal getFamilyBetTotal() {
        return familyBetTotal;
    }

    public void setFamilyBetTotal(BigDecimal familyBetTotal) {
        this.familyBetTotal = familyBetTotal;
    }

    public String getTimeTotalStr() {
        return timeTotalStr;
    }

    public void setTimeTotalStr(String timeTotalStr) {
        this.timeTotalStr = timeTotalStr;
    }
}
