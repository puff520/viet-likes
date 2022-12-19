package com.likes.common.model.dto.pay;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 代充人用户登录用户信息 响应对象
 * @author 瑞夫
 * @version 1.0
 * @date 2020/5/5
 */
public class LoginRepayUserDO {

	private String acctoken;
	private String accno;
	private String nickname;
	private String acclogin;
	private String clintipadd;

	private Long repaymemid;
	/**
	 * 代充人余额
	 */
	private BigDecimal memgold;

	/**
	 * 充值优惠(%)
	 */
	private BigDecimal discountrag;

	/**
	 * 开店时间
	 */
	@JsonFormat(pattern = "HH:mm")
	private Date onlinedates;

	/**
	 * 关店时间
	 */
	@JsonFormat(pattern = "HH:mm")
	private Date onlinedatee;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdate;

	public Long getRepaymemid() {
		return repaymemid;
	}

	public void setRepaymemid(Long repaymemid) {
		this.repaymemid = repaymemid;
	}

	public BigDecimal getMemgold() {
		return memgold;
	}

	public void setMemgold(BigDecimal memgold) {
		this.memgold = memgold;
	}

	public Date getOnlinedates() {
		return onlinedates;
	}

	public void setOnlinedates(Date onlinedates) {
		this.onlinedates = onlinedates;
	}

	public Date getOnlinedatee() {
		return onlinedatee;
	}

	public void setOnlinedatee(Date onlinedatee) {
		this.onlinedatee = onlinedatee;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public BigDecimal getDiscountrag() {
		return discountrag;
	}

	public void setDiscountrag(BigDecimal discountrag) {
		this.discountrag = discountrag;
	}

	public String getAcctoken() {
		return acctoken;
	}

	public void setAcctoken(String acctoken) {
		this.acctoken = acctoken;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAcclogin() {
		return acclogin;
	}

	public void setAcclogin(String acclogin) {
		this.acclogin = acclogin;
	}

	public String getClintipadd() {
		return clintipadd;
	}

	public void setClintipadd(String clintipadd) {
		this.clintipadd = clintipadd;
	}
}
