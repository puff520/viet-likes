package com.likes.common.mybatis.mapperext;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;

public interface FinanceDamaAdjustmentMapperExt {
    BigDecimal getJiaMaTotal(@Param("accno") String accno,@Param("beginDate")Date beginDate);
}
