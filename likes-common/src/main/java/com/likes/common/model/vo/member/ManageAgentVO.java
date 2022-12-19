package com.likes.common.model.vo.member;

import java.math.BigDecimal;

public class ManageAgentVO {

    private Long memid;

    private String accno;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 推荐码
     */
    private String recomcode;

    /**
     * 下级人数(第一级)
     */
    private Long memnums;

    /**
     * 累计充值人数
     */
    private BigDecimal payUserTotal;

    /**
     * 下级充值总金额
     */
    private BigDecimal chargeamt;

    /**
     * 代理下的所有人的累计充值金额
     */
    private BigDecimal withdrawalTotal;

    /**
     * 抽成金额
     */
    private BigDecimal reverseamt;

    /**
     * 代理跳转url
     */
    private String proxyUrl;

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

    public String getRecomcode() {
        return recomcode;
    }

    public void setRecomcode(String recomcode) {
        this.recomcode = recomcode;
    }

    public Long getMemnums() {
        return memnums;
    }

    public void setMemnums(Long memnums) {
        this.memnums = memnums;
    }

    public BigDecimal getPayUserTotal() {
        return payUserTotal;
    }

    public void setPayUserTotal(BigDecimal payUserTotal) {
        this.payUserTotal = payUserTotal;
    }

    public BigDecimal getChargeamt() {
        return chargeamt;
    }

    public void setChargeamt(BigDecimal chargeamt) {
        this.chargeamt = chargeamt;
    }

    public BigDecimal getWithdrawalTotal() {
        return withdrawalTotal;
    }

    public void setWithdrawalTotal(BigDecimal withdrawalTotal) {
        this.withdrawalTotal = withdrawalTotal;
    }

    public BigDecimal getReverseamt() {
        return reverseamt;
    }

    public void setReverseamt(BigDecimal reverseamt) {
        this.reverseamt = reverseamt;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public String getProxyUrl() {
        return proxyUrl;
    }

    public void setProxyUrl(String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }
}
