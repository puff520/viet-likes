package com.likes.common.mybatis.mapperext;


import com.likes.common.model.dto.money.HandPaymentDTO;
import com.likes.common.model.dto.money.OfflinePaymentDTO;

import com.likes.common.model.vo.money.HandPaymentVO;
import com.likes.common.model.vo.money.OfflinePaymentVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PaymentInfoMapperExt {
    /**
     * 线下支付
     */
    List<OfflinePaymentVO> selOfflinePaymentMenu(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
                                                 @Param("offlinePaymentDTO") OfflinePaymentDTO offlinePaymentDTO);

    /**
     * 线下支付数量
     */
    Integer selOfflinePaymentMenuCount(@Param("offlinePaymentDTO") OfflinePaymentDTO offlinePaymentDTO);

    /**
     * 统计线下支付
     *
     * @return
     */
    Map<String, BigDecimal> calcMoneyOfflinePayment(@Param("offlinePaymentDTO") OfflinePaymentDTO offlinePaymentDTO);

    /**
     * 手动入款
     */
    List<HandPaymentVO> selHandPaymentMenu(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
                                           @Param("handPaymentDTO") HandPaymentDTO handPaymentDTO);

    /**
     * 手动入款数量
     */
    Integer selHandPaymentMenuCount(@Param("handPaymentDTO") HandPaymentDTO handPaymentDTO);

    Map<String, BigDecimal> countHandPayment(@Param("handPaymentDTO") HandPaymentDTO handPaymentDTO);

    /**
     * 入款汇总
     */
    List<HandPaymentVO> selSummaryPaymentMenu(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
                                              @Param("handPaymentDTO") OfflinePaymentDTO handPaymentDTO);

    /**
     * 入款汇总数量
     */
    Integer selSummaryPaymentMenuCount(@Param("handPaymentDTO") OfflinePaymentDTO handPaymentDTO);

//    /**
//     * 账变记录
//     */
//    List<BalanceChangeVO> selBalanceChange(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
//                                           @Param("memberId") Integer memberId, @Param("typeList") List<Integer> typeList,
//                                           @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 账变记录数量
     */
    Integer selBalanceChangeCount(@Param("memberId") Integer memberId, @Param("typeList") List<Integer> typeList,
                                  @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 统计变动金额
     *
     * @param memberId
     * @param typeList
     * @param startTime
     * @param endTime
     * @return
     */
    BigDecimal tatolBalanceChange(@Param("memberId") Integer memberId, @Param("typeList") List<Integer> typeList, @Param("startTime") String startTime, @Param("endTime") String endTime);
//	List<BalanceChangeVO> tatolBalanceChange(@Param("type") String type, @Param("startTime") String startTime,
//			@Param("endTime") String endTime, @Param("account") String account);

    List<OfflinePaymentVO> queryPaymentExcelExportContent(@Param("idList") List<Integer> idList);
}
