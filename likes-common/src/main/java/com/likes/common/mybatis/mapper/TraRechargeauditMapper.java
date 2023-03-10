package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.TraRechargeaudit;
import com.likes.common.mybatis.entity.TraRechargeauditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TraRechargeauditMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargeaudit
     *
     * @mbggenerated
     */
    int countByExample(TraRechargeauditExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargeaudit
     *
     * @mbggenerated
     */
    int deleteByExample(TraRechargeauditExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargeaudit
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long recauditid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargeaudit
     *
     * @mbggenerated
     */
    int insert(TraRechargeaudit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargeaudit
     *
     * @mbggenerated
     */
    int insertSelective(TraRechargeaudit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargeaudit
     *
     * @mbggenerated
     */
    TraRechargeaudit selectOneByExample(TraRechargeauditExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargeaudit
     *
     * @mbggenerated
     */
    List<TraRechargeaudit> selectByExample(TraRechargeauditExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargeaudit
     *
     * @mbggenerated
     */
    TraRechargeaudit selectByPrimaryKey(Long recauditid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargeaudit
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TraRechargeaudit record, @Param("example") TraRechargeauditExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargeaudit
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TraRechargeaudit record, @Param("example") TraRechargeauditExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargeaudit
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TraRechargeaudit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_rechargeaudit
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TraRechargeaudit record);
}