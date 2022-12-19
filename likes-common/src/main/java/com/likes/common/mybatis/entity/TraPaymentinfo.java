package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TraPaymentinfo implements Serializable {
    /**
     * 字段: tra_paymentinfo.payid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 支付id
     *
     * @mbggenerated
     */
    private Long payid;

    /**
     * 字段: tra_paymentinfo.orderid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 订单id
     *
     * @mbggenerated
     */
    private Long orderid;

    /**
     * 字段: tra_paymentinfo.paycode<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 支付标示第三方支付标示号，如支付宝的订单号或微信的prepay_id等
     *
     * @mbggenerated
     */
    private String paycode;

    /**
     * 字段: tra_paymentinfo.paydate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 支付时间
     *
     * @mbggenerated
     */
    private Date paydate;

    /**
     * 字段: tra_paymentinfo.paydatee<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 支付完成时间
     *
     * @mbggenerated
     */
    private Date paydatee;

    /**
     * 字段: tra_paymentinfo.serialno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 流水号：8位年月日+8位数字，如2016052800001200
     *
     * @mbggenerated
     */
    private Long serialno;

    /**
     * 字段: tra_paymentinfo.accno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 会员标识号
     *
     * @mbggenerated
     */
    private String accno;

    /**
     * 字段: tra_paymentinfo.orderno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 40<br/>
     * 说明: 订单编号4码年+2码月+8码流水号，生成逻辑：取当前年月，然后从数据库中取当前年月最大订单号，然后将后面8位流水号+1
     *
     * @mbggenerated
     */
    private String orderno;

    /**
     * 字段: tra_paymentinfo.paykind<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 支付类别 weixin微信支付 alipay支付宝支付
     *
     * @mbggenerated
     */
    private String paykind;

    /**
     * 字段: tra_paymentinfo.paytype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 支付方式  JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付   NETBANK 网银转账
     *
     * @mbggenerated
     */
    private String paytype;

    /**
     * 字段: tra_paymentinfo.tradingno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 交易标识号
     *
     * @mbggenerated
     */
    private String tradingno;

    /**
     * 字段: tra_paymentinfo.payamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 支付金额
     *
     * @mbggenerated
     */
    private BigDecimal payamt;

    /**
     * 字段: tra_paymentinfo.payscore<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 支付积分
     *
     * @mbggenerated
     */
    private Integer payscore;

    /**
     * 字段: tra_paymentinfo.paystatus<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 支付状态0支付成功/退款成功   1支付中/退款中     9支付失败/退款失败
     *
     * @mbggenerated
     */
    private Integer paystatus;

    /**
     * 字段: tra_paymentinfo.payerrdesc<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 支付错误描述 不用格式自己定义，如微信支付可以存错误代码$$错误描述
     *
     * @mbggenerated
     */
    private String payerrdesc;

    /**
     * 字段: tra_paymentinfo.systemname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 系统代码：来源系统代码
     *
     * @mbggenerated
     */
    private String systemname;

    /**
     * 字段: tra_paymentinfo.paycodeurl<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 支付标示二维码(页面)
     *
     * @mbggenerated
     */
    private String paycodeurl;

    /**
     * 字段: tra_paymentinfo.paynote<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 2000<br/>
     * 说明: 备注 退款时把原支付id写到这里
     *
     * @mbggenerated
     */
    private String paynote;

    /**
     * 字段: tra_paymentinfo.payrefundtype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 支付退款类型  0或null 支付  9 退款
     *
     * @mbggenerated
     */
    private Integer payrefundtype;

    /**
     * 字段: tra_paymentinfo.refundnote<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 退款说明
     *
     * @mbggenerated
     */
    private String refundnote;

    /**
     * 字段: tra_paymentinfo.refundcode<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 退款标示号 uuid
     *
     * @mbggenerated
     */
    private String refundcode;

    /**
     * 字段: tra_paymentinfo.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: tra_paymentinfo.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 字段: tra_paymentinfo.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: tra_paymentinfo.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * 字段: tra_paymentinfo.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * 字段: tra_paymentinfo.provider_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明:
     *
     * @mbggenerated
     */
    private Long providerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tra_paymentinfo
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return tra_paymentinfo.payid: 支付id
     *
     * @mbggenerated
     */
    public Long getPayid() {
        return payid;
    }

    /**
     * 字段: tra_paymentinfo.payid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 支付id
     *
     * @mbggenerated
     */
    public void setPayid(Long payid) {
        this.payid = payid;
    }

    /**
     * @return tra_paymentinfo.orderid: 订单id
     *
     * @mbggenerated
     */
    public Long getOrderid() {
        return orderid;
    }

    /**
     * 字段: tra_paymentinfo.orderid<br/>
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
     * @return tra_paymentinfo.paycode: 支付标示第三方支付标示号，如支付宝的订单号或微信的prepay_id等
     *
     * @mbggenerated
     */
    public String getPaycode() {
        return paycode;
    }

    /**
     * 字段: tra_paymentinfo.paycode<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 支付标示第三方支付标示号，如支付宝的订单号或微信的prepay_id等
     *
     * @mbggenerated
     */
    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }

    /**
     * @return tra_paymentinfo.paydate: 支付时间
     *
     * @mbggenerated
     */
    public Date getPaydate() {
        return paydate;
    }

    /**
     * 字段: tra_paymentinfo.paydate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 支付时间
     *
     * @mbggenerated
     */
    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    /**
     * @return tra_paymentinfo.paydatee: 支付完成时间
     *
     * @mbggenerated
     */
    public Date getPaydatee() {
        return paydatee;
    }

    /**
     * 字段: tra_paymentinfo.paydatee<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 支付完成时间
     *
     * @mbggenerated
     */
    public void setPaydatee(Date paydatee) {
        this.paydatee = paydatee;
    }

    /**
     * @return tra_paymentinfo.serialno: 流水号：8位年月日+8位数字，如2016052800001200
     *
     * @mbggenerated
     */
    public Long getSerialno() {
        return serialno;
    }

    /**
     * 字段: tra_paymentinfo.serialno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 流水号：8位年月日+8位数字，如2016052800001200
     *
     * @mbggenerated
     */
    public void setSerialno(Long serialno) {
        this.serialno = serialno;
    }

    /**
     * @return tra_paymentinfo.accno: 会员标识号
     *
     * @mbggenerated
     */
    public String getAccno() {
        return accno;
    }

    /**
     * 字段: tra_paymentinfo.accno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 会员标识号
     *
     * @mbggenerated
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * @return tra_paymentinfo.orderno: 订单编号4码年+2码月+8码流水号，生成逻辑：取当前年月，然后从数据库中取当前年月最大订单号，然后将后面8位流水号+1
     *
     * @mbggenerated
     */
    public String getOrderno() {
        return orderno;
    }

    /**
     * 字段: tra_paymentinfo.orderno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 40<br/>
     * 说明: 订单编号4码年+2码月+8码流水号，生成逻辑：取当前年月，然后从数据库中取当前年月最大订单号，然后将后面8位流水号+1
     *
     * @mbggenerated
     */
    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    /**
     * @return tra_paymentinfo.paykind: 支付类别 weixin微信支付 alipay支付宝支付
     *
     * @mbggenerated
     */
    public String getPaykind() {
        return paykind;
    }

    /**
     * 字段: tra_paymentinfo.paykind<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 支付类别 weixin微信支付 alipay支付宝支付
     *
     * @mbggenerated
     */
    public void setPaykind(String paykind) {
        this.paykind = paykind;
    }

    /**
     * @return tra_paymentinfo.paytype: 支付方式  JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付   NETBANK 网银转账
     *
     * @mbggenerated
     */
    public String getPaytype() {
        return paytype;
    }

    /**
     * 字段: tra_paymentinfo.paytype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 支付方式  JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付   NETBANK 网银转账
     *
     * @mbggenerated
     */
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    /**
     * @return tra_paymentinfo.tradingno: 交易标识号
     *
     * @mbggenerated
     */
    public String getTradingno() {
        return tradingno;
    }

    /**
     * 字段: tra_paymentinfo.tradingno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 交易标识号
     *
     * @mbggenerated
     */
    public void setTradingno(String tradingno) {
        this.tradingno = tradingno;
    }

    /**
     * @return tra_paymentinfo.payamt: 支付金额
     *
     * @mbggenerated
     */
    public BigDecimal getPayamt() {
        return payamt;
    }

    /**
     * 字段: tra_paymentinfo.payamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 支付金额
     *
     * @mbggenerated
     */
    public void setPayamt(BigDecimal payamt) {
        this.payamt = payamt;
    }

    /**
     * @return tra_paymentinfo.payscore: 支付积分
     *
     * @mbggenerated
     */
    public Integer getPayscore() {
        return payscore;
    }

    /**
     * 字段: tra_paymentinfo.payscore<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 10<br/>
     * 说明: 支付积分
     *
     * @mbggenerated
     */
    public void setPayscore(Integer payscore) {
        this.payscore = payscore;
    }

    /**
     * @return tra_paymentinfo.paystatus: 支付状态0支付成功/退款成功   1支付中/退款中     9支付失败/退款失败
     *
     * @mbggenerated
     */
    public Integer getPaystatus() {
        return paystatus;
    }

    /**
     * 字段: tra_paymentinfo.paystatus<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 支付状态0支付成功/退款成功   1支付中/退款中     9支付失败/退款失败
     *
     * @mbggenerated
     */
    public void setPaystatus(Integer paystatus) {
        this.paystatus = paystatus;
    }

    /**
     * @return tra_paymentinfo.payerrdesc: 支付错误描述 不用格式自己定义，如微信支付可以存错误代码$$错误描述
     *
     * @mbggenerated
     */
    public String getPayerrdesc() {
        return payerrdesc;
    }

    /**
     * 字段: tra_paymentinfo.payerrdesc<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 支付错误描述 不用格式自己定义，如微信支付可以存错误代码$$错误描述
     *
     * @mbggenerated
     */
    public void setPayerrdesc(String payerrdesc) {
        this.payerrdesc = payerrdesc;
    }

    /**
     * @return tra_paymentinfo.systemname: 系统代码：来源系统代码
     *
     * @mbggenerated
     */
    public String getSystemname() {
        return systemname;
    }

    /**
     * 字段: tra_paymentinfo.systemname<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 系统代码：来源系统代码
     *
     * @mbggenerated
     */
    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    /**
     * @return tra_paymentinfo.paycodeurl: 支付标示二维码(页面)
     *
     * @mbggenerated
     */
    public String getPaycodeurl() {
        return paycodeurl;
    }

    /**
     * 字段: tra_paymentinfo.paycodeurl<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 支付标示二维码(页面)
     *
     * @mbggenerated
     */
    public void setPaycodeurl(String paycodeurl) {
        this.paycodeurl = paycodeurl;
    }

    /**
     * @return tra_paymentinfo.paynote: 备注 退款时把原支付id写到这里
     *
     * @mbggenerated
     */
    public String getPaynote() {
        return paynote;
    }

    /**
     * 字段: tra_paymentinfo.paynote<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 2000<br/>
     * 说明: 备注 退款时把原支付id写到这里
     *
     * @mbggenerated
     */
    public void setPaynote(String paynote) {
        this.paynote = paynote;
    }

    /**
     * @return tra_paymentinfo.payrefundtype: 支付退款类型  0或null 支付  9 退款
     *
     * @mbggenerated
     */
    public Integer getPayrefundtype() {
        return payrefundtype;
    }

    /**
     * 字段: tra_paymentinfo.payrefundtype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 支付退款类型  0或null 支付  9 退款
     *
     * @mbggenerated
     */
    public void setPayrefundtype(Integer payrefundtype) {
        this.payrefundtype = payrefundtype;
    }

    /**
     * @return tra_paymentinfo.refundnote: 退款说明
     *
     * @mbggenerated
     */
    public String getRefundnote() {
        return refundnote;
    }

    /**
     * 字段: tra_paymentinfo.refundnote<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 退款说明
     *
     * @mbggenerated
     */
    public void setRefundnote(String refundnote) {
        this.refundnote = refundnote;
    }

    /**
     * @return tra_paymentinfo.refundcode: 退款标示号 uuid
     *
     * @mbggenerated
     */
    public String getRefundcode() {
        return refundcode;
    }

    /**
     * 字段: tra_paymentinfo.refundcode<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 退款标示号 uuid
     *
     * @mbggenerated
     */
    public void setRefundcode(String refundcode) {
        this.refundcode = refundcode;
    }

    /**
     * @return tra_paymentinfo.is_delete: 是否删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 字段: tra_paymentinfo.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @return tra_paymentinfo.create_user: 创建人
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 字段: tra_paymentinfo.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return tra_paymentinfo.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: tra_paymentinfo.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return tra_paymentinfo.update_user: 最后修改人
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 字段: tra_paymentinfo.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return tra_paymentinfo.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: tra_paymentinfo.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return tra_paymentinfo.provider_id:
     *
     * @mbggenerated
     */
    public Long getProviderId() {
        return providerId;
    }

    /**
     * 字段: tra_paymentinfo.provider_id<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明:
     *
     * @mbggenerated
     */
    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_paymentinfo
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
        TraPaymentinfo other = (TraPaymentinfo) that;
        return (this.getPayid() == null ? other.getPayid() == null : this.getPayid().equals(other.getPayid()))
                && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
                && (this.getPaycode() == null ? other.getPaycode() == null : this.getPaycode().equals(other.getPaycode()))
                && (this.getPaydate() == null ? other.getPaydate() == null : this.getPaydate().equals(other.getPaydate()))
                && (this.getPaydatee() == null ? other.getPaydatee() == null : this.getPaydatee().equals(other.getPaydatee()))
                && (this.getSerialno() == null ? other.getSerialno() == null : this.getSerialno().equals(other.getSerialno()))
                && (this.getAccno() == null ? other.getAccno() == null : this.getAccno().equals(other.getAccno()))
                && (this.getOrderno() == null ? other.getOrderno() == null : this.getOrderno().equals(other.getOrderno()))
                && (this.getPaykind() == null ? other.getPaykind() == null : this.getPaykind().equals(other.getPaykind()))
                && (this.getPaytype() == null ? other.getPaytype() == null : this.getPaytype().equals(other.getPaytype()))
                && (this.getTradingno() == null ? other.getTradingno() == null : this.getTradingno().equals(other.getTradingno()))
                && (this.getPayamt() == null ? other.getPayamt() == null : this.getPayamt().equals(other.getPayamt()))
                && (this.getPayscore() == null ? other.getPayscore() == null : this.getPayscore().equals(other.getPayscore()))
                && (this.getPaystatus() == null ? other.getPaystatus() == null : this.getPaystatus().equals(other.getPaystatus()))
                && (this.getPayerrdesc() == null ? other.getPayerrdesc() == null : this.getPayerrdesc().equals(other.getPayerrdesc()))
                && (this.getSystemname() == null ? other.getSystemname() == null : this.getSystemname().equals(other.getSystemname()))
                && (this.getPaycodeurl() == null ? other.getPaycodeurl() == null : this.getPaycodeurl().equals(other.getPaycodeurl()))
                && (this.getPaynote() == null ? other.getPaynote() == null : this.getPaynote().equals(other.getPaynote()))
                && (this.getPayrefundtype() == null ? other.getPayrefundtype() == null : this.getPayrefundtype().equals(other.getPayrefundtype()))
                && (this.getRefundnote() == null ? other.getRefundnote() == null : this.getRefundnote().equals(other.getRefundnote()))
                && (this.getRefundcode() == null ? other.getRefundcode() == null : this.getRefundcode().equals(other.getRefundcode()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
                && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getProviderId() == null ? other.getProviderId() == null : this.getProviderId().equals(other.getProviderId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_paymentinfo
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPayid() == null) ? 0 : getPayid().hashCode());
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getPaycode() == null) ? 0 : getPaycode().hashCode());
        result = prime * result + ((getPaydate() == null) ? 0 : getPaydate().hashCode());
        result = prime * result + ((getPaydatee() == null) ? 0 : getPaydatee().hashCode());
        result = prime * result + ((getSerialno() == null) ? 0 : getSerialno().hashCode());
        result = prime * result + ((getAccno() == null) ? 0 : getAccno().hashCode());
        result = prime * result + ((getOrderno() == null) ? 0 : getOrderno().hashCode());
        result = prime * result + ((getPaykind() == null) ? 0 : getPaykind().hashCode());
        result = prime * result + ((getPaytype() == null) ? 0 : getPaytype().hashCode());
        result = prime * result + ((getTradingno() == null) ? 0 : getTradingno().hashCode());
        result = prime * result + ((getPayamt() == null) ? 0 : getPayamt().hashCode());
        result = prime * result + ((getPayscore() == null) ? 0 : getPayscore().hashCode());
        result = prime * result + ((getPaystatus() == null) ? 0 : getPaystatus().hashCode());
        result = prime * result + ((getPayerrdesc() == null) ? 0 : getPayerrdesc().hashCode());
        result = prime * result + ((getSystemname() == null) ? 0 : getSystemname().hashCode());
        result = prime * result + ((getPaycodeurl() == null) ? 0 : getPaycodeurl().hashCode());
        result = prime * result + ((getPaynote() == null) ? 0 : getPaynote().hashCode());
        result = prime * result + ((getPayrefundtype() == null) ? 0 : getPayrefundtype().hashCode());
        result = prime * result + ((getRefundnote() == null) ? 0 : getRefundnote().hashCode());
        result = prime * result + ((getRefundcode() == null) ? 0 : getRefundcode().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getProviderId() == null) ? 0 : getProviderId().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_paymentinfo
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", payid=").append(payid);
        sb.append(", orderid=").append(orderid);
        sb.append(", paycode=").append(paycode);
        sb.append(", paydate=").append(paydate);
        sb.append(", paydatee=").append(paydatee);
        sb.append(", serialno=").append(serialno);
        sb.append(", accno=").append(accno);
        sb.append(", orderno=").append(orderno);
        sb.append(", paykind=").append(paykind);
        sb.append(", paytype=").append(paytype);
        sb.append(", tradingno=").append(tradingno);
        sb.append(", payamt=").append(payamt);
        sb.append(", payscore=").append(payscore);
        sb.append(", paystatus=").append(paystatus);
        sb.append(", payerrdesc=").append(payerrdesc);
        sb.append(", systemname=").append(systemname);
        sb.append(", paycodeurl=").append(paycodeurl);
        sb.append(", paynote=").append(paynote);
        sb.append(", payrefundtype=").append(payrefundtype);
        sb.append(", refundnote=").append(refundnote);
        sb.append(", refundcode=").append(refundcode);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", providerId=").append(providerId);
        sb.append("]");
        return sb.toString();
    }
}