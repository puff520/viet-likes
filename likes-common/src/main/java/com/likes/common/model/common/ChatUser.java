package com.likes.common.model.common;

public class ChatUser {

    private String acctoken;
    private String accno;
    private String nickname;
    private String memlevel;
    private Integer logintype;
    private String stream;
    // 房间管理员 0是 9否
    private Integer ismanage;
    private Long roomid;
    // 客户端ip
    private String clientip;
    private String serverip;
    private String levelSvgaUrl;
    private String headimgurl;

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getAcctoken() {
        return acctoken;
    }

    public void setAcctoken(String acctoken) {
        this.acctoken = acctoken;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(String memlevel) {
        this.memlevel = memlevel;
    }

    public Integer getLogintype() {
        return logintype;
    }

    public void setLogintype(Integer logintype) {
        this.logintype = logintype;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public Integer getIsmanage() {
        return ismanage;
    }

    public void setIsmanage(Integer ismanage) {
        this.ismanage = ismanage;
    }

    public String getLevelSvgaUrl() {
        return levelSvgaUrl;
    }

    public void setLevelSvgaUrl(String levelSvgaUrl) {
        this.levelSvgaUrl = levelSvgaUrl;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder("{");
        info.append("\"acctoken\":").append("\"").append(acctoken).append("\"").append(",");
        info.append("\"accno\":").append("\"").append(accno).append("\"").append(",");
        info.append("\"nickname\":").append("\"").append(nickname).append("\"").append(",");
        info.append("\"memlevel\":").append("\"").append(memlevel).append("\"").append(",");
        info.append("\"logintype\":").append("\"").append(logintype).append("\"").append(",");
        info.append("\"stream\":").append("\"").append(stream).append("\"").append(",");
        info.append("\"ismanage\":").append("\"").append(ismanage).append("\"").append(",");
        info.append("\"roomid\":").append("\"").append(roomid).append("\"").append(",");
        info.append("\"clientip\":").append("\"").append(clientip).append("\"").append(",");
        info.append("\"serverip\":").append("\"").append(serverip).append("\"").append("}");
        info.append("\"levelSvgaUrl\":").append("\"").append(levelSvgaUrl).append("\"").append("}");
        return info.toString();
    }

}
