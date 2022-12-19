package com.likes.common.model.dto.order;


import com.likes.common.mybatis.entity.AeBetOrder;

public class AeBetOrderDTO extends AeBetOrder {

    private static final long serialVersionUID = 1L;

    private String account;
    private Integer memberId;
    private String aeAccount;
    private String gameName;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    private String roomName;

    public String getAccount() {
        return account;
    }

    public String getAeAccount() {
        return aeAccount;
    }

    public void setAeAccount(String aeAccount) {
        this.aeAccount = aeAccount;
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

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

}
