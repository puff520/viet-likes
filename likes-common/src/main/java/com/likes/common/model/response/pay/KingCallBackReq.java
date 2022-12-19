package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description king支付回调请求
 * @Version 1.0
 **/
public class KingCallBackReq extends BaseCallBackReq {

    /** 金额字符串 */
    private String moneyString;

    /** 商户编号 */
    private String memberId;

    /** 交易流水号 */
    private String transactionId;

    /** 交易时间 */
    private String dateTime;

    /** 扩展返回 */
    private String attach;

    /** 签名 */
    private String sign;

    /** 签名2 */
    private String sign2;

    public KingCallBackReq() {
    }

    public String getMoneyString() {
        return moneyString;
    }

    public void setMoneyString(String moneyString) {
        this.moneyString = moneyString;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign2() {
        return sign2;
    }

    public void setSign2(String sign2) {
        this.sign2 = sign2;
    }
}
