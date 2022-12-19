package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.LhcLotterySg;
import com.likes.common.mybatis.entity.LhcLotterySgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LhcLotterySgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_lottery_sg
     *
     * @mbggenerated
     */
    int countByExample(LhcLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByExample(LhcLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_lottery_sg
     *
     * @mbggenerated
     */
    int insert(LhcLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_lottery_sg
     *
     * @mbggenerated
     */
    int insertSelective(LhcLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_lottery_sg
     *
     * @mbggenerated
     */
    LhcLotterySg selectOneByExample(LhcLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_lottery_sg
     *
     * @mbggenerated
     */
    List<LhcLotterySg> selectByExample(LhcLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_lottery_sg
     *
     * @mbggenerated
     */
    LhcLotterySg selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") LhcLotterySg record, @Param("example") LhcLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") LhcLotterySg record, @Param("example") LhcLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LhcLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LhcLotterySg record);
}