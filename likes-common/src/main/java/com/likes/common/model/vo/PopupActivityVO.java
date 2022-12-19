package com.likes.common.model.vo;

import com.likes.common.model.vo.activity.ActivityVO;

import java.util.List;

public class PopupActivityVO {
    private Integer id;
    private Integer appId;
    private String appName;
    private Integer versionId;
    private String versionNumber;
    private Integer activityId;
    private String activityName;
    private String title;
    private Integer status;
    private String message;
    private String issueTime;
    private List<AppVO> appList;
    private List<AppVersionVO> appVersionList;
    private List<ActivityVO> activityList;

    public PopupActivityVO() {
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

    public Integer getActivityId() {
        return this.activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}

