package com.likes.common.model.dto;

import com.likes.common.mybatis.entity.SysPayprovider;

public class MandatoryDO extends SysPayprovider {

	private Long tpaysetid;

	private String tpayname;

	private String paytype;

	private String paycode;

	private String payvalue;

	public Long getTpaysetid() {
		return tpaysetid;
	}

	public void setTpaysetid(Long tpaysetid) {
		this.tpaysetid = tpaysetid;
	}

	public String getTpayname() {
		return tpayname;
	}

	public void setTpayname(String tpayname) {
		this.tpayname = tpayname;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getPaycode() {
		return paycode;
	}

	public void setPaycode(String paycode) {
		this.paycode = paycode;
	}

	public String getPayvalue() {
		return payvalue;
	}

	public void setPayvalue(String payvalue) {
		this.payvalue = payvalue;
	}

}
