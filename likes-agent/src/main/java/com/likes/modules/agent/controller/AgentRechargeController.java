package com.likes.modules.agent.controller;

import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.AgentOrderReq;
import com.likes.modules.agent.service.AgentRechargeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author abu
 * @version 1.0
 * @Description 运营登录
 * @revise
 * @time 2018年6月8日 下午3:25:23
 */
@RestController
@RequestMapping(value = "/agent/recharge")
public class AgentRechargeController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private AgentRechargeService rechargeService;


    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "线上入款订单管理", value = "/orderList", method = RequestMethod.POST)
    public ResultInfo onlineOrderList(AgentOrderReq req, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(rechargeService.rechargeList(req,page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.onlineOrderList线上入款订单管理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("线上入款订单管理失败");
            logger.error("{}.onlineOrderList线上入款订单管理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/onlineOrderList耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


}
