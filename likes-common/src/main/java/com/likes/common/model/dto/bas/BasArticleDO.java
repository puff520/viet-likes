package com.likes.common.model.dto.bas;

import com.likes.common.mybatis.entity.BasArticle;

import java.util.List;
import java.util.Map;

public class BasArticleDO extends BasArticle {

    private String titlename;
    private Long memid;
    private String headimg;
    private String headimgurl;
    private Integer isme;
    private String nickname;
    private Integer commentnum;
    // 是否收藏
    private String isview;
    // 是否关注
    private String isattention;
    // 是否点赞
    private String ispraise;
    //分享
    private Integer isshare;

    private List<String> picidsList;

    private List<Map<String, Object>> picidsMapsList;

    private List<String> usertagsList;

    private List<Long> usertagidsList;

    private String videobofangurl;

    private String bdusername;

    public List<Map<String, Object>> getPicidsMapsList() {
        return picidsMapsList;
    }

    public void setPicidsMapsList(List<Map<String, Object>> picidsMapsList) {
        this.picidsMapsList = picidsMapsList;
    }

    public List<Long> getUsertagidsList() {
        return usertagidsList;
    }

    public void setUsertagidsList(List<Long> usertagidsList) {
        this.usertagidsList = usertagidsList;
    }

    public String getTitlename() {
        return titlename;
    }

    public void setTitlename(String titlename) {
        this.titlename = titlename;
    }

    public String getBdusername() {
        return bdusername;
    }

    public void setBdusername(String bdusername) {
        this.bdusername = bdusername;
    }

    public String getVideobofangurl() {
        return videobofangurl;
    }

    public void setVideobofangurl(String videobofangurl) {
        this.videobofangurl = videobofangurl;
    }

    public List<String> getPicidsList() {
        return picidsList;
    }

    public void setPicidsList(List<String> picidsList) {
        this.picidsList = picidsList;
    }

    public List<String> getUsertagsList() {
        return usertagsList;
    }

    public void setUsertagsList(List<String> usertagsList) {
        this.usertagsList = usertagsList;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getHeadimgurl() {
        if (null == headimgurl || "".equals(headimgurl)) {
            return headimg;
        }
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Integer getIsme() {
        return isme;
    }

    public void setIsme(Integer isme) {
        this.isme = isme;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }

    public String getIsview() {
        return isview;
    }

    public void setIsview(String isview) {
        this.isview = isview;
    }

    public String getIsattention() {
        return isattention;
    }

    public void setIsattention(String isattention) {
        this.isattention = isattention;
    }

    public String getIspraise() {
        return ispraise;
    }

    public void setIspraise(String ispraise) {
        this.ispraise = ispraise;
    }

    public Integer getIsshare() {
        return isshare;
    }

    public void setIsshare(Integer isshare) {
        this.isshare = isshare;
    }
}
