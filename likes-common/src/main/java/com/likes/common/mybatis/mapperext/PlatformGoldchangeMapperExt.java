package com.likes.common.mybatis.mapperext;

import com.likes.common.mybatis.entity.PlatformGoldchange;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlatformGoldchangeMapperExt {


    /**
     * 平台分成  分 refaccno 主播的
     *
     * @param platformGoldchange
     * @return
     */
    int insertSelectivePlatformGoldchange(PlatformGoldchange platformGoldchange);
}