package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.BasInvestors;
import com.likes.common.mybatis.entity.BasInvestorsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasInvestorsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_investors
     *
     * @mbggenerated
     */
    int countByExample(BasInvestorsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_investors
     *
     * @mbggenerated
     */
    int deleteByExample(BasInvestorsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_investors
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long investorsid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_investors
     *
     * @mbggenerated
     */
    int insert(BasInvestors record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_investors
     *
     * @mbggenerated
     */
    int insertSelective(BasInvestors record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_investors
     *
     * @mbggenerated
     */
    BasInvestors selectOneByExample(BasInvestorsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_investors
     *
     * @mbggenerated
     */
    List<BasInvestors> selectByExample(BasInvestorsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_investors
     *
     * @mbggenerated
     */
    BasInvestors selectByPrimaryKey(Long investorsid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_investors
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BasInvestors record, @Param("example") BasInvestorsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_investors
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BasInvestors record, @Param("example") BasInvestorsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_investors
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BasInvestors record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_investors
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BasInvestors record);
}