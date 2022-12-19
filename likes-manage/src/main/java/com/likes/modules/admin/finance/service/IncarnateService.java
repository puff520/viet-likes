package com.likes.modules.admin.finance.service;

import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.IncarnateOrderReq;
import com.likes.common.model.response.EntryIncarnateOrderExcelResponse;

import java.util.List;
import java.util.Map;


public interface IncarnateService {

    /**
     * 主播提现列表
     *
     * @param req
     * @param page
     * @param loginAdmin
     * @return
     */
    /*PageResult incarnateOrderList(IncarnateOrderReq req, PageBounds page, LoginUser loginAdmin);*/

    /**
     * 确认订单
     *
     * @param req
     * @param loginAdmin
     * @return
     */
    String sureOrder(IncarnateOrderReq req, LoginUser loginAdmin);

    /**
     * 取消确认订单
     *
     * @param req
     * @param loginAdmin
     * @return
     */
    String cancelSureIncarnateOrder(IncarnateOrderReq req, LoginUser loginAdmin);

    /**
     * 已经提现完成的订单详细
     *
     * @param req
     * @param loginAdmin
     * @return
     */
    Map<String, Object> incarnateOrderDetail(IncarnateOrderReq req, LoginUser loginAdmin);

    /**
     * 待提现的订单详细
     *
     * @param req
     * @param loginAdmin
     * @return
     */
    Map<String, Object> incarnateHandleOrderDetail(IncarnateOrderReq req, LoginUser loginAdmin);


    //-----------------------------------------------------------------------------------------------------------

    /**
     * 用户提现列表
     *
     * @param req
     * @param page
     * @param loginAdmin
     * @return
     */
    Map<String, Object> incarnateOrderListV2(IncarnateOrderReq req, PageBounds page, LoginUser loginAdmin);

    /**
     * 用户提现列表数据导出
     *
     * @param req
     * @return
     */
    List<EntryIncarnateOrderExcelResponse> incarnateOrderListV2Excel(IncarnateOrderReq req);


    /**
     * 用户提现
     *
     * @param req
     * @param loginAdmin
     * @return
     */
    String incarnateConfirmV2(IncarnateOrderReq req, LoginUser loginAdmin);


    boolean subUdun(IncarnateOrderReq req, LoginUser loginAdmin);



}
