package com.likes.common.model.request;

import com.likes.common.model.common.PageBaseInfo;

import java.util.Date;
import java.util.List;

public class ArticleRequest extends PageBaseInfo {
    private Long articleid;
    private String acctoken;
    private Long memid;
    private String accno;
    private String title;
    private String introduction;
    private Integer arttype;
    private String comment;
    private Long parcommid;

    //是否关注
    private Integer isattention;
    //收藏
    private Integer isview;
    //点赞
    private Integer ispraise;
    //分享
    private Integer isshare;

    private Long commid;
    //排序
    private Integer sortby;

    private Integer checkstatus;

    /**
     * 用于删除
     */
    private List<Long> articleids;

    private String modifyuser;
    private Date modifydate;


    public Integer getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Integer checkstatus) {
        this.checkstatus = checkstatus;
    }

    public Integer getArttype() {
        return arttype;
    }

    public void setArttype(Integer arttype) {
        this.arttype = arttype;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public List<Long> getArticleids() {
        return articleids;
    }

    public void setArticleids(List<Long> articleids) {
        this.articleids = articleids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }


    public Long getArticleid() {
        return articleid;
    }

    public void setArticleid(Long articleid) {
        this.articleid = articleid;
    }

    public String getAcctoken() {
        return acctoken;
    }

    public void setAcctoken(String acctoken) {
        this.acctoken = acctoken;
    }

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getParcommid() {
        return parcommid;
    }

    public void setParcommid(Long parcommid) {
        this.parcommid = parcommid;
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

    public Long getCommid() {
        return commid;
    }

    public void setCommid(Long commid) {
        this.commid = commid;
    }

    public Integer getSortby() {
        return sortby;
    }

    public void setSortby(Integer sortby) {
        this.sortby = sortby;
    }
}
