package com.likes.common.model.dto.order;

import com.likes.common.mybatis.entity.KyBetOrder;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class KyBetOrderDTO extends KyBetOrder {

    private static final long serialVersionUID = 1L;

    private String account;
    private Integer memberId;
    private String kyAccount;
    private String gameName;
    private String serverName;
    private String nickname;
    private String mobileno;
    private String uniqueId;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT-6"
    )
    private Date gameStartTime;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getKyAccount() {
        return kyAccount;
    }

    public void setKyAccount(String kyAccount) {
        this.kyAccount = kyAccount;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public Date getGameStartTime() {
        return gameStartTime;
    }

    @Override
    public void setGameStartTime(Date gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
