package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 发家支付回调对象
 * @author 瑞夫
 * @version 1.0
 * @date 2020/7/3
 */
@Scope("prototype")
@Component
public class FajiaNotify extends Notify {

    private String order_no;
    private String user_id;
    private String money;
    private String type;
    private String date;
    private String trade_no;
    //支付状态，0表示支付成功;
    private String status;
    private String sign;
    private String shop_no;
    private String shopAccountId;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getShop_no() {
        return shop_no;
    }

    public void setShop_no(String shop_no) {
        this.shop_no = shop_no;
    }

    public String getShopAccountId() {
        return shopAccountId;
    }

    public void setShopAccountId(String shopAccountId) {
        this.shopAccountId = shopAccountId;
    }
}
