package com.likes.common.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class StatisticsOnLineOrderResponse {
    private Double totalsumamt;
    private Double totalrealamt;

    public Double getTotalsumamt() {
        return totalsumamt;
    }

    public void setTotalsumamt(Double totalsumamt) {
        this.totalsumamt = totalsumamt;
    }

    public Double getTotalrealamt() {
        return totalrealamt;
    }

    public void setTotalrealamt(Double totalrealamt) {
        this.totalrealamt = totalrealamt;
    }
}
