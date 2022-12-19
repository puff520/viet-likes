package com.likes.common.model.dto.pay;

import java.math.BigDecimal;

public class RepayuserDO {

	private String nickname;

	private String qq;

	private String webchat;

	private String alipay;

	private BigDecimal memgold;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWebchat() {
		return webchat;
	}

	public void setWebchat(String webchat) {
		this.webchat = webchat;
	}

	public String getAlipay() {
		return alipay;
	}

	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

	public BigDecimal getMemgold() {
		return memgold;
	}

	public void setMemgold(BigDecimal memgold) {
		this.memgold = memgold;
	}

}
