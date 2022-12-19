package com.likes.common.model.request;

import com.likes.common.model.common.PageBaseInfo;

import java.util.Date;
import java.util.List;

public class VideoRequest extends PageBaseInfo {
    private Long videoid;
    private List<Integer> videoidlist;
    private String videoname;
    private String videoinfo;
    private String accno;
    private Long videosize;
    private String videoimg;

    private String updateUser;
    private Date updateTime;

    private Long commid;
    private Long memid;
    private Long articleid;

    //是否关注
    private Integer isattention;
    //收藏
    private Integer isview;
    //点赞
    private Integer ispraise;
    //分享
    private Integer isshare;
    //作者accno
    private String authorAccno;

    private Integer checkstatus;
    private Integer goldnum;

    public Long getCommid() {
        return commid;
    }

    public void setCommid(Long commid) {
        this.commid = commid;
    }

    public Long getArticleid() {
        return articleid;
    }

    public void setArticleid(Long articleid) {
        this.articleid = articleid;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public String getAuthorAccno() {
        return authorAccno;
    }

    public void setAuthorAccno(String authorAccno) {
        this.authorAccno = authorAccno;
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

    public Integer getIsshare() {
        return isshare;
    }

    public void setIsshare(Integer isshare) {
        this.isshare = isshare;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getVideoid() {
        return videoid;
    }

    public void setVideoid(Long videoid) {
        this.videoid = videoid;
    }

    public List<Integer> getVideoidlist() {
        return videoidlist;
    }

    public void setVideoidlist(List<Integer> videoidlist) {
        this.videoidlist = videoidlist;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getVideoinfo() {
        return videoinfo;
    }

    public void setVideoinfo(String videoinfo) {
        this.videoinfo = videoinfo;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Long getVideosize() {
        return videosize;
    }

    public void setVideosize(Long videosize) {
        this.videosize = videosize;
    }

    public String getVideoimg() {
        return videoimg;
    }

    public void setVideoimg(String videoimg) {
        this.videoimg = videoimg;
    }

    public Integer getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Integer checkstatus) {
        this.checkstatus = checkstatus;
    }

    public Integer getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(Integer goldnum) {
        this.goldnum = goldnum;
    }
}
