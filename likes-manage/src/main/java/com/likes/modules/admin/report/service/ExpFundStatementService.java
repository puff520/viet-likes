package com.likes.modules.admin.report.service;


import com.likes.common.model.LoginUser;
import com.likes.common.model.dto.report.ExpFundStatementDO;
import com.likes.common.model.dto.report.ExpFundsSummaryDO;
import com.likes.common.model.vo.report.ExpFundStatementVO;
import com.likes.common.model.vo.report.ExpFundsVO;
import com.likes.common.model.vo.report.SuperExpFundsVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Date;
import java.util.List;

/**
 * @Author xiaoming
 * @ClassName ExpFundStatementService
 * @Description
 * @Date 10:56 上午 7/27/20
 **/
public interface ExpFundStatementService {

    /**
     * 查询指定月份数据
     *
     * @param yearmonth 年月 格式yyyyMM
     * @return
     */
    ExpFundsVO getListByYearMonth(String yearmonth);

    /**
     * 查询指定月份汇总数据
     *
     * @param yearmonth 年月 格式yyyyMM
     * @return
     */
    ExpFundsSummaryDO getOneMonthSum(String yearmonth);

    /**
     * 导出资金报表Excel
     *
     * @param yearmonth 年月 格式yyyyMM
     * @return
     */
    Workbook exportFundsExcel(String yearmonth);


    /**
     * 手动执行统计时间范围内的资金报表数据
     *
     * @param startTime
     * @param endTime
     */
    void manualStatisticsFunds(String startTime, String endTime, LoginUser loginUser);

    SuperExpFundsVO collectListByYearMonth(String yearmonth);
}
