package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description
 * @Version 1.0
 **/
public class XlfPayReq extends BasePayReq {

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
     * 时间
     */
    private String payAapplydate;

    /**
     * MD5key
     */
    private String md5Key;

    /**
     * 商品名称
     */
    private String payProductname = "xlfzf";

    public XlfPayReq() {
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


    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }

    public String getPayAapplydate() {
        return payAapplydate;
    }

    public void setPayAapplydate(String payAapplydate) {
        this.payAapplydate = payAapplydate;
    }

    public String getPayProductname() {
        return payProductname;
    }

    public void setPayProductname(String payProductname) {
        this.payProductname = payProductname;
    }
}
