package com.likes.common.model.vo.start;

/**
 * @Description 首页中间广告实体类
 * @Author HANS
 * @Date 2020/01/09 19:26
 * @Version 1.0
 **/
public class AdMinPhotoVO {
    private Integer id;
    private String photo;
    private Integer openFlag;
    private Integer categoryId;
    private String moveType;
    private String moveUrl;

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

    public Integer getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(Integer openFlag) {
        this.openFlag = openFlag;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    public String getMoveUrl() {
        return moveUrl;
    }

    public void setMoveUrl(String moveUrl) {
        this.moveUrl = moveUrl;
    }
}
