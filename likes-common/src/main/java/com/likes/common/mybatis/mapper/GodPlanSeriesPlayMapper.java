package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.GodPlanSeriesPlay;
import com.likes.common.mybatis.entity.GodPlanSeriesPlayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GodPlanSeriesPlayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_series_play
     *
     * @mbggenerated
     */
    int countByExample(GodPlanSeriesPlayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_series_play
     *
     * @mbggenerated
     */
    int deleteByExample(GodPlanSeriesPlayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_series_play
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_series_play
     *
     * @mbggenerated
     */
    int insert(GodPlanSeriesPlay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_series_play
     *
     * @mbggenerated
     */
    int insertSelective(GodPlanSeriesPlay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_series_play
     *
     * @mbggenerated
     */
    GodPlanSeriesPlay selectOneByExample(GodPlanSeriesPlayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_series_play
     *
     * @mbggenerated
     */
    List<GodPlanSeriesPlay> selectByExample(GodPlanSeriesPlayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_series_play
     *
     * @mbggenerated
     */
    GodPlanSeriesPlay selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_series_play
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") GodPlanSeriesPlay record, @Param("example") GodPlanSeriesPlayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_series_play
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") GodPlanSeriesPlay record, @Param("example") GodPlanSeriesPlayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_series_play
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GodPlanSeriesPlay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_series_play
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GodPlanSeriesPlay record);
}