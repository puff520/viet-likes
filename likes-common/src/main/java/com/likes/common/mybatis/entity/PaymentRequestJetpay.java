//package com.likes.common.mybatis.entity;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//
//public class PaymentRequestJetpay implements Serializable {
//    /**
//     * 字段: payment_request_jetpay.id<br/>
//     * 主键: 自动增长<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    private Integer id;
//
//    /**
//     * 字段: payment_request_jetpay.tranName<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 24<br/>
//     * 说明: 交易名称
//     *
//     * @mbggenerated
//     */
//    private String tranname;
//
//    /**
//     * 字段: payment_request_jetpay.version<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 12<br/>
//     * 说明: 版本号
//     *
//     * @mbggenerated
//     */
//    private String version;
//
//    /**
//     * 字段: payment_request_jetpay.merCode<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 32<br/>
//     * 说明: 商户号
//     *
//     * @mbggenerated
//     */
//    private String mercode;
//
//    /**
//     * 字段: payment_request_jetpay.orderNo<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 30<br/>
//     * 说明: 订单时间
//     *
//     * @mbggenerated
//     */
//    private String orderno;
//
//    /**
//     * 字段: payment_request_jetpay.orderTime<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 14<br/>
//     * 说明: 订单时间yyyyMMddhhmmss
//     *
//     * @mbggenerated
//     */
//    private String ordertime;
//
//    /**
//     * 字段: payment_request_jetpay.payType<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 30<br/>
//     * 说明: 支付方式
//     *
//     * @mbggenerated
//     */
//    private String paytype;
//
//    /**
//     * 字段: payment_request_jetpay.amount<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明: 金额无小数点。默认最后两位为小数位。从客户
//     *
//     * @mbggenerated
//     */
//    private Integer amount;
//
//    /**
//     * 字段: payment_request_jetpay.currency<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 3<br/>
//     * 说明: 目前只支持 CNY
//     *
//     * @mbggenerated
//     */
//    private String currency;
//
//    /**
//     * 字段: payment_request_jetpay.productName<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 256<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    private String productname;
//
//    /**
//     * 字段: payment_request_jetpay.orderDesc<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 4000<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    private String orderdesc;
//
//    /**
//     * 字段: payment_request_jetpay.returnURL<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 256<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    private String returnurl;
//
//    /**
//     * 字段: payment_request_jetpay.notifyURL<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 256<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    private String notifyurl;
//
//    /**
//     * 字段: payment_request_jetpay.reservedField1<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 256<br/>
//     * 说明: 附言
//     *
//     * @mbggenerated
//     */
//    private String reservedfield1;
//
//    /**
//     * 字段: payment_request_jetpay.reservedField2<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 256<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    private String reservedfield2;
//
//    /**
//     * 字段: payment_request_jetpay.sign<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 256<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    private String sign;
//
//    /**
//     * 字段: payment_request_jetpay.menberid<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 32<br/>
//     * 说明: 用户id
//     *
//     * @mbggenerated
//     */
//    private String menberid;
//
//    /**
//     * 字段: payment_request_jetpay.status<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 2<br/>
//     * 说明: 状态(1发起充值2充值成功3充值失败4未确定)
//     *
//     * @mbggenerated
//     */
//    private String status;
//
//    /**
//     * 字段: payment_request_jetpay.source<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 24<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    private String source;
//
//    /**
//     * 字段: payment_request_jetpay.createtime<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 24<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    private String createtime;
//
//    /**
//     * 字段: payment_request_jetpay.trade_time<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 19<br/>
//     * 说明: 交易时间
//     *
//     * @mbggenerated
//     */
//    private Date tradeTime;
//
//    /**
//     * 字段: payment_request_jetpay.actual_amount<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 20<br/>
//     * 说明: 实际金额
//     *
//     * @mbggenerated
//     */
//    private BigDecimal actualAmount;
//
//    /**
//     * 字段: payment_request_jetpay.flowNo<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 商家流水号
//     *
//     * @mbggenerated
//     */
//    private String flowno;
//
//    /**
//     * 字段: payment_request_jetpay.tag<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 商家标识
//     *
//     * @mbggenerated
//     */
//    private String tag;
//
//    /**
//     * This field was generated by MyBatis Generator.
//     * This field corresponds to the database table payment_request_jetpay
//     *
//     * @mbggenerated
//     */
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * @return payment_request_jetpay.id:
//     *
//     * @mbggenerated
//     */
//    public Integer getId() {
//        return id;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.id<br/>
//     * 主键: 自动增长<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    /**
//     * @return payment_request_jetpay.tranName: 交易名称
//     *
//     * @mbggenerated
//     */
//    public String getTranname() {
//        return tranname;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.tranName<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 24<br/>
//     * 说明: 交易名称
//     *
//     * @mbggenerated
//     */
//    public void setTranname(String tranname) {
//        this.tranname = tranname;
//    }
//
//    /**
//     * @return payment_request_jetpay.version: 版本号
//     *
//     * @mbggenerated
//     */
//    public String getVersion() {
//        return version;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.version<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 12<br/>
//     * 说明: 版本号
//     *
//     * @mbggenerated
//     */
//    public void setVersion(String version) {
//        this.version = version;
//    }
//
//    /**
//     * @return payment_request_jetpay.merCode: 商户号
//     *
//     * @mbggenerated
//     */
//    public String getMercode() {
//        return mercode;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.merCode<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 32<br/>
//     * 说明: 商户号
//     *
//     * @mbggenerated
//     */
//    public void setMercode(String mercode) {
//        this.mercode = mercode;
//    }
//
//    /**
//     * @return payment_request_jetpay.orderNo: 订单时间
//     *
//     * @mbggenerated
//     */
//    public String getOrderno() {
//        return orderno;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.orderNo<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 30<br/>
//     * 说明: 订单时间
//     *
//     * @mbggenerated
//     */
//    public void setOrderno(String orderno) {
//        this.orderno = orderno;
//    }
//
//    /**
//     * @return payment_request_jetpay.orderTime: 订单时间yyyyMMddhhmmss
//     *
//     * @mbggenerated
//     */
//    public String getOrdertime() {
//        return ordertime;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.orderTime<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 14<br/>
//     * 说明: 订单时间yyyyMMddhhmmss
//     *
//     * @mbggenerated
//     */
//    public void setOrdertime(String ordertime) {
//        this.ordertime = ordertime;
//    }
//
//    /**
//     * @return payment_request_jetpay.payType: 支付方式
//     *
//     * @mbggenerated
//     */
//    public String getPaytype() {
//        return paytype;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.payType<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 30<br/>
//     * 说明: 支付方式
//     *
//     * @mbggenerated
//     */
//    public void setPaytype(String paytype) {
//        this.paytype = paytype;
//    }
//
//    /**
//     * @return payment_request_jetpay.amount: 金额无小数点。默认最后两位为小数位。从客户
//     *
//     * @mbggenerated
//     */
//    public Integer getAmount() {
//        return amount;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.amount<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 10<br/>
//     * 说明: 金额无小数点。默认最后两位为小数位。从客户
//     *
//     * @mbggenerated
//     */
//    public void setAmount(Integer amount) {
//        this.amount = amount;
//    }
//
//    /**
//     * @return payment_request_jetpay.currency: 目前只支持 CNY
//     *
//     * @mbggenerated
//     */
//    public String getCurrency() {
//        return currency;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.currency<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 3<br/>
//     * 说明: 目前只支持 CNY
//     *
//     * @mbggenerated
//     */
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }
//
//    /**
//     * @return payment_request_jetpay.productName:
//     *
//     * @mbggenerated
//     */
//    public String getProductname() {
//        return productname;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.productName<br/>
//     * 必填: true<br/>
//     * 缺省: <br/>
//     * 长度: 256<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    public void setProductname(String productname) {
//        this.productname = productname;
//    }
//
//    /**
//     * @return payment_request_jetpay.orderDesc:
//     *
//     * @mbggenerated
//     */
//    public String getOrderdesc() {
//        return orderdesc;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.orderDesc<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 4000<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    public void setOrderdesc(String orderdesc) {
//        this.orderdesc = orderdesc;
//    }
//
//    /**
//     * @return payment_request_jetpay.returnURL:
//     *
//     * @mbggenerated
//     */
//    public String getReturnurl() {
//        return returnurl;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.returnURL<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 256<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    public void setReturnurl(String returnurl) {
//        this.returnurl = returnurl;
//    }
//
//    /**
//     * @return payment_request_jetpay.notifyURL:
//     *
//     * @mbggenerated
//     */
//    public String getNotifyurl() {
//        return notifyurl;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.notifyURL<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 256<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    public void setNotifyurl(String notifyurl) {
//        this.notifyurl = notifyurl;
//    }
//
//    /**
//     * @return payment_request_jetpay.reservedField1: 附言
//     *
//     * @mbggenerated
//     */
//    public String getReservedfield1() {
//        return reservedfield1;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.reservedField1<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 256<br/>
//     * 说明: 附言
//     *
//     * @mbggenerated
//     */
//    public void setReservedfield1(String reservedfield1) {
//        this.reservedfield1 = reservedfield1;
//    }
//
//    /**
//     * @return payment_request_jetpay.reservedField2:
//     *
//     * @mbggenerated
//     */
//    public String getReservedfield2() {
//        return reservedfield2;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.reservedField2<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 256<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    public void setReservedfield2(String reservedfield2) {
//        this.reservedfield2 = reservedfield2;
//    }
//
//    /**
//     * @return payment_request_jetpay.sign:
//     *
//     * @mbggenerated
//     */
//    public String getSign() {
//        return sign;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.sign<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 256<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    public void setSign(String sign) {
//        this.sign = sign;
//    }
//
//    /**
//     * @return payment_request_jetpay.menberid: 用户id
//     *
//     * @mbggenerated
//     */
//    public String getMenberid() {
//        return menberid;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.menberid<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 32<br/>
//     * 说明: 用户id
//     *
//     * @mbggenerated
//     */
//    public void setMenberid(String menberid) {
//        this.menberid = menberid;
//    }
//
//    /**
//     * @return payment_request_jetpay.status: 状态(1发起充值2充值成功3充值失败4未确定)
//     *
//     * @mbggenerated
//     */
//    public String getStatus() {
//        return status;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.status<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 2<br/>
//     * 说明: 状态(1发起充值2充值成功3充值失败4未确定)
//     *
//     * @mbggenerated
//     */
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    /**
//     * @return payment_request_jetpay.source:
//     *
//     * @mbggenerated
//     */
//    public String getSource() {
//        return source;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.source<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 24<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    /**
//     * @return payment_request_jetpay.createtime:
//     *
//     * @mbggenerated
//     */
//    public String getCreatetime() {
//        return createtime;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.createtime<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 24<br/>
//     * 说明:
//     *
//     * @mbggenerated
//     */
//    public void setCreatetime(String createtime) {
//        this.createtime = createtime;
//    }
//
//    /**
//     * @return payment_request_jetpay.trade_time: 交易时间
//     *
//     * @mbggenerated
//     */
//    public Date getTradeTime() {
//        return tradeTime;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.trade_time<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 19<br/>
//     * 说明: 交易时间
//     *
//     * @mbggenerated
//     */
//    public void setTradeTime(Date tradeTime) {
//        this.tradeTime = tradeTime;
//    }
//
//    /**
//     * @return payment_request_jetpay.actual_amount: 实际金额
//     *
//     * @mbggenerated
//     */
//    public BigDecimal getActualAmount() {
//        return actualAmount;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.actual_amount<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 20<br/>
//     * 说明: 实际金额
//     *
//     * @mbggenerated
//     */
//    public void setActualAmount(BigDecimal actualAmount) {
//        this.actualAmount = actualAmount;
//    }
//
//    /**
//     * @return payment_request_jetpay.flowNo: 商家流水号
//     *
//     * @mbggenerated
//     */
//    public String getFlowno() {
//        return flowno;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.flowNo<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 商家流水号
//     *
//     * @mbggenerated
//     */
//    public void setFlowno(String flowno) {
//        this.flowno = flowno;
//    }
//
//    /**
//     * @return payment_request_jetpay.tag: 商家标识
//     *
//     * @mbggenerated
//     */
//    public String getTag() {
//        return tag;
//    }
//
//    /**
//     * 字段: payment_request_jetpay.tag<br/>
//     * 必填: false<br/>
//     * 缺省: <br/>
//     * 长度: 255<br/>
//     * 说明: 商家标识
//     *
//     * @mbggenerated
//     */
//    public void setTag(String tag) {
//        this.tag = tag;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table payment_request_jetpay
//     *
//     * @mbggenerated
//     */
//    @Override
//    public boolean equals(Object that) {
//        if (this == that) {
//            return true;
//        }
//        if (that == null) {
//            return false;
//        }
//        if (getClass() != that.getClass()) {
//            return false;
//        }
//        PaymentRequestJetpay other = (PaymentRequestJetpay) that;
//        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
//            && (this.getTranname() == null ? other.getTranname() == null : this.getTranname().equals(other.getTranname()))
//            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
//            && (this.getMercode() == null ? other.getMercode() == null : this.getMercode().equals(other.getMercode()))
//            && (this.getOrderno() == null ? other.getOrderno() == null : this.getOrderno().equals(other.getOrderno()))
//            && (this.getOrdertime() == null ? other.getOrdertime() == null : this.getOrdertime().equals(other.getOrdertime()))
//            && (this.getPaytype() == null ? other.getPaytype() == null : this.getPaytype().equals(other.getPaytype()))
//            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
//            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
//            && (this.getProductname() == null ? other.getProductname() == null : this.getProductname().equals(other.getProductname()))
//            && (this.getOrderdesc() == null ? other.getOrderdesc() == null : this.getOrderdesc().equals(other.getOrderdesc()))
//            && (this.getReturnurl() == null ? other.getReturnurl() == null : this.getReturnurl().equals(other.getReturnurl()))
//            && (this.getNotifyurl() == null ? other.getNotifyurl() == null : this.getNotifyurl().equals(other.getNotifyurl()))
//            && (this.getReservedfield1() == null ? other.getReservedfield1() == null : this.getReservedfield1().equals(other.getReservedfield1()))
//            && (this.getReservedfield2() == null ? other.getReservedfield2() == null : this.getReservedfield2().equals(other.getReservedfield2()))
//            && (this.getSign() == null ? other.getSign() == null : this.getSign().equals(other.getSign()))
//            && (this.getMenberid() == null ? other.getMenberid() == null : this.getMenberid().equals(other.getMenberid()))
//            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
//            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
//            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
//            && (this.getTradeTime() == null ? other.getTradeTime() == null : this.getTradeTime().equals(other.getTradeTime()))
//            && (this.getActualAmount() == null ? other.getActualAmount() == null : this.getActualAmount().equals(other.getActualAmount()))
//            && (this.getFlowno() == null ? other.getFlowno() == null : this.getFlowno().equals(other.getFlowno()))
//            && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()));
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table payment_request_jetpay
//     *
//     * @mbggenerated
//     */
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
//        result = prime * result + ((getTranname() == null) ? 0 : getTranname().hashCode());
//        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
//        result = prime * result + ((getMercode() == null) ? 0 : getMercode().hashCode());
//        result = prime * result + ((getOrderno() == null) ? 0 : getOrderno().hashCode());
//        result = prime * result + ((getOrdertime() == null) ? 0 : getOrdertime().hashCode());
//        result = prime * result + ((getPaytype() == null) ? 0 : getPaytype().hashCode());
//        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
//        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
//        result = prime * result + ((getProductname() == null) ? 0 : getProductname().hashCode());
//        result = prime * result + ((getOrderdesc() == null) ? 0 : getOrderdesc().hashCode());
//        result = prime * result + ((getReturnurl() == null) ? 0 : getReturnurl().hashCode());
//        result = prime * result + ((getNotifyurl() == null) ? 0 : getNotifyurl().hashCode());
//        result = prime * result + ((getReservedfield1() == null) ? 0 : getReservedfield1().hashCode());
//        result = prime * result + ((getReservedfield2() == null) ? 0 : getReservedfield2().hashCode());
//        result = prime * result + ((getSign() == null) ? 0 : getSign().hashCode());
//        result = prime * result + ((getMenberid() == null) ? 0 : getMenberid().hashCode());
//        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
//        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
//        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
//        result = prime * result + ((getTradeTime() == null) ? 0 : getTradeTime().hashCode());
//        result = prime * result + ((getActualAmount() == null) ? 0 : getActualAmount().hashCode());
//        result = prime * result + ((getFlowno() == null) ? 0 : getFlowno().hashCode());
//        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
//        return result;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table payment_request_jetpay
//     *
//     * @mbggenerated
//     */
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", tranname=").append(tranname);
//        sb.append(", version=").append(version);
//        sb.append(", mercode=").append(mercode);
//        sb.append(", orderno=").append(orderno);
//        sb.append(", ordertime=").append(ordertime);
//        sb.append(", paytype=").append(paytype);
//        sb.append(", amount=").append(amount);
//        sb.append(", currency=").append(currency);
//        sb.append(", productname=").append(productname);
//        sb.append(", orderdesc=").append(orderdesc);
//        sb.append(", returnurl=").append(returnurl);
//        sb.append(", notifyurl=").append(notifyurl);
//        sb.append(", reservedfield1=").append(reservedfield1);
//        sb.append(", reservedfield2=").append(reservedfield2);
//        sb.append(", sign=").append(sign);
//        sb.append(", menberid=").append(menberid);
//        sb.append(", status=").append(status);
//        sb.append(", source=").append(source);
//        sb.append(", createtime=").append(createtime);
//        sb.append(", tradeTime=").append(tradeTime);
//        sb.append(", actualAmount=").append(actualAmount);
//        sb.append(", flowno=").append(flowno);
//        sb.append(", tag=").append(tag);
//        sb.append("]");
//        return sb.toString();
//    }
//}