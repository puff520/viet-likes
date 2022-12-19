package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.SysInfolog;
import com.likes.common.mybatis.entity.SysInfologExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysInfologMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_infolog
     *
     * @mbggenerated
     */
    int countByExample(SysInfologExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_infolog
     *
     * @mbggenerated
     */
    int deleteByExample(SysInfologExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_infolog
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long logid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_infolog
     *
     * @mbggenerated
     */
    int insert(SysInfolog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_infolog
     *
     * @mbggenerated
     */
    int insertSelective(SysInfolog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_infolog
     *
     * @mbggenerated
     */
    SysInfolog selectOneByExample(SysInfologExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_infolog
     *
     * @mbggenerated
     */
    List<SysInfolog> selectByExample(SysInfologExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_infolog
     *
     * @mbggenerated
     */
    SysInfolog selectByPrimaryKey(Long logid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_infolog
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysInfolog record, @Param("example") SysInfologExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_infolog
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysInfolog record, @Param("example") SysInfologExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_infolog
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysInfolog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_infolog
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysInfolog record);
}