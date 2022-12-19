package com.likes.common.model.dto.pay;

public class ThreePayMapDO {
	//支付商code
	String channeltype;
	//支付路径
	String posturl;
	//支付上id
	String merchantid;
	//支付通道
	String paytype;
	//支付设置
	String payvalue;
	//支付商密钥
	String apisecret;
	//公钥
	String pubsecret;
	//私钥
	String prisecret;
	//本地订单号
	String orderno;
	//充值金额
	String payprice;
	//充值姓名
	String userName;
	//返回地址
	String returnurl;
	//回调地址
	String notifyurl;
	//
	String proname;
	//备注
	String mark;
	//查询地址
	String torderurl;
	//用户memid
	String memid;
	//客户端ip
	String ip;
	//会员标识
	String accno;

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}

	public String getTorderurl() {
		return torderurl;
	}

	public void setTorderurl(String torderurl) {
		this.torderurl = torderurl;
	}

	public String getChanneltype() {
		return channeltype;
	}

	public void setChanneltype(String channeltype) {
		this.channeltype = channeltype;
	}

	public String getPosturl() {
		return posturl;
	}

	public void setPosturl(String posturl) {
		this.posturl = posturl;
	}

	public String getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getPayvalue() {
		return payvalue;
	}

	public void setPayvalue(String payvalue) {
		this.payvalue = payvalue;
	}

	public String getApisecret() {
		return apisecret;
	}

	public void setApisecret(String apisecret) {
		this.apisecret = apisecret;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getPayprice() {
		return payprice;
	}

	public void setPayprice(String payprice) {
		this.payprice = payprice;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReturnurl() {
		return returnurl;
	}

	public void setReturnurl(String returnurl) {
		this.returnurl = returnurl;
	}

	public String getNotifyurl() {
		return notifyurl;
	}

	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getPrisecret() {
		return prisecret;
	}

	public void setPrisecret(String prisecret) {
		this.prisecret = prisecret;
	}

	public String getPubsecret() {
		return pubsecret;
	}

	public void setPubsecret(String pubsecret) {
		this.pubsecret = pubsecret;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}
}
