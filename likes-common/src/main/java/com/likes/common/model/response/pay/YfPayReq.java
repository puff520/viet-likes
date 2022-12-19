package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 运
 * @Version 1.0
 **/
public class YfPayReq extends BasePayReq {
    private String url; //请求的地址
    private String source; //请求来源
    private String sign; //签名
    private String remark;//备注
    private String md5Key; //MD5key
    private String random;//随机串

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
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


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }
}
