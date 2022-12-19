package com.likes.common.mybatis.mapperext.tra;

import com.likes.common.model.AgentTransactionDO;
import com.likes.common.model.AgentTransactionDetailDO;
import com.likes.common.model.dto.GreatPerson;
import com.likes.common.model.dto.chess.*;
import com.likes.common.model.dto.order.*;
import com.likes.common.model.dto.pay.TraOrderinfomDO;
import com.likes.common.model.dto.chess.ChessKaiYuanResponse;
import com.likes.common.model.dto.chess.ChessKaiYuanStatisticsResponse;
import com.likes.common.model.dto.chess.ChessOrderDO;
import com.likes.common.model.dto.chess.ChessOrderResponse;
import com.likes.common.model.dto.chess.ChessOrderStatisticsResponse;
import com.likes.common.model.dto.chess.ChessRequest;
import com.likes.common.model.dto.order.BettingRecordDO;
import com.likes.common.model.dto.order.CaiBettingRecordDO;
import com.likes.common.model.dto.order.CaiStatisticsResponse;
import com.likes.common.model.dto.order.OrderRequest;
import com.likes.common.model.dto.order.OrderResponse;
import com.likes.common.model.dto.order.TraOrderinfomDTO;
import com.likes.common.model.dto.report.DailyFundsDataDO;
import com.likes.common.model.dto.report.DepositStatisticsDO;
import com.likes.common.model.dto.report.FirstRechargeDailyDataDO;
import com.likes.common.model.dto.report.GiftDailyDataDO;
import com.likes.common.model.dto.report.GiftDailyRoomDO;
import com.likes.common.model.dto.report.ThirdProviderDO;
import com.likes.common.model.dto.report.*;
import com.likes.common.model.request.*;
import com.likes.common.model.request.CaiRequest;
import com.likes.common.model.response.*;
import com.likes.common.mybatis.entity.ChessOrder;
import com.likes.common.mybatis.entity.TraOrderinfom;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface TraOrderinfomMapperExt {

    int insertOrder(TraOrderinfom traOrderinfom);

    Page<OrderResponse> getMyOrderList(OrderRequest req, RowBounds rowBounds);


    int updateStatus(TraOrderinfom traOrderinfom);

    TraOrderinfom selectWeiZhifuOne(@Param("accno") String accno, @Param("price") BigDecimal price);

    int insertIncarateOrder(TraOrderinfom traOrderinfom);

    TraOrderinfom existOrderinfom(OrderRequest orderRequest);

    TraOrderinfom isNew(String accno);


    List<TraOrderinfom> findChongZhiDaijiesuanByAccno(String accno);

    List<TraOrderinfom> findOrderByAccnoAndStatus(OrderRequest yitixianRequest);

    int insertXiaZhuOrder(TraOrderinfomReq traOrderinfom);

    Page<BettingRecordDO> getMyBetrecordList(BettingRecordRequest req, RowBounds rowBounds);

    Page<ChessKaiYuanResponse> myMoreKaiyuanChessList(ChessRequest req, RowBounds rowBounds);

    ChessKaiYuanStatisticsResponse getStatistics(ChessRequest req);


    List<ChessOrder> getQiPaiStatisticsByPerson(ChessRequest chessRequest);

    CaiStatisticsResponse getCaiZhongStatistics(CaiRequest req);

    Page<CaiBettingRecordDO> getMyMoreCaiList(CaiRequest req, RowBounds rowBounds);

    //牛人 神算子
    GreatPerson getShenSuanZi();

    //牛人  财气主播
    GreatPerson getCaiqizhubo();

    //财富子 消费最高
    GreatPerson getCaifuzi();


    TraOrderinfom findByOrderno(String orderno);

    int doUpdateRechargeOrder(TraOrderinfom traOrderinfom);

    Page<EntryOrderResponse> orderList(EntryOrderReq req, RowBounds rowBounds);

    Page<EntryOrderResponse> appOrderList(EntryOrderReq req, RowBounds rowBounds);

    List<EntryOrderExcelResponse> exportOrderList(EntryOrderReq req);

    EntryOrderTotalResponse orderListTotal(EntryOrderReq req);

    Page<IncarnateOrderResponse> incarnateOrderList(IncarnateOrderReq req, RowBounds rowBounds);

    Page<IncarnateOrderResponse> incarnateOrderListBySuper(IncarnateOrderReq req, RowBounds rowBounds);
    Page<IncarnateOrderResponse> appIncarnateOrderListBySuper(IncarnateOrderReq req, RowBounds rowBounds);
    Page<AgentTakeCashOrder> agentTakeCashOrderList(AgentOrderReq req, RowBounds rowBounds);

    List<EntryIncarnateOrderExcelResponse> incarnateOrderListExcel(IncarnateOrderReq req);

    int doUpdateIncarnateHandleOrder(TraOrderinfom traOrderinfom);

    int doUpdateIncarnateCancelSureOrder(TraOrderinfom traOrderinfom);

    int updateIncarnateConfirmOrder(TraOrderinfom traOrderinfom);

    int undUpdateIncarnateConfirmOrder(TraOrderinfom traOrderinfom);

    TraOrderinfom findByFamilyAccno(String accno);

    //家族体现订单
    int insertFamilyIncarateOrder(TraOrderinfom traOrderinfom);

    TraOrderinfom findLastOrderByFamilyAccno(FamilyIncarnateRequest req);

    void doJiesuanOrder(List<Long> list);

    int insertCommonOrder(TraOrderinfom traOrderinfom);

    Page<ChessOrderResponse> getShangfenOrderByPerson(ChessRequest req, RowBounds rowBounds);

    List<ChessOrderDO> getQiPaiXiaZhuByPerson(ChessRequest req);

    Page<ChessOrderStatisticsResponse> getChessStatisticsQipai(ChessRequest req, RowBounds rowBounds);

    int updateJieSuanStatus(Long orderid);

    Page<EntryOrderResponse> onlineOrderList(EntryOrderReq req, RowBounds rowBounds);

    Page<AgentRechargeOrder> agentRechargeOrderList(AgentOrderReq req, RowBounds rowBounds);

    StatisticsOnLineOrderResponse getStatisticsOnLineOrder(EntryOrderReq req);

    List<TraOrderinfom> getOrderByRoom(FamilyStatisticsRequest f);

    void doUpdateJieSuanOrderById(FamilyIncarnateRequest familyIncarnateRequest);

    Page<CaiBettingRecordDO> getCaiList(BettingRecordRequest req, RowBounds rowBounds);

    TraOrderinfom findExistByAccno(@Param("accno") String accno,
                                   @Param("ordertype") int ordertype, @Param("paysetid") Long paysetid);

    TraOrderinfom findExistByAccnoRengong(@Param("accno") String accno, @Param("paysetid") Long paysetid);

    int doUpdateFamilyRechargeOrder(TraOrderinfom traOrderinfom);

    Page<CaiBettingRecordDO> getKSBettingRecordList(BettingRecordRequest req, RowBounds rowBounds);

    Page<CaiBettingRecordDO> getSSCBettingRecordList(BettingRecordRequest req, RowBounds rowBounds);

    List<UserResp> getAnchorTopTen(UserReq userReq);

    @Select("select ifnull(sum(o.apycamt),0.00) from tra_applycash o where o.is_delete = 0 and o.apycstatus in (1,2) and o.accno = #{accno}")
    BigDecimal countRecgoldnum(@Param("accno") String accno);

    Page<FamilyIncarnateResponse> getOrderList(FamilyIncarnateRequest req, RowBounds toRowBounds);

    @Update("update tra_orderinfom set orderstatus = #{orderstatus},accountstatus = #{status} where orderid = #{orderid}")
    void updateOrderinfom(@Param("orderid") Long orderid, @Param("status") String status, @Param("orderstatus") String orderstatus);

    @Select("select ifnull(o.apycid,0) from tra_applycash o where o.is_delete = 0 and o.orderid = #{orderid}")
    Long getApycId(@Param("orderid") Long orderid);

    @Select("SELECT ifnull(sum(sumamt),0) from tra_orderinfom where is_delete=b'0' and ordertype=10 AND roomid=#{roomid}")
    Double getSumamt(@Param("roomid") Long roomid);


    String findNoteById(@Param("orderid") Long orderid);

    /**
     * 统计本次直播会员送出礼物总金额
     *
     * @param map
     * @return
     */
    Double statisticsRoomLineMemGift(Map map);

    /**
     * 统计房间最后一次直播的收入
     */
    Double statisticsRoomLastInCome(Map map);

    /**
     * 统计房间总收入(所有直播段收入)
     */
    Double statisticsRoomAllInCome(Long roomId);

    @Select("SELECT ifnull(count(*),0) FROM tra_orderinfom d where \n" +
            " d.ordertype in (12,13) and d.orderstatus in ('acc04','acc08') and d.accountstatus in ('acc04','acc08')  and d.is_delete = b'0'")
    long countTraOrderinfom();

    List<TraOrderinfomDTO> getYesterdayAgentOrder(@Param("refaccno") String refaccno, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取用户提现历史
     */
    Page<TraOrderinfom> getUserWithdraw(SysAgentinfoReq req, RowBounds page);

    TraOrderinfom getYesterdayWithdraw(@Param("refaccno") String refaccno, @Param("startDate") String startDate, @Param("endDate") String endDate);

    DailyFundsDataDO statisticalDayData(@Param("startTime") String startTime, @Param("endTime") String endTime);

    GiftDailyDataDO statisticalGiftSumamtData(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<GiftDailyRoomDO> statisticalGiftamtData(@Param("startTime") String startTime, @Param("endTime") String endTime);

    FirstRechargeDailyDataDO statisticalFirstRechargeData(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 补历史数据用
     */
    List<String> getAllWithdrawalAndPayAccno();

    List<Double> getPayTotalByAccno(@Param("accno") String accno);

    List<Double> getWithdrawalTotalByAccno(@Param("accno") String accno);

    /**
     * 修改三方订单号
     *
     * @param orderno
     * @param paycode
     * @return
     */
    Integer updatePaycode(@Param("orderno") String orderno, @Param("paycode") String paycode);

    Integer updateOrderNotSuccess(TraOrderinfom traOrderinfom);

    /**
     * 查询代充人的订单
     *
     * @return
     */
    List<TraOrderinfomDO> getRepayuserOrderList(@Param("accno") String accno, @Param("startTime") String startTime, @Param("endTime") String endTime);

    TraOrderinfom findSuccessByOrderno(String orderno);


    List<EntryOnLineOrderExcelResponse> onlineOrderListExecl(EntryOrderReq req);

    List<RechargeUserDataDO> getRecharge(@Param("startTime") String startTime,
                                         @Param("endTime") String endTime);
//    DepositStatisticsDO selectSumPeople(@Param("startTime") String startTime, @Param("endTime") String endTime);

    Page<AgentTransactionDO> selectAgentOrderList(AgentTransactionQuery query, RowBounds toRowBounds);

    AgentTransactionDetailDO selectAgentOrderById(Long orderid);

    DepositStatisticsDO selectSumPeople(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("ordertype") Integer ordertype);

    List<DepositStatisticsDO> selectNumPeopleCompany(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("ordertype2") Integer ordertype2);

    List<ThirdProviderDO> getDepositReportThird(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("ids") List<Long> ids);

    List<ThirdProviderDO> getDepositReportFailThird(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("ids") List<Long> ids);

    BigDecimal incarnateOrderActualTotal(IncarnateOrderReq req);

    DepositStatisticsDO getPayFor(@Param("startTime") String startTime, @Param("endTime") String endTime);

    Double getFamilyOrderGiftTotal(@Param("accno") String accno, @Param("startTime") String startTime, @Param("endTime") String endTime);

    Double getFamilyOrderBetTotal(@Param("accno") String accno, @Param("startTime") String startTime, @Param("endTime") String endTime);

    List<ThirdProviderDO> getallPeople(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("ids") List<Long> ids);

    List<TraOrderinfom> doDayExportOrder(@Param("orderStatus") String orderStatus, @Param("orderType") Integer orderType);

    void batchUpdateOrder(@Param("orderList") List<TraOrderinfom> orderList);

    @Select("SELECT count(1) FROM tra_orderinfom  trf WHERE trf.ordertype = 1 or ordertype =  2  and orderstatus = 'ord08' and accno = #{accno}")
    int countFirstRecharge(String accno);

    @Select("SELECT count(1) FROM tra_orderinfom  trf WHERE trf.ordertype = 3 or ordertype =  4  and orderstatus = 'ord12' and to_days(trf.create_time) = to_days(now()) and accno = #{accno}")
    int countTodayWithdrawal(String accno);
}
