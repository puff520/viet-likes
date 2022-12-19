package com.likes.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author abu
 * @Description
 * @revise
 * @time 2018年3月13日 上午9:55:35
 * @version 1.0
 * @copyright Copyright @2017, Co., Ltd. All right.
 */
public class BusParam {

	private Long busparid;

	private String busparcode;

	private String parbusparcode;

	private String busparname;

	private String busparpvalue;

	private String buspardesc;

	private Integer sortby;

	private Integer isdelete;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdate;
	private String createuser;
	private Date modifydate;
	private String modifyuser;

	private List<BusParam> children = new ArrayList<BusParam>();

	public Long getBusparid() {
		return busparid;
	}

	public void setBusparid(Long busparid) {
		this.busparid = busparid;
	}

	public String getBusparcode() {
		return busparcode;
	}

	public void setBusparcode(String busparcode) {
		this.busparcode = busparcode;
	}

	public String getParbusparcode() {
		return parbusparcode;
	}

	public void setParbusparcode(String parbusparcode) {
		this.parbusparcode = parbusparcode;
	}

	public String getBusparname() {
		return busparname;
	}

	public void setBusparname(String busparname) {
		this.busparname = busparname;
	}

	public String getBusparpvalue() {
		return busparpvalue;
	}

	public void setBusparpvalue(String busparpvalue) {
		this.busparpvalue = busparpvalue;
	}

	public String getBuspardesc() {
		return buspardesc;
	}

	public void setBuspardesc(String buspardesc) {
		this.buspardesc = buspardesc;
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

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public String getModifyuser() {
		return modifyuser;
	}

	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}

	public List<BusParam> getChildren() {
		return children;
	}

	public void setChildren(List<BusParam> children) {
		this.children = children;
	}

}
