package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.FivebjpksKillNumber;
import com.likes.common.mybatis.entity.FivebjpksKillNumberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FivebjpksKillNumberMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fivebjpks_kill_number
     *
     * @mbggenerated
     */
    int countByExample(FivebjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fivebjpks_kill_number
     *
     * @mbggenerated
     */
    int deleteByExample(FivebjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fivebjpks_kill_number
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fivebjpks_kill_number
     *
     * @mbggenerated
     */
    int insert(FivebjpksKillNumber record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fivebjpks_kill_number
     *
     * @mbggenerated
     */
    int insertSelective(FivebjpksKillNumber record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fivebjpks_kill_number
     *
     * @mbggenerated
     */
    FivebjpksKillNumber selectOneByExample(FivebjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fivebjpks_kill_number
     *
     * @mbggenerated
     */
    List<FivebjpksKillNumber> selectByExample(FivebjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fivebjpks_kill_number
     *
     * @mbggenerated
     */
    FivebjpksKillNumber selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fivebjpks_kill_number
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FivebjpksKillNumber record, @Param("example") FivebjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fivebjpks_kill_number
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FivebjpksKillNumber record, @Param("example") FivebjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fivebjpks_kill_number
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FivebjpksKillNumber record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fivebjpks_kill_number
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FivebjpksKillNumber record);
}