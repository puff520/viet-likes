package com.likes.common.model.response.pay;

/**
 * @ClassName CsCallBackReq
 * @Description Fy
 * @Version 1.0
 **/
public class ECallBackReq extends BaseCallBackReq {

    private String attach;//应用ID
    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
