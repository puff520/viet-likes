package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.LhcGodType;
import com.likes.common.mybatis.entity.LhcGodTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LhcGodTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_god_type
     *
     * @mbggenerated
     */
    int countByExample(LhcGodTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_god_type
     *
     * @mbggenerated
     */
    int deleteByExample(LhcGodTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_god_type
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_god_type
     *
     * @mbggenerated
     */
    int insert(LhcGodType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_god_type
     *
     * @mbggenerated
     */
    int insertSelective(LhcGodType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_god_type
     *
     * @mbggenerated
     */
    LhcGodType selectOneByExample(LhcGodTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_god_type
     *
     * @mbggenerated
     */
    List<LhcGodType> selectByExample(LhcGodTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_god_type
     *
     * @mbggenerated
     */
    LhcGodType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_god_type
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") LhcGodType record, @Param("example") LhcGodTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_god_type
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") LhcGodType record, @Param("example") LhcGodTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_god_type
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LhcGodType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_god_type
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LhcGodType record);
}