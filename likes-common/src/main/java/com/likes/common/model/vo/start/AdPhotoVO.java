package com.likes.common.model.vo.start;

/**
 * @author lzy
 * @create 2018-09-07 17:17
 **/
public class AdPhotoVO {

    private Integer id;

    private String photo; // 图片路径

    private String url; // 跳转url

    private String title;

    /**
     * 说明: 有效开始时间
     */
    private String startTime;

    /**
     * 说明: 有效结束时间
     */
    private String endTime;

    private Integer sort;

    private String status;

    /**
     * ,跳转ID活动
     */
    private Integer targetId;

    /**
     * 说明: -1，使用原有的跳转url，0，前端原生界面跳转，1,跳转ID活动
     */
    private Integer targetType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }
}
