package com.likes.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TraRepayOrderDO {

    /**
     * 代充订单id
     */
    private Long reorderid;

    /**
     * 订单号
     */
    private String orderno;

    /**
     * 会员ID
     */
    private String memaccount;

    /**
     * 暱称
     */
    private String nickname;

    /**
     * 代充账号
     */
    private String acclogin;

    /**
     * 入款金额
     */
    private BigDecimal realamt;

    /**
     * 状态
     */
    private String orderstatus;

    /**
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    public Long getReorderid() {
        return reorderid;
    }

    public void setReorderid(Long reorderid) {
        this.reorderid = reorderid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }


    public String getMemaccount() {
        return memaccount;
    }

    public void setMemaccount(String memaccount) {
        this.memaccount = memaccount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public BigDecimal getRealamt() {
        return realamt;
    }

    public void setRealamt(BigDecimal realamt) {
        this.realamt = realamt;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
