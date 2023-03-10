package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.GoldGiveGroup;
import com.likes.common.mybatis.entity.GoldGiveGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoldGiveGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gold_give_group
     *
     * @mbggenerated
     */
    int countByExample(GoldGiveGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gold_give_group
     *
     * @mbggenerated
     */
    int deleteByExample(GoldGiveGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gold_give_group
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gold_give_group
     *
     * @mbggenerated
     */
    int insert(GoldGiveGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gold_give_group
     *
     * @mbggenerated
     */
    int insertSelective(GoldGiveGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gold_give_group
     *
     * @mbggenerated
     */
    GoldGiveGroup selectOneByExample(GoldGiveGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gold_give_group
     *
     * @mbggenerated
     */
    List<GoldGiveGroup> selectByExample(GoldGiveGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gold_give_group
     *
     * @mbggenerated
     */
    GoldGiveGroup selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gold_give_group
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") GoldGiveGroup record, @Param("example") GoldGiveGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gold_give_group
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") GoldGiveGroup record, @Param("example") GoldGiveGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gold_give_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GoldGiveGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gold_give_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GoldGiveGroup record);
}