package com.likes.common.model.dto;

public class VideoDO {

    /**
     * 视频id
     */
    private Long videoid;

    /**
     * 会员标识号
     */
    private String accno;

    /**
     * 视频名称
     */
    private String videoname;

    /**
     * 视频封面
     */
    private String videoimg;

    /**
     * 视频简介
     */
    private String videoinfo;

    /**
     * 作者名称
     */
    private String nickname;

    /**
     * 点赞数
     */
    private Integer praisenum;

    /**
     * 会员id
     */
    private Long memid;

    /**
     * 作者名称 与nickname重复
     */
    private String videoauthor;

    /**
     * 与nickname重复
     */
    private String createusername;

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
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

    public Integer getPraisenum() {
        return praisenum;
    }

    public void setPraisenum(Integer praisenum) {
        this.praisenum = praisenum;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getVideoid() {
        return videoid;
    }

    public void setVideoid(Long videoid) {
        this.videoid = videoid;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getVideoimg() {
        return videoimg;
    }

    public void setVideoimg(String videoimg) {
        this.videoimg = videoimg;
    }

    public String getVideoinfo() {
        return videoinfo;
    }

    public void setVideoinfo(String videoinfo) {
        this.videoinfo = videoinfo;
    }
}
