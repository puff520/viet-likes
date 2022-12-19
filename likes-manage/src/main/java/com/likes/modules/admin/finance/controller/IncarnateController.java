package com.likes.modules.admin.finance.controller;

import com.alibaba.excel.EasyExcel;
import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.IncarnateOrderReq;
import com.likes.common.model.response.EntryIncarnateOrderExcelResponse;
import com.likes.common.util.JsonUtil;
import com.likes.common.util.LogUtils;
import com.likes.common.util.redis.RedisLock;
import com.likes.modules.admin.finance.service.IncarnateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 出账
 * 主播
 *
 * @author bjkj
 */
@Controller
@RequestMapping(value = "/incarnate")
public class IncarnateController extends BaseController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Resource
    private IncarnateService incarnateService;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 查看已提现完成的订单
     *
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "出账订单查看", value = "/incarnateOrderDetail", method = RequestMethod.GET)
    public ResultInfo incarnateOrderDetail(IncarnateOrderReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(incarnateService.incarnateOrderDetail(req, loginAdmin));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}/incarnateOrderDetail出账订单查看出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("出账订单查看失败");
            logger.error("{}/incarnateOrderDetail出账订单查看出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/incarnateOrderDetail耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @Syslog("出账订单确认")
    @ResponseBody
    @RequestMapping(name = "出账订单确认", value = "/sureIncarnateOrder", method = RequestMethod.POST)
    public ResultInfo sureIncarnateOrder(IncarnateOrderReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        RLock lock = null;
        try {
            LoginUser loginAdmin = getLoginAdmin();
            lock = redissonClient.getReadWriteLock(RedisLock.FINANCE_MANAGE_WITHDRAWAL_SURE + req.getOrderno()).writeLock();
            boolean bool = lock.tryLock(0, 60, TimeUnit.SECONDS);
            if (!bool) {
                throw new BusinessException("操作太频繁，请稍后再试！");
            }
            String keySuffix = RedisLock.FINANCE_APP_WITHDRAWAL_UDUN + req.getOrderno();
            if (redisTemplate.hasKey(keySuffix)) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "第三方回调正在处理，请稍后再试！");
            }
            response.setData(incarnateService.sureOrder(req, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".sureIncarnateOrder", req, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}/sureIncarnateOrder出账订单确认出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("出账订单确认失败");
            logger.error("{}/sureIncarnateOrder出账订单确认出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
        logger.info("/sureIncarnateOrder耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("出账订单取消")
    @ResponseBody
    @RequestMapping(name = "出账订单取消", value = "/cancelSureIncarnateOrder", method = RequestMethod.POST)
    public ResultInfo cancelSureIncarnateOrder(IncarnateOrderReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        RLock lock = null;
        try {
            LoginUser loginAdmin = getLoginAdmin();
            lock = redissonClient.getReadWriteLock(RedisLock.FINANCE_MANAGE_WITHDRAWAL_CANCEL + "_" + req.getOrderno()).writeLock();
            boolean bool = lock.tryLock(0, 60, TimeUnit.SECONDS);
            if (!bool) {
                throw new BusinessException("操作太频繁，请稍后再试！");
            }
            String keySuffix = RedisLock.FINANCE_APP_WITHDRAWAL_UDUN + req.getOrderno();
            if (redisTemplate.hasKey(keySuffix)) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "第三方回调正在处理，请稍后再试！");
            }
            response.setData(incarnateService.cancelSureIncarnateOrder(req, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".cancelSureIncarnateOrder", req, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}/cancelSureIncarnateOrder出账订单取消出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("出账订单取消失败");
            logger.error("{}/cancelSureIncarnateOrder出账订单取消出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
        logger.info("/cancelSureIncarnateOrder耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    /**
     * 查看提现处理中的订单
     *
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "出账订单处理", value = "/incarnateHandleOrderDetail", method = RequestMethod.GET)
    public ResultInfo incarnateHandleOrderDetail(IncarnateOrderReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(incarnateService.incarnateHandleOrderDetail(req, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".incarnateHandleOrderDetail", req, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}/incarnateHandleOrderDetail出账订单处理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("出账订单处理失败");
            logger.error("{}/incarnateHandleOrderDetail出账订单处理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/incarnateHandleOrderDetail耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    //-------------------------------------v2 用户提现--------------------------------------
    @ResponseBody
    @RequestMapping(name = "出帐订单管理", value = "/v2/orderList", method = RequestMethod.GET)
    public ResultInfo orderListV2(IncarnateOrderReq req, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(incarnateService.incarnateOrderListV2(req, page, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".orderListV2", req, loginAdmin);
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


    @RequestMapping(name = "导出线上出款excel", value = "/v2/orderList/export", method = RequestMethod.GET)
    public void downOnlineOrderList(HttpServletResponse response, IncarnateOrderReq req) throws IOException {
        long start = System.currentTimeMillis();
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("线上出款订单", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            Set<String> excludeColumnFiledNames = new HashSet<>();
            excludeColumnFiledNames.add("orderstatus");
            excludeColumnFiledNames.add("updateUser");
            excludeColumnFiledNames.add("accounttype");
            excludeColumnFiledNames.add("updateTime");

            req.setAccno(getLoginAdmin().getAccno());
            EasyExcel.write(response.getOutputStream(), EntryIncarnateOrderExcelResponse.class).excludeColumnFiledNames(excludeColumnFiledNames).autoCloseStream(Boolean.FALSE).sheet("模板").doWrite(incarnateService.incarnateOrderListV2Excel(req));
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
        logger.info("/v2/orderList/export耗时{}毫秒：", (System.currentTimeMillis() - start));
    }


    @Syslog("出账订单处理,确认转账")
    @ResponseBody
    @RequestMapping(name = "出账订单处理,确认转账", value = "/v2/incarnateConfirm", method = RequestMethod.POST)
    public ResultInfo incarnateConfirmV2(IncarnateOrderReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        RLock lock = null;
        try {
            LoginUser loginAdmin = getLoginAdmin();
            lock = redissonClient.getReadWriteLock(RedisLock.FINANCE_MANAGE_WITHDRAWAL_CONFIRM + "_" + req.getOrderno()).writeLock();
            boolean bool = lock.tryLock(0, 60, TimeUnit.SECONDS);
            if (!bool) {
                throw new BusinessException("操作太频繁，请稍后再试！");
            }
            String keySuffix = RedisLock.FINANCE_APP_WITHDRAWAL_UDUN + req.getOrderno();
            if (redisTemplate.hasKey(keySuffix)) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "第三方回调正在处理，请稍后再试！");
            }
            response.setData(incarnateService.incarnateConfirmV2(req, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".incarnateConfirmV2", req, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}/v2/incarnateConfirm出账订单处理,确认转账出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("出账订单处理,确认转账失败");
            logger.error("{}/v2/incarnateConfirm出账订单处理,确认转账出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
        logger.info("/v2/incarnateConfirm耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "出账订单处理,确认转账", value = "/v2/subUdun", method = RequestMethod.POST)
    public ResultInfo subUdun(IncarnateOrderReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        LoginUser loginAdmin = getLoginAdmin();
        response.setData(incarnateService.subUdun(req, loginAdmin));
        LogUtils.logUserModifyOperates(getClass().getName() + ".incarnateConfirmV2", req, loginAdmin);
        logger.info("/v2/incarnateConfirm耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


}
