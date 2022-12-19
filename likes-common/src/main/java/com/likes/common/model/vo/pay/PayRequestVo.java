package com.likes.common.model.vo.pay;

public class PayRequestVo {
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 用户标识
	 */
	private String accno;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 渠道方式
	 */
	private String payType;

	/**
	 * 金额
	 */
	private String price;

	/**
	 * 支付设置ID
	 */
	private String  paySetId;

	/**
	 * 方式类型  NETBANK 网银转账  WECHAT 微信收款  ALIPAY 支付宝支付
	 */
	private String wayType;

	/**
	 * 商户ID
	 */
	private Long	providerId;


	private String source;

	public String getPaySetId() {
		return paySetId;
	}

	public void setPaySetId(String paySetId) {
		this.paySetId = paySetId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getWayType() {
		return wayType;
	}

	public void setWayType(String wayType) {
		this.wayType = wayType;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
