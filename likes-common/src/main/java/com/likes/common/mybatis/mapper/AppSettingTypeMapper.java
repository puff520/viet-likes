package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.AppSettingType;
import com.likes.common.mybatis.entity.AppSettingTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppSettingTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_setting_type
     *
     * @mbggenerated
     */
    int countByExample(AppSettingTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_setting_type
     *
     * @mbggenerated
     */
    int deleteByExample(AppSettingTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_setting_type
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_setting_type
     *
     * @mbggenerated
     */
    int insert(AppSettingType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_setting_type
     *
     * @mbggenerated
     */
    int insertSelective(AppSettingType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_setting_type
     *
     * @mbggenerated
     */
    AppSettingType selectOneByExample(AppSettingTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_setting_type
     *
     * @mbggenerated
     */
    List<AppSettingType> selectByExample(AppSettingTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_setting_type
     *
     * @mbggenerated
     */
    AppSettingType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_setting_type
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") AppSettingType record, @Param("example") AppSettingTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_setting_type
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") AppSettingType record, @Param("example") AppSettingTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_setting_type
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AppSettingType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_setting_type
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AppSettingType record);
}