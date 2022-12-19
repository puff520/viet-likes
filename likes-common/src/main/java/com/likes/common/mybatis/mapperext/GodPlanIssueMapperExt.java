package com.likes.common.mybatis.mapperext;

import com.likes.common.mybatis.entity.GodPlanIssue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface GodPlanIssueMapperExt {
    /**
     * 批量添加
     *
     * @return
     */
    int insertBatch(List<GodPlanIssue> GodPlanPlayIssues);


    /**
     * 统计前20期中的期数
     *
     * @return
     */
    int countTop20(@Param("godId") Integer godId, @Param("lotteryId") Integer lotteryId, @Param("startDate") Date startDate, @Param("issueIdealDate") Date issueIdealDate);

    /**
     * 获取分组id(澳洲系列期号是循环的)
     *
     * @param godId
     * @return
     */
    List<Long> selectPlanIds(@Param("godId") Integer godId, @Param("pagesize") Integer pagesize);

    /**
     * 逻辑删除大神计划表
     *
     * @param record
     * @return
     */
    int updateDeletedByRecord(GodPlanIssue record);
}