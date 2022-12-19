package com.likes.common.model.request;

import java.util.List;

public class RoleFunctionorgRequest {
    private Long sysroleid;

    private List<Long> sfunidList;

    public Long getSysroleid() {
        return sysroleid;
    }

    public void setSysroleid(Long sysroleid) {
        this.sysroleid = sysroleid;
    }

    public List<Long> getSfunidList() {
        return sfunidList;
    }

    public void setSfunidList(List<Long> sfunidList) {
        this.sfunidList = sfunidList;
    }


}
