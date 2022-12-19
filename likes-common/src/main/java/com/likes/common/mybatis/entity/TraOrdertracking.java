package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class TraOrdertracking implements Serializable {
    /**
     * 字段: tra_ordertracking.trackid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 追踪id
     *
     * @mbggenerated
     */
    private Long trackid;

    /**
     * 字段: tra_ordertracking.orderid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 订单id
     *
     * @mbggenerated
     */
    private Long orderid;

    /**
     * 字段: tra_ordertracking.trackdate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 处理时间
     *
     * @mbggenerated
     */
    private Date trackdate;

    /**
     * 字段: tra_ordertracking.trackbody<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 处理内容
     *
     * @mbggenerated
     */
    private String trackbody;

    /**
     * 字段: tra_ordertracking.operuse<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 操作人
     *
     * @mbggenerated
     */
    private String operuse;

    /**
     * 字段: tra_ordertracking.orderstatus<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 订单状态 ord01新订单 ord04已审核待付款  ord08已付款待评价 ord12已发货 ord16已完成（已收货） ord10已评价 ord99已取消  ord07退款中  ord11已退款  
     *
     * @mbggenerated
     */
    private String orderstatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tra_ordertracking
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return tra_ordertracking.trackid: 追踪id
     *
     * @mbggenerated
     */
    public Long getTrackid() {
        return trackid;
    }

    /**
     * 字段: tra_ordertracking.trackid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 追踪id
     *
     * @mbggenerated
     */
    public void setTrackid(Long trackid) {
        this.trackid = trackid;
    }

    /**
     * @return tra_ordertracking.orderid: 订单id
     *
     * @mbggenerated
     */
    public Long getOrderid() {
        return orderid;
    }

    /**
     * 字段: tra_ordertracking.orderid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 订单id
     *
     * @mbggenerated
     */
    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    /**
     * @return tra_ordertracking.trackdate: 处理时间
     *
     * @mbggenerated
     */
    public Date getTrackdate() {
        return trackdate;
    }

    /**
     * 字段: tra_ordertracking.trackdate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 处理时间
     *
     * @mbggenerated
     */
    public void setTrackdate(Date trackdate) {
        this.trackdate = trackdate;
    }

    /**
     * @return tra_ordertracking.trackbody: 处理内容
     *
     * @mbggenerated
     */
    public String getTrackbody() {
        return trackbody;
    }

    /**
     * 字段: tra_ordertracking.trackbody<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 处理内容
     *
     * @mbggenerated
     */
    public void setTrackbody(String trackbody) {
        this.trackbody = trackbody;
    }

    /**
     * @return tra_ordertracking.operuse: 操作人
     *
     * @mbggenerated
     */
    public String getOperuse() {
        return operuse;
    }

    /**
     * 字段: tra_ordertracking.operuse<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 操作人
     *
     * @mbggenerated
     */
    public void setOperuse(String operuse) {
        this.operuse = operuse;
    }

    /**
     * @return tra_ordertracking.orderstatus: 订单状态 ord01新订单 ord04已审核待付款  ord08已付款待评价 ord12已发货 ord16已完成（已收货） ord10已评价 ord99已取消  ord07退款中  ord11已退款  
     *
     * @mbggenerated
     */
    public String getOrderstatus() {
        return orderstatus;
    }

    /**
     * 字段: tra_ordertracking.orderstatus<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 订单状态 ord01新订单 ord04已审核待付款  ord08已付款待评价 ord12已发货 ord16已完成（已收货） ord10已评价 ord99已取消  ord07退款中  ord11已退款  
     *
     * @mbggenerated
     */
    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_ordertracking
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TraOrdertracking other = (TraOrdertracking) that;
        return (this.getTrackid() == null ? other.getTrackid() == null : this.getTrackid().equals(other.getTrackid()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getTrackdate() == null ? other.getTrackdate() == null : this.getTrackdate().equals(other.getTrackdate()))
            && (this.getTrackbody() == null ? other.getTrackbody() == null : this.getTrackbody().equals(other.getTrackbody()))
            && (this.getOperuse() == null ? other.getOperuse() == null : this.getOperuse().equals(other.getOperuse()))
            && (this.getOrderstatus() == null ? other.getOrderstatus() == null : this.getOrderstatus().equals(other.getOrderstatus()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_ordertracking
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrackid() == null) ? 0 : getTrackid().hashCode());
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getTrackdate() == null) ? 0 : getTrackdate().hashCode());
        result = prime * result + ((getTrackbody() == null) ? 0 : getTrackbody().hashCode());
        result = prime * result + ((getOperuse() == null) ? 0 : getOperuse().hashCode());
        result = prime * result + ((getOrderstatus() == null) ? 0 : getOrderstatus().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_ordertracking
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", trackid=").append(trackid);
        sb.append(", orderid=").append(orderid);
        sb.append(", trackdate=").append(trackdate);
        sb.append(", trackbody=").append(trackbody);
        sb.append(", operuse=").append(operuse);
        sb.append(", orderstatus=").append(orderstatus);
        sb.append("]");
        return sb.toString();
    }
}