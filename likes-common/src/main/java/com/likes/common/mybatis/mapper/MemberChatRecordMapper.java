package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.MemberChatRecord;
import com.likes.common.mybatis.entity.MemberChatRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberChatRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_chat_record
     *
     * @mbggenerated
     */
    int countByExample(MemberChatRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_chat_record
     *
     * @mbggenerated
     */
    int deleteByExample(MemberChatRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_chat_record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_chat_record
     *
     * @mbggenerated
     */
    int insert(MemberChatRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_chat_record
     *
     * @mbggenerated
     */
    int insertSelective(MemberChatRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_chat_record
     *
     * @mbggenerated
     */
    MemberChatRecord selectOneByExample(MemberChatRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_chat_record
     *
     * @mbggenerated
     */
    List<MemberChatRecord> selectByExample(MemberChatRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_chat_record
     *
     * @mbggenerated
     */
    MemberChatRecord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_chat_record
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MemberChatRecord record, @Param("example") MemberChatRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_chat_record
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MemberChatRecord record, @Param("example") MemberChatRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_chat_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MemberChatRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_chat_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MemberChatRecord record);
}