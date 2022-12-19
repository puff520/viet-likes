package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description ESPAY回调请求
 * @Version 1.0
 **/
public class EsCallBackReq extends BaseCallBackReq {

    /** 金额字符串 */
    private String moneyString;

    /** 商户编号 */
    private String mchId;

    /** 原始订单号 */
    private String originalTradeNo;

    /** 发送请求的时间 */
    private String notifyTime;

    /** 签名 */
    private String sign;

    public EsCallBackReq() {
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getOriginalTradeNo() {
        return originalTradeNo;
    }

    public void setOriginalTradeNo(String originalTradeNo) {
        this.originalTradeNo = originalTradeNo;
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMoneyString() {
        return moneyString;
    }

    public void setMoneyString(String moneyString) {
        this.moneyString = moneyString;
    }
}
