package com.likes.common.mybatis.mapperext;


import com.likes.common.mybatis.entity.GodPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GodPlanMapperExt {
    /**
     * 批量添加
     *
     * @return
     */
    int insertBatch(@Param("godPlans") List<GodPlan> GodPlanList);

    /**
     * 更新头像
     *
     * @param planId
     * @param fileData
     */
    void updateGodPlanHead(@Param("planId") Integer planId, @Param("fileData") String fileData);
}