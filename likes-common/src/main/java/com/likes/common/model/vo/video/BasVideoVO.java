package com.likes.common.model.vo.video;

import com.likes.common.mybatis.entity.BasVideo;

public class BasVideoVO extends BasVideo {

    /**
     * 视频播放url
     */
    private String videobofangurl;

    /**
     * 视频封面url
     */
    private String videoimgurl;

    /**
     * 获取视频播放路径
     */
    private String shareurl;

    /**
     * 是否自己
     */
    private Integer isme;

    /**
     * 头像url
     */
    private String headimgurl;

    /**
     * 创建者accno
     */
    private String authoraccno;

    /**
     * 昵称
     */
    private String videoauthor;

    /**
     * memid
     */
    private Long memid;

    /**
     * 上一条视频id
     */
    private Long upvideoid;

    /**
     * 下一条视频id
     */
    private Long downvideoid;

    /**
     * 视频封面url
     */
    private String upvideoimgurl;

    /**
     * 上一条视频封面
     */
    private String downvideoimgurl;

    /**
     * isattention；是否已关注 0否 1是
     */
    private Integer isattention;

    /**
     * 是否已收藏 0否 1是
     */
    private Integer isview;

    /**
     * ispraise；是否已点赞 0否 1是
     */
    private Integer ispraise;

    public String getVideobofangurl() {
        return videobofangurl;
    }

    public void setVideobofangurl(String videobofangurl) {
        this.videobofangurl = videobofangurl;
    }

    public String getVideoimgurl() {
        return videoimgurl;
    }

    public void setVideoimgurl(String videoimgurl) {
        this.videoimgurl = videoimgurl;
    }

    public String getShareurl() {
        return shareurl;
    }

    public void setShareurl(String shareurl) {
        this.shareurl = shareurl;
    }

    public Integer getIsme() {
        return isme;
    }

    public void setIsme(Integer isme) {
        this.isme = isme;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getAuthoraccno() {
        return authoraccno;
    }

    public void setAuthoraccno(String authoraccno) {
        this.authoraccno = authoraccno;
    }

    public String getVideoauthor() {
        return videoauthor;
    }

    public void setVideoauthor(String videoauthor) {
        this.videoauthor = videoauthor;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public Long getUpvideoid() {
        return upvideoid;
    }

    public void setUpvideoid(Long upvideoid) {
        this.upvideoid = upvideoid;
    }

    public Long getDownvideoid() {
        return downvideoid;
    }

    public void setDownvideoid(Long downvideoid) {
        this.downvideoid = downvideoid;
    }

    public String getUpvideoimgurl() {
        return upvideoimgurl;
    }

    public void setUpvideoimgurl(String upvideoimgurl) {
        this.upvideoimgurl = upvideoimgurl;
    }

    public String getDownvideoimgurl() {
        return downvideoimgurl;
    }

    public void setDownvideoimgurl(String downvideoimgurl) {
        this.downvideoimgurl = downvideoimgurl;
    }

    public Integer getIsattention() {
        return isattention;
    }

    public void setIsattention(Integer isattention) {
        this.isattention = isattention;
    }

    public Integer getIsview() {
        return isview;
    }

    public void setIsview(Integer isview) {
        this.isview = isview;
    }

    public Integer getIspraise() {
        return ispraise;
    }

    public void setIspraise(Integer ispraise) {
        this.ispraise = ispraise;
    }
}
