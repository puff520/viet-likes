package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.BjpksKillNumber;
import com.likes.common.mybatis.entity.BjpksKillNumberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BjpksKillNumberMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_kill_number
     *
     * @mbggenerated
     */
    int countByExample(BjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_kill_number
     *
     * @mbggenerated
     */
    int deleteByExample(BjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_kill_number
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_kill_number
     *
     * @mbggenerated
     */
    int insert(BjpksKillNumber record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_kill_number
     *
     * @mbggenerated
     */
    int insertSelective(BjpksKillNumber record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_kill_number
     *
     * @mbggenerated
     */
    BjpksKillNumber selectOneByExample(BjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_kill_number
     *
     * @mbggenerated
     */
    List<BjpksKillNumber> selectByExample(BjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_kill_number
     *
     * @mbggenerated
     */
    BjpksKillNumber selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_kill_number
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BjpksKillNumber record, @Param("example") BjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_kill_number
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BjpksKillNumber record, @Param("example") BjpksKillNumberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_kill_number
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BjpksKillNumber record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_kill_number
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BjpksKillNumber record);
}