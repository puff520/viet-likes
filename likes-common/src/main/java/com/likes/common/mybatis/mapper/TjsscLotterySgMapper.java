package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.TjsscLotterySg;
import com.likes.common.mybatis.entity.TjsscLotterySgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TjsscLotterySgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tjssc_lottery_sg
     *
     * @mbggenerated
     */
    int countByExample(TjsscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tjssc_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByExample(TjsscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tjssc_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tjssc_lottery_sg
     *
     * @mbggenerated
     */
    int insert(TjsscLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tjssc_lottery_sg
     *
     * @mbggenerated
     */
    int insertSelective(TjsscLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tjssc_lottery_sg
     *
     * @mbggenerated
     */
    TjsscLotterySg selectOneByExample(TjsscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tjssc_lottery_sg
     *
     * @mbggenerated
     */
    List<TjsscLotterySg> selectByExample(TjsscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tjssc_lottery_sg
     *
     * @mbggenerated
     */
    TjsscLotterySg selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tjssc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TjsscLotterySg record, @Param("example") TjsscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tjssc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TjsscLotterySg record, @Param("example") TjsscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tjssc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TjsscLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tjssc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TjsscLotterySg record);
}