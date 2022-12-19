package com.likes.common.model.response.pay;

/**
 * @ClassName XdCallBackReq
 * @Description 信达
 * @Version 1.0
 **/
public class XdCallBackReq extends BaseCallBackReq {
    private String sign; //签名
    private String merchantId; //商户编号ID
    private String remarks;//描述

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
