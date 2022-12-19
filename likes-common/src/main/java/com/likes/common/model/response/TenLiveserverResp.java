package com.likes.common.model.response;

import com.likes.common.util.robin.Node;

import java.util.List;

/**
 * @author 阿布
 */
public class TenLiveserverResp {

    // 初始权重 （保持不变）
    private int weight;
    // 服务名
    private String servername;
    // 服务器地址
    private String serverurl;
    // 服务器区域
    private String region;
    // 服务器id
    private Long tliveid;

    private List<Node> children;

    public TenLiveserverResp() {
        super();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public String getServerurl() {
        return serverurl;
    }

    public void setServerurl(String serverurl) {
        this.serverurl = serverurl;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Long getTliveid() {
        return tliveid;
    }

    public void setTliveid(Long tliveid) {
        this.tliveid = tliveid;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

}