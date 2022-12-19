package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 谷歌金服 创建订单 返回结果
 * @Author yeezy
 * @Date 2019/11/7 15:37
 * @Version 1.0
 **/
public class GgPayResp extends BasePayResp {

    /** 错误码 */
    private String errCode;

    /** 商户订单号 */
    private String sdOrderNo;

    /** 订单金额 */
    private String money;

    /** url类型 */
    private String useType;

    public GgPayResp() {
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getSdOrderNo() {
        return sdOrderNo;
    }

    public void setSdOrderNo(String sdOrderNo) {
        this.sdOrderNo = sdOrderNo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }
}
