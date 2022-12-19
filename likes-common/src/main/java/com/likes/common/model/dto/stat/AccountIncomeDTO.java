package com.likes.common.model.dto.stat;


import com.likes.common.enums.PayTypeEnum;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 收入统计
 */
public class AccountIncomeDTO {
    /** 公司入款 */
    private BigDecimal companyIncome;
    /** 线上支付 */
    private BigDecimal payOnline;
    /** 系统入款 */
    private BigDecimal systemIncome;

    public AccountIncomeDTO fillData(Map<String, Object> dataMap) {
        Object companyIncome = dataMap.get(AccountStatDTO.PREFIX_PAYMENT + PayTypeEnum.OFFLINE.getValue());
        this.companyIncome = new BigDecimal(null == companyIncome ? "0" : companyIncome.toString());

        Object payOnline = dataMap.get(AccountStatDTO.PREFIX_PAYMENT + PayTypeEnum.ONLINE.getValue());
        this.payOnline = new BigDecimal(null == payOnline ? "0" : payOnline.toString());

        Object systemIncome = dataMap.get(AccountStatDTO.PREFIX_PAYMENT + PayTypeEnum.MANUAL.getValue());
        this.systemIncome = new BigDecimal(null == systemIncome ? "0" : systemIncome.toString());
        return this;
    }

    public BigDecimal sum() {
        return this.companyIncome
                .add(this.payOnline)
                .add(this.systemIncome);
    }

    public BigDecimal getCompanyIncome() {
        return companyIncome;
    }

    public void setCompanyIncome(BigDecimal companyIncome) {
        this.companyIncome = companyIncome;
    }

    public BigDecimal getPayOnline() {
        return payOnline;
    }

    public void setPayOnline(BigDecimal payOnline) {
        this.payOnline = payOnline;
    }

    public BigDecimal getSystemIncome() {
        return systemIncome;
    }

    public void setSystemIncome(BigDecimal systemIncome) {
        this.systemIncome = systemIncome;
    }
}
