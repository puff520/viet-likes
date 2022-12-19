package com.likes.common.model.dto.sys;

import com.likes.common.mybatis.entity.SysShortmsg;

public class SysShortmsgDO extends SysShortmsg {
    private Integer countDown;

    public Integer getCountDown() {
        return countDown;
    }

    public void setCountDown(Integer countDown) {
        this.countDown = countDown;
    }

}
