package com.likes.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TraApplycashDO {
    private String modifyusername;
    private Long apycid;
    private Long orderid;
    private String accno;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date apycdate;
    private BigDecimal apycgold;
    private BigDecimal apycamt;
    //申请状态  1提交申请  2提现处理中  4已打款    8已到账  9已取消
    private Integer apycstatus;
    private String paymemname;
    private Date paydate;
    private String createuser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;
    private String modifyuser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifydate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String orderstatus;
    private Integer ordertype;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    //凭证
    private String payimgurl;

    public String getPayimgurl() {
        return payimgurl;
    }

    public void setPayimgurl(String payimgurl) {
        this.payimgurl = payimgurl;
    }

    public String getModifyusername() {
        return modifyusername;
    }

    public void setModifyusername(String modifyusername) {
        this.modifyusername = modifyusername;
    }

    public Long getApycid() {
        return apycid;
    }

    public void setApycid(Long apycid) {
        this.apycid = apycid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Date getApycdate() {
        return apycdate;
    }

    public void setApycdate(Date apycdate) {
        this.apycdate = apycdate;
    }

    public BigDecimal getApycgold() {
        return apycgold;
    }

    public void setApycgold(BigDecimal apycgold) {
        this.apycgold = apycgold;
    }

    public BigDecimal getApycamt() {
        return apycamt;
    }

    public void setApycamt(BigDecimal apycamt) {
        this.apycamt = apycamt;
    }

    public Integer getApycstatus() {
        return apycstatus;
    }

    public void setApycstatus(Integer apycstatus) {
        this.apycstatus = apycstatus;
    }

    public String getPaymemname() {
        return paymemname;
    }

    public void setPaymemname(String paymemname) {
        this.paymemname = paymemname;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
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
