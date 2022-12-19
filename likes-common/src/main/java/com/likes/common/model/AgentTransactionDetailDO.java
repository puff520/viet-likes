package com.likes.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AgentTransactionDetailDO {

    /**
     * 订单id
     */
    private Long orderid;

    /**
     * 订单编号
     */
    private String orderno;

    /**
     * 代充人账号
     */
    private String acclogin;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    /**
     * 充值优惠%
     */
    private BigDecimal discountrag;

    /**
     * 汇款姓名
     */
    private String payuser;

    /**
     * 汇款备注
     */
    private String paynote;

    /**
     * 支付凭证截图 多张以“，”分隔
     */
    private String payimg;

    /**
     * 支付凭证截图地址
     */
    private List<String> payimgurl;

    /**
     * 充值金额
     */
    private BigDecimal realamt;

    /**
     * 收款人姓名
     */
    private String accountname;

    /**
     * 收款银行
     */
    private String bankaddress;

    /**
     * 收款账号
     */
    private String accountno;

    /**
     * 实际到账金额
     */
    private BigDecimal sumamt;

    /**
     * 状态
     */
    private String orderstatus;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public BigDecimal getDiscountrag() {
        return discountrag;
    }

    public void setDiscountrag(BigDecimal discountrag) {
        this.discountrag = discountrag;
    }

    public String getPayuser() {
        return payuser;
    }

    public void setPayuser(String payuser) {
        this.payuser = payuser;
    }

    public String getPaynote() {
        return paynote;
    }

    public void setPaynote(String paynote) {
        this.paynote = paynote;
    }

    public String getPayimg() {
        return payimg;
    }

    public void setPayimg(String payimg) {
        this.payimg = payimg;
    }

    public List<String> getPayimgurl() {
        return payimgurl;
    }

    public void setPayimgurl(List<String> payimgurl) {
        this.payimgurl = payimgurl;
    }

    public BigDecimal getRealamt() {
        return realamt;
    }

    public void setRealamt(BigDecimal realamt) {
        this.realamt = realamt;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public BigDecimal getSumamt() {
        return sumamt;
    }

    public void setSumamt(BigDecimal sumamt) {
        this.sumamt = sumamt;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }
}
