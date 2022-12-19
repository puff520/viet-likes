package com.likes.common.mybatis.mapper;


import com.likes.common.model.EastTransform;
import com.likes.common.model.QutoutiaoTransform;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface EastTransformMapper extends Mapper<EastTransform> {


    @Select("select qt.*  from east_transform qt where 1=1 and  imei = #{mcode} or androidid = #{mcode} " +
            "and DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(create_time)")
    EastTransform selectQutoutiaoByAndroid(@Param("mcode") String mcode);

    @Select("select * from east_transform where 1=1 and  imei = #{imei}")
    EastTransform selectQutoutiaoByImeimd5(@Param("imei") String imei);

    @Select("select * from east_transform where 1=1 and  androidid = #{androidid}")
    EastTransform selectQutoutiaoByAndroididmd5(@Param("androidid") String androidid);


    @Select("select qt.*  from east_transform qt where 1=1 and  idfa = #{idfa}" +
            " and DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(create_time);")
    EastTransform selectQutoutiaoByIos(@Param("idfa") String idfa);

}