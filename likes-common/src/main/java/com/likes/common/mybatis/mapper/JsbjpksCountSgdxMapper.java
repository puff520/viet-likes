package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.JsbjpksCountSgdx;
import com.likes.common.mybatis.entity.JsbjpksCountSgdxExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsbjpksCountSgdxMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_count_sgdx
     *
     * @mbggenerated
     */
    int countByExample(JsbjpksCountSgdxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_count_sgdx
     *
     * @mbggenerated
     */
    int deleteByExample(JsbjpksCountSgdxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_count_sgdx
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_count_sgdx
     *
     * @mbggenerated
     */
    int insert(JsbjpksCountSgdx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_count_sgdx
     *
     * @mbggenerated
     */
    int insertSelective(JsbjpksCountSgdx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_count_sgdx
     *
     * @mbggenerated
     */
    JsbjpksCountSgdx selectOneByExample(JsbjpksCountSgdxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_count_sgdx
     *
     * @mbggenerated
     */
    List<JsbjpksCountSgdx> selectByExample(JsbjpksCountSgdxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_count_sgdx
     *
     * @mbggenerated
     */
    JsbjpksCountSgdx selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_count_sgdx
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") JsbjpksCountSgdx record, @Param("example") JsbjpksCountSgdxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_count_sgdx
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") JsbjpksCountSgdx record, @Param("example") JsbjpksCountSgdxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_count_sgdx
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(JsbjpksCountSgdx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsbjpks_count_sgdx
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(JsbjpksCountSgdx record);
}