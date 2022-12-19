package com.likes.common.model.dto.bas;

import com.likes.common.model.common.PageBaseInfo;

import java.util.Date;
import java.util.List;

public class BasAnchorroomReq extends PageBaseInfo {
    private Long roomid;

    private String accno;

    private String roomname;

    private String roomtheme;

    private Integer roomstatus;

    private Integer istalk;

    private String clientid;

    private Date onlinedate;

    private Date offlinedate;

    private Long channelid;

    private List<String> accnoList;

    /**
     * 所属家族姓名
     */
    private String familyname;
    /**
     * 主播昵称
     */
    private String nickname;
    /**
     * 主播账号（手机）
     */
    private String mobileno;

    public List<String> getAccnoList() {
        return accnoList;
    }

    public void setAccnoList(List<String> accnoList) {
        this.accnoList = accnoList;
    }

    public Long getChannelid() {
        return channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
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

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getRoomtheme() {
        return roomtheme;
    }

    public void setRoomtheme(String roomtheme) {
        this.roomtheme = roomtheme;
    }

    public Integer getRoomstatus() {
        return roomstatus;
    }

    public void setRoomstatus(Integer roomstatus) {
        this.roomstatus = roomstatus;
    }

    public Integer getIstalk() {
        return istalk;
    }

    public void setIstalk(Integer istalk) {
        this.istalk = istalk;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
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

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
}