package com.likes.common.enums;



public enum BrokerMessageStatus {

    SENDING(0),

    SEND_OK(1),

    SEND_FAIL(2);

    private Integer code;

    BrokerMessageStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
