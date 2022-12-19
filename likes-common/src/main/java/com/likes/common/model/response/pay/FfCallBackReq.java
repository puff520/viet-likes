package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date
 * @Version 1.0
 **/
public class FfCallBackReq extends BaseCallBackReq {
    private String sign; //签名
    private String paytime; //支付时间
    private String merchantId; //商户编号ID
    private String attach;

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
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
