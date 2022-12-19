package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TraOrderinfom implements Serializable {
    /**
     * 字段: tra_orderinfom.orderid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 订单id
     *
     * @mbggenerated
     */
    private Long orderid;

    /**
     * 字段: tra_orderinfom.mealid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 充值套餐id
     *
     * @mbggenerated
     */
    private Long mealid;

    /**
     * 字段: tra_orderinfom.bankid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 入款账户id
     *
     * @mbggenerated
     */
    private Long bankid;

    /**
     * 字段: tra_orderinfom.roomid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 主播房间id
     *
     * @mbggenerated
     */
    private Long roomid;

    /**
     * 字段: tra_orderinfom.lotkindid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 彩种id
     *
     * @mbggenerated
     */
    private Long lotkindid;

    /**
     * 字段: tra_orderinfom.sschistoryid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 時時彩開獎id
     *
     * @mbggenerated
     */
    private Long sschistoryid;

    /**
     * 字段: tra_orderinfom.oddsid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 投注項id
     *
     * @mbggenerated
     */
    private Long oddsid;

    /**
     * 字段: tra_orderinfom.chekindid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 棋牌分類id
     *
     * @mbggenerated
     */
    private Long chekindid;

    /**
     * 字段: tra_orderinfom.tpaysetid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 三方支付id
     *
     * @mbggenerated
     */
    private Long tpaysetid;

    /**
     * 字段: tra_orderinfom.ordertype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 2<br/>
     * 说明: 订单类型 1在线支付 2线下支付 3在線提現 4線下提現 5彩票購彩 6彩票兌獎 7棋牌上分 8棋牌下分 9其他收入(發帖/推薦)  10其他支出(打賞)  11代理結算收入 14 代充人入款 15 代充人给会员充值

     *
     * @mbggenerated
     */
    private Integer ordertype;

    /**
     * 字段: tra_orderinfom.orderno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 40<br/>
     * 说明: 订单编号4码年+2码月+8码流水号，生成逻辑：取当前年月，然后从数据库中取当前年月最大订单号，然后将后面8位流水号+1
     *
     * @mbggenerated
     */
    private String orderno;

    /**
     * 字段: tra_orderinfom.accno<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 会员标识号
     *
     * @mbggenerated
     */
    private String accno;

    /**
     * 字段: tra_orderinfom.paycode<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 64<br/>
     * 说明: 支付标示第三方支付标示号，如支付宝的订单号或微信的prepay_id等
     *
     * @mbggenerated
     */
    private String paycode;

    /**
     * 字段: tra_orderinfom.orderdate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 订单日期
     *
     * @mbggenerated
     */
    private Date orderdate;

    /**
     * 字段: tra_orderinfom.expiredate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 过期时间
     *
     * @mbggenerated
     */
    private Date expiredate;

    /**
     * 字段: tra_orderinfom.paytype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 支付方式  JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付   NETBANK 网银转账  WECHAT 微信收款  ALIPAY 支付宝支付
     *
     * @mbggenerated
     */
    private String paytype;

    /**
     * 字段: tra_orderinfom.oldamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 订单原价
     *
     * @mbggenerated
     */
    private BigDecimal oldamt;

    /**
     * 字段: tra_orderinfom.sumamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 订单总金额
     *
     * @mbggenerated
     */
    private BigDecimal sumamt;

    /**
     * 字段: tra_orderinfom.realamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 实付金额
     *
     * @mbggenerated
     */
    private BigDecimal realamt;

    /**
     * 字段: tra_orderinfom.isinvoice<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 是否开具发票0是 9否
     *
     * @mbggenerated
     */
    private Integer isinvoice;

    /**
     * 字段: tra_orderinfom.orderstatus<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 订单状态 ord01新订单 ord04待付款 ord05提現申請 ord06提現取消 ord07提現處理中 ord08已付款  ord09用户取消 ord10已评价  ord11已退款 ord12已提現  ord98已拉取棋牌訂單 ord99已过期   
     *
     * @mbggenerated
     */
    private String orderstatus;

    /**
     * 字段: tra_orderinfom.accountstatus<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 结算状态 acc04待结算（未打码）  acc08已结算（已打码）  acc99已取消（不可结算）
     *
     * @mbggenerated
     */
    private String accountstatus;

    /**
     * 字段: tra_orderinfom.cancelreason<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 取消订单原因
     *
     * @mbggenerated
     */
    private String cancelreason;

    /**
     * 字段: tra_orderinfom.payimg<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1000<br/>
     * 说明: 支付凭证截图 多张以“，”分隔
     *
     * @mbggenerated
     */
    private String payimg;

    /**
     * 字段: tra_orderinfom.paywechat<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 支付处理微信号
     *
     * @mbggenerated
     */
    private String paywechat;

    /**
     * 字段: tra_orderinfom.paydate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 支付时间
     *
     * @mbggenerated
     */
    private Date paydate;

    /**
     * 字段: tra_orderinfom.payuser<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 匯款姓名
     *
     * @mbggenerated
     */
    private String payuser;

    /**
     * 字段: tra_orderinfom.paynote<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 匯款備註
     *
     * @mbggenerated
     */
    private String paynote;

    /**
     * 字段: tra_orderinfom.ordernote<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 备注
     *
     * @mbggenerated
     */
    private String ordernote;

    /**
     * 字段: tra_orderinfom.is_delete<br/>
     * 必填: true<br/>
     * 缺省: b'0'<br/>
     * 长度: 1<br/>
     * 说明: 是否删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 字段: tra_orderinfom.create_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 创建人
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 字段: tra_orderinfom.create_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 字段: tra_orderinfom.update_user<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 32<br/>
     * 说明: 最后修改人
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * 字段: tra_orderinfom.update_time<br/>
     * 必填: true<br/>
     * 缺省: CURRENT_TIMESTAMP<br/>
     * 长度: 19<br/>
     * 说明: 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * 字段: tra_orderinfom.source<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    private String source;

    /**
     * 字段: tra_orderinfom.cleanid<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 19<br/>
     * 说明: 代理结算id,大于0，表示结算过，可追溯被何处结算
     *
     * @mbggenerated
     */
    private Long cleanid;

    /**
     * 字段: tra_orderinfom.buy_vip<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 购买VIP的等级,为空则表示不购买等级
     *
     * @mbggenerated
     */
    private Long buyVip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tra_orderinfom
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return tra_orderinfom.orderid: 订单id
     *
     * @mbggenerated
     */
    public Long getOrderid() {
        return orderid;
    }

    /**
     * 字段: tra_orderinfom.orderid<br/>
     * 主键: 自动增长<br/>
     * 必填: true<br/>
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
     * @return tra_orderinfom.mealid: 充值套餐id
     *
     * @mbggenerated
     */
    public Long getMealid() {
        return mealid;
    }

    /**
     * 字段: tra_orderinfom.mealid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 充值套餐id
     *
     * @mbggenerated
     */
    public void setMealid(Long mealid) {
        this.mealid = mealid;
    }

    /**
     * @return tra_orderinfom.bankid: 入款账户id
     *
     * @mbggenerated
     */
    public Long getBankid() {
        return bankid;
    }

    /**
     * 字段: tra_orderinfom.bankid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 入款账户id
     *
     * @mbggenerated
     */
    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    /**
     * @return tra_orderinfom.roomid: 主播房间id
     *
     * @mbggenerated
     */
    public Long getRoomid() {
        return roomid;
    }

    /**
     * 字段: tra_orderinfom.roomid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 主播房间id
     *
     * @mbggenerated
     */
    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    /**
     * @return tra_orderinfom.lotkindid: 彩种id
     *
     * @mbggenerated
     */
    public Long getLotkindid() {
        return lotkindid;
    }

    /**
     * 字段: tra_orderinfom.lotkindid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 彩种id
     *
     * @mbggenerated
     */
    public void setLotkindid(Long lotkindid) {
        this.lotkindid = lotkindid;
    }

    /**
     * @return tra_orderinfom.sschistoryid: 時時彩開獎id
     *
     * @mbggenerated
     */
    public Long getSschistoryid() {
        return sschistoryid;
    }

    /**
     * 字段: tra_orderinfom.sschistoryid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 時時彩開獎id
     *
     * @mbggenerated
     */
    public void setSschistoryid(Long sschistoryid) {
        this.sschistoryid = sschistoryid;
    }

    /**
     * @return tra_orderinfom.oddsid: 投注項id
     *
     * @mbggenerated
     */
    public Long getOddsid() {
        return oddsid;
    }

    /**
     * 字段: tra_orderinfom.oddsid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 投注項id
     *
     * @mbggenerated
     */
    public void setOddsid(Long oddsid) {
        this.oddsid = oddsid;
    }

    /**
     * @return tra_orderinfom.chekindid: 棋牌分類id
     *
     * @mbggenerated
     */
    public Long getChekindid() {
        return chekindid;
    }

    /**
     * 字段: tra_orderinfom.chekindid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 棋牌分類id
     *
     * @mbggenerated
     */
    public void setChekindid(Long chekindid) {
        this.chekindid = chekindid;
    }

    /**
     * @return tra_orderinfom.tpaysetid: 三方支付id
     *
     * @mbggenerated
     */
    public Long getTpaysetid() {
        return tpaysetid;
    }

    /**
     * 字段: tra_orderinfom.tpaysetid<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 三方支付id
     *
     * @mbggenerated
     */
    public void setTpaysetid(Long tpaysetid) {
        this.tpaysetid = tpaysetid;
    }

    /**
     * @return tra_orderinfom.ordertype: 订单类型 1在线支付 2线下支付 3在線提現 4線下提現 5彩票購彩 6彩票兌獎 7棋牌上分 8棋牌下分 9其他收入(發帖/推薦)  10其他支出(打賞)  11代理結算收入 14 代充人入款 15 代充人给会员充值

     *
     * @mbggenerated
     */
    public Integer getOrdertype() {
        return ordertype;
    }

    /**
     * 字段: tra_orderinfom.ordertype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 2<br/>
     * 说明: 订单类型 1在线支付 2线下支付 3在線提現 4線下提現 5彩票購彩 6彩票兌獎 7棋牌上分 8棋牌下分 9其他收入(發帖/推薦)  10其他支出(打賞)  11代理結算收入 14 代充人入款 15 代充人给会员充值

     *
     * @mbggenerated
     */
    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    /**
     * @return tra_orderinfom.orderno: 订单编号4码年+2码月+8码流水号，生成逻辑：取当前年月，然后从数据库中取当前年月最大订单号，然后将后面8位流水号+1
     *
     * @mbggenerated
     */
    public String getOrderno() {
        return orderno;
    }

    /**
     * 字段: tra_orderinfom.orderno<br/>
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
     * @return tra_orderinfom.accno: 会员标识号
     *
     * @mbggenerated
     */
    public String getAccno() {
        return accno;
    }

    /**
     * 字段: tra_orderinfom.accno<br/>
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
     * @return tra_orderinfom.paycode: 支付标示第三方支付标示号，如支付宝的订单号或微信的prepay_id等
     *
     * @mbggenerated
     */
    public String getPaycode() {
        return paycode;
    }

    /**
     * 字段: tra_orderinfom.paycode<br/>
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
     * @return tra_orderinfom.orderdate: 订单日期
     *
     * @mbggenerated
     */
    public Date getOrderdate() {
        return orderdate;
    }

    /**
     * 字段: tra_orderinfom.orderdate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 订单日期
     *
     * @mbggenerated
     */
    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    /**
     * @return tra_orderinfom.expiredate: 过期时间
     *
     * @mbggenerated
     */
    public Date getExpiredate() {
        return expiredate;
    }

    /**
     * 字段: tra_orderinfom.expiredate<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 过期时间
     *
     * @mbggenerated
     */
    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    /**
     * @return tra_orderinfom.paytype: 支付方式  JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付   NETBANK 网银转账  WECHAT 微信收款  ALIPAY 支付宝支付
     *
     * @mbggenerated
     */
    public String getPaytype() {
        return paytype;
    }

    /**
     * 字段: tra_orderinfom.paytype<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 支付方式  JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付   NETBANK 网银转账  WECHAT 微信收款  ALIPAY 支付宝支付
     *
     * @mbggenerated
     */
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    /**
     * @return tra_orderinfom.oldamt: 订单原价
     *
     * @mbggenerated
     */
    public BigDecimal getOldamt() {
        return oldamt;
    }

    /**
     * 字段: tra_orderinfom.oldamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 订单原价
     *
     * @mbggenerated
     */
    public void setOldamt(BigDecimal oldamt) {
        this.oldamt = oldamt;
    }

    /**
     * @return tra_orderinfom.sumamt: 订单总金额
     *
     * @mbggenerated
     */
    public BigDecimal getSumamt() {
        return sumamt;
    }

    /**
     * 字段: tra_orderinfom.sumamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 订单总金额
     *
     * @mbggenerated
     */
    public void setSumamt(BigDecimal sumamt) {
        this.sumamt = sumamt;
    }

    /**
     * @return tra_orderinfom.realamt: 实付金额
     *
     * @mbggenerated
     */
    public BigDecimal getRealamt() {
        return realamt;
    }

    /**
     * 字段: tra_orderinfom.realamt<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 16<br/>
     * 说明: 实付金额
     *
     * @mbggenerated
     */
    public void setRealamt(BigDecimal realamt) {
        this.realamt = realamt;
    }

    /**
     * @return tra_orderinfom.isinvoice: 是否开具发票0是 9否
     *
     * @mbggenerated
     */
    public Integer getIsinvoice() {
        return isinvoice;
    }

    /**
     * 字段: tra_orderinfom.isinvoice<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1<br/>
     * 说明: 是否开具发票0是 9否
     *
     * @mbggenerated
     */
    public void setIsinvoice(Integer isinvoice) {
        this.isinvoice = isinvoice;
    }

    /**
     * @return tra_orderinfom.orderstatus: 订单状态 ord01新订单 ord04待付款 ord05提現申請 ord06提現取消 ord07提現處理中 ord08已付款  ord09用户取消 ord10已评价  ord11已退款 ord12已提現  ord98已拉取棋牌訂單 ord99已过期   
     *
     * @mbggenerated
     */
    public String getOrderstatus() {
        return orderstatus;
    }

    /**
     * 字段: tra_orderinfom.orderstatus<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 订单状态 ord01新订单 ord04待付款 ord05提現申請 ord06提現取消 ord07提現處理中 ord08已付款  ord09用户取消 ord10已评价  ord11已退款 ord12已提現  ord98已拉取棋牌訂單 ord99已过期   
     *
     * @mbggenerated
     */
    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    /**
     * @return tra_orderinfom.accountstatus: 结算状态 acc04待结算（未打码）  acc08已结算（已打码）  acc99已取消（不可结算）
     *
     * @mbggenerated
     */
    public String getAccountstatus() {
        return accountstatus;
    }

    /**
     * 字段: tra_orderinfom.accountstatus<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 20<br/>
     * 说明: 结算状态 acc04待结算（未打码）  acc08已结算（已打码）  acc99已取消（不可结算）
     *
     * @mbggenerated
     */
    public void setAccountstatus(String accountstatus) {
        this.accountstatus = accountstatus;
    }

    /**
     * @return tra_orderinfom.cancelreason: 取消订单原因
     *
     * @mbggenerated
     */
    public String getCancelreason() {
        return cancelreason;
    }

    /**
     * 字段: tra_orderinfom.cancelreason<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 取消订单原因
     *
     * @mbggenerated
     */
    public void setCancelreason(String cancelreason) {
        this.cancelreason = cancelreason;
    }

    /**
     * @return tra_orderinfom.payimg: 支付凭证截图 多张以“，”分隔
     *
     * @mbggenerated
     */
    public String getPayimg() {
        return payimg;
    }

    /**
     * 字段: tra_orderinfom.payimg<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 1000<br/>
     * 说明: 支付凭证截图 多张以“，”分隔
     *
     * @mbggenerated
     */
    public void setPayimg(String payimg) {
        this.payimg = payimg;
    }

    /**
     * @return tra_orderinfom.paywechat: 支付处理微信号
     *
     * @mbggenerated
     */
    public String getPaywechat() {
        return paywechat;
    }

    /**
     * 字段: tra_orderinfom.paywechat<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 支付处理微信号
     *
     * @mbggenerated
     */
    public void setPaywechat(String paywechat) {
        this.paywechat = paywechat;
    }

    /**
     * @return tra_orderinfom.paydate: 支付时间
     *
     * @mbggenerated
     */
    public Date getPaydate() {
        return paydate;
    }

    /**
     * 字段: tra_orderinfom.paydate<br/>
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
     * @return tra_orderinfom.payuser: 匯款姓名
     *
     * @mbggenerated
     */
    public String getPayuser() {
        return payuser;
    }

    /**
     * 字段: tra_orderinfom.payuser<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 100<br/>
     * 说明: 匯款姓名
     *
     * @mbggenerated
     */
    public void setPayuser(String payuser) {
        this.payuser = payuser;
    }

    /**
     * @return tra_orderinfom.paynote: 匯款備註
     *
     * @mbggenerated
     */
    public String getPaynote() {
        return paynote;
    }

    /**
     * 字段: tra_orderinfom.paynote<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 匯款備註
     *
     * @mbggenerated
     */
    public void setPaynote(String paynote) {
        this.paynote = paynote;
    }

    /**
     * @return tra_orderinfom.ordernote: 备注
     *
     * @mbggenerated
     */
    public String getOrdernote() {
        return ordernote;
    }

    /**
     * 字段: tra_orderinfom.ordernote<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 400<br/>
     * 说明: 备注
     *
     * @mbggenerated
     */
    public void setOrdernote(String ordernote) {
        this.ordernote = ordernote;
    }

    /**
     * @return tra_orderinfom.is_delete: 是否删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 字段: tra_orderinfom.is_delete<br/>
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
     * @return tra_orderinfom.create_user: 创建人
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 字段: tra_orderinfom.create_user<br/>
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
     * @return tra_orderinfom.create_time: 创建时间
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 字段: tra_orderinfom.create_time<br/>
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
     * @return tra_orderinfom.update_user: 最后修改人
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 字段: tra_orderinfom.update_user<br/>
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
     * @return tra_orderinfom.update_time: 更新时间
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 字段: tra_orderinfom.update_time<br/>
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
     * @return tra_orderinfom.source: 
     *
     * @mbggenerated
     */
    public String getSource() {
        return source;
    }

    /**
     * 字段: tra_orderinfom.source<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 50<br/>
     * 说明: 
     *
     * @mbggenerated
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return tra_orderinfom.cleanid: 代理结算id,大于0，表示结算过，可追溯被何处结算
     *
     * @mbggenerated
     */
    public Long getCleanid() {
        return cleanid;
    }

    /**
     * 字段: tra_orderinfom.cleanid<br/>
     * 必填: false<br/>
     * 缺省: 0<br/>
     * 长度: 19<br/>
     * 说明: 代理结算id,大于0，表示结算过，可追溯被何处结算
     *
     * @mbggenerated
     */
    public void setCleanid(Long cleanid) {
        this.cleanid = cleanid;
    }

    /**
     * @return tra_orderinfom.buy_vip: 购买VIP的等级,为空则表示不购买等级
     *
     * @mbggenerated
     */
    public Long getBuyVip() {
        return buyVip;
    }

    /**
     * 字段: tra_orderinfom.buy_vip<br/>
     * 必填: false<br/>
     * 缺省: <br/>
     * 长度: 19<br/>
     * 说明: 购买VIP的等级,为空则表示不购买等级
     *
     * @mbggenerated
     */
    public void setBuyVip(Long buyVip) {
        this.buyVip = buyVip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_orderinfom
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
        TraOrderinfom other = (TraOrderinfom) that;
        return (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getMealid() == null ? other.getMealid() == null : this.getMealid().equals(other.getMealid()))
            && (this.getBankid() == null ? other.getBankid() == null : this.getBankid().equals(other.getBankid()))
            && (this.getRoomid() == null ? other.getRoomid() == null : this.getRoomid().equals(other.getRoomid()))
            && (this.getLotkindid() == null ? other.getLotkindid() == null : this.getLotkindid().equals(other.getLotkindid()))
            && (this.getSschistoryid() == null ? other.getSschistoryid() == null : this.getSschistoryid().equals(other.getSschistoryid()))
            && (this.getOddsid() == null ? other.getOddsid() == null : this.getOddsid().equals(other.getOddsid()))
            && (this.getChekindid() == null ? other.getChekindid() == null : this.getChekindid().equals(other.getChekindid()))
            && (this.getTpaysetid() == null ? other.getTpaysetid() == null : this.getTpaysetid().equals(other.getTpaysetid()))
            && (this.getOrdertype() == null ? other.getOrdertype() == null : this.getOrdertype().equals(other.getOrdertype()))
            && (this.getOrderno() == null ? other.getOrderno() == null : this.getOrderno().equals(other.getOrderno()))
            && (this.getAccno() == null ? other.getAccno() == null : this.getAccno().equals(other.getAccno()))
            && (this.getPaycode() == null ? other.getPaycode() == null : this.getPaycode().equals(other.getPaycode()))
            && (this.getOrderdate() == null ? other.getOrderdate() == null : this.getOrderdate().equals(other.getOrderdate()))
            && (this.getExpiredate() == null ? other.getExpiredate() == null : this.getExpiredate().equals(other.getExpiredate()))
            && (this.getPaytype() == null ? other.getPaytype() == null : this.getPaytype().equals(other.getPaytype()))
            && (this.getOldamt() == null ? other.getOldamt() == null : this.getOldamt().equals(other.getOldamt()))
            && (this.getSumamt() == null ? other.getSumamt() == null : this.getSumamt().equals(other.getSumamt()))
            && (this.getRealamt() == null ? other.getRealamt() == null : this.getRealamt().equals(other.getRealamt()))
            && (this.getIsinvoice() == null ? other.getIsinvoice() == null : this.getIsinvoice().equals(other.getIsinvoice()))
            && (this.getOrderstatus() == null ? other.getOrderstatus() == null : this.getOrderstatus().equals(other.getOrderstatus()))
            && (this.getAccountstatus() == null ? other.getAccountstatus() == null : this.getAccountstatus().equals(other.getAccountstatus()))
            && (this.getCancelreason() == null ? other.getCancelreason() == null : this.getCancelreason().equals(other.getCancelreason()))
            && (this.getPayimg() == null ? other.getPayimg() == null : this.getPayimg().equals(other.getPayimg()))
            && (this.getPaywechat() == null ? other.getPaywechat() == null : this.getPaywechat().equals(other.getPaywechat()))
            && (this.getPaydate() == null ? other.getPaydate() == null : this.getPaydate().equals(other.getPaydate()))
            && (this.getPayuser() == null ? other.getPayuser() == null : this.getPayuser().equals(other.getPayuser()))
            && (this.getPaynote() == null ? other.getPaynote() == null : this.getPaynote().equals(other.getPaynote()))
            && (this.getOrdernote() == null ? other.getOrdernote() == null : this.getOrdernote().equals(other.getOrdernote()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getCleanid() == null ? other.getCleanid() == null : this.getCleanid().equals(other.getCleanid()))
            && (this.getBuyVip() == null ? other.getBuyVip() == null : this.getBuyVip().equals(other.getBuyVip()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_orderinfom
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getMealid() == null) ? 0 : getMealid().hashCode());
        result = prime * result + ((getBankid() == null) ? 0 : getBankid().hashCode());
        result = prime * result + ((getRoomid() == null) ? 0 : getRoomid().hashCode());
        result = prime * result + ((getLotkindid() == null) ? 0 : getLotkindid().hashCode());
        result = prime * result + ((getSschistoryid() == null) ? 0 : getSschistoryid().hashCode());
        result = prime * result + ((getOddsid() == null) ? 0 : getOddsid().hashCode());
        result = prime * result + ((getChekindid() == null) ? 0 : getChekindid().hashCode());
        result = prime * result + ((getTpaysetid() == null) ? 0 : getTpaysetid().hashCode());
        result = prime * result + ((getOrdertype() == null) ? 0 : getOrdertype().hashCode());
        result = prime * result + ((getOrderno() == null) ? 0 : getOrderno().hashCode());
        result = prime * result + ((getAccno() == null) ? 0 : getAccno().hashCode());
        result = prime * result + ((getPaycode() == null) ? 0 : getPaycode().hashCode());
        result = prime * result + ((getOrderdate() == null) ? 0 : getOrderdate().hashCode());
        result = prime * result + ((getExpiredate() == null) ? 0 : getExpiredate().hashCode());
        result = prime * result + ((getPaytype() == null) ? 0 : getPaytype().hashCode());
        result = prime * result + ((getOldamt() == null) ? 0 : getOldamt().hashCode());
        result = prime * result + ((getSumamt() == null) ? 0 : getSumamt().hashCode());
        result = prime * result + ((getRealamt() == null) ? 0 : getRealamt().hashCode());
        result = prime * result + ((getIsinvoice() == null) ? 0 : getIsinvoice().hashCode());
        result = prime * result + ((getOrderstatus() == null) ? 0 : getOrderstatus().hashCode());
        result = prime * result + ((getAccountstatus() == null) ? 0 : getAccountstatus().hashCode());
        result = prime * result + ((getCancelreason() == null) ? 0 : getCancelreason().hashCode());
        result = prime * result + ((getPayimg() == null) ? 0 : getPayimg().hashCode());
        result = prime * result + ((getPaywechat() == null) ? 0 : getPaywechat().hashCode());
        result = prime * result + ((getPaydate() == null) ? 0 : getPaydate().hashCode());
        result = prime * result + ((getPayuser() == null) ? 0 : getPayuser().hashCode());
        result = prime * result + ((getPaynote() == null) ? 0 : getPaynote().hashCode());
        result = prime * result + ((getOrdernote() == null) ? 0 : getOrdernote().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getCleanid() == null) ? 0 : getCleanid().hashCode());
        result = prime * result + ((getBuyVip() == null) ? 0 : getBuyVip().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_orderinfom
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderid=").append(orderid);
        sb.append(", mealid=").append(mealid);
        sb.append(", bankid=").append(bankid);
        sb.append(", roomid=").append(roomid);
        sb.append(", lotkindid=").append(lotkindid);
        sb.append(", sschistoryid=").append(sschistoryid);
        sb.append(", oddsid=").append(oddsid);
        sb.append(", chekindid=").append(chekindid);
        sb.append(", tpaysetid=").append(tpaysetid);
        sb.append(", ordertype=").append(ordertype);
        sb.append(", orderno=").append(orderno);
        sb.append(", accno=").append(accno);
        sb.append(", paycode=").append(paycode);
        sb.append(", orderdate=").append(orderdate);
        sb.append(", expiredate=").append(expiredate);
        sb.append(", paytype=").append(paytype);
        sb.append(", oldamt=").append(oldamt);
        sb.append(", sumamt=").append(sumamt);
        sb.append(", realamt=").append(realamt);
        sb.append(", isinvoice=").append(isinvoice);
        sb.append(", orderstatus=").append(orderstatus);
        sb.append(", accountstatus=").append(accountstatus);
        sb.append(", cancelreason=").append(cancelreason);
        sb.append(", payimg=").append(payimg);
        sb.append(", paywechat=").append(paywechat);
        sb.append(", paydate=").append(paydate);
        sb.append(", payuser=").append(payuser);
        sb.append(", paynote=").append(paynote);
        sb.append(", ordernote=").append(ordernote);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", source=").append(source);
        sb.append(", cleanid=").append(cleanid);
        sb.append(", buyVip=").append(buyVip);
        sb.append("]");
        return sb.toString();
    }
}