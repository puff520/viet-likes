package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.MemFollow;
import com.likes.common.mybatis.entity.MemFollowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemFollowMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_follow
     *
     * @mbggenerated
     */
    int countByExample(MemFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_follow
     *
     * @mbggenerated
     */
    int deleteByExample(MemFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_follow
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long followid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_follow
     *
     * @mbggenerated
     */
    int insert(MemFollow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_follow
     *
     * @mbggenerated
     */
    int insertSelective(MemFollow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_follow
     *
     * @mbggenerated
     */
    MemFollow selectOneByExample(MemFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_follow
     *
     * @mbggenerated
     */
    List<MemFollow> selectByExample(MemFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_follow
     *
     * @mbggenerated
     */
    MemFollow selectByPrimaryKey(Long followid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_follow
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MemFollow record, @Param("example") MemFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_follow
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MemFollow record, @Param("example") MemFollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_follow
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MemFollow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_follow
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MemFollow record);
}