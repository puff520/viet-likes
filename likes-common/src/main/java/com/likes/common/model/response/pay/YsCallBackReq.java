package com.likes.common.model.response.pay;

/**
 * @ClassName YsCallBackReq
 * @Description TODO
 * @Author
 * @Date
 * @Version 1.0
 **/
public class YsCallBackReq extends BaseCallBackReq {
    private String sign; //签名
    private String time;
    private String goodsDesc;
    private String moneyString;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoneyString() {
        return moneyString;
    }

    public void setMoneyString(String moneyString) {
        this.moneyString = moneyString;
    }
}
