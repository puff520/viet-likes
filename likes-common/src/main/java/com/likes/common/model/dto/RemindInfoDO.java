package com.likes.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RemindInfoDO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date senddate;

    private String remindtxt;

    private Integer msgnum;

    private String nickname;

    private String mobileno;

    private Long rmdid;

    public Long getRmdid() {
        return rmdid;
    }

    public void setRmdid(Long rmdid) {
        this.rmdid = rmdid;
    }

    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    public String getRemindtxt() {
        return remindtxt;
    }

    public void setRemindtxt(String remindtxt) {
        this.remindtxt = remindtxt;
    }

    public Integer getMsgnum() {
        return msgnum;
    }

    public void setMsgnum(Integer msgnum) {
        this.msgnum = msgnum;
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
