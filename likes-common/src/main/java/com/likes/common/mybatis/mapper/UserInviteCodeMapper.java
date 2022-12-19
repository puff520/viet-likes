package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.UserInviteCode;
import com.likes.common.mybatis.entity.UserInviteCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInviteCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    int countByExample(UserInviteCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    int deleteByExample(UserInviteCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    int insert(UserInviteCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    int insertSelective(UserInviteCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    UserInviteCode selectOneByExample(UserInviteCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    List<UserInviteCode> selectByExample(UserInviteCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    UserInviteCode selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") UserInviteCode record, @Param("example") UserInviteCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") UserInviteCode record, @Param("example") UserInviteCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserInviteCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_invite_code
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserInviteCode record);
}