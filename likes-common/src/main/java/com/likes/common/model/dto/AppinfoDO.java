package com.likes.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AppinfoDO {
    private String appname;

    private String version;

    private String appsize;

    private String versionname;

    private String versioninfo;

    private Integer isnew;

    private String appdownurl;

    private Integer is_force;

    private Integer app_type;

    private Integer isPublishedtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishedtime;

    private String[] versioninfolist;

    public String getAppsize() {
        return appsize;
    }

    public void setAppsize(String appsize) {
        this.appsize = appsize;
    }

    public String[] getVersioninfolist() {
        return versioninfolist;
    }

    public void setVersioninfolist(String[] versioninfolist) {
        this.versioninfolist = versioninfolist;
    }

    public Date getPublishedtime() {
        return publishedtime;
    }

    public void setPublishedtime(Date publishedtime) {
        this.publishedtime = publishedtime;
    }

    public Integer getIsPublishedtime() {
        return isPublishedtime;
    }

    public void setIsPublishedtime(Integer isPublishedtime) {
        this.isPublishedtime = isPublishedtime;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getVersioninfo() {
        return versioninfo;
    }

    public void setVersioninfo(String versioninfo) {
        this.versioninfo = versioninfo;
    }

    public Integer getIsnew() {
        return isnew;
    }

    public void setIsnew(Integer isnew) {
        this.isnew = isnew;
    }

    public String getAppdownurl() {
        return appdownurl;
    }

    public void setAppdownurl(String appdownurl) {
        this.appdownurl = appdownurl;
    }

    public Integer getIs_force() {
        return is_force;
    }

    public void setIs_force(Integer is_force) {
        this.is_force = is_force;
    }

    public Integer getApp_type() {
        return app_type;
    }

    public void setApp_type(Integer app_type) {
        this.app_type = app_type;
    }


}
