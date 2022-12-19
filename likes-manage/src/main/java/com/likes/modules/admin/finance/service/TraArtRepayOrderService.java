package com.likes.modules.admin.finance.service;


import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.TraArtRepayOrderQuery;
import com.likes.common.model.request.TraArtRepayOrderRequest;

public interface TraArtRepayOrderService {

    /**
     * 代充存入&提出
     *
     * @param request   请求参数
     * @param loginUser 登录用户
     * @return id
     */
    long agentArtRepayOrder(TraArtRepayOrderRequest request, LoginUser loginUser);

    /**
     * 代充存提列表
     *
     * @param query 查询条件
     * @param page  分页
     * @return List
     */
    PageResult agentArtRepayOrderList(TraArtRepayOrderQuery query, PageBounds page);

}
