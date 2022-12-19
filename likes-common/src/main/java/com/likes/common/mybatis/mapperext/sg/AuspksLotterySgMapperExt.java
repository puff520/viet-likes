package com.likes.common.mybatis.mapperext.sg;

import com.likes.common.mybatis.entity.AuspksLotterySg;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
@Mapper
public interface AuspksLotterySgMapperExt {
    int updateByIssue(AuspksLotterySg record);

    int openCountByExample(Map<String,Object> map);
}
