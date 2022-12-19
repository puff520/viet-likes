package com.likes.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TraArtRepayOrderDO {

    /**
     * 人工存提id
     */
    private Long artorderid;

    /**
     * 订单号
     */
    private String orderno;

    /**
     * 代充账号
     */
    private String acclogin;

    /**
     * 暱称
     */
    private String nickname;

    /**
     * 存提类型 0-存入 1-提出
     */
    private String ordertype;

    /**
     * 存提金额
     */
    private BigDecimal optamt;

    /**
     * 处理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    /**
     * 处理者
     */
    private String createuser;

    /**
     * 备注
     */
    private String note;

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

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public BigDecimal getOptamt() {
        return optamt;
    }

    public void setOptamt(BigDecimal optamt) {
        this.optamt = optamt;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
