package com.likes.modules.admin.finance.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.config.UdunProperties;
import com.likes.common.constant.Constants;
import com.likes.common.constant.ModuleConstant;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.pay.MemBankAccountTypeEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.IncarnateOrderReq;
import com.likes.common.model.response.EntryIncarnateOrderExcelResponse;
import com.likes.common.model.response.IncarnateOrderResponse;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.DzCoinMapper;
import com.likes.common.mybatis.mapper.MemBankaccountMapper;
import com.likes.common.mybatis.mapper.UdunOrderMapper;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemBaseinfoWriteService;
import com.likes.common.service.member.MemLevelConfigService;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.service.money.TraApplyauditService;
import com.likes.common.service.money.TraApplycashService;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.service.money.TraOrdertrackingService;
import com.likes.common.service.sys.InfSysremindinfoService;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.util.DateUtils;
import com.likes.common.util.redis.RedisBaseUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.common.util.redis.RedisLock;
import com.likes.modules.admin.common.service.CommonService;
import com.likes.modules.admin.finance.service.IncarnateService;
import com.github.pagehelper.Page;
import com.uduncloud.sdk.client.UdunClient;
import com.uduncloud.sdk.domain.ResultMsg;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

@Service
public class IncarnateServiceImpl extends BaseServiceImpl implements IncarnateService {

    private final Logger logger = LogManager.getLogger(getClass());

    @Resource
    private CommonService commonService;
    @Resource
    private TraOrderinfomService traOrderinfomMapperService;
    @Resource
    private TraOrdertrackingService traOrdertrackingMapperService;
    @Resource
    private TraApplycashService traApplycashMapperService;
    @Resource
    private SysBusParamService sysBusParamService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemBankaccountMapper memBankaccountMapper;
    @Resource
    private MemGoldchangeService memGoldchangeService;
    @Resource
    private InfSysremindinfoService infSysremindinfoService;
    @Resource
    private MemLevelConfigService memLevelConfigService;
    @Resource
    private UdunOrderMapper udunRechargeMapper;
    @Resource
    private TraApplyauditService traApplyauditMapperService;
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private DzCoinMapper dzCoinMapper;
    @Resource
    UdunClient udunClient;
    @Resource
    UdunProperties udunProperties;

