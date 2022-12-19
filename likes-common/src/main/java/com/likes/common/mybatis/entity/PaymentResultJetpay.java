package com.likes.common.mybatis.entity;

import java.io.Serializable;

public class PaymentResultJetpay implements Serializable {
    /**
     * 字段: payment_result_jetpay.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 字段: payment_result_jetpay.tranName<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 24<br/>
     * 说明: 交易名称
     *
     * @mbggenerated
     */
    private String tranname;

    /**
     * 字段: payment_result_jetpay.version<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 版本号
     *
     * @mbggenerated
     */
    private String version;

    /**
     * 字段: payment_result_jetpay.merCode<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 商户号
     *
     * @mbggenerated
     */
    private String mercode;

    /**
     * 字段: payment_result_jetpay.orderNo<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * 说明: 订单时间
     *
     * @mbggenerated
     */
    private String orderno;

    /**
     * 字段: payment_result_jetpay.orderDate<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 订单时间yyyyMMddhhmmss
     *
     * @mbggenerated
     */
    private String orderdate;

    /**
     * 字段: payment_result_jetpay.flowNo<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 系统流水号
     *
     * @mbggenerated
     */
    private String flowno;

    /**
     * 字段: payment_result_jetpay.payType<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * 说明: 支付方式
     *
     * @mbggenerated
     */
    private String paytype;

    /**
     * 字段: payment_result_jetpay.ordAmt<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 订单金额
     *
     * @mbggenerated
     */
    private Integer ordamt;

    /**
     * 字段: payment_result_jetpay.currency<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 3<br/>
     * 说明: 目前只支持 CNY
     *
     * @mbggenerated
     */
    private String currency;

    /**
     * 字段: payment_result_jetpay.paymentState<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 2<br/>
     * 说明: 支付状态00:成功；01:失败；02:不确定
     *
     * @mbggenerated
     */
    private String paymentstate;

    /**
     * 字段: payment_result_jetpay.orderDealTime<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 交易完成时间yyyyMMddhhmmss 由捷智付系统返回
     *
     * @mbggenerated
     */
    private String orderdealtime;

    /**
     * 字段: payment_result_jetpay.workdate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 8<br/>
     * 说明: 捷智付系统接收日期yyyymmdd 由捷智付系统返回
     *
     * @mbggenerated
     */
    private String workdate;

    /**
     * 字段: payment_result_jetpay.clearDate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 8<br/>
     * 说明: 清算日期yyyymmdd 由银行返回给清结算系统，表示
     *
     * @mbggenerated
     */
    private String cleardate;

    /**
     * 字段: payment_result_jetpay.errorCode<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 8<br/>
     * 说明: 错误代码
     *
     * @mbggenerated
     */
    private String errorcode;

    /**
     * 字段: payment_result_jetpay.errorMessage<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 错误信息
     *
     * @mbggenerated
     */
    private String errormessage;

    /**
     * 字段: payment_result_jetpay.reservedField1<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private String reservedfield1;

    /**
     * 字段: payment_result_jetpay.reservedField2<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 预留字段 1
     *
     * @mbggenerated
     */
    private String reservedfield2;

    /**
     * 字段: payment_result_jetpay.sign<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private String sign;

    /**
     * 字段: payment_result_jetpay.menberid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 用户id
     *
     * @mbggenerated
     */
    private String menberid;

    /**
     * 字段: payment_result_jetpay.status<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 2<br/>
     * 说明: 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 字段: payment_result_jetpay.createtime<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 24<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private String createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table payment_result_jetpay
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return payment_result_jetpay.id: 
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 字段: payment_result_jetpay.id<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return payment_result_jetpay.tranName: 交易名称
     *
     * @mbggenerated
     */
    public String getTranname() {
        return tranname;
    }

    /**
     * 字段: payment_result_jetpay.tranName<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 24<br/>
     * 说明: 交易名称
     *
     * @mbggenerated
     */
    public void setTranname(String tranname) {
        this.tranname = tranname;
    }

