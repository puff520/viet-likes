package com.likes.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AnchorDO {
    private String accno;
    private Long familyid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date onlinedate;
    private Integer roomstatus;
    private String nickname;
    private Long roomid;

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
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

    public Long getFamilyid() {
        return familyid;
    }

    public void setFamilyid(Long familyid) {
        this.familyid = familyid;
    }

    public Date getOnlinedate() {
        return onlinedate;
    }

    public void setOnlinedate(Date onlinedate) {
        this.onlinedate = onlinedate;
    }

    public Integer getRoomstatus() {
        return roomstatus;
    }

    public void setRoomstatus(Integer roomstatus) {
        this.roomstatus = roomstatus;
    }

}
