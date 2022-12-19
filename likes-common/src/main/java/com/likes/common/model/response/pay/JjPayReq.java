package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description ESPAY支付请求
 * @Version 1.0
 **/
public class JjPayReq extends BasePayReq {

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

    /** 商品简单描述 */
    private String body;

    /** 附加数据 */
    private String attach;

    /** 用户终端ip */
    private String spbillIp;

    public JjPayReq() {
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

    public String getSpbillIp() {
        return spbillIp;
    }

    public void setSpbillIp(String spbillIp) {
        this.spbillIp = spbillIp;
    }
}
