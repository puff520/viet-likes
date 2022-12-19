package com.likes.modules.admin.agent.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.constant.Constants;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.*;
import com.likes.common.model.dto.member.CountWithdrawalDO;
import com.likes.common.model.dto.member.MemAgentDetailResp;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.dto.order.TraOrderinfomDTO;
import com.likes.common.model.request.*;
import com.likes.common.model.response.AgentAdminResponse;
import com.likes.common.model.response.TeamAdminResponse;
import com.likes.common.model.response.TeamResponse;
import com.likes.common.model.vo.member.ManageAgentVO;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.*;
import com.likes.common.mybatis.mapperext.tra.TraAgentclearingMapperExt;
import com.likes.common.mybatis.mapperext.tra.TraOrderinfomMapperExt;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemBaseinfoWriteService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.service.member.MemRelationshipService;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.DateUtils;
import com.likes.common.util.StringUtils;
import com.likes.modules.admin.agent.service.AgentReportService;
import com.github.pagehelper.Page;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

/**
 * @author 阿布
 */

@Service
public class AgentReportServiceImpl extends BaseServiceImpl implements AgentReportService {
    private static final Logger logger = LoggerFactory.getLogger(AgentReportServiceImpl.class);

    @Resource
    private EveryDayReportMapper everyDayReportMapper;
    @Resource
    private TraAgentclearingMapper traAgentclearingMapper;
    @Resource
    private TraAgentclearingMapperExt traAgentclearingMapperExt;
    @Resource
    private MemRelationshipService memRelationshipService;
    @Resource
    private TraOrderinfomService traOrderinfomMapperService;
    @Autowired
    @Lazy
    private AgentReportService agentReportService;
    @Resource
    private MemBaseinfoWriteService memBaseinfoWriteService;
    @Resource
    private TraOrderinfomMapperExt traOrderinfomMapperExt;
    @Autowired
    private RedissonClient redissonClient;
    @Resource
    private AgentMapper agentMapper;
    @Resource
    private AgentAdminMapper agentAdminMapper;
    @Resource
    private MemLoginService memLoginService;

    @Override
    @DS("slave")
    public PageResult getAgentList(SysAgentinfoReq req, PageBounds page) throws BusinessException {
        Page<ManageAgentVO> pageData = this.traAgentclearingMapperExt.getAgentList(req, page.toRowBounds());
        for (ManageAgentVO mar : pageData) {
            addParam(mar);
        }
        return PageResult.getPageResult(page, pageData);
    }

    /**
     * 提现总金额
     */
    private void addParam(ManageAgentVO mar) {
        List<CountWithdrawalDO> data = traAgentclearingMapperExt.countWithdrawal(mar.getAccno());
        if (null != data && data.size() == 2) {
            BigDecimal pay = data.get(0).getSumrealamt();
            BigDecimal payUserTotal = data.get(0).getUserTotal();
            BigDecimal withdrawal = data.get(1).getSumrealamt();
            mar.setPayUserTotal(null == payUserTotal ? new BigDecimal("0") : payUserTotal);
            mar.setWithdrawalTotal(null == withdrawal ? new BigDecimal("0") : withdrawal);
            mar.setChargeamt(null == pay ? new BigDecimal("0") : pay);
        } else {
            mar.setPayUserTotal(new BigDecimal("0"));
            mar.setWithdrawalTotal(new BigDecimal("0"));
            mar.setChargeamt(new BigDecimal("0"));
        }
    }

    @Override
    public PageResult getDetail(SysAgentinfoReq req, PageBounds page) throws BusinessException {
        if (StringUtils.isEmpty(req.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "无效参数");
        }
        return PageResult.getPageResult(page, this.traAgentclearingMapperExt.getDetail(req, page.toRowBounds()));
    }

    @Override
    @DS("slave")
    public PageResult getNextList(SysAgentinfoReq req, PageBounds page) throws BusinessException {
        if (StringUtils.isEmpty(req.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "无效参数");
        }
        return PageResult.getPageResult(page, this.traAgentclearingMapperExt.getNextList(req, page.toRowBounds()));
    }

    @Override
    public PageResult getNextDetail(SysAgentinfoReq req, PageBounds page) throws BusinessException {
        if (StringUtils.isEmpty(req.getAccno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "无效参数");
        }
        return PageResult.getPageResult(page, this.traAgentclearingMapperExt.getNextDetail(req, page.toRowBounds()));
    }

