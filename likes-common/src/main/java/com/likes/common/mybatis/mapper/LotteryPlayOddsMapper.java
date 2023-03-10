package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.LotteryPlayOdds;
import com.likes.common.mybatis.entity.LotteryPlayOddsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LotteryPlayOddsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    int countByExample(LotteryPlayOddsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    int deleteByExample(LotteryPlayOddsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    int insert(LotteryPlayOdds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    int insertSelective(LotteryPlayOdds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    LotteryPlayOdds selectOneByExample(LotteryPlayOddsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    List<LotteryPlayOdds> selectByExample(LotteryPlayOddsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    LotteryPlayOdds selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") LotteryPlayOdds record, @Param("example") LotteryPlayOddsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") LotteryPlayOdds record, @Param("example") LotteryPlayOddsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LotteryPlayOdds record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_odds
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LotteryPlayOdds record);
}