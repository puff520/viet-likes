package com.likes.common.mybatis.mapper;


import com.likes.common.mybatis.entity.TraOrderaccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TraOrderaccountMapper {
    int insert(TraOrderaccount record);

    int insertSelective(TraOrderaccount record);
}