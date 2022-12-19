package com.likes.common.model.dto.report;

import java.math.BigDecimal;

/**
 * @Author xiaoming
 * @ClassName 首页统计
 * @Description
 * @Date 11:04 上午 8/12/20
 **/
public class ProfitAllDataDO {


    /**
     * 总盈利 总盈利 = 累计打码量-会员赢钱
     */
    private BigDecimal totalprofit;
    /**
     * 盈率 = 总盈利/累计打码量
     */
    private BigDecimal profitrate;

    public BigDecimal getTotalprofit() {
        return totalprofit;
    }

    public void setTotalprofit(BigDecimal totalprofit) {
        this.totalprofit = totalprofit;
    }

    public BigDecimal getProfitrate() {
        return profitrate;
    }

    public void setProfitrate(BigDecimal profitrate) {
        this.profitrate = profitrate;
    }
}
