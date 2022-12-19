package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud
 * @description: 99支付回调参数
 * @author: 芥黄
 * @create: 2020-06-15 13:24
 **/
@Scope("prototype")
@Component
public class JiujiuNotify extends Notify {
    private String code;

    private String message;

    private JiujiuData data;

    public static class JiujiuData {
        /**
         * 【商户编号】
         **/
        private String merchantNo;

        /**
         * 【订单号】
         **/
        private String outTradeNo;

        /**
         * 【订单总金额，单位为分】
         **/
        private String payMoney;

        /**
         * 【交易流水号】
         **/
        private String orderNo;

        /**
         * 【交易时间】
         **/
        private String payTime;
        /**
         * 【交易标识，SUCCESS/FAIL】
         *
         **/
        private String resultCode;
        /**
         * 【扩展返回】
         *
         **/
        private String attach;

        private String sign;

        private String body;

        private String message;

        public String getMerchantNo() {
            return merchantNo;
        }

        public void setMerchantNo(String merchantNo) {
            this.merchantNo = merchantNo;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public String getPayMoney() {
            return payMoney;
        }

        public void setPayMoney(String payMoney) {
            this.payMoney = payMoney;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getAttach() {
            return attach;
        }

        public void setAttach(String attach) {
            this.attach = attach;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JiujiuData getData() {
        return data;
    }

    public void setData(JiujiuData data) {
        this.data = data;
    }
}
