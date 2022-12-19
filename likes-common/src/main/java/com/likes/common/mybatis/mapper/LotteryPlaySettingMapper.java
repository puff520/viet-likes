package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.LotteryPlaySetting;
import com.likes.common.mybatis.entity.LotteryPlaySettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LotteryPlaySettingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_setting
     *
     * @mbggenerated
     */
    int countByExample(LotteryPlaySettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_setting
     *
     * @mbggenerated
     */
    int deleteByExample(LotteryPlaySettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_setting
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_setting
     *
     * @mbggenerated
     */
    int insert(LotteryPlaySetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_setting
     *
     * @mbggenerated
     */
    int insertSelective(LotteryPlaySetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_setting
     *
     * @mbggenerated
     */
    LotteryPlaySetting selectOneByExample(LotteryPlaySettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_setting
     *
     * @mbggenerated
     */
    List<LotteryPlaySetting> selectByExample(LotteryPlaySettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_setting
     *
     * @mbggenerated
     */
    LotteryPlaySetting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_setting
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") LotteryPlaySetting record, @Param("example") LotteryPlaySettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_setting
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") LotteryPlaySetting record, @Param("example") LotteryPlaySettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_setting
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LotteryPlaySetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_play_setting
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LotteryPlaySetting record);
}