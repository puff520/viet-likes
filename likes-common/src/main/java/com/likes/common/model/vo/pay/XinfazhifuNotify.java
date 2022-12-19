package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 发家支付回调对象
 *
 * @author 瑞夫
 * @version 1.0
 * @date 2020/7/3
 */
@Scope("prototype")
@Component
public class XinfazhifuNotify extends Notify {

    private String data;

    private String merchNo;

    private String orderNo;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public class notifyData {
        private String merchNo;
        private String payType;
        private String orderNo;
        private String amount;
        private String goodsName;
        private String payStateCode;
        //支付状态，0表示支付成功;
        private String payDate;
        private String Sign;

        public String getMerchNo() {
            return merchNo;
        }

        public void setMerchNo(String merchNo) {
            this.merchNo = merchNo;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getPayStateCode() {
            return payStateCode;
        }

        public void setPayStateCode(String payStateCode) {
            this.payStateCode = payStateCode;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public String getSign() {
            return Sign;
        }

        public void setSign(String sign) {
            Sign = sign;
        }
    }
}
