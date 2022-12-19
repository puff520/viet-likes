package com.likes.modules.admin.users.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.order.OrderRequest;
import com.likes.common.model.request.PayPasswordReq;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.mybatis.entity.SysFeedback;
import com.likes.common.service.member.MemBankaccountService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.CommonFunction;
import com.likes.common.util.LogUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.common.service.AWSS3Service;
import com.likes.modules.admin.users.service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/person")
public class PersonController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private PersonService personService;
    @Resource
    private AWSS3Service s3Service;
    @Resource
    private SysParamService sysParamService;

    @Resource
    private MemBankaccountService memBankaccountService;

    @ResponseBody
    @RequestMapping(name = "登出", value = "/loginout", method = RequestMethod.GET)
    public ResultInfo loginout() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(personService.loginout(loginUserAPP));
            //增加删除在线人数
            List<String> delList = new ArrayList<>();
            delList.add(loginUserAPP.getMemid().toString());
            RedisBusinessUtil.delMemberOnline(loginUserAPP.getSourceType(), delList);
            LogUtils.logUserModifyOperates(getClass().getName() + ".loginout", null, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.loginout 失败:{}, params:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            logger.error("{}.loginout 出错:{}, params:{}", getClass().getName(), e.getMessage(), e);
            response = ResultInfo.error("登出出错");
        }
        logger.info("/loginout耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "我的-个人信息", value = "/getUserInfo", method = RequestMethod.GET)
    public ResultInfo getUserInfo() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(personService.getUserInfo(loginUserAPP));
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.getUserInfo 失败:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("获取个人信息出错");
            logger.error("{}.getUserInfo 出错:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getUserInfo耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "我的-我的推广", value = "/getMyPromotion", method = RequestMethod.GET)
    public ResultInfo getMyPromotion(PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(personService.getMyPromotion(loginUserAPP.getAccno(), page));
            LogUtils.logUserModifyOperates(getClass().getName() + ".getMyPromotion", page, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.getMyPromotion 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(page), e);
        } catch (Exception e) {
            response = ResultInfo.error("获取我的推广出错");
            logger.error("{}.getMyPromotion 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(page), e);
        }
        logger.info("/getMyPromotion耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "我的-分享推广", value = "/sharePromotion", method = RequestMethod.GET)
    public ResultInfo sharePromotion() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(personService.sharePromotion(loginUserAPP.getAccno()));
            LogUtils.logUserModifyOperates(getClass().getName() + ".sharePromotion", loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.sharePromotion 失败:{}, params:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("分享推广出错");
            logger.error("{}.sharePromotion 出错:{}, params:{}", getClass().getName(), e.getMessage(), e);
        }

        logger.info("/sharePromotion耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "我的-修改生日/性别", value = "/updateUserOther", method = RequestMethod.POST)
    public ResultInfo updateUserOther(UsersRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(personService.updateUserOther(req, loginUserAPP));
            LogUtils.logUserModifyOperates(getClass().getName() + ".updateUserOther", req, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.updateUserOther 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("修改个人信息出错");
            logger.error("{}.updateUserOther 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/updateUserOther耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "我的-修改昵称", value = "/updateNickname", method = RequestMethod.POST)
    public ResultInfo updateNickname(UsersRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(personService.updateNickname(req, loginUserAPP));
            LogUtils.logUserModifyOperates(getClass().getName() + ".updateNickname", req, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.updateNickname 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("修改昵称出错");
            logger.error("{}.updateNickname 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/updateNickname耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }



    @ResponseBody
    @RequestMapping(name = "我的-邀请码", value = "/getRecomcode", method = RequestMethod.GET)
    public ResultInfo getRecomcode() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(personService.getRecomcode(loginUserAPP));
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.getRecomcode 失败:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("获取邀请码出错");
            logger.error("{}.getRecomcode 出错:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getRecomcode耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "我的-填写邀请码", value = "/fillinRecomcode", method = RequestMethod.POST)
    public ResultInfo fillinRecomcode(UsersRequest usersRequest) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            usersRequest.setAccno(loginUserAPP.getAccno());
            response.setData(personService.fillinRecomcode(usersRequest, loginUserAPP));
            LogUtils.logUserModifyOperates(getClass().getName() + ".fillinRecomcode", usersRequest, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.fillinRecomcode 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(usersRequest), e);
        } catch (Exception e) {
            response = ResultInfo.error("填写邀请码出错");
            logger.error("{}.fillinRecomcode 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(usersRequest), e);
        }
        logger.info("/fillinRecomcode耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }




    @ResponseBody
    @RequestMapping(name = "我的-订单列表", value = "/myOrderList", method = RequestMethod.GET)
    public ResultInfo myOrderList(OrderRequest req, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            req.setAccno(loginUserAPP.getAccno());
            response.setData(personService.myOrderList(page, req));
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.myOrderList 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("获取订单列表出错");
            logger.error("{}.myOrderList 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/myOrderList耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "我的-订单详细", value = "/myOrderDetail", method = RequestMethod.GET)
    public ResultInfo myOrderDetail(OrderRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(personService.myOrderDetail(req));
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.myOrderDetail 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("获取订单详细出错");
            logger.error("{}.myOrderDetail 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/myOrderDetail耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "我的-取消订单", value = "/cancelOrder", method = RequestMethod.POST)
    public ResultInfo cancelOrder(OrderRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(personService.cancelOrder(loginUserAPP, req));
            LogUtils.logUserModifyOperates(getClass().getName() + ".cancelOrder", req, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.cancelOrder 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("取消订单出错");
            logger.error("{}.cancelOrder 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/cancelOrder耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "我的-设置支付密码", value = "/savePayPassword", method = RequestMethod.POST)
    public ResultInfo setPayPassword(PayPasswordReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(personService.setPayPassword(loginUserAPP, req));
            LogUtils.logUserModifyOperates(getClass().getName() + ".setPayPassword", req, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.setPayPassword 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("设置支付密码出错");
            logger.error("{}.setPayPassword 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/setPayPassword耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "查询用户是否设置资金密码和绑卡", value = "/findPayPassAndAccount", method = RequestMethod.GET)
    public ResultInfo findMemAccount() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(memBankaccountService.findPayPassAndAccount(loginUserAPP));
        } catch (Exception e) {
            response = ResultInfo.error("app更新出错");
            logger.error("{}.updateapp 出错:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/updateapp耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "我的-更新支付密码", value = "/updatePayPassword", method = RequestMethod.POST)
    public ResultInfo updatePayPassword(PayPasswordReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(personService.updatePayPassword(loginUserAPP, req));
            LogUtils.logUserModifyOperates(getClass().getName() + ".updatePayPassword", req, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("{}.updatePayPassword 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("更新支付密码出错");
            logger.error("{}.updatePayPassword 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/updatePayPassword耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    /**
     * 刷新帐户余额
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "刷新账户余额", value = "/doReflushGoldnumBalance", method = RequestMethod.GET)
    public ResultInfo doReflushGoldnumBalance() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginUserAPP();
            response.setData(this.personService.doReflushGoldnumBalance(loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doReflushGoldnumBalance", loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doReflushGoldnumBalance 失败:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("刷新账户余额出错");
            logger.error("{}.doReflushGoldnumBalance 出错:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doReflushGoldnumBalance耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


}
