package com.likes.common.model.dto.money;

import com.likes.common.mybatis.entity.MemBaseinfo;

public class ChangeMoneyForm {
    private MemBaseinfo memBaseinfo;
    private Integer userId;
    private Integer money;
    private Integer balanceChangeType;
    private String remark;

    public MemBaseinfo getMemBaseinfo() {
        return memBaseinfo;
    }

    public void setMemBaseinfo(MemBaseinfo memBaseinfo) {
        this.memBaseinfo = memBaseinfo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getBalanceChangeType() {
        return balanceChangeType;
    }

    public void setBalanceChangeType(Integer balanceChangeType) {
        this.balanceChangeType = balanceChangeType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
