package com.likes.common.model.vo.report;

import com.likes.common.model.dto.report.SuperExpFundsSummaryDO;

/**
 * 【为超级后台提供资金报表】
 *
 * @Author jiehuang
 * @Description SuperExpFundsVO
 * @Date 2020/8/20-10:29
 * @Param
 * @return
 **/
public class SuperExpFundsVO {

    private SuperExpFundsSummaryDO summary;

    public SuperExpFundsSummaryDO getSummary() {
        return summary;
    }

    public void setSummary(SuperExpFundsSummaryDO summary) {
        this.summary = summary;
    }
}