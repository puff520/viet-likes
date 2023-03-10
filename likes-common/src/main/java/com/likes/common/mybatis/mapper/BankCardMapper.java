package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.BankCard;
import com.likes.common.mybatis.entity.BankCardExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankCardMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bank_card
     *
     * @mbggenerated
     */
    int countByExample(BankCardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bank_card
     *
     * @mbggenerated
     */
    int deleteByExample(BankCardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bank_card
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bank_card
     *
     * @mbggenerated
     */
    int insert(BankCard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bank_card
     *
     * @mbggenerated
     */
    int insertSelective(BankCard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bank_card
     *
     * @mbggenerated
     */
    BankCard selectOneByExample(BankCardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bank_card
     *
     * @mbggenerated
     */
    List<BankCard> selectByExample(BankCardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bank_card
     *
     * @mbggenerated
     */
    BankCard selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bank_card
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BankCard record, @Param("example") BankCardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bank_card
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BankCard record, @Param("example") BankCardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bank_card
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BankCard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bank_card
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BankCard record);
}