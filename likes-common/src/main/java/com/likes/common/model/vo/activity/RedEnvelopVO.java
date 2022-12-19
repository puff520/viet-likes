package com.likes.common.model.vo.activity;

import java.math.BigDecimal;

public class RedEnvelopVO {
	
	private String mebNickname;
	
	private BigDecimal receiveAmount;
	
	public String getMebNickname() {
		return mebNickname;
	}
	public void setMebNickname(String mebNickname) {
		this.mebNickname = mebNickname;
	}
	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}
	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}
}
