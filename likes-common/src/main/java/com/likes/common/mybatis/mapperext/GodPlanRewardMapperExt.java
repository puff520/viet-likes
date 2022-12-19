package com.likes.common.mybatis.mapperext;


import com.likes.common.model.dto.report.GodPlanRewardDataDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GodPlanRewardMapperExt {
    List<GodPlanRewardDataDO> getGodPlanReward(@Param("startTime") String startTime, @Param("endTime") String endTime);
}