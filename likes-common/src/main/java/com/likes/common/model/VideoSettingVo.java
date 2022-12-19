package com.likes.common.model;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: VideoResult
 * Description: 推拉流设置vo
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/6/24 15:49
 */
public class VideoSettingVo {
    //房间id
    private Long roomid;
    //MD5加密key
    private String key;
    //编码方式 H264|H265
    private String encode;
    /**
     * {
     * '360':'T360',
     * '480':'T480',
     * '720':'T720'
     * }
     */
    private Map<String, String> templete;
    //http://domain/AppName
    private String url;
    //流名称
    private String stream;
    //视频后缀
    private String suffix;
    private long txTime;
    // 封面图
    private String cover;
    //业务参数
    private String busParams;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getEncode() {
        return null == encode || "".equals(encode.trim()) ? "h264" : encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public Map<String, String> getTemplete() {
        return null == templete ? new HashMap<>() : templete;
    }

    public void setTemplete(Map<String, String> templete) {
        this.templete = templete;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public long getTxTime() {
        return txTime;
    }

    public void setTxTime(long txTime) {
        this.txTime = txTime;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBusParams() {
        return busParams;
    }

    public void setBusParams(String busParams) {
        this.busParams = busParams;
    }
}
