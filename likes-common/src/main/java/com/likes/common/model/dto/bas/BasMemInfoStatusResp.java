package com.likes.common.model.dto.bas;


/**
 * @ClassName BasMemInfoStatusResp
 * @Description 直播间会员异常状态列表(返回前端展示)
 * @Author ping
 * @Date 2020/6/23 17:51
 * @Version 1.0
 **/
public class BasMemInfoStatusResp {
    private Long id;
    private String nickname;
    private String accno;
    private String memname;
    private String displaystatus;
    private String startdate;
    private String enddate;
    private String roomname;
    private String anchoraccno;
    private String type;
    private String mobileno;
    private String updateuser;
    private String updatetime;
    private String uniqueId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMemname() {
        return memname;
    }

    public void setMemname(String memname) {
        this.memname = memname;
    }

    public String getDisplaystatus() {
        return displaystatus;
    }

    public void setDisplaystatus(String displaystatus) {
        this.displaystatus = displaystatus;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getAnchoraccno() {
        return anchoraccno;
    }

    public void setAnchoraccno(String anchoraccno) {
        this.anchoraccno = anchoraccno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}