package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 顺旺支付回调
 * @author 瑞夫
 * @version 1.0
 * @date 2020/5/18
 */
@Scope("prototype")
@Component
public class ShunwangNotify extends Notify {

	String appId;
	String serviceCode;
	String version;
	String signType;
	String nonce;
	String timestamp;
	String sign;

	ShunwangNotifyData data;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public ShunwangNotifyData getData() {
		return data;
	}

	public void setData(ShunwangNotifyData data) {
		this.data = data;
	}

	public static class ShunwangNotifyData{
		/**
		 * 商户订单号
		 */
		String outOrderNo;
		/**
		 * 平台订单号
		 */
		String orderNo;
		String referNo;
		String memo;
		String status;
		String statusDesc;
		//yyyy-MM-dd HH:ss:mm
		String payedTime;
		//单位，分
		String amount;
		String extraFee;

		public String getOutOrderNo() {
			return outOrderNo;
		}

		public void setOutOrderNo(String outOrderNo) {
			this.outOrderNo = outOrderNo;
		}

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public String getReferNo() {
			return referNo;
		}

		public void setReferNo(String referNo) {
			this.referNo = referNo;
		}

		public String getMemo() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getStatusDesc() {
			return statusDesc;
		}

		public void setStatusDesc(String statusDesc) {
			this.statusDesc = statusDesc;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getExtraFee() {
			return extraFee;
		}

		public void setExtraFee(String extraFee) {
			this.extraFee = extraFee;
		}

		public String getPayedTime() {
			return payedTime;
		}

		public void setPayedTime(String payedTime) {
			this.payedTime = payedTime;
		}
	}
}

