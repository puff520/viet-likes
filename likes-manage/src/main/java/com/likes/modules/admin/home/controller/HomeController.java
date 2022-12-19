package com.likes.modules.admin.home.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.annotation.HttpValidate;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.ResultInfo;
import com.likes.modules.admin.home.service.HomeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 首页
 *
 * @author bjkj
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController extends BaseController {

    private final static Logger logger = LogManager.getLogger(HomeController.class);

    @Resource
    private HomeService homeService;

    @ResponseBody
    @RequestMapping(name = "会员注册数量统计", value = "/getPeoples", method = RequestMethod.GET)
    public ResultInfo getPeoples() {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo();
        try {
            response = ResultInfo.ok(homeService.getPeoples());
            logger.info("{}.getPeoples会员注册数量统计成功", getClass().getName());
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.getPeoples会员注册数量统计失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("会员注册数量统计失败");
            logger.error("{}.getPeoples会员注册数量统计出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getPeoples耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "会员地理分布统计", value = "/getAddressDistributeds", method = RequestMethod.GET)
    public ResultInfo getAddressDistributeds() {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo();
        try {
            response = ResultInfo.ok(homeService.getAddressDistributeds());
            logger.info("{}.getAddressDistributeds会员地理分布统计成功!", getClass().getName());
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.getAddressDistributeds会员地理分布统计失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("会员地理分布统计失败");
            logger.error("{}.getAddressDistributeds会员地理分布统计出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getAddressDistributeds耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "会员等级分布统计", value = "/getLevelDistributeds", method = RequestMethod.GET)
    public ResultInfo getLevelDistributeds() {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo();
        try {
            response = ResultInfo.ok(homeService.getLevelDistributedsFive());
            logger.info("{}.getLevelDistributeds会员等级分布统计成功!", getClass().getName());
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.getLevelDistributeds会员等级分布统计失败，失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("会员等级分布统计出错");
            logger.error("{}.getLevelDistributeds会员等级分布统计出错，出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getLevelDistributeds耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }



    @ResponseBody
    @AllowAccess
    @RequestMapping(name = "实时在线人数", value = "/getCurrentOnlineNums", method = RequestMethod.GET)
    public ResultInfo getCurrentOnlineNums() {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo();
        try {
            response = ResultInfo.ok(homeService.getCurrentOnlineNums());
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.getCurrentOnlineNums实时在线人数统计失败，失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("实时在线人数统计失败");
            logger.error("{}.getCurrentOnlineNums实时在线人数统计失败，失败信息:{}", getClass().getName(), e.getMessage(), e);
        }

        logger.info("/getCurrentOnlineNums耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @AllowAccess
    @HttpValidate //http验签
    @ResponseBody
    @RequestMapping(name = "站点实时在线人数", value = "/pushSuperOnlineNum", method = RequestMethod.GET)
    public ResultInfo pushSuperOnlineNum() {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo();
        try {
            response = ResultInfo.ok(homeService.getCurrentOnlineNums());
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.pushSupperOnlineNum，失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("实时在线人数統計失败");
            logger.error("{}.pushSupperOnlineNum，失败信息:{}", getClass().getName(), e.getMessage(), e);
        }

        logger.info("/pushSupperOnlineNum耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @AllowAccess
    @RequestMapping(name = "首页统计", value = "/getAllStatics", method = RequestMethod.GET)
    public ResultInfo getAllStatics(String startTime, String endTime) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo();
        try {
            response.setData(homeService.manualHomeStatics(startTime, endTime));
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.getAllStatics首页统计失败，失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("首页统计失败");
            logger.error("{}.getAllStatics首页统计失败，失败信息:{}", getClass().getName(), e.getMessage(), e);
        }

        logger.info("/getAllStatics耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @AllowAccess
    @RequestMapping(name = "获取当日新订单", value = "/getlNewOrderNo", method = RequestMethod.GET)
    public ResultInfo getNewOrder(){
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo();
        try {
            response.setData(homeService.getNewOrder());
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.getlNewOrderNo，失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("首页统计失败");
            logger.error("{}.getlNewOrderNo，失败信息:{}", getClass().getName(), e.getMessage(), e);
        }

        logger.info("/getlNewOrderNo{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @AllowAccess
    @RequestMapping(name = "获取所有会员账户余额", value = "/getAllUserGoldNum", method = RequestMethod.GET)
    public ResultInfo getAllUserGoldNum() {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo();
        try {
            response.setData(homeService.getAllUserGoldNum());
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.getAllUserGoldNum统计失败，失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("首页统计失败");
            logger.error("{}.getAllUserGoldNum统计失败，失败信息:{}", getClass().getName(), e.getMessage(), e);
        }

        logger.info("/getAllUserGoldNum耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

}
