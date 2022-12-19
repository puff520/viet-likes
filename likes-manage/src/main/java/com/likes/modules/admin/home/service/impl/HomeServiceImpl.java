package com.likes.modules.admin.home.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.likes.common.constant.Constants;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.EchartsyAxisDO;
import com.likes.common.model.dto.NameValueDO;
import com.likes.common.model.dto.report.ActivityAllDataDO;
import com.likes.common.model.dto.report.ActivityUserDataDO;
import com.likes.common.model.dto.report.ConsumptionAllDataDO;
import com.likes.common.model.dto.report.GameUserDataDO;
import com.likes.common.model.dto.report.GiftUserDataDO;
import com.likes.common.model.dto.report.GodPlanRewardDataDO;
import com.likes.common.model.dto.report.HomeDataDO;
import com.likes.common.model.dto.report.LotteryAllDataDO;
import com.likes.common.model.dto.report.LotteryBetDataDO;
import com.likes.common.model.dto.report.LotteryDataDO;
import com.likes.common.model.dto.report.PaymentAllDataDO;
import com.likes.common.model.dto.report.PaymentUserDataDO;
import com.likes.common.model.dto.report.ProfitAllDataDO;
import com.likes.common.model.dto.report.RechargeAllDataDO;
import com.likes.common.model.dto.report.RechargeUserDataDO;
import com.likes.common.mybatis.entity.CirclePostExample;
import com.likes.common.mybatis.mapper.CirclePostMapper;
import com.likes.common.mybatis.mapperext.member.MemBaseinfoMapperExt;
import com.likes.common.mybatis.mapperext.member.MemGoldchangeMapperExt;
import com.likes.common.mybatis.mapperext.member.MemLoginMapperExt;
import com.likes.common.mybatis.mapperext.order.AeBetOrderMapperExt;
import com.likes.common.mybatis.mapperext.order.AgBetOrderMapperExt;
import com.likes.common.mybatis.mapperext.order.AgFishBetOrderMapperExt;
import com.likes.common.mybatis.mapperext.order.DbBetOrderMapperExt;
import com.likes.common.mybatis.mapperext.order.EsBetOrderMapperExt;
import com.likes.common.mybatis.mapperext.order.KyBetOrderMapperExt;
import com.likes.common.mybatis.mapperext.order.MgBetOrderMapperExt;
import com.likes.common.mybatis.mapperext.order.OrderBetRecordMapperExt;
import com.likes.common.mybatis.mapperext.tra.TraOrderinfomMapperExt;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.util.DateUtils;
import com.likes.common.util.MutiFutureTask;
import com.likes.common.util.NumUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.home.service.HomeService;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {

    private final static Logger logger = LogManager.getLogger(HomeServiceImpl.class);

    @Resource
    private MemLoginService memLoginService;

    @Resource
    private CirclePostMapper circlePostMapper;
    @Resource
    private MemLoginMapperExt memLoginMapperExt;
    @Resource
    private TraOrderinfomMapperExt traOrderinfomMapperExt;

    @Resource
    private OrderBetRecordMapperExt orderBetRecordMapperExt;

    @Resource
    private AeBetOrderMapperExt aeBetOrderMapperExt;
    @Resource
    private AgBetOrderMapperExt agBetOrderMapperExt;
    @Resource
    private AgFishBetOrderMapperExt agFishBetOrderMapperExt;
    @Resource
    private DbBetOrderMapperExt dbBetOrderMapperExt;
    @Resource
    private EsBetOrderMapperExt esBetOrderMapperExt;
    @Resource
    private KyBetOrderMapperExt kyBetOrderMapperExt;
    @Resource
    private MgBetOrderMapperExt mgBetOrderMapperExt;
    @Resource
    private MemGoldchangeMapperExt memGoldchangeMapperExt;
    @Resource
    private MemBaseinfoMapperExt memBaseinfoMapperExt;

    @Override
    public Map<String, Object> getPeoples() {
        Map<String, Object> dataMap = new HashMap<String, Object>();

        // 自然周
        Date weekfirstdaydate = DateUtils.getFirstDayOfWeek(new Date());
        Date weeklastddaydate = DateUtils.getLastDayOfWeek(new Date());
        String week_firstday = DateUtils.formatDate(weekfirstdaydate, "yyyyMMdd");
        String week_lastdday = DateUtils.formatDate(weeklastddaydate, "yyyyMMdd");
        String week_firstday2 = DateUtils.formatDate(weekfirstdaydate, "yyyy-MM-dd 00:00:00");
        String week_lastdday2 = DateUtils.formatDate(weeklastddaydate, "yyyy-MM-dd 23:59:59");

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("starttime", week_firstday);
        param.put("endtime", week_lastdday);
        // 注册会员数
        Integer allregisterednum = RedisBusinessUtil.getAllRegisteredNumCache();
        if (null == allregisterednum) {
            allregisterednum = memLoginService.getAllRegisteredNum();
        }
        dataMap.put("allregisterednum", allregisterednum);
        // 这周新增注册会员数
        Integer thisweekregisterednum = memLoginService.getThisWeekRegisteredNum(param);
        dataMap.put("thisweekregisterednum", thisweekregisterednum);



        // 贴文数(改为圈子总数)
        CirclePostExample circlePostExample = new CirclePostExample();
        CirclePostExample.Criteria totalCriteria = circlePostExample.createCriteria();
        totalCriteria.andChannelEqualTo(Constants.DEFAULT_INTEGER);
        Integer allartclenum = circlePostMapper.countByExample(circlePostExample);
        dataMap.put("allartclenum", allartclenum);
        // 这周
        CirclePostExample.Criteria criteria = circlePostExample.createCriteria();
        criteria.andCreateTimeGreaterThanOrEqualTo(DateUtils.getDayBegin(week_firstday));
        criteria.andCreateTimeLessThanOrEqualTo(DateUtils.getDayEnd(week_lastdday));
        criteria.andChannelEqualTo(Constants.DEFAULT_INTEGER);
        Integer thisweekartclenum = circlePostMapper.countByExample(circlePostExample);
        dataMap.put("thisweekartclenum", thisweekartclenum);

        return dataMap;
    }

    @Override
    public Map<String, Object> getAddressDistributeds() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<String> echartsxAxisDO = new ArrayList<String>();
        List<Integer> echartsyAxisDO = new ArrayList<Integer>();
        // List<NameValueDO> list = memLoginMapper.getAddressStatics();
        List<NameValueDO> topten = memLoginService.getAddressStaticsTen();
        if (CollectionUtils.isNotEmpty(topten)) {
            topten.forEach(o -> {
                echartsxAxisDO.add(o.getName());
                echartsyAxisDO.add(o.getValue());
            });
        }
        dataMap.put("echartsxAxis", echartsxAxisDO);
        dataMap.put("echartsyAxis", echartsyAxisDO);
        // dataMap.put("list", list);
        return dataMap;
    }

    @Override
    public List<Map<Integer, String>> getNewOrder() {
        List<Map<Integer, String>> result = memLoginService.getNewOrder();
        return result;
    }

    @Override
    public List<NameValueDO> getLevelDistributedsTen() {
        List<NameValueDO> list = new ArrayList<NameValueDO>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 1-10级
        param.put("start", 1);
        param.put("end", 10);
        Integer a = memLoginService.getLevelNum(param);
        // if (a > 0) {
        NameValueDO aDo = new NameValueDO("1-10级", a);
        list.add(aDo);
        // }

        // 11-20级
        param.put("start", 11);
        param.put("end", 20);
        Integer b = memLoginService.getLevelNum(param);
        // if (b > 0) {
        NameValueDO bDo = new NameValueDO("11-20级", b);
        list.add(bDo);
        // }

        // 21-30级
        param.put("start", 21);
        param.put("end", 30);
        Integer c = memLoginService.getLevelNum(param);
        // if (c > 0) {
        NameValueDO cDo = new NameValueDO("21-30级", c);
        list.add(cDo);
        // }

        // 31-40级
        param.put("start", 31);
        param.put("end", 40);
        Integer d = memLoginService.getLevelNum(param);
        // if (d > 0) {
        NameValueDO dDo = new NameValueDO("31-40级", d);
        list.add(dDo);
        // }

        // 41-50级
        param.put("start", 41);
        param.put("end", 50);
        Integer e = memLoginService.getLevelNum(param);
        // if (e > 0) {
        NameValueDO eDo = new NameValueDO("41-50级", e);
        list.add(eDo);
        // }

        // 51级以上
        param.put("start", 51);
        param.put("end", 150);
        Integer f = memLoginService.getLevelNum(param);
        // if (f > 0) {
        NameValueDO fDo = new NameValueDO("51级以上", f);
        list.add(fDo);
        // }

        return list;
    }

    @Override
    public List<NameValueDO> getLevelDistributedsFive() {
        List<NameValueDO> list = new ArrayList<NameValueDO>();
        List<Integer> maxlist = new ArrayList<Integer>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 1-10级
        param.put("start", 1);
        param.put("end", 5);
        Integer a = memLoginService.getLevelNum(param);
        // 11-20级
        param.put("start", 6);
        param.put("end", 10);
        Integer b = memLoginService.getLevelNum(param);
        // 21-30级
        param.put("start", 11);
        param.put("end", 15);
        Integer c = memLoginService.getLevelNum(param);
        // 31-40级
        param.put("start", 16);
        param.put("end", 20);
        Integer d = memLoginService.getLevelNum(param);
        // 41-50级
        param.put("start", 21);
        param.put("end", 25);
        Integer e = memLoginService.getLevelNum(param);
        // 51级以上
        param.put("start", 26);
        param.put("end", 150);
        Integer f = memLoginService.getLevelNum(param);

        maxlist.add(a);
        maxlist.add(b);
        maxlist.add(c);
        maxlist.add(d);
        maxlist.add(e);
        maxlist.add(f);

        Collections.sort(maxlist);
        Integer maxvalue = maxlist.get(maxlist.size() - 1);
        maxvalue = Integer.parseInt(String.valueOf((int) (Math.pow(10, String.valueOf(maxvalue).length()))));
        NameValueDO aDo = new NameValueDO("1-5级", a > 0 ? a + maxvalue : 0, maxvalue);
        list.add(aDo);
        NameValueDO bDo = new NameValueDO("6-10级", b > 0 ? b + maxvalue : 0, maxvalue);
        list.add(bDo);
        NameValueDO cDo = new NameValueDO("11-15级", c > 0 ? c + maxvalue : 0, maxvalue);
        list.add(cDo);
        NameValueDO dDo = new NameValueDO("16-20级", d > 0 ? d + maxvalue : 0, maxvalue);
        list.add(dDo);
        NameValueDO eDo = new NameValueDO("21-25级", e > 0 ? e + maxvalue : 0, maxvalue);
        list.add(eDo);
        NameValueDO fDo = new NameValueDO("26级以上", f > 0 ? f + maxvalue : 0, maxvalue);
        list.add(fDo);

        return list;
    }

    @Override
    public Long getCurrentOnlineNums() {
        return RedisBusinessUtil.countCheckInByDate(5);
    }

    private List<Integer> statMonthData(List<DateTime> dateTimes, List<NameValueDO> nameValueDOS) {
        List<Integer> data = new ArrayList<Integer>();
        Map<String, Integer> nameValueMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(nameValueDOS)) {
            nameValueMap = nameValueDOS.stream().collect(Collectors.toMap(NameValueDO::getName, NameValueDO::getValue));
        }
        List<List<DateTime>> partition = Lists.partition(dateTimes, 3);
        for (List<DateTime> list : partition) {
            Integer total = 0;
            for (DateTime dateTime : list) {
                total += Optional.ofNullable(nameValueMap.get(DateUtil.format(dateTime, "yyyy-MM"))).orElse(0);
            }
            data.add(total);
        }
        return data;
    }


    private List<String> getSeeStaticsXaxis(List<DateTime> dateTimes) {
        List<String> data = new ArrayList<String>();
        List<List<DateTime>> partition = Lists.partition(dateTimes, 3);
        for (List<DateTime> list : partition) {
            String xaxis = DateUtil.format(list.get(0), "yyyy-MM");
            data.add(xaxis);
        }
        return data;
    }



    @Override
    public HomeDataDO manualHomeStatics(String startTime, String endTime) {
        if (StringUtils.isEmpty(startTime)) {
            startTime = DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN);
        }
        if (StringUtils.isEmpty(endTime)) {
            endTime = DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN);
        }
        startTime = startTime + " 00:00:00";
        endTime = endTime + " 23:59:59";

        HomeDataDO homeDataDO = new HomeDataDO();
        List<HomeDataDO.QueryRule> queryRuleList = HomeDataDO.getQueryRule(startTime, endTime);
        try {
            MutiFutureTask.batchExec(queryRuleList, (HomeDataDO.QueryRule queryRule) -> {
                String startTime1 = queryRule.getStartTime();
                String endTime1 = queryRule.getEndTime();
                switch (queryRule.getKey()) {
                    case Constants.HOME_REGISTERED:
                        homeDataDO.setRegistered(getRegistered(startTime1, endTime1));
                        break;
                    case Constants.HOME_RECHARGEALLDATA:
                        homeDataDO.setRechargeAllDataDO(getRecharge(startTime1, endTime1));
                        break;
                    case Constants.HOME_PAYMENTALLDATA:
                        homeDataDO.setPaymentAllDataDO(getPayment(startTime1, endTime1));
                        break;
                    case Constants.HOME_ACTIVITYALLDATA:
                        homeDataDO.setActivityAllDataDO(getActivity(startTime1, endTime1));
                        break;
                    case Constants.HOME_CONSUMPTIONALLDATA:
                        homeDataDO.setConsumptionAllDataDO(getConsumption(startTime1, endTime1));
                        break;
                    case Constants.HOME_LOTTERYALLDATA:
                        homeDataDO.setLotteryAllDataDO(getLotteryProfit(startTime1, endTime1));
                        break;
                    default:
                }
                return queryRule;
            });
            homeDataDO.setProfitAllDataDO(getTotalProfit(homeDataDO.getConsumptionAllDataDO(), homeDataDO.getLotteryAllDataDO()));
        } catch (Exception e) {
            logger.error("manualHomeStatics统计失败，失败信息:{}", e.getMessage(), e);
            throw new BusinessException("manualHomeStatics统计失败");
        }
        logger.info("首页统计结果={}", JSON.toJSONString(homeDataDO));
        return homeDataDO;
    }

    @Override
    public BigDecimal getAllUserGoldNum() {
        //return NumberUtil.round(NumberUtil.null2Zero(memBaseinfoMapperExt.countAllUserAmount()), Constants.NUMBER_DECIMAL_POSITION, RoundingMode.DOWN);
        return NumUtils.precision3down(memBaseinfoMapperExt.countAllUserAmount());
    }

    /**
     * @return
     * @Author xiaoming
     * @Description 累计充值
     * @Date 2:11 下午 8/11/20
     * @Param
     **/
    private RechargeAllDataDO getRecharge(String startTime, String endTime) {
        long start = System.currentTimeMillis();
        List<RechargeUserDataDO> rechargeReportDOList = traOrderinfomMapperExt.getRecharge(startTime, endTime);

        RechargeAllDataDO rechargeAllDataDO = new RechargeAllDataDO();

        if (CollectionUtils.isNotEmpty(rechargeReportDOList)) {
            Map<Integer, List<RechargeUserDataDO>> integerListMap = rechargeReportDOList.stream().collect(Collectors.groupingBy(RechargeUserDataDO::getRechargetype));

            List<RechargeUserDataDO> memberUserDataDOS = Optional.ofNullable(integerListMap.get(Constants.ORDERTYPE1)).orElse(Lists.newArrayList());
            List<RechargeUserDataDO> companyUserDataDOS = Optional.ofNullable(integerListMap.get(Constants.ORDERTYPE2)).orElse(Lists.newArrayList());
            List<RechargeUserDataDO> agentUserDataDOS = Optional.ofNullable(integerListMap.get(Constants.ORDERTYPE15)).orElse(Lists.newArrayList());


            Function<List<RechargeUserDataDO>, BigDecimal> getamt = (item) -> item.stream().map(RechargeUserDataDO::getRechargeamt).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
            Function<List<RechargeUserDataDO>, Integer> getPeople = (item) -> CollectionUtils.size(item.stream().map(RechargeUserDataDO::getUserid).distinct().collect(Collectors.toList()));


            BigDecimal allrechargeamt = getamt.apply(rechargeReportDOList);
            int allrechargepeoples = getPeople.apply(rechargeReportDOList);

            BigDecimal onlineamt = getamt.apply(memberUserDataDOS);
            int onlinepeoples = getPeople.apply(memberUserDataDOS);
            BigDecimal offlineamt = getamt.apply(companyUserDataDOS);
            int offlinepeoples = getPeople.apply(companyUserDataDOS);
            BigDecimal agentamt = getamt.apply(agentUserDataDOS);
            int agentpeoples = getPeople.apply(agentUserDataDOS);

            rechargeAllDataDO.setAllrechargeamt(NumUtils.precision3down(allrechargeamt));
            rechargeAllDataDO.setAllrechargepeoples((long) allrechargepeoples);
            rechargeAllDataDO.setAllrechargetimes((long) CollectionUtils.size(rechargeReportDOList));
            rechargeAllDataDO.setOnlineamt(NumUtils.precision3down(onlineamt));
            rechargeAllDataDO.setOnlinepeoples((long) onlinepeoples);
            rechargeAllDataDO.setOfflineamt(NumUtils.precision3down(offlineamt));
            rechargeAllDataDO.setOfflinepeoples((long) offlinepeoples);
            rechargeAllDataDO.setAgentamt(NumUtils.precision3down(agentamt));
            rechargeAllDataDO.setAgentpeoples((long) agentpeoples);
        } else {
            rechargeAllDataDO = RechargeAllDataDO.getDefault();
        }
        logger.info("/getRecharge耗时{}毫秒：", (System.currentTimeMillis() - start));
        return rechargeAllDataDO;
    }

    /**
     * @return
     * @Author xiaoming
     * @Description 累计提现
     * @Date 2:11 下午 8/11/20
     * @Param
     **/
    private PaymentAllDataDO getPayment(String startTime, String endTime) {
        long start = System.currentTimeMillis();
        List<PaymentUserDataDO> paymentUserDataDOS = memGoldchangeMapperExt.getPayment(startTime, endTime);
        PaymentAllDataDO paymentAllDataDO = new PaymentAllDataDO();
        if (CollectionUtils.isNotEmpty(paymentUserDataDOS)) {
            Map<Integer, List<PaymentUserDataDO>> integerListMap = paymentUserDataDOS.stream().collect(Collectors.groupingBy(PaymentUserDataDO::getPaymenttype));
            List<PaymentUserDataDO> memberUserDataDOS = Optional.ofNullable(integerListMap.get(GoldchangeEnum.WITHDRAWN.getValue())).orElse(Lists.newArrayList());
            List<PaymentUserDataDO> familyGiftUserDataDOS = Optional.ofNullable(integerListMap.get(GoldchangeEnum.FAMILY_GIFT_DIVIDED.getValue())).orElse(Lists.newArrayList());
            List<PaymentUserDataDO> familyBetUserDataDOS = Optional.ofNullable(integerListMap.get(GoldchangeEnum.FAMILY_BET_DIVIDED.getValue())).orElse(Lists.newArrayList());
            List<PaymentUserDataDO> familyUserDataDOS = Lists.newArrayList();
            familyUserDataDOS.addAll(familyGiftUserDataDOS);
            familyUserDataDOS.addAll(familyBetUserDataDOS);


            Function<List<PaymentUserDataDO>, BigDecimal> getamt = (item) -> item.stream().map(PaymentUserDataDO::getPaymentamt).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
            Function<List<PaymentUserDataDO>, Integer> getPeople = (item) -> CollectionUtils.size(item.stream().map(PaymentUserDataDO::getUserid).distinct().collect(Collectors.toList()));

            BigDecimal allpaymentamt = getamt.apply(paymentUserDataDOS);
            Integer allpaymentpeoples = getPeople.apply(paymentUserDataDOS);
            BigDecimal membereamt = getamt.apply(memberUserDataDOS);
            Integer memberpeoples = getPeople.apply(memberUserDataDOS);
            BigDecimal familyamt = getamt.apply(familyUserDataDOS);
            Integer familypeoples = getPeople.apply(familyUserDataDOS);

            paymentAllDataDO.setAllpaymentamt(NumUtils.precision3down(allpaymentamt));
            paymentAllDataDO.setAllpaymentpeoples(allpaymentpeoples == null ? 0L : allpaymentpeoples.longValue());
            paymentAllDataDO.setAllpaymenttimes((long) CollectionUtils.size(paymentUserDataDOS));
            paymentAllDataDO.setMemberamt(NumUtils.precision3down(membereamt));
            paymentAllDataDO.setMemberpeoples(memberpeoples == null ? 0L : memberpeoples.longValue());
            paymentAllDataDO.setFamilyamt(NumUtils.precision3down(familyamt));
            paymentAllDataDO.setFamilypeoples(familypeoples == null ? 0L : familypeoples.longValue());

        } else {
            paymentAllDataDO = PaymentAllDataDO.getDefault();
        }
        logger.info("/getPayment耗时{}毫秒：", (System.currentTimeMillis() - start));
        return paymentAllDataDO;
    }

    /**
     * @return
     * @Author xiaoming
     * @Description 活动
     * @Date 9:44 下午 8/11/20
     * @Param
     **/
    private ActivityAllDataDO getActivity(String startTime, String endTime) {
        long start = System.currentTimeMillis();
        ActivityAllDataDO activityAllDataDO = new ActivityAllDataDO();
        List<ActivityUserDataDO> activityUserDataDOS = Optional.ofNullable(memGoldchangeMapperExt.getActivityGoldchange(startTime, endTime)).orElse(Lists.newArrayList());

        List<GodPlanRewardDataDO> godPlanRewardDataDOS = Optional.ofNullable(memGoldchangeMapperExt.getGodPlanReward(startTime, endTime)).orElse(Lists.newArrayList());
        BigDecimal godplanamt = NumberUtil.null2Zero(godPlanRewardDataDOS.stream().map(GodPlanRewardDataDO::getGodplanrewardamt).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        List<String> godPlanUser = godPlanRewardDataDOS.stream().map(GodPlanRewardDataDO::getUserid).distinct().collect(Collectors.toList());

        BigDecimal activitydiscountamt = NumberUtil.null2Zero(activityUserDataDOS.stream().map(ActivityUserDataDO::getActivityamt).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        List<String> activityUser = activityUserDataDOS.stream().map(ActivityUserDataDO::getUserid).distinct().collect(Collectors.toList());

        List<String> alluser = Lists.newArrayList();
        alluser.addAll(activityUser);
        alluser.addAll(godPlanUser);

        activityAllDataDO.setAllactivityamt(NumUtils.precision3down(godplanamt.subtract(activitydiscountamt)));
        activityAllDataDO.setAllactivitypeoples((long) CollectionUtils.size(alluser.stream().distinct().collect(Collectors.toList())));
        activityAllDataDO.setActivitydiscountamt(NumUtils.precision3down(activitydiscountamt));
        activityAllDataDO.setActivitydiscountpeoples((long) CollectionUtils.size(activityUser));
        activityAllDataDO.setActivityrecoveryamt(NumUtils.precision3down(godplanamt));
        activityAllDataDO.setActivityrecoverypeoples((long) CollectionUtils.size(godPlanUser));
        logger.info("/getActivity耗时{}毫秒：", (System.currentTimeMillis() - start));
        return activityAllDataDO;
    }

    /**
     * @return
     * @Author xiaoming
     * @Description 打码流水
     * @Date 2:11 下午 8/11/20
     * @Param
     **/
    private ConsumptionAllDataDO getConsumption(String startTime, String endTime) {
        long start = System.currentTimeMillis();
        //彩票投注
        List<LotteryBetDataDO> lotteryBetDataDOS = Optional.ofNullable(orderBetRecordMapperExt.statisticalAllBetData(startTime, endTime)).orElse(Lists.newArrayList());
        // 游戏
        List<GameUserDataDO> gameUserDataDOS = getGameUserData(startTime, endTime);
        // 大神
        List<GodPlanRewardDataDO> godPlanRewardDataDOS = Optional.ofNullable(memGoldchangeMapperExt.getGodPlanReward(startTime, endTime)).orElse(Lists.newArrayList());
        // 礼物
        List<GiftUserDataDO> giftDataDOS = Optional.ofNullable(memGoldchangeMapperExt.getGift(startTime, endTime)).orElse(Lists.newArrayList());

        BigDecimal lotamt = NumberUtil.null2Zero(lotteryBetDataDOS.stream().map(LotteryBetDataDO::getLotamt).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        BigDecimal gameamt = NumberUtil.null2Zero(gameUserDataDOS.stream().map(GameUserDataDO::getGameamt).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        BigDecimal gameawardamt = NumberUtil.null2Zero(gameUserDataDOS.stream().filter(item -> {
            return BigDecimal.ZERO.compareTo(NumberUtil.null2Zero(item.getGameawardamt())) < 1;
        }).map(GameUserDataDO::getGameawardamt).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        BigDecimal godplanamt = NumberUtil.null2Zero(godPlanRewardDataDOS.stream().map(GodPlanRewardDataDO::getGodplanrewardamt).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));
        BigDecimal giftamt = NumberUtil.null2Zero(giftDataDOS.stream().map(GiftUserDataDO::getGiftamt).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add));

        List<String> lotteryUser = lotteryBetDataDOS.stream().map(LotteryBetDataDO::getUserid).distinct().collect(Collectors.toList());
        List<String> gameUser = gameUserDataDOS.stream().map(GameUserDataDO::getUserid).distinct().collect(Collectors.toList());
        List<String> godPlanUser = godPlanRewardDataDOS.stream().map(GodPlanRewardDataDO::getUserid).distinct().collect(Collectors.toList());
        List<String> giftUser = giftDataDOS.stream().map(GiftUserDataDO::getUserid).distinct().collect(Collectors.toList());

        List<String> alluser = Lists.newArrayList();
        alluser.addAll(lotteryUser);
        alluser.addAll(gameUser);
        alluser.addAll(godPlanUser);
        alluser.addAll(giftUser);

        ConsumptionAllDataDO consumptionAllDataDO = new ConsumptionAllDataDO();
        consumptionAllDataDO.setAllconsumptionamt(NumUtils.precision3down(lotamt.add(gameamt).add(godplanamt).add(giftamt)));
        consumptionAllDataDO.setAllconsumptionpeoples((long) CollectionUtils.size(alluser.stream().distinct().collect(Collectors.toList())));
        consumptionAllDataDO.setLotamt(NumUtils.precision3down(lotamt));
        consumptionAllDataDO.setLotpeoples((long) CollectionUtils.size(lotteryUser));
        consumptionAllDataDO.setGameamt(NumUtils.precision3down(gameamt));
        consumptionAllDataDO.setGameawardamt(NumUtils.precision3down(gameawardamt));
        consumptionAllDataDO.setGamepeoples((long) CollectionUtils.size(gameUser));
        consumptionAllDataDO.setGodplanamt(NumUtils.precision3down(godplanamt));
        consumptionAllDataDO.setGodplanpeoples((long) CollectionUtils.size(godPlanUser));
        consumptionAllDataDO.setGiftamt(NumUtils.precision3down(giftamt));
        consumptionAllDataDO.setGiftpeoples((long) CollectionUtils.size(giftUser));

        logger.info("/getConsumption耗时{}毫秒：", (System.currentTimeMillis() - start));
        return consumptionAllDataDO;
    }

    /**
     * @return
     * @Author xiaoming
     * @Description 彩票盈利
     * @Date 2:12 下午 8/11/20
     * @Param
     **/
    private LotteryAllDataDO getLotteryProfit(String startTime, String endTime) {
        long start = System.currentTimeMillis();
        List<LotteryDataDO> lotteryDataDOS = Optional.ofNullable(orderBetRecordMapperExt.statisticalDataBylottery(startTime, endTime)).orElse(Lists.newArrayList());
        LotteryAllDataDO lotteryAllDataDO = new LotteryAllDataDO();
        if (CollectionUtils.isNotEmpty(lotteryDataDOS)) {
            BigDecimal lotamt = lotteryDataDOS.stream().map(LotteryDataDO::getLotamt).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal lotawardamt = lotteryDataDOS.stream().map(LotteryDataDO::getLotawardamt).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal lotrofit = lotamt.subtract(lotawardamt);

            LotteryDataDO maxbet = lotteryDataDOS.stream().max(Comparator.comparing(LotteryDataDO::getLotamt)).get();

            LotteryDataDO max = lotteryDataDOS.stream().max(Comparator.comparing(LotteryDataDO::getLotprofit)).get();

            LotteryDataDO maxloss = lotteryDataDOS.stream().min(Comparator.comparing(LotteryDataDO::getLotprofit)).get();

            lotteryAllDataDO.setLotrofit(NumUtils.precision3down(lotrofit));
            lotteryAllDataDO.setLotamt(NumUtils.precision3down(lotamt));
            lotteryAllDataDO.setLotawardamt(NumUtils.precision3down(lotawardamt));

            if (max.getLotprofit().compareTo(BigDecimal.ZERO) > -1) {
                lotteryAllDataDO.setMaxprofit(NumUtils.precision3down(max.getLotprofit()));
                lotteryAllDataDO.setMaxprofitlotteryId(max.getLotteryid());
                lotteryAllDataDO.setMaxprofitlotteryname(max.getLotteryname());
            } else {
                lotteryAllDataDO.setMaxprofit(BigDecimal.ZERO);
                lotteryAllDataDO.setMaxprofitlotteryId(0L);
                lotteryAllDataDO.setMaxprofitlotteryname("无");
            }
            if (maxloss.getLotprofit().compareTo(BigDecimal.ZERO) < 1) {
                lotteryAllDataDO.setMaxloss(NumUtils.precision3down(maxloss.getLotprofit()));
                lotteryAllDataDO.setMaxlosslotteryid(maxloss.getLotteryid());
                lotteryAllDataDO.setMaxlosslotteryname(maxloss.getLotteryname());
            } else {
                lotteryAllDataDO.setMaxloss(BigDecimal.ZERO);
                lotteryAllDataDO.setMaxlosslotteryid(0L);
                lotteryAllDataDO.setMaxlosslotteryname("无");
            }
            lotteryAllDataDO.setMaxbet(NumUtils.precision3down(maxbet.getLotamt()));
            lotteryAllDataDO.setMaxbetlotteryid(maxbet.getLotteryid());
            lotteryAllDataDO.setMaxbetlotteryname(maxbet.getLotteryname());
        } else {
            lotteryAllDataDO = LotteryAllDataDO.getDefault();
        }
        logger.info("/getLotteryProfit耗时{}毫秒：", (System.currentTimeMillis() - start));
        return lotteryAllDataDO;
    }

    /**
     * @return
     * @Author xiaoming
     * @Description 总盈利
     * @Date 9:49 下午 8/11/20
     * @Param
     **/
    private ProfitAllDataDO getTotalProfit(ConsumptionAllDataDO consumption, LotteryAllDataDO lotteryProfit) {
        ProfitAllDataDO profitAllDataDO = new ProfitAllDataDO();
        // 打码流水
        BigDecimal allconsumptionamt = NumberUtil.null2Zero(consumption.getAllconsumptionamt());
        // 游戏
        BigDecimal gameawardamt = NumberUtil.null2Zero(consumption.getGameawardamt());
        // 彩票
        BigDecimal lotawardamt = NumberUtil.null2Zero(lotteryProfit.getLotawardamt());

        BigDecimal totalprofit = allconsumptionamt.subtract(gameawardamt.add(lotawardamt));

        BigDecimal profitrate = BigDecimal.ZERO;
        if (allconsumptionamt.compareTo(BigDecimal.ZERO) != 0) {
            profitrate = totalprofit.divide(allconsumptionamt, 4, BigDecimal.ROUND_HALF_UP);
        }

        profitAllDataDO.setTotalprofit(NumUtils.precision3down(totalprofit));
        profitAllDataDO.setProfitrate(NumUtils.precision3down(profitrate));
        return profitAllDataDO;
    }


    /**
     * @return
     * @Author xiaoming
     * @Description 注册人数
     * @Date 2:12 下午 8/11/20
     * @Param
     **/
    private Integer getRegistered(String startTime, String endTime) {
        long start = System.currentTimeMillis();
        Integer memnums = memLoginMapperExt.getRegisteredNumByDate(startTime, endTime);
        logger.info("/getRegistered耗时{}毫秒：", (System.currentTimeMillis() - start));
        return memnums == null ? 0 : memnums;
    }

    private List<GameUserDataDO> getGameUserData(String startTime, String endTime) {
        List<GameUserDataDO> ae = aeBetOrderMapperExt.statisticalDataByUser(startTime, endTime);
        List<GameUserDataDO> ag = agBetOrderMapperExt.statisticalDataByUser(startTime, endTime);
        List<GameUserDataDO> agFish = agFishBetOrderMapperExt.statisticalDataByUser(startTime, endTime);
        List<GameUserDataDO> db = dbBetOrderMapperExt.statisticalDataByUser(startTime, endTime);
        List<GameUserDataDO> es = esBetOrderMapperExt.statisticalDataByUser(startTime, endTime);
        List<GameUserDataDO> ky = kyBetOrderMapperExt.statisticalDataByUser(startTime, endTime);
        List<GameUserDataDO> mg = mgBetOrderMapperExt.statisticalDataByUser(startTime, endTime);

        List<GameUserDataDO> gameUserDataDOS = Lists.newArrayList();
        gameUserDataDOS.addAll(ae);
        gameUserDataDOS.addAll(ag);
        gameUserDataDOS.addAll(agFish);
        gameUserDataDOS.addAll(db);
        gameUserDataDOS.addAll(es);
        gameUserDataDOS.addAll(ky);
        gameUserDataDOS.addAll(mg);

        return gameUserDataDOS;

    }
}
