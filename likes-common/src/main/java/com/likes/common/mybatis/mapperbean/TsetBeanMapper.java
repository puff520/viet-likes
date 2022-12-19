package com.likes.common.mybatis.mapperbean;


import com.likes.common.mybatis.entity.OperateLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TsetBeanMapper {


    @Select("select * from operate_log where id = #{id}")
    OperateLog selectTest(@Param("id") int id);

}
