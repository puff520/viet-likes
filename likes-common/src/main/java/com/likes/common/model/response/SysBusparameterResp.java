package com.likes.common.model.response;

import java.util.List;

/**
 * abu
 */
public class SysBusparameterResp {
	private Long busparamid;

	private String busparamcode;

	private String pbusparamcode;

	private String busparamname;

	private String remark;

	private Integer status;

	private Integer sortby;

	private List<SysBusparameterResp> children;

	public Long getBusparamid() {
		return busparamid;
	}

	public void setBusparamid(Long busparamid) {
		this.busparamid = busparamid;
	}

	public String getBusparamcode() {
		return busparamcode;
	}

	public void setBusparamcode(String busparamcode) {
		this.busparamcode = busparamcode;
	}

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

	public Integer getSortby() {
		return sortby;
	}

	public void setSortby(Integer sortby) {
		this.sortby = sortby;
	}

	public List<SysBusparameterResp> getChildren() {
		return children;
	}

	public void setChildren(List<SysBusparameterResp> children) {
		this.children = children;
	}
}