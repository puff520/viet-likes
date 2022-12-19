package com.likes.common.model.dto.chess;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ChessOrderDO {

    private Long cheorderid;
    private String chessname;
    private String orderno;
    private Long orderid;
    private Long chekindid;
    private Long kindid;
    private Long channelid;
    private String gameid;
    private String account;
    private BigDecimal sumamt;
    private Integer status;
    private BigDecimal winamt;
    private BigDecimal revenue;
    // private String buttonnote;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startdate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stopdate;

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Long getCheorderid() {
        return cheorderid;
    }

    public void setCheorderid(Long cheorderid) {
        this.cheorderid = cheorderid;
    }

    public String getChessname() {
        return chessname;
    }

    public void setChessname(String chessname) {
        this.chessname = chessname;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Long getChekindid() {
        return chekindid;
    }

    public void setChekindid(Long chekindid) {
        this.chekindid = chekindid;
    }

    public Long getKindid() {
        return kindid;
    }

    public void setKindid(Long kindid) {
        this.kindid = kindid;
    }

    public Long getChannelid() {
        return channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getSumamt() {
        return sumamt;
    }

    public void setSumamt(BigDecimal sumamt) {
        this.sumamt = sumamt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getWinamt() {
        return winamt;
    }

    public void setWinamt(BigDecimal winamt) {
        this.winamt = winamt;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getStopdate() {
        return stopdate;
    }

    public void setStopdate(Date stopdate) {
        this.stopdate = stopdate;
    }

}
