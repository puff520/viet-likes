package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 168支付回调对象
 * @author 芥黄
 * @version 1.0
 * @date 2020/7/3
 */
@Scope("prototype")
@Component
public class YiliubaNotify extends Notify {

    private String merchant;
    private String qrtype;
    private String customno;
    private String sendtime;
    private String orderno;
    private String money;
    //支付状态，0表示支付成功;
    private String state;

    private String paytime;

    private String Sign;

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getQrtype() {
        return qrtype;
    }

    public void setQrtype(String qrtype) {
        this.qrtype = qrtype;
    }

    public String getCustomno() {
        return customno;
    }

    public void setCustomno(String customno) {
        this.customno = customno;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }
}
