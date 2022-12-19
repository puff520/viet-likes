package com.likes.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TraAnchorDetailDO {
    private String accno;

    private Long familyid;

    private Double giftincome = 0d;

    private Double betsincome = 0d;

    private Double allincome = 0d;

    private String nickname;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date onlinedate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date offlinedate;

    public Double getAllincome() {
        return allincome;
    }

    public void setAllincome(Double allincome) {
        this.allincome = allincome;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Long getFamilyid() {
        return familyid;
    }

    public void setFamilyid(Long familyid) {
        this.familyid = familyid;
    }

    public Double getGiftincome() {
        return giftincome;
    }

    public void setGiftincome(Double giftincome) {
        this.giftincome = giftincome;
    }

    public Double getBetsincome() {
        return betsincome;
    }

    public void setBetsincome(Double betsincome) {
        this.betsincome = betsincome;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getOnlinedate() {
        return onlinedate;
    }

    public void setOnlinedate(Date onlinedate) {
        this.onlinedate = onlinedate;
    }

    public Date getOfflinedate() {
        return offlinedate;
    }

    public void setOfflinedate(Date offlinedate) {
        this.offlinedate = offlinedate;
    }
}
