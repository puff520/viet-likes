package com.likes.common.mybatis.mapperext;


import com.likes.common.model.dto.RechargeStatDTO;
import com.likes.common.mybatis.entity.PaymentSummary;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 人工充值扩展mapper
 *
 * @author dlucky
 */
public interface PaymentSummaryMapperExt {

    List<PaymentSummary> list(@Param("ids") String[] ids);

    List<Map<String, Object>> statisticsAllWay(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("status") Integer status);

    /**
     * 统计充值次数
     *
     * @param userId
     * @return
     */
    RechargeStatDTO statRechargeAccount(@Param("userId") Integer userId);

    /**
     * 统计充值次数和总值
     *
     * @param userId
     * @return
     */
    RechargeStatDTO statRechargeSum(@Param("userId") Integer userId);

    /**
     * 统计用户在线充值信息
     *
     * @param userId
     * @return
     */
    RechargeStatDTO statOnlinePayInfo(@Param("userId") Integer userId);

    Double sumRechargeForMemberId(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("memberId") Integer memberId);

}