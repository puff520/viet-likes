package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 99支付 回调请求
 * @Version 1.0
 **/
public class JjCallBackReq extends BaseCallBackReq {

    /** 返回状态码 */
    private Integer code;

    /** 交易标识 */
    private String resultCode;

    /** 商户号 */
    private String merchantNo;

    /** 签名 */
    private String sign;

    /** 商品描述 */
    private String body;

    /** 附加数据 */
    private String attach;

    /** 订单金额 */
    private String payMoney;

    /** 支付完成时间 */
    private String payTime;

    /** 交易状态信息 */
    private String message;

    public JjCallBackReq() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
