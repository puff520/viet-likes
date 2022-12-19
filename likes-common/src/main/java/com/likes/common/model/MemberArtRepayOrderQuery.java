package com.likes.common.model;

import java.util.Date;

public class MemberArtRepayOrderQuery {

    /**
     * 订单号
     */
    private String orderno;

    /**
     * 会员ID
     */
    private String memaccount;


    /**
     * 存提类型 0-存入 1-提出
     */
    private Integer ordertype;

    /**
     * 变动类型
     */
    private Integer changetype;
    
    /**
     * 起始时间
     */
    private Date startdate;

    /**
     * 结束时间
     */
    private Date enddate;

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getMemaccount() {
        return memaccount;
    }

    public void setMemaccount(String memaccount) {
        this.memaccount = memaccount;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

	public Integer getChangetype() {
		return changetype;
	}

	public void setChangetype(Integer changetype) {
		this.changetype = changetype;
	}

	public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
