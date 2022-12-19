package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 超凡支付参数
 * @Version 1.0
 **/
public class JzPayReq extends BasePayReq {
    private Integer userId; //支付用户
    private String url; //请求的地址
    private String source; //请求来源
    private String sign; //签名
    private String md5Key; //MD5key
    private String fxip;//IP
    private String fxdesc = "bbzf";

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
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

    public Integer getUserId() {
        return userId;
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

    public String getFxip() {
        return fxip;
    }

    public void setFxip(String fxip) {
        this.fxip = fxip;
    }

    public String getFxdesc() {
        return fxdesc;
    }

    public void setFxdesc(String fxdesc) {
        this.fxdesc = fxdesc;
    }
}
