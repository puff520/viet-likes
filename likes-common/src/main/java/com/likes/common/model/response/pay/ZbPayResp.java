package com.likes.common.model.response.pay;


/**
 * @ClassName
 * @Description 众宝支付 创建订单 返回结果
 * @Author puff
 * @Date 2019/11/7 15:37
 * @Version 1.0
 **/
public class ZbPayResp extends BasePayResp {

    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
