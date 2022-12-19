package com.likes.common.mybatis.mapperbean;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.Date;

public interface GameBetamountRecordBeanMapper {

    @Select("select SUM(betAmount) from `game_betamount_record` where user_id = #{userId} and create_time >= #{beginTime}")
    BigDecimal selectAllBetAmountByUserId(@Param("userId") Long userId, @Param("beginTime")Date beginTime);
}
