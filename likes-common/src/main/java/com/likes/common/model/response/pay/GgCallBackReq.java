package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 谷歌金服回调请求
 * @Version 1.0
 **/
public class GgCallBackReq extends BaseCallBackReq {

    /** 金额字符串 */
    private String totalFee;

    /** 商户编号 */
    private Integer customerId;

    /** 订单备注说明 */
    private String remark;

    /** md5验证签名串 */
    private String sign;

    public GgCallBackReq() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }
}
