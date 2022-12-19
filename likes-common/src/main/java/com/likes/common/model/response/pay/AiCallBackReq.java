package com.likes.common.model.response.pay;

import java.math.BigDecimal;

/**
 * @ClassName
 * @Description 爱支付回调请求
 * @Version 1.0
 **/
public class AiCallBackReq extends BaseCallBackReq {

    /** 金额字符串 */
    private String moneyString;

    /** 入账金额字符串 */
    private String incomeString;

    /** 商户ID */
    private String mchId;

    /** 应用ID */
    private String appId;

    /** 支付产品ID */
    private Integer productId;

    /** 入账金额 */
    private BigDecimal income;

    /** 状态 */
    private Integer statusInt;

    /** 渠道订单号 */
    private String channelOrderNo;

    /** 渠道数据包 */
    private String channelAttach;

    /** 扩展参数1 */
    private String param1;

    /** 扩展参数2 */
    private String param2;

    /** 支付成功时间 */
    private Long paySuccTime;

    /** 通知类型 */
    private Integer backType;

    /** 签名 */
    private String sign;

    public AiCallBackReq() {
    }

    public String getMoneyString() {
        return moneyString;
    }

    public void setMoneyString(String moneyString) {
        this.moneyString = moneyString;
    }

    public String getIncomeString() {
        return incomeString;
    }

    public void setIncomeString(String incomeString) {
        this.incomeString = incomeString;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public Integer getStatusInt() {
        return statusInt;
    }

    public void setStatusInt(Integer statusInt) {
        this.statusInt = statusInt;
    }

    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public void setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
    }

    public String getChannelAttach() {
        return channelAttach;
    }

    public void setChannelAttach(String channelAttach) {
        this.channelAttach = channelAttach;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public Long getPaySuccTime() {
        return paySuccTime;
    }

    public void setPaySuccTime(Long paySuccTime) {
        this.paySuccTime = paySuccTime;
    }

    public Integer getBackType() {
        return backType;
    }

    public void setBackType(Integer backType) {
        this.backType = backType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
