package com.likes.common.model.request;

import java.util.List;

public class RepayListRequest {
	//private Long mealid;
	private String payuser;
	private String paynote;
	private Long bankid;
	// 充值金额  前端传200.0 会直接400，所以改为String 接受参数
	private String money;
	private Integer moneyInt;
	// 充值类型 1.银联 2.人工充值 3 支付宝 4微信充值
	private Integer rechargetype;
	//账号类型  1支付宝 2微信   3银联
	private Integer accounttype;
	//用户等级
	private String memlevel;
	//商家
	private String shangjia;
	//支付方式
	private String paytype;
	//支持商家
	List<String> support;
	//是否手动输入 0 是 9 不是 用于前端 便于区分
	Integer isenter;
	//是否允许输入金额  0允许 9禁止 
	Integer isinput;
	
	
	//跳转的查询页面 查看是否支付成功和失败
	Long orderid;
	
	Integer num = 8;
	
	public Integer getMoneyInt() {
		return moneyInt;
	}

	public void setMoneyInt(Integer moneyInt) {
		this.moneyInt = moneyInt;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public Integer getIsinput() {
		return isinput;
	}

	public void setIsinput(Integer isinput) {
		this.isinput = isinput;
	}

	public Integer getIsenter() {
		return isenter;
	}

	public void setIsenter(Integer isenter) {
		this.isenter = isenter;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getShangjia() {
		return shangjia;
	}

	public void setShangjia(String shangjia) {
		this.shangjia = shangjia;
	}

	public List<String> getSupport() {
		return support;
	}

	public void setSupport(List<String> support) {
		this.support = support;
	}

	public Integer getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(Integer accounttype) {
		this.accounttype = accounttype;
	}

	public String getMemlevel() {
		return memlevel;
	}

	public void setMemlevel(String memlevel) {
		this.memlevel = memlevel;
	}

	public Integer getRechargetype() {
		return rechargetype;
	}

	public void setRechargetype(Integer rechargetype) {
		this.rechargetype = rechargetype;
	}

	/*public Long getMealid() {
		return mealid;
	}
	
	public void setMealid(Long mealid) {
		this.mealid = mealid;
	}*/

	public String getPayuser() {
		return payuser;
	}

	public void setPayuser(String payuser) {
		this.payuser = payuser;
	}

	public String getPaynote() {
		return paynote;
	}

	public void setPaynote(String paynote) {
		this.paynote = paynote;
	}

	public Long getBankid() {
		return bankid;
	}

	public void setBankid(Long bankid) {
		this.bankid = bankid;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

}
