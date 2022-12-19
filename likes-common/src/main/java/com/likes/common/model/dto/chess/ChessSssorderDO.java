package com.likes.common.model.dto.chess;

import com.likes.common.mybatis.entity.ChessOrder;

import java.math.BigDecimal;

public class ChessSssorderDO extends ChessOrder {
    private String accloginid;
    private String acclogin;
    private String pchessname;
    private String chessname;
    private String agent;
    private BigDecimal allsumamt;
    private BigDecimal allwinamt;
    private String nickname;
    private String jiesuanname;
    private Long orderid;
    private String orderstatus;
    private String orderno;

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getJiesuanname() {
        return jiesuanname;
    }

    public void setJiesuanname(String jiesuanname) {
        this.jiesuanname = jiesuanname;
    }

    public String getAccloginid() {
        return accloginid;
    }

    public void setAccloginid(String accloginid) {
        this.accloginid = accloginid;
    }

    public String getPchessname() {
        return pchessname;
    }

    public void setPchessname(String pchessname) {
        this.pchessname = pchessname;
    }

    public String getChessname() {
        return chessname;
    }

    public void setChessname(String chessname) {
        this.chessname = chessname;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public BigDecimal getAllsumamt() {
        return allsumamt;
    }

    public void setAllsumamt(BigDecimal allsumamt) {
        this.allsumamt = allsumamt;
    }

    public BigDecimal getAllwinamt() {
        return allwinamt;
    }

    public void setAllwinamt(BigDecimal allwinamt) {
        this.allwinamt = allwinamt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
