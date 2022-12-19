package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description
 * @Version 1.0
 **/
public class AaPayReq extends BasePayReq {

    /** 支付用户 */
    private Integer userId;

    /** 请求的地址 */
    private String url;

    /** 请求来源 */
    private String source;

    /** 商品名称 */
    private String fxdesc = "33aazf";

    /** MD5key */
    private String md5Key;

    /** 异步数据类型*/
    private String fxnotifystyle = "2";

    /**绑定商户的用户id */
    private String fxuserid;

    /** 客户IP地址 */
    private String fxip;

    public AaPayReq() {
    }

    public String getFxip() {
        return fxip;
    }

    public void setFxip(String fxip) {
        this.fxip = fxip;
    }

    public String getFxuserid() {
        return fxuserid;
    }

    public void setFxuserid(String fxuserid) {
        this.fxuserid = fxuserid;
    }

    public String getFxnotifystyle() {
        return fxnotifystyle;
    }

    public void setFxnotifystyle(String fxnotifystyle) {
        this.fxnotifystyle = fxnotifystyle;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }

    public String getFxdesc() {
        return fxdesc;
    }

    public void setFxdesc(String fxdesc) {
        this.fxdesc = fxdesc;
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
}
