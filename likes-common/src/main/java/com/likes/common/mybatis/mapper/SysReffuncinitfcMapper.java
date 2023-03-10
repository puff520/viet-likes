package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.SysReffuncinitfc;
import com.likes.common.mybatis.entity.SysReffuncinitfcExample;
import com.likes.common.mybatis.entity.SysReffuncinitfcKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysReffuncinitfcMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_reffuncinitfc
     *
     * @mbggenerated
     */
    int countByExample(SysReffuncinitfcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_reffuncinitfc
     *
     * @mbggenerated
     */
    int deleteByExample(SysReffuncinitfcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_reffuncinitfc
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(SysReffuncinitfcKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_reffuncinitfc
     *
     * @mbggenerated
     */
    int insert(SysReffuncinitfc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_reffuncinitfc
     *
     * @mbggenerated
     */
    int insertSelective(SysReffuncinitfc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_reffuncinitfc
     *
     * @mbggenerated
     */
    SysReffuncinitfc selectOneByExample(SysReffuncinitfcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_reffuncinitfc
     *
     * @mbggenerated
     */
    List<SysReffuncinitfc> selectByExample(SysReffuncinitfcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_reffuncinitfc
     *
     * @mbggenerated
     */
    SysReffuncinitfc selectByPrimaryKey(SysReffuncinitfcKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_reffuncinitfc
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysReffuncinitfc record, @Param("example") SysReffuncinitfcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_reffuncinitfc
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysReffuncinitfc record, @Param("example") SysReffuncinitfcExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_reffuncinitfc
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysReffuncinitfc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_reffuncinitfc
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysReffuncinitfc record);
}