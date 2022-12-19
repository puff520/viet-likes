package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description ESPAY支付请求
 * @Version 1.0
 **/
public class EsPayReq extends BasePayReq {

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

    /** 订单标题 */
    private String subject;

    /** 订单描述 */
    private String body;

    /** 客户IP地址 */
    private String clientIp;

    /** 发送请求的时间 */
    private String timestamp;

    public EsPayReq() {
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
