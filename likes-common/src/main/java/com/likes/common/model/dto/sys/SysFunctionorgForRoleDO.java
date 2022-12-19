package com.likes.common.model.dto.sys;

import com.likes.common.mybatis.entity.SysFunctionorg;

public class SysFunctionorgForRoleDO extends SysFunctionorg {
	private Integer checkbox;
	private Long sysroleid;

	public Integer getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(Integer checkbox) {
		this.checkbox = checkbox;
	}

	public Long getSysroleid() {
		return sysroleid;
	}

	public void setSysroleid(Long sysroleid) {
		this.sysroleid = sysroleid;
	}

}
