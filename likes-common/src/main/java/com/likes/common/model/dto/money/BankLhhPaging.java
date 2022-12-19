package com.likes.common.model.dto.money;

public class BankLhhPaging {

	private int total;//总数
	private int page_total;//总页
	private int page_size;//页码数量
	private int page_now;//当前页

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage_total() {
		return page_total;
	}

	public void setPage_total(int page_total) {
		this.page_total = page_total;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	public int getPage_now() {
		return page_now;
	}

	public void setPage_now(int page_now) {
		this.page_now = page_now;
	}

}
