package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.AgPayType;
import com.likes.common.mybatis.entity.AgPayTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgPayTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ag_pay_type
     *
     * @mbggenerated
     */
    int countByExample(AgPayTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ag_pay_type
     *
     * @mbggenerated
     */
    int deleteByExample(AgPayTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ag_pay_type
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ag_pay_type
     *
     * @mbggenerated
     */
    int insert(AgPayType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ag_pay_type
     *
     * @mbggenerated
     */
    int insertSelective(AgPayType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ag_pay_type
     *
     * @mbggenerated
     */
    AgPayType selectOneByExample(AgPayTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ag_pay_type
     *
     * @mbggenerated
     */
    List<AgPayType> selectByExample(AgPayTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ag_pay_type
     *
     * @mbggenerated
     */
    AgPayType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ag_pay_type
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") AgPayType record, @Param("example") AgPayTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ag_pay_type
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") AgPayType record, @Param("example") AgPayTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ag_pay_type
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AgPayType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ag_pay_type
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AgPayType record);
}