package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.LotteryPlay;
import com.likes.common.mybatis.entity.LotteryPlayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LotteryPlayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play
     *
     * @mbggenerated
     */
    int countByExample(LotteryPlayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play
     *
     * @mbggenerated
     */
    int deleteByExample(LotteryPlayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play
     *
     * @mbggenerated
     */
    int insert(LotteryPlay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play
     *
     * @mbggenerated
     */
    int insertSelective(LotteryPlay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play
     *
     * @mbggenerated
     */
    LotteryPlay selectOneByExample(LotteryPlayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play
     *
     * @mbggenerated
     */
    List<LotteryPlay> selectByExample(LotteryPlayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play
     *
     * @mbggenerated
     */
    LotteryPlay selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") LotteryPlay record, @Param("example") LotteryPlayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") LotteryPlay record, @Param("example") LotteryPlayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LotteryPlay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LotteryPlay record);
}