package com.likes.common.model.vo.pay;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 【汇盛支付回调】
 *
 * @Author 芥黄
 * @Description WeiyifuNotify
 * @Date 2020/7/11-12:58
 **/
@Scope("prototype")
@Component
public class WeiyifuNotify extends Notify {
    @JSONField(name = "MemberId")
    private String MemberId;
    @JSONField(name = "OrderId")
    private String OrderId;
    //单位，元
    @JSONField(name = "Amount")
    private String Amount;
    @JSONField(name = "TransactionId")
    private String TransactionId;
    @JSONField(name = "Date")
    private String Date;
    //CODE_FAIL-未支付 CODE_SUCCESS已支付
    @JSONField(name = "Status")
    private String Status;
    @JSONField(name = "Attach")
    private String Attach;
    @JSONField(name = "Sign")
    private String Sign;

    @JSONField(name = "MemberId")
    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    @JSONField(name = "OrderId")
    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    @JSONField(name = "Amount")
    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    @JSONField(name = "TransactionId")
    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    @JSONField(name = "Date")
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @JSONField(name = "Status")
    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @JSONField(name = "Attach")
    public String getAttach() {
        return Attach;
    }

    public void setAttach(String attach) {
        Attach = attach;
    }

    @JSONField(name = "Sign")
    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }
}
