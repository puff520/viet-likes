package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.AeGame;
import com.likes.common.mybatis.entity.AeGameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AeGameMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_game
     *
     * @mbggenerated
     */
    int countByExample(AeGameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_game
     *
     * @mbggenerated
     */
    int deleteByExample(AeGameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_game
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_game
     *
     * @mbggenerated
     */
    int insert(AeGame record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_game
     *
     * @mbggenerated
     */
    int insertSelective(AeGame record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_game
     *
     * @mbggenerated
     */
    AeGame selectOneByExample(AeGameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_game
     *
     * @mbggenerated
     */
    List<AeGame> selectByExample(AeGameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_game
     *
     * @mbggenerated
     */
    AeGame selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_game
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") AeGame record, @Param("example") AeGameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_game
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") AeGame record, @Param("example") AeGameExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_game
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AeGame record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ae_game
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AeGame record);
}