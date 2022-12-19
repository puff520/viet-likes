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
public class PingguoNotify extends Notify {

	String appid;
	String params;

	PingguoNotifyData data;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public PingguoNotifyData getData() {
		return data;
	}

	public void setData(PingguoNotifyData data) {
		this.data = data;
	}

	public static class PingguoNotifyData {
		String wm_callbacks;
		String wm_appid;
		String wm_pay_type;
		String wm_success_url;
		String wm_error_url;
		String wm_out_trade_no;
		String wm_amount;
		String wm_sign;
		String wm_amount_true;
		String wm_out_uid;

		public String getWm_callbacks() {
			return wm_callbacks;
		}

		public void setWm_callbacks(String wm_callbacks) {
			this.wm_callbacks = wm_callbacks;
		}

		public String getWm_appid() {
			return wm_appid;
		}

		public void setWm_appid(String wm_appid) {
			this.wm_appid = wm_appid;
		}

		public String getWm_pay_type() {
			return wm_pay_type;
		}

		public void setWm_pay_type(String wm_pay_type) {
			this.wm_pay_type = wm_pay_type;
		}

		public String getWm_success_url() {
			return wm_success_url;
		}

		public void setWm_success_url(String wm_success_url) {
			this.wm_success_url = wm_success_url;
		}

		public String getWm_error_url() {
			return wm_error_url;
		}

		public void setWm_error_url(String wm_error_url) {
			this.wm_error_url = wm_error_url;
		}

		public String getWm_out_trade_no() {
			return wm_out_trade_no;
		}

		public void setWm_out_trade_no(String wm_out_trade_no) {
			this.wm_out_trade_no = wm_out_trade_no;
		}

		public String getWm_amount() {
			return wm_amount;
		}

		public void setWm_amount(String wm_amount) {
			this.wm_amount = wm_amount;
		}

		public String getWm_sign() {
			return wm_sign;
		}

		public void setWm_sign(String wm_sign) {
			this.wm_sign = wm_sign;
		}

		public String getWm_amount_true() {
			return wm_amount_true;
		}

		public void setWm_amount_true(String wm_amount_true) {
			this.wm_amount_true = wm_amount_true;
		}

		public String getWm_out_uid() {
			return wm_out_uid;
		}

		public void setWm_out_uid(String wm_out_uid) {
			this.wm_out_uid = wm_out_uid;
		}

		@Override
		public String toString() {
			return "PingguoNotifyData{" +
					"wm_callbacks='" + wm_callbacks + '\'' +
					", wm_appid='" + wm_appid + '\'' +
					", wm_pay_type='" + wm_pay_type + '\'' +
					", wm_success_url='" + wm_success_url + '\'' +
					", wm_error_url='" + wm_error_url + '\'' +
					", wm_out_trade_no='" + wm_out_trade_no + '\'' +
					", wm_amount='" + wm_amount + '\'' +
					", wm_sign='" + wm_sign + '\'' +
					", wm_amount_true='" + wm_amount_true + '\'' +
					", wm_out_uid='" + wm_out_uid + '\'' +
					'}';
		}
	}
}

