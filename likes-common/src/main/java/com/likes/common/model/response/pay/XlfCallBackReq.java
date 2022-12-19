package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description
 * @Version 1.0
 **/
public class XlfCallBackReq extends BaseCallBackReq {

    private String  sign;
    private String  memberid;
    private String datetime; //商户订单日期
    private String returncode; //响应码

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

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
}
