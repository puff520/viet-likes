package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.FtjssscLotterySg;
import com.likes.common.mybatis.entity.FtjssscLotterySgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FtjssscLotterySgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjsssc_lottery_sg
     *
     * @mbggenerated
     */
    int countByExample(FtjssscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjsssc_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByExample(FtjssscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjsssc_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjsssc_lottery_sg
     *
     * @mbggenerated
     */
    int insert(FtjssscLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjsssc_lottery_sg
     *
     * @mbggenerated
     */
    int insertSelective(FtjssscLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjsssc_lottery_sg
     *
     * @mbggenerated
     */
    FtjssscLotterySg selectOneByExample(FtjssscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjsssc_lottery_sg
     *
     * @mbggenerated
     */
    List<FtjssscLotterySg> selectByExample(FtjssscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjsssc_lottery_sg
     *
     * @mbggenerated
     */
    FtjssscLotterySg selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjsssc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FtjssscLotterySg record, @Param("example") FtjssscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjsssc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FtjssscLotterySg record, @Param("example") FtjssscLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjsssc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FtjssscLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjsssc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FtjssscLotterySg record);
}