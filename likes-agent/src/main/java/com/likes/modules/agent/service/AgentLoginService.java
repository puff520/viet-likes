package com.likes.modules.agent.service;

import com.likes.common.model.request.UsersRequest;
import com.likes.common.service.BaseService;
import com.likes.common.model.LoginUser;

/**
 * @author abu
 *
 */
public interface AgentLoginService extends BaseService {

    /**
     * APP 登陆
     */
    LoginUser login(UsersRequest req, String source);

}
