package com.likes.modules.admin.job;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.util.CollectionUtils;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.model.MemDailyReport;
import com.likes.common.mybatis.entity.MemRelationship;
import com.likes.common.mybatis.mapper.MemDailyReportMapper;
import com.likes.common.mybatis.mapperext.member.MemGoldchangeMapperExt;
import com.likes.common.service.member.MemRelationshipService;
import com.likes.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Component
public class EveryDayEarningsTask {

    @Resource
    private MemGoldchangeMapperExt memGoldchangeMapperExt;
    @Resource
    private MemDailyReportMapper memDailyReportMapper;
    @Resource
    private MemRelationshipService memRelationshipService;

    Logger log = LoggerFactory.getLogger(DailyReportTask.class);

    @Scheduled(cron = "0 38 0 * * ? ")
    public void dailyTask() {
        try {
            String beginTime = DateUtils.dayBeforeStratStr();
            String enDTime = DateUtils.timeBeforeEndStr();
            List<String> accNoList = memGoldchangeMapperExt.getYesterdayGoldAccno(beginTime, enDTime);
            int totalSize = accNoList.size();
            int pageSize = 200;
            int totalPage = (totalSize + pageSize - 1) / pageSize;
            for (int pageNo = 1; pageNo <= totalSize; pageNo++) {
                if (pageNo > totalPage) {
                    break;
                }
                Integer page = (pageNo - 1) * pageSize;
                chuli(beginTime, enDTime, page, pageSize);
            }
        } catch (Exception e) {
            log.error("执行定时任务错误 ：============》》 {}", e);
        }

    }


    private void chuli(String beginTime, String enDTime, Integer page, Integer limit) {
        List<MemDailyReport> addList = new LinkedList<>();
        List<String> accNoList = memGoldchangeMapperExt.getYesterdayGoldPage(beginTime, enDTime, page, limit);
        accNoList.forEach(accno -> {
            List<Map<String, Object>> list = memGoldchangeMapperExt.getYesterdayQuantityByChangetype(accno, beginTime, enDTime);
            if (CollectionUtils.isEmpty(list)) {
                return;
            }
            MemDailyReport memDailyReport = new MemDailyReport();
            BigDecimal recharge = new BigDecimal(0L);
            BigDecimal withdrawn = new BigDecimal(0L);
            BigDecimal buyVip = new BigDecimal(0L);
            BigDecimal taskAward = new BigDecimal(0L);
            BigDecimal taskBrokerage = new BigDecimal(0L);
            BigDecimal vipBrokerage = new BigDecimal(0L);

            for (Map<String, Object> paramMap : list) {
                GoldchangeEnum goldchangeEnum = GoldchangeEnum.valueOf(Integer.parseInt(paramMap.get("changetype").toString()));
                if (null == goldchangeEnum) {
                    return;
                }
                switch (goldchangeEnum) {
                    case RECHARGE:
                    case MANUALLY_INCOME_MONEY:
                        recharge = recharge.add(new BigDecimal(paramMap.get("quantity").toString()));
                        break;
                    case WITHDRAWN:
                        withdrawn = new BigDecimal(paramMap.get("quantity").toString());
                        break;
                    case TASK_AWARD:
                        taskAward = new BigDecimal(paramMap.get("quantity").toString());
                        break;
                    case BUY_VIP:
                        buyVip = new BigDecimal(paramMap.get("quantity").toString());
                        break;
                    case BROKERAGE_LEVEL_1:
                    case BROKERAGE_LEVEL_2:
                    case BROKERAGE_LEVEL_3:
                        taskBrokerage = taskBrokerage.add(new BigDecimal(paramMap.get("quantity").toString()));
                        break;
                    case BUVIP_LEVEL_1:
                    case BUVIP_LEVEL_2:
                    case BUVIP_LEVEL_3:
                        vipBrokerage = vipBrokerage.add(new BigDecimal(paramMap.get("quantity").toString()));
                        break;
                }
            }
            memDailyReport.setRecharge(recharge);
            memDailyReport.setTaskBrokerage(taskBrokerage);
            memDailyReport.setVipBrokerage(vipBrokerage);
            memDailyReport.setTaskAward(taskAward);
            memDailyReport.setWithdrawal(withdrawn.abs());
            memDailyReport.setBuyVip(buyVip.abs());
            memDailyReport.setAccno(accno);
            //上级代理
            MemRelationship current = memRelationshipService.findByAccno(accno);
            if (ObjectUtil.isNotNull(current)) {
                MemRelationship super1Mem = memRelationshipService.findByAccno(current.getRefaccno());
                if (ObjectUtil.isNotNull(super1Mem)) {
                    memDailyReport.setTop1Accno(super1Mem.getAccno());
                    MemRelationship super2Mem = memRelationshipService.findByAccno(super1Mem.getRefaccno());
                    if (ObjectUtil.isNotNull(super2Mem)) {
                        memDailyReport.setTop2Accno(super2Mem.getAccno());
                        MemRelationship super3Mem = memRelationshipService.findByAccno(super2Mem.getRefaccno());
                        if (ObjectUtil.isNotNull(super3Mem)) {
                            memDailyReport.setTop3Accno(super3Mem.getAccno());
                        }
                    }
                }
            }
            memDailyReport.setCreateTime(DateUtils.str2date(beginTime));
            memDailyReport.setDateStr(beginTime.substring(0, 10));
            addList.add(memDailyReport);
        });
        memDailyReportMapper.insertBatch(addList);
    }

}
