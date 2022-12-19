package com.likes.modules.admin.finance.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.EntryOrderReq;
import com.likes.common.model.request.OrderReq;
import com.likes.common.model.response.EntryOnLineOrderExcelResponse;
import com.likes.common.model.response.EntryOrderExcelResponse;
import com.likes.common.model.response.EntryOrderTotalResponse;
import com.likes.common.model.response.StatisticsOnLineOrderResponse;

import java.util.List;
import java.util.Map;

public interface RechargeOrderService {

    Map<String, Object> getRechargeOrderDetail(OrderReq req, LoginUser loginUser);

    PageResult orderList(EntryOrderReq req, PageBounds page);

    Map<String, Object> getOrderDetail(EntryOrderReq req);

    List<EntryOrderExcelResponse> orderList(EntryOrderReq req);

    EntryOrderTotalResponse orderListTotal(EntryOrderReq req);

    Map<String, Object> orderListReuslt(EntryOrderReq req, PageBounds page);

    PageResult onlineOrderList(EntryOrderReq req, PageBounds page);

    StatisticsOnLineOrderResponse getStatisticsOnLineOrder(EntryOrderReq req);

    Map<String, Object> getOnlineOrderDetail(EntryOrderReq req);

    String doRechargeOrderV2(OrderReq req, LoginUser loginUser);

    Map<String, Object> getMandatoryOrderDetail(EntryOrderReq req);

    String doMandatoryOrder(EntryOrderReq req, LoginUser loginUser);

    List<EntryOnLineOrderExcelResponse> onlineOrderList(EntryOrderReq req);

    void doDayExportOrder();

}
