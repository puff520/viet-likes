package com.likes.common.model.dto.member;

import java.math.BigDecimal;
import java.util.Date;

public class UserDateDO extends UserDO {
    private Date createtime;
    private BigDecimal goldnum;

    public BigDecimal getGoldnum() {
        return goldnum;
    }

    public void setGoldnum(BigDecimal goldnum) {
        this.goldnum = goldnum;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
