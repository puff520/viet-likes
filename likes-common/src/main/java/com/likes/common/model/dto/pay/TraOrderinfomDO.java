package com.likes.common.model.dto.pay;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 代充人订单
 * @author 瑞夫
 * @version 1.0
 * @date 2020/5/7
 */
public class TraOrderinfomDO {
    private Long orderid;

    /**
     * 交易类型
     */
    private Integer ordertype;
    /**
     * 交易类型描述
     */
    private String ordertypedec;

    /**
     * 订单金额
     */
    private BigDecimal oldamt;

    /**
     * 实际到账金额
     */
    private BigDecimal sumamt;
    /**
     * 实际支付金额
     */
    private BigDecimal realamt;

    /**
     * 订单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderdate;
    /**
     * 代充 用户账号ID ，
     */
    private String memaccount;

    /**
     * 银行
     */
    private String nickname;

    /**
     * 银行标识
     */
    private String bankname;

    /**
     * 收款银行卡号
     */
    private String accountno;

    /**
     * 收款人
     */
    private String accountname;

    /**
     * 收款卡开户行
     */
    private String bankaddress;

    private Long mealid;

    private Long bankid;

    private Long roomid;

    private Long lotkindid;

    private Long sschistoryid;

    private Long oddsid;

    private Long chekindid;

    private Long tpaysetid;

    private String orderno;

    private String accno;

    private String paycode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiredate;

    private String paytype;

    /**
     * 支付方式 描述
     */
    private String paytypedec;

    private Integer isinvoice;

    private String orderstatus;

    private String accountstatus;

    private String cancelreason;

    private String payimg;

    private String paywechat;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paydate;

    private String payuser;

    private String paynote;

    private String ordernote;

    private Integer isdelete;

    private String createuser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    private String modifyuser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifydate;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Long getMealid() {
        return mealid;
    }

    public void setMealid(Long mealid) {
        this.mealid = mealid;
    }

    public Long getBankid() {
        return bankid;
    }

    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public Long getLotkindid() {
        return lotkindid;
    }

    public void setLotkindid(Long lotkindid) {
        this.lotkindid = lotkindid;
    }

    public Long getSschistoryid() {
        return sschistoryid;
    }

    public void setSschistoryid(Long sschistoryid) {
        this.sschistoryid = sschistoryid;
    }

    public Long getOddsid() {
        return oddsid;
    }

    public void setOddsid(Long oddsid) {
        this.oddsid = oddsid;
    }

    public Long getChekindid() {
        return chekindid;
    }

    public void setChekindid(Long chekindid) {
        this.chekindid = chekindid;
    }

    public Long getTpaysetid() {
        return tpaysetid;
    }

    public void setTpaysetid(Long tpaysetid) {
        this.tpaysetid = tpaysetid;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public BigDecimal getOldamt() {
        return oldamt;
    }

    public void setOldamt(BigDecimal oldamt) {
        this.oldamt = oldamt;
    }

    public BigDecimal getSumamt() {
        return sumamt;
    }

    public void setSumamt(BigDecimal sumamt) {
        this.sumamt = sumamt;
    }

    public BigDecimal getRealamt() {
        return realamt;
    }

    public void setRealamt(BigDecimal realamt) {
        this.realamt = realamt;
    }

    public Integer getIsinvoice() {
        return isinvoice;
    }

    public void setIsinvoice(Integer isinvoice) {
        this.isinvoice = isinvoice;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getAccountstatus() {
        return accountstatus;
    }

    public void setAccountstatus(String accountstatus) {
        this.accountstatus = accountstatus;
    }

    public String getCancelreason() {
        return cancelreason;
    }

    public void setCancelreason(String cancelreason) {
        this.cancelreason = cancelreason;
    }

    public String getPayimg() {
        return payimg;
    }

    public void setPayimg(String payimg) {
        this.payimg = payimg;
    }

    public String getPaywechat() {
        return paywechat;
    }

    public void setPaywechat(String paywechat) {
        this.paywechat = paywechat;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
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

    public String getOrdernote() {
        return ordernote;
    }

    public void setOrdernote(String ordernote) {
        this.ordernote = ordernote;
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


    public String getMemaccount() {
        return memaccount;
    }

    public void setMemaccount(String memaccount) {
        this.memaccount = memaccount;
    }

    public String getOrdertypedec() {
        return ordertypedec;
    }

    public void setOrdertypedec(String ordertypedec) {
        this.ordertypedec = ordertypedec;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
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

    public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
    }

    public String getPaytypedec() {
        return paytypedec;
    }

    public void setPaytypedec(String paytypedec) {
        this.paytypedec = paytypedec;
    }
}
