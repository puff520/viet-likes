package com.likes.common.model.dto.chess;

public class ChessOrderStatisticsResponse {
    private Double allsumamt;
    private Double allwinamt;
    private String nickname;
    private String chename;
    private String account;
    private String channelid;
    private String accno;
    private Long parchekindid;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getChename() {
        return chename;
    }

    public void setChename(String chename) {
        this.chename = chename;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Long getParchekindid() {
        return parchekindid;
    }

    public void setParchekindid(Long parchekindid) {
        this.parchekindid = parchekindid;
    }

}
