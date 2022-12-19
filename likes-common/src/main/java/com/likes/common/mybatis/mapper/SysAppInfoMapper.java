package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.SysAppInfo;
import com.likes.common.mybatis.entity.SysAppInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAppInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_info
     *
     * @mbggenerated
     */
    int countByExample(SysAppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_info
     *
     * @mbggenerated
     */
    int deleteByExample(SysAppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long appid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_info
     *
     * @mbggenerated
     */
    int insert(SysAppInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_info
     *
     * @mbggenerated
     */
    int insertSelective(SysAppInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_info
     *
     * @mbggenerated
     */
    SysAppInfo selectOneByExample(SysAppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_info
     *
     * @mbggenerated
     */
    List<SysAppInfo> selectByExample(SysAppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_info
     *
     * @mbggenerated
     */
    SysAppInfo selectByPrimaryKey(Long appid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_info
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysAppInfo record, @Param("example") SysAppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_info
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysAppInfo record, @Param("example") SysAppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysAppInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_app_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysAppInfo record);
}