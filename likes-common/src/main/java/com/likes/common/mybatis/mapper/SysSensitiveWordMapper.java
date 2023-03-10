package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.SysSensitiveWord;
import com.likes.common.mybatis.entity.SysSensitiveWordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSensitiveWordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    int countByExample(SysSensitiveWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    int deleteByExample(SysSensitiveWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    int insert(SysSensitiveWord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    int insertSelective(SysSensitiveWord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    SysSensitiveWord selectOneByExampleWithBLOBs(SysSensitiveWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    List<SysSensitiveWord> selectByExampleWithBLOBs(SysSensitiveWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    SysSensitiveWord selectOneByExample(SysSensitiveWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    List<SysSensitiveWord> selectByExample(SysSensitiveWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    SysSensitiveWord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysSensitiveWord record, @Param("example") SysSensitiveWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") SysSensitiveWord record, @Param("example") SysSensitiveWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysSensitiveWord record, @Param("example") SysSensitiveWordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysSensitiveWord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(SysSensitiveWord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sensitive_word
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysSensitiveWord record);
}