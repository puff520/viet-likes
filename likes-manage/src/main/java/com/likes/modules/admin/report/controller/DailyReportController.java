//package com.likes.modules.admin.report.controller;
//
//import com.likes.common.annotation.AllowAccess;
//import com.likes.common.constant.Constants;
//import com.likes.common.exception.BusinessException;
//import com.likes.common.model.common.ResultInfo;
//import com.likes.common.model.response.DepositsReports;
//import com.likes.common.model.response.ThirdProvider;
//import com.likes.common.model.response.ThirdProviderNameResponse;
//import com.likes.common.service.money.SysPayproviderService;
//import com.likes.common.util.DateUtils;
//import com.likes.common.util.MathUtil;
//import com.likes.modules.admin.report.service.DepositReportService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.*;
//
//@RestController
//@RequestMapping(value = "/Report")
//public class DailyReportController {
//    private static final Logger logger = LoggerFactory.getLogger(DailyReportController.class);
//
//    @Autowired
//    private DepositReportService depositReportService;
//    @Autowired
//    private SysPayproviderService sysPayproviderService;
//
//    /**
//     * 入款报表统计
//     */
//    @AllowAccess
//    @RequestMapping(name = "入款报表统计", value = "/depositReportStatistics", method = RequestMethod.POST)
//    public ResultInfo<List<DepositsReports>> depositReportStatistics(@RequestParam(value = "startTime", required = false) String startTime, @RequestParam(value = "endTime", required = false) String endTime) {
//        ResultInfo<List<DepositsReports>> resultInfo = new ResultInfo<>();
//        if (endTime != null && !"".equals(endTime) && !startTime.equals(endTime)) {
//            endTime = DateUtils.getEndOfDayString(endTime);
//        }
//        if (startTime == null || "".equals(startTime)) {
//            startTime = DateUtils.getFirstDayString();
//        }
//        if (endTime == null || "".equals(endTime)) {
//            endTime = DateUtils.getLastDayString();
//        }
//        if (startTime.equals(endTime)) {
//            startTime = DateUtils.getStartOfDayString(startTime);
//            endTime = DateUtils.getEndOfDayString(endTime);
//        }
//
//        try {
//            List<DepositsReports> future = new ArrayList<>();
//            List<Future<DepositsReports>> futures = new ArrayList<>();
//            DepositsReports depositsReport = new DepositsReports();
//            final String endTimeFinal = endTime;
//            final String startTimeFinal = startTime;
//
//            Future<DepositsReports> submit = executors.submit(new Callable<DepositsReports>() {
//                @Override
//                public DepositsReports call() {
//                    return depositReportService.getDepositByKefu(startTimeFinal, endTimeFinal);
//                }
//
//            });
//            futures.add(submit);
//            Future<DepositsReports> company = executors.submit(new Callable<DepositsReports>() {
//                @Override
//                public DepositsReports call() {
//                    return depositReportService.getDepositByCompany(startTimeFinal, endTimeFinal);
//                }
//
//            });
//            futures.add(company);
//            Future<DepositsReports> online = executors.submit(new Callable<DepositsReports>() {
//                @Override
//                public DepositsReports call() {
//                    return depositReportService.getDepositByThirdOnline(startTimeFinal, endTimeFinal);
//                }
//
//            });
//            futures.add(online);
//            Future<DepositsReports> payfor = executors.submit(new Callable<DepositsReports>() {
//                @Override
//                public DepositsReports call() {
//                    return depositReportService.getDepositByPayFor(startTimeFinal, endTimeFinal);
//                }
//
//            });
//            futures.add(payfor);
//
//
//            DepositsReports s = null;
//            for (Future<DepositsReports> future1 : futures) {
//                logger.info("reportStatistics.get");
//                s = future1.get();
//                future.add(s);
//            }
//            String caculate = Constants.STR_ZERO;
//            String caculateDeposit = Constants.STR_ZERO;
//            String caculateSuccess = Constants.STR_ZERO;
//            String caculateFail = Constants.STR_ZERO;
//            for (DepositsReports depositsReports : future) {
//                caculate = MathUtil.sum(caculate, depositsReports.getNumPeople());
//                caculateDeposit = MathUtil.sum(caculateDeposit, depositsReports.getSumDeposits());
//                caculateFail = MathUtil.sum(caculateFail, depositsReports.getNumFail() == Constants.DEPOSITS_NULL ? "0" : depositsReports.getNumFail());
//                caculateSuccess = MathUtil.sum(caculateSuccess, depositsReports.getNumSuccess() == Constants.DEPOSITS_NULL ? "0" : depositsReports.getNumSuccess());
//            }
//            depositsReport.setDepositsType(Constants.CACULATE);
//            depositsReport.setNumPeople(caculate);
//            depositsReport.setSumDeposits(caculateDeposit);
//            depositsReport.setSuccessRatio(Constants.DEPOSITS_NULL);
//            depositsReport.setNumFail(caculateFail);
//            depositsReport.setNumSuccess(caculateSuccess);
//            future.add(depositsReport);
//            resultInfo.setData(future);
//
//        } catch (BusinessException e) {
//            logger.error("{}.depositReportStatistics失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
//            resultInfo.setResultInfo(e.getCode(), e.getMessage());
//        } catch (Exception e) {
//            logger.error("{}.depositReportStatistics,出错:{},params:{}", getClass().getName(), e.getMessage(), "startTime=" + startTime + ";endTime=" + endTime, e);
//            resultInfo = ResultInfo.error("入款报表统计出错");
//        }
//        return resultInfo;
//    }
//
//    @AllowAccess
//    @RequestMapping(name = "三方", value = "/depositReportThird", method = RequestMethod.POST)
//    public ResultInfo<List<ThirdProvider>> depositReportThird(@RequestParam(value = "startTime", required = false) String startTime, @RequestParam(value = "endTime", required = false) String endTime, @RequestParam(value = "provider", required = false) String provider) {
//        ResultInfo<List<ThirdProvider>> resultInfo = new ResultInfo<>();
//        if (endTime != null && !"".equals(endTime) && !startTime.equals(endTime)) {
//            endTime = DateUtils.getEndOfDayString(endTime);
//        }
//        if (startTime == null || "".equals(startTime)) {
//            startTime = DateUtils.getFirstDayString();
//        }
//        if (endTime == null || "".equals(endTime)) {
//            endTime = DateUtils.getLastDayString();
//        }
//        if (startTime.equals(endTime)) {
//            startTime = DateUtils.getStartOfDayString(startTime);
//            endTime = DateUtils.getEndOfDayString(endTime);
//        }
//        List<ThirdProvider> thirdProviders = null;
//        try {
//            thirdProviders = depositReportService.getDepositReportThird(startTime, endTime, provider);
//        } catch (BusinessException e) {
//            logger.error("{}.depositReportThird失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
//            resultInfo.setResultInfo(e.getCode(), e.getMessage());
//        } catch (Exception e) {
//            logger.error("{}.depositReportThird,出错:{},params:{},provider:{}", getClass().getName(), e.getMessage(), "startTime=" + startTime + ";endTime=" + endTime, provider, e);
//            resultInfo = ResultInfo.error("获取三方报表出错");
//        }
//        return resultInfo.setData(thirdProviders);
//    }
//
//    @AllowAccess
//    @RequestMapping(value = "/ThirdProvider", method = RequestMethod.POST)
//    public ResultInfo<List<ThirdProviderNameResponse>> ThirdProvider() {
//        ResultInfo<List<ThirdProviderNameResponse>> resultInfo = new ResultInfo<>();
//        List<ThirdProviderNameResponse> thirdProviders = null;
//        try {
//            thirdProviders = sysPayproviderService.getIdAndName();
//        } catch (BusinessException e) {
//            logger.error("{}.ThirdProvider三方列表失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
//            resultInfo.setResultInfo(e.getCode(), e.getMessage());
//        } catch (Exception e) {
//            logger.error("{}.depositReportThird,{}出错:", getClass().getName(), e.getMessage(), e);
//            resultInfo = ResultInfo.error("获取三方列表出错");
//        }
//        return resultInfo.setData(thirdProviders);
//    }
//
//
//}
//
