package com.likes.common.mybatis.mapper;

import com.likes.common.mybatis.entity.CptOpenMember;
import com.likes.common.mybatis.entity.OrderBetRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReturnWaterTaskMapper {

    // 查询AG返水
    List<CptOpenMember> queryWaterList(@Param("startTimeDate") String startTimeDate,
                                       @Param("endTimeDate") String endTimeDate);

    // 查询KY返水
    List<CptOpenMember> queryKyWaterList(@Param("startTimeDate") String startTimeDate,
                                         @Param("endTimeDate") String endTimeDate);

    // 查询彩种返水
    List<OrderBetRecord> queryLotteryWaterList(@Param("startTimeDate") String startTimeDate,
                                               @Param("endTimeDate") String endTimeDate);


    //查询电竞返水
    List<CptOpenMember> queryEsWaterList(@Param("startTimeDate") String startTimeDate,
                                         @Param("endTimeDate") String endTimeDate);

    //查询AE返水
    List<CptOpenMember> queryAeWaterList(@Param("startTimeDate") String startTimeDate,
                                         @Param("endTimeDate") String endTimeDate);

}
