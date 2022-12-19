package com.likes.common.model.vo;


import com.likes.common.model.vo.activity.ActivityVO;
import com.likes.common.model.vo.start.AppNoticeVO;

import java.util.List;

public class SlideshowVO {
    private Integer id;
    private Integer appId;
    private String appName;
    private Integer versionId;
    private String versionNumber;
    private String name;
    private Integer type;
    private Integer jumpUrl;
    private String photoUrl;
    private Integer sort;
    private String issueTime;
    private List<AppVO> appList;
    private List<AppVersionVO> appVersionList;
    private List<ActivityVO> activityList;
    private List<AppNoticeVO> appNoticeList;

    public SlideshowVO() {
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

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getVersionId() {
        return this.versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public String getVersionNumber() {
        return this.versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getJumpUrl() {
        return this.jumpUrl;
    }

    public void setJumpUrl(Integer jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIssueTime() {
        return this.issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public List<AppVO> getAppList() {
        return this.appList;
    }

    public void setAppList(List<AppVO> appList) {
        this.appList = appList;
    }

    public List<AppVersionVO> getAppVersionList() {
        return this.appVersionList;
    }

    public void setAppVersionList(List<AppVersionVO> appVersionList) {
        this.appVersionList = appVersionList;
    }

    public List<ActivityVO> getActivityList() {
        return this.activityList;
    }

    public void setActivityList(List<ActivityVO> activityList) {
        this.activityList = activityList;
    }

    public List<AppNoticeVO> getAppNoticeList() {
        return this.appNoticeList;
    }

    public void setAppNoticeList(List<AppNoticeVO> appNoticeList) {
        this.appNoticeList = appNoticeList;
    }
}

