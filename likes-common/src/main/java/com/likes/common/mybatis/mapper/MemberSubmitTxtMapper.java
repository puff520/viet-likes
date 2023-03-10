package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.MemberSubmitTxt;
import com.likes.common.mybatis.entity.MemberSubmitTxtExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberSubmitTxtMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_submit_txt
     *
     * @mbggenerated
     */
    int countByExample(MemberSubmitTxtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_submit_txt
     *
     * @mbggenerated
     */
    int deleteByExample(MemberSubmitTxtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_submit_txt
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_submit_txt
     *
     * @mbggenerated
     */
    int insert(MemberSubmitTxt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_submit_txt
     *
     * @mbggenerated
     */
    int insertSelective(MemberSubmitTxt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_submit_txt
     *
     * @mbggenerated
     */
    MemberSubmitTxt selectOneByExample(MemberSubmitTxtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_submit_txt
     *
     * @mbggenerated
     */
    List<MemberSubmitTxt> selectByExample(MemberSubmitTxtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_submit_txt
     *
     * @mbggenerated
     */
    MemberSubmitTxt selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_submit_txt
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MemberSubmitTxt record, @Param("example") MemberSubmitTxtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_submit_txt
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MemberSubmitTxt record, @Param("example") MemberSubmitTxtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_submit_txt
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MemberSubmitTxt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_submit_txt
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MemberSubmitTxt record);
}