  /*  @Override
    public PageResult incarnateOrderList(IncarnateOrderReq req, PageBounds page, LoginUser loginAdmin) {
        // ??????????????????????????????
        req.setAccno(loginAdmin.getAccno());
        Page<IncarnateOrderResponse> list = null;
        if (loginAdmin.getSysroleid() == Constants.SUPERADMINSYSROLEID) {
            // ??????????????????????????????????????????
            list = traOrderinfomMapperService.incarnateOrderListBySuper(req, page.toRowBounds());
        } else {
            list = traOrderinfomMapperService.incarnateOrderList(req, page.toRowBounds());
        }

        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(o -> {
                // ???????????? 1????????? 2?????? 3??????
                int accounttype = o.getAccounttype();
                o.setAccounttypename(MemBankAccountTypeEnum.valueOf(accounttype).getName());
                if (accounttype == 3) {
                    String name = "***"
                            + o.getAccountno().substring(o.getAccountno().length() - 4, o.getAccountno().length());
                    o.setAccountno(name);
                    // ????????????
                    SysBusparameter sysBusparameter = sysBusParamService.selectByBusparamcode(o.getBankname());
                    if (sysBusparameter != null) {
                        o.setBanknamealias(sysBusparameter.getBusparamname());
                    }
                }

            });
        }

        return PageResult.getPageResult(page, list);
    }*/

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized String sureOrder(IncarnateOrderReq req, LoginUser loginAdmin) {
        if (StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "??????????????????");
        }
        // ??????????????????
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(req.getOrderno());
        if (traOrderinfom == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "???????????????");
        }
        if (!Constants.ORDER_ORD05.equals(traOrderinfom.getOrderstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "??????????????????????????????");
        }
        // ????????????????????????
        traOrderinfom.setOrderstatus(Constants.ORDER_ORD07);
        traOrderinfom.setUpdateUser(loginAdmin.getAccno());
        int i = traOrderinfomMapperService.doUpdateIncarnateHandleOrder(traOrderinfom);
        if (i > 0) {
            // ??????????????????
            TraOrdertracking traOrdertracking = new TraOrdertracking();
            traOrdertracking.setOrderid(traOrderinfom.getOrderid());
            traOrdertracking.setTrackdate(new Date());
            traOrdertracking.setOrderstatus(Constants.ORDER_ORD07);
            traOrdertracking.setOperuse(loginAdmin.getAccno());
            traOrdertracking.setTrackbody("?????????[" + loginAdmin.getBdusername() + "]??????????????????");
            traOrdertrackingMapperService.insertSelective(traOrdertracking);

            // ?????????????????????????????????
            TraApplycash traApplycash = traApplycashMapperService.findByOrderid(traOrderinfom.getOrderid());
            if (traApplycash == null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "?????????????????????");
            }

            if (Constants.APYCSTATUS1 != traApplycash.getApycstatus()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "????????????????????????");
            }

            // ??????
            traApplycash.setApycstatus(Constants.APYCSTATUS2);
            traApplycash.setPaymemname(loginAdmin.getAccno());
            traApplycash.setUpdateUser(loginAdmin.getAccno());

            // ????????????
            int k = traApplycashMapperService.doUpdateIncarnateHandleOrder(traApplycash);
            if (!(k > 0)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_107.getCode(), "??????????????????????????????");
            }
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "???????????????");
        }
        RedisBusinessUtil.delIncarnateOrderListCahce();

        // ????????????????????????
        MemBaseinfo info = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
        if (null != info) {
            SysInfolog sysInfolog = new SysInfolog();
            sysInfolog.setAccno(loginAdmin.getAccno());
            sysInfolog.setOptcontent("????????????[" + info.getUniqueId() + "]??????[" + traOrderinfom.getRealamt() + "]?????????[" + req.getOrderno() + "]??????");
            sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
            sysInfolog.setModelname("????????????");
            sysInfolog.setOrginfo("sureIncarnateOrder");
            commonService.insertSelective(sysInfolog);
        }
        return Constants.SUCCESS_MSG;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String cancelSureIncarnateOrder(IncarnateOrderReq req, LoginUser loginAdmin) {
        if (StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "??????????????????");
        }
        // ??????????????????
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(req.getOrderno());
        if (traOrderinfom == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "???????????????");
        }
        if (!Constants.ORDER_ORD07.equals(traOrderinfom.getOrderstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "?????????????????????????????????");
        }
        // ????????????????????????
        // ???????????? ????????? ??????????????? ??????????????? ord05???????????? ??????
        traOrderinfom.setOrderstatus(Constants.ORDER_ORD05);
        traOrderinfom.setUpdateUser(loginAdmin.getAccno());
        int i = traOrderinfomMapperService.doUpdateIncarnateCancelSureOrder(traOrderinfom);
        if (i > 0) {
            // ??????????????????
            TraOrdertracking traOrdertracking = new TraOrdertracking();
            traOrdertracking.setOrderid(traOrderinfom.getOrderid());
            traOrdertracking.setTrackdate(new Date());
            traOrdertracking.setOrderstatus(Constants.ORDER_ORD05);
            traOrdertracking.setOperuse(loginAdmin.getAccno());
            traOrdertracking.setTrackbody("?????????[" + loginAdmin.getBdusername() + "]????????????????????????????????????????????????????????????");
            traOrdertrackingMapperService.insertSelective(traOrdertracking);

            // ?????????????????????????????????
            TraApplycash traApplycash = traApplycashMapperService.findByOrderid(traOrderinfom.getOrderid());
            if (traApplycash == null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "?????????????????????");
            }
            if (Constants.APYCSTATUS2 != traApplycash.getApycstatus()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "???????????????????????????");
            }

            // ??????
            traApplycash.setApycstatus(Constants.APYCSTATUS1);
            // ??????
            traApplycash.setPaymemname(null);
            traApplycash.setUpdateUser(loginAdmin.getAccno());

            // ????????????
            int k = traApplycashMapperService.doUpdateIncarnateCancelSureOrder(traApplycash);
            if (!(k > 0)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_107.getCode(), "?????????????????????????????????");
            }
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "?????????????????????");
        }
        RedisBusinessUtil.delIncarnateOrderListCahce();

        // ??????????????????????????????
        MemBaseinfo info = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
        if (null != info) {
            SysInfolog sysInfolog = new SysInfolog();
            sysInfolog.setAccno(loginAdmin.getAccno());
            sysInfolog.setOptcontent("????????????[" + info.getUniqueId() + "]??????[" + traOrderinfom.getRealamt() + "]?????????[" + req.getOrderno() + "]????????????");
            sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
            sysInfolog.setModelname("????????????");
            sysInfolog.setOrginfo("cancelSureIncarnateOrder");
            commonService.insertSelective(sysInfolog);
        }
        return Constants.SUCCESS_MSG;
    }

    @Override
    public Map<String, Object> incarnateOrderDetail(IncarnateOrderReq req, LoginUser loginAdmin) {
        if (StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "??????????????????");
        }
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(req.getOrderno());
        if (traOrderinfom == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "???????????????");
        }
        if (!Constants.ORDER_ORD12.equals(traOrderinfom.getOrderstatus()) && !Constants.ORDER_ORD14.equals(traOrderinfom.getOrderstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "???????????????????????????");
        }

        Map<String, Object> dataMap = new HashMap<String, Object>();
        // dataMap.put("orderstatus", traOrderinfom.getOrderstatus());
        dataMap.put("orderno", traOrderinfom.getOrderno());
        dataMap.put("createdate", DateUtils.formatDate(traOrderinfom.getCreateTime()));
        dataMap.put("realamt", traOrderinfom.getRealamt().setScale(3, BigDecimal.ROUND_HALF_DOWN));
        dataMap.put("goldnum", traOrderinfom.getSumamt().setScale(3, BigDecimal.ROUND_HALF_DOWN));
        dataMap.put("ordernote", traOrderinfom.getOrdernote());
        // ????????????
        String payimg = traOrderinfom.getPayimg();
        if (StringUtils.isNotEmpty(payimg)) {
            List<String> imgList = new ArrayList<String>();
            String[] arr = payimg.split(",");
            for (String img : arr) {
                imgList.add(img);
            }
            dataMap.put("imgList", imgList);
        } else {
            dataMap.put("imgList", new ArrayList<String>());
        }
        // ????????????
        MemBaseinfo zhubo = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
        if (zhubo != null) {
            dataMap.put("nickname", zhubo.getNickname());
        } else {
            dataMap.put("nickname", null);
        }

        TraApplycash traApplycash = traApplycashMapperService.findByOrderid(traOrderinfom.getOrderid());
        if (traApplycash == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "?????????????????????");
        }

        Long bankaccid = traApplycash.getBankaccid();
        MemBankaccount o = memBankaccountMapper.selectByPrimaryKey(bankaccid);
        if (o != null) {
            dataMap.put("accountname", o.getAccountname());
            dataMap.put("accountno", o.getAccountno());
            dataMap.put("bankname", o.getBankname());
            dataMap.put("bankaddress", o.getBankaddress());
            // ???????????? 1????????? 2?????? 3??????
            dataMap.put("accounttypename", MemBankAccountTypeEnum.valueOf(o.getAccounttype()).getName());
            if (o.getAccounttype().equals(MemBankAccountTypeEnum.NETBANK.getValue())) {
                dataMap.put("accounttypename", "??????");
                SysBusparameter sysBusparameter = sysBusParamService.selectByBusparamcode(o.getBankname());
                if (sysBusparameter != null) {
                    dataMap.put("banknamealias", sysBusparameter.getBusparamname());
                }
            }
        } else {
            dataMap.put("accountname", null);
            dataMap.put("accountno", null);
            dataMap.put("bankaddress", null);
            dataMap.put("bankname", null);
            dataMap.put("banknamealias", null);
            dataMap.put("accounttypename", null);
        }

        return dataMap;
    }

    @Override
    public Map<String, Object> incarnateHandleOrderDetail(IncarnateOrderReq req, LoginUser loginAdmin) {
        if (StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "??????????????????");
        }
        // ??????
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(req.getOrderno());
        if (traOrderinfom == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "???????????????");
        }
        if (!Constants.ORDER_ORD07.equals(traOrderinfom.getOrderstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "?????????????????????????????????");
        }

        // ??????
        MemBaseinfo zhubo = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
        if (zhubo == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "???????????????");
        }

        // ????????????
        TraApplycash traApplycash = traApplycashMapperService.findByOrderid(traOrderinfom.getOrderid());
        if (traApplycash == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "?????????????????????");
        }
        if (Constants.APYCSTATUS2 != traApplycash.getApycstatus()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "???????????????????????????");
        }

        if (!(loginAdmin.getSysroleid().equals(Constants.SUPERADMINSYSROLEID))) {
            // ????????????????????? ????????????????????????
            if (!traApplycash.getPaymemname().equals(loginAdmin.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_107.getCode(), "???????????????????????????????????????");
            }
        }

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("nickname", zhubo.getNickname());
        // dataMap.put("orderstatus", traOrderinfom.getOrderstatus());
        dataMap.put("orderno", traOrderinfom.getOrderno());
        dataMap.put("createdate", DateUtils.formatDate(traOrderinfom.getCreateTime()));
        dataMap.put("realamt", traOrderinfom.getRealamt().setScale(3, BigDecimal.ROUND_HALF_DOWN));
        dataMap.put("goldnum", traOrderinfom.getSumamt().setScale(3, BigDecimal.ROUND_HALF_DOWN));

        Long bankaccid = traApplycash.getBankaccid();
        MemBankaccount o = memBankaccountMapper.selectByPrimaryKey(bankaccid);
        if (o != null) {
            dataMap.put("accountname", o.getAccountname());
            dataMap.put("accountno", o.getAccountno());
            dataMap.put("bankname", o.getBankname());
            dataMap.put("bankaddress", o.getBankaddress());
            // ???????????? 1????????? 2?????? 3??????
            dataMap.put("accounttypename", MemBankAccountTypeEnum.valueOf(o.getAccounttype()).getName());
            if (o.getAccounttype().equals(MemBankAccountTypeEnum.NETBANK.getValue())) {
                dataMap.put("accounttypename", "??????");
                SysBusparameter sysBusparameter = sysBusParamService.selectByBusparamcode(o.getBankname());
                if (sysBusparameter != null) {
                    dataMap.put("banknamealias", sysBusparameter.getBusparamname());
                }
            }
        } else {
            dataMap.put("accountname", null);
            dataMap.put("accountno", null);
            dataMap.put("bankaddress", null);
            dataMap.put("bankname", null);
            dataMap.put("banknamealias", null);
            dataMap.put("accounttypename", null);
        }

        return dataMap;
    }


    private void doInfSysremindinfo(TraOrderinfom traOrderinfom, LoginUser loginAdmin) {
        InfSysremindinfo infSysremindinfo = new InfSysremindinfo();
        infSysremindinfo.setSender(loginAdmin.getAccno());
        infSysremindinfo.setRmtype(Constants.RMTYPE_SYSTEM);
        infSysremindinfo.setRecipienter(traOrderinfom.getAccno());
        infSysremindinfo.setRemindtxt("??????????????????" + traOrderinfom.getRealamt().doubleValue() + "?????????");
        infSysremindinfo.setRmtitle("????????????");
        infSysremindinfo.setSenddate(new Date());
        infSysremindinfo.setRefparm(String.valueOf(traOrderinfom.getOrderid()));
        infSysremindinfo.setRefaddlink(String.valueOf(traOrderinfom.getOrderid()));
        infSysremindinfo.setRmdateexp(DateUtils.afterDays(new Date(), 7));
        infSysremindinfo.setIssee(Constants.ISSEE_9);
        infSysremindinfo.setIstodo(Constants.ISTODO_0);
        infSysremindinfo.setCreateUser(loginAdmin.getAccno());
        infSysremindinfo.setUpdateUser(loginAdmin.getAccno());
        infSysremindinfoService.insertSelective(infSysremindinfo);
        RedisBusinessUtil.delete(RedisKeys.LIVE_INF_SYSREMINDINFO_NUM + traOrderinfom.getAccno());

    }

    // ------------------------------------------v2-------------------------------------------------------------------
    @Override
    @DS("slave")
    public Map<String, Object> incarnateOrderListV2(IncarnateOrderReq req, PageBounds page, LoginUser loginAdmin) {
        // ??????????????????????????????
//        req.setAccno(loginAdmin.getAccno()); //todo
        if (req.getStartDate() != null && req.getStartDate() != "") {
            req.setStartDate(req.getStartDate() + " 00:00:00");
        }
        if (req.getEndDate() != null && req.getEndDate() != "") {
            req.setEndDate(req.getEndDate() + " 23:59:59");
        }
        BigDecimal realamt = traOrderinfomMapperService.incarnateOrderActualTotal(req);
        if(ObjectUtil.isNotNull(req.getLevelSeq())){
            MemLevelConfig levelConfig = memLevelConfigService.getMemLevelConfigForConfigId(req.getLevelSeq());
            req.setLevelSeq(levelConfig.getLevelSeq().longValue());
        }
        Page<IncarnateOrderResponse> list = traOrderinfomMapperService.incarnateOrderListBySuper(req, page.toRowBounds());
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(o -> {
                if (Constants.ORDER_ORD05.equals(o.getOrderstatus())) {
                    o.setUpdateUser(null);
                }
                if (Constants.ORDER_ORD12.equals(o.getOrderstatus()) || Constants.ORDER_ORD14.equals(o.getOrderstatus())) {
                    o.setFinishTime(o.getUpdateTime());
                }
                if (o.getAccounttype() != null) {
                    o.setAccounttypename(MemBankAccountTypeEnum.valueOf(o.getAccounttype()).getName());
                    String name = o.getAccountno().substring(0, 3) + "***" + o.getAccountno().substring(o.getAccountno().length() - 4);
                    o.setAccountno(name);
                    if (StringUtils.isNotBlank(o.getBankname())) {
                        SysBusparameter sysBusparameter = sysBusParamService.selectByBusparamcode(o.getBankname());
                        if (sysBusparameter != null) {
                            o.setBanknamealias(sysBusparameter.getBusparamname());
                        }
                    }
                }
            });
        }
        for (IncarnateOrderResponse incarnateOrderResponse : list) {
            SysBusparameter w_audit_amout = this.sysBusParamService.selectByBusparamcode("w_audit_amout");
            if (new BigDecimal(String.valueOf(incarnateOrderResponse.getSumamt())).intValue() >= Integer.parseInt(w_audit_amout.getBusparamname())) {

                Integer businessIdNum = udunRechargeMapper.countBusinessId(incarnateOrderResponse.getOrderno());
                if (businessIdNum <1) {
                    incarnateOrderResponse.setShowThirdButton(true);
                }
                }
        }
        RedisBusinessUtil.addIncarnateOrderListCahce(list, req, page.toRowBounds());
        return incarnateOrderPageToMap(page, list, realamt);
    }

    private Map<String, Object> incarnateOrderPageToMap(PageBounds page, Page<IncarnateOrderResponse> list, BigDecimal realamt) {
        Map<String, Object> resutMap = new HashMap();
        PageResult pageResult = PageResult.getPageResult(page, list);
        resutMap.put("pageNo", pageResult.getPageNo());
        resutMap.put("pageSize", pageResult.getPageSize());
        resutMap.put("totalCount", pageResult.getTotalCount());
        resutMap.put("totalPage", pageResult.getTotalPage());
        resutMap.put("actualTotal", realamt == null ? new BigDecimal(0) : realamt);
        resutMap.put("data", pageResult.getData());
        return resutMap;
    }

    @Override
    public List<EntryIncarnateOrderExcelResponse> incarnateOrderListV2Excel(IncarnateOrderReq req) {
        List<EntryIncarnateOrderExcelResponse> list = traOrderinfomMapperService.incarnateOrderListByExcel(req);
        if (!CollectionUtils.isEmpty(list)) {
            for (EntryIncarnateOrderExcelResponse o : list) {
                if (Constants.ORDER_ORD05.equals(o.getOrderstatus())) {
                    o.setUpdateUser(null);
                }
                if (Constants.ORDER_ORD12.equals(o.getOrderstatus()) || Constants.ORDER_ORD14.equals(o.getOrderstatus())) {
                    o.setFinishTime(o.getUpdateTime());
                }
                if (o.getAccounttype() != null) {
                    String name = o.getAccountno().substring(0, 3) + "***" + o.getAccountno().substring(o.getAccountno().length() - 4);
                    o.setAccountno(name);
                }
            }
        }
        return list;
    }

    @Override
    public boolean subUdun(IncarnateOrderReq req, LoginUser loginAdmin) {
        if (StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "???????????????");
        }
        // ??????
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(req.getOrderno());
        if (traOrderinfom == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "???????????????");
        }
        if (Constants.ORDER_ORD12.equals(traOrderinfom.getOrderstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_203.getCode(), "?????????????????????");
        }
        // ??????
        MemBaseinfo zhubo = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
        if (zhubo == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "???????????????");
        }
        // ????????????
        TraApplycash traApplycash = traApplycashMapperService.findByOrderid(traOrderinfom.getOrderid());
        if (traApplycash == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "?????????????????????");
        }
        boolean flag = submitWithdraw("TRC20", traOrderinfom.getOrderno(), traOrderinfom.getRealamt(), traOrderinfom.getPayimg(), traOrderinfom.getAccno());
        if (!flag) {
            throw new RuntimeException("??????????????????");
        }
        return flag;
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean submitWithdraw(String coinName, String businessId, BigDecimal amount, String moneyAddress, String accno) {
        Integer businessIdNum = udunRechargeMapper.countBusinessId(businessId);
        if (businessIdNum > 0) {
            throw new BusinessException("??????????????????????????????");
        }
        DzCoin dzCoin = getCoinType(coinName);
        UdunOrder udunOrder = new UdunOrder();
        udunOrder.setAmount(getTradeOffAmount(amount));
        udunOrder.setAccno(accno);
        udunOrder.setMainCoinType(dzCoin.getMainCoinType());
        udunOrder.setCoinType(dzCoin.getCoinType());
        udunOrder.setTradeType(2);
        udunOrder.setAddress(moneyAddress);
        udunOrder.setBusinessId(businessId);
        udunOrder.setTradeStatus(0);
        udunOrder.setCreateUser(accno);
        udunOrder.setCreateTime(new Date());
        udunRechargeMapper.insertSelective(udunOrder);
//        throw  new RuntimeException("22");
        ResultMsg resultMsg = udunClient.withdraw(moneyAddress, udunOrder.getAmount(), dzCoin.getMainCoinType(), dzCoin.getCoinType(), businessId, null, udunProperties.getCallUrl());
        if (resultMsg.getCode().equals(200)) {
            return true;
        }else{
            logger.error("U dun?????????????????? ????????????  ===== >> {}", JSONUtil.toJsonStr(resultMsg));
            logger.error("U dun?????????????????? == coinName={},moneyAddress={},accLogin={}", coinName, moneyAddress, accno);
            throw  new RuntimeException(resultMsg.getMessage());
        }
    }


    public DzCoin getCoinType(String coinName) {
        DzCoin dzCoin = RedisBaseUtil.get("dz:dzCoin:" + coinName);
        if (ObjectUtil.isNull(dzCoin)) {
            DzCoin param = new DzCoin();
            param.setCoinName(coinName);
            dzCoin = dzCoinMapper.selectOne(param);
            RedisBaseUtil.set("dz:dzCoin:" + coinName, dzCoin);
        }
        return dzCoin;
    }


    @Override
    @Transactional
    public String incarnateConfirmV2(IncarnateOrderReq req, LoginUser loginAdmin) {
        if (StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "???????????????");
        }

        // ??????
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(req.getOrderno());
        if (traOrderinfom == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "???????????????");
        }
        if (!Constants.ORDER_ORD07.equals(traOrderinfom.getOrderstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "?????????????????????????????????");
        }
        if (Constants.ORDER_ORD12.equals(traOrderinfom.getOrderstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_203.getCode(), "?????????????????????");
        }

        // ??????
        MemBaseinfo zhubo = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
        if (zhubo == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "???????????????");
        }

        // ????????????
        TraApplycash traApplycash = traApplycashMapperService.findByOrderid(traOrderinfom.getOrderid());
        if (traApplycash == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "?????????????????????");
        }
        if (Constants.APYCSTATUS2 != traApplycash.getApycstatus()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "???????????????????????????");
        }
        if (!(loginAdmin.getSysroleid().equals(Constants.SUPERADMINSYSROLEID))) {
            // ????????????????????? ????????????????????????
            if (!traApplycash.getPaymemname().equals(loginAdmin.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_107.getCode(), "???????????????????????????????????????");
            }
        }

        if (req.getBeSucceed() == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "????????????????????????");
        }

        if (!req.getBeSucceed()) {
            failedOrder(req, loginAdmin);
            RedisBusinessUtil.delIncarnateOrderListCahce();
            return Constants.SUCCESS_MSG;
        }

        // ??????????????????
        // ??????
        traOrderinfom.setOrderstatus(Constants.ORDER_ORD12);
        traOrderinfom.setPaydate(new Date());
        traOrderinfom.setUpdateUser(loginAdmin.getAccno());
        traOrderinfom.setOrdernote(req.getReason());
        int i = traOrderinfomMapperService.updateIncarnateConfirmOrder(traOrderinfom);
        if (i > 0) {
            // ??????????????????
            TraOrdertracking traOrdertracking = new TraOrdertracking();
            traOrdertracking.setOrderid(traOrderinfom.getOrderid());
            traOrdertracking.setTrackdate(new Date());
            traOrdertracking.setOrderstatus(Constants.ORDER_ORD12);
            traOrdertracking.setOperuse(loginAdmin.getAccno());
            traOrdertracking.setTrackbody("?????????[" + loginAdmin.getBdusername() + "]????????????");
            // ????????????
            int tc = traOrdertrackingMapperService.insertTraOrdertracking(traOrdertracking);
            if (!(tc > 0)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_202.getCode(), "?????????????????????");
            }
            // ??????????????????
            traApplycash.setPaydate(new Date());
            traApplycash.setApycstatus(Constants.APYCSTATUS4);
            traApplycash.setUpdateUser(loginAdmin.getAccno());

            // ????????????
            int k = traApplycashMapperService.updateIncarnateConfirmApplycash(traApplycash);
            if (!(k > 0)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "?????????????????????????????????");
            }

            // ?????? ????????????????????? ?????????????????????????????? ?????? ???????????????
            MemGoldchange paramMemGoldchange = new MemGoldchange();
            paramMemGoldchange.setAccno(traOrderinfom.getAccno());
            paramMemGoldchange.setChangetype(GoldchangeEnum.WITHDRAWAL_APPLY.getValue());
            paramMemGoldchange.setRefid(traOrderinfom.getOrderid());
            paramMemGoldchange.setUpdateUser(loginAdmin.getAccno());
            paramMemGoldchange.setOpnote("????????????");
            paramMemGoldchange.setSource(traOrderinfom.getSource());

            int mg = memGoldchangeService.updateZhuboTixian(paramMemGoldchange);
            if (!(mg > 0)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_115.getCode(), "????????????????????????");
            }

            // ?????? ?????? ??????????????? ????????????
            Long apyid = traApplycash.getApycid();
            List<TraApplyaudit> traApplyaudits = traApplyauditMapperService.getListById(apyid);
            if (CollectionUtils.isNotEmpty(traApplyaudits)) {
                List<Long> orderids = traApplyaudits.stream().map(ob -> ob.getOrderid()).collect(Collectors.toList());
                traOrderinfomMapperService.doJiesuanOrder(orderids);
            }
            MemBaseinfoExample membaseinfoExample = new MemBaseinfoExample();
            membaseinfoExample.createCriteria().andAccnoEqualTo(traOrderinfom.getAccno());
            MemBaseinfo membaseinfo = memBaseinfoService.selectOneByExample(membaseinfoExample);
            // ??????????????????
            membaseinfo.setWithdrawalAmount(getTradeOffAmount(traOrderinfom.getSumamt()));
            // ????????????????????????
            if (membaseinfo.getWithdrawalFirst() == null || membaseinfo.getWithdrawalFirst().compareTo(BigDecimal.ZERO) == 0) {
                membaseinfo.setWithdrawalFirst(getTradeOffAmount(traOrderinfom.getSumamt()));
            }
            // ????????????????????????
            if (membaseinfo.getWithdrawalMax() == null || membaseinfo.getWithdrawalMax().compareTo(getTradeOffAmount(traOrderinfom.getSumamt())) == -1) {
                membaseinfo.setWithdrawalMax(getTradeOffAmount(traOrderinfom.getSumamt()));
            }
            // ?????????????????????
            memBaseinfoService.updateWithdrawalAmount(membaseinfo);
            // ??????????????????
            this.doInfSysremindinfo(traOrderinfom, loginAdmin);

            // ????????????????????????
            SysInfolog sysInfolog = new SysInfolog();
            sysInfolog.setAccno(loginAdmin.getAccno());
            sysInfolog.setOptcontent("????????????[" + membaseinfo.getUniqueId() + "]??????[" + traOrderinfom.getRealamt() + "]?????????[" + req.getOrderno() + "]????????????");
            sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
            sysInfolog.setModelname("????????????");
            sysInfolog.setOrginfo("doIncarnateConfirm");
            commonService.insertSelective(sysInfolog);
            RedisBusinessUtil.delIncarnateOrderListCahce();
            logger.info("{}{}????????????{}??????{}????????????", loginAdmin.getBdusername(), loginAdmin.getAccno(), traOrderinfom.getAccno(), zhubo.getNickname());
            return Constants.SUCCESS_MSG;

        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "????????????(???????????????)");
        }
    }

    // ??????????????????
    private void failedOrder(IncarnateOrderReq req, LoginUser loginAdmin) {
        if (req.getBeSucceed()) {
            return;
        }
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(req.getOrderno());

        BigDecimal sumamt = traOrderinfom.getSumamt();

        // ??????????????????
        traOrderinfom.setOrderstatus(Constants.ORDER_ORD14);
        traOrderinfom.setUpdateUser(loginAdmin.getAccno());
        traOrderinfom.setUpdateTime(new Date());
        traOrderinfom.setOrdernote(req.getReason());
        traOrderinfomMapperService.updateByPrimaryKeySelective(traOrderinfom);
        Long mid = memBaseinfoService.selectByAccno(traOrderinfom.getAccno()).getMemid();
        RReadWriteLock lock = redissonClient.getReadWriteLock(RedisLock.UPDATE_USER_BALANCE_ + mid);
        try {
            boolean bool = lock.writeLock().tryLock(100, 20, TimeUnit.SECONDS);
            if (!bool) {
                logger.error("{}.failedOrder ????????????:{}", getClass().getName(), RedisLock.UPDATE_USER_BALANCE_ + mid);
                throw new BusinessException("?????????????????????????????????");
            }
            // ??????????????????
            MemBaseinfo membaseinfo = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
            TraOrdertracking traOrdertracking = new TraOrdertracking();
            traOrdertracking.setOrderid(traOrderinfom.getOrderid());
            traOrdertracking.setTrackdate(new Date());
            traOrdertracking.setOrderstatus(Constants.ORDER_ORD14);
            traOrdertracking.setOperuse(loginAdmin.getAccno());
            traOrdertracking.setTrackbody("?????????[" + loginAdmin.getBdusername() + "]???????????????[" + traOrderinfom.getOrderno() + "]??????");
            traOrdertrackingMapperService.insertSelective(traOrdertracking);

            // ?????????????????????????????????
            TraApplycash traApplycash = traApplycashMapperService.findByOrderid(traOrderinfom.getOrderid());
            if (traApplycash == null) {
                throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "?????????????????????");
            }

            if (Constants.APYCSTATUS2 != traApplycash.getApycstatus()) {
                throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "????????????????????????");
            }

            // ?????? ?????????????????????
            traApplycash.setApycstatus(Constants.APYCSTATUS3);
            traApplycash.setPaymemname(loginAdmin.getAccno());
            traApplycash.setUpdateUser(loginAdmin.getAccno());
            traApplycashMapperService.updateByPrimaryKeySelective(traApplycash);
            //??????
            // ?????? ????????????????????? ?????????????????????????????? ?????? ???????????????
            MemGoldchange memGoldchange = memGoldchangeService.selectOneByExample(traOrderinfom.getOrderid(), traOrderinfom.getAccno());
            BigDecimal tradeOffAmount = getTradeOffAmount(membaseinfo.getNoWithdrawalAmount());
            BigDecimal tradeOffAmount1 = getTradeOffAmount(membaseinfo.getGoldnum());
            memGoldchange.setAfterCgdml(tradeOffAmount);
            memGoldchange.setPreCgdml(tradeOffAmount);
            memGoldchange.setSource(traOrderinfom.getSource());
            memGoldchange.setGoldnum(tradeOffAmount1);
            memGoldchange.setQuantity(getTradeOffAmount(sumamt.multiply(new BigDecimal(Constants.CHONGZHIBILIE))));
            memGoldchange.setAmount(getTradeOffAmount(sumamt));
            memGoldchange.setRecgoldnum(tradeOffAmount1.add(sumamt));
            memGoldchange.setChangetype(GoldchangeEnum.WITHDRAW_FAILED.getValue());
            memGoldchange.setUpdateUser(loginAdmin.getAccno());
            memGoldchange.setUpdateTime(new Date());
            memGoldchange.setOpnote("????????????");
            memGoldchangeService.updateByPrimaryKeySelective(memGoldchange);
            memBaseinfoService.updateMemBalance(sumamt, BigDecimal.ZERO, BigDecimal.ZERO, membaseinfo.getAccno());

            // ????????????????????????
            SysInfolog sysInfolog = new SysInfolog();
            sysInfolog.setAccno(loginAdmin.getAccno());
            sysInfolog.setOptcontent("????????????[" + membaseinfo.getUniqueId() + "]??????[" + traOrderinfom.getRealamt() + "]?????????[" + req.getOrderno() + "]????????????");
            sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
            sysInfolog.setModelname("????????????");
            sysInfolog.setOrginfo("doIncarnateConfirm");
            commonService.insertSelective(sysInfolog);
        } catch (Exception e) {
            logger.error("{}.failedOrder ??????", getClass().getName(), e);
            throw new BusinessException("??????????????????");
        } finally {
            lock.writeLock().unlock();
        }
    }

}
