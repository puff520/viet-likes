package com.likes.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class MemberArtRepayOrderDO {

    /**
     * 人工存提id
     */
    private Long artorderid;

    /**
     * 订单号
     */
    private String orderno;

    /**
     * 会员ID
     */
    private Long memaccount;

    /**
     * 存入/提出事由
     */
    private String note;

    /**
     * 打码倍数
     */
    private BigDecimal auditper;

    /**
     * 存入/提出金额
     */
    private BigDecimal optamt;

    /**
     * 会员账号余额
     */
    private BigDecimal goldnum;

    /**
     * 处理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    /**
     * 处理者
     */
    private String createuser;

    public Long getArtorderid() {
        return artorderid;
    }

    public void setArtorderid(Long artorderid) {
        this.artorderid = artorderid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Long getMemaccount() {
        return memaccount;
    }

    public void setMemaccount(Long memaccount) {
        this.memaccount = memaccount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getAuditper() {
        return auditper;
    }

    public void setAuditper(BigDecimal auditper) {
        this.auditper = auditper;
    }

    public BigDecimal getOptamt() {
        return optamt;
    }

    public void setOptamt(BigDecimal optamt) {
        this.optamt = optamt;
    }

    public BigDecimal getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(BigDecimal goldnum) {
        this.goldnum = goldnum;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }
}
