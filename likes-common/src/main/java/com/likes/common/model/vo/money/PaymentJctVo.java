package com.likes.common.model.vo.money;

/**
 * @Author admin
 * @Description //聚财厅vo
 * @Date 2019/11/12 15:55
 * @Param
 * @return
 **/
public class PaymentJctVo {

	private Integer merchat_id;//商户id
	private String order_sn;// 订单号
	private Integer amount;//金额
	private String content;//商户自定义字符串
	private Integer payway;//支付方式
	private Integer finished_time;//支付成功时间戳
	private Integer status;// 支付状态，1成功，0失败
	private String sign;//签名字符串

	public Integer getMerchat_id() {
		return merchat_id;
	}

	public void setMerchat_id(Integer merchat_id) {
		this.merchat_id = merchat_id;
	}

	public String getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPayway() {
		return payway;
	}

	public void setPayway(Integer payway) {
		this.payway = payway;
	}

	public Integer getFinished_time() {
		return finished_time;
	}

	public void setFinished_time(Integer finished_time) {
		this.finished_time = finished_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
