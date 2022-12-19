package com.likes.modules.admin.finance.service;


import com.likes.common.model.AgentAccountDetailDO;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.AgentAccountRequest;

public interface AgentAccountService {

    Long createAccount(AgentAccountRequest request, LoginUser loginUser);

    int deleteAccount(Long bankid, LoginUser loginUser);

    int updateAccount(AgentAccountRequest request, LoginUser loginUser);

    int UpdateAccountStatus(Long bankid, Integer status, LoginUser loginUser);

    AgentAccountDetailDO accountDetail(Long bankid);

    PageResult accountList(String nickname, PageBounds page);
}
