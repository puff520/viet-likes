package com.likes.modules.admin.report.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.constant.Constants;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.model.dto.report.DepositStatisticsDO;
import com.likes.common.model.dto.report.ProviderSetDO;
import com.likes.common.model.dto.report.ThirdProviderDO;
import com.likes.common.model.response.DepositsReports;
import com.likes.common.model.response.ThirdProvider;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.service.money.SysPayproviderService;
import com.likes.common.service.money.SysThreePaysetService;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.util.MathUtil;
import com.likes.common.util.StringUtils;
import com.likes.modules.admin.report.service.DepositReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepositReportServiceImpl implements DepositReportService {
    private static final Logger logger = LoggerFactory.getLogger(DepositReportServiceImpl.class);

    @Resource
    private TraOrderinfomService traOrderinfomService;
    @Resource
    private MemGoldchangeService memGoldchangeService;
    @Resource
    private SysThreePaysetService sysThreePaysetService;
    @Resource
    private SysPayproviderService sysPayproviderService;


    @Override
    public DepositsReports getDepositByKefu(String startTime, String endTime) {
        logger.info("DepositReportServiceImpl.getDepositByCompany:{}", startTime + ";" + endTime);
        DepositsReports depositsReports = new DepositsReports();
        DepositStatisticsDO sumDistinct = memGoldchangeService.selectSumPeople(startTime, endTime, GoldchangeEnum.MANUALLY_INCOME_MONEY.getValue());
        depositsReports.setDepositsType(Constants.ARTIFICIAL);
        depositsReports.setNumPeople(sumDistinct.getAllRenci() == null ? "0" : sumDistinct.getAllRenci());
        depositsReports.setSumDeposits(sumDistinct.getMoney() == null ? "0" : sumDistinct.getMoney());
        depositsReports.setNumSuccess(Constants.DEPOSITS_NULL);
        depositsReports.setSuccessRatio(Constants.DEPOSITS_NULL);
        depositsReports.setNumFail(Constants.DEPOSITS_NULL);
        return depositsReports;
    }

    @Override
    public DepositsReports getDepositByCompany(String startTime, String endTime) {
        logger.info("DepositReportServiceImpl.getDepositByCompany:{}", JSONObject.toJSONString(null));
        DepositsReports depositsReports = new DepositsReports();
        List<DepositStatisticsDO> list = traOrderinfomService.selectNumPeopleCompany(startTime, endTime, Constants.ORDERTYPE2);
        DepositStatisticsDO sumDistinct = traOrderinfomService.selectSumPeople(startTime, endTime, Constants.ORDERTYPE2);

        String failRenci13 = null;
        String failRenci09 = null;
        String waitRenci = null;
        String successMoney = null;
        String successRenci = null;
        for (DepositStatisticsDO depositStatisticsDO : list) {
            switch (depositStatisticsDO.getStatus()) {
                case Constants.ORDER_ORD04:
                    break;
                case Constants.ORDER_ORD13:
                    failRenci13 = depositStatisticsDO.getRenci() == null ? "0" : depositStatisticsDO.getRenci();
                    break;
                case Constants.ORDER_ORD09:
                    failRenci09 = depositStatisticsDO.getRenci() == null ? "0" : depositStatisticsDO.getRenci();
                    break;
                case Constants.ORDER_ORD08:
                    successMoney = depositStatisticsDO.getMoney() == null ? "0" : depositStatisticsDO.getMoney();
                    successRenci = depositStatisticsDO.getRenci() == null ? "0" : depositStatisticsDO.getRenci();
                    break;
                default:
                    logger.info("DepositReportServiceImpl.getDepositByCompany.depositStatisticsDO异常:{}", JSONObject.toJSONString(depositStatisticsDO.getStatus()));
                    break;
            }
        }
        depositsReports.setDepositsType(Constants.COMPANY);
        depositsReports.setSumDeposits(successMoney == null ? "0" : successMoney);
        depositsReports.setNumPeople(sumDistinct.getAllRenci() == null ? "0" : sumDistinct.getAllRenci());
        depositsReports.setNumFail(MathUtil.sum(failRenci09, failRenci13));
        depositsReports.setSuccessRatio(MathUtil.StringtoBaiFen(successRenci == null ? "0" : successRenci, MathUtil.sum(successRenci, failRenci09, failRenci13)));
        depositsReports.setNumSuccess(successRenci == null ? "0" : successRenci);
        return depositsReports;
    }

    @Override
    public DepositsReports getDepositByThirdOnline(String startTime, String endTime) {
        List<DepositStatisticsDO> list = traOrderinfomService.selectNumPeopleCompany(startTime, endTime, Constants.ORDERTYPE1);
        DepositStatisticsDO sumDistinct = traOrderinfomService.selectSumPeople(startTime, endTime, Constants.ORDERTYPE1);
        logger.info("DepositReportServiceImpl.getDepositByThirdOnline:{}", JSONObject.toJSONString(list));

        String failRenci = "0";
        String WaitRenci = null;
        String successMoney = null;
        String successRenci = null;
        for (DepositStatisticsDO depositStatisticsDO : list) {
            switch (depositStatisticsDO.getStatus()) {
                case Constants.ORDER_ORD04:
                    WaitRenci = depositStatisticsDO.getRenci() == null ? "0" : depositStatisticsDO.getRenci();
                    break;
                case Constants.ORDER_ORD08:
                    successMoney = depositStatisticsDO.getMoney() == null ? "0" : depositStatisticsDO.getMoney();
                    successRenci = depositStatisticsDO.getRenci() == null ? "0" : depositStatisticsDO.getRenci();
                    break;
                default:
                    logger.info("DepositReportServiceImpl.depositStatisticsDO:{}", JSONObject.toJSONString(depositStatisticsDO.getStatus()));
                    break;
            }
        }
        DepositsReports depositsReports = new DepositsReports();
        depositsReports.setDepositsType(Constants.THIRDONLINE);
        // 失败默认给0
        depositsReports.setNumFail(failRenci);
        depositsReports.setNumSuccess(successRenci == null ? "0" : successRenci);
        depositsReports.setNumPeople(sumDistinct.getAllRenci() == null ? "0" : sumDistinct.getAllRenci());
        depositsReports.setSumDeposits(successMoney == null ? "0" : successMoney);
        String sum = MathUtil.sum(failRenci, successRenci);
        depositsReports.setSuccessRatio(MathUtil.StringtoBaiFen(successRenci, sum));
        return depositsReports;
    }

    @Override
    public DepositsReports getDepositByPayFor(String startTime, String endTime) {
        logger.info("DepositReportServiceImpl.getDepositByPayFor:{}", startTime + ";" + endTime);
        DepositsReports depositsReports = new DepositsReports();
        DepositStatisticsDO sumDistinct = traOrderinfomService.getPayFor(startTime, endTime);
        depositsReports.setDepositsType(Constants.PAYFOR);
        depositsReports.setNumPeople(sumDistinct.getAllRenci() == null ? "0" : sumDistinct.getAllRenci());
        depositsReports.setSumDeposits(sumDistinct.getMoney() == null ? "0" : sumDistinct.getMoney());
        depositsReports.setNumSuccess(Constants.DEPOSITS_NULL);
        depositsReports.setSuccessRatio(Constants.DEPOSITS_NULL);
        depositsReports.setNumFail(Constants.DEPOSITS_NULL);
        return depositsReports;
    }


    @Override
    public List<ThirdProvider> getDepositReportThird(String startTime, String endTime, String provider) {
        List<ThirdProvider> listProvider = new ArrayList<>();
        List<Long> ids = null;
        if (StringUtils.isNotEmpty(provider)) {
            Long providerId = sysPayproviderService.getProviderId(provider);
            if (null != providerId) {
                ids = sysThreePaysetService.getAllids(providerId.toString());
            } else {
                return null;
            }

        }
        List<ThirdProviderDO> listSuccess = traOrderinfomService.getDepositReportThird(startTime, endTime, ids);
        List<ThirdProviderDO> listFail = traOrderinfomService.getDepositReportFailThird(startTime, endTime, ids);
        List<ProviderSetDO> listPay = sysThreePaysetService.getAllsetAndProvider();
//        List<ThirdProviderDO> listPeople = traOrderinfomService.getallPeople(startTime, endTime, ids);
//        Map<Long, ThirdProviderDO> mapPeople = new HashMap<>();
//        for (ThirdProviderDO thirdProviderDO : listPeople) {
//            mapPeople.put(thirdProviderDO.getTpaysetid(), thirdProviderDO);
//        }
        String failRenci = "0";
        Map<Long, ProviderSetDO> map = new HashMap<>();
        for (ProviderSetDO providerSetDO : listPay) {
            map.put(providerSetDO.getTpaysetid(), providerSetDO);
        }

        List<ThirdProviderDO> listSuccessTwo = new ArrayList<>();
        for (ThirdProviderDO thirdProviderSuccess : listSuccess) {
            for (ThirdProviderDO thirdProviderFail : listFail) {
                if (thirdProviderFail.getTpaysetid().equals(thirdProviderSuccess.getTpaysetid())) {
                    thirdProviderSuccess.setFailNum(failRenci);
                    listFail.remove(thirdProviderFail);
                    break;
                }
            }
            listSuccessTwo.add(thirdProviderSuccess);
        }
        if (listFail.size() > 0) {
            for (ThirdProviderDO thirdProviderDO : listFail) {
                ThirdProviderDO thirdProviderDO1 = new ThirdProviderDO();
                thirdProviderDO1.setFailNum(failRenci);
                thirdProviderDO1.setTpaysetid(thirdProviderDO.getTpaysetid());
//                thirdProviderDO1.setPeopleNum(thirdProviderDO.getSuccessNum() == null ? "0" : thirdProviderDO.getSuccessNum());
                listSuccessTwo.add(thirdProviderDO1);
            }
        }
        ThirdProvider caculate = new ThirdProvider();
        for (ThirdProviderDO thirdProviderDO : listSuccessTwo) {
            ThirdProvider thirdProvider = new ThirdProvider();
            thirdProvider.setDepositSum(new BigDecimal(thirdProviderDO.getSumamtSum() == null ? "0" : thirdProviderDO.getSumamtSum()));
            thirdProvider.setFailNum(new BigDecimal(thirdProviderDO.getFailNum() == null ? "0" : thirdProviderDO.getFailNum()));
            thirdProvider.setSuccessNum(new BigDecimal(thirdProviderDO.getSuccessNum() == null ? "0" : thirdProviderDO.getSuccessNum()));
            thirdProvider.setNumPeople(new BigDecimal(thirdProviderDO.getPeopleNum() == null ? "0" : thirdProviderDO.getPeopleNum()));
            thirdProvider.setTpaysetid(thirdProviderDO.getTpaysetid());
            thirdProvider.setSuccessRatio(MathUtil.StringtoBaiFen(thirdProviderDO.getSuccessNum() == null ? "0" : thirdProviderDO.getSuccessNum(), MathUtil.sum(thirdProviderDO.getFailNum(), thirdProviderDO.getSuccessNum())));
            thirdProvider.setProviderName(map.get(thirdProvider.getTpaysetid()).getProvider());
            thirdProvider.setThirddivideName(map.get(thirdProvider.getTpaysetid()).getTpayname());
            thirdProvider.setWayType(map.get(thirdProvider.getTpaysetid()).getPaytype());
            listProvider.add(thirdProvider);
        }
        return listProvider;
    }

}
