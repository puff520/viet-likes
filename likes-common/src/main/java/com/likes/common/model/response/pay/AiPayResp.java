package com.likes.common.model.response.pay;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName
 * @Description 爱支付 创建订单 返回结果
 * @Author yeezy
 * @Date 2019/11/7 15:37
 * @Version 1.0
 **/
public class AiPayResp extends BasePayResp {

    /** 返回状态码 */
    private String retCode;

    /** 签名 */
    private String sign;

    /** 支付订单号 */
    private String payOrderId;

    /** 支付参数 */
    private JSONObject payParams;

    public AiPayResp() {
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public JSONObject getPayParams() {
        return payParams;
    }

    public void setPayParams(JSONObject payParams) {
        this.payParams = payParams;
    }
}
