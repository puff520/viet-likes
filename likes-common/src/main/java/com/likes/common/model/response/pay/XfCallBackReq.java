package com.likes.common.model.response.pay;

/**
 * @ClassName XfCallBackReq
 * @Description 喜付
 * @Version 1.0
 **/
public class XfCallBackReq extends BaseCallBackReq {

    private String  sign;
    private String transDate; //商户订单日期
    private String version; //版本号

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    private String merchantId; //商户编号ID

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
