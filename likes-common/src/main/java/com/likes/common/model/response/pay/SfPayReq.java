package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 四方支付请求
 * @Version 1.0
 **/
public class SfPayReq extends BasePayReq {

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

    /** 商品信息 */
    private String goodInfo;

    /** IP地址 */
    private String ip;

    /** 交易类型 */
    private String tradeType;

    public SfPayReq() {
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

    public String getGoodInfo() {
        return goodInfo;
    }

    public void setGoodInfo(String goodInfo) {
        this.goodInfo = goodInfo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
