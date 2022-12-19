package com.likes.modules.admin.task;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.task.TaskOrderTypeEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.ReceiveTaskRequest;
import com.likes.common.model.request.SubmitTaskRequest;
import com.likes.common.model.request.TaskOrderRequest;
import com.likes.common.model.request.TaskRequest;
import com.likes.common.service.task.TaskCategoryService;
import com.likes.common.service.task.TaskOrderService;
import com.likes.common.service.task.TaskService;
import com.likes.common.util.StringUtils;
import com.likes.common.util.redis.RedisLock;
import com.likes.modules.admin.common.service.CommonService;
import com.likes.modules.admin.task.service.TaskAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * @author 阿布
 * <p>
 * 代理设置
 */


@Controller
@RequestMapping(value = "/taskApp")
public class TaskAppController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TaskService taskService;
    @Resource
    private TaskAppService taskAppService;
    @Resource
    private TaskCategoryService taskCategoryService;
    @Resource
    private TaskOrderService taskOrderService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private CommonService commonService;

    @ResponseBody
    @AllowAccess
    @RequestMapping(name = "查询所有任务分类", value = "/categoryList", method = RequestMethod.GET)
    public ResultInfo categoryList(String language) {
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(taskCategoryService.categoryList(language));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("失败:{}", e.getMessage());
        } catch (Exception e) {
            response.setResultInfo("100000001", null);
            //response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("categoryList error,  er:{},", e);
        }
        return response;
    }


    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "查询任务", value = "/taskList", method = RequestMethod.GET)
    public ResultInfo taskList(TaskRequest req) {
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginUserAPP();
            if (loginUser != null) {
                req.setAccno(loginUser.getAccno());
            }
            if (commonService.checkUserMemberLevelExpire(req.getLevelSeq(), loginUser.getAccno())) {
                throw new BusinessException(StatusCode.MEMBER_EXPIRE_ERROR_130014.getCode(), StatusCode.MEMBER_EXPIRE_ERROR_130014.getValue());
            }
            req.setLevelSeqStr(req.getLevelSeq()+"");
            response.setData(taskAppService.taskAppList(req,  loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("失败:{}", e.getMessage());
        } catch (Exception e) {
            response.setResultInfo("100000001", null);
            //response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("getList error,  req:{},", JSONObject.toJSONString(req), e);
        }
        return response;
    }


    @RequestMapping(name = "app编辑任务", value = "/editTask", method = RequestMethod.POST)
    public ResultInfo updateAccount(TaskRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginUserAPP();
            response.setData(taskService.updateTask(request, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/task/edit:{}", e.getMessage());
        } catch (Exception ex) {
            response.setResultInfo("100000001", null);
            logger.error("editTask error,  req:{},", JSONObject.toJSONString(request), ex);
        }
        logger.info("/task/edit:{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "打开链接", value = "/openLink", method = RequestMethod.GET)
    public ResultInfo openLink(Long taskOrderId) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginUserAPP();
            response.setData(taskService.openLink(taskOrderId, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/task/edit:{}", e.getMessage());
        } catch (Exception ex) {
            response.setResultInfo("100000001", null);
            logger.error("editTask error,  req:{},", JSONObject.toJSONString(request), ex);
        }
        logger.info("/task/edit:{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "查询任务详情", value = "/taskDetail", method = RequestMethod.GET)
    public ResultInfo getAgentReport(Long taskId) {
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(taskAppService.taskAppDetail(taskId));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("失败:{}", e.getMessage());
        } catch (Exception e) {
            response.setResultInfo("100000001", null);
            logger.error("taskDetail error, req:{},", taskId, e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "会员订单列表", value = "/memOrderList", method = RequestMethod.GET)
    public ResultInfo getNextList(TaskOrderRequest req) {
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(taskAppService.queryMemberOrderList(loginUserAPP, req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("失败:{}", e.getMessage());
        } catch (Exception e) {
            response.setResultInfo("100000001", null);
            logger.error("getNextList error, req:{},", JSONObject.toJSONString(req), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "我的待审核任务", value = "/waitReceiveList", method = RequestMethod.GET)
    public ResultInfo waitReceiveList(@RequestParam("status") Integer status, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(taskAppService.waitReceiveList(loginUserAPP, status, pageNo, pageSize));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("失败:{}", e.getMessage());
        } catch (Exception e) {
            response.setResultInfo("100000001", null);
        }
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "领取任务", value = "/receiveTask", method = RequestMethod.POST)
    public ResultInfo receiveTask(ReceiveTaskRequest request) {
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(taskAppService.reviceTask(loginUserAPP, request));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("失败:{}", e.getMessage());
        } catch (Exception e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
        }
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "提交完成任务", value = "/submitTask", method = RequestMethod.POST)
    public ResultInfo submitTask(SubmitTaskRequest request) {
        ResultInfo response = ResultInfo.ok();
        RedisLock lock = new RedisLock(RedisLock.FINANCE_APP_SUBMITTASK_APPLY_LOCK, 0, 10 * 2000);
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            // 控制频率
            String keySuffix = RedisLock.FINANCE_APP_SUBMITTASK_APPLY_LOCK + loginUserAPP.getMemid();
            if (redisTemplate.hasKey(keySuffix)) {
                return ResultInfo.error("提现操作频繁，请稍后再试！");
            }
            boolean haveAuth = redisTemplate.opsForValue().setIfAbsent(keySuffix, "1", 2, TimeUnit.SECONDS);
            if (!haveAuth) {
                return ResultInfo.error("提现操作频繁，请稍后再试！");
            }

            if (!lock.lock()) {
                return ResultInfo.error("提现操作频繁，请稍后再试！");
            }
            response.setData(taskAppService.submitTask(loginUserAPP, request));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("失败:{}", e.getMessage());
        } catch (Exception e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
        } finally {
            lock.unlock();
        }
        return response;
    }


}
