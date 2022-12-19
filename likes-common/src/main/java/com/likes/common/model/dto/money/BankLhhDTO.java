package com.likes.common.model.dto.money;

public class BankLhhDTO {

	private String error_code;//错误码
	private String reason;//错误原因
	private BankLhhResult result;//

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BankLhhResult getResult() {
		return result;
	}

	public void setResult(BankLhhResult result) {
		this.result = result;
	}

}
