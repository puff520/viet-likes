package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.SysBduserrole;
import com.likes.common.mybatis.entity.SysBduserroleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysBduserroleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bduserrole
     *
     * @mbggenerated
     */
    int countByExample(SysBduserroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bduserrole
     *
     * @mbggenerated
     */
    int deleteByExample(SysBduserroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bduserrole
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long refurid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bduserrole
     *
     * @mbggenerated
     */
    int insert(SysBduserrole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bduserrole
     *
     * @mbggenerated
     */
    int insertSelective(SysBduserrole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bduserrole
     *
     * @mbggenerated
     */
    SysBduserrole selectOneByExample(SysBduserroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bduserrole
     *
     * @mbggenerated
     */
    List<SysBduserrole> selectByExample(SysBduserroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bduserrole
     *
     * @mbggenerated
     */
    SysBduserrole selectByPrimaryKey(Long refurid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bduserrole
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysBduserrole record, @Param("example") SysBduserroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bduserrole
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysBduserrole record, @Param("example") SysBduserroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bduserrole
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysBduserrole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bduserrole
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysBduserrole record);
}