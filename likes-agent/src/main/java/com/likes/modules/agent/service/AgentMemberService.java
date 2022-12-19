package com.likes.modules.agent.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.AgentData;
import com.likes.common.model.dto.PromotionDo;
import com.likes.common.model.request.AgentMemberOrderReq;
import com.likes.common.model.request.AgentOrderReq;
import com.likes.common.model.request.FundsRequest;
import com.likes.common.model.request.TeamRequest;
import com.likes.common.service.BaseService;

import java.util.List;

/**
 * @author abu
 *
 */
public interface AgentMemberService extends BaseService {

    /**
     * APP 登陆
     */
    PageResult subList(AgentMemberOrderReq req, PageBounds page);

    AgentData dataList(AgentOrderReq req, PageBounds page);

    PageResult fundsList(FundsRequest req, PageBounds page);

    PageResult teamList(TeamRequest req, PageBounds page);

    List<PromotionDo> agentTree(String accno);

    String getRecomcode(LoginUser loginUser);


}
