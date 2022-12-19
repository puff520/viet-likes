package com.likes.common.model.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 阿布 代理人信息
 */
public class MemAgentResp {

    private String accno;

    private String nickname;
    // 推荐码
    private String recomcode;
    // 下级人数(第一级)
    private Long memnums;
    // 下级充值总金额
    private BigDecimal chargeamt;
    // 抽成金额
    private BigDecimal reverseamt;
    // 充值时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    /**
     * 首次充值金额(代理报表查看下级使用)
     */
    private BigDecimal payFirst;

    /**
     * 个人累计提现金额(代理报表查看下级使用)
     */
    private BigDecimal withdrawalTotal;

    /**
     * 最后登录时间(代理报表查看下级使用)
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastlogindate;

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    private String headimg;

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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

    public BigDecimal getChargeamt() {
        return chargeamt;
    }

    public void setChargeamt(BigDecimal chargeamt) {
        this.chargeamt = chargeamt;
    }

    public BigDecimal getReverseamt() {
        return reverseamt;
    }

    public void setReverseamt(BigDecimal reverseamt) {
        this.reverseamt = reverseamt;
    }

    public BigDecimal getPayFirst() {
        return payFirst;
    }

    public void setPayFirst(BigDecimal payFirst) {
        this.payFirst = payFirst;
    }

    public BigDecimal getWithdrawalTotal() {
        return withdrawalTotal;
    }

    public void setWithdrawalTotal(BigDecimal withdrawalTotal) {
        this.withdrawalTotal = withdrawalTotal;
    }

    public Date getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }
}
