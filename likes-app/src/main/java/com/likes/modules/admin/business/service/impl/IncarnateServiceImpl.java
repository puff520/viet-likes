package com.likes.modules.admin.business.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.likes.common.constant.Constants;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.pay.MemBankAccountTypeEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.member.MemBankResponse;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.dto.order.OrderRequest;
import com.likes.common.model.request.IncarnateRequest;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.model.response.IncarnateRecordResponse;
import com.likes.common.model.response.level.MemberLevelResponse;
import com.likes.common.model.vo.finance.MemFinanceVO;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.UdunOrderMapper;
import com.likes.common.mybatis.mapperext.tra.TraOrderinfomMapperExt;
import com.likes.common.service.credit.MemCreditService;
import com.likes.common.service.member.*;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.service.money.SysPaysetService;
import com.likes.common.service.money.TraApplyauditService;
import com.likes.common.service.money.TraApplycashService;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.service.money.TraOrdertrackingService;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.BeanUtils;
import com.likes.common.util.SnowflakeIdWorker;
import com.likes.common.util.SourceUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.business.service.IncarnateService;
import com.github.pagehelper.Page;
import com.likes.modules.admin.pay.service.MemWalletService;
import com.uduncloud.sdk.client.UdunClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

@Service
public class IncarnateServiceImpl implements IncarnateService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysParamService sysParamService;
    @Resource
    private MemGoldchangeService memGoldchangeService;
    @Resource
    private TraOrderinfomService traOrderinfomMapperService;
    @Resource
    private TraOrdertrackingService traOrdertrackingMapperService;
    @Resource
    private SysBusParamService sysBusParamService;
    @Resource
    private TraApplycashService applycashMapperService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemLoginService memLoginService;
    @Resource
    private MemBankaccountService memBankaccountService;
    @Resource
    private SysPaysetService sysPaysetService;
    @Resource
    private TraApplyauditService traApplyauditMapperService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemBaseinfoWriteService memBaseinfoWriteService;
    @Autowired
    private TraOrderinfomMapperExt traOrderinfomMapperExt;
    @Resource
    UdunClient udunClient;
    @Resource
    private MemWalletService memWalletService;
    @Resource
    private MemLevelConfigService memLevelConfigService;
    @Resource
    private MemCreditService memCreditService;

    @Override
    public PageResult anchorIncarnateRecord(LoginUser loginUserAPP, PageBounds page) {
        Page<IncarnateRecordResponse> list = applycashMapperService.getIncarnateRecordList(loginUserAPP.getAccno(), page.toRowBounds());
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(o -> {
                // ???????????? 1????????? 2?????? 3??????
                int accounttype = o.getAccounttype();
                o.setAccounttypename(MemBankAccountTypeEnum.valueOf(accounttype).getName());
                if (MemBankAccountTypeEnum.NETBANK.getValue().equals(accounttype)) {
                    String name = o.getAccountno().substring(o.getAccountno().length() - 4);
                    o.setAccountno(name);
                    // ????????????
                    SysBusparameter sysBusparameter = sysBusParamService.selectByBusparamcode(o.getBankname());
                    if (sysBusparameter != null) {
                        o.setBanknamealias(sysBusparameter.getBusparamname());
                    }
                }

                Double shouxufei = o.getApycgold().doubleValue() - o.getApycamt().doubleValue();
                o.setShouxufei(new BigDecimal(shouxufei));
                if (o.getApycstatus().equals(Constants.APYCSTATUS3)) {
                    String note = traOrderinfomMapperService.findNoteById(o.getOrderid());
                    if (com.likes.common.util.StringUtils.isNotEmpty(note)) {
                        o.setOrdernote(note);
                    } else {
                        o.setOrdernote("????????????");
                    }
                }

            });
        }
        return PageResult.getPageResult((int) list.getTotal(), page, list);
    }

    @Override
    public Map<String, Object> anchorBank(LoginUser loginUserAPP) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        // ???????????????

        //AnchorMemBankaccount anchorMemBankAccount = anchorMemBankAccountRest.getAnchorMemBankAccount(loginUserAPP.getAccno());

        MemBankaccount memBankaccount = memBankaccountService.findByAccno(loginUserAPP.getAccno());
        if (Objects.nonNull(memBankaccount)) {
            MemBankResponse o = new MemBankResponse();
            BeanUtils.copyProperties(memBankaccount, o);
            o.setReaccountno(memBankaccount.getAccountno());
            // ???????????? 1????????? 2?????? 3??????
            int accounttype = o.getAccounttype();
            if (Constants.ACCOUNTTYPE_ALIPAY == accounttype) {
                o.setAccounttypename("???????????????");
                String name = o.getAccountno().substring(0, 3) + "***" + o.getAccountno().substring(o.getAccountno().length() - 4);
                o.setAccountno(name);
            } else if (Constants.ACCOUNTTYPE_WECHAT == accounttype) {
                o.setAccounttypename("????????????");
                String name = o.getAccountno().substring(0, 3) + "***" + o.getAccountno().substring(o.getAccountno().length() - 4);
                o.setAccountno(name);
            } else if (Constants.ACCOUNTTYPE_UNIONPAY == accounttype) {
                o.setAccounttypename("??????");
                String name = o.getAccountno().substring(0, 3) + "***" + o.getAccountno().substring(o.getAccountno().length() - 4);
                o.setAccountno(name);
                // ????????????
                SysBusparameter sysBusparameter = sysBusParamService.selectByBusparamcode(o.getBankname());
                if (sysBusparameter != null) {
                    o.setBanknamealias(sysBusparameter.getBusparamname());
                }
            }

            dataMap.put("bank", o);
        } else {
            dataMap.put("bank", null);
        }

        // ?????????????????????
        // 5.1????????????????????????????????????
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setAccno(loginUserAPP.getAccno());
        List<String> orderstatusList = new ArrayList<String>();
        // ord05???????????? ord07???????????????
        orderstatusList.add(Constants.ORDER_ORD05);
        orderstatusList.add(Constants.ORDER_ORD07);
        orderRequest.setOrderstatusList(orderstatusList);
        TraOrderinfom existOrderinfom = traOrderinfomMapperService.existOrderinfom(orderRequest);
        if (existOrderinfom != null) {
            dataMap.put("orderstatus", existOrderinfom.getOrderstatus());
            dataMap.put("realamt", existOrderinfom.getRealamt());
        } else {
            dataMap.put("orderstatus", null);
            dataMap.put("realamt", null);
        }

        return dataMap;
    }

    @Override
    public Long doSetAnchorBank(LoginUser loginUserAPP, MemBankaccount memBankaccount) {
        boolean b = udunClient.checkAddress("195", memBankaccount.getBankaddress());
        if (!b) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11008.getCode(), "??????????????????????????????!");
        }
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setAccno(loginUserAPP.getAccno());
        List<String> orderstatusList = new ArrayList<>();
        // ord05???????????? ord07???????????????
        orderstatusList.add(Constants.ORDER_ORD05);
        orderstatusList.add(Constants.ORDER_ORD07);
        orderRequest.setOrderstatusList(orderstatusList);
        TraOrderinfom existOrderinfom = traOrderinfomMapperService.existOrderinfom(orderRequest);
        if (existOrderinfom != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "??????????????????,????????????????????????");
        }

        Long bankaccid;
        MemBankaccount bankaccount = memBankaccountService.findBankByAccno(loginUserAPP.getAccno());
        if (bankaccount != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_114.getCode(), "????????????????????????");
        }
