package com.likes.common.model.vo.report;

import com.likes.common.model.dto.report.ExpFundsSummaryDO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;
import java.util.List;

/**
 *@Author xiaoming
 *@ClassName
 *@Description
 *@Date 6:10 下午 7/29/20
 **/
public class ExpFundsVO {

    private List<ExpFundStatementVO> list;
    private ExpFundsSummaryDO summary;

    public List<ExpFundStatementVO> getList() {
        return list;
    }

    public void setList(List<ExpFundStatementVO> list) {
        this.list = list;
    }

    public ExpFundsSummaryDO getSummary() {
        return summary;
    }

    public void setSummary(ExpFundsSummaryDO summary) {
        this.summary = summary;
    }
}