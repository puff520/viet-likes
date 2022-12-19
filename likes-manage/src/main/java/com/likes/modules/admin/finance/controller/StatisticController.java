package com.likes.modules.admin.finance.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.StatisticRequest;
import com.likes.modules.admin.finance.service.StatisticService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台统计
 */
@Controller
@RequestMapping("/statistic")
public class StatisticController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticController.class);

    @Resource
    private StatisticService statisticService;

    @ResponseBody
    @RequestMapping(name = "帐变列表", value = "/pageGoldChange.json", method = RequestMethod.POST)
    public ResultInfo pageGoldChange(StatisticRequest para, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("pageResult", statisticService.pageGoldChange(para, page));
            result.put("tatolAmoutBigDecimal", statisticService.tatolGoldChange(para));
            result.put("goldChangeType", statisticService.listChangeType());
            response.setData(result);
        } catch (Exception e) {
            response = ResultInfo.error("帐变列表出错");
            logger.error("{}.pageGoldChange,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(para), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "出入账目汇总", value = "/accountSummary.json", method = RequestMethod.POST)
    public ResultInfo accountSummary(StatisticRequest para) {
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(statisticService.getAccountSummary(para));
        } catch (Exception e) {
            response = ResultInfo.error("出入账目汇总出错");
            logger.error("{}.accountSummary,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(para), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "会员报表", value = "/userReport.json", method = RequestMethod.POST)
    public ResultInfo userReport(String nickname, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(PageResult.getPageResult(page, statisticService.userReport(nickname, page.toRowBounds())));
        } catch (Exception e) {
            response = ResultInfo.error("会员报表出错");
            logger.error("{}.userReport,会员报表出错:{},params:{}", getClass().getName(), e.getMessage(), "nickname=" + nickname, e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "会员报表-资金明细列表", value = "/userReportDetail.json", method = RequestMethod.POST)
    public ResultInfo userReportDetail(StatisticRequest req, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isBlank(req.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "accno为空");
            }
            //不分页-保留分页功能(防止后面又要分页)
            page.setPageNo(1);
            page.setPageSize(100000);
            response.setData(statisticService.userReportDetail(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.userReportDetail,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("会员报表-资金明细列表出错");
            logger.error("{}.userReportDetail,会员报表-资金明细列表出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }
}
