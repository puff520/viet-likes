package com.likes.modules.admin.finance.service;


import com.likes.common.model.AgentTransactionDetailDO;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.AgentTransactionQuery;
import com.likes.common.model.request.AgentTransactionRequest;

public interface AgentTransactionService {

    PageResult rechargeList(AgentTransactionQuery query, PageBounds page);

    AgentTransactionDetailDO rechargeDetail(Long orderId);

    Integer updateRecharge(AgentTransactionRequest request, LoginUser loginUser);
}