//        MemBankaccount bankAddress = memBankaccountService.findBankByAddress(memBankaccount.getBankaddress());
//        if (bankAddress != null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_114.getCode(), "?????????????????????");
//        }
        // ??????????????????
        memBankaccount.setAccno(loginUserAPP.getAccno());
        memBankaccount.setCheckstatus(Constants.CHECKSTATUS_8);
        memBankaccount.setCreateUser(loginUserAPP.getAccno());
        memBankaccount.setUpdateUser(loginUserAPP.getAccno());

        int i = memBankaccountService.insertBank(memBankaccount);
        if (i > 0) {
            bankaccid = memBankaccount.getBankaccid();
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_114.getCode(), "????????????????????????");
        }
        return bankaccid;
    }

    @Override
    public Long reSetAnchorBank(LoginUser loginUserAPP, MemBankaccount memBankaccount) {
        boolean b = udunClient.checkAddress("195", memBankaccount.getBankaddress());
        if (!b) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11008.getCode(), "??????????????????????????????!");
        }
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setAccno(loginUserAPP.getAccno());
        List<String> orderstatusList = new ArrayList<String>();
        // ord05???????????? ord07???????????????
        orderstatusList.add(Constants.ORDER_ORD05);
        orderstatusList.add(Constants.ORDER_ORD07);
        orderRequest.setOrderstatusList(orderstatusList);
        TraOrderinfom existOrderinfom = traOrderinfomMapperService.existOrderinfom(orderRequest);
        if (existOrderinfom != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "??????????????????,????????????????????????");
        }
        Long bankaccid;
        MemBankaccount bankaccount = memBankaccountService.findBankByAccno(loginUserAPP.getAccno());
        if (bankaccount == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_107.getCode(), "????????????????????????????????????");
        }
