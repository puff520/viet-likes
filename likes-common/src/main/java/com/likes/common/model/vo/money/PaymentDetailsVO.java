package com.likes.common.model.vo.money;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

public class PaymentDetailsVO {

	/**
	 * 时间列表
	 */
	private JSONObject timeList;

	/**
	 * 订单金额
	 */
	private BigDecimal amount;

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 充值类型
	 */
	private Integer type;

	/**
	 * 充值状态
	 */
	private Integer status;

	/**
	 * 实际金额
	 */
	private BigDecimal actualAmount;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 附言
	 */
	private Integer postScript;

	public Integer getPostScript() {
		return postScript;
	}

	public void setPostScript(Integer postScript) {
		this.postScript = postScript;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public JSONObject getTimeList() {
		return timeList;
	}

	public void setTimeList(JSONObject timeList) {
		this.timeList = timeList;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
