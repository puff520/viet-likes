package com.likes.common.model.dto.bas;

import com.likes.common.mybatis.entity.BasInvestors;

public class BasInvestorsDO extends BasInvestors {
	private String investlogurl;

	private String createusername;

	public String getCreateusername() {
		return createusername;
	}

	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	public String getInvestlogurl() {
		return investlogurl;
	}

	public void setInvestlogurl(String investlogurl) {
		this.investlogurl = investlogurl;
	}

}
