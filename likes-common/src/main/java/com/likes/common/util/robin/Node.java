package com.likes.common.util.robin;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 阿布 服务器属性bean
 */
public class Node {
    // 服务器id
    private Long tliveid;
    // 初始权重 （保持不变）
    private int weight;
    // 服务名
    private String servername;
    // 服务器地址
    private String serverurl;
    // 服务器区域
    private String region;
    // 当前权重
    private int currentWeight;

    private String primarykey;

    private String backupkey;

    public static String DEFAULT_VIDEO_ENCODE = "h265";

    public Node() {
    }

    public Node(String servername, String serverurl, String primarykey, int weight) {
        this.weight = weight;
        this.servername = servername;
        this.serverurl = serverurl;
        this.primarykey = primarykey;
    }

    public Node(String servername, String serverurl, String primarykey, int weight, Long tliveid) {
        this.weight = weight;
        this.servername = servername;
        this.serverurl = serverurl;
        this.primarykey = primarykey;
        this.tliveid = tliveid;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public String[] getUrlSuffix() {
        if (null == serverurl || "".equals(serverurl.trim())) {
            return new String[]{"", ""};
        }
        String url = this.serverurl;
        String suffix = "";
        String[] urlArray = serverurl.split("@");
        if (urlArray.length == 2) {
            url = urlArray[0];
            suffix = urlArray[1];
        }
        return new String[]{url, suffix};
    }

    public Long getTliveid() {
        return tliveid;
    }

    public void setTliveid(Long tliveid) {
        this.tliveid = tliveid;
    }

    public int getWeight() {
        return weight;
    }

    public String getServername() {
        return servername;
    }

    public String getServerurl() {
        return serverurl;
    }

    public String getRegion() {
        return region;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public void setServerurl(String serverurl) {
        this.serverurl = serverurl;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey;
    }

    public String getBackupkey() {
        return backupkey;
    }

    public void setBackupkey(String backupkey) {
        this.backupkey = backupkey;
    }
}