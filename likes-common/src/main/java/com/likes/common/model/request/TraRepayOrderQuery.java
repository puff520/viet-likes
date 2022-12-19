package com.likes.common.model.request;

import java.util.Date;

public class TraRepayOrderQuery {

    /**
     * 会员ID
     */
    private String memaccount;

    /**
     * 暱称
     */
    private String nickname;

    /**
     * 代充账号
     */
    private String acclogin;

    /**
     * 订单号
     */
    private String orderno;

    /**
     * 起始时间
     */
    private Date startdate;

    /**
     * 结束时间
     */
    private Date enddate;


    public String getMemaccount() {
        return memaccount;
    }

    public void setMemaccount(String memaccount) {
        this.memaccount = memaccount;
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

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
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
