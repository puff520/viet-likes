package com.likes.common.model.request;

import com.likes.common.mybatis.entity.TraOrderinfom;

public class TraOrderinfomReq extends TraOrderinfom {

    private String period;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

}
