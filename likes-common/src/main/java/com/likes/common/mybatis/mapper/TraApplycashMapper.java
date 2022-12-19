package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.TraApplycash;
import com.likes.common.mybatis.entity.TraApplycashExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TraApplycashMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applycash
     *
     * @mbggenerated
     */
    int countByExample(TraApplycashExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applycash
     *
     * @mbggenerated
     */
    int deleteByExample(TraApplycashExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applycash
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long apycid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applycash
     *
     * @mbggenerated
     */
    int insert(TraApplycash record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applycash
     *
     * @mbggenerated
     */
    int insertSelective(TraApplycash record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applycash
     *
     * @mbggenerated
     */
    TraApplycash selectOneByExample(TraApplycashExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applycash
     *
     * @mbggenerated
     */
    List<TraApplycash> selectByExample(TraApplycashExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applycash
     *
     * @mbggenerated
     */
    TraApplycash selectByPrimaryKey(Long apycid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applycash
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TraApplycash record, @Param("example") TraApplycashExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applycash
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TraApplycash record, @Param("example") TraApplycashExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applycash
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TraApplycash record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applycash
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TraApplycash record);
}