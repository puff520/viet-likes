package com.likes.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 代充人用户登录用户信息 继承LoginUser 扩展代充用户
 * 满足系统原有创建sessionid、适配各个系统的拦截器
 *
 * @author 瑞夫
 * @version 1.0
 * @date 2020/5/5
 */
public class LoginRepayUser extends LoginUser {

	private Long repaymemid;

	/**
	 * 代充人余额
	 */
	private BigDecimal memgold;

	/**
	 * 充值优惠(%)
	 */
	private BigDecimal discountrag;

	@JsonFormat(pattern = "HH:mm")
	private Date onlinedates;

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
}
