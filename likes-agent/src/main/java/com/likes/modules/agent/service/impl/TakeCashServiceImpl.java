package com.likes.modules.agent.service.impl;


import com.github.pagehelper.Page;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.AgentOrderReq;
import com.likes.common.model.response.AgentTakeCashOrder;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemLogin;
import com.likes.common.mybatis.mapper.AgentMapper;
import com.likes.common.mybatis.mapperext.member.MemLevelConfigMapperExt;
import com.likes.common.mybatis.mapperext.member.MemLevelMapperExt;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.service.sys.SysParamService;
import com.likes.modules.agent.service.TakeCashService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author abu
 * @version 1.0
 * @Description
 * @revise
 * @time 2018年6月8日 下午3:24:55
 */
@Service
public class TakeCashServiceImpl extends BaseServiceImpl implements TakeCashService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MemLoginService memLoginService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemLevelMapperExt memLevelMapperExt;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private MemLevelConfigMapperExt memLevelConfigMapperExt;
    @Resource
    private TraOrderinfomService traOrderinfomMapperService;

    @Resource
    private AgentMapper agentMapper;


    @Override
    public PageResult cashList(AgentOrderReq req, PageBounds page) {
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
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(req.getAccno());
        req.setSuperiorId(memBaseinfo.getUniqueId());
        Page<AgentTakeCashOrder> list = traOrderinfomMapperService.agentTakeCashOrderList(req, page.toRowBounds());
        return PageResult.getPageResult(page, list);
    }

}
