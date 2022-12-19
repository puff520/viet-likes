package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description
 * @Version 1.0
 **/
public class CxPayReq extends BasePayReq {

    /** 支付用户 */
    private Integer userId;

    /** 请求的地址 */
    private String url;

    /** 请求来源 */
    private String source;

    /** 提交时间 */
    private String payApplydate;

    /** MD5key */
    private String md5Key;

    /** 使用类型*/
    private String format = "json";

    /**商品名称 */
    private String payProductname = "cxzf";


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

    public String getPayApplydate() {
        return payApplydate;
    }

    public void setPayApplydate(String payApplydate) {
        this.payApplydate = payApplydate;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getPayProductname() {
        return payProductname;
    }

    public void setPayProductname(String payProductname) {
        this.payProductname = payProductname;
    }
}
