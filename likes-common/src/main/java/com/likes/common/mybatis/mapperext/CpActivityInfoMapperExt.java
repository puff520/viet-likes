package com.likes.common.mybatis.mapperext;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

public interface CpActivityInfoMapperExt {
    //此处取有效充值人次金额有误，需从paymentSummary去查
    @Select({"SELECT sum(mtu.money) FROM member_top_up mtu,cp_activity_info cai WHERE cai.act_start_time < mtu.create_time AND mtu.create_time > cai.act_end_time AND cai.id=#{actId}"})
    BigDecimal activitySumRedPackChargeCurrentDay(@Param("actId") int actId);
}