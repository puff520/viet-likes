package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.TcplsLotterySg;
import com.likes.common.mybatis.entity.TcplsLotterySgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TcplsLotterySgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tcpls_lottery_sg
     *
     * @mbggenerated
     */
    int countByExample(TcplsLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tcpls_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByExample(TcplsLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tcpls_lottery_sg
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tcpls_lottery_sg
     *
     * @mbggenerated
     */
    int insert(TcplsLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tcpls_lottery_sg
     *
     * @mbggenerated
     */
    int insertSelective(TcplsLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tcpls_lottery_sg
     *
     * @mbggenerated
     */
    TcplsLotterySg selectOneByExample(TcplsLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tcpls_lottery_sg
     *
     * @mbggenerated
     */
    List<TcplsLotterySg> selectByExample(TcplsLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tcpls_lottery_sg
     *
     * @mbggenerated
     */
    TcplsLotterySg selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tcpls_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TcplsLotterySg record, @Param("example") TcplsLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tcpls_lottery_sg
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TcplsLotterySg record, @Param("example") TcplsLotterySgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tcpls_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TcplsLotterySg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tcpls_lottery_sg
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TcplsLotterySg record);
}