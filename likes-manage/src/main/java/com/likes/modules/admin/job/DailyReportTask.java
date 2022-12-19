package com.likes.modules.admin.job;

import cn.hutool.core.util.ObjectUtil;
import com.likes.common.model.dto.AgentEveryData;
import com.likes.common.mybatis.entity.EveryDayReport;
import com.likes.common.mybatis.mapper.AgentAdminMapper;
import com.likes.common.mybatis.mapper.EveryDayReportMapper;
import com.likes.common.util.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class DailyReportTask {
    @Resource
    private AgentAdminMapper agentAdminMapper;
    @Resource
    private EveryDayReportMapper everyDayReportMapper;

    @Scheduled(cron = "0 30 0 * * ? ")
    public void dailyReportTask() {

//        String createDate = DateUtils.getDateString(new Date());
        String beginTime = DateUtils.dayBeforeStratStr();
        String endTime = DateUtils.timeBeforeEndStr();
        List<AgentEveryData> everyList = everyDayReportMapper.everyDayList(beginTime, endTime);
        everyList.forEach(item -> {
            EveryDayReport everyDayReport = new EveryDayReport();
            everyDayReport.setCreateDate(item.getDat());
            everyDayReport.setCreateTime(DateUtils.str2date(item.getDat(), DateUtils.FORMAT_YYYY_MM_DD));
            everyDayReport.setTaskAmount(item.getRw());
            everyDayReport.setRecharge(item.getRgcz().add(item.getCz()));
            everyDayReport.setWithdrawal(item.getTx().abs());
            everyDayReport.setVipAmount(item.getVip().abs());
            everyDayReport.setSubTaskAmount(item.getTask1().add(item.getTask2()).add(item.getTask3()));
            Integer vipNum = agentAdminMapper.addVipNum(item.getDat() + " 00:00:00", item.getDat() + " 23:59:59");
            everyDayReport.setAddVipNum(vipNum);
            EveryDayReport param = new EveryDayReport();
            param.setCreateDate(item.getDat());
            EveryDayReport result = everyDayReportMapper.selectOne(param);
            if (ObjectUtil.isNotNull(result)) {
                everyDayReport.setId(result.getId());
                everyDayReportMapper.updateByPrimaryKeySelective(everyDayReport);
            } else {
                everyDayReportMapper.insertSelective(everyDayReport);
            }
        });
    }


}
