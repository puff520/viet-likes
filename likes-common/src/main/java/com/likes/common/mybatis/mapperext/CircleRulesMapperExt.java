package com.likes.common.mybatis.mapperext;

import com.likes.common.mybatis.entity.CircleRules;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CircleRulesMapperExt {

    @Select("select id,content from circle_rules order by create_time desc LIMIT 1")
    CircleRules getLastOne();

}
