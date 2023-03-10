package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.FinanceDamaAdjustment;
import com.likes.common.mybatis.entity.FinanceDamaAdjustmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FinanceDamaAdjustmentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    int countByExample(FinanceDamaAdjustmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    int deleteByExample(FinanceDamaAdjustmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    int insert(FinanceDamaAdjustment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    int insertSelective(FinanceDamaAdjustment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    FinanceDamaAdjustment selectOneByExample(FinanceDamaAdjustmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    List<FinanceDamaAdjustment> selectByExample(FinanceDamaAdjustmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    FinanceDamaAdjustment selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FinanceDamaAdjustment record, @Param("example") FinanceDamaAdjustmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FinanceDamaAdjustment record, @Param("example") FinanceDamaAdjustmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FinanceDamaAdjustment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table finance_dama_adjustment
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FinanceDamaAdjustment record);
}