//        MemBankaccount bankAddress = memBankaccountService.findBankByAddress(memBankaccount.getBankaddress());
//        if (bankAddress != null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_114.getCode(), "?????????????????????");
//        }
        // ??????????????????
        bankaccount.setUpdateUser(loginUserAPP.getAccno());
        bankaccount.setAccountno(memBankaccount.getAccountno());
        bankaccount.setAccounttype(memBankaccount.getAccounttype());
        bankaccount.setBankname(memBankaccount.getBankname());
        bankaccount.setBankaddress(memBankaccount.getBankaddress());
        int i = memBankaccountService.reset(bankaccount);
        if (i > 0) {
            bankaccid = bankaccount.getBankaccid();
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "????????????????????????");
        }
        return bankaccid;
    }


    private void checkMoney(BigDecimal apycamt) {
        Double apycamtInteger = apycamt.doubleValue();
        if (!(apycamtInteger > 0)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_995.getCode(), "??????????????????0");
        }
        if ((apycamtInteger % 1) > 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_996.getCode(), "??????????????????");
        }
    }


    // ------------------------------------------------v2-----------------------------------------------------------------------------------------
    @Override
    public String surep(LoginUser loginUserAPP, IncarnateRequest req) {
        if (StringUtils.isEmpty(req.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11002.getCode(), "??????????????????");
        }
        // ??????????????????
        MemLogin memLogin = memLoginService.findByAccno(loginUserAPP.getAccno());
        if (StringUtils.isEmpty(memLogin.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11000.getCode(), "?????????????????????????????????");
        }
        if (!req.getPaypassword().equals(memLogin.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11001.getCode(), "?????????????????????");
        }
        return Constants.SUCCESS_MSG;
    }

    @Override
    //@Transactional(rollbackFor = Exception.class)
    public MemFinanceVO anchorIncarnateDataV2(LoginUser loginUserAPP) {
        // ?????????????????????
        return tixianshujuallmoney(loginUserAPP);
    }

    /**
     * ???????????? ???????????????
     *
     * @param loginUserAPP
     * @return
     */
    private MemFinanceVO tixianshujuallmoney(LoginUser loginUserAPP) {
        MemFinanceVO memFinanceVO = new MemFinanceVO();
        memFinanceVO.setIshavepay(0);
        // ????????????????????????????????????
        MemLogin memLogin = memLoginService.findByAccno(loginUserAPP.getAccno());
        if (StringUtils.isEmpty(memLogin.getPaypassword())) {
            memFinanceVO.setIshavepay(9);
        }

        // ??????????????????
        SysPayset gongsisysPayset = sysPaysetService.getUseOne(2);
        if (gongsisysPayset == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1111.getCode(), "????????????????????????");
        }
        memFinanceVO.setMaxchargeamt(gongsisysPayset.getMaxchargeamt());
        memFinanceVO.setMinchargeamt(gongsisysPayset.getMinchargeamt());
        // 1.?????? ??????????????? + ????????????????????? + ???????????? + ??????
        // ??????????????????
        // ????????????(??????)
        UsersRequest param = new UsersRequest();
        param.setAccno(loginUserAPP.getAccno());
        List<Integer> changetypeList = new ArrayList<Integer>();
        // ?????? + ???????????? + ????????????
        changetypeList.add(GoldchangeEnum.RECHARGE.getValue());
        changetypeList.add(GoldchangeEnum.DELIVER.getValue());
        changetypeList.add(GoldchangeEnum.ATTENDANCE_AWARD.getValue());
        changetypeList.add(GoldchangeEnum.POSTING_AWARD.getValue());
        changetypeList.add(GoldchangeEnum.SEND_VIDEO_REWARDS.getValue());
        changetypeList.add(GoldchangeEnum.INVITE_USERS.getValue());
        changetypeList.add(GoldchangeEnum.RECHARGE_BONUS.getValue());
        changetypeList.add(GoldchangeEnum.ANCHOR_SPLIT.getValue());
        changetypeList.add(GoldchangeEnum.LOTTERY_PRIZE.getValue());
        changetypeList.add(GoldchangeEnum.MANUALLY_INCOME_MONEY.getValue());
        param.setChangetypeList(changetypeList);

        // ?????? ??????
        // ????????????(??????)
        Double allliveincome = memGoldchangeService.getAllQuantityByType(param);
        memFinanceVO.setAllliveincome(getTradeOffAmount(new BigDecimal(allliveincome)));


        //????????????
        // ?????????????????? ??????3???????????????
        Integer freechargenums = gongsisysPayset.getFreechargenums();
        // ???????????????????????? 0??????????????????
        BigDecimal servicechargeDouble = gongsisysPayset.getServicecharge().setScale(3, BigDecimal.ROUND_HALF_DOWN);
        memFinanceVO.setShouxufei(BigDecimal.ZERO);

        //// ?????????????????????
        // ???????????? ?????? ????????????????????? ??? ?????? ?????????/???????????? ????????? ???????????? ?????????
        List<TraOrderinfom> yitixianList = getTraOrderinfomsForPay(loginUserAPP);

        if (CollectionUtils.isNotEmpty(yitixianList) && yitixianList.size() < freechargenums) {
            memFinanceVO.setShouxufei(servicechargeDouble);
        }


        // ??????????????????????????? ????????????????????????
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(loginUserAPP.getAccno());

        if (memBaseinfo == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "???????????????");
        }
        // ???????????????
        memFinanceVO.setAllincarnate(getTradeOffAmount(memBaseinfo.getWithdrawalAmount()));
        memFinanceVO.setHaixudamaliang(memBaseinfo.getNoWithdrawalAmount());

        // ?????? ?????? ????????? + ????????? ???????????? ???????????? ????????????

        memFinanceVO.setGoldnum(memBaseinfo.getGoldnum().setScale(3, BigDecimal.ROUND_HALF_DOWN));


        return memFinanceVO;

    }

    private List<TraOrderinfom> getTraOrderinfomsForPay(LoginUser loginUserAPP) {
        OrderRequest yitixianRequest = new OrderRequest();
        yitixianRequest.setAccno(loginUserAPP.getAccno());
        List<String> orderstatusList = new ArrayList<String>();
        orderstatusList.add(Constants.ORDER_ORD07);
        orderstatusList.add(Constants.ORDER_ORD12);
        yitixianRequest.setOrderstatusList(orderstatusList);
        //??????????????????
        return traOrderinfomMapperService.findOrderByAccnoAndStatus(yitixianRequest);
    }


    /**
     * ??????????????????????????????@Transactional???????????????????????????????????????????????????
     * ????????????????????????(??????@Transactional)??????????????????????????????????????????????????????????????????(??????@Transactional)?????????????????????????????????????????????????????????
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String doIncarnateV2(LoginUser loginUserAPP, IncarnateRequest req) {
        try {
            logger.info("{}doIncarnateV2 entry user:{}, req:{}, local ip:{}", loginUserAPP.getMemid(), JSONObject.toJSONString(loginUserAPP), JSONObject.toJSONString(req), InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            logger.error("{}doIncarnateV2 ocuur error. user:{}, req:{}", loginUserAPP.getMemid(), JSONObject.toJSONString(loginUserAPP), JSONObject.toJSONString(req), e);
        }

        if (null == req.getApycamt()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1104.getCode(), "??????????????????");
        }
        if (req.getApycamt().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1105.getCode(), "???????????????????????????");
        }
        if (StringUtils.isEmpty(req.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11002.getCode(), "??????????????????");
        }
        if (new BigDecimal(req.getApycamt().intValue()).compareTo(req.getApycamt()) != 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1107.getCode(), "???????????????????????????");
        }
        Integer xyf = memCreditService.selectCreditByMemNo(loginUserAPP.getAccno());
        if (xyf < 60) {
            throw new BusinessException(StatusCode.LIVE_ERROR_160.getCode(), "???????????????,????????????????????????");
        }
        MemberLevelResponse response = memLevelConfigService.getMemLevelConfig(loginUserAPP.getAccno());
        if (response.equals(null) || response.getLevelSeq() < 1) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1107.getCode(), "vip0??????????????????");
        }
        MemBankaccount bankaccount = memBankaccountService.findBankByAccno(loginUserAPP.getAccno());
        if (ObjectUtil.isNull(bankaccount) || StringUtils.isBlank(bankaccount.getBankaddress())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1199.getCode(), "????????????????????????");
        }
        req.setMoneyAddress(bankaccount.getBankaddress());
        DzCoin dzCoin = memWalletService.getCoinType(req.getCoinName());
        memWalletService.checkAddress(dzCoin.getMainCoinType(), req.getMoneyAddress());
//        SysPayset syspayset = sysPaysetService.getUseOne(2);
//        if (ObjectUtils.isEmpty(syspayset)) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1103.getCode(), "?????????????????????");
//        }
//        if (syspayset.getMaxchargeamt() != null && syspayset.getMaxchargeamt().compareTo(BigDecimal.ZERO) == 1 && syspayset.getMaxchargeamt().compareTo(req.getApycamt()) == -1) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_130001.getCode(), "???????????????????????????");
//        }
//        if (syspayset.getMinchargeamt() != null && syspayset.getMinchargeamt().compareTo(BigDecimal.ZERO) == 1 && syspayset.getMinchargeamt().compareTo(req.getApycamt()) == 1) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_130002.getCode(), "???????????????????????????");
//        }

//        Integer integral = memCreditService.selectCreditByMemNo(loginUserAPP.getAccno());
//        if(integral < 50){
//            throw new BusinessException(StatusCode.LIVE_ERROR_130007.getCode(), "????????????????????????50?????????????????????");
//        }
        // 1.?????????????????????1??????????????????????????????????????????
        this.checkMoney(req.getApycamt());
        // ????????????????????? ??????????????????
        // 2.??????????????????
        MemLogin memLogin = memLoginService.findByAccno(loginUserAPP.getAccno());
        if (!req.getPaypassword().equals(memLogin.getPasswordmd5())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11001.getCode(), "?????????????????????");
        }
        // 3.????????????????????????
        TraApplycash traApplycash = applycashMapperService.findNotInCashByCashByAccno(loginUserAPP.getAccno());
        if (traApplycash != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "??????????????????");
        }
        MemBaseinfo membaseinfo = memBaseinfoService.getUserByAccno(loginUserAPP.getAccno());
        // ????????????
        double xiangti = req.getApycamt().doubleValue();
        int sxfInt = 2;

        Integer memLevel = response.getLevelSeq();
        Integer applyAmount = req.getApycamt().intValue();
        if (memLevel.equals(1) && applyAmount > 50) {
            sxfInt = 0;
        } else if (memLevel.equals(2) && applyAmount > 100) {
            sxfInt = 0;
        } else if (memLevel.equals(3) && applyAmount > 200) {
            sxfInt = 0;
        } else if (memLevel.equals(4) && applyAmount > 500) {
            sxfInt = 0;
        } else if (memLevel.equals(5) && applyAmount > 1000) {
            sxfInt = 0;
        } else if (memLevel.equals(6) && applyAmount > 2000) {
            sxfInt = 0;
        }

        // ????????????????????? ??????????????????????????????0???????????? ????????????0,???????????????0
        double incarnatemoney = membaseinfo.getGoldnum().doubleValue();
        Double haixudamaliang = membaseinfo.getNoWithdrawalAmount().doubleValue();
        // ???????????????????????? ??? ??????????????????
        int a = (int) incarnatemoney;
        int b = (int) xiangti + sxfInt;
        if (a < b) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11006.getCode(), "????????????");
        }
        //????????????????????????
        SysBusparameter f_min_w = this.sysBusParamService.selectByBusparamcode("f_min_w");
        int todayWnum = traOrderinfomMapperExt.countTodayWithdrawal(loginUserAPP.getAccno());

        if (xiangti < Double.valueOf(f_min_w.getBusparamname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11004.getCode(), "????????????????????????????????????!");
        }
        //??????????????????
        SysBusparameter day_w_num = this.sysBusParamService.selectByBusparamcode("day_w_num");
        if (todayWnum >= Integer.parseInt(day_w_num.getBusparamname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11007.getCode(), "????????????????????????!");
        }
        Double sxf = Double.valueOf(sxfInt);


        // ?????? ?????? / ???????????? / ????????????/ ??????????????????/???????????? / ??????????????????
        double sumamt = xiangti;
        String businessId = SnowflakeIdWorker.generateShortId();
        // ????????????
        Long orderid = doCreateTixianOrder(req, businessId, loginUserAPP, xiangti, sxf, 0);

        // ??????????????????
        this.doCreateTraApplycashV2(req, loginUserAPP, orderid, sumamt, null, 0D, haixudamaliang);
        // ????????????
        req.setApycamt(BigDecimal.ZERO.subtract(req.getApycamt()));
        req.setUserId(membaseinfo.getMemid().intValue());
        this.updateMemGoldchangeApplycashIncarnateV2(req, loginUserAPP, orderid, xiangti, sxf, 0);
        RedisBusinessUtil.delIncarnateOrderListCahce();

        SysBusparameter w_audit_amout = this.sysBusParamService.selectByBusparamcode("w_audit_amout");
        boolean udunFLag = new BigDecimal(sumamt).intValue() < Integer.parseInt(w_audit_amout.getBusparamname());
        if (StringUtils.isNotBlank(req.getCoinName()) && udunFLag) {
            boolean flag = memWalletService.submitWithdraw(req.getCoinName(), businessId, new BigDecimal(sumamt), req.getMoneyAddress(), loginUserAPP);
            if (!flag) {
                throw new RuntimeException("??????????????????");
            }
        }
        return Constants.SUCCESS_MSG;
    }

    private void updateMemGoldchangeApplycashIncarnateV2(IncarnateRequest req, LoginUser loginUserAPP, Long orderid, double xiangti, double shouxufei, double xingzhengfei) {
        MemBaseinfo baseinfo = memBaseinfoService.selectByPrimaryKey(loginUserAPP.getMemid());
        req.setApycamt(req.getApycamt().subtract(new BigDecimal(shouxufei)));
        Jian(req, loginUserAPP, orderid, GoldchangeEnum.WITHDRAWAL_APPLY.getValue(), "????????????", baseinfo.getNoWithdrawalAmount());
        // 2.
        double allfei = shouxufei + xingzhengfei;
        String opnote = "";
        // ???????????????
        if (shouxufei > 0) {
            opnote += "?????????(" + shouxufei + ") ";
        }
        if (xingzhengfei > 0) {
            opnote += "?????????(" + xingzhengfei + ") ";
        }
        if (allfei > 0) {
            logger.info(opnote);
            req.setApycamt(new BigDecimal(allfei));
        }
    }


    private void Jian(IncarnateRequest req, LoginUser loginUserAPP, Long orderid, Integer CHANGETYPE, String opnote, BigDecimal noWithdrawalAmount) {
        MemGoldchangeDO memGoldChange = new MemGoldchangeDO();
        memGoldChange.setNoWithdrawalAmount(getTradeOffAmount(noWithdrawalAmount));
        memGoldChange.setShowChange(getTradeOffAmount(req.getApycamt()));
        memGoldChange.setChangetype(CHANGETYPE);
        memGoldChange.setQuantity(getTradeOffAmount(req.getApycamt().multiply(new BigDecimal(Constants.CHONGZHIBILIE))));
        memGoldChange.setRefid(orderid);
        memGoldChange.setAccno(loginUserAPP.getAccno());
        memGoldChange.setAmount(getTradeOffAmount(req.getApycamt()));
        memGoldChange.setCreateUser(loginUserAPP.getAccno());
        memGoldChange.setUpdateUser(loginUserAPP.getAccno());
        memGoldChange.setOpnote(opnote);
        memGoldChange.setUserId(req.getUserId());
        memBaseinfoWriteService.updateUserBalance(memGoldChange);

    }

    private Long doCreateTraApplycashV2(IncarnateRequest req, LoginUser loginUserAPP, Long orderid, double sumamt, Long paysetid, Double xingzhenfei, Double haixudamaliang) {
        // Integer jineInteger = req.getApycamt().intValue();
        TraApplycash traApplycash = new TraApplycash();
        traApplycash.setBankaccid(req.getBankaccid());
        traApplycash.setOrderid(orderid);
        traApplycash.setAccno(loginUserAPP.getAccno());
        traApplycash.setApycdate(new Date());
        traApplycash.setApycgold(getTradeOffAmount(new BigDecimal(sumamt)));
        traApplycash.setApycamt(getTradeOffAmount(req.getApycamt()));
        traApplycash.setApycstatus(Constants.APYCSTATUS1);
        traApplycash.setXingzhengfei(getTradeOffAmount(new BigDecimal(xingzhenfei)));
        traApplycash.setDamaliang(getTradeOffAmount(new BigDecimal(haixudamaliang)));
        MemBaseinfo baseinfo = memBaseinfoService.selectByPrimaryKey(loginUserAPP.getMemid());
        traApplycash.setBetAmount(baseinfo.getBetAmount());
        traApplycash.setNoWithdrawalAmount(baseinfo.getNoWithdrawalAmount());
        //traApplycash.setPaysetid(paysetid);
        traApplycash.setPaymemname(null);
        traApplycash.setPaydate(null);
        traApplycash.setCreateUser(loginUserAPP.getAccno());
        traApplycash.setUpdateUser(loginUserAPP.getAccno());

        // ??????
        int i = applycashMapperService.doInsertIncarnate(traApplycash);
        if (!(i > 0)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "??????????????????");
        }
        return traApplycash.getApycid();
    }


    /**
     * ??????????????????
     *
     * @param loginUserAPP
     * @param xiangti
     * @param shouxufei
     * @param xingzhengfei
     */
    private Long doCreateTixianOrder(IncarnateRequest req, String businessId, LoginUser loginUserAPP, double xiangti, double shouxufei, double xingzhengfei) {
        // ???????????????
        double sumamt = xiangti + shouxufei + xingzhengfei;
        // ????????????
        double realamt = xiangti;

        Date nowDate = new Date();
        // ????????????
        TraOrderinfom traOrderinfom = new TraOrderinfom();
        traOrderinfom.setMealid(null);
        traOrderinfom.setOrdertype(Constants.ORDERTYPE4);
        traOrderinfom.setOrderno(null);
        traOrderinfom.setAccno(loginUserAPP.getAccno());
        traOrderinfom.setPaycode(null);
        traOrderinfom.setOrderdate(nowDate);
        // ???????????? 1????????? 2?????? 3??????
        // APP--app?????? NETBANK ???????????? WECHAT ???????????? ALIPAY ???????????????
        BigDecimal tradeOffAmount = getTradeOffAmount(new BigDecimal(sumamt));
        traOrderinfom.setOldamt(tradeOffAmount);
        traOrderinfom.setSumamt(tradeOffAmount);
        traOrderinfom.setRealamt(getTradeOffAmount(new BigDecimal(realamt)));
        traOrderinfom.setIsinvoice(9);
        traOrderinfom.setOrderstatus(Constants.ORDER_ORD05);
        traOrderinfom.setAccountstatus(Constants.ORDER_ACC04);
        traOrderinfom.setCancelreason(null);
        traOrderinfom.setPayimg(req.getMoneyAddress());
        traOrderinfom.setPaywechat(null);
        traOrderinfom.setPaydate(null);
        traOrderinfom.setOrdernote("??????[" + loginUserAPP.getNickname() + "]??????");
        traOrderinfom.setCreateUser(loginUserAPP.getAccno());
        traOrderinfom.setUpdateUser(loginUserAPP.getAccno());
        traOrderinfom.setSource(resolveClientType(request));

        traOrderinfom.setOrderno(businessId);
        // ??????????????????
        int i = traOrderinfomMapperService.insertIncarateOrder(traOrderinfom);
        if (i > 0) {
            // ??????????????????
            // ??????????????????
            TraOrdertracking traOrdertracking = new TraOrdertracking();
            traOrdertracking.setOrderid(traOrderinfom.getOrderid());
            traOrdertracking.setTrackdate(new Date());
            traOrdertracking.setOrderstatus(Constants.ORDER_ORD05);
            traOrdertracking.setOperuse(loginUserAPP.getAccno());
            traOrdertracking.setTrackbody("??????[" + loginUserAPP.getNickname() + "]????????????");
            traOrdertrackingMapperService.insertSelective(traOrdertracking);
            return traOrderinfom.getOrderid();
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "????????????????????????");
        }

    }

    private String resolveClientType(HttpServletRequest request) {
        String clientType = request.getHeader(Constants.CLIENT_TYPE_STRING);
        if (org.apache.commons.lang3.StringUtils.isEmpty(clientType)) {
            clientType = SourceUtil.getClientSource(request);
        }
        return org.apache.commons.lang3.StringUtils.isEmpty(clientType) ? "" : clientType.toUpperCase();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String cancelIncarnateV2(LoginUser loginUserAPP) {
        // ????????? ????????????
        TraApplycash param = new TraApplycash();
        param.setAccno(loginUserAPP.getAccno());
        // param.setApycstatus(Constants.APYCSTATUS1);
        TraApplycash traApplycash = applycashMapperService.findCashByCash(param);
        if (traApplycash == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "?????????????????????");
        }
        //
        if (Constants.APYCSTATUS1 != traApplycash.getApycstatus()) {
            // throw new BusinessException(106, "????????????????????????????????????");
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "?????????????????????,????????????");
        }

        // ??????
        // long goldnum = traApplycash.getApycgold();
        // long goldnum = traApplycash.getApycgold().longValue();
        long orderid = traApplycash.getOrderid();
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.selectByPrimaryKey(orderid);
        if (traOrderinfom == null || traOrderinfom.getIsDelete()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "?????????????????????");
        }

        if (!traOrderinfom.getOrderstatus().equals(Constants.ORDER_ORD05)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "????????????????????????????????????");
        }

        // ?????? ????????????
        traApplycash.setApycstatus(Constants.APYCSTATUS9);
        traApplycash.setUpdateUser(loginUserAPP.getAccno());
        int i = applycashMapperService.updateTraApplycash(traApplycash);
        if (i > 0) {
            // ????????????
            traOrderinfom.setOrderstatus(Constants.ORDER_ORD06);
            traOrderinfom.setUpdateUser(loginUserAPP.getAccno());
            traOrderinfomMapperService.updateByPrimaryKeySelective(traOrderinfom);

            // ??????????????????
            TraOrdertracking traOrdertracking = new TraOrdertracking();
            traOrdertracking.setOrderid(traOrderinfom.getOrderid());
            traOrdertracking.setTrackdate(new Date());
            traOrdertracking.setOrderstatus(Constants.ORDER_ORD06);
            traOrdertracking.setOperuse(loginUserAPP.getAccno());
            traOrdertracking.setTrackbody("??????[" + loginUserAPP.getNickname() + "]??????????????????");
            traOrdertrackingMapperService.insertSelective(traOrdertracking);

            // ?????????????????????
            this.updateMemGoldchangeCancelIncarnateV2(traApplycash, loginUserAPP, orderid);

            // ?????? ????????????
            this.doDelJihe(traApplycash.getApycid());
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_110.getCode(), "????????????????????????");
        }

        return Constants.SUCCESS_MSG;
    }

    private void doDelJihe(Long apycid) {
        traApplyauditMapperService.doDelJihe(apycid);
    }

    private void updateMemGoldchangeCancelIncarnateV2(TraApplycash traApplycash, LoginUser loginUserAPP, long orderid) {
        Double apycamt = traApplycash.getApycamt().doubleValue();
        Double apycgold = traApplycash.getApycgold().doubleValue();

        // xingzhenfei
        double allfei = apycgold - apycamt;

        // ??????????????????
        jia(apycamt, loginUserAPP, orderid, GoldchangeEnum.WITHDRAWAL_CANCLE.getValue(), "????????????????????????");
        if (allfei > 0) {
            // ??????????????? 17????????????????????? ?????????????????????????????????
//            jia(allfei, loginUserAPP, orderid, GoldchangeEnum.AUDIT_FEE_CANCLE.getValue(), "???????????????????????????");
        }
    }

    private void jia(double goldnums, LoginUser loginUserAPP, long orderid, Integer changetype, String opnote) {
        Double goldnum = (new BigDecimal(goldnums)).doubleValue();
        // ??????????????????
        UsersRequest usersRequest = new UsersRequest();
        BigDecimal tradeOffAmount = getTradeOffAmount(new BigDecimal(goldnum));
        usersRequest.setGoldnum(tradeOffAmount.doubleValue());
        usersRequest.setAccno(loginUserAPP.getAccno());

        // ????????????????????????
        MemGoldchange memGoldchange = new MemGoldchange();
        memGoldchange.setRefid(orderid);
        memGoldchange.setAccno(loginUserAPP.getAccno());
        memGoldchange.setChangetype(changetype);
        memGoldchange.setQuantity(tradeOffAmount);
        memGoldchange.setAmount(tradeOffAmount);
        memGoldchange.setCreateUser(loginUserAPP.getAccno());
        memGoldchange.setUpdateUser(loginUserAPP.getAccno());
        memGoldchange.setOpnote(opnote);
        // ???insert?????????
        int i = memGoldchangeService.insertSelectiveMemGoldchange(memGoldchange);
        if (i > 0) {
            memBaseinfoService.updateAddGold(usersRequest);
            logger.info("?????????????????????????????????");
        } else {
            logger.info("??????????????????");
            throw new BusinessException(StatusCode.LIVE_ERROR_2104.getCode(), "??????????????????");
        }
    }

}
