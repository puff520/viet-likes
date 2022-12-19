package com.likes.common.model.dto.chess;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class ChessOrderResponse {
    private Long orderid;
    private String orderno;
    private String orderstatus;
    private String ordername;
    private Double allsumamt;
    private Double allwinamt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gamestartdate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gamestopdate;
    private List<ChessOrderDO> chesslist;

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
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

    public Double getAllsumamt() {
        return allsumamt;
    }

    public void setAllsumamt(Double allsumamt) {
        this.allsumamt = allsumamt;
    }

    public Double getAllwinamt() {
        return allwinamt;
    }

    public void setAllwinamt(Double allwinamt) {
        this.allwinamt = allwinamt;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public Date getGamestartdate() {
        return gamestartdate;
    }

    public void setGamestartdate(Date gamestartdate) {
        this.gamestartdate = gamestartdate;
    }

    public Date getGamestopdate() {
        return gamestopdate;
    }

    public void setGamestopdate(Date gamestopdate) {
        this.gamestopdate = gamestopdate;
    }

    public List<ChessOrderDO> getChesslist() {
        return chesslist;
    }

    public void setChesslist(List<ChessOrderDO> chesslist) {
        this.chesslist = chesslist;
    }

}
