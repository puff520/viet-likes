package com.likes.common.model.dto.chess;

public class ChessRequest {

    private String accno;
    // 开始和结束时间
    private String starttime;
    private String endtime;
    private Long fisrstId;
    private Long secondId;
    private String searchname;
    private Integer ordertype;
    private String orderstatus;
    private Long chekindid;
    private Long parchekindid;
    private String orderno;
    private Long orderid;
    private Integer type;
    private Long parentid;
    private String KindID;
    private Double chessgoldnum;

    public String getKindID() {
        return KindID;
    }

    public void setKindID(String kindID) {
        KindID = kindID;
    }

    public Double getChessgoldnum() {
        return chessgoldnum;
    }

    public void setChessgoldnum(Double chessgoldnum) {
        this.chessgoldnum = chessgoldnum;
    }

    public Integer getAutomatic() {
        return automatic;
    }

    public void setAutomatic(Integer automatic) {
        this.automatic = automatic;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    //自动余额转换    1 打开 0 关闭
    Integer automatic;

    //1 =今天 ；2 =昨天；  3 =7天
    Integer day;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Long getParchekindid() {
        return parchekindid;
    }

    public void setParchekindid(Long parchekindid) {
        this.parchekindid = parchekindid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Long getChekindid() {
        return chekindid;
    }

    public void setChekindid(Long chekindid) {
        this.chekindid = chekindid;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
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

    public Long getFisrstId() {
        return fisrstId;
    }

    public void setFisrstId(Long fisrstId) {
        this.fisrstId = fisrstId;
    }

    public Long getSecondId() {
        return secondId;
    }

    public void setSecondId(Long secondId) {
        this.secondId = secondId;
    }

    public String getSearchname() {
        return searchname;
    }

    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }
}
