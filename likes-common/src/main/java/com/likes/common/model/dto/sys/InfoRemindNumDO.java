package com.likes.common.model.dto.sys;

public class InfoRemindNumDO {
    private String rmtype;
    private Integer num;

    public InfoRemindNumDO(String rmtype, Integer num) {
        this.rmtype = rmtype;
        this.num = num;
    }

    public String getRmtype() {
        return rmtype;
    }

    public void setRmtype(String rmtype) {
        this.rmtype = rmtype;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
