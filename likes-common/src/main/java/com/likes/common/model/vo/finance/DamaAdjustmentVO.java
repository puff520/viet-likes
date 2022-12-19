package com.likes.common.model.vo.finance;

import java.io.Serializable;
import java.math.BigDecimal;

public class DamaAdjustmentVO extends FinanceMemBaseVo implements Serializable {


    /**
     * 打码量
     */
    private BigDecimal damaliang;
    /**
     * 所需打码量
     */
    private BigDecimal needDamaliang;

    public BigDecimal getDamaliang() {
        return damaliang;
    }

    public void setDamaliang(BigDecimal damaliang) {
        this.damaliang = damaliang;
    }

    public BigDecimal getNeedDamaliang() {
        return needDamaliang;
    }

    public void setNeedDamaliang(BigDecimal needDamaliang) {
        this.needDamaliang = needDamaliang;
    }
}
