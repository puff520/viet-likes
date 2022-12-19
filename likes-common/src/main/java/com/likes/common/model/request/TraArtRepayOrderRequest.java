package com.likes.common.model.request;

import java.math.BigDecimal;

public class TraArtRepayOrderRequest {

    /**
     * 代充人账号
     */
    private String acclogin;

    /**
     * 存提类型 0-存入 1-提出
     */
    private Integer ordertype;

    /**
     * 存提金额
     */
    private BigDecimal optamt;

    /**
     * 备注
     */
    private String note;

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public BigDecimal getOptamt() {
        return optamt;
    }

    public void setOptamt(BigDecimal optamt) {
        this.optamt = optamt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
