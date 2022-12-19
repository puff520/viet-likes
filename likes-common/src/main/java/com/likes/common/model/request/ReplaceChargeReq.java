package com.likes.common.model.request;

import java.math.BigDecimal;

/**
 * 代充人 代充请求
 * @author 瑞夫
 * @version 1.0
 * @Description
 * @date 2020/5/6
 **/
public class ReplaceChargeReq {

    /**
     * 被充值人账号id membaseinfo unique_id
     */
    private String memaccount;

    /**
     * 充值金额
     */
    private BigDecimal amount;

    /**
     * 代充人会员标识
     */
    private String accno;
    /**
     * 代充人账号
     */
    private String acclogin;

    /**
     *  来源
     */
    private String source;

    private Long levelSeq;

    private Long levelId;

    public String getMemaccount() {
        return memaccount;
    }

    public void setMemaccount(String memaccount) {
        this.memaccount = memaccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getLevelSeq() {
        return levelSeq;
    }

    public void setLevelSeq(Long levelSeq) {
        this.levelSeq = levelSeq;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }
}
