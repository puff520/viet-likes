package com.likes.common.model.request;

import java.util.List;

public class FamilyStatisticsRequest {

    private List<String> anchorAccnoList;
    // 上次提现时间
    private String pretime;
    private String endtime;

    private String starttime;

    //主播accno
    private String accno;
    //List<Integer> changetypelist;

    //房间号
    private List<Long> roomidlist;
    private Long roomid;
    //订单类型
    private List<Integer> ordertypelist;

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public List<Long> getRoomidlist() {
        return roomidlist;
    }

    public void setRoomidlist(List<Long> roomidlist) {
        this.roomidlist = roomidlist;
    }

    public List<Integer> getOrdertypelist() {
        return ordertypelist;
    }

    public void setOrdertypelist(List<Integer> ordertypelist) {
        this.ordertypelist = ordertypelist;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

	/*public List<Integer> getChangetypelist() {
		return changetypelist;
	}
	
	public void setChangetypelist(List<Integer> changetypelist) {
		this.changetypelist = changetypelist;
	}*/

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

    public List<String> getAnchorAccnoList() {
        return anchorAccnoList;
    }

    public void setAnchorAccnoList(List<String> anchorAccnoList) {
        this.anchorAccnoList = anchorAccnoList;
    }

    public String getPretime() {
        return pretime;
    }

    public void setPretime(String pretime) {
        this.pretime = pretime;
    }

}
