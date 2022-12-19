package com.likes.modules.agent.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.Page;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.AgentOrderReq;
import com.likes.common.model.response.AgentReportResponse;
import com.likes.common.model.response.AgentTakeCashOrder;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.mapper.AgentMapper;
import com.likes.common.mybatis.mapper.AgentReportMapper;
import com.likes.common.mybatis.mapperext.member.MemLevelConfigMapperExt;
import com.likes.common.mybatis.mapperext.member.MemLevelMapperExt;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.CollectionUtil;
import com.likes.modules.agent.service.AgentReportService;
import com.likes.modules.agent.service.TakeCashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author abu
 * @version 1.0
 * @Description
 * @revise
 * @time 2018年6月8日 下午3:24:55
 */
@Service
public class AgentReportServiceImpl extends BaseServiceImpl implements AgentReportService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MemBaseinfoService memBaseinfoService;

    @Resource
    private AgentReportMapper agentReportMapper;


    @Override
    @DS("slave")
    public PageResult reportList(AgentOrderReq req, PageBounds page) {
        if (req.getBeginTime() != null && req.getBeginTime() != "") {
            req.setBeginTime(req.getBeginTime() + " 00:00:00");
        }
        if (req.getEndTime() != null && req.getEndTime() != "") {
            req.setEndTime(req.getEndTime() + " 23:59:59");
        }

        MemBaseinfo memBaseinfo = memBaseinfoService.selectByAccno(req.getAccno());

        Integer todayRegister = agentReportMapper.todayRegister(memBaseinfo.getSuperiorId(), req.getBeginTime(), req.getEndTime());
        Map<String, String> tjMap = agentReportMapper.amoutTj(memBaseinfo.getSuperiorId(), req.getBeginTime(), req.getEndTime());
        Map<String, Object> taskMap = agentReportMapper.taskTj(memBaseinfo.getSuperiorId(), req.getBeginTime(), req.getEndTime());
        AgentReportResponse response = new AgentReportResponse();
        response.setAddMem(todayRegister);
        if (CollectionUtil.isNotEmpty(tjMap)) {
            response.setBuyVipAmount(tjMap.get("buyVipAmount"));
            response.setRecAmount(tjMap.get("recAmount"));
            response.setTakeCashAmount(tjMap.get("takeCashAmount"));
        }

        if (CollectionUtil.isNotEmpty(taskMap)) {
            response.setTaskNum(Integer.parseInt(taskMap.get("taskNum").toString()));
            response.setTaskTotalPrice(taskMap.get("taskTotalPrice").toString());
        }


        List<AgentReportResponse> list = new LinkedList<>();
        list.add(response);
        return PageResult.getPageResult(page, list);
    }
}
