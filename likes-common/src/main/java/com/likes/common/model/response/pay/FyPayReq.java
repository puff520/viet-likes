package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 创世参数
 * @Version 1.0
 **/
public class FyPayReq extends BasePayReq {
    private Integer userId; //支付用户
    private String url; //请求的地址
    private String source; //请求来源
    private String md5Key; //MD5key
    private String random;//随机串

    public FyPayReq() {
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
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

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }
}
