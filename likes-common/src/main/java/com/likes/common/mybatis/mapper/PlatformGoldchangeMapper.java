package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.PlatformGoldchange;
import com.likes.common.mybatis.entity.PlatformGoldchangeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlatformGoldchangeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_goldchange
     *
     * @mbggenerated
     */
    int countByExample(PlatformGoldchangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_goldchange
     *
     * @mbggenerated
     */
    int deleteByExample(PlatformGoldchangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_goldchange
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long pgoldid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_goldchange
     *
     * @mbggenerated
     */
    int insert(PlatformGoldchange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_goldchange
     *
     * @mbggenerated
     */
    int insertSelective(PlatformGoldchange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_goldchange
     *
     * @mbggenerated
     */
    PlatformGoldchange selectOneByExample(PlatformGoldchangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_goldchange
     *
     * @mbggenerated
     */
    List<PlatformGoldchange> selectByExample(PlatformGoldchangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_goldchange
     *
     * @mbggenerated
     */
    PlatformGoldchange selectByPrimaryKey(Long pgoldid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_goldchange
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") PlatformGoldchange record, @Param("example") PlatformGoldchangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_goldchange
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") PlatformGoldchange record, @Param("example") PlatformGoldchangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_goldchange
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PlatformGoldchange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_goldchange
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PlatformGoldchange record);
}