    /**
     * @return payment_result_jetpay.version: 版本号
     *
     * @mbggenerated
     */
    public String getVersion() {
        return version;
    }

    /**
     * 字段: payment_result_jetpay.version<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 12<br/>
     * 说明: 版本号
     *
     * @mbggenerated
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return payment_result_jetpay.merCode: 商户号
     *
     * @mbggenerated
     */
    public String getMercode() {
        return mercode;
    }

    /**
     * 字段: payment_result_jetpay.merCode<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 商户号
     *
     * @mbggenerated
     */
    public void setMercode(String mercode) {
        this.mercode = mercode;
    }

    /**
     * @return payment_result_jetpay.orderNo: 订单时间
     *
     * @mbggenerated
     */
    public String getOrderno() {
        return orderno;
    }

    /**
     * 字段: payment_result_jetpay.orderNo<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * 说明: 订单时间
     *
     * @mbggenerated
     */
    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    /**
     * @return payment_result_jetpay.orderDate: 订单时间yyyyMMddhhmmss
     *
     * @mbggenerated
     */
    public String getOrderdate() {
        return orderdate;
    }

    /**
     * 字段: payment_result_jetpay.orderDate<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 订单时间yyyyMMddhhmmss
     *
     * @mbggenerated
     */
    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    /**
     * @return payment_result_jetpay.flowNo: 系统流水号
     *
     * @mbggenerated
     */
    public String getFlowno() {
        return flowno;
    }

    /**
     * 字段: payment_result_jetpay.flowNo<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 系统流水号
     *
     * @mbggenerated
     */
    public void setFlowno(String flowno) {
        this.flowno = flowno;
    }

    /**
     * @return payment_result_jetpay.payType: 支付方式
     *
     * @mbggenerated
     */
    public String getPaytype() {
        return paytype;
    }

    /**
     * 字段: payment_result_jetpay.payType<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 30<br/>
     * 说明: 支付方式
     *
     * @mbggenerated
     */
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    /**
     * @return payment_result_jetpay.ordAmt: 订单金额
     *
     * @mbggenerated
     */
    public Integer getOrdamt() {
        return ordamt;
    }

    /**
     * 字段: payment_result_jetpay.ordAmt<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 订单金额
     *
     * @mbggenerated
     */
    public void setOrdamt(Integer ordamt) {
        this.ordamt = ordamt;
    }

    /**
     * @return payment_result_jetpay.currency: 目前只支持 CNY
     *
     * @mbggenerated
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 字段: payment_result_jetpay.currency<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 3<br/>
     * 说明: 目前只支持 CNY
     *
     * @mbggenerated
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return payment_result_jetpay.paymentState: 支付状态00:成功；01:失败；02:不确定
     *
     * @mbggenerated
     */
    public String getPaymentstate() {
        return paymentstate;
    }

    /**
     * 字段: payment_result_jetpay.paymentState<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 2<br/>
     * 说明: 支付状态00:成功；01:失败；02:不确定
     *
     * @mbggenerated
     */
    public void setPaymentstate(String paymentstate) {
        this.paymentstate = paymentstate;
    }

    /**
     * @return payment_result_jetpay.orderDealTime: 交易完成时间yyyyMMddhhmmss 由捷智付系统返回
     *
     * @mbggenerated
     */
    public String getOrderdealtime() {
        return orderdealtime;
    }

    /**
     * 字段: payment_result_jetpay.orderDealTime<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 交易完成时间yyyyMMddhhmmss 由捷智付系统返回
     *
     * @mbggenerated
     */
    public void setOrderdealtime(String orderdealtime) {
        this.orderdealtime = orderdealtime;
    }

    /**
     * @return payment_result_jetpay.workdate: 捷智付系统接收日期yyyymmdd 由捷智付系统返回
     *
     * @mbggenerated
     */
    public String getWorkdate() {
        return workdate;
    }

