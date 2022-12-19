package com.likes.common.model.response.pay;

/**
 * @ClassName XfCallBackReq
 * @Description 一亿伽
 * @Version 1.0
 **/
public class YjCallBackReq extends BaseCallBackReq {

    private String sign;
    private String initAmount; //订单初始金额
    private String merchantId; //商户编号ID

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getInitAmount() {
        return initAmount;
    }

    public void setInitAmount(String initAmount) {
        this.initAmount = initAmount;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
