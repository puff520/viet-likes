package com.likes.common.model.dto.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;


public class FirstRechargeDailyDataDO {
    /**
     * 日期
     */
    private String date;

    /**
     * 首充人数
     */
    private Long firstrecharge;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getFirstrecharge() {
        return firstrecharge;
    }

    public void setFirstrecharge(Long firstrecharge) {
        this.firstrecharge = firstrecharge;
    }

    public static FirstRechargeDailyDataDO getDefault() {
        FirstRechargeDailyDataDO firstRechargeDailyDataDO = new FirstRechargeDailyDataDO();
        firstRechargeDailyDataDO.setFirstrecharge(0L);
        return firstRechargeDailyDataDO;

    }
}