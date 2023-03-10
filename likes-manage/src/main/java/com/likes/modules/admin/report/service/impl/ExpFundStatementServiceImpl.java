package com.likes.modules.admin.report.service.impl;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.likes.common.constant.Constants;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.bas.FamilyRoomDTO;
import com.likes.common.model.dto.report.*;
import com.likes.common.model.vo.report.ExpFundStatementVO;
import com.likes.common.model.vo.report.ExpFundsVO;
import com.likes.common.model.vo.report.SuperExpFundsVO;
import com.likes.common.mybatis.entity.ExpFundstatement;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.mybatis.mapperext.order.*;
import com.likes.common.mybatis.mapperext.report.ExpFundstatementMapperExt;
import com.likes.common.mybatis.mapperext.sys.SysParameterMapperExt;
import com.likes.common.mybatis.mapperext.tra.TraOrderinfomMapperExt;
import com.likes.common.util.DateUtils;
import com.likes.modules.admin.report.service.ExpFundStatementService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author xiaoming
 * @ClassName
 * @Description
 * @Date 9:04 ?????? 7/29/20
 **/
@Service
public class ExpFundStatementServiceImpl implements ExpFundStatementService {
    private static final Logger logger = LoggerFactory.getLogger(ExpFundStatementServiceImpl.class);

    @Resource
    private ExpFundstatementMapperExt expFundstatementMapperExt;
    @Resource
    private OrderBetRecordMapperExt orderBetRecordMapperExt;
    @Resource
    private TraOrderinfomMapperExt traOrderinfomMapperExt;
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
    private SysParameterMapperExt sysParameterMapperExt;
    @Autowired
    private RedissonClient redissonClient;


    @Override
    public ExpFundsVO getListByYearMonth(String yearmonth) {
        if (StringUtils.isBlank(yearmonth)) {
            yearmonth = DateUtil.format(new Date(), "yyyyMM");
        }
        ExpFundsVO expFundsVO = new ExpFundsVO();
        List<ExpFundstatement> expFundstatements = expFundstatementMapperExt.getListByYearMonth(yearmonth);
        List<ExpFundStatementVO> list = expFundstatements.stream().map(ExpFundStatementVO::getDefault).collect(Collectors.toList());
        expFundsVO.setList(list);
        expFundsVO.setSummary(getOneMonthSum(yearmonth));
        return expFundsVO;
    }

