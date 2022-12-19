package com.likes.common.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class FamilyIncarnateResponse {

    private Long familyid;
    private String familyname;
    private String accno;
    private String familyman;
    private Integer accounttype;
    private String accounttypename;
    private Double royaltypercent;
    private Double bettingpercentage;//投注分成比例
    // private String bankaddress;
    private String bankname;
    private String banknamealias;
    private String accountname;
    private String accountno;
    private String orderstatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    // 能提现的金额
    private BigDecimal amount;

    private Integer isday;

    private String remark;
    //申请状态
    private Integer apycstatus;
    //提现ID
    private Long apycid;

    private Long orderid;
    private Integer ordertype;
    private BigDecimal sumamt;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public BigDecimal getSumamt() {
        return sumamt;
    }

    public void setSumamt(BigDecimal sumamt) {
        this.sumamt = sumamt;
    }

    public Double getRoyaltypercent() {
        return royaltypercent;
    }

    public Double getBettingpercentage() {
        return bettingpercentage;
    }

    public void setBettingpercentage(Double bettingpercentage) {
        this.bettingpercentage = bettingpercentage;
    }

    public void setRoyaltypercent(Double royaltypercent) {
        this.royaltypercent = royaltypercent;
    }

    public Long getApycid() {
        return apycid;
    }

    public void setApycid(Long apycid) {
        this.apycid = apycid;
    }

    public Integer getApycstatus() {
        return apycstatus;
    }

    public void setApycstatus(Integer apycstatus) {
        this.apycstatus = apycstatus;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public Integer getIsday() {
        return isday;
    }

    public void setIsday(Integer isday) {
        this.isday = isday;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getFamilyid() {
        return familyid;
    }

    public void setFamilyid(Long familyid) {
        this.familyid = familyid;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getFamilyman() {
        return familyman;
    }

    public void setFamilyman(String familyman) {
        this.familyman = familyman;
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

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

}
