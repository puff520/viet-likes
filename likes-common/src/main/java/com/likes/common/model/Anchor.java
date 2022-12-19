package com.likes.common.model;

/**
 * @author 阿布
 * 直播主体bean
 */
public class Anchor {

    //直播地址
    private String tcUrl;
    //串流秘钥
    private String stream;
    private String key;
    private Long roomid;
    //ios调试用，后面删除
    private String iosUrl;

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getTcUrl() {
        return tcUrl;
    }

    public void setTcUrl(String tcUrl) {
        this.tcUrl = tcUrl;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIosUrl() {
        return iosUrl;
    }

    public void setIosUrl(String iosUrl) {
        this.iosUrl = iosUrl;
    }
}