    @Override
    @DS("slave")
    public PageResult teamTradingList(AgentDetailReq req, PageBounds page) throws BusinessException {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(req.getBeginTime())) {
            req.setBeginTime(req.getBeginTime() + " 00:00:00");
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(req.getEndTime())) {
            req.setEndTime(req.getEndTime() + " 23:59:59");
        }
        if (StringUtils.isNotBlank(req.getSubEmail()) && StringUtils.isBlank(req.getEmail())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "请先输入代理账号");
        }

        List<String> levelAllSubAcc = new LinkedList<>();
        List<String> level1SubAcc = new LinkedList<>();
        List<String> level2SubAcc = new LinkedList<>();
        List<String> level3SubAcc = new LinkedList<>();

        if (StringUtils.isNotBlank(req.getEmail())) {
            MemLogin memLogin = memLoginService.findByAccloginRegister(req.getEmail());
            if (ObjectUtil.isNull(memLogin)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "请输入正确的代理账号");
            }
            level1SubAcc = agentMapper.level1SubAccList(memLogin.getAccno());
            level2SubAcc = agentMapper.level2SubAccList(memLogin.getAccno());
            level3SubAcc = agentMapper.level3SubAccList(memLogin.getAccno());
            levelAllSubAcc.addAll(level1SubAcc);
            levelAllSubAcc.addAll(level2SubAcc);
            levelAllSubAcc.addAll(level3SubAcc);
        }
        if (CollectionUtil.isEmpty(levelAllSubAcc) && !StringUtils.isBlank(req.getEmail())) {
            return PageResult.getPageResult(page, new LinkedList());
        }
        Page<MemAgentDetailResp> pageResult = traAgentclearingMapperExt.agentDetailList(req, levelAllSubAcc, page.toRowBounds());
        List<MemAgentDetailResp> list = pageResult.getResult();
        for (MemAgentDetailResp item : list) {
            if (level1SubAcc.contains(item.getAccno())) {
                item.setAgentLevel("一级代理");
            }
            if (level2SubAcc.contains(item.getAccno())) {
                item.setAgentLevel("二级代理");
            }
            if (level3SubAcc.contains(item.getAccno())) {
                item.setAgentLevel("三级代理");
            }
            if (!StringUtils.isBlank(item.getBuyLevel())) {
                item.setBuyLevel("Vip" + item.getBuyLevel());
            }
        }
        return PageResult.getPageResult(page, pageResult);
    }

