package com.likes.common.model.dto.member;

import java.math.BigDecimal;

public class MemGoldchangeDTO {
    private Integer changetype;
    private BigDecimal quantity;
    private Integer type;
    private String opnote;
    private String prename;

    private  String createTime;

    public Integer getChangetype() {
        return changetype;
    }

    public void setChangetype(Integer changetype) {
        this.changetype = changetype;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOpnote() {
        return opnote;
    }

    public void setOpnote(String opnote) {
        this.opnote = opnote;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
