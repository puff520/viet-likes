package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.MgGame;
import com.likes.common.mybatis.entity.MgGameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MgGameMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    int countByExample(MgGameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    int deleteByExample(MgGameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    int insert(MgGame record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    int insertSelective(MgGame record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    MgGame selectOneByExample(MgGameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    List<MgGame> selectByExample(MgGameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    MgGame selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MgGame record, @Param("example") MgGameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MgGame record, @Param("example") MgGameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MgGame record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mg_game
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MgGame record);
}