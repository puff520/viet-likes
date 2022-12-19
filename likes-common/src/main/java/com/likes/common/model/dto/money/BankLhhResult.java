package com.likes.common.model.dto.money;

import java.util.List;

public class BankLhhResult {

	private BankLhhPaging paging;
	private List<BankLhh> list;

	public BankLhhPaging getPaging() {
		return paging;
	}

	public void setPaging(BankLhhPaging paging) {
		this.paging = paging;
	}

	public List<BankLhh> getList() {
		return list;
	}

	public void setList(List<BankLhh> list) {
		this.list = list;
	}

}
