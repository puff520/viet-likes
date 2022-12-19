package com.likes.common.model.response.pay;

/**
 * @ClassName CfCallBackReq
 * @Description 超凡
 * @Version 1.0
 **/
public class CfCallBackReq extends BaseCallBackReq {
    private String sign; //签名
    private String code; //支付时间
    private String message; //支付时间
    private String merchantId; //商户编号ID
    private String signMethod;//描述
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
