package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 默默支付回调请求
 * @Version 1.0
 **/
public class MoCallBackReq extends BaseCallBackReq {

    /** 金额字符串 */
    private String moneyString;

    /** 版本号 */
    private String version;

    /** 字符集 */
    private String charset;

    /** 签名方式 */
    private String signType;

    /** 返回信息 */
    private String message;

    /** 业务结果 */
    private String resultCode;

    /** 商户号 */
    private String mchId;

    /** 随机字符串 */
    private String nonceStr;

    /** 错误代码 */
    private String errCode;

    /** 错误代码描述 */
    private String errMsg;

    /** 签名 */
    private String sign;

    /** 交易类型 */
    private String tradeType;

    /** 支付结果 */
    private Integer payResult;

    /** 支付完成时间 */
    private String timeEnd;

    public MoCallBackReq() {
    }

    public String getMoneyString() {
        return moneyString;
    }

    public void setMoneyString(String moneyString) {
        this.moneyString = moneyString;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getPayResult() {
        return payResult;
    }

    public void setPayResult(Integer payResult) {
        this.payResult = payResult;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
