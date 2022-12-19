package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.BjpksLotterySg;
import com.likes.common.mybatis.entity.BjpksLotterySgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BjpksLotterySgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_lottery_sg
     *
     * @mbggenerated
     */
    int countByExample(BjpksLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByExample(BjpksLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_lottery_sg
     *
     * @mbggenerated
     */
    int insert(BjpksLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_lottery_sg
     *
     * @mbggenerated
     */
    int insertSelective(BjpksLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_lottery_sg
     *
     * @mbggenerated
     */
    BjpksLotterySg selectOneByExample(BjpksLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_lottery_sg
     *
     * @mbggenerated
     */
    List<BjpksLotterySg> selectByExample(BjpksLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_lottery_sg
     *
     * @mbggenerated
     */
    BjpksLotterySg selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BjpksLotterySg record, @Param("example") BjpksLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BjpksLotterySg record, @Param("example") BjpksLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BjpksLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bjpks_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BjpksLotterySg record);
}