//package com.likes.common.mybatis.mapper;
//
//import com.likes.common.mybatis.entity.CirclePushUserRecord;
//import com.likes.common.mybatis.entity.CirclePushUserRecordExample;
//import java.util.List;
//import org.apache.ibatis.annotations.Param;
//
//public interface CirclePushUserRecordMapper {
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    int countByExample(CirclePushUserRecordExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    int deleteByExample(CirclePushUserRecordExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    int deleteByPrimaryKey(Integer id);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    int insert(CirclePushUserRecord record);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    int insertSelective(CirclePushUserRecord record);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    CirclePushUserRecord selectOneByExample(CirclePushUserRecordExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    List<CirclePushUserRecord> selectByExample(CirclePushUserRecordExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    CirclePushUserRecord selectByPrimaryKey(Integer id);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    int updateByExampleSelective(@Param("record") CirclePushUserRecord record, @Param("example") CirclePushUserRecordExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    int updateByExample(@Param("record") CirclePushUserRecord record, @Param("example") CirclePushUserRecordExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    int updateByPrimaryKeySelective(CirclePushUserRecord record);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    int updateByPrimaryKey(CirclePushUserRecord record);
//}