package com.likes.common.model.response.pay;

/**
 * @ClassName CsCallBackReq
 * @Description jz 橘子
 * @Version 1.0
 **/
public class LltCallBackReq extends BaseCallBackReq {
    private String merchantId; //商户号
    private String goodsName; //商品名称
    private String payTime;//支付成功时的时间
    private String sign;

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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}
