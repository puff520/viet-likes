package com.likes.common.model.dto.member;

import java.math.BigDecimal;

public class MemberCountDTO {
    private Integer isAdd;

    public Integer getAdd() {
        return isAdd;
    }

    public void setAdd(Integer add) {
        isAdd = add;
    }

    private BigDecimal musttaskprice;
    private Integer id;
    private String level;
    private BigDecimal rechargeamount;
    private Integer memnum;

    public BigDecimal getMusttaskprice() {
        return musttaskprice;
    }

    public void setMusttaskprice(BigDecimal musttaskprice) {
        this.musttaskprice = musttaskprice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public BigDecimal getRechargeamount() {
        return rechargeamount;
    }

    public void setRechargeamount(BigDecimal rechargeamount) {
        this.rechargeamount = rechargeamount;
    }

    public Integer getMemnum() {
        return memnum;
    }

    public void setMemnum(Integer memnum) {
        this.memnum = memnum;
    }
}
