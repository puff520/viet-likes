package com.likes.modules.admin.agent.service;

import com.likes.common.model.SysRecord;
import com.likes.common.service.BaseService;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.SysAgentinfoReq;
import com.likes.common.mybatis.entity.SysAgentinfo;

import java.util.List;

/**
 * @author 阿布
 */
public interface AgentService extends BaseService {

    /**
     * 获取代理列表
     *
     * @param req
     * @param page
     * @return
     */
    PageResult getList(SysAgentinfoReq req, PageBounds page);


    /**
     * 新增/编辑代理
     *
     * @param req
     * @param user
     */
    void doEdt(SysAgentinfo req, LoginUser user) throws BusinessException;

    /**
     * 删除代理
     *
     * @param agentid
     * @param user
     * @throws BusinessException
     */
    void doDel(Long agentid, LoginUser user) throws BusinessException;
}
