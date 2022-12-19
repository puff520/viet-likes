package com.likes.common.model.dto.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.likes.common.model.SysRecord;

import java.util.Date;

public class SysRecordDO extends SysRecord {
    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }


    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-5")
    private Date recorddate;

    private String operationName;
}
