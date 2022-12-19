package com.likes.common.model.request;

import java.math.BigDecimal;

/**
 * 代充人 充值请求
 * @author 瑞夫
 * @version 1.0
 * @Description
 * @date 2020/5/5
 **/
public class RepayuserRechargeReq {
    /**
     * 会员唯一标识
     */
   private String accno;

    /**
     * 充值方式,目前仅支持银行卡转账 3
     */
   private Long rechargetype;

    /**
     * 充值金额,元
     */
   private BigDecimal amount;

    /**
     * 实际到账金额,元
     */
   private BigDecimal realAmount;

    /**
     * 汇款银行
     */
   private String bank;

    /**
     * 汇款银行标识
     */
    private String bankCode;

    /**
     * 汇款姓名
     */
   private String payuser;

    /**
     * 汇款备注
     */
   private String paynote;

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Long getRechargetype() {
        return rechargetype;
    }

    public void setRechargetype(Long rechargetype) {
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
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

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}