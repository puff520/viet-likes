package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description ESPAY 创建订单 返回结果
 * @Author yeezy
 * @Date 2019/11/7 15:37
 * @Version 1.0
 **/
public class EsPayResp extends BasePayResp {

    /** 网关返回码 0成功；其他失败 */
    private String code;

    /** 商户编号 */
    private String mchId;

    /** 平台订单号 */
    private String tradeNo;

    /** 商户订单号 */
    private String outTradeNo;

    /** 原始订单号 */
    private String originalTradeNo;

    /** 订单金额 */
    private String money;

    /** 支付地址 */
    private String payUrl;

    /** 付款二维码图片地址 */
    private String qrcodeUrl;

    /** 付款二维码图片内容 */
    private String qrcodeContent;

    /** 订单有效截止时间 */
    private String expiredTime;

    /** SDK内容 */
    private String sdkContent;

    public EsPayResp() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOriginalTradeNo() {
        return originalTradeNo;
    }

    public void setOriginalTradeNo(String originalTradeNo) {
        this.originalTradeNo = originalTradeNo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public String getQrcodeContent() {
        return qrcodeContent;
    }

    public void setQrcodeContent(String qrcodeContent) {
        this.qrcodeContent = qrcodeContent;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getSdkContent() {
        return sdkContent;
    }

    public void setSdkContent(String sdkContent) {
        this.sdkContent = sdkContent;
    }
}
