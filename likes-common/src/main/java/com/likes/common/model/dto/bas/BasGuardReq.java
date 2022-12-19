package com.likes.common.model.dto.bas;

import com.likes.common.model.common.PageBaseInfo;

public class BasGuardReq extends PageBaseInfo {

    private Long guardid;

    private Long roomid;

    private String accno;

    private Long refid;

    private String nickname;

    public Long getGuardid() {
        return guardid;
    }

    public void setGuardid(Long guardid) {
        this.guardid = guardid;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Long getRefid() {
        return refid;
    }

    public void setRefid(Long refid) {
        this.refid = refid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}