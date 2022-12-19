package com.likes.common.model.request;

import java.util.Date;

public class DayReportInfoRequest extends BaseRequest {
    private String[] mobileno;
    private Date startTime;
    private Date endTime;
    private String accno;

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String[] getMobileno() {
        return mobileno;
    }

    public void setMobileno(String[] mobileno) {
        this.mobileno = mobileno;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}