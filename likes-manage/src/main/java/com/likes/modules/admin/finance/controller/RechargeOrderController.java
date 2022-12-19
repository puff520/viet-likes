package com.likes.modules.admin.finance.controller;

import com.alibaba.excel.EasyExcel;
import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.annotation.Syslog;
import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.EntryOrderReq;
import com.likes.common.model.request.OrderReq;
import com.likes.common.model.response.EntryOnLineOrderExcelResponse;
import com.likes.common.model.response.EntryOrderExcelResponse;
import com.likes.common.util.CommonFunction;
import com.likes.common.util.JsonUtil;
import com.likes.common.util.LogUtils;
import com.likes.common.util.redis.RedisLock;
import com.likes.modules.admin.common.service.AWSS3Service;
import com.likes.modules.admin.finance.service.RechargeOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 充值订单-公司入款订单管理 /线上入账订单/强制入款
 *
 * @author bjkj
 */
@Controller
@RequestMapping(value = "/order")
public class RechargeOrderController extends BaseController {
    private final static Logger logger = LogManager.getLogger(RechargeOrderController.class);

    @Resource
    private RechargeOrderService rechargeOrderService;
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private AWSS3Service s3Service;

    //-------------------------------------------公司入款订单----------------------------------------------------------
    @ResponseBody
    @RequestMapping(name = "公司入款订单处理", value = "/getRechargeOrderDetail", method = RequestMethod.GET)
    public ResultInfo getRechargeOrderDetail(@Valid OrderReq req, BindingResult err) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (err.hasErrors()) {
                throw new BusinessException(err.getAllErrors().get(0).getDefaultMessage());
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(rechargeOrderService.getRechargeOrderDetail(req, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".getRechargeOrderDetail", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getRechargeOrderDetai公司入款订单处理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("公司入款订单处理失败");
            logger.error("{}.getRechargeOrderDetai公司入款订单处理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getRechargeOrderDetail耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("交易凭证上传多图上传")
    @ResponseBody
    @RequestMapping(name = "交易凭证上传多图上传", value = "/awsupload/manyBusinessPicFileUpload", method = RequestMethod.POST)
    public ResultInfo manyBusinessPicFileUpload(@RequestParam("file") MultipartFile[] files) throws Exception {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (files == null || files.length == 0) {
                response.setInfo("请选择交易凭证截图");
                response.setStatus(StatusCode.LIVE_ERROR_202.getCode());
                return response;
            }

            logger.info("files.length: {}", files.length);
            if (files != null && files.length > 5) {
                response.setInfo("图片数量超过5张");
                response.setStatus(StatusCode.LIVE_ERROR_202.getCode());
                return response;
            }
            for (MultipartFile multipartFile : files) {
                String fileName = multipartFile.getOriginalFilename();
                //System.out.println(fileName);
                boolean flagb = CommonFunction.imageSuffix(fileName);
                if (!flagb) {
                    response.setInfo("图片格式不对");
                    response.setStatus(StatusCode.LIVE_ERROR_202.getCode());
                    return response;
                }

                long size = multipartFile.getSize();
                if ((size / (1024 * 1024)) > 2) {
                    response.setInfo("图片大小不超过2M");
                    response.setStatus(StatusCode.LIVE_ERROR_203.getCode());
                    return response;
                }
            }
            response.setData(s3Service.manyArticlePicFileUpload(files, Constants.LIVE_FILE));
            LoginUser loginUser = getLoginAdmin();
            LogUtils.logUserModifyOperates(getClass().getName() + ".manyBusinessPicFileUpload", null, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.manyBusinessPicFileUpload交易凭证上传多图上传出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("交易凭证上传多图上传失败");
            logger.error("{}.manyBusinessPicFileUpload交易凭证上传多图上传出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/manyBusinessPicFileUpload耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("公司入款订单处理,处理订单")
    @ResponseBody
    @RequestMapping(name = "公司入款订单处理,处理订单", value = "/doRechargeOrder", method = RequestMethod.POST)
    public ResultInfo doRechargeOrder(OrderReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        RLock lock = null;
        try {
            lock = redissonClient.getReadWriteLock(RedisLock.FINANCE_MANAGE_PAY_CONFIRM + req.getOrderno()).writeLock();

            LoginUser loginUser = getLoginAdmin();
            boolean bool = lock.tryLock(0, 60, TimeUnit.SECONDS);
            if (!bool) {
                throw new BusinessException("操作太频繁，请稍后再试！");
            }
            response.setData(rechargeOrderService.doRechargeOrderV2(req, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doRechargeOrder", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doRechargeOrder公司入款订单处理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("公司入款订单处理失败");
            logger.error("{}.doRechargeOrder公司入款订单处理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
        logger.info("/doRechargeOrder耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "公司入款订单管理", value = "/orderList", method = RequestMethod.GET)
    public ResultInfo orderList(EntryOrderReq req, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(rechargeOrderService.orderListReuslt(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.orderList公司入款订单管理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("公司入款订单管理失败");
            logger.error("{}.orderList公司入款订单管理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/orderList耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @AllowAccess
    @RequestMapping(name = "导出excel", value = "/export/recharge", method = RequestMethod.GET)
    public void downTemplate(HttpServletResponse response, EntryOrderReq req) throws IOException {
        long start = System.currentTimeMillis();
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("公司入款订单", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            Set<String> excludeColumnFiledNames = new HashSet<String>();
            excludeColumnFiledNames.add("ordernote");
            excludeColumnFiledNames.add("orderstatus");
            excludeColumnFiledNames.add("modifyusername");
            excludeColumnFiledNames.add("updateTime");
            EasyExcel.write(response.getOutputStream(), EntryOrderExcelResponse.class).excludeColumnFiledNames(excludeColumnFiledNames).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(rechargeOrderService.orderList(req));
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JsonUtil.toJSONObject(map));
        }
        logger.info("/export/recharge耗时{}毫秒：", (System.currentTimeMillis() - start));
    }

    @ResponseBody
    @RequestMapping(name = "公司入款订单查看", value = "/getOrderDetail", method = RequestMethod.GET)
    public ResultInfo orderList(EntryOrderReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(rechargeOrderService.getOrderDetail(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getOrderDetail公司入款订单查看出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("公司入款订单查看失败");
            logger.error("{}.getOrderDetail公司入款订单查看出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getOrderDetail耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    //----------------------------------------线上订单-------------------------------------------------
    @ResponseBody
    @RequestMapping(name = "线上入款订单管理", value = "/onlineOrderList", method = RequestMethod.GET)
    public ResultInfo onlineOrderList(EntryOrderReq req, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            Map returnMap = new HashMap();
            returnMap.put("list", rechargeOrderService.onlineOrderList(req, page));
            returnMap.put("data", rechargeOrderService.getStatisticsOnLineOrder(req));
            response.setData(returnMap);
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

    @AllowAccess
    @RequestMapping(name = "导出线上入款excel", value = "/export/onlineOrderList", method = RequestMethod.GET)
    public void downOnlineOrderList(HttpServletResponse response, EntryOrderReq req) throws IOException {
        long start = System.currentTimeMillis();
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("线上入款订单", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            Set<String> excludeColumnFiledNames = new HashSet<String>();
            excludeColumnFiledNames.add("orderid");
            excludeColumnFiledNames.add("rechargegold");
            excludeColumnFiledNames.add("ordernote");
            excludeColumnFiledNames.add("orderstatus");
            excludeColumnFiledNames.add("modifyusername");
            excludeColumnFiledNames.add("updateTime");
            excludeColumnFiledNames.add("tpaysetid");
            excludeColumnFiledNames.add("payuser");
            excludeColumnFiledNames.add("paynote");
            EasyExcel.write(response.getOutputStream(), EntryOnLineOrderExcelResponse.class).excludeColumnFiledNames(excludeColumnFiledNames).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(rechargeOrderService.onlineOrderList(req));
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JsonUtil.toJSONObject(map));
        }
        logger.info("/export/onlineOrderList耗时{}毫秒：", (System.currentTimeMillis() - start));
    }

    @ResponseBody
    @RequestMapping(name = "线上入款订单查看", value = "/getOnlineOrderDetail", method = RequestMethod.GET)
    public ResultInfo getOnlineOrderDetail(EntryOrderReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(rechargeOrderService.getOnlineOrderDetail(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getOnlineOrderDetail线上入款订单查看,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("线上入款订单查看失败");
            logger.error("{}.getOnlineOrderDetail线上入款订单查看,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getOnlineOrderDetail耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    //----------------------------------------------强制入款-----------------------------------------------------
    //订单详细
    @Syslog("强制入账")
    @ResponseBody
    @RequestMapping(name = "强制入账", value = "/getMandatoryOrderDetail", method = RequestMethod.GET)
    public ResultInfo getMandatoryOrderDetail(EntryOrderReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(rechargeOrderService.getMandatoryOrderDetail(req));
            LoginUser loginUser = getLoginAdmin();
            LogUtils.logUserModifyOperates(getClass().getName() + ".getMandatoryOrderDetail", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getMandatoryOrderDetail强制入账出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("强制入账失败");
            logger.error("{}.getMandatoryOrderDetail强制入账出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getMandatoryOrderDetail耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "强制入账确认", value = "/doMandatoryOrder", method = RequestMethod.POST)
    public ResultInfo doMandatoryOrder(EntryOrderReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(rechargeOrderService.doMandatoryOrder(req, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doMandatoryOrder", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doMandatoryOrder强制入账确认出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("强制入账确认失败");
            logger.error("{}.doMandatoryOrder强制入账确认出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doMandatoryOrder耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

}
