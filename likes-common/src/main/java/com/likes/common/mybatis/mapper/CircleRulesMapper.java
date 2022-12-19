package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.CircleRules;
import com.likes.common.mybatis.entity.CircleRulesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CircleRulesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_rules
     *
     * @mbggenerated
     */
    int countByExample(CircleRulesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_rules
     *
     * @mbggenerated
     */
    int deleteByExample(CircleRulesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_rules
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_rules
     *
     * @mbggenerated
     */
    int insert(CircleRules record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_rules
     *
     * @mbggenerated
     */
    int insertSelective(CircleRules record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_rules
     *
     * @mbggenerated
     */
    CircleRules selectOneByExample(CircleRulesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_rules
     *
     * @mbggenerated
     */
    List<CircleRules> selectByExample(CircleRulesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_rules
     *
     * @mbggenerated
     */
    CircleRules selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_rules
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CircleRules record, @Param("example") CircleRulesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_rules
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CircleRules record, @Param("example") CircleRulesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_rules
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CircleRules record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table circle_rules
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CircleRules record);
}