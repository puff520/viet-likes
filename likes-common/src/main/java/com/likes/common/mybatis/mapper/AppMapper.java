package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.App;
import com.likes.common.mybatis.entity.AppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    int countByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    int deleteByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    int insert(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    int insertSelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    App selectOneByExampleWithBLOBs(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    List<App> selectByExampleWithBLOBs(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    App selectOneByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    List<App> selectByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    App selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") App record, @Param("example") AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") App record, @Param("example") AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") App record, @Param("example") AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(App record);
}