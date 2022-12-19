package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description ESPAY回调请求
 * @Version 1.0
 **/
public class SfCallBackReq extends BaseCallBackReq {

    /** 金额字符串 */
    private String moneyString;

    /** IP地址 */
    private String message;

    /** 回掉类型 */
    private String callbackType;

    /** 签名 */
    private String sign;

    public SfCallBackReq() {
    }

    public String getMoneyString() {
        return moneyString;
    }

    public void setMoneyString(String moneyString) {
        this.moneyString = moneyString;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(String callbackType) {
        this.callbackType = callbackType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
