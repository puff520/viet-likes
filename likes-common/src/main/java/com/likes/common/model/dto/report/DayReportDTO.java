package com.likes.common.model.dto.report;

import com.likes.common.model.MemDailyReport;

public class DayReportDTO extends MemDailyReport {
    private String mobileno;
    private String preMobileno;

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPreMobileno() {
        return preMobileno;
    }

    public void setPreMobileno(String preMobileno) {
        this.preMobileno = preMobileno;
    }
}
