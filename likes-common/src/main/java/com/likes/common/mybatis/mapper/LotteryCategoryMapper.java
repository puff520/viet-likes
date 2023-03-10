package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.LotteryCategory;
import com.likes.common.mybatis.entity.LotteryCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LotteryCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_category
     *
     * @mbggenerated
     */
    int countByExample(LotteryCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_category
     *
     * @mbggenerated
     */
    int deleteByExample(LotteryCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_category
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_category
     *
     * @mbggenerated
     */
    int insert(LotteryCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_category
     *
     * @mbggenerated
     */
    int insertSelective(LotteryCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_category
     *
     * @mbggenerated
     */
    LotteryCategory selectOneByExample(LotteryCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_category
     *
     * @mbggenerated
     */
    List<LotteryCategory> selectByExample(LotteryCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_category
     *
     * @mbggenerated
     */
    LotteryCategory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_category
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") LotteryCategory record, @Param("example") LotteryCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_category
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") LotteryCategory record, @Param("example") LotteryCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LotteryCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LotteryCategory record);
}