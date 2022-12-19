package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description
 * @Version 1.0
 **/
public class AcePayReq extends BasePayReq {

    /**
     * 支付用户
     */
    private Integer userId;

    /**
     * 请求的地址
     */
    private String url;

    /**
     * 请求来源
     */
    private String source;

    /**
     * 返回类型
     */
    private String returnType = "json";

    /**
     * MD5key
     */
    private String md5Key;


    public AcePayReq() {
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }
}
