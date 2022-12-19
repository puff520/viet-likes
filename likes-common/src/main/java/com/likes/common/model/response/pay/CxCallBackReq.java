package com.likes.common.model.response.pay;

/**
 * @ClassName AaCallBackReq
 * @Description
 * @Version 1.0
 **/
public class CxCallBackReq extends BaseCallBackReq {
    private String memberid; //商户号
    private String datetime;//支付时间
    private String code = "1395699017";//安全码
    private String sign;


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
