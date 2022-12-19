package com.likes.modules.admin.users.controller;

import com.likes.common.BaseController;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.EntryOrderReq;
import com.likes.common.model.request.IncarnateOrderReq;
import com.likes.common.util.LogUtils;
import com.likes.modules.admin.users.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/memberorder")
public class OrderController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private OrderService orderService;

    @ResponseBody

    @RequestMapping(name = "app充值记录", value = "/rechargeOrderList", method = RequestMethod.GET)
    public ResultInfo orderList(EntryOrderReq req, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            req.setAccno(loginUserAPP.getAccno());
            response.setData(orderService.appOrderList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.orderList入款订单管理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("入款订单管理失败");
            logger.error("{}.orderList入款订单管理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/orderList耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "app提现记录", value = "/withdrawalOrderList", method = RequestMethod.GET)
    public ResultInfo withdrawalOrderList(IncarnateOrderReq req, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            req.setAccno(loginUserAPP.getAccno());
            response.setData(orderService.incarnateOrderListV2(req, page));
            LogUtils.logUserModifyOperates(getClass().getName() + ".orderListV2", req, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}/v2/orderList出帐订单管理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("出帐订单管理失败");
            logger.error("{}/v2/orderList出帐订单管理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/v2/orderList耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }
}
