/**
 *
 */
package com.likes.common.model;

/**
 * 
 * @author abu
 * @Description
 * @revise
 * @time 2017年5月27日 下午3:38:17
 * @version 1.0
 */
public class NetPreCondition {
	private String pbusparamcode;

	private String busparamname;

	private String busparamcode;

	private String remark;

	private Integer status;// 启用状态0启用9未启用

	public String getPbusparamcode() {
		return pbusparamcode;
	}

	public void setPbusparamcode(String pbusparamcode) {
		this.pbusparamcode = pbusparamcode;
	}

	public String getBusparamname() {
		return busparamname;
	}

	public void setBusparamname(String busparamname) {
		this.busparamname = busparamname;
	}

	public String getBusparamcode() {
		return busparamcode;
	}

	public void setBusparamcode(String busparamcode) {
		this.busparamcode = busparamcode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
