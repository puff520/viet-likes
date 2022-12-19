package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.DzCoin;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface DzCoinMapper extends Mapper<DzCoin> {

    @Select("select coin_name from dz_coin where coin_type =#{coinType}")
    String findCoinNameByCoinType(String coinType);

    @Select("select * from dz_coin where coin_name =#{coinName}")
    DzCoin findCoinByCoinName(String coinName);
}
