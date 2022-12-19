package com.likes.modules.admin.report.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.annotation.HttpValidate;
import com.likes.common.constant.SystemTips;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.report.ExpFundStatementDO;
import com.likes.common.model.dto.report.ExpFundsSummaryDO;
import com.likes.common.model.vo.report.ExpFundStatementVO;
import com.likes.common.util.BaseUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.report.service.ExpFundStatementService;
import com.likes.modules.admin.sys.service.PermissionService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;

/**
 * @Author xiaoming
 * @ClassName
 * @Description
 * @Date 8:43 下午 7/29/20
 **/
@RestController
@RequestMapping(value = "/expfunds")
public class ExpFundStatementController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ExpFundStatementService expFundstatementService;
    @Resource
    private PermissionService permissionService;

    /**
     * @return
     * @Author xiaoming
     * @Description
     * @Date 11:10 上午 7/27/20
     * @Param
     **/
    @ResponseBody
    @RequestMapping(name = "资金报表", value = "/list", method = RequestMethod.GET)
    public ResultInfo list(@RequestParam(name = "yearmonth", required = false) String yearmonth) {
        long start = System.currentTimeMillis();
        ResultInfo resultInfo = ResultInfo.ok();
        try {
            resultInfo = ResultInfo.ok(this.expFundstatementService.getListByYearMonth(yearmonth));
        } catch (BusinessException e) {
            resultInfo.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.list失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
            resultInfo.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.list获取资金报表出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
            resultInfo = ResultInfo.error("获取资金报表出错");
        }
        logger.info("/获取资金报表list耗时{}毫秒", (System.currentTimeMillis() - start));
        return resultInfo;
    }


    /**
     * 【为超级后台提供资金报表，跟后台资金报表不同在于这里直播是收入，不是盈利】
     *
     * @Author jiehuang
     * @Description ExpFundStatementController
     * @Date 2020/8/20-10:26
     **/
    @ResponseBody
    @HttpValidate //http验签
    @AllowAccess
    @RequestMapping(name = "超级后台提供资金报表", value = "/collectList", method = RequestMethod.GET)
    public ResultInfo collectList(@RequestParam(name = "yearmonth", required = false) String yearmonth) {
        long start = System.currentTimeMillis();
        ResultInfo resultInfo = ResultInfo.ok();
        try {
            resultInfo = ResultInfo.ok(this.expFundstatementService.collectListByYearMonth(yearmonth));
        } catch (BusinessException e) {
            resultInfo.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.collectList失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
            resultInfo.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.collectList超级后台提供资金报表出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
            resultInfo = ResultInfo.error("超级后台提供资金报表获取资金报表出错");
        }
        logger.info("/超级后台提供资金报表获取资金报表collectList耗时{}毫秒", (System.currentTimeMillis() - start));
        return resultInfo;
    }
    /**
     * 资金报表导出
     *
     * @param res
     * @param yearmonth 年月 格式yyyyMM 如202004
     * @param acctoken  年月 acctoken
     * @return
     */
    @AllowAccess
    @RequestMapping(name = "资金报表导出", value = "/exportexcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse res, HttpServletRequest req,
                            @RequestParam(name = "acctoken", required = false) String acctoken,
                            @RequestParam(name = "yearmonth", required = false) String yearmonth) {
        long start = System.currentTimeMillis();
        ResultInfo resultInfo = ResultInfo.ok();
        boolean auth = permissionService.checkUserAuthByToken(acctoken, "/expfunds/exportexcel");
        if (!auth) {
            throw new BusinessException("您没有权限执行该操作");
        }
        try {
            // 获取workbook对象
            Workbook workbook = expFundstatementService.exportFundsExcel(yearmonth);
            // 判断数据
            if (workbook == null) {
                resultInfo = ResultInfo.error("资金报表导出出错");
                return;
            }
            Date date = DateUtil.parse(yearmonth, "yyyyMM");
            String excelDateStr = DateUtil.format(date, "yyyy年MM月");
            // 设置excel的文件名称
            String fileName = excelDateStr + "资金报表.xlsx";
            String userAgent = req.getHeader("User-Agent");
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                // 非IE浏览器的处理：
                fileName = new String((fileName).getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
            res.setContentType("multipart/form-data");
            res.setCharacterEncoding("utf-8");
            res.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            // 写出数据输出流到页面
            OutputStream output = res.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
        } catch (BusinessException e) {
            resultInfo.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.exportExcel失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
            resultInfo.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.exportExcel导出资金报表出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
            resultInfo = ResultInfo.error("导出资金报表出错");
        }
        logger.info("导出资金报表耗时{}毫秒", (System.currentTimeMillis() - start));
    }


    /**
     * 资金报表手动拉取执行
     *
     * @param startTime 起始时间 yyyyMMdd 20200404
     * @param endTime   结束时间 yyyyMMdd 20200408
     */
    @ResponseBody
    //@AllowAccess
    @RequestMapping(name = "拉取数据", value = "/manualfunds", method = RequestMethod.GET)
    public ResultInfo manualStatisticsFunds(@RequestParam("startTime") String startTime,
                                            @RequestParam("endTime") String endTime) {
        long start = System.currentTimeMillis();
        ResultInfo resultInfo = ResultInfo.ok();
        try {
            LoginUser loginUser = new LoginUser();
            expFundstatementService.manualStatisticsFunds(startTime, endTime, loginUser);
        } catch (BusinessException e) {
            resultInfo.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.manualStatisticsFunds失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
            resultInfo.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.manualStatisticsFunds拉取资金报表出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
            resultInfo = ResultInfo.error("拉取资金报表出错");
        }
        logger.info("拉取资金报表耗时{}毫秒", (System.currentTimeMillis() - start));
        return resultInfo;
    }

}
