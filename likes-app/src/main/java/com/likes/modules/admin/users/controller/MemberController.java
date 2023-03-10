package com.likes.modules.admin.users.controller;

import com.alibaba.excel.util.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.RankDto;
import com.likes.common.model.dto.RankingDto;
import com.likes.common.model.dto.member.MemberVipDTO;
import com.likes.common.model.request.*;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.MemberCreditDetailMapper;
import com.likes.common.service.credit.MemCreditService;
import com.likes.common.service.member.*;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.service.task.TaskCategoryService;
import com.likes.common.util.DateUtils;
import com.likes.common.util.LogUtils;
import com.likes.common.util.SnowflakeIdWorker;
import com.likes.common.util.encrypt.AESUtils;
import com.likes.common.util.redis.RedisBaseUtil;
import com.likes.modules.admin.users.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/member")
@Api(value = "????????????")
public class MemberController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UsersService usersService;
    @Autowired
    private MemberOtherService memberother;
    @Resource
    private MemLevelConfigService memLevelConfigService;

    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemCreditService memCreditService;
    @Resource
    private MemBankaccountService memBankaccountService;
    @Resource
    private TaskCategoryService taskCategoryService;
    @Resource
    private MemLevelService memLevelService;
    @Resource
    private SysBusParamService sysBusParamService;

    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "?????????????????????", value = "/getSysParam", method = RequestMethod.GET)
    public ResultInfo getSysParam(String code) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.usersService.getSysParam(code));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getSysParam ??????:{}, params:{}", getClass().getName(), e.getMessage(), code, e);
        } catch (Exception e) {
            response = ResultInfo.error("???????????????????????????");
            logger.error("{}.getSysParam ??????:{}, params:{}", getClass().getName(), e.getMessage(), code, e);
        }
        logger.info("/getSysParam??????{}??????", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "app??????????????????", value = "/getLink")
    public ResultInfo getLink() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(this.usersService.getLink(loginUserAPP));
            LogUtils.logUserModifyOperates(getClass().getName() + ".registerAnchor", loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getLink ??????:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("app????????????????????????");
            logger.error("{}.getLink ??????:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getLink??????{}??????", (System.currentTimeMillis() - start));
        return response;
    }

    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "H5??????????????????", value = "/invite/{param}", method = RequestMethod.GET)
    public ResultInfo invite(@PathVariable("param") String param) throws IOException {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(param) || "null".equals(param)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_141.getCode(), "???????????????");
            }
            String invitecode = AESUtils.decryptData(param, AESUtils.KEY);
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("invitecode", invitecode);
            response.setData(dataMap);
        } catch (Exception e) {
            response = ResultInfo.error("H5????????????????????????");
            logger.error("{}.invite ??????:{}, params:{}", getClass().getName(), e.getMessage(), param, e);
        }
        logger.info("/invite??????{}??????", (System.currentTimeMillis() - start));
        return response;
    }

    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "APP????????????", value = "/appdownurl", method = RequestMethod.GET)
    public ResultInfo appdownurl() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(usersService.appdownurl());
        } catch (Exception e) {
            response = ResultInfo.error("H5????????????????????????");
            logger.error("{}.appdownurl ??????:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/appdownurl??????{}??????", (System.currentTimeMillis() - start));
        return response;
    }

    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "app??????", value = "/updateapp", method = RequestMethod.GET)
    public ResultInfo updateapp() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(usersService.updateapp());
        } catch (Exception e) {
            response = ResultInfo.error("app????????????");
            logger.error("{}.updateapp ??????:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/updateapp??????{}??????", (System.currentTimeMillis() - start));
        return response;
    }

    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "???????????????????????? ", value = "/levelConfig", method = RequestMethod.GET)
    @ApiOperation(value = "????????????????????????", notes = "????????????????????????")
    public ResultInfo levelConfig() {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            response = ResultInfo.ok(memLevelConfigService.memLevelConfigAlllist());
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.member/levelConfig,????????????:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            logger.error("{}.member/levelConfig??????????????????????????????,????????????:{}", getClass().getName(), e.getMessage(), e);
            response.setResultInfo("100000001", null);
        }
        logger.info("/member/levelConfig??????{}?????????", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "?????????????????? ", value = "/memberLevel", method = RequestMethod.GET)
    @ApiOperation(value = "??????????????????", notes = "??????????????????")
    public ResultInfo memberLevel() {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response = ResultInfo.ok(memLevelConfigService.getMemLevelConfig(loginUserAPP.getAccno()));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.member/memberLevel,????????????:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response.setResultInfo("100000001", null);
            logger.error("{}.member/memberLevel,????????????:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/member/memberLevel??????{}?????????", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "????????????VIP???????????? ", value = "/getVipAmount", method = RequestMethod.GET)
    public ResultInfo getVipAmount(ReplaceChargeReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            LoginUser loginUser = getLoginUserAPP();
            response = ResultInfo.ok(memLevelService.getVipAmount(loginUser, req.getLevelId()));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.member/getVipAmount,????????????:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("????????????VIP????????????");
            logger.error("{}.member/getVipAmount???????????????VIP??????,????????????:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/member/getVipAmount??????{}?????????", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "??????vip ", value = "/buyVip", method = RequestMethod.POST)
    public ResultInfo buyVip(ReplaceChargeReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            BigDecimal amount = req.getAmount();
            LoginUser loginUser = getLoginUserAPP();
            // ????????????????????????????????????????????????
//            MemLevelConfig memLevelConfig=memLevelConfigService.getNotExpireUserMaxLevelInfoByAccno(loginUser.getAccno());
//            if((req.getLevelSeq()-memLevelConfig.getLevelSeq())>1){
//                throw new BusinessException(StatusCode.ACROSS_THE_LEVEL_130013.getCode(),StatusCode.ACROSS_THE_LEVEL_130013.getValue());
//            }
            MemBaseinfo chongzhiBaseinfo = memBaseinfoService.getUserByAccno(loginUser.getAccno());

            Integer xuyaoAmount = memLevelService.getVipAmount(loginUser, req.getLevelId());
            if (chongzhiBaseinfo.getGoldnum().intValue() < xuyaoAmount) {
                throw new BusinessException("????????????");
            }
            Date nowDate = new Date();
            String orderno = SnowflakeIdWorker.generateShortId();
            TraOrderinfom traOrderinfom = new TraOrderinfom();
            traOrderinfom.setMealid(null);
            //???????????????
            traOrderinfom.setOrdertype(Constants.ORDERTYPE16);
            traOrderinfom.setOrderno(orderno);
            traOrderinfom.setAccno(loginUser.getAccno());
            traOrderinfom.setPaycode(null);
            traOrderinfom.setOrderdate(nowDate);
            traOrderinfom.setBuyVip(req.getLevelSeq());

            // ?????????????????? 1 ??? ????????? 23:59:00???
            Date twoDayAfter = DateUtils.afterDays(nowDate, 1);
            String twoDayAfterString = DateUtils.formatDate(twoDayAfter, "yyyy-MM-dd HH:mm:ss");
            String expiredateString = twoDayAfterString.substring(0, 10).concat(" 23:59:00");
            try {
                traOrderinfom.setExpiredate(DateUtils.parseDate(expiredateString, "yyyy-MM-dd HH:mm:ss"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            traOrderinfom.setOldamt(amount);
            //??????????????????????????????????????????
            traOrderinfom.setSumamt(amount);
            traOrderinfom.setRealamt(amount);

            traOrderinfom.setIsinvoice(9);
            traOrderinfom.setOrderstatus(Constants.ORDER_ORD15);
            traOrderinfom.setAccountstatus(Constants.ORDER_ACC04);
            traOrderinfom.setOrdernote("??????[" + loginUser.getNickname() + "]??????vip: ");
            traOrderinfom.setPaydate(nowDate);
            response = ResultInfo.ok(memLevelService.buyVIPLevel(chongzhiBaseinfo, traOrderinfom, loginUser, req.getLevelId()));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.member/buyVip,????????????:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("??????????????????");
            logger.error("{}.member/buyVip?????????????????????,????????????:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/member/buyVip?????????{}?????????", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "??????????????????", value = "/updateMemname", method = RequestMethod.GET)
    public ResultInfo updateapp(String memname) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();

            response.setData(memBaseinfoService.updateMemNameById(memname, loginUserAPP.getMemid()));
        } catch (Exception e) {
            response = ResultInfo.error("app????????????");
            logger.error("{}.updateapp ??????:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/updateapp??????{}??????", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @AllowAccess
    @RequestMapping(name = "????????????", value = "/ranking", method = RequestMethod.GET)
    public ResultInfo ranking(TaskCategoryRequest request) {
        ResultInfo response = ResultInfo.ok();
        try {
            List<RankDto> resultList = null;
            List<RankDto> redisList = RedisBaseUtil.get("likes_ranking2");
            redisList.forEach(item -> {
                item.setUserName(Desensitization.emailDesensitization(item.getUserName()));
            });
            if (!CollectionUtils.isEmpty(redisList)) {
                resultList = redisList.stream().sorted(Comparator.comparing(RankDto::getUserName)).collect(Collectors.toList());
            }
            response.setData(resultList);
        } catch (Exception e) {
            response = ResultInfo.error("app????????????");
            logger.error("{}.isBindRelated ??????:{}", getClass().getName(), e.getMessage(), e);
        }
        return response;
    }

    @Resource
    private MemberCreditDetailMapper memberCreditDetailMapper;

    @ResponseBody
    @RequestMapping(name = "?????????????????? ", value = "/memberCreditDetailLst", method = RequestMethod.GET)
    public ResultInfo memberCreditDetailLst(BaseRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            List<MemberCreditDetail> lst = memberCreditDetailMapper.getMemberCreditDeailList(loginUserAPP.getAccno());
            PageHelper.startPage(req.getPageNo(), req.getPageSize());
            PageInfo pageInfo = new PageInfo(lst);
            response = ResultInfo.ok(pageInfo);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.memberCreditDetailLst??????????????????,????????????:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("??????????????????????????????");
            logger.error("{}.memberCreditDetailLst??????????????????,????????????:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/memberCreditDetailLst??????{}?????????", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "??????????????????", value = "/getMemberCredit", method = RequestMethod.GET)
    public ResultInfo getMemberCredit() {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response = ResultInfo.ok(memCreditService.selectCreditByMemNo(loginUserAPP.getAccno()));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getMemberCredit??????????????????,????????????:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("??????????????????????????????");
            logger.error("{}.getMemberCredit??????????????????,????????????:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getMemberCredit??????{}?????????", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "????????????", value = "/getTeamReport", method = RequestMethod.GET)
    public ResultInfo getTeamReport(FundsRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response = ResultInfo.ok(memberother.appTeamList(req, loginUserAPP.getAccno()));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getDayReport,????????????:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("??????????????????");
            logger.error("{}.getDayReport,????????????:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getDayReport{}?????????", (System.currentTimeMillis() - start));
        return response;
    }



    @ResponseBody
    @RequestMapping(name = "????????????", value = "/appTeamReport", method = RequestMethod.GET)
    public ResultInfo appTeamReport(AppTeamReportRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response = ResultInfo.ok(memberother.appTeamReport(req, loginUserAPP));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getDayReport,????????????:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("??????????????????");
            logger.error("{}.getDayReport,????????????:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getDayReport{}?????????", (System.currentTimeMillis() - start));
        return response;
    }




    public static String getRandom() {
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; i++) {
                fourRandom = "0" + fourRandom;
            }
        }
        return fourRandom;
    }

    @ResponseBody
    @RequestMapping(name = "??????????????????", value = "/MemberDayReport", method = RequestMethod.GET)
    public ResultInfo getMemberReportDto() {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response = ResultInfo.ok(memberother.getMemberReportDto(loginUserAPP.getAccno()));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getMemberReportDto,????????????:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("????????????");
            logger.error("{}.getMemberReportDto,????????????:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getMemberReportDto{}?????????", (System.currentTimeMillis() - start));
        return response;
    }


}

