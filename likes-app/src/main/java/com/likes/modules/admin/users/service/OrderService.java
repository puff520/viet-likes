package com.likes.modules.admin.users.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.EntryOrderReq;
import com.likes.common.model.request.IncarnateOrderReq;
import com.likes.common.model.response.EntryOrderTotalResponse;
import com.likes.common.model.response.StatisticsOnLineOrderResponse;

import java.util.Map;

public interface OrderService {


    PageResult appOrderList(EntryOrderReq req, PageBounds page);


    PageResult incarnateOrderListV2(IncarnateOrderReq req, PageBounds page);
}
