package com.likes.common.model.response;

import java.math.BigDecimal;
import java.util.Date;

public class SysAgentinfoResp {
	private Long agentid;

	private String agentname;

	private BigDecimal minamt;

	private BigDecimal maxamt;

	private BigDecimal commission;

	private Integer sortby;

	private Integer isdelete;

	private String createuser;

	private Date createdate;

	private String modifyuser;

	private Date modifydate;

	public Long getAgentid() {
		return agentid;
	}

	public void setAgentid(Long agentid) {
		this.agentid = agentid;
	}

	public String getAgentname() {
		return agentname;
	}

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public BigDecimal getMinamt() {
		return minamt;
	}

	public void setMinamt(BigDecimal minamt) {
		this.minamt = minamt;
	}

	public BigDecimal getMaxamt() {
		return maxamt;
	}

	public void setMaxamt(BigDecimal maxamt) {
		this.maxamt = maxamt;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
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
}