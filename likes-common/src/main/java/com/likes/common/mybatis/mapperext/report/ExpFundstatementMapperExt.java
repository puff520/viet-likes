package com.likes.common.mybatis.mapperext.report;

import com.likes.common.model.dto.report.DailyFundsDataDO;
import com.likes.common.model.dto.report.ExpFundsSummaryDO;
import com.likes.common.model.dto.report.SuperExpFundsSummaryDO;
import com.likes.common.mybatis.entity.ExpFundstatement;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ExpFundstatementMapperExt {
    int deleteByPrimaryKey(Long fundid);

    int insert(ExpFundstatement record);

    int insertSelective(ExpFundstatement record);

    ExpFundstatement selectByPrimaryKey(Long fundid);

    int updateByPrimaryKeySelective(ExpFundstatement record);

    int updateByPrimaryKey(ExpFundstatement record);

    List<ExpFundstatement> getListByYearMonth(String yearmonth);

    ExpFundsSummaryDO getOneMonthSum(String yearmonth);

    ExpFundstatement existsByDate(String dateStr);


    int updateIsDeleteByDate(@Param("updatedIsDelete") Boolean updatedIsDelete, @Param("dateStr") String dateStr);

    SuperExpFundsSummaryDO getSuperOneMonthSum(String yearmonth);
}