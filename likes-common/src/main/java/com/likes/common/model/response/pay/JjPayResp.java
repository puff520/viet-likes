package com.likes.common.model.response.pay;

/**
 * @ClassName
 * @Description 99支付 创建订单 返回结果
 * @Author yeezy
 * @Date 2019/11/7 15:37
 * @Version 1.0
 **/
public class JjPayResp extends BasePayResp {

    /** 网关返回码 0成功；其他失败 */
    private String code;

    /** 订单金额 */
    private String money;

    public JjPayResp() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
