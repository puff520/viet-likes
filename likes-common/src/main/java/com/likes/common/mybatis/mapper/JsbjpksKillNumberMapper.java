package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.JsbjpksKillNumber;
import com.likes.common.mybatis.entity.JsbjpksKillNumberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsbjpksKillNumberMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_kill_number
     *
     * @mbggenerated
     */
    int countByExample(JsbjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_kill_number
     *
     * @mbggenerated
     */
    int deleteByExample(JsbjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_kill_number
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_kill_number
     *
     * @mbggenerated
     */
    int insert(JsbjpksKillNumber record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_kill_number
     *
     * @mbggenerated
     */
    int insertSelective(JsbjpksKillNumber record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_kill_number
     *
     * @mbggenerated
     */
    JsbjpksKillNumber selectOneByExample(JsbjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_kill_number
     *
     * @mbggenerated
     */
    List<JsbjpksKillNumber> selectByExample(JsbjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_kill_number
     *
     * @mbggenerated
     */
    JsbjpksKillNumber selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_kill_number
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") JsbjpksKillNumber record, @Param("example") JsbjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_kill_number
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") JsbjpksKillNumber record, @Param("example") JsbjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_kill_number
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(JsbjpksKillNumber record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_kill_number
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(JsbjpksKillNumber record);
}