    /**
     * 字段: payment_result_jetpay.workdate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 8<br/>
     * 说明: 捷智付系统接收日期yyyymmdd 由捷智付系统返回
     *
     * @mbggenerated
     */
    public void setWorkdate(String workdate) {
        this.workdate = workdate;
    }

    /**
     * @return payment_result_jetpay.clearDate: 清算日期yyyymmdd 由银行返回给清结算系统，表示
     *
     * @mbggenerated
     */
    public String getCleardate() {
        return cleardate;
    }

    /**
     * 字段: payment_result_jetpay.clearDate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 8<br/>
     * 说明: 清算日期yyyymmdd 由银行返回给清结算系统，表示
     *
     * @mbggenerated
     */
    public void setCleardate(String cleardate) {
        this.cleardate = cleardate;
    }

    /**
     * @return payment_result_jetpay.errorCode: 错误代码
     *
     * @mbggenerated
     */
    public String getErrorcode() {
        return errorcode;
    }

    /**
     * 字段: payment_result_jetpay.errorCode<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 8<br/>
     * 说明: 错误代码
     *
     * @mbggenerated
     */
    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    /**
     * @return payment_result_jetpay.errorMessage: 错误信息
     *
     * @mbggenerated
     */
    public String getErrormessage() {
        return errormessage;
    }

    /**
     * 字段: payment_result_jetpay.errorMessage<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 错误信息
     *
     * @mbggenerated
     */
    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    /**
     * @return payment_result_jetpay.reservedField1: 
     *
     * @mbggenerated
     */
    public String getReservedfield1() {
        return reservedfield1;
    }

    /**
     * 字段: payment_result_jetpay.reservedField1<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setReservedfield1(String reservedfield1) {
        this.reservedfield1 = reservedfield1;
    }

    /**
     * @return payment_result_jetpay.reservedField2: 预留字段 1
     *
     * @mbggenerated
     */
    public String getReservedfield2() {
        return reservedfield2;
    }

    /**
     * 字段: payment_result_jetpay.reservedField2<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 256<br/>
     * 说明: 预留字段 1
     *
     * @mbggenerated
     */
    public void setReservedfield2(String reservedfield2) {
        this.reservedfield2 = reservedfield2;
    }

    /**
     * @return payment_result_jetpay.sign: 
     *
     * @mbggenerated
     */
    public String getSign() {
        return sign;
    }

    /**
     * 字段: payment_result_jetpay.sign<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 255<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * @return payment_result_jetpay.menberid: 用户id
     *
     * @mbggenerated
     */
    public String getMenberid() {
        return menberid;
    }

    /**
     * 字段: payment_result_jetpay.menberid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 用户id
     *
     * @mbggenerated
     */
    public void setMenberid(String menberid) {
        this.menberid = menberid;
    }

