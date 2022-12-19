package com.likes.common.mybatis.mapperext;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JPushInfoMapperExt {
    List<Integer> getPushSettingOnUser(@Param("tag") String tag);

    Integer winPushIsOff(@Param("tag") String tag, @Param("userId") Integer userId);
}
