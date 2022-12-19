package com.likes.common.model.vo.activity;

import java.util.List;

/**
 * @Description  首页中间广告页面展示实体类
 * @Author HANS
 * @Date 2020/01/09 19:26
 * @Version 1.0
 **/
public class AdMinPhotoPageVO {
    private List<AdMinCpTypeVO> cpTypeList;
    private String moveUrl;
    private Integer aid;
    private String aphoto;
    private Integer iid;
    private String iphoto;
    private Integer wid;
    private String wphoto;
    private Integer hid;
    private String hphoto;

    public List<AdMinCpTypeVO> getCpTypeList() {
        return cpTypeList;
    }

    public void setCpTypeList(List<AdMinCpTypeVO> cpTypeList) {
        this.cpTypeList = cpTypeList;
    }

    public String getMoveUrl() {
        return moveUrl;
    }

    public void setMoveUrl(String moveUrl) {
        this.moveUrl = moveUrl;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAphoto() {
        return aphoto;
    }

    public void setAphoto(String aphoto) {
        this.aphoto = aphoto;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getIphoto() {
        return iphoto;
    }

    public void setIphoto(String iphoto) {
        this.iphoto = iphoto;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public String getWphoto() {
        return wphoto;
    }

    public void setWphoto(String wphoto) {
        this.wphoto = wphoto;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public String getHphoto() {
        return hphoto;
    }

    public void setHphoto(String hphoto) {
        this.hphoto = hphoto;
    }
}