    /**
     * @return payment_result_jetpay.status: 状态
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * 字段: payment_result_jetpay.status<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 2<br/>
     * 说明: 状态
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return payment_result_jetpay.createtime: 
     *
     * @mbggenerated
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * 字段: payment_result_jetpay.createtime<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 24<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_result_jetpay
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
        PaymentResultJetpay other = (PaymentResultJetpay) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTranname() == null ? other.getTranname() == null : this.getTranname().equals(other.getTranname()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getMercode() == null ? other.getMercode() == null : this.getMercode().equals(other.getMercode()))
            && (this.getOrderno() == null ? other.getOrderno() == null : this.getOrderno().equals(other.getOrderno()))
            && (this.getOrderdate() == null ? other.getOrderdate() == null : this.getOrderdate().equals(other.getOrderdate()))
            && (this.getFlowno() == null ? other.getFlowno() == null : this.getFlowno().equals(other.getFlowno()))
            && (this.getPaytype() == null ? other.getPaytype() == null : this.getPaytype().equals(other.getPaytype()))
            && (this.getOrdamt() == null ? other.getOrdamt() == null : this.getOrdamt().equals(other.getOrdamt()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getPaymentstate() == null ? other.getPaymentstate() == null : this.getPaymentstate().equals(other.getPaymentstate()))
            && (this.getOrderdealtime() == null ? other.getOrderdealtime() == null : this.getOrderdealtime().equals(other.getOrderdealtime()))
            && (this.getWorkdate() == null ? other.getWorkdate() == null : this.getWorkdate().equals(other.getWorkdate()))
            && (this.getCleardate() == null ? other.getCleardate() == null : this.getCleardate().equals(other.getCleardate()))
            && (this.getErrorcode() == null ? other.getErrorcode() == null : this.getErrorcode().equals(other.getErrorcode()))
            && (this.getErrormessage() == null ? other.getErrormessage() == null : this.getErrormessage().equals(other.getErrormessage()))
            && (this.getReservedfield1() == null ? other.getReservedfield1() == null : this.getReservedfield1().equals(other.getReservedfield1()))
            && (this.getReservedfield2() == null ? other.getReservedfield2() == null : this.getReservedfield2().equals(other.getReservedfield2()))
            && (this.getSign() == null ? other.getSign() == null : this.getSign().equals(other.getSign()))
            && (this.getMenberid() == null ? other.getMenberid() == null : this.getMenberid().equals(other.getMenberid()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_result_jetpay
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTranname() == null) ? 0 : getTranname().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getMercode() == null) ? 0 : getMercode().hashCode());
        result = prime * result + ((getOrderno() == null) ? 0 : getOrderno().hashCode());
        result = prime * result + ((getOrderdate() == null) ? 0 : getOrderdate().hashCode());
        result = prime * result + ((getFlowno() == null) ? 0 : getFlowno().hashCode());
        result = prime * result + ((getPaytype() == null) ? 0 : getPaytype().hashCode());
        result = prime * result + ((getOrdamt() == null) ? 0 : getOrdamt().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getPaymentstate() == null) ? 0 : getPaymentstate().hashCode());
        result = prime * result + ((getOrderdealtime() == null) ? 0 : getOrderdealtime().hashCode());
        result = prime * result + ((getWorkdate() == null) ? 0 : getWorkdate().hashCode());
        result = prime * result + ((getCleardate() == null) ? 0 : getCleardate().hashCode());
        result = prime * result + ((getErrorcode() == null) ? 0 : getErrorcode().hashCode());
        result = prime * result + ((getErrormessage() == null) ? 0 : getErrormessage().hashCode());
        result = prime * result + ((getReservedfield1() == null) ? 0 : getReservedfield1().hashCode());
        result = prime * result + ((getReservedfield2() == null) ? 0 : getReservedfield2().hashCode());
        result = prime * result + ((getSign() == null) ? 0 : getSign().hashCode());
        result = prime * result + ((getMenberid() == null) ? 0 : getMenberid().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_result_jetpay
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tranname=").append(tranname);
        sb.append(", version=").append(version);
        sb.append(", mercode=").append(mercode);
        sb.append(", orderno=").append(orderno);
        sb.append(", orderdate=").append(orderdate);
        sb.append(", flowno=").append(flowno);
        sb.append(", paytype=").append(paytype);
        sb.append(", ordamt=").append(ordamt);
        sb.append(", currency=").append(currency);
        sb.append(", paymentstate=").append(paymentstate);
        sb.append(", orderdealtime=").append(orderdealtime);
        sb.append(", workdate=").append(workdate);
        sb.append(", cleardate=").append(cleardate);
        sb.append(", errorcode=").append(errorcode);
        sb.append(", errormessage=").append(errormessage);
        sb.append(", reservedfield1=").append(reservedfield1);
        sb.append(", reservedfield2=").append(reservedfield2);
        sb.append(", sign=").append(sign);
        sb.append(", menberid=").append(menberid);
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}