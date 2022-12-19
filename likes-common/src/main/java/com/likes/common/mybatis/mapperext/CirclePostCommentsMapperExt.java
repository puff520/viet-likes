package com.likes.common.mybatis.mapperext;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface CirclePostCommentsMapperExt {

    /**
     * 批量更新数据
     * @param map
     * @return
     */
    int batchUpdateByExample(Map<String,Object> map);
}
