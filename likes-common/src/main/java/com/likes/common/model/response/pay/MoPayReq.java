package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 默默支付请求
 * @Version 1.0
 **/
public class MoPayReq extends BasePayReq {

    /** 默认version */
    public static final String MO_PAY_DEFAULT_VERSION = "1.0";

    /** 默认char set */
    public static final String MO_PAY_DEFAULT_CHAR_SET = "UTF-8";

    /** 默认sign type */
    public static final String MO_PAY_DEFAULT_SIGN_TYPE = "MD5";

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

    /** 版本号 */
    private String version;

    /** 字符集 */
    private String charSet;

    /** 签名方式 */
    private String signType;

    /** 设备号 */
    private String deviceInfo;

    /** 商品描述 */
    private String body;

    /** 附加信息 */
    private String attach;

    /** 终端IP */
    private String mchCreateIp;

    /** 订单生成时间 */
    private String timeStart;

    /** 订单超时时间 */
    private String timeExpire;

    /** 操作员 */
    private String opUserId;

    /** 商品标记 */
    private String goodsTag;

    /** 商品ID */
    private String productId;

    /** 随机字符串 */
    private String nonceStr;

    /** 是否限制信用卡 */
    private String limitCreditPay;

    public MoPayReq() {
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
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

    public String getMchCreateIp() {
        return mchCreateIp;
    }

    public void setMchCreateIp(String mchCreateIp) {
        this.mchCreateIp = mchCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getLimitCreditPay() {
        return limitCreditPay;
    }

    public void setLimitCreditPay(String limitCreditPay) {
        this.limitCreditPay = limitCreditPay;
    }
}
