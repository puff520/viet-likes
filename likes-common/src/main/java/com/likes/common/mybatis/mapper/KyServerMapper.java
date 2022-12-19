package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.KyServer;
import com.likes.common.mybatis.entity.KyServerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KyServerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ky_server
     *
     * @mbggenerated
     */
    int countByExample(KyServerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ky_server
     *
     * @mbggenerated
     */
    int deleteByExample(KyServerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ky_server
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ky_server
     *
     * @mbggenerated
     */
    int insert(KyServer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ky_server
     *
     * @mbggenerated
     */
    int insertSelective(KyServer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ky_server
     *
     * @mbggenerated
     */
    KyServer selectOneByExample(KyServerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ky_server
     *
     * @mbggenerated
     */
    List<KyServer> selectByExample(KyServerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ky_server
     *
     * @mbggenerated
     */
    KyServer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ky_server
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") KyServer record, @Param("example") KyServerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ky_server
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") KyServer record, @Param("example") KyServerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ky_server
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(KyServer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ky_server
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(KyServer record);
}