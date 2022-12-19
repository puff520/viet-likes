package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.BasRobotset;
import com.likes.common.mybatis.entity.BasRobotsetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasRobotsetMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_robotset
     *
     * @mbggenerated
     */
    int countByExample(BasRobotsetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_robotset
     *
     * @mbggenerated
     */
    int deleteByExample(BasRobotsetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_robotset
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long robotSetid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_robotset
     *
     * @mbggenerated
     */
    int insert(BasRobotset record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_robotset
     *
     * @mbggenerated
     */
    int insertSelective(BasRobotset record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_robotset
     *
     * @mbggenerated
     */
    BasRobotset selectOneByExample(BasRobotsetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_robotset
     *
     * @mbggenerated
     */
    List<BasRobotset> selectByExample(BasRobotsetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_robotset
     *
     * @mbggenerated
     */
    BasRobotset selectByPrimaryKey(Long robotSetid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_robotset
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BasRobotset record, @Param("example") BasRobotsetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_robotset
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BasRobotset record, @Param("example") BasRobotsetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_robotset
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BasRobotset record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bas_robotset
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BasRobotset record);
}