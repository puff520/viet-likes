package com.likes.common.model.request;

public class AgentTransactionRequest {

    /**
     * 订单id
     */
    private Long orderid;

    /**
     * 支付凭证截图 多张以“，”分隔
     */
//    private String payimg;

    /**
     * 状态
     */
    private String orderstatus;

    /**
     * 备注
     */
    private String paynote;


    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

//    public String getPayimg() {
//        return payimg;
//    }
//
//    public void setPayimg(String payimg) {
//        this.payimg = payimg;
//    }



    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }


    public String getPaynote() {
        return paynote;
    }

    public void setPaynote(String paynote) {
        this.paynote = paynote;
    }
}
