package com.likes.modules.agent.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.Page;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.AgentData;
import com.likes.common.model.dto.AgentMemberDTO;
import com.likes.common.model.dto.PromotionDo;
import com.likes.common.model.dto.member.FundsResponse;
import com.likes.common.model.request.*;
import com.likes.common.model.response.AppTeamResponse;
import com.likes.common.model.response.TeamBo;
import com.likes.common.model.response.TeamResponse;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemLogin;
import com.likes.common.mybatis.entity.Task;
import com.likes.common.mybatis.mapper.AgentMapper;
import com.likes.common.mybatis.mapper.MemBaseinfoMapper;
import com.likes.common.mybatis.mapperext.member.MemLevelConfigMapperExt;
import com.likes.common.mybatis.mapperext.member.MemLevelMapperExt;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.DateUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.agent.service.AgentMemberService;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author abu
 * @version 1.0
 * @Description
 * @revise
 * @time 2018年6月8日 下午3:24:55
 */
@Service
public class AgentMemberServiceImpl extends BaseServiceImpl implements AgentMemberService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MemLoginService memLoginService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemBaseinfoMapper memBaseinfoMapper;
    @Resource
    private AgentMapper agentMapper;

    @Override
    @DS("slave")
    public PageResult subList(AgentMemberOrderReq req, PageBounds page) {
        logger.info("updatepuff");
        String accLogin = req.getAcclogin();
        if (StringUtils.isNotBlank(req.getEmail()) || StringUtils.isNotBlank(req.getAcclogin()) || StringUtils.isNotBlank(req.getSuprecomcode()) || StringUtils.isNotBlank(req.getRegisterip())) {
            req.setAccno(null);
        }
        Page<AgentMemberDTO> list = new Page<>();
        if (StringUtils.isNotBlank(req.getBeginTime())) {
            req.setBeginTime(req.getBeginTime() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndTime())) {
            req.setEndTime(req.getEndTime() + " 23:59:59");

        }
        if (StringUtils.isNotBlank(req.getEmail())) {
            MemLogin memLogin = memLoginService.findByAccloginRegister(req.getEmail());
            req.setAccno(memLogin.getAccno());
        }
        if (StringUtils.isNotBlank(req.getAcclogin()) && !req.getAcclogin().equals("null")) {
            req.setAcclogin(accLogin);
        }

        list = agentMapper.memList(req, page.toRowBounds());
        list.forEach(item -> {
            String token = RedisBusinessUtil.get("Login_info:" + item.getAccno());
            if (null == token) {
                item.setOnlinestatus(0);
            } else {
                item.setOnlinestatus(1);
            }
        });
        return PageResult.getPageResult(page, list);
    }

    @Override
    @DS("slave")
    public AgentData dataList(AgentOrderReq req, PageBounds page) {
        if (StringUtils.isNotBlank(req.getBeginTime())) {
            req.setBeginTime(req.getBeginTime() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndTime())) {
            req.setEndTime(req.getEndTime() + " 23:59:59");

        }
        Map<String, Object> recMap = new HashMap<>();
        Map<String, Object> cashMap = new HashMap<>();
        AgentData agentData = new AgentData();
        List<String> addMemList = agentMapper.teamAddMem(req.getAccno(), req.getBeginTime(), req.getEndTime());
        Integer vipNum = agentMapper.levelCount(req.getAccno(), req.getBeginTime(), req.getEndTime());
        Integer taskNum = agentMapper.teamTaskNum(req.getAccno(), req.getBeginTime(), req.getEndTime());
        List<Map<String, Object>> orderList = agentMapper.subTimeOrderListByAccno(req.getAccno(), req.getBeginTime(), req.getEndTime());
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

        String dateStr = DateUtils.formatDate(new Date(),DateUtils.FORMAT_YYYY_MM_DD);
        Integer integer =   agentMapper.memTodayTaskNum(req.getAccno(),dateStr);
        agentData.setMemTodayTaskNum(integer);
        agentData.setMemNum(addMemList.size());
        agentData.setVipNum(vipNum);
        agentData.setTaskNum(taskNum);

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
    @DS("slave")
    public PageResult fundsList(FundsRequest req, PageBounds page) {
        if (StringUtils.isNotBlank(req.getBeginTime())) {
            req.setBeginTime(req.getBeginTime() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndTime())) {
            req.setEndTime(req.getEndTime() + " 23:59:59");
        }
        Page<FundsResponse> responseList = agentMapper.fundsList(req, page.toRowBounds());
        return PageResult.getPageResult(page, responseList);
    }

    @Override
    @DS("slave")
    public PageResult teamList(TeamRequest req, PageBounds page) {
        if (StringUtils.isNotBlank(req.getBeginTime())) {
            req.setBeginTime(req.getBeginTime() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndTime())) {
            req.setEndTime(req.getEndTime() + " 23:59:59");
        }

        String dateStr = DateUtils.formatDate(new Date(),DateUtils.FORMAT_YYYY_MM_DD);
        Page<TeamResponse> teamPage = agentMapper.teamList(req, page.toRowBounds());
        teamPage.forEach(item -> {
            Integer subNum = agentMapper.subNum2(item.getAccno());
            if (subNum > 0) {
                List<String> todayList = agentMapper.todayAddSubNum(item.getAccno(), req.getBeginTime(), req.getEndTime());
                Integer addMemTaskNum = 0;
                if (CollectionUtil.isNotEmpty(todayList)) {
                    addMemTaskNum = agentMapper.todayTaskNum(todayList, req.getBeginTime(), req.getEndTime());
                }
                Integer todaySubmit = agentMapper.todaySubmitTaskNum(item.getAccno(),dateStr);
                item.setSubNum(subNum);
                item.setAddTaskNum(addMemTaskNum);
                item.setAddMemNum(todayList.size());

                item.setTodayTaskNum(todaySubmit);
            } else {
                item.setSubNum(0);
                item.setAddTaskNum(0);
                item.setAddMemNum(0);
                item.setTodayTaskNum(0);
            }
        });
        return PageResult.getPageResult(page, teamPage);
    }

//    @Override
//    public TeamResponse team2List(TeamRequest req, PageBounds page) {
//        if (StringUtils.isNotBlank(req.getBeginTime())) {
//            req.setBeginTime(req.getBeginTime() + " 00:00:00");
//        }
//        if (StringUtils.isNotBlank(req.getEndTime())) {
//            req.setEndTime(req.getEndTime() + " 23:59:59");
//        }
//        TeamResponse teamResponse = new TeamResponse();
//        req.setBeginTime("2020-01-11 00:00:00");
//        req.setEndTime("2050-01-11 23:59:59");
//        Integer addMemList = agentMapper.teamAddMem(req.getAccno(), req.getBeginTime(), req.getEndTime());
//        Integer taskList = agentMapper.teamTaskNum(req.getAccno(), req.getBeginTime(), req.getEndTime());
//        Integer todayList = agentMapper.teamTodayNum(req.getAccno());
//        teamResponse.setAddMemNum(addMemList);
//        teamResponse.setAddTaskNum(taskList);
//        teamResponse.setTodayTaskNum(todayList);
//        return teamResponse;
//    }

    @Override
    @DS("slave")
    public List<PromotionDo> agentTree(String accno) {
        return agentMapper.agentTree(accno);
    }


    @Override
    @DS("slave")
    public String getRecomcode(LoginUser loginUser) {
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(loginUser.getAccno());
        return memBaseinfo.getRecomcode();
    }

    public void check3LevelAgent(String accLogin) {
        if (StringUtils.isNotBlank(accLogin)) {
            MemLogin memLogin = memLoginService.findByAccloginRegister(accLogin);
            if (ObjectUtil.isNotNull(memLogin)) {
                List<String> allList = new LinkedList<>();
                List<String> levelOneAccList = agentMapper.subNumNotTime(Arrays.asList(memLogin.getAccno()));
                List<String> levelTwoAccList = agentMapper.subNumNotTime(levelOneAccList);
                List<String> levelThreeAccList = agentMapper.subNumNotTime(levelTwoAccList);
                allList.addAll(levelOneAccList);
                allList.addAll(levelTwoAccList);
                allList.addAll(levelThreeAccList);
                if (CollectionUtil.isNotEmpty(allList) && allList.contains(memLogin.getAccno())) {

                } else {
                    throw new BusinessException("此账号不是你的三级代理");
                }
            }
            throw new BusinessException("找不到该账号");
        }
    }


    public List<String> getTeamAccNoList(String accno, String beginTime, String endTime) {
        List<String> allList = new LinkedList<>();
        List<String> levelOneAccList = agentMapper.subNum(Arrays.asList(accno), beginTime, endTime);
        List<String> levelTwoAccList = new LinkedList<>();
        List<String> levelThreeAccList = new LinkedList<>();
        if (CollectionUtil.isNotEmpty(levelOneAccList)) {
            levelTwoAccList = agentMapper.subNum(levelOneAccList, beginTime, endTime);
            if (CollectionUtil.isNotEmpty(levelTwoAccList)) {
                levelThreeAccList = agentMapper.subNum(levelTwoAccList, beginTime, endTime);
            }
        }
        allList.addAll(levelOneAccList);
        allList.addAll(levelTwoAccList);
        allList.addAll(levelThreeAccList);
        return allList;
    }

}
