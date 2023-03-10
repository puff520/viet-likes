package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.ThreeStrongWayDx;
import com.likes.common.mybatis.entity.ThreeStrongWayDxExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThreeStrongWayDxMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table three_strong_way_dx
     *
     * @mbggenerated
     */
    int countByExample(ThreeStrongWayDxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table three_strong_way_dx
     *
     * @mbggenerated
     */
    int deleteByExample(ThreeStrongWayDxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table three_strong_way_dx
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table three_strong_way_dx
     *
     * @mbggenerated
     */
    int insert(ThreeStrongWayDx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table three_strong_way_dx
     *
     * @mbggenerated
     */
    int insertSelective(ThreeStrongWayDx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table three_strong_way_dx
     *
     * @mbggenerated
     */
    ThreeStrongWayDx selectOneByExample(ThreeStrongWayDxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table three_strong_way_dx
     *
     * @mbggenerated
     */
    List<ThreeStrongWayDx> selectByExample(ThreeStrongWayDxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table three_strong_way_dx
     *
     * @mbggenerated
     */
    ThreeStrongWayDx selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table three_strong_way_dx
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ThreeStrongWayDx record, @Param("example") ThreeStrongWayDxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table three_strong_way_dx
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ThreeStrongWayDx record, @Param("example") ThreeStrongWayDxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table three_strong_way_dx
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ThreeStrongWayDx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table three_strong_way_dx
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ThreeStrongWayDx record);
}