package com.likes.common.model.request;

public class StatisticRequest {

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 查询类型，多个以英文,分隔
     */
    private String type;

    /**
     * 用户名
     */
    private String nickname;

    /**
     * 今天=1,昨天=2,本周=3,上周=4,上月=5，全部=6，本月=7
     */
    private Integer dateType;

    /**
     * 用户唯一id
     */
    private String uniqueId;

    /**
     * accno(用户报表-详情页查询用)
     */
    private String accno;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }
}
