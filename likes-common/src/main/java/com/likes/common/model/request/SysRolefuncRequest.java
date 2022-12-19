package com.likes.common.model.request;

import com.likes.common.mybatis.entity.SysRolefunc;

import java.util.List;

public class SysRolefuncRequest extends SysRolefunc {
    private List<Long> sfunidList;

    public List<Long> getSfunidList() {
        return sfunidList;
    }

    public void setSfunidList(List<Long> sfunidList) {
        this.sfunidList = sfunidList;
    }

}
