package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.UdunOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UdunOrderMapper extends Mapper<UdunOrder> {

    @Select("select count(1) from  dz_udun_order where  trade_type =2 and business_Id =#{businessId}")
    Integer countBusinessId(@Param("businessId") String businessId);
}
