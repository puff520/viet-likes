package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.MemFamilymem;
import com.likes.common.mybatis.entity.MemFamilymemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemFamilymemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_familymem
     *
     * @mbggenerated
     */
    int countByExample(MemFamilymemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_familymem
     *
     * @mbggenerated
     */
    int deleteByExample(MemFamilymemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_familymem
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long familymemid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_familymem
     *
     * @mbggenerated
     */
    int insert(MemFamilymem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_familymem
     *
     * @mbggenerated
     */
    int insertSelective(MemFamilymem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_familymem
     *
     * @mbggenerated
     */
    MemFamilymem selectOneByExample(MemFamilymemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_familymem
     *
     * @mbggenerated
     */
    List<MemFamilymem> selectByExample(MemFamilymemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_familymem
     *
     * @mbggenerated
     */
    MemFamilymem selectByPrimaryKey(Long familymemid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_familymem
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MemFamilymem record, @Param("example") MemFamilymemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_familymem
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MemFamilymem record, @Param("example") MemFamilymemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_familymem
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MemFamilymem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_familymem
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MemFamilymem record);
}