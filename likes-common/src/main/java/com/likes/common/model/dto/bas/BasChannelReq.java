package com.likes.common.model.dto.bas;

import com.likes.common.model.common.PageBaseInfo;

public class BasChannelReq extends PageBaseInfo {
    private Long channelid;

    private String channelname;

    private String channeldesc;

    private Integer roomnum;

    private Integer anchornum;

    private Integer sortby;

    public Long getChannelid() {
        return channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public String getChanneldesc() {
        return channeldesc;
    }

    public void setChanneldesc(String channeldesc) {
        this.channeldesc = channeldesc;
    }

    public Integer getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(Integer roomnum) {
        this.roomnum = roomnum;
    }

    public Integer getAnchornum() {
        return anchornum;
    }

    public void setAnchornum(Integer anchornum) {
        this.anchornum = anchornum;
    }

    public Integer getSortby() {
        return sortby;
    }

    public void setSortby(Integer sortby) {
        this.sortby = sortby;
    }

}