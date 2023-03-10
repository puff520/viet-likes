package com.likes.common.model.vo.circle;

public class PostMemberVO {
    private Integer userId;    //用户id
    private String heads;   //头像
    private String nickname;    //昵称
    private Integer isFocus;    //是否关注  0否1是

    private Integer fansNumber;
    private Integer focusNumner;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHeads() {
        return heads;
    }

    public void setHeads(String heads) {
        this.heads = heads;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(Integer isFocus) {
        this.isFocus = isFocus;
    }

    public Integer getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(Integer fansNumber) {
        this.fansNumber = fansNumber;
    }

    public Integer getFocusNumner() {
        return focusNumner;
    }

    public void setFocusNumner(Integer focusNumner) {
        this.focusNumner = focusNumner;
    }
}
