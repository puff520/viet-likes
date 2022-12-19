package com.likes.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class TraAnchorDO {

    private String nickname;
    private String accno;
    private Long apycid;
    private Long anconlineid;
    private Long familyid;
    private Double giftincome;
    private Double betsincome;
    private Double allincome;
    private Long traanchorid;

    public Long getTraanchorid() {
        return traanchorid;
    }

    public void setTraanchorid(Long traanchorid) {
        this.traanchorid = traanchorid;
    }

    public List<TraAnchorDetailDO> getList() {
        return list;
    }

    public void setList(List<TraAnchorDetailDO> list) {
        this.list = list;
    }

    private List<TraAnchorDetailDO> list;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date onlinedate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date offlinedate;

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

    public Double getGiftincome() {
        return giftincome;
    }

    public Double getBetsincome() {
        return betsincome;
    }

    public Double getAllincome() {
        return allincome;
    }

    public void setAllincome(Double allincome) {
        this.allincome = allincome;
    }

    public void setGiftincome(Double giftincome) {
        this.giftincome = giftincome;
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

    public Long getAnconlineid() {
        return anconlineid;
    }

    public void setAnconlineid(Long anconlineid) {
        this.anconlineid = anconlineid;
    }

    public Long getFamilyid() {
        return familyid;
    }

    public void setFamilyid(Long familyid) {
        this.familyid = familyid;
    }


}
