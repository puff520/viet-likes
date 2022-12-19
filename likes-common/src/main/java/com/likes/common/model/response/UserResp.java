package com.likes.common.model.response;

import com.likes.common.constant.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserResp {

    private Long onlineid;
    private Long memid;
    private String nickname;
    private String headimgurl;
    private Double goldnum;
    private Integer ranknum;
    private String headimg;
    private Integer sex;
    private String memlevel;
    private Integer ismanage;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;
    private String accno;
    private Integer isforbidden;
    private String refAccno;
    private Long roomid;

    public String getRefAccno() {
        return refAccno;
    }

    public void setRefAccno(String refAccno) {
        this.refAccno = refAccno;
    }

    public Double getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(Double goldnum) {
        this.goldnum = goldnum;
    }

    public Integer getIsforbidden() {
        return isforbidden;
    }

    public void setIsforbidden(Integer isforbidden) {
        this.isforbidden = isforbidden;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Long getOnlineid() {
        return onlineid;
    }

    public void setOnlineid(Long onlineid) {
        this.onlineid = onlineid;
    }

    public String getHeadimgurl() {
        return headimg;
    }

    public String getMemlevel() {
        return null == memlevel || "".equals(memlevel) ? Constants.LEVEL_ONE : memlevel;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getIsmanage() {
        return ismanage;
    }

    public void setIsmanage(Integer ismanage) {
        this.ismanage = ismanage;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Integer getRanknum() {
        return ranknum;
    }

    public void setRanknum(Integer ranknum) {
        this.ranknum = ranknum;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }
}
