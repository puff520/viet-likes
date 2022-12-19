package com.likes.common.service.money;

import com.likes.common.model.dto.GreatPerson;
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
import com.likes.common.model.dto.order.OrderTotalDTO;
import com.likes.common.model.dto.order.TraOrderinfomDTO;
import com.likes.common.model.dto.report.DepositStatisticsDO;
import com.likes.common.model.dto.report.ThirdProviderDO;
import com.likes.common.model.request.*;
import com.likes.common.model.response.*;
import com.likes.common.mybatis.entity.ChessOrder;
import com.likes.common.mybatis.entity.TraOrderinfom;
import com.likes.common.mybatis.entity.TraOrderinfomExample;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TraOrderinfomService {

    int countByExample(TraOrderinfomExample example);

    int deleteByExample(TraOrderinfomExample example);

    int deleteByPrimaryKey(Long orderid);

    int insert(TraOrderinfom record);

    int insertSelective(TraOrderinfom record);

    TraOrderinfom selectOneByExample(TraOrderinfomExample example);

    List<TraOrderinfom> selectByExample(TraOrderinfomExample example);

    TraOrderinfom selectByPrimaryKey(Long orderid);

    int updateByExampleSelective(TraOrderinfom record, TraOrderinfomExample example);

    int updateByExample(TraOrderinfom record, TraOrderinfomExample example);

    int updateByPrimaryKeySelective(TraOrderinfom record);

    int updateByPrimaryKey(TraOrderinfom record);

    int insertOrder(TraOrderinfom traOrderinfom);

    Page<OrderResponse> getMyOrderList(OrderRequest req, RowBounds rowBounds);

    int updateStatus(TraOrderinfom traOrderinfom);

    TraOrderinfom selectWeiZhifuOne(String accno, BigDecimal price);

    int insertIncarateOrder(TraOrderinfom traOrderinfom);

    TraOrderinfom existOrderinfom(OrderRequest orderRequest);

    TraOrderinfom isNew(String accno);

    List<TraOrderinfom> findChongZhiDaijiesuanByAccno(String accno);

    List<TraOrderinfom> findOrderByAccnoAndStatus(OrderRequest yitixianRequest);

    int insertXiaZhuOrder(TraOrderinfomReq traOrderinfom);


    Page<ChessKaiYuanResponse> myMoreKaiyuanChessList(ChessRequest req, RowBounds rowBounds);

    ChessKaiYuanStatisticsResponse getStatistics(ChessRequest req);

    List<ChessOrder> getQiPaiStatisticsByPerson(ChessRequest chessRequest);

    CaiStatisticsResponse getCaiZhongStatistics(CaiRequest req);

    Page<CaiBettingRecordDO> getMyMoreCaiList(CaiRequest req, RowBounds rowBounds);

    GreatPerson getShenSuanZi();

    GreatPerson getCaiqizhubo();

    GreatPerson getCaifuzi();

    TraOrderinfom findByOrderno(String orderno);

    int doUpdateRechargeOrder(TraOrderinfom traOrderinfom);

    Page<EntryOrderResponse> appOrderList(EntryOrderReq req, RowBounds rowBounds);
    Page<EntryOrderResponse> orderList(EntryOrderReq req, RowBounds rowBounds);

    List<EntryOrderExcelResponse> orderList(EntryOrderReq req);

    EntryOrderTotalResponse orderListTotal(EntryOrderReq req);

    Page<IncarnateOrderResponse> incarnateOrderList(IncarnateOrderReq req, RowBounds rowBounds);

    Page<IncarnateOrderResponse> incarnateOrderListBySuper(IncarnateOrderReq req, RowBounds rowBounds);
    Page<IncarnateOrderResponse> appIncarnateOrderListBySuper(IncarnateOrderReq req, RowBounds rowBounds);

    Page<AgentTakeCashOrder> agentTakeCashOrderList(AgentOrderReq req, RowBounds rowBounds);

    BigDecimal incarnateOrderActualTotal(IncarnateOrderReq req);

    int doUpdateIncarnateHandleOrder(TraOrderinfom traOrderinfom);

    int doUpdateIncarnateCancelSureOrder(TraOrderinfom traOrderinfom);

    int updateIncarnateConfirmOrder(TraOrderinfom traOrderinfom);

    int udunUpdateIncarnateConfirmOrder(TraOrderinfom traOrderinfom);

    TraOrderinfom findByFamilyAccno(String accno);

    int insertFamilyIncarateOrder(TraOrderinfom traOrderinfom);

    TraOrderinfom findLastOrderByFamilyAccno(FamilyIncarnateRequest req);


    void doJiesuanOrder(List<Long> list);

    int insertCommonOrder(TraOrderinfom traOrderinfom);

    Page<ChessOrderResponse> getShangfenOrderByPerson(ChessRequest req, RowBounds rowBounds);

    List<ChessOrderDO> getQiPaiXiaZhuByPerson(ChessRequest req);

    Page<ChessOrderStatisticsResponse> getChessStatisticsQipai(ChessRequest req, RowBounds rowBounds);


    Page<EntryOrderResponse> onlineOrderList(EntryOrderReq req, RowBounds rowBounds);

    Page<AgentRechargeOrder> agentRechargeOrderList(AgentOrderReq req, RowBounds rowBounds);

    StatisticsOnLineOrderResponse getStatisticsOnLineOrder(EntryOrderReq req);

    List<TraOrderinfom> getOrderByRoom(FamilyStatisticsRequest f);

    void doUpdateJieSuanOrderById(FamilyIncarnateRequest familyIncarnateRequest);

    Page<CaiBettingRecordDO> getCaiList(BettingRecordRequest req, RowBounds rowBounds);

    TraOrderinfom findExistByAccno(String accno, int ordertype, Long paysetid);

    TraOrderinfom findExistByAccnoRengong(String accno, Long paysetid);

    int doUpdateFamilyRechargeOrder(TraOrderinfom traOrderinfom);

    Page<CaiBettingRecordDO> getKSBettingRecordList(BettingRecordRequest req, RowBounds rowBounds);

    Page<CaiBettingRecordDO> getSSCBettingRecordList(BettingRecordRequest req, RowBounds rowBounds);

    List<UserResp> getAnchorTopTen(UserReq userReq);

    BigDecimal countRecgoldnum(String accno);

    Page<FamilyIncarnateResponse> getOrderList(FamilyIncarnateRequest req, RowBounds toRowBounds);

    void updateOrderinfom(Long orderid, String status, String orderstatus);

    Long getApycId(Long orderid);

    Double getSumamt(Long roomid);

    String findNoteById(Long orderid);

    /**
     * 统计在线会员在本房间指定时间端送出的礼物总金额
     */
    double statisticsRoomLineMemGift(Map param);

    long countTraOrderinfom();

    /**
     * 获取代理的昨天的用户充值数
     */
    List<TraOrderinfomDTO> getYesterdayAgentOrder(String refaccno, String startDate, String endDate);

    /**
     * 统计房间最后一次收入(最近的一段直播收入)
     */
    double statisticsRoomLastInCome(Map param);

    /**
     * 统计房间总收入(所有直播段收入)
     */
    double statisticsRoomAllInCome(Long roomId);

    /**
     * 获取代理的昨天的用户充值数
     */
    TraOrderinfom getYesterdayWithdraw(String refaccno, String startDate, String endDate);

    Integer offlinePayNum(String accno);

    /**
     * 插入三方订单号
     *
     * @param orderno 订单号
     * @param paycode 三方订单号
     * @return
     */
    Integer updatePaycode(String orderno, String paycode);

    Integer updateOrderNotSuccess(TraOrderinfom traOrderinfom);

    List<EntryOnLineOrderExcelResponse> onlineOrderList(EntryOrderReq entryOrderReq);

    DepositStatisticsDO selectSumPeople(String startTime, String endTime, Integer ordertype2);

    List<DepositStatisticsDO> selectNumPeopleCompany(String startTime, String endTime, Integer ordertype2);

    List<EntryIncarnateOrderExcelResponse> incarnateOrderListByExcel(IncarnateOrderReq req);

    List<ThirdProviderDO> getDepositReportThird(String startTime, String endTime, List<Long> ids);

    List<ThirdProviderDO> getDepositReportFailThird(String startTime, String endTime, List<Long> ids);

    DepositStatisticsDO getPayFor(String startTime, String endTime);

    OrderTotalDTO getFamilyOrderTotal(String accno, String startTime, String endTime);

    List<ThirdProviderDO> getallPeople(String startTime, String endTime, List<Long> ids);

    List<TraOrderinfom> doDayExportOrder(String orderStatus, Integer orderType);

    void batchUpdateOrder(List<TraOrderinfom> orderList);

}
