package com.likes.common.model.dto.money;

import com.likes.common.model.common.PageBaseInfo;

import java.util.Date;

public class PaymentDTO extends PageBaseInfo {

	private Date date;

	private Integer userId;

	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
