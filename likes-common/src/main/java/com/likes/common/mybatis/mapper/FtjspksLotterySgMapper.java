package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.FtjspksLotterySg;
import com.likes.common.mybatis.entity.FtjspksLotterySgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FtjspksLotterySgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjspks_lottery_sg
     *
     * @mbggenerated
     */
    int countByExample(FtjspksLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjspks_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByExample(FtjspksLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjspks_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjspks_lottery_sg
     *
     * @mbggenerated
     */
    int insert(FtjspksLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjspks_lottery_sg
     *
     * @mbggenerated
     */
    int insertSelective(FtjspksLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjspks_lottery_sg
     *
     * @mbggenerated
     */
    FtjspksLotterySg selectOneByExample(FtjspksLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjspks_lottery_sg
     *
     * @mbggenerated
     */
    List<FtjspksLotterySg> selectByExample(FtjspksLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjspks_lottery_sg
     *
     * @mbggenerated
     */
    FtjspksLotterySg selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjspks_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FtjspksLotterySg record, @Param("example") FtjspksLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjspks_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FtjspksLotterySg record, @Param("example") FtjspksLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjspks_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FtjspksLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ftjspks_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FtjspksLotterySg record);
}