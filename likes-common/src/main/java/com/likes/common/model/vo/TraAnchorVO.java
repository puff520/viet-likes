package com.likes.common.model.vo;

import java.math.BigDecimal;

public class TraAnchorVO {
    private String nickname;
    private String accno;
    private Long apycid;
    private Long familyid;
    private BigDecimal giftincome;
    private BigDecimal betsincome;
    private Long traanchorid;
    private Integer ordertype;

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public BigDecimal getBetsincome() {
        return betsincome;
    }

    public void setBetsincome(BigDecimal betsincome) {
        this.betsincome = betsincome;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Long getApycid() {
        return apycid;
    }

    public void setApycid(Long apycid) {
        this.apycid = apycid;
    }

    public Long getFamilyid() {
        return familyid;
    }

    public void setFamilyid(Long familyid) {
        this.familyid = familyid;
    }

    public BigDecimal getGiftincome() {
        return giftincome;
    }

    public void setGiftincome(BigDecimal giftincome) {
        this.giftincome = giftincome;
    }

    public Long getTraanchorid() {
        return traanchorid;
    }

    public void setTraanchorid(Long traanchorid) {
        this.traanchorid = traanchorid;
    }
}
