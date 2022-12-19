package com.likes.common.model.vo.pay;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 万通支付
 *
 * @author 瑞夫
 * @version 1.0
 * @date 2020/7/13
 */
@Scope("prototype")
@Component
public class WantongNotify extends Notify {

    private String transdata;

    private String sign;

    private WantongNotifyData notifyData;

    public String getTransdata() {
        return transdata;
    }

    public void setTransdata(String transdata) {
        this.transdata = transdata;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public WantongNotifyData getNotifyData() throws UnsupportedEncodingException {
        return JSON.parseObject(URLDecoder.decode(transdata, "UTF-8"), WantongNotifyData.class);
    }

    public static class WantongNotifyData {
        /**
         * 商户订单号
         */
        private String order_no;

        /**
         * 订单金额（元为单位）
         */
        private String order_amount;
        /**
         * UF系统订单创建时间
         */
        private String order_time;

        /**
         * 订单支付通道码
         */
        private String pay_type;

        /**
         * 支付时上传的商品名称
         */
        private String product_name;
        /**
         * 支付时上传的商品CODE
         */
        private String product_code;
        /**
         * 支付时上传的用户ID
         */
        private String user_no;
        /**
         * 支付成功，目前该字段值一定为支付成功四个字
         */
        private String payment;

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_code() {
            return product_code;
        }

        public void setProduct_code(String product_code) {
            this.product_code = product_code;
        }

        public String getUser_no() {
            return user_no;
        }

        public void setUser_no(String user_no) {
            this.user_no = user_no;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }
    }

    public String toJosnString() {
        return "{" +
                "transdata=\"" + transdata + '\"' +
                ", sign=\"" + sign + '\"' +
                "}";
    }
}
