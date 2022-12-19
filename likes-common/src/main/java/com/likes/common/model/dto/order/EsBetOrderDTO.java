package com.likes.common.model.dto.order;

import com.likes.common.mybatis.entity.EsBetOrder;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EsBetOrderDTO extends EsBetOrder {
	
	private static final long serialVersionUID = 1L;
	
	private String account;
	private Integer memberId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT-6")
	private Date settlementTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT-6")
	private Date createTime;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Override
	public Date getSettlementTime() {
		return settlementTime;
	}

	@Override
	public void setSettlementTime(Date settlementTime) {
		this.settlementTime = settlementTime;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
