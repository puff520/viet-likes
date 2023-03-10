package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.Fc7lcLotterySg;
import com.likes.common.mybatis.entity.Fc7lcLotterySgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Fc7lcLotterySgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc7lc_lottery_sg
     *
     * @mbggenerated
     */
    int countByExample(Fc7lcLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc7lc_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByExample(Fc7lcLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc7lc_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc7lc_lottery_sg
     *
     * @mbggenerated
     */
    int insert(Fc7lcLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc7lc_lottery_sg
     *
     * @mbggenerated
     */
    int insertSelective(Fc7lcLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc7lc_lottery_sg
     *
     * @mbggenerated
     */
    Fc7lcLotterySg selectOneByExample(Fc7lcLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc7lc_lottery_sg
     *
     * @mbggenerated
     */
    List<Fc7lcLotterySg> selectByExample(Fc7lcLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc7lc_lottery_sg
     *
     * @mbggenerated
     */
    Fc7lcLotterySg selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc7lc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Fc7lcLotterySg record, @Param("example") Fc7lcLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc7lc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Fc7lcLotterySg record, @Param("example") Fc7lcLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc7lc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Fc7lcLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fc7lc_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Fc7lcLotterySg record);
}