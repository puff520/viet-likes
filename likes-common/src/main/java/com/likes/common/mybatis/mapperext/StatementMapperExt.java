package com.likes.common.mybatis.mapperext;

import com.likes.common.model.vo.money.BenefitsBetVo;
import com.likes.common.mybatis.entity.OrderBetRecord;
import com.likes.common.mybatis.entity.PaymentSummary;
import com.likes.common.mybatis.entity.WithdrawPick;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatementMapperExt {
    List<OrderBetRecord> validBet(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<PaymentSummary> successCharge(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<WithdrawPick> successWithdraw(@Param("startTime") String startTime, @Param("endTime") String endTime);

    Integer chargeUser(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<BenefitsBetVo> getBenefitsBet(@Param("account") String account, @Param("memberId") Integer memberId, @Param("realName") String realName,
                                       @Param("nickname") String nickname, @Param("cate") String cate, @Param("lottery") String lottery, @Param("play") String play,
                                       @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("orderbyname") String orderbyname);


    Integer benefitsBetCount(@Param("account") String account, @Param("memberId") Integer memberId, @Param("realName") String realName,
                             @Param("nickname") String nickname, @Param("cate") String cate, @Param("lottery") String lottery, @Param("play") String play,
                             @Param("startTime") String startTime, @Param("endTime") String endTime);
}
