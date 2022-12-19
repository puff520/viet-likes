package com.likes.common.model.vo.pay;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud
 * @description: 天虎支付回调对象
 * @author: 芥黄
 * @create: 2020-06-20 14:55
 **/
@Scope("prototype")
@Component
public class BishangNotify extends Notify{

    private String ORDER_ID;

    private String ORDER_AMT;

    private String BUS_CODE;

    private String PAY_ORDER_ID;

    private String TRANS_STATUS;

    private String AMOUNT;

    private String SIGN;

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public String getORDER_AMT() {
        return ORDER_AMT;
    }

    public void setORDER_AMT(String ORDER_AMT) {
        this.ORDER_AMT = ORDER_AMT;
    }

    public String getBUS_CODE() {
        return BUS_CODE;
    }

    public void setBUS_CODE(String BUS_CODE) {
        this.BUS_CODE = BUS_CODE;
    }

    public String getPAY_ORDER_ID() {
        return PAY_ORDER_ID;
    }

    public void setPAY_ORDER_ID(String PAY_ORDER_ID) {
        this.PAY_ORDER_ID = PAY_ORDER_ID;
    }

    public String getTRANS_STATUS() {
        return TRANS_STATUS;
    }

    public void setTRANS_STATUS(String TRANS_STATUS) {
        this.TRANS_STATUS = TRANS_STATUS;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getSIGN() {
        return SIGN;
    }

    public void setSIGN(String SIGN) {
        this.SIGN = SIGN;
    }
}
