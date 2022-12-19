package com.likes.common.model.request;

import java.util.Date;
import java.util.List;

public class UserReq {
    private Long roomid;
    private Integer limitnum;
    private String starttime;
    private String endtime;
    private List<Integer> changetypeList;
    private String accno;
    private Long memid;
    private Integer type;
    private Integer forbidtype;
    private Integer changetype;
    private List<String> accnoLists;
    private String onlinedate;
    private String offlinedate;
    private String refAccno;

    public String getRefAccno() {
        return refAccno;
    }

    public void setRefAccno(String refAccno) {
        this.refAccno = refAccno;
    }

    public String getOnlinedate() {
        return onlinedate;
    }

    public void setOnlinedate(String onlinedate) {
        this.onlinedate = onlinedate;
    }

    public String getOfflinedate() {
        return offlinedate;
    }

    public void setOfflinedate(String offlinedate) {
        this.offlinedate = offlinedate;
    }

    public List<String> getAccnoLists() {
        return accnoLists;
    }

    public void setAccnoLists(List<String> accnoLists) {
        this.accnoLists = accnoLists;
    }

    public Integer getChangetype() {
        return changetype;
    }

    public void setChangetype(Integer changetype) {
        this.changetype = changetype;
    }

    public Integer getForbidtype() {
        return forbidtype;
    }

    public void setForbidtype(Integer forbidtype) {
        this.forbidtype = forbidtype;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public Integer getLimitnum() {
        return limitnum;
    }

    public void setLimitnum(Integer limitnum) {
        this.limitnum = limitnum;
    }

    public List<Integer> getChangetypeList() {
        return changetypeList;
    }

    public void setChangetypeList(List<Integer> changetypeList) {
        this.changetypeList = changetypeList;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

}
