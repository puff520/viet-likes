package com.likes.common.model.vo.finance;

import java.io.Serializable;
import java.math.BigDecimal;

public class FinanceMemBaseVo  implements Serializable {
    private String mobileno;

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    /**
     * 会员标识
     */
    private String accno;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 会员ID
     */
    private String uniqueId;
    /**
     * 所属代理昵称
     */
    private String relaname;
    /**
     * 会员等级
     */
    private String memlevel;
    /**
     * 总金额
     */
    private BigDecimal goldnum;

    /**
     * 备注
     */
    private String remark;
    private String email;
    /**
     * 状态
     */
    private Integer accstatus;
    /**
     * 累计充值金额
     */
    private BigDecimal payAmount;
    /**
     * 累计提现金额
     */
    private BigDecimal withdrawalAmount;

    /**
     * 当前不可提金额
     */
    private BigDecimal noWithdrawalAmount;
    /**
     * 累计消费金额
     */
    private BigDecimal consumeAmount;

    public BigDecimal getNoWithdrawalAmount() {
        return noWithdrawalAmount;
    }

    public void setNoWithdrawalAmount(BigDecimal noWithdrawalAmount) {
        this.noWithdrawalAmount = noWithdrawalAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getRelaname() {
        return relaname;
    }

    public void setRelaname(String relaname) {
        this.relaname = relaname;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public BigDecimal getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(BigDecimal goldnum) {
        this.goldnum = goldnum;
    }

    public Integer getAccstatus() {
        return accstatus;
    }

    public void setAccstatus(Integer accstatus) {
        this.accstatus = accstatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