//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void doDayExport() throws BusinessException {
//        if (this.traAgentclearingMapperExt.doInsertDayAgent() > 0) {
//            this.traAgentclearingMapperExt.doUpdateDayAgent();
//            // 代理结算
//            this.traAgentclearingMapperExt.doUpdateDayClearing();
//            // 费用打款
//            List<TraAgentclearing> list = this.traAgentclearingMapperExt.getPayList();
//            for (TraAgentclearing ta : list) {
//                // 订单
//                TraOrderinfom to = new TraOrderinfom();
//                to.setAccno(ta.getAccno());
//                to.setOrdertype(Constants.ORDERTYPE11);
//                to.setOrderdate(new Date());
//                BigDecimal tradeOffAmount = getTradeOffAmount(ta.getReverseamt());
//                to.setOldamt(tradeOffAmount);
//                to.setSumamt(tradeOffAmount);
//                to.setRealamt(tradeOffAmount);
//                to.setOrderstatus(Constants.ORDER_ORD08);
//                // 未结算
//                to.setAccountstatus(Constants.ORDER_ACC04);
//                to.setOrdernote(Constants.AGENT_DES);
//                to.setCreateUser(ta.getAccno());
//                to.setUpdateUser(ta.getAccno());
//                to.setIsDelete(false);
//
//                // 变动明细
//                MemGoldchange mg = new MemGoldchange();
//                mg.setRefid(to.getOrderid());
//                mg.setAccno(ta.getAccno());
//                mg.setChangetype(GoldchangeEnum.AGENCY_SETTLE.getValue());
//                BigDecimal tradeOffAmount1 = getTradeOffAmount(ta.getReverseamt());
//                mg.setQuantity(tradeOffAmount1);
//                mg.setAmount(tradeOffAmount1);
//                mg.setQuantity(tradeOffAmount1);
//                mg.setOpnote(Constants.AGENT_DES);
//                mg.setCreateUser(ta.getAccno());
//                mg.setUpdateUser(ta.getAccno());
//                mg.setIsDelete(false);
//                this.traAgentclearingMapperExt.insertPayAgent(to);
//                this.memGoldchangeService.insertAuto(mg);
//                // 更新用户B币
//                this.memBaseinfoService.updatePayAgent(ta);
//            }
//        }
//    }

    /**
     * 代理-新方法
     */
    @Override
    public String doDayExportNew(String date, String type) throws BusinessException {
        if (StringUtils.isEmpty(date)) {
            logger.info("代理定时任务，指定date错误");
            return "代理定时任务，指定date错误";
        }
        RReadWriteLock lock = redissonClient.getReadWriteLock("AgentReportTaskLock");
        boolean bool = false;
        try {
            // 写锁（等待时间0s，超时时间5分钟[自动解锁]，单位：秒）【设定超时时间，超时后自动释放锁，防止死锁】
            bool = lock.writeLock().tryLock(0, 300, TimeUnit.SECONDS);
            // 判断是否获取到锁
            if (bool) {
                Date time = DateUtils.parseDate(date);
                String yesterdayStartDate = DateUtils.formatDate(time, "yyyy-MM-dd 00:00:00");
                String yesterdayEndDate = DateUtils.formatDate(time, "yyyy-MM-dd 23:59:59");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(time);
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
                Date today = calendar.getTime();
                String createTime = DateUtils.formatDate(today, "yyyy-MM-dd 09:00:00");
                Date createDate = DateUtils.parseDate(createTime);

                process(yesterdayStartDate, yesterdayEndDate, createDate, type);
                return Constants.SUCCESS_MSG;
            } else {
                logger.error("代理定时任务没拿到锁,date:{},type:{}", date, type);
                return "已有定时任务在运行";
            }
        } catch (Exception e) {
            logger.error("代理定时任务,doDayExportNew,出错,date:{},type:{}", date, type, e);
            throw new BusinessException("代理定时任务出错", e.getCause());
        } finally {
            // 释放锁
            if (bool) {
                lock.writeLock().unlock();
                logger.info("doDayExportNew 代理定时任务释放锁,date:{},type:{}", date, type);
            } else {
                logger.info("doDayExportNew 代理定时任不需要释放锁,date:{},type:{}", date, type);
            }
        }
    }

    /**
     * 统计代理的总额,并插入代理表
     * 更新代理id
     * 代理统计
     * 费用结算打款
     */
    private void process(String yesterdayStartDate, String yesterdayEndDate, Date createDate, String type) {
        //统计代理的总额,并插入代理表
        doInsertDayAgent(yesterdayStartDate, yesterdayEndDate, createDate, type);
        //更新代理id
        doUpdateDayAgent();
        // 代理统计
        doUpdateDayClearing();
        //费用结算打款
        doSettlement(createDate, type);
    }

    /**
     * 第一步统计代理的总额,并插入代理表
     */
    private void doInsertDayAgent(String yesterdayStartDate, String yesterdayEndDate, Date createDate, String type) {
        List<MemRelationship> list = memRelationshipService.getRefaccnos();
        if (CollectionUtil.isEmpty(list)) {
            logger.info("代理定时任务，第一步，无代理数据");
            return;
        }
        logger.info("代理定时任务，第一步,统计时间:{}~{},代理个数:{}", yesterdayStartDate, yesterdayEndDate, list.size());
        for (MemRelationship mrs : list) {
            try {
                String refaccno = mrs.getRefaccno();//代理accno
                doTraAgentclearing(refaccno, yesterdayStartDate, yesterdayEndDate, createDate, type);
            } catch (Exception e) {
                logger.error("代理定时任务,第二步执行失败", e);
            }
        }
    }

    /**
     * 插入TraAgentclearing表，并修改TraOrderinfom表Cleanid
     */
    private void doTraAgentclearing(String refaccno, String yesterdayStartDate, String yesterdayEndDate, Date createDate, String type) {
        //查找昨日代理所有数据
        if (countRefaccno(refaccno, createDate) > 0) {
            logger.info("代理定时任务,第二步，代理:{},{}，已经统计过，不能重复统计", refaccno, createDate);
            return;
        }
        List<TraOrderinfomDTO> orderInfoList = traOrderinfomMapperService.getYesterdayAgentOrder(refaccno, yesterdayStartDate, yesterdayEndDate);
//        if (CollectionUtils.isEmpty(orderInfoList)) {
//            logger.info("代理定时任务,第二步，代理:{},{}，无充值，停止计算", refaccno, createDate);
//            return;
//        }
        orderInfoList = orderInfoList == null ? new ArrayList<>() : orderInfoList;
        TraOrderinfom withdraw = traOrderinfomMapperService.getYesterdayWithdraw(refaccno, yesterdayStartDate, yesterdayEndDate);
        Integer newUsers = memRelationshipService.countChild(refaccno, yesterdayStartDate, yesterdayEndDate);
        agentReportService.insertTraAgentclearing(orderInfoList, withdraw.getRealamt(), newUsers, refaccno, createDate, type, yesterdayStartDate);
        logger.info("代理定时任务,第二步，代理:{},{}，插入代理结算表ok", refaccno, createDate);
    }

    /**
     * 更新代理id
     */
    private void doUpdateDayAgent() {
        int n = this.traAgentclearingMapperExt.doUpdateDayAgent();
        logger.info("代理定时任务,第三步，更新代理id完成,updateNum:{}", n);
    }

    /**
     * 代理统计
     */
    private void doUpdateDayClearing() {
        int n = this.traAgentclearingMapperExt.doUpdateDayClearing();
        logger.info("代理定时任务,第四步，代理统计完成,updateNum:{}", n);
    }

    /**
     * 费用结算打款
     */
    private void doSettlement(Date createDate, String type) {
        List<TraAgentclearing> list = this.traAgentclearingMapperExt.getPayList(createDate);
        if (CollectionUtil.isEmpty(list)) {
            logger.info("代理定时任务,第五步，费用结算完成,无打款数据");
            return;
        }
        for (TraAgentclearing ta : list) {
            agentReportService.insertDayExportNew(ta, type);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertTraAgentclearing(List<TraOrderinfomDTO> orderInfoList, BigDecimal withdraw, Integer newUsers, String refaccno, Date createDate, String type, String yesterdayStartDate) throws BusinessException {
        //插入代理数据
        TraAgentclearing traAgentclearing = new TraAgentclearing();
        traAgentclearing.setAccno(refaccno);
        traAgentclearing.setCleantype("day");
        traAgentclearing.setReverseamt(new BigDecimal("0"));
        traAgentclearing.setCreateUser("admin");
        traAgentclearing.setRefids(type + "1插入订单ok;2修改cleanid状态ok;");
        traAgentclearing.setCreateTime(createDate);
        traAgentclearing.setDailyWithdrawal(null == withdraw ? new BigDecimal("0") : withdraw);
        traAgentclearing.setDailyNewUsers(null == newUsers ? Constants.DEFAULT_INTEGER : newUsers);
        //计算金额
        countRealamt(orderInfoList, yesterdayStartDate, traAgentclearing);
        traAgentclearingMapper.insertSelective(traAgentclearing);

        if (orderInfoList.size() > 0) {
            //修改订单表clearid
            List<Long> idList = new ArrayList<Long>();
            for (TraOrderinfom order : orderInfoList) {
                idList.add(order.getOrderid());
            }

            TraOrderinfom newInfo = new TraOrderinfom();
            newInfo.setCleanid(traAgentclearing.getCleanid());
            TraOrderinfomExample example = new TraOrderinfomExample();
            TraOrderinfomExample.Criteria criteria = example.createCriteria();
            criteria.andOrderidIn(idList);
            traOrderinfomMapperService.updateByExampleSelective(newInfo, example);
        }
    }

    /**
     * 查询代理是否已经处理过
     */
    private int countRefaccno(String refaccno, Date createDate) {
        TraAgentclearingExample example = new TraAgentclearingExample();
        TraAgentclearingExample.Criteria criteria = example.createCriteria();
        criteria.andAccnoEqualTo(refaccno);
        criteria.andCreateTimeEqualTo(createDate);
        criteria.andIsDeleteEqualTo(false);
        return traAgentclearingMapper.countByExample(example);
    }

    /**
     * 统计金额和当天注册首充人数
     */
    private void countRealamt(List<TraOrderinfomDTO> orderInfoList, String yesterdayStartDate, TraAgentclearing tac) {
        BigDecimal chargeamt = new BigDecimal("0.00");
        Map<String, Object> dailyPayUsersMap = new HashMap<String, Object>();
        BigDecimal dailyPayTotal = new BigDecimal("0.00");
        for (TraOrderinfomDTO order : orderInfoList) {
            if (null != order && null != order.getRealamt()) {
                chargeamt = chargeamt.add(order.getRealamt().abs());
            }
            String registDay = DateUtils.formatDate(order.getRegisterdate(), "yyyy-MM-dd 00:00:00");
            if (yesterdayStartDate.equals(registDay)) {
                if (dailyPayUsersMap.containsKey(order.getAccno())) {
                    //此用户是昨天注册，但不是第一次充值
                } else {
                    //此用户是昨天注册，且是第一次充值
                    dailyPayUsersMap.put(order.getAccno(), null);
                    dailyPayTotal = dailyPayTotal.add(order.getRealamt().abs());
                }
            }
        }
        int dailyPayUsers = dailyPayUsersMap.size();
        tac.setChargeamt(chargeamt.abs());
        tac.setDailyPayUsers(dailyPayUsers);
        tac.setDailyPayTotal(dailyPayTotal);
        logger.info("首充,数量:{},accno:{},all总金额:{}", dailyPayUsers, JSONObject.toJSONString(dailyPayUsersMap), chargeamt);
    }

    /**
     * 添加订单表，帐变表，修改余额
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertDayExportNew(TraAgentclearing ta, String type) throws BusinessException {
        // 订单
        TraOrderinfom to = new TraOrderinfom();
        to.setAccno(ta.getAccno());
        to.setOrdertype(Constants.ORDERTYPE11);
        to.setOrderdate(new Date());
        to.setOldamt(ta.getReverseamt());
        to.setSumamt(ta.getReverseamt());
        to.setRealamt(ta.getReverseamt());
        to.setOrderstatus(Constants.ORDER_ORD08);
        // 未结算
        to.setAccountstatus(Constants.ORDER_ACC04);
        to.setOrdernote(Constants.AGENT_DES);
        to.setCreateUser(ta.getAccno());
        to.setUpdateUser(ta.getAccno());
        to.setIsDelete(false);
        this.traAgentclearingMapperExt.insertPayAgent(to);
        logger.info("代理定时任务,第五步1，insertPayAgent,代理accno:{},Orderid:{}", ta.getAccno(), to.getOrderid());

        //更新帐变和余额
        updateAccountBalance(ta, to);
        logger.info("代理定时任务,第五步2，updateAccountBalance,代理accno:{},Cleanid:{}", ta.getAccno(), ta.getCleanid());

        TraAgentclearing tac = new TraAgentclearing();
        tac.setCleanid(ta.getCleanid());
        tac.setRefids(ta.getRefids() + "5已结算" + type);
        tac.setButtonnote(ta.getButtonnote() + ",已结算" + type);
        this.traAgentclearingMapper.updateByPrimaryKeySelective(tac);
        logger.info("代理定时任务,第五步3，完美收工,代理accno:{},Cleanid:{}", ta.getAccno(), ta.getCleanid());
    }

    @Override
    @DS("slave")
    public PageResult getUserWithdraw(SysAgentinfoReq req, PageBounds page) throws BusinessException {
        Page<TraOrderinfom> data = traOrderinfomMapperExt.getUserWithdraw(req, page.toRowBounds());
        return PageResult.getPageResult(page, data);
    }

    /**
     * 更新帐变和余额
     */
    private void updateAccountBalance(TraAgentclearing ta, TraOrderinfom to) {
        MemGoldchangeDO memGoldchange = new MemGoldchangeDO();
        memGoldchange.setRefid(to.getOrderid());
        memGoldchange.setRefaccno(ta.getAccno());
        memGoldchange.setAccno(ta.getAccno());
        memGoldchange.setChangetype(GoldchangeEnum.AGENCY_SETTLE.getValue());
        memGoldchange.setQuantity(getTradeOffAmount(ta.getReverseamt()));
        memGoldchange.setAmount(getTradeOffAmount(ta.getReverseamt()));
        memGoldchange.setCreateUser(ta.getAccno());
        memGoldchange.setUpdateUser(ta.getAccno());
        memGoldchange.setOpnote(Constants.AGENT_DES);
        memGoldchange.setNoWithdrawalAmount(getTradeOffAmount(ta.getReverseamt()));
        memBaseinfoWriteService.updateUserBalance(memGoldchange);
    }

    @Override
    @DS("slave")
    public PageResult everyDayList(AgentOrderReq req, PageBounds page) {
        List<AgentEveryData> todayList = null;
        String createDate = DateUtils.getDateString(new Date());
        if (org.apache.commons.lang3.StringUtils.isNotBlank(req.getBeginTime())) {
            req.setBeginTime(req.getBeginTime() + " 00:00:00");
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(req.getEndTime())) {
            req.setEndTime(req.getEndTime() + " 23:59:59");
        }
        if (req.getBeginTime().contains(createDate) || req.getEndTime().contains(createDate)) {
            todayList = everyDayReportMapper.everyDayList(createDate + " 00:00:00", createDate + " 23:59:59");
            page.setPageSize(page.getPageSize() - 1);
        }
        Page<AgentEveryData> pageData = everyDayReportMapper.everyDayReport(req.getBeginTime(), req.getEndTime(), page.toRowBounds());
        if (CollectionUtil.isNotEmpty(todayList)) {
            for (AgentEveryData initData : todayList) {
                initData.setCreateTime(new Date());
                initData.setTaskAmount(initData.getRw());
                initData.setRechargeAmount(initData.getRgcz().add(initData.getCz()));
                initData.setWithdrawAmount(initData.getTx());
                initData.setBuyVipAmount(initData.getVip());
                initData.setSubTaskBrokerage(initData.getTask1().add(initData.getTask2()).add(initData.getTask3()));
                Integer vipNum = agentAdminMapper.addVipNum(initData.getDat() + " 00:00:00", initData.getDat() + " 23:59:59");
                initData.setAddVipNum(vipNum);
                pageData.getResult().addAll(todayList);
            }
            page.setPageSize(page.getPageSize() + 1);
        }
        pageData.getResult().sort(Comparator.comparing(AgentEveryData::getCreateTime).reversed());
        return PageResult.getPageResult(page, pageData);
    }

    @Override
    @DS("slave")
    public PageResult teamReport(TeamRequest req, PageBounds page) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(req.getBeginTime())) {
            req.setBeginTime(req.getBeginTime() + " 00:00:00");
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(req.getEndTime())) {
            req.setEndTime(req.getEndTime() + " 23:59:59");
        }
        Map<String, Object> recMap = new HashMap<>();
        Map<String, Object> cashMap = new HashMap<>();
        List<Map<String, Object>> orderList = new LinkedList<>();
        Integer recMemNum = 0;
        Integer cashMemNum = 0;
        Page<TeamAdminResponse> teamPage = agentAdminMapper.teamAdminList(req, page.toRowBounds());
        for (TeamAdminResponse teamResponse : teamPage) {
            Integer teamVipList = agentAdminMapper.levelCount(teamResponse.getAccno(), req.getBeginTime(), req.getEndTime());
            teamResponse.setVipNum(teamVipList);
            List<String> todayList = agentMapper.todayAddSubNum(teamResponse.getAccno(), req.getBeginTime(), req.getEndTime());
            teamResponse.setAddMemNum(todayList.size());
            Integer addMemTaskNum = 0;
            if (CollectionUtil.isNotEmpty(todayList)) {
                addMemTaskNum = agentMapper.todayTaskNum(todayList, req.getBeginTime(), req.getEndTime());
                orderList = agentMapper.subTimeOrderList(todayList, req.getBeginTime(), req.getEndTime());
                recMemNum = agentAdminMapper.recMemNum(todayList, req.getBeginTime(), req.getEndTime());
                cashMemNum = agentAdminMapper.cashMemNum(todayList, req.getBeginTime(), req.getEndTime());
            }
            teamResponse.setAddTaskNum(addMemTaskNum);
            teamResponse.setAddMemNum(todayList.size());
            Integer theDayTaskNum = agentMapper.teamTaskNum(teamResponse.getAccno(), req.getBeginTime(), req.getEndTime());
            teamResponse.setTheDayTaskNum(theDayTaskNum);
            if (CollectionUtil.isNotEmpty(orderList)) {
                for (Map<String, Object> order : orderList) {
                    if (order.get("orderstatus").toString().equals("ord08")) {
                        recMap = order;

                    }
                    if (order.get("orderstatus").toString().equals("ord12")) {
                        cashMap = order;

                    }
                }
            }
            if (CollectionUtil.isNotEmpty(recMap)) {
                teamResponse.setRecNum(Integer.parseInt(recMap.get("operNum").toString()));
                teamResponse.setRecAmount(new BigDecimal(recMap.get("realamt").toString()));
            }
            if (CollectionUtil.isNotEmpty(cashMap)) {
                teamResponse.setCashNum(Integer.parseInt(cashMap.get("operNum").toString()));
                teamResponse.setCashAmount(new BigDecimal(cashMap.get("realamt").toString()));
            }
            teamResponse.setRecMemAmount(recMemNum);
            teamResponse.setCashMemNum(cashMemNum);
        }
        return PageResult.getPageResult(page, teamPage);
    }

    @Override
    @DS("slave")
    public PageResult agentList(AgentAdminRequest req, PageBounds page) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(req.getStartTime())) {
            req.setBeginTime(req.getStartTime() + " 00:00:00");
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(req.getEndTime())) {
            req.setEndTime(req.getEndTime() + " 23:59:59");
        }

        Page<AgentAdminResponse> pageResult;
        if (com.likes.common.util.StringUtils.isNotBlank(req.getEmail()) && com.likes.common.util.StringUtils.isNotBlank(req.getLookSub())) {
            MemLogin memLogin = memLoginService.findByAccloginRegister(req.getEmail());
            req.setAccno(memLogin.getAccno());
            req.setEmail(null);
            pageResult = agentAdminMapper.agentSubList(req, page.toRowBounds());
        } else {
            pageResult = agentAdminMapper.agentList(req, page.toRowBounds());
        }

        for (AgentAdminResponse agentAdminResponse : pageResult) {

            List<String> teamIds = agentMapper.levelAllSubAccList(agentAdminResponse.getAccno());

            Integer subNum = agentAdminMapper.subNum(agentAdminResponse.getAccno(), req.getBeginTime(), req.getEndTime());

            List<String> levelAccList = new ArrayList<>();
            List<String> levelOneAccList = agentMapper.subNum(Arrays.asList(agentAdminResponse.getAccno()), req.getBeginTime(), req.getEndTime());
            List<String> levelTwoAccList = new LinkedList<>();
            List<String> levelThreeAccList = new LinkedList<>();
            if (CollectionUtil.isNotEmpty(levelOneAccList)) {
                levelTwoAccList = agentMapper.subNum(levelOneAccList, req.getBeginTime(), req.getEndTime());
                if (CollectionUtil.isNotEmpty(levelTwoAccList)) {
                    levelThreeAccList = agentMapper.subNum(levelTwoAccList, req.getBeginTime(), req.getEndTime());
                }
            }
            levelAccList.addAll(levelOneAccList);
            levelAccList.addAll(levelTwoAccList);
            levelAccList.addAll(levelThreeAccList);

            BigDecimal subTaskBrokerageY = null;


            if (CollectionUtil.isNotEmpty(levelAccList)) {
                subTaskBrokerageY = agentAdminMapper.subTaskBrokerage(levelAccList, req.getBeginTime(), req.getEndTime());
            }
            if (CollectionUtil.isNotEmpty(teamIds)) {
                BigDecimal rechargeAmountY = getTradeOffAmount(null);
                BigDecimal withdrawAmountY = getTradeOffAmount(null);
                BigDecimal buyVipAmountY = getTradeOffAmount(null);
                BigDecimal taskAmountY = getTradeOffAmount(null);
                List<ChangeTypeData> dataList = agentAdminMapper.changeTypeAmount(teamIds, req.getBeginTime(), req.getEndTime());
                for (ChangeTypeData typeData : dataList) {
                    if (typeData.getChangeType().equals(1) || typeData.getChangeType().equals(55)) {
                        rechargeAmountY = rechargeAmountY.add(typeData.getAmount());
                    }
                    if (typeData.getChangeType().equals(12)) {
                        withdrawAmountY = withdrawAmountY.add(typeData.getAmount());
                    }
                    if (typeData.getChangeType().equals(100)) {
                        buyVipAmountY = buyVipAmountY.add(typeData.getAmount());
                    }
                    if (typeData.getChangeType().equals(200)) {
                        taskAmountY = taskAmountY.add(typeData.getAmount());
                    }
                }

                agentAdminResponse.setRechargeAmount(getTradeOffAmount(rechargeAmountY));
                agentAdminResponse.setWithdrawAmount(getTradeOffAmount(withdrawAmountY).abs());
                agentAdminResponse.setBuyVipAmount(getTradeOffAmount(buyVipAmountY));
                agentAdminResponse.setTaskAmount(getTradeOffAmount(taskAmountY));
            }

            BigDecimal subTaskBrokerage = getTradeOffAmount(subTaskBrokerageY);

            agentAdminResponse.setTeamNum(teamIds.size());
            agentAdminResponse.setDirectNum(subNum == null ? 0 : subNum);
            agentAdminResponse.setSubRebate(subTaskBrokerage);

        }
        return PageResult.getPageResult(page, pageResult);
    }


    @Override
    @DS("slave")
    public AgentData dataList(AgentOrderReq req, PageBounds page) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(req.getBeginTime())) {
            req.setBeginTime(req.getBeginTime() + " 00:00:00");
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(req.getEndTime())) {
            req.setEndTime(req.getEndTime() + " 23:59:59");

        }
        Map<String, Object> recMap = new HashMap<>();
        Map<String, Object> cashMap = new HashMap<>();
        AgentData agentData = new AgentData();
        List<String> addMemList = agentMapper.teamAddMem(null, req.getBeginTime(), req.getEndTime());
        Integer vipNum = agentMapper.levelCount(null, req.getBeginTime(), req.getEndTime());
        Integer taskNum = agentMapper.teamTaskNum(null, req.getBeginTime(), req.getEndTime());
        List<Map<String, Object>> orderList = agentMapper.subTimeOrderListByAccno(null, req.getBeginTime(), req.getEndTime());
        if (CollectionUtil.isNotEmpty(orderList)) {
            for (Map<String, Object> order : orderList) {
                if (order.get("orderstatus").toString().equals("ord08")) {
                    recMap = order;

                }
                if (order.get("orderstatus").toString().equals("ord12")) {
                    cashMap = order;

                }
            }
        }
        String dateStr = DateUtils.formatDate(new Date(), DateUtils.FORMAT_YYYY_MM_DD);
        Integer integer = agentMapper.memTodayTaskNum(null, dateStr);
        BigDecimal balance = agentMapper.memBalance(req.getBeginTime(), req.getEndTime());

        agentData.setMemTodayTaskNum(integer == null ? 0 : integer);
        agentData.setMemNum(addMemList.size());
        agentData.setVipNum(vipNum);
        agentData.setTaskNum(taskNum);
        agentData.setBalance(balance);

        if (CollectionUtil.isNotEmpty(recMap)) {
            agentData.setRecNum(Integer.parseInt(recMap.get("operNum").toString()));
            agentData.setRecAmount(recMap.get("realamt").toString());
        }
        if (CollectionUtil.isNotEmpty(cashMap)) {
            agentData.setCashNum(Integer.parseInt(cashMap.get("operNum").toString()));
            agentData.setCashAmount(cashMap.get("realamt").toString());
        }
        return agentData;
    }

    @Override
    public PageResult agentLineList(AgentLineReq req, PageBounds page) {
        if (StringUtils.isNotBlank(req.getBeginTime())) {
            req.setBeginTime(req.getBeginTime() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndTime())) {
            req.setEndTime(req.getEndTime() + " 23:59:59");

        }
        Page<AgentLineData> lineData = agentMapper.agentLineList(req.getBeginTime(), req.getEndTime(), page.toRowBounds());
        lineData.getResult().forEach(item -> {
            item.setDifference(item.getRechargeAmount().subtract(item.getWithdrawAmount()));
        });
        lineData.getResult().sort(Comparator.comparing(AgentLineData::getDifference).reversed());
        return PageResult.getPageResult(page, lineData);
    }
}
