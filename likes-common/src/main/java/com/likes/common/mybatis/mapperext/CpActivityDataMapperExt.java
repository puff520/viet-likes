package com.likes.common.mybatis.mapperext;

import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

public interface CpActivityDataMapperExt {

    /**
     * 当天红包活动领取数
     *
     * @return
     */
    @Select("SELECT COUNT(id) FROM cp_activity_data WHERE TO_DAYS(receive_time) = to_days(NOW())")
    int activityRedPackCountCurrentDay();

    /**
     * 当天红包活动领取金额
     *
     * @return
     */
    @Select("SELECT SUM(receive_amount) FROM cp_activity_data WHERE TO_DAYS(receive_time) = to_days(NOW())")
    BigDecimal activityRedPackSumCurrentDay();

    /**
     * 红包活动分享总金额
     *
     * @return
     */
    @Select("SELECT sum(receive_amount) FROM cp_activity_data WHERE '2020-1-24' < create_time AND create_time < '2020-2-1' AND act_id=0")
    BigDecimal activitySumShareRedPack();

    /**
     * 统计红包活动总金额（过年）
     *
     * @return
     */
    @Select("SELECT sum(receive_amount) FROM cp_activity_data WHERE '2020-1-24' < create_time AND create_time < '2020-2-1' AND act_id!=0")
    BigDecimal activitySumRedPack();
}