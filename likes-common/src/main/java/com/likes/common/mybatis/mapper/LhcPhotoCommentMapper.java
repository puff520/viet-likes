package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.LhcPhotoComment;
import com.likes.common.mybatis.entity.LhcPhotoCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LhcPhotoCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_photo_comment
     *
     * @mbggenerated
     */
    int countByExample(LhcPhotoCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_photo_comment
     *
     * @mbggenerated
     */
    int deleteByExample(LhcPhotoCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_photo_comment
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_photo_comment
     *
     * @mbggenerated
     */
    int insert(LhcPhotoComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_photo_comment
     *
     * @mbggenerated
     */
    int insertSelective(LhcPhotoComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_photo_comment
     *
     * @mbggenerated
     */
    LhcPhotoComment selectOneByExample(LhcPhotoCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_photo_comment
     *
     * @mbggenerated
     */
    List<LhcPhotoComment> selectByExample(LhcPhotoCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_photo_comment
     *
     * @mbggenerated
     */
    LhcPhotoComment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_photo_comment
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") LhcPhotoComment record, @Param("example") LhcPhotoCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_photo_comment
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") LhcPhotoComment record, @Param("example") LhcPhotoCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_photo_comment
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LhcPhotoComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lhc_photo_comment
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LhcPhotoComment record);
}