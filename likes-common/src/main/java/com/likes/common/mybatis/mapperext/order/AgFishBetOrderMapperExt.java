package com.likes.common.mybatis.mapperext.order;

import com.likes.common.model.dto.report.GameDailyDataDO;
import com.likes.common.model.dto.report.GameUserDataDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgFishBetOrderMapperExt {
    GameDailyDataDO statisticalDayData(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<GameUserDataDO> statisticalDataByUser(@Param("startTime") String startTime, @Param("endTime") String endTime);
}