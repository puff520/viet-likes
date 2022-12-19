package com.likes.common.service.money.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.constant.Constants;
import com.likes.common.constant.RedisKeys;
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
import com.likes.common.mybatis.mapper.TraOrderinfomMapper;
import com.likes.common.mybatis.mapperext.tra.TraOrderinfomMapperExt;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class TraOrderinfomServiceImpl implements TraOrderinfomService {
    private static final Logger logger = LoggerFactory.getLogger(TraOrderinfomServiceImpl.class);

    @Resource
    private TraOrderinfomMapper traOrderinfomMapper;
    @Resource
    private TraOrderinfomMapperExt traOrderinfomMapperExt;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int countByExample(TraOrderinfomExample example) {
        return traOrderinfomMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TraOrderinfomExample example) {
        int n = traOrderinfomMapper.deleteByExample(example);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public int deleteByPrimaryKey(Long orderid) {
        int n = traOrderinfomMapper.deleteByPrimaryKey(orderid);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public int insert(TraOrderinfom record) {
        int n = traOrderinfomMapper.insert(record);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public int insertSelective(TraOrderinfom record) {
        int n = traOrderinfomMapper.insertSelective(record);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public TraOrderinfom selectOneByExample(TraOrderinfomExample example) {
        return traOrderinfomMapper.selectOneByExample(example);
    }

    @Override
    public List<TraOrderinfom> selectByExample(TraOrderinfomExample example) {
        return traOrderinfomMapper.selectByExample(example);
    }

    @Override
    public TraOrderinfom selectByPrimaryKey(Long orderid) {
        return traOrderinfomMapper.selectByPrimaryKey(orderid);
    }

    @Override
    public int updateByExampleSelective(TraOrderinfom record, TraOrderinfomExample example) {
        int n = traOrderinfomMapper.updateByExampleSelective(record, example);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public int updateByExample(TraOrderinfom record, TraOrderinfomExample example) {
        int n = traOrderinfomMapper.updateByExample(record, example);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public int updateByPrimaryKeySelective(TraOrderinfom record) {
        int n = traOrderinfomMapper.updateByPrimaryKeySelective(record);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public int updateByPrimaryKey(TraOrderinfom record) {
        int n = traOrderinfomMapper.updateByPrimaryKey(record);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public int insertOrder(TraOrderinfom traOrderinfom) {
        int n = traOrderinfomMapperExt.insertOrder(traOrderinfom);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public Page<OrderResponse> getMyOrderList(OrderRequest req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.getMyOrderList(req, rowBounds);
    }

    @Override
    public int updateStatus(TraOrderinfom traOrderinfom) {
        int n = traOrderinfomMapperExt.updateStatus(traOrderinfom);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public TraOrderinfom selectWeiZhifuOne(String accno, BigDecimal price) {
        return traOrderinfomMapperExt.selectWeiZhifuOne(accno, price);
    }

    @Override
    public int insertIncarateOrder(TraOrderinfom traOrderinfom) {
        int n = traOrderinfomMapperExt.insertIncarateOrder(traOrderinfom);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public TraOrderinfom existOrderinfom(OrderRequest orderRequest) {
        return traOrderinfomMapperExt.existOrderinfom(orderRequest);
    }

    @Override
    public TraOrderinfom isNew(String accno) {
        return traOrderinfomMapperExt.isNew(accno);
    }

    @Override
    public List<TraOrderinfom> findChongZhiDaijiesuanByAccno(String accno) {
        return traOrderinfomMapperExt.findChongZhiDaijiesuanByAccno(accno);
    }

    @Override
    public List<TraOrderinfom> findOrderByAccnoAndStatus(OrderRequest yitixianRequest) {
        return traOrderinfomMapperExt.findOrderByAccnoAndStatus(yitixianRequest);
    }

    @Override
    public int insertXiaZhuOrder(TraOrderinfomReq traOrderinfom) {
        int n = traOrderinfomMapperExt.insertXiaZhuOrder(traOrderinfom);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public Page<ChessKaiYuanResponse> myMoreKaiyuanChessList(ChessRequest req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.myMoreKaiyuanChessList(req, rowBounds);
    }

    @Override
    public ChessKaiYuanStatisticsResponse getStatistics(ChessRequest req) {
        return traOrderinfomMapperExt.getStatistics(req);
    }

    @Override
    public List<ChessOrder> getQiPaiStatisticsByPerson(ChessRequest chessRequest) {
        return traOrderinfomMapperExt.getQiPaiStatisticsByPerson(chessRequest);
    }

    @Override
    public CaiStatisticsResponse getCaiZhongStatistics(CaiRequest req) {
        return traOrderinfomMapperExt.getCaiZhongStatistics(req);
    }

    @Override
    public Page<CaiBettingRecordDO> getMyMoreCaiList(CaiRequest req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.getMyMoreCaiList(req, rowBounds);
    }

    @Override
    public GreatPerson getShenSuanZi() {
        return traOrderinfomMapperExt.getShenSuanZi();
    }

    @Override
    public GreatPerson getCaiqizhubo() {
        return traOrderinfomMapperExt.getCaiqizhubo();
    }

    @Override
    public GreatPerson getCaifuzi() {
        return traOrderinfomMapperExt.getCaifuzi();
    }

    @Override
    public TraOrderinfom findByOrderno(String orderno) {
        return traOrderinfomMapperExt.findByOrderno(orderno);
    }

    @Override
    public int doUpdateRechargeOrder(TraOrderinfom traOrderinfom) {
        int n = traOrderinfomMapperExt.doUpdateRechargeOrder(traOrderinfom);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public Page<EntryOrderResponse> orderList(EntryOrderReq req, RowBounds rowBounds) {
        if(req.getStartDate() != null && req.getStartDate() != ""){
            req.setStartDate(req.getStartDate()+" 00:00:00");
        }
        if(req.getEndDate() != null && req.getEndDate() != ""){
            req.setEndDate(req.getEndDate()+" 23:59:59");
        }
        return traOrderinfomMapperExt.orderList(req, rowBounds);
    }


    @Override
    @DS("slave")
    public Page<EntryOrderResponse> appOrderList(EntryOrderReq req, RowBounds rowBounds) {
        if(req.getStartDate() != null && req.getStartDate() != ""){
            req.setStartDate(req.getStartDate()+" 00:00:00");
        }
        if(req.getEndDate() != null && req.getEndDate() != ""){
            req.setEndDate(req.getEndDate()+" 23:59:59");
        }
        return traOrderinfomMapperExt.appOrderList(req, rowBounds);
    }

    @Override
    public List<EntryOrderExcelResponse> orderList(EntryOrderReq req) {
        return traOrderinfomMapperExt.exportOrderList(req);
    }

    @Override
    public EntryOrderTotalResponse orderListTotal(EntryOrderReq req) {
        return traOrderinfomMapperExt.orderListTotal(req);
    }

    @Override
    public Page<IncarnateOrderResponse> incarnateOrderList(IncarnateOrderReq req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.incarnateOrderList(req, rowBounds);
    }

    @Override
    public Page<IncarnateOrderResponse> incarnateOrderListBySuper(IncarnateOrderReq req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.incarnateOrderListBySuper(req, rowBounds);
    }


    @Override
    @DS("slave")
    public Page<IncarnateOrderResponse> appIncarnateOrderListBySuper(IncarnateOrderReq req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.appIncarnateOrderListBySuper(req, rowBounds);
    }


    @Override
    public Page<AgentTakeCashOrder> agentTakeCashOrderList(AgentOrderReq req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.agentTakeCashOrderList(req, rowBounds);
    }

    @Override
    public BigDecimal incarnateOrderActualTotal(IncarnateOrderReq req) {
        return traOrderinfomMapperExt.incarnateOrderActualTotal(req);
    }

    @Override
    public int doUpdateIncarnateHandleOrder(TraOrderinfom traOrderinfom) {
        int n = traOrderinfomMapperExt.doUpdateIncarnateHandleOrder(traOrderinfom);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public int doUpdateIncarnateCancelSureOrder(TraOrderinfom traOrderinfom) {
        int n = traOrderinfomMapperExt.doUpdateIncarnateCancelSureOrder(traOrderinfom);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public int updateIncarnateConfirmOrder(TraOrderinfom traOrderinfom) {
        int n = traOrderinfomMapperExt.updateIncarnateConfirmOrder(traOrderinfom);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }


    @Override
    public int udunUpdateIncarnateConfirmOrder(TraOrderinfom traOrderinfom) {
        int n = traOrderinfomMapperExt.undUpdateIncarnateConfirmOrder(traOrderinfom);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public TraOrderinfom findByFamilyAccno(String accno) {
        return traOrderinfomMapperExt.findByFamilyAccno(accno);
    }

    @Override
    public int insertFamilyIncarateOrder(TraOrderinfom traOrderinfom) {
        int n = traOrderinfomMapperExt.insertFamilyIncarateOrder(traOrderinfom);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public TraOrderinfom findLastOrderByFamilyAccno(FamilyIncarnateRequest req) {
        return traOrderinfomMapperExt.findLastOrderByFamilyAccno(req);
    }

    @Override
    public void doJiesuanOrder(List<Long> list) {
        traOrderinfomMapperExt.doJiesuanOrder(list);
    }

    @Override
    public int insertCommonOrder(TraOrderinfom traOrderinfom) {
        int n = traOrderinfomMapperExt.insertCommonOrder(traOrderinfom);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public Page<ChessOrderResponse> getShangfenOrderByPerson(ChessRequest req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.getShangfenOrderByPerson(req, rowBounds);
    }

    @Override
    public List<ChessOrderDO> getQiPaiXiaZhuByPerson(ChessRequest req) {
        return traOrderinfomMapperExt.getQiPaiXiaZhuByPerson(req);
    }

    @Override
    public Page<ChessOrderStatisticsResponse> getChessStatisticsQipai(ChessRequest req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.getChessStatisticsQipai(req, rowBounds);
    }


    @Override
    public Page<EntryOrderResponse> onlineOrderList(EntryOrderReq req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.onlineOrderList(req, rowBounds);
    }
    @Override
    public Page<AgentRechargeOrder> agentRechargeOrderList(AgentOrderReq req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.agentRechargeOrderList(req, rowBounds);
    }


    @Override
    public StatisticsOnLineOrderResponse getStatisticsOnLineOrder(EntryOrderReq req) {
        return traOrderinfomMapperExt.getStatisticsOnLineOrder(req);
    }

    @Override
    public List<TraOrderinfom> getOrderByRoom(FamilyStatisticsRequest f) {
        return traOrderinfomMapperExt.getOrderByRoom(f);
    }

    @Override
    public void doUpdateJieSuanOrderById(FamilyIncarnateRequest familyIncarnateRequest) {
        traOrderinfomMapperExt.doUpdateJieSuanOrderById(familyIncarnateRequest);
        RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);

    }

    @Override
    public Page<CaiBettingRecordDO> getCaiList(BettingRecordRequest req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.getCaiList(req, rowBounds);
    }

    @Override
    public TraOrderinfom findExistByAccno(String accno, int ordertype, Long paysetid) {
        return traOrderinfomMapperExt.findExistByAccno(accno, ordertype, paysetid);
    }

    @Override
    public TraOrderinfom findExistByAccnoRengong(String accno, Long paysetid) {
        return traOrderinfomMapperExt.findExistByAccnoRengong(accno, paysetid);
    }

    @Override
    public int doUpdateFamilyRechargeOrder(TraOrderinfom traOrderinfom) {
        int n = traOrderinfomMapperExt.doUpdateFamilyRechargeOrder(traOrderinfom);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public Page<CaiBettingRecordDO> getKSBettingRecordList(BettingRecordRequest req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.getKSBettingRecordList(req, rowBounds);
    }

    @Override
    public Page<CaiBettingRecordDO> getSSCBettingRecordList(BettingRecordRequest req, RowBounds rowBounds) {
        return traOrderinfomMapperExt.getSSCBettingRecordList(req, rowBounds);
    }

    @Override
    public List<UserResp> getAnchorTopTen(UserReq userReq) {
        return traOrderinfomMapperExt.getAnchorTopTen(userReq);
    }

    @Override
    public BigDecimal countRecgoldnum(String accno) {
        return traOrderinfomMapperExt.countRecgoldnum(accno);
    }

    @Override
    public Page<FamilyIncarnateResponse> getOrderList(FamilyIncarnateRequest req, RowBounds toRowBounds) {
        String key = RedisKeys.LIVE_FAMILY_WITHDRAWAL_MANAGEMENT + JSONObject.toJSONString(req) + JSONObject.toJSONString(toRowBounds);
        if (redisTemplate.hasKey(key)) {
            return (Page<FamilyIncarnateResponse>) redisTemplate.opsForValue().get(key);
        } else {
            Page<FamilyIncarnateResponse> orderList = traOrderinfomMapperExt.getOrderList(req, toRowBounds);
            if (CollectionUtil.isNotEmpty(orderList)) {
                redisTemplate.opsForValue().set(key + "count", orderList.getTotal());
                redisTemplate.opsForValue().set(key, orderList);
            }
            return orderList;
        }
    }

    @Override
    public void updateOrderinfom(Long orderid, String status, String orderstatus) {
        traOrderinfomMapperExt.updateOrderinfom(orderid, status, orderstatus);
        RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
    }

    @Override
    public Long getApycId(Long orderid) {
        return traOrderinfomMapperExt.getApycId(orderid);
    }

    @Override
    public Double getSumamt(Long roomid) {
        return traOrderinfomMapperExt.getSumamt(roomid);
    }

    @Override
    public String findNoteById(Long orderid) {
        return traOrderinfomMapperExt.findNoteById(orderid);
    }

    /**
     * 统计房间在线会员在当前本次直播中送出的礼物金额
     *
     * @param param
     * @return
     */
    @Override
    public double statisticsRoomLineMemGift(Map param) {
        Double sumamt = traOrderinfomMapperExt.statisticsRoomLineMemGift(param);
        if (null == sumamt) {
            return 0.00;
        } else {
            return sumamt.doubleValue();
        }
    }

    @Override
    public long countTraOrderinfom() {
        return traOrderinfomMapperExt.countTraOrderinfom();
    }

    /**
     * 获取代理的昨天的用户充值数
     */
    @Override
    public List<TraOrderinfomDTO> getYesterdayAgentOrder(String refaccno, String startDate, String endDate) {
        return traOrderinfomMapperExt.getYesterdayAgentOrder(refaccno, startDate, endDate);
    }

    /**
     * 统计房间最后一次收入(最近的一段直播收入)
     */
    @Override
    public double statisticsRoomLastInCome(Map param) {
        Double sumamt = traOrderinfomMapperExt.statisticsRoomLastInCome(param);
        if (null == sumamt) {
            return 0.00;
        } else {
            return sumamt.doubleValue();
        }
    }

    /**
     * 统计房间总收入(所有直播段收入)
     */
    @Override
    public double statisticsRoomAllInCome(Long roomId) {
        Double sumamt = traOrderinfomMapperExt.statisticsRoomAllInCome(roomId);
        if (null == sumamt) {
            return 0.00;
        } else {
            return sumamt.doubleValue();
        }
    }

    @Override
    public TraOrderinfom getYesterdayWithdraw(String refaccno, String startDate, String endDate) {
        return traOrderinfomMapperExt.getYesterdayWithdraw(refaccno, startDate, endDate);
    }

    @Override
    public Integer offlinePayNum(String accno) {

        TraOrderinfomExample example = new TraOrderinfomExample();
        example.createCriteria().andAccnoEqualTo(accno)
                .andOrdertypeEqualTo(2)
                .andOrderstatusEqualTo(Constants.ORDER_ORD08)
                .andAccountstatusEqualTo(Constants.ORDER_ACC04);
        return traOrderinfomMapper.countByExample(example);
    }

    @Override
    public Integer updatePaycode(String orderno, String paycode) {
        int n = traOrderinfomMapperExt.updatePaycode(orderno, paycode);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public Integer updateOrderNotSuccess(TraOrderinfom traOrderinfom) {
        int n = traOrderinfomMapperExt.updateOrderNotSuccess(traOrderinfom);
        if (n > 0) {
            RedisBusinessUtil.deleteFuzzyMatchCache(RedisKeys.LIVE_MANAGE_ONLINE_ORDER_LIST_PAGE);
        }
        return n;
    }

    @Override
    public List<EntryOnLineOrderExcelResponse> onlineOrderList(EntryOrderReq req) {
        return traOrderinfomMapperExt.onlineOrderListExecl(req);
    }


    @Override
    public DepositStatisticsDO selectSumPeople(String startTime, String endTime, Integer ordertype1) {
        DepositStatisticsDO sum = traOrderinfomMapperExt.selectSumPeople(startTime, endTime, ordertype1);
        return sum;
    }

    @Override
    public List<DepositStatisticsDO> selectNumPeopleCompany(String startTime, String endTime, Integer ordertype2) {

        List<DepositStatisticsDO> depositStatisticsDOlist = traOrderinfomMapperExt.selectNumPeopleCompany(startTime, endTime, ordertype2);

        return depositStatisticsDOlist;
    }

    @Override
    public List<EntryIncarnateOrderExcelResponse> incarnateOrderListByExcel(IncarnateOrderReq req) {
        return traOrderinfomMapperExt.incarnateOrderListExcel(req);
    }

    @Override
    public List<ThirdProviderDO> getDepositReportThird(String startTime, String endTime, List<Long> ids) {
        return traOrderinfomMapperExt.getDepositReportThird(startTime, endTime, ids);

    }

    @Override
    public List<ThirdProviderDO> getDepositReportFailThird(String startTime, String endTime, List<Long> ids) {
        return traOrderinfomMapperExt.getDepositReportFailThird(startTime, endTime, ids);


    }

    @Override
    public DepositStatisticsDO getPayFor(String startTime, String endTime) {
        return traOrderinfomMapperExt.getPayFor(startTime, endTime);
    }

    @Override
    public OrderTotalDTO getFamilyOrderTotal(String accno, String startTime, String endTime) {
        Double gift = traOrderinfomMapperExt.getFamilyOrderGiftTotal(accno, startTime, endTime);
        Double bet = traOrderinfomMapperExt.getFamilyOrderBetTotal(accno, startTime, endTime);
        OrderTotalDTO tatal = new OrderTotalDTO();
        if (null != gift) {
            tatal.setFamilyGiftTotal(new BigDecimal(String.valueOf(gift)));
        } else {
            tatal.setFamilyGiftTotal(new BigDecimal("0"));
        }
        if (null != bet) {
            tatal.setFamilyBetTotal(new BigDecimal(String.valueOf(bet)));
        } else {
            tatal.setFamilyBetTotal(new BigDecimal("0"));
        }
        return tatal;
    }

    @Override
    public List<ThirdProviderDO> getallPeople(String startTime, String endTime, List<Long> ids) {
        return traOrderinfomMapperExt.getallPeople(startTime, endTime, ids);
    }

    @Override
    public List<TraOrderinfom> doDayExportOrder(String orderStatus, Integer orderType) {
        return traOrderinfomMapperExt.doDayExportOrder(orderStatus, orderType);
    }

    @Override
    public void batchUpdateOrder(List<TraOrderinfom> orderList) {
        traOrderinfomMapperExt.batchUpdateOrder(orderList);
    }

}
