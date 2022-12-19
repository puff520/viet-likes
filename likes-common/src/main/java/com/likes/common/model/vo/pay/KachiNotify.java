package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 苹果支付回调
 * @author 瑞夫
 * @version 1.0
 * @date 2020/7/27
 */
@Scope("prototype")
@Component
public class KachiNotify extends Notify {

	private String status;

	private String orderno;

	private String money;

	private String merorderno;

	private String time;

	private String attach;

	private String sign;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getMerorderno() {
		return merorderno;
	}

	public void setMerorderno(String merorderno) {
		this.merorderno = merorderno;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}

