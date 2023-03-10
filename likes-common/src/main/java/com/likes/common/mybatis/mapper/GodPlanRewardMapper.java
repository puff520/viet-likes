package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.GodPlanReward;
import com.likes.common.mybatis.entity.GodPlanRewardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GodPlanRewardMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_reward
     *
     * @mbggenerated
     */
    int countByExample(GodPlanRewardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_reward
     *
     * @mbggenerated
     */
    int deleteByExample(GodPlanRewardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_reward
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_reward
     *
     * @mbggenerated
     */
    int insert(GodPlanReward record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_reward
     *
     * @mbggenerated
     */
    int insertSelective(GodPlanReward record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_reward
     *
     * @mbggenerated
     */
    GodPlanReward selectOneByExample(GodPlanRewardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_reward
     *
     * @mbggenerated
     */
    List<GodPlanReward> selectByExample(GodPlanRewardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_reward
     *
     * @mbggenerated
     */
    GodPlanReward selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_reward
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") GodPlanReward record, @Param("example") GodPlanRewardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_reward
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") GodPlanReward record, @Param("example") GodPlanRewardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_reward
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GodPlanReward record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table god_plan_reward
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GodPlanReward record);
}