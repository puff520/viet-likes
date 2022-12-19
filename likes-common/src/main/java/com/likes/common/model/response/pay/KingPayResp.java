package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description king支付 创建订单 返回结果
 * @Author yeezy
 * @Date 2019/11/7 15:37
 * @Version 1.0
 **/
public class KingPayResp extends BasePayResp {

    /** 时间戳 */
    private String timestamp;

    public KingPayResp() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
