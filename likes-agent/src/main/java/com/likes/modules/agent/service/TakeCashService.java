package com.likes.modules.agent.service;

import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.AgentOrderReq;
import com.likes.common.service.BaseService;

/**
 * @author abu
 */
public interface TakeCashService extends BaseService {

    /**
     * APP 登陆
     */
    PageResult cashList(AgentOrderReq req, PageBounds page);

}
