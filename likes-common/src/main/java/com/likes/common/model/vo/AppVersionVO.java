package com.likes.common.model.vo;

public class AppVersionVO {
    private Integer id;
    private Integer appId;
    private String andriodName;
    private String iosName;
    private String number;
    private Double andriodSize;
    private Double iosSize;
    private String andriodDownUrl;
    private String iosDownUrl;
    private Integer noticeStatus;
    private String message;

    public AppVersionVO() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return this.appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getAndriodSize() {
        return this.andriodSize;
    }

    public void setAndriodSize(Double andriodSize) {
        this.andriodSize = andriodSize;
    }

    public Double getIosSize() {
        return this.iosSize;
    }

    public void setIosSize(Double iosSize) {
        this.iosSize = iosSize;
    }

    public Integer getNoticeStatus() {
        return this.noticeStatus;
    }

    public void setNoticeStatus(Integer noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAndriodName() {
        return this.andriodName;
    }

    public void setAndriodName(String andriodName) {
        this.andriodName = andriodName;
    }

    public String getIosName() {
        return this.iosName;
    }

    public void setIosName(String iosName) {
        this.iosName = iosName;
    }

    public String getAndriodDownUrl() {
        return this.andriodDownUrl;
    }

    public void setAndriodDownUrl(String andriodDownUrl) {
        this.andriodDownUrl = andriodDownUrl;
    }

    public String getIosDownUrl() {
        return this.iosDownUrl;
    }

    public void setIosDownUrl(String iosDownUrl) {
        this.iosDownUrl = iosDownUrl;
    }
}

