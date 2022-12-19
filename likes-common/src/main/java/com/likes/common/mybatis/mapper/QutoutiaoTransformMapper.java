package com.likes.common.mybatis.mapper;

import com.likes.common.model.QutoutiaoTransform;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface QutoutiaoTransformMapper extends Mapper<QutoutiaoTransform> {


    @Select("select qt.CALLBACK_URL as callbackUrl,qt.*  from qutoutiao_transform qt where 1=1 and  imeimd5 = #{mcode} or androididmd5 = #{mcode} " +
            "and DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(create_time)")
    QutoutiaoTransform selectQutoutiaoByAndroid(@Param("mcode") String mcode);

    @Select("select qt.CALLBACK_URL as callbackUrl,qt.*  from qutoutiao_transform qt where 1=1 and  idfa = #{idfa}" +
            " and DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(create_time);")
    QutoutiaoTransform selectQutoutiaoByIos(@Param("idfa") String idfa);

    @Select("select * from qutoutiao_transform where 1=1 and  imeimd5 = #{imeimd5}")
    QutoutiaoTransform selectQutoutiaoByImeimd5(@Param("imeimd5") String imeimd5);

    @Select("select * from qutoutiao_transform where 1=1 and  androididmd5 = #{androididmd5}")
    QutoutiaoTransform selectQutoutiaoByAndroididmd5(@Param("androididmd5") String androididmd5);


}