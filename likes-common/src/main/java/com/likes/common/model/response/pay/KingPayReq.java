package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description king支付请求
 * @Version 1.0
 **/
public class KingPayReq extends BasePayReq {

    /** 支付用户 */
    private Integer userId;

    /** 请求的地址 */
    private String url;

    /** 请求来源 */
    private String source;

    /** 签名 */
    private String sign;

    /** MD5key */
    private String md5Key;

    /** 提交时间 */
    private String payApplyDate;

    /** 银行编码 */
    private String payBankCode;

    /** 附加字段 */
    private String payAttach;

    /** 商品名称 */
    private String payProductName;

    /** 商品描述 */
    private String payProductDesc;

    /** 商户链接地址 */
    private String payProductUrl;

    public KingPayReq() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }

    public String getPayApplyDate() {
        return payApplyDate;
    }

    public void setPayApplyDate(String payApplyDate) {
        this.payApplyDate = payApplyDate;
    }

    public String getPayBankCode() {
        return payBankCode;
    }

    public void setPayBankCode(String payBankCode) {
        this.payBankCode = payBankCode;
    }

    public String getPayAttach() {
        return payAttach;
    }

    public void setPayAttach(String payAttach) {
        this.payAttach = payAttach;
    }

    public String getPayProductName() {
        return payProductName;
    }

    public void setPayProductName(String payProductName) {
        this.payProductName = payProductName;
    }

    public String getPayProductDesc() {
        return payProductDesc;
    }

    public void setPayProductDesc(String payProductDesc) {
        this.payProductDesc = payProductDesc;
    }

    public String getPayProductUrl() {
        return payProductUrl;
    }

    public void setPayProductUrl(String payProductUrl) {
        this.payProductUrl = payProductUrl;
    }
}
