package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 淘宝支付回调对象
 * @author 芥黄
 * @version 1.0
 * @date 2020/7/3
 */
@Scope("prototype")
@Component
public class TaobaoNotify extends Notify {

    private String Mc;
    private String User;
    private String Trans_Id;
    private String Order_Id;
    private String Order_Time;
    private String Amount;
    //支付状态，0表示支付成功;
    private String Status;
    private String Sign;

    public String getMc() {
        return Mc;
    }

    public void setMc(String mc) {
        Mc = mc;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getTrans_Id() {
        return Trans_Id;
    }

    public void setTrans_Id(String trans_Id) {
        Trans_Id = trans_Id;
    }

    public String getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(String order_Id) {
        Order_Id = order_Id;
    }

    public String getOrder_Time() {
        return Order_Time;
    }

    public void setOrder_Time(String order_Time) {
        Order_Time = order_Time;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }
}
