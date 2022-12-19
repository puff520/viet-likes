package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 四方支付 创建订单 返回结果
 * @Author yeezy
 * @Date 2019/11/7 15:37
 * @Version 1.0
 **/
public class SfPayResp extends BasePayResp {

    /** 时间戳 */
    private String timestamp;

    /** 校验参数 */
    private String param;

    /** 支付类型 */
    private String paymentType;

    /** 签名 */
    private String sign;

    /** 商户订单号 */
    private String tradeNo;

    /** 平台订单号 */
    private String transNo;

    public SfPayResp() {
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
