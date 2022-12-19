package com.likes.common.model.dto.sys;

import java.util.Date;
import java.util.List;

/**
 * 系统参数
 * 
 * @author abu
 *
 */
public class SysBusparameterDto {
	private Long busparamid;

	private String busparamcode;

	private String pbusparamcode;

	private String busparamname;

	private String remark;

	private Integer status;

	private Integer sortby;

	private Integer isdelete;

	private String createuser;

	private Date createdate;

	private String modifyuser;

	private Date modifydate;

	private List<SysBusparameterDto> children;

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

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getModifyuser() {
		return modifyuser;
	}

	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public List<SysBusparameterDto> getChildren() {
		return children;
	}

	public void setChildren(List<SysBusparameterDto> children) {
		this.children = children;
	}

}