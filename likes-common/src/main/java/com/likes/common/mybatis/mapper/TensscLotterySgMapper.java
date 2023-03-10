package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.TensscLotterySg;
import com.likes.common.mybatis.entity.TensscLotterySgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TensscLotterySgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenssc_lottery_sg
     *
     * @mbggenerated
     */
    int countByExample(TensscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenssc_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByExample(TensscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenssc_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenssc_lottery_sg
     *
     * @mbggenerated
     */
    int insert(TensscLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenssc_lottery_sg
     *
     * @mbggenerated
     */
    int insertSelective(TensscLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenssc_lottery_sg
     *
     * @mbggenerated
     */
    TensscLotterySg selectOneByExample(TensscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenssc_lottery_sg
     *
     * @mbggenerated
     */
    List<TensscLotterySg> selectByExample(TensscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenssc_lottery_sg
     *
     * @mbggenerated
     */
    TensscLotterySg selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenssc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TensscLotterySg record, @Param("example") TensscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenssc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TensscLotterySg record, @Param("example") TensscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenssc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TensscLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tenssc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TensscLotterySg record);
}