package com.likes.common.model.request;

import java.math.BigDecimal;

/**
 * 代充人充值请求
 *
 * @author 瑞夫
 * @version 1.0
 * @Description
 * @date 2020/5/6
 **/
public class RepayRechargeReq {

    /**
     * 汇款姓名
     */
    private String payuser;

    /**
     * 汇款备注
     */
    private String paynote;

    /**
     * 汇款银行
     */
    private String bank;

    /**
     * 汇款银行标识
     */
    private String bankCode;

    /**
     * 充值类型 3.银联
     */
    private Integer rechargetype;
    /**
     * 充值金额,元
     */
    private BigDecimal amount;

    /**
     * 实际到账金额,元
     */
    private BigDecimal realAmount;

    /**
     * 收款账户id
     */
    private Long bankid;

    /**
     * 会员标识号
     */
    private String accno;

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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public Integer getRechargetype() {
        return rechargetype;
    }

    public void setRechargetype(Integer rechargetype) {
        this.rechargetype = rechargetype;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public Long getBankid() {
        return bankid;
    }

    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }
}
