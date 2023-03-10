package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.ChatPackRecord;
import com.likes.common.mybatis.entity.ChatPackRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatPackRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_pack_record
     *
     * @mbggenerated
     */
    int countByExample(ChatPackRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_pack_record
     *
     * @mbggenerated
     */
    int deleteByExample(ChatPackRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_pack_record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_pack_record
     *
     * @mbggenerated
     */
    int insert(ChatPackRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_pack_record
     *
     * @mbggenerated
     */
    int insertSelective(ChatPackRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_pack_record
     *
     * @mbggenerated
     */
    ChatPackRecord selectOneByExample(ChatPackRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_pack_record
     *
     * @mbggenerated
     */
    List<ChatPackRecord> selectByExample(ChatPackRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_pack_record
     *
     * @mbggenerated
     */
    ChatPackRecord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_pack_record
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ChatPackRecord record, @Param("example") ChatPackRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_pack_record
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ChatPackRecord record, @Param("example") ChatPackRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_pack_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ChatPackRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_pack_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ChatPackRecord record);
}