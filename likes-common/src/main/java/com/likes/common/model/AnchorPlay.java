package com.likes.common.model;

/**
 * @author 阿布 直播主体bean
 */
public class AnchorPlay {

    // 直播地址/聊天
    private String playurl;
    private String key;
    // 串流秘钥
    private String stream;
    // 封面图
    private String cover;
    //临时提供给ios调试
    private String iosUrl;

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
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
