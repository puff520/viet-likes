package com.likes.modules.admin.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.AdminAccess;
import com.likes.common.annotation.Syslog;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.*;
import com.likes.common.mybatis.entity.MemberDeviceCalc;
import com.likes.common.util.LogUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.likes.common.util.redis.RedisBusinessUtil.deleteFuzzyMatchCache;

/**
 * 用户管理
 *
 * @author bjkj
 */
@AdminAccess
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    public static String addonline = "0";

    @ResponseBody
    @RequestMapping(name = "普通用户,主播管理,系统用户", value = "/list", method = RequestMethod.GET)
    public ResultInfo userList(UserRequest req, @RequestParam(value = "pageNo") Integer pageNo, @RequestParam(value = "pageSize") Integer pageSize) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(userService.userList(req, pageNo, pageSize));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.userList普通用户,主播管理,系统用户获取出错,params:{},出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("普通用户,主播管理,系统用户获取失败");
            logger.error("{}.userList普通用户,主播管理,系统用户获取出错,params:{},出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        }
        logger.info("/userlist耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "会员银行卡钱包地址地址", value = "/bankList", method = RequestMethod.GET)
    public ResultInfo bankList(MemberBankRequest req, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(userService.memBankList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            response = ResultInfo.error("普通用户,主播管理,系统用户获取失败");
        }
        return response;
    }

    @Syslog("普通用户禁用启用,主播管理禁用启用")
    @ResponseBody
    @RequestMapping(name = "普通用户禁用启用,主播管理禁用启用", value = "/doAccstatusUser", method = RequestMethod.POST)
    public ResultInfo doAccstatusUser(UserRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            req.setOperataccno(loginAdmin.getAccno());
            response.setData(userService.doAccstatusUser(req));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doAccstatusUser", req, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doAccstatusUser普通用户禁用启用,主播管理禁用启用出错,params:{}出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("支付设定启用禁用失败");
            logger.error("{}.doAccstatusUser普通用户禁用启用,主播管理禁用启用出错,params:{}出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        }
        logger.info("/doAccstatusUser耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("普通用户删除,主播管理删除,系统用户删除")
    @ResponseBody
    @RequestMapping(name = "普通用户删除,主播管理删除,系统用户删除", value = "/doDelUser", method = RequestMethod.POST)
    public ResultInfo doDelUser(UserRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            req.setOperataccno(loginAdmin.getAccno());
            response.setData(userService.doDelUser(req));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doDelUser", req, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doDelUser普通用户删除,主播管理删除,系统用户删除出错,params:{}出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("普通用户删除,主播管理删除,系统用户删除失败");
            logger.error("{}.doDelUser普通用户删除,主播管理删除,系统用户删除出错,params:{}出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        }
        logger.info("/doDelUser耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "用户详细", value = "/getUserDetail", method = RequestMethod.GET)
    public ResultInfo getUserDetail(UserRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            req.setOperataccno(loginAdmin.getAccno());
            response.setData(userService.getUserDetail(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getUserDetail用户详细出错,params:{}出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("用户详细失败");
            logger.error("{}.getUserDetail用户详细出错,params:{}出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        }
        logger.info("/getUserDetail耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "主播审核", value = "/applyAnchorList", method = RequestMethod.POST)
    public ResultInfo applyAnchorList(@RequestParam(name = "username", required = false) String username, @RequestBody PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            UserRequest request = new UserRequest();
            request.setUsername(username);
            response.setData(userService.applyAnchorList(request, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.applyAnchorList主播审核出错username:{},出错信息:{}", getClass().getName(), username, e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("主播审核失败");
            logger.error("{}.applyAnchorList主播审核出错username:{},出错信息:{}", getClass().getName(), username, e.getMessage(), e);
        }
        logger.info("/applyAnchorList耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

//    @ResponseBody
//    @RequestMapping(name = "主播审核操作", value = "/reviewAnchor", method = RequestMethod.POST)
//    public ResultInfo reviewAnchor(@RequestParam(name = "familyid", required = false) Long familyid, @RequestBody MemCertification req) {
//        long start = System.currentTimeMillis();
//        ResultInfo response = ResultInfo.ok();
//        try {
//            LoginUser loginAdmin = getLoginAdmin();
//            response.setData(userService.reviewAnchor(req, loginAdmin, familyid));
//            LogUtils.logUserModifyOperates(getClass().getName() + ".reviewAnchor", req, loginAdmin);
//        } catch (BusinessException e) {
//            response.setResultInfo(e.getCode(), e.getMessage());
//            logger.error("{}.reviewAnchor主播审核操作出错,familyid:{}出错信息:{}", getClass().getName(), familyid, e.getMessage(), e);
//        } catch (Exception e) {
//            response = ResultInfo.error("主播审核操作失败");
//            logger.error("{}.reviewAnchor主播审核操作出错,familyid:{}出错信息:{}", getClass().getName(), familyid, e.getMessage(), e);
//        }
//        logger.info("/reviewAnchor耗时{}毫秒：", (System.currentTimeMillis() - start));
//        return response;
//    }

    /**
     * 统计在线人数
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "统计在线人数", value = "/selDeviceNum", method = RequestMethod.GET)
    public ResultInfo selDeviceNum(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo resultInfo = new ResultInfo();
        try {
            MemberDeviceCalc c = RedisBusinessUtil.getMemberDeviceCalc();
            if (null == c) {
                c = new MemberDeviceCalc();
            }
            c.setAndroidCount(c.getAndroidCount() + Integer.parseInt(addonline));
            c.setTotalCount(c.getTotalCount() + Integer.parseInt(addonline));
            resultInfo = ResultInfo.ok(c);
        } catch (BusinessException e) {
            logger.error("{}.selDeviceNum获取当前设备人数失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
            resultInfo = ResultInfo.error("获取当前设备人数失败");
        } catch (Exception e) {
            logger.error("{}.selDeviceNum获取当前设备人数出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
            resultInfo = ResultInfo.error("获取当前设备人数出错");
        }
        logger.info("/selDeviceNum耗时{}毫秒：", (System.currentTimeMillis() - start));
        return resultInfo;
    }

    @Syslog("普通用户-修改密码")
    @ResponseBody
    @RequestMapping(name = "普通用户-修改密码", value = "/updatePassword", method = RequestMethod.POST)
    public ResultInfo updatePassword(@RequestParam(name = "acclogin") String acclogin, @RequestParam(name = "pwd") String pwd, @RequestParam(name = "type") Integer type) {
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            if (loginAdmin == null || StringUtils.isEmpty(loginAdmin.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), " 未登录");
            }
            if (type == null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "类型为空");
            }
            if (StringUtils.isEmpty(acclogin)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "手机号为空");
            }
            if (StringUtils.isEmpty(pwd)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "密码为空");
            }
            response.setData(userService.updatePassword(acclogin, pwd, type, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".updatePassword", "acclogin=" + acclogin + ",pwd=" + pwd + ",type=" + type, loginAdmin);
        } catch (BusinessException e) {
            response = ResultInfo.error("修改密码失败");
            logger.error("{}.updatePassword 失败:{}, params:{}", getClass().getName(), e.getMessage(), "acclogin=" + acclogin + ",pwd=" + pwd + ",type=" + type, e);
        } catch (Exception e) {
            response = ResultInfo.error("修改密码出错");
            logger.error("{}.updatePassword 出错:{}, params:{}", getClass().getName(), e.getMessage(), "acclogin=" + acclogin + ",pwd=" + pwd + ",type=" + type, e);
        }
        return response;
    }

    @Syslog("普通用户-修改备注")
    @ResponseBody
    @RequestMapping(name = "普通用户-修改备注", value = "/updateRemark", method = RequestMethod.POST)
    public ResultInfo updateRemark(@RequestParam(name = "memid") Long memid, @RequestParam(name = "remark") String remark) {
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            if (loginAdmin == null || StringUtils.isEmpty(loginAdmin.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), " 未登录");
            }
            if (null == memid) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "用户id为空");
            }
            response.setData(userService.updateRemark(memid, remark, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".updateRemark", "memid=" + memid + ",remark=" + remark, loginAdmin);
        } catch (BusinessException e) {
            response = ResultInfo.error("修改备注失败");
            logger.error("{}.updateRemark 失败:{}, params:{}", getClass().getName(), e.getMessage(), "memid=" + memid + ",remark=" + remark, e);
        } catch (Exception e) {
            response = ResultInfo.error("修改备注出错");
            logger.error("{}.updateRemark 出错:{}, params:{}", getClass().getName(), e.getMessage(), "memid=" + memid + ",remark=" + remark, e);
        }
        return response;
    }

    @Syslog("普通用户-编辑")
    @ResponseBody
    @RequestMapping(name = "普通用户-编辑", value = "/updateUser", method = RequestMethod.POST)
    public ResultInfo updateUser(UserUpdateRequest req) {
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            req.updateUserCheck(loginAdmin);
            response.setData(userService.updateUser(req, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".updateUser", req, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.updateUser 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("编辑出错");
            logger.error("{}.updateUser 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }


    @Syslog("系统用户禁用启用")
    @ResponseBody
    @RequestMapping(name = "系统用户禁用启用", value = "/doAccstatusSystemUser", method = RequestMethod.POST)
    public ResultInfo doAccstatusSystemUser(UserRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            req.setOperataccno(loginAdmin.getAccno());
            response.setData(userService.doAccstatusSystemUser(req));
            deleteFuzzyMatchCache(RedisKeys.LIVE_SYSTEM_USER);
            LogUtils.logUserModifyOperates(getClass().getName() + ".doAccstatusSystemUser", req, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doAccstatusSystemUser系统用户禁用启用出错,params:{}出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("系统用户禁用启用失败");
            logger.error("{}.doAccstatusSystemUser系统用户禁用启用出错,params:{}出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        }
        logger.info("/doAccstatusSystemUser耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("系统用户删除")
    @ResponseBody
    @RequestMapping(name = "系统用户删除", value = "/doDelSystemUser", method = RequestMethod.POST)
    public ResultInfo doDelSystemUser(UserRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            req.setOperataccno(loginAdmin.getAccno());
            response.setData(userService.doDelSystemUser(req));
            deleteFuzzyMatchCache(RedisKeys.LIVE_SYSTEM_USER);
            LogUtils.logUserModifyOperates(getClass().getName() + ".doDelUser", req, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doDelUser普通用户删除,主播管理删除,系统用户删除出错,params:{}出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("普通用户删除,主播管理删除,系统用户删除失败");
            logger.error("{}.doDelUser普通用户删除,主播管理删除,系统用户删除出错,params:{}出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        }
        logger.info("/doDelUser耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("普通用户-清除头")
    @ResponseBody
    @RequestMapping(name = "普通用户-清除头像", value = "/delUserHeadimg", method = RequestMethod.POST)
    public ResultInfo delUserHeadimg(BankRequest req) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == req.getMemid()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "用户id为空");
            }
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(userService.delUserHeadimg(req, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".delUserHeadimg", req, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.delUserHeadimg 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("普通用户-清除头像出错");
            logger.error("{}.delUserHeadimg 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "普通用户-编辑会员-详情页", value = "/userDetail", method = RequestMethod.GET)
    public ResultInfo userDetailForUpdate(BankRequest req) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == req.getMemid()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "用户id为空");
            }
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(userService.userDetailForUpdate(req, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".userDetailForUpdate", req, loginAdmin);
        } catch (BusinessException e) {
            response = ResultInfo.error("普通用户-编辑会员-详情页失败");
            logger.error("{}.userDetailForUpdate 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("普通用户-编辑会员-详情页出错");
            logger.error("{}.userDetailForUpdate 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }

    @Syslog("修改代理跳转url")
    @ResponseBody
    @RequestMapping(name = "修改代理跳转url", value = "/updateProxyUrl", method = RequestMethod.POST)
    public ResultInfo updateProxyUrl(@RequestParam(name = "memid") Long memid, @RequestParam(name = "proxyUrl") String proxyUrl) {
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            if (loginAdmin == null || org.apache.commons.lang.StringUtils.isEmpty(loginAdmin.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), " 未登录");
            }
            if (null == memid) {
                throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "用户id为空");
            }
            response.setData(userService.updateProxyUrl(memid, proxyUrl, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".updateProxyUrl", "memid=" + memid + ",proxyUrl=" + proxyUrl, loginAdmin);
        } catch (BusinessException e) {
            response = ResultInfo.error("修改代理跳转url失败");
            logger.error("{}.updateProxyUrl 失败:{}, params:{}", getClass().getName(), e.getMessage(), "memid=" + memid + ",proxyUrl=" + proxyUrl, e);
        } catch (Exception e) {
            response = ResultInfo.error("修改代理跳转url出错");
            logger.error("{}.updateProxyUrl 出错:{}, params:{}", getClass().getName(), e.getMessage(), "memid=" + memid + ",proxyUrl=" + proxyUrl, e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "系统用户", value = "/getSystemUserList", method = RequestMethod.GET)
    public ResultInfo systemUserList(UserRequest req, @RequestParam(value = "pageNo") Integer pageNo, @RequestParam(value = "pageSize") Integer pageSize) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(userService.getSystemUserList(req, pageNo, pageSize));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.systemUserList系统用户获取出错,params:{},出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("系统用户获取失败");
            logger.error("{}.systemUserList系统用户获取出错,params:{},出错信息:{}", getClass().getName(), JSONObject.toJSONString(req), e.getMessage(), e);
        }
        logger.info("/systemUserList耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("调整信誉分")
    @ResponseBody
    @RequestMapping(name = "调整信誉分", value = "/modifyCredit", method = RequestMethod.POST)
    public ResultInfo modifyCredit(UserCreditRequest userCreditRequest) {
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            if (loginAdmin == null || StringUtils.isEmpty(loginAdmin.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), " 未登录");
            }
            response.setData(userService.modifyCredit(userCreditRequest, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".modifyCredit", JSONObject.toJSONString(userCreditRequest), loginAdmin);
        } catch (BusinessException e) {
            response = ResultInfo.error(e.getMessage());
            logger.error("{}.updateRemark  modifyCredit 失败:{}, params:{}", getClass().getName(), e.getMessage(),JSONObject.toJSONString(userCreditRequest), e);
        } catch (Exception e) {
            response = ResultInfo.error("调整信誉分出错");
            logger.error("{}.updateRemark modifyCredit 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(userCreditRequest), e);
        }
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "信誉积分调整记录", value = "/creditList", method = RequestMethod.GET)
    public ResultInfo creditList(UserCreditRequest userCreditRequest, @RequestParam(value = "pageNo") Integer pageNo, @RequestParam(value = "pageSize") Integer pageSize) {
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            if (loginAdmin == null || StringUtils.isEmpty(loginAdmin.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), " 未登录");
            }
            response.setData(userService.creditList(userCreditRequest, loginAdmin, pageNo, pageSize));
            LogUtils.logUserModifyOperates(getClass().getName() + ".modifyCredit", JSONObject.toJSONString(userCreditRequest), loginAdmin);
        } catch (BusinessException e) {
            response = ResultInfo.error("信誉积分调整记录失败");
            logger.error("{}.updateRemark  creditList 失败:{}, params:{}", getClass().getName(), e.getMessage(),JSONObject.toJSONString(userCreditRequest), e);
        } catch (Exception e) {
            response = ResultInfo.error("信誉积分调整记录出错");
            logger.error("{}.updateRemark creditList 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(userCreditRequest), e);
        }
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "调整信誉分", value = "/singleModifyCredit", method = RequestMethod.POST)
    public ResultInfo singleModifyCredit(CreditChangeRequest changeRequest) {
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginAdmin = getLoginAdmin();
            if (loginAdmin == null || StringUtils.isEmpty(loginAdmin.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_401.getCode(), " 未登录");
            }
            if(changeRequest.getChangeType().equals(2)){
                changeRequest.setIntegral(changeRequest.getIntegral()*(-1));
            }
            response.setData(userService.singleModifyCredit(changeRequest, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".modifyCredit", JSONObject.toJSONString(changeRequest), loginAdmin);
        } catch (BusinessException e) {
            response = ResultInfo.error(e.getMessage());
            logger.error("{}.updateRemark  singleModifyCredit 失败:{}, params:{}", getClass().getName(), e.getMessage(),JSONObject.toJSONString(changeRequest), e);
        } catch (Exception e) {
            response = ResultInfo.error("调整信誉分出错");
            logger.error("{}.updateRemark singleModifyCredit 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(changeRequest), e);
        }
        return response;
    }



    @GetMapping("/memCreditList")
    @ResponseBody
    public ResultInfo memCreditList(CreditChangeRequest creditRequest, @RequestParam(value = "pageNo") Integer pageNo, @RequestParam(value = "pageSize") Integer pageSize) {
        ResultInfo response = ResultInfo.ok();
        try {
            PageBounds page = new PageBounds();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            response.setData(userService.memCreditList(creditRequest, page));
        } catch (BusinessException e) {
            logger.error("{}/list,出错信息:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error(e.getMessage());
        } catch (Exception e) {
            logger.error("{}/list,出错信息:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
        return response;
    }

}
