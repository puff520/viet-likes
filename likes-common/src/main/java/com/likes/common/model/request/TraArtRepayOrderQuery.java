package com.likes.common.model.request;

import java.util.Date;

public class TraArtRepayOrderQuery {

    /**
     * 订单号
     */
    private String orderno;

    /**
     * 代充人账号
     */
    private String acclogin;

    /**
     * 暱称
     */
    private String nickname;

    /**
     * 存提类型 0-存入 1-提出
     */
    private Integer ordertype;

    /**
     * 起始时间
     */
    private Date starttime;

    /**
     * 结束时间
     */
    private Date endtime;

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getAcclogin() {
        return acclogin;
    }

    public void setAcclogin(String acclogin) {
        this.acclogin = acclogin;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}
