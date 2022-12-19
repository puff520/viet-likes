package com.likes.common.model.dto.chess;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ChessKaiYuanResponse {

    private String orderno;
    private String orderstatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paydate;
    private Double sumamt;
    private Double winamt;
    private String chename;
    private Integer status;
    private String parchename;
    private String paycode;
    private String gameid;

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public Double getSumamt() {
        return sumamt;
    }

    public void setSumamt(Double sumamt) {
        this.sumamt = sumamt;
    }

    public Double getWinamt() {
        return winamt;
    }

    public void setWinamt(Double winamt) {
        this.winamt = winamt;
    }

    public String getChename() {
        return chename;
    }

    public void setChename(String chename) {
        this.chename = chename;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getParchename() {
        return parchename;
    }

    public void setParchename(String parchename) {
        this.parchename = parchename;
    }

}
