package com.likes.common.model.dto.sys;

import com.likes.common.mybatis.entity.SysPayaccount;

import java.math.BigDecimal;

public class SysPayaccountDO extends SysPayaccount {
    private String accounttypename;
    private String banknamealias;
    private BigDecimal rechargeamt;

    public BigDecimal getRechargeamt() {
        return rechargeamt;
    }

    public void setRechargeamt(BigDecimal rechargeamt) {
        this.rechargeamt = rechargeamt;
    }

    public String getAccounttypename() {
        return accounttypename;
    }

    public void setAccounttypename(String accounttypename) {
        this.accounttypename = accounttypename;
    }

    public String getBanknamealias() {
        return banknamealias;
    }

    public void setBanknamealias(String banknamealias) {
        this.banknamealias = banknamealias;
    }


    /**
     * @return sys_payaccount.minamt: 单笔最低金额
     * @mbggenerated
     */
    @Override
    public BigDecimal getMinamt() {
        BigDecimal minamt = super.getMinamt();
        if (minamt == null) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(minamt.intValue());
    }

    /**
     * @return sys_payaccount.maxamt: 单笔入账最高金额
     * @mbggenerated
     */
    @Override
    public BigDecimal getMaxamt() {
        BigDecimal maxamt = super.getMaxamt();
        if (maxamt == null) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(maxamt.intValue());
    }

}
