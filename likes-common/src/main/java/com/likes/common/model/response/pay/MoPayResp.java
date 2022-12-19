package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 默默支付 创建订单 返回结果
 * @Author yeezy
 * @Date 2019/11/7 15:37
 * @Version 1.0
 **/
public class MoPayResp extends BasePayResp {

    /** 版本号 */
    private String version;

    /** 字符集 */
    private String charSet;

    /** 签名方式 */
    private String signType;

    /** 业务结果 */
    private String resultCode;

    /** 签名 */
    private String sign;

    public MoPayResp() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