    @Override
    public ExpFundsSummaryDO getOneMonthSum(String yearmonth) {
        BigDecimal zero = new BigDecimal(0);
        ExpFundsSummaryDO expFundsSummaryDO = Optional.ofNullable(expFundstatementMapperExt.getOneMonthSum(yearmonth)).orElse(ExpFundsSummaryDO.getDefault(yearmonth));

        String lotamt = NumberUtil.null2Zero(expFundsSummaryDO.getLotamt()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        String lotawardamt = NumberUtil.null2Zero(expFundsSummaryDO.getLotawardamt()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        expFundsSummaryDO.setLotamtAwardamt(String.join("/", lotamt, lotawardamt));

        String gameamt = NumberUtil.null2Zero(expFundsSummaryDO.getGameamt()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        String gameawardamt = NumberUtil.null2Zero(expFundsSummaryDO.getGameawardamt()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        expFundsSummaryDO.setGameamtAwardamt(String.join("/", gameamt, gameawardamt));

        //??????????????????
        expFundsSummaryDO.setGiftfamilyamt(expFundsSummaryDO.getGiftsumamt() != null ? expFundsSummaryDO.getGiftsumamt().subtract(expFundsSummaryDO.getGiftamt()) : zero);
        String giftsumamt = NumberUtil.null2Zero(expFundsSummaryDO.getGiftsumamt()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        String giftfamilyamt = NumberUtil.null2Zero(expFundsSummaryDO.getGiftfamilyamt()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        expFundsSummaryDO.setGiftamtAwardamt(String.join("/", giftsumamt, giftfamilyamt));

        Date date = DateUtil.parse(yearmonth, "yyyyMM");
        String excelDateStr = DateUtil.format(date, "yyyy???MM???");

        expFundsSummaryDO.setExcelDateStr(excelDateStr);
        return expFundsSummaryDO;
    }

    @Override
    public Workbook exportFundsExcel(String yearmonth) {
        if (StringUtils.isBlank(yearmonth)) {
            throw new BusinessException("????????????");
        }
        ExpFundsVO expFundsVO = getListByYearMonth(yearmonth);
        TemplateExportParams params = new TemplateExportParams("excel/expfundsTemplate.xlsx");
        Map<String, Object> map = new HashMap<>();
        map.put("list", expFundsVO.getList());
        map.put("summary", expFundsVO.getSummary());
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        return workbook;
    }


    /**
     * @return
     * @Author xiaoming
     * @Description ???????????????????????????
     * @Date 10:38 ?????? 7/29/20
     * @Param
     **/
    private void statisticalDailyFundStatement(Date date, LoginUser loginUser) {
        String formatDate = DateUtil.format(date, DatePattern.NORM_DATE_PATTERN);
        String startTime = formatDate + " 00:00:00";
        String endTime = formatDate + " 23:59:59";
        DailyFundsDataDO dailyFundsDataDO = Optional.ofNullable(traOrderinfomMapperExt.statisticalDayData(startTime, endTime)).orElse(DailyFundsDataDO.getDefault());
        FirstRechargeDailyDataDO firstRechargeDailyDataDO = Optional.ofNullable(traOrderinfomMapperExt.statisticalFirstRechargeData(startTime, endTime)).orElse(FirstRechargeDailyDataDO.getDefault());
        LotDailyWinDataDO lotDailyData = getLotDailyData(startTime, endTime);
         // GiftDailyDataDO giftDailyData = getGiftDailyData(startTime, endTime);
        GameDailyDataDO gameDailyData = getGameDailyData(startTime, endTime);
        //????????????
        BigDecimal lotamt = NumberUtil.null2Zero(lotDailyData.getLotamt());
        BigDecimal lotawardamt = NumberUtil.null2Zero(lotDailyData.getLotawardamt());
        BigDecimal lprofit = lotamt.subtract(lotawardamt);
        //????????????
        BigDecimal gameamt = NumberUtil.null2Zero(gameDailyData.getGameamt());
        BigDecimal gameawardamt = NumberUtil.null2Zero(gameDailyData.getGameawardamt());
        BigDecimal gameprofit = gameamt.subtract(gameawardamt);

        BigDecimal giftsumamt = BigDecimal.ZERO;
        BigDecimal giftamt = BigDecimal.ZERO;
        //?????????
        BigDecimal profitamt = lprofit.add(gameprofit).add(giftamt);
        boolean flag = profitamt.compareTo(BigDecimal.ZERO) == -1;
        //???????????????,????????????
        //???????????????????????? ???????????????10
        BigDecimal lotbonus = new BigDecimal("0.10");
        SysParameter bonusparameter = sysParameterMapperExt.selectByCode(Constants.PLANT_BOUNS);
        if (Objects.nonNull(bonusparameter)) {
            lotbonus = new BigDecimal(bonusparameter.getSysparamvalue());
        }
        BigDecimal lotDraw = BigDecimal.ZERO, gameDraw = BigDecimal.ZERO, giftDraw = BigDecimal.ZERO;
        lotDraw = lprofit.compareTo(BigDecimal.ZERO) != -1 ? lprofit.multiply(lotbonus) : BigDecimal.ZERO;
        gameDraw = gameprofit.compareTo(BigDecimal.ZERO) != -1 ? gameprofit.multiply(lotbonus) : BigDecimal.ZERO;
        giftDraw = giftamt.compareTo(BigDecimal.ZERO) != -1 ? giftamt.multiply(lotbonus) : BigDecimal.ZERO;
        BigDecimal platformamt = lotDraw.add(gameDraw).add(giftDraw);
        //??????????????? ??????5w
        BigDecimal operateamt = new BigDecimal("50000.00");
        SysParameter operateparameter = sysParameterMapperExt.selectByCode(Constants.PLANT_OPERATE);
        if (Objects.nonNull(operateparameter)) {
            operateamt = new BigDecimal(operateparameter.getSysparamvalue());
        }
        //???????????????????????????0
        platformamt = flag ? BigDecimal.ZERO : platformamt;
        BigDecimal dailyOperateamt = operateamt.divide(new BigDecimal(DateUtils.getDaysOfMonth(date)), 2);
        BigDecimal payamt = platformamt.add(dailyOperateamt);

        ExpFundstatement expFundstatement = new ExpFundstatement();
        expFundstatement.setRechargeamt(NumberUtil.null2Zero(dailyFundsDataDO.getRechargeamt()));
        expFundstatement.setPaymentamt(NumberUtil.null2Zero(dailyFundsDataDO.getPaymentamt()));
        expFundstatement.setFirstrecharge(firstRechargeDailyDataDO.getFirstrecharge() != null ? firstRechargeDailyDataDO.getFirstrecharge() : 0L);
        expFundstatement.setLotamt(lotamt);
        expFundstatement.setLotawardamt(lotawardamt);
        expFundstatement.setGameamt(gameamt);
        expFundstatement.setGameawardamt(gameawardamt);
        expFundstatement.setGiftsumamt(giftsumamt);
        expFundstatement.setGiftamt(giftamt);
        expFundstatement.setProfitamt(profitamt);
        expFundstatement.setPlatformamt(platformamt);
        expFundstatement.setOperateamt(dailyOperateamt);
        expFundstatement.setPayamt(payamt);
        expFundstatement.setCreateTime(date);
        //?????????????????????????????? ???????????????
        ExpFundstatement fundstatements = expFundstatementMapperExt.existsByDate(formatDate);
        if (Objects.nonNull(fundstatements)) {
            expFundstatementMapperExt.updateIsDeleteByDate(true, formatDate);
        }
        if (loginUser != null) {
            expFundstatement.setCreateUser(loginUser.getAccno());
        }
        expFundstatementMapperExt.insertSelective(expFundstatement);
        return;
    }

    /**
     * @return
     * @Author xiaoming
     * @Description ??????????????????????????????
     * @Date 10:36 ?????? 7/29/20
     * @Param
     **/
    private LotDailyWinDataDO getLotDailyData(String startTime, String endTime) {
        LotDailyWinDataDO data = orderBetRecordMapperExt.statisticalDayData(startTime, endTime);
        LotDailyWinDataDO winData = orderBetRecordMapperExt.statisticalDayWinData(startTime, endTime);
        LotDailyWinDataDO result = new LotDailyWinDataDO();
        if (Objects.nonNull(data)) {
            result.setLotamt(data.getLotamt());
        }
        if (Objects.nonNull(winData)) {
            result.setLotawardamt(winData.getLotawardamt());
        }
        return result;
    }


    /**
     * @return
     * @Author xiaoming
     * @Description ??????????????????????????????
     * @Date 10:35 ?????? 7/29/20
     * @Param
     **/
    private GameDailyDataDO getGameDailyData(String startTime, String endTime) {
        //???????????????
        BigDecimal gameamt = BigDecimal.ZERO;
        //?????????
        BigDecimal gameawardamt = BigDecimal.ZERO;
        GameDailyDataDO ae = Optional.ofNullable(aeBetOrderMapperExt.statisticalDayData(startTime, endTime)).orElse(GameDailyDataDO.getDefault());
        GameDailyDataDO ag = Optional.ofNullable(agBetOrderMapperExt.statisticalDayData(startTime, endTime)).orElse(GameDailyDataDO.getDefault());
        GameDailyDataDO agFish = Optional.ofNullable(agFishBetOrderMapperExt.statisticalDayData(startTime, endTime)).orElse(GameDailyDataDO.getDefault());
        GameDailyDataDO db = Optional.ofNullable(dbBetOrderMapperExt.statisticalDayData(startTime, endTime)).orElse(GameDailyDataDO.getDefault());
        GameDailyDataDO es = Optional.ofNullable(esBetOrderMapperExt.statisticalDayData(startTime, endTime)).orElse(GameDailyDataDO.getDefault());
        GameDailyDataDO ky = Optional.ofNullable(kyBetOrderMapperExt.statisticalDayData(startTime, endTime)).orElse(GameDailyDataDO.getDefault());
        GameDailyDataDO mg = Optional.ofNullable(mgBetOrderMapperExt.statisticalDayData(startTime, endTime)).orElse(GameDailyDataDO.getDefault());

        List<GameDailyDataDO> gameDailyDataDOS = Lists.newArrayList(ae, ag, agFish, db, es, ky, mg);

        logger.info("startTime ={} endTime ={}  gameDailyData={}", startTime, endTime, JSON.toJSONString(gameDailyDataDOS));
        gameamt = gameDailyDataDOS.stream().map(GameDailyDataDO::getGameamt).reduce(BigDecimal.ZERO, BigDecimal::add);
        gameawardamt = gameDailyDataDOS.stream().filter(item -> {
            return BigDecimal.ZERO.compareTo(NumberUtil.null2Zero(item.getGameawardamt())) < 1;
        }).map(GameDailyDataDO::getGameawardamt).reduce(BigDecimal.ZERO, BigDecimal::add);
        GameDailyDataDO result = new GameDailyDataDO();
        result.setGameamt(gameamt);
        result.setGameawardamt(gameawardamt);
        return result;

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void manualStatisticsFunds(String startTime, String endTime, LoginUser loginUser) {
        if (StringUtils.isBlank(startTime)) {
            throw new BusinessException("??????????????????");
        }
        if (StringUtils.isBlank(endTime)) {
            throw new BusinessException("??????????????????");
        }
        Date startDate = DateUtil.parse(startTime, DatePattern.PURE_DATE_PATTERN);
        Date endDate = DateUtil.parse(endTime, DatePattern.PURE_DATE_PATTERN);
        if (startDate.compareTo(endDate) > 0) {
            throw new BusinessException("????????????????????????????????????");
        }
        long daynums = DateUtil.betweenDay(startDate, endDate, true);
        if (daynums > Constants.EXP_FUND_STATISTICS_DAYS) {
            throw new BusinessException("??????????????????60???");
        }
//        //???????????????
//        if (DateUtil.isSameDay(new Date(), endDate) || endDate.after(new Date())) {
//            throw new BusinessException("???????????????????????????");
//        }
        String key = "lock_statisticalDailyFundStatement";
        RReadWriteLock lock = redissonClient.getReadWriteLock(key + "lock");
        try {
            boolean bool = lock.writeLock().tryLock(20, 30, TimeUnit.SECONDS);
            if (!bool) {
                throw new BusinessException("????????????????????????????????????");
            }
            //?????????????????????????????????
            List<DateTime> dateTimes = DateUtil.rangeToList(startDate, endDate, DateField.DAY_OF_YEAR);
            for (DateTime dateTime : dateTimes) {
                statisticalDailyFundStatement(dateTime, loginUser);
            }
        } catch (Exception e) {
            logger.error("processStatisticalDailyFundStatementOccur error", e);
            throw new BusinessException("????????????????????????");
        } finally {
            lock.writeLock().unlock();
        }

    }

    @Override
    public SuperExpFundsVO collectListByYearMonth(String yearmonth) {
        if (StringUtils.isBlank(yearmonth)) {
            yearmonth = DateUtil.format(new Date(), "yyyyMM");
        }
        SuperExpFundsVO superExpFundsVO = new SuperExpFundsVO();
        superExpFundsVO.setSummary(getSuperOneMonthSum(yearmonth));
        return superExpFundsVO;
    }

    public SuperExpFundsSummaryDO getSuperOneMonthSum(String yearmonth) {
        return Optional.ofNullable(expFundstatementMapperExt.getSuperOneMonthSum(yearmonth)).orElse(SuperExpFundsSummaryDO.getDefault(yearmonth));
    }
}
