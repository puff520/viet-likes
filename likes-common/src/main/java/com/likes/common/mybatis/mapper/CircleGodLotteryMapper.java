//package com.likes.common.mybatis.mapper;
//
//import com.likes.common.mybatis.entity.CircleGodLottery;
//import com.likes.common.mybatis.entity.CircleGodLotteryExample;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
//public interface CircleGodLotteryMapper {
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_god_lottery
//     *
//     * @mbggenerated
//     */
//    int countByExample(CircleGodLotteryExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_god_lottery
//     *
//     * @mbggenerated
//     */
//    int deleteByExample(CircleGodLotteryExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_god_lottery
//     *
//     * @mbggenerated
//     */
//    int deleteByPrimaryKey(Integer id);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_god_lottery
//     *
//     * @mbggenerated
//     */
//    int insert(CircleGodLottery record);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_god_lottery
//     *
//     * @mbggenerated
//     */
//    int insertSelective(CircleGodLottery record);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_god_lottery
//     *
//     * @mbggenerated
//     */
//    CircleGodLottery selectOneByExample(CircleGodLotteryExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_god_lottery
//     *
//     * @mbggenerated
//     */
//    List<CircleGodLottery> selectByExample(CircleGodLotteryExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_god_lottery
//     *
//     * @mbggenerated
//     */
//    CircleGodLottery selectByPrimaryKey(Integer id);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_god_lottery
//     *
//     * @mbggenerated
//     */
//    int updateByExampleSelective(@Param("record") CircleGodLottery record, @Param("example") CircleGodLotteryExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_god_lottery
//     *
//     * @mbggenerated
//     */
//    int updateByExample(@Param("record") CircleGodLottery record, @Param("example") CircleGodLotteryExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_god_lottery
//     *
//     * @mbggenerated
//     */
//    int updateByPrimaryKeySelective(CircleGodLottery record);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_god_lottery
//     *
//     * @mbggenerated
//     */
//    int updateByPrimaryKey(CircleGodLottery record);
//}