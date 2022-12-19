package com.likes.common.model.request;

import java.util.List;

public class AdRequest {

    private String seatcode;

    private Long bseatid;

    private String dateTime;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<Long> getList() {
        return list;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }

    private List<Long> list;

    public Long getBseatid() {
        return bseatid;
    }

    public void setBseatid(Long bseatid) {
        this.bseatid = bseatid;
    }

    public String getSeatcode() {
        return seatcode;
    }

    public void setSeatcode(String seatcode) {
        this.seatcode = seatcode;
    }
}
