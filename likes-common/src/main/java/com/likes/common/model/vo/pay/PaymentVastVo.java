package com.likes.common.model.vo.pay;

import java.math.BigDecimal;

public class PaymentVastVo {

	private BigDecimal price;// 价格
	private String orderNo;// 商户自定义订单号
	private String flowNo;// 流水号

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
