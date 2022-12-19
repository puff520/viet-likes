package com.likes.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CommentDO {

	private Long articleid;
	private String comment;
	private Long commid;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdate;
	private Integer praisenum;
	private String picids;
	private String headimg;
	private String nickname;

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getArticleid() {
		return articleid;
	}

	public void setArticleid(Long articleid) {
		this.articleid = articleid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getCommid() {
		return commid;
	}

	public void setCommid(Long commid) {
		this.commid = commid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getPraisenum() {
		return praisenum;
	}

	public void setPraisenum(Integer praisenum) {
		this.praisenum = praisenum;
	}

	public String getPicids() {
		return picids;
	}

	public void setPicids(String picids) {
		this.picids = picids;
	}

}
