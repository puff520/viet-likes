package com.likes.modules.admin.finance.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.constant.Constants;
import com.likes.common.constant.ModuleConstant;
import com.likes.common.enums.CreditChangeEnum;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.dto.MandatoryDO;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.request.CreditRequest;
import com.likes.common.model.request.EntryOrderReq;
import com.likes.common.model.request.OrderReq;
import com.likes.common.model.response.EntryOnLineOrderExcelResponse;
import com.likes.common.model.response.EntryOrderExcelResponse;
import com.likes.common.model.response.EntryOrderResponse;
import com.likes.common.model.response.EntryOrderTotalResponse;
import com.likes.common.model.response.StatisticsOnLineOrderResponse;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.MemLoginMapper;
import com.likes.common.mybatis.mapperext.member.MemLevelConfigMapperExt;
import com.likes.common.mybatis.mapperext.tra.TraOrdertrackingMapperExt;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.credit.MemCreditService;
import com.likes.common.service.member.*;
import com.likes.common.service.member.impl.MemBaseinfoWriteServiceImpl;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.service.money.SysPayAccountService;
import com.likes.common.service.money.SysPayproviderService;
import com.likes.common.service.money.SysPaysetService;
import com.likes.common.service.money.SysThreePaysetService;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.service.money.TraOrdertrackingService;
import com.likes.common.service.money.TraPaymentinfoService;
import com.likes.common.service.money.TraRechargemealService;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.util.CommonFunction;
import com.likes.common.util.DateUtils;
import com.likes.common.util.PaySignUtil;
import com.likes.common.util.http.HttpUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.common.service.CommonService;
import com.likes.modules.admin.finance.service.RechargeOrderService;
import com.github.pagehelper.Page;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

@Service
public class RechargeOrderServiceImpl extends BaseServiceImpl implements RechargeOrderService {

    private final static Logger LOGGER = LogManager.getLogger(RechargeOrderServiceImpl.class);

    @Resource
    private TraOrderinfomService traOrderinfomMapperService;
    @Resource
    private TraOrdertrackingService traOrdertrackingMapperService;
    @Resource
    private TraOrdertrackingMapperExt traOrdertrackingMapperExt;
    @Resource
    private TraRechargemealService traRechargemealMapperService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemLoginMapper memLoginMapper;
    @Resource
    private MemLoginService memLoginService;
    @Resource
    private CommonService commonService;
    @Resource
    private MemGoldchangeService memGoldchangeService;
    @Resource
    private MemLevelService memLevelService;
    @Resource
    private SysBusParamService sysBusParamService;

    @Resource
    private SysPayAccountService sysPayAccountMapperService;

    @Resource
    private SysPayproviderService sysPayproviderService;
    @Resource
    private SysPaysetService sysPaysetService;
    @Resource
    private TraPaymentinfoService traPaymentinfoMapperService;
    @Resource
    private MemLevelConfigMapperExt memLevelConfigMapperExt;
    @Autowired
    private HttpServletRequest request;
    @Resource
    private MemBaseinfoWriteServiceImpl memBaseinfoWriteService;
    @Resource
    private BdUserService bdUserService;
    @Resource
    private SysThreePaysetService sysThreePaysetService;

    @Resource
    private MemCreditService memCreditService;
    @Resource
    private MemRelationshipService memRelationshipService;

    @Override
    public Map<String, Object> getRechargeOrderDetail(OrderReq req, LoginUser loginUser) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
		/*String orderno = null;
		// try {
		orderno = AESUtils.decryptData(req.getCopyno(), Constants.ORDERAES);
		if (StringUtils.isEmpty(orderno)) {
			throw new BusinessException(StatusCode.LIVE_ERROR_110.getCode(), "请输入正确的查询码");
		}*/
        if (StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10001.getCode(), "订单编号为空");
        }

        String orderno = req.getOrderno();
        // 获取订单信息
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(orderno);
        if (traOrderinfom == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "订单不存在");
        }
        if (!Constants.ORDER_ORD04.equals(traOrderinfom.getOrderstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "订单状态不为待支付");
        }
        // NETBANK 网银转账 WECHAT 微信收款 ALIPAY 支付宝支付
        // 会员
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
        if (memBaseinfo == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "下单用户不存在");
        }
        // 套餐
        TraRechargemeal traRechargemeal = traRechargemealMapperService.selectByPrimaryKey(traOrderinfom.getMealid());
        if (traRechargemeal == null || traRechargemeal.getIsDelete()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "该订单的套餐不存在");
        }

        dataMap.put("orderno", traOrderinfom.getOrderno());
        dataMap.put("orderstatus", traOrderinfom.getOrderstatus());
        dataMap.put("orderrealamt", traOrderinfom.getRealamt());
        dataMap.put("createdate", DateUtils.formatDate(traOrderinfom.getCreateTime()));
        dataMap.put("nickname", memBaseinfo.getNickname());
        dataMap.put("realamt", traRechargemeal.getRealamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
        dataMap.put("givepercent", traRechargemeal.getGivepercent().setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
        dataMap.put("rechargegold", traRechargemeal.getRechargegold().setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
        dataMap.put("givegold", traRechargemeal.getGivegold().setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
        dataMap.put("allgold", traRechargemeal.getRechargegold().setScale(3, BigDecimal.ROUND_DOWN).doubleValue() + traRechargemeal.getGivegold().setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
        if ("ALIPAY".equals(traOrderinfom.getPaytype())) {
            dataMap.put("paytypename", "支付宝");
        } else if ("WECHAT".equals(traOrderinfom.getPaytype())) {
            dataMap.put("paytypename", "微信");
        } else if ("NETBANK".equals(traOrderinfom.getPaytype())) {
            dataMap.put("paytypename", "银联");
        }

        dataMap.put("payuser", traOrderinfom.getPayuser());
        dataMap.put("paynote", traOrderinfom.getPaynote());
        // 收款账号
        SysPayaccount sysPayaccount = sysPayAccountMapperService.selectByPrimaryKey(traOrderinfom.getBankid());
        if (sysPayaccount != null) {
            dataMap.put("accountno", sysPayaccount.getAccountno());
            dataMap.put("accountname", sysPayaccount.getAccountname());
        } else {
            dataMap.put("accountno", null);
            dataMap.put("accountname", null);
        }
        // 获取

        // 当前后台登录用户的 微信号
		/*BdUser bdUser = bdUserMapper.selectByAccno(loginUser.getAccno());
		if (bdUser != null) {
			String[] weichats = bdUser.getWechat().split(",");
			dataMap.put("weichats", weichats);
		}*/
		/*} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getCode(), e.getMessage());
		}*/
        return dataMap;
    }

    @Override
    public Map<String, Object> getOrderDetail(EntryOrderReq req) {
        if (StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "订单编号为空");
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String orderno = req.getOrderno();
        // 获取订单信息
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(orderno);
        if (traOrderinfom == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "订单不存在");
        }

        // 会员
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
        if (memBaseinfo == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "下单用户不存在");
        }
        // 套餐
		/*TraRechargemeal traRechargemeal = traRechargemealMapper.selectByPrimaryKey(traOrderinfom.getMealid());
		if (traRechargemeal == null) {
			// ||traRechargemeal.getIsdelete() != Constants.isdelete_0
			throw new BusinessException(StatusCode.LIVE_ERROR_104.getCode(), "该订单的套餐不存在");
		} else {
			dataMap.put("mealisdelete", traRechargemeal.getIsdelete());
		}*/
        // 处理者
        BdUser bdUser = bdUserService.selectByAccno(traOrderinfom.getUpdateUser());
        if (bdUser != null) {
            dataMap.put("modifyusername", bdUser.getBdusername());
        }
        dataMap.put("orderno", traOrderinfom.getOrderno());
        dataMap.put("orderrealamt", traOrderinfom.getRealamt());
        dataMap.put("orderstatus", traOrderinfom.getOrderstatus());
        dataMap.put("paywechat", traOrderinfom.getPaywechat());
        dataMap.put("createdate", DateUtils.formatDate(traOrderinfom.getCreateTime()));
        dataMap.put("modifydate", DateUtils.formatDate(traOrderinfom.getUpdateTime()));
        dataMap.put("nickname", memBaseinfo.getNickname());
        dataMap.put("ordernote", traOrderinfom.getOrdernote());
		/*dataMap.put("givepercent", traRechargemeal.getGivepercent());
		dataMap.put("rechargegold", traRechargemeal.getRechargegold());
		dataMap.put("givegold", traRechargemeal.getGivegold());
		dataMap.put("allgold", traRechargemeal.getRechargegold().doubleValue()
				+ traRechargemeal.getGivegold().doubleValue());*/
        dataMap.put("realamt", traOrderinfom.getRealamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue());

        BigDecimal givepercent = traOrderinfom.getSumamt().subtract(traOrderinfom.getRealamt()).divide(traOrderinfom.getRealamt(), 3, BigDecimal.ROUND_DOWN);
        dataMap.put("givepercent", givepercent.setScale(3, BigDecimal.ROUND_DOWN).doubleValue());

        dataMap.put("rechargegold", traOrderinfom.getRealamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
        dataMap.put("givegold", traOrderinfom.getSumamt().subtract(traOrderinfom.getRealamt()).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
        dataMap.put("allgold", traOrderinfom.getSumamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue());

        if ("ALIPAY".equals(traOrderinfom.getPaytype())) {
            dataMap.put("paytypename", "支付宝");
        } else if ("WECHAT".equals(traOrderinfom.getPaytype())) {
            dataMap.put("paytypename", "微信");
        } else if ("NETBANK".equals(traOrderinfom.getPaytype())) {
            dataMap.put("paytypename", "银联");
        }

        dataMap.put("payuser", traOrderinfom.getPayuser());
        dataMap.put("paynote", traOrderinfom.getPaynote());
        // 收款账号
        SysPayaccount sysPayaccount = sysPayAccountMapperService.selectByPrimaryKey(traOrderinfom.getBankid());
        if (sysPayaccount != null) {
            dataMap.put("accountno", sysPayaccount.getAccountno());
            dataMap.put("accountname", sysPayaccount.getAccountname());
        } else {
            dataMap.put("accountno", null);
            dataMap.put("accountname", null);
        }
        // 获取图片
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

        return dataMap;
    }


    /**
     * @param orderid      订单id
     * @param accno        用户accno
     * @param givegold     赠送金额
     * @param rechargegold 縂金额
     * @param realamt      实际充值金额
     * @param opearteAccno 操作者
     * @param clientType   客户端
     */
    private void updateMemGoldchangeRechargeOrder(Long orderid, String accno, Double givegold, Double rechargegold, BigDecimal realamt, String opearteAccno, MemBaseinfo chongzhiBaseinfo, String clientType) {
        // 插入金币变动数据
        MemGoldchangeDO memGoldchange = new MemGoldchangeDO();
        memGoldchange.setRefid(orderid);
        memGoldchange.setAccno(accno);
        memGoldchange.setChangetype(GoldchangeEnum.RECHARGE.getValue());
        memGoldchange.setAmount(getTradeOffAmount(realamt));
        memGoldchange.setCreateUser(opearteAccno);
        memGoldchange.setUpdateUser(opearteAccno);
        memGoldchange.setOpnote("充值成功");
        memGoldchange.setSource(clientType);
        // 变动金额
        memGoldchange.setShowChange(getTradeOffAmount(realamt));
        memGoldchange.setNoWithdrawalAmount(getTradeOffAmount(realamt));
        memGoldchange.setQuantity(getTradeOffAmount(realamt.multiply(new BigDecimal(Constants.CHONGZHIBILIE))));
        memGoldchange.setUserId(chongzhiBaseinfo.getMemid().intValue());
        memGoldchange.setPayAmount(getTradeOffAmount(realamt));
        memGoldchange.setSource(clientType);
        memBaseinfoWriteService.updateUserBalance(memGoldchange);
        if (null != givegold || givegold > 0) {
            MemGoldchangeDO giveGoldchange = new MemGoldchangeDO();
            giveGoldchange.setRefid(orderid);
            giveGoldchange.setAccno(accno);
            giveGoldchange.setChangetype(GoldchangeEnum.RECHARGE_BONUS.getValue());
            giveGoldchange.setAmount(getTradeOffAmount(BigDecimal.valueOf(givegold)));
            giveGoldchange.setCreateUser(opearteAccno);
            giveGoldchange.setUpdateUser(opearteAccno);
            giveGoldchange.setOpnote(GoldchangeEnum.RECHARGE_BONUS.getName());
            giveGoldchange.setSource(clientType);
            // 变动金额
            giveGoldchange.setShowChange(getTradeOffAmount(BigDecimal.valueOf(givegold)));
            giveGoldchange.setNoWithdrawalAmount(getTradeOffAmount(BigDecimal.valueOf(givegold)));
            giveGoldchange.setQuantity(getTradeOffAmount(BigDecimal.valueOf(givegold).multiply(new BigDecimal(Constants.CHONGZHIBILIE))));
            giveGoldchange.setUserId(chongzhiBaseinfo.getMemid().intValue());
            giveGoldchange.setPayAmount(getTradeOffAmount(BigDecimal.ZERO));
            memBaseinfoWriteService.updateUserBalance(giveGoldchange);
        }
    }

    @Override
    public PageResult orderList(EntryOrderReq req, PageBounds page) {
        Page<EntryOrderResponse> list = traOrderinfomMapperService.orderList(req, page.toRowBounds());
        list.forEach(r -> {
            if (Constants.ORDER_ORD04.equals(r.getOrderstatus())) {
                r.setModifyusername(null);
                r.setUpdateTime(null);
            }
        });
        return PageResult.getPageResult(page, list);
    }

    @Override
    public List<EntryOrderExcelResponse> orderList(EntryOrderReq req) {
        return traOrderinfomMapperService.orderList(req);
    }

    @Override
    public EntryOrderTotalResponse orderListTotal(EntryOrderReq req) {

        if (!StringUtils.isBlank(req.getOrderstatus()) && !Constants.ORDER_ORD08.equals(req.getOrderstatus())) {
            EntryOrderTotalResponse entryOrderTotalResponse = new EntryOrderTotalResponse();
            entryOrderTotalResponse.setAllIn(BigDecimal.ZERO);
            entryOrderTotalResponse.setAllPay(BigDecimal.ZERO);
            entryOrderTotalResponse.setAllSend(BigDecimal.ZERO);
            return entryOrderTotalResponse;
        }
        EntryOrderTotalResponse entryOrderTotalResponse = traOrderinfomMapperService.orderListTotal(req);
        if (ObjectUtils.isEmpty(entryOrderTotalResponse)) {
            entryOrderTotalResponse = new EntryOrderTotalResponse();
            entryOrderTotalResponse.setAllIn(BigDecimal.ZERO);
            entryOrderTotalResponse.setAllPay(BigDecimal.ZERO);
            entryOrderTotalResponse.setAllSend(BigDecimal.ZERO);
        }
        return entryOrderTotalResponse;
    }


    @Override
    @DS("slave")
    public Map<String, Object> orderListReuslt(EntryOrderReq req, PageBounds page) {
        Map result = RedisBusinessUtil.getRechargeUnLineOrder(req, page);
        if (result != null && !result.isEmpty()) {
            return result;
        }

        result = new HashMap<>();
        result.put("pageInfo", orderList(req, page));
        result.put("totalInfo", orderListTotal(req));
        RedisBusinessUtil.addRechargeUnLineOrder(req, page, result);
        return result;
    }

    // -------------------------------------------线上订单----------------------------------------------------------

    @Override
    public PageResult onlineOrderList(EntryOrderReq req, PageBounds page) {
        if(req.getStartDate() != null && req.getStartDate() != ""){
            req.setStartDate(req.getStartDate()+" 00:00:00");
        }
        if(req.getEndDate() != null && req.getEndDate() != ""){
            req.setEndDate(req.getEndDate()+" 23:59:59");
        }
        Page<EntryOrderResponse> list = traOrderinfomMapperService.onlineOrderList(req, page.toRowBounds());
        list.forEach(r -> {
            if (Constants.ORDER_ORD04.equals(r.getOrderstatus())) {
                r.setModifyusername(null);
                r.setUpdateTime(null);
            }
        });
        return PageResult.getPageResult(page, list);
    }

    @Override
    public StatisticsOnLineOrderResponse getStatisticsOnLineOrder(EntryOrderReq req) {
        if (!StringUtils.isBlank(req.getOrderstatus()) && !Constants.ORDER_ORD08.equals(req.getOrderstatus())) {
            StatisticsOnLineOrderResponse entryOrderTotalResponse = new StatisticsOnLineOrderResponse();
            entryOrderTotalResponse.setTotalsumamt(BigDecimal.ZERO.doubleValue());
            entryOrderTotalResponse.setTotalrealamt(BigDecimal.ZERO.doubleValue());
            return entryOrderTotalResponse;
        } else {
            req.setOrderstatus(Constants.ORDER_ORD08);
        }
        return traOrderinfomMapperService.getStatisticsOnLineOrder(req);

    }

    @Override
    public List<EntryOnLineOrderExcelResponse> onlineOrderList(EntryOrderReq req) {
        //支付商查询
        if (!StringUtils.isEmpty(req.getProviderid())) {
            List<Long> list = sysThreePaysetService.getAllSysThreePaySet(req.getProviderid());
            if (list.size() > 0) {
                req.setTpaysetids(list);
            }
        }
        List<EntryOnLineOrderExcelResponse> list = traOrderinfomMapperService.onlineOrderList(req);
        list.forEach(r -> {
            if (Constants.ORDER_ORD04.equals(r.getOrderstatus())) {
                r.setModifyusername(null);
                r.setUpdateTime(null);
            }
        });
        for (EntryOnLineOrderExcelResponse entryOrderResponse : list) {
            String payprovider = sysPayproviderService.selectByTpaySetId(entryOrderResponse.getTpaysetid());
            if (!StringUtils.isEmpty(payprovider)) {
                entryOrderResponse.setProvider(payprovider);
            }
        }
        return list;
    }

    @Override
    public void doDayExportOrder() {
        List<TraOrderinfom> traOrderinfomList = traOrderinfomMapperService.doDayExportOrder(Constants.ORDER_ORD04, Constants.ORDERTYPE1);
        List<TraOrderinfom> orderList = new ArrayList<>();
        if (traOrderinfomList.size() > 0) {
            for (TraOrderinfom traOrderinfom : traOrderinfomList) {
                traOrderinfom.setOrderstatus(Constants.ORDER_ORD99);
                orderList.add(traOrderinfom);
            }
            traOrderinfomMapperService.batchUpdateOrder(orderList);
        }
    }

    @Override
    public Map<String, Object> getOnlineOrderDetail(EntryOrderReq req) {
        if (StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "订单编号为空");
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String orderno = req.getOrderno();
        // 获取订单信息
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(orderno);
        if (traOrderinfom == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "订单不存在");
        }

        // 会员
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
        if (memBaseinfo == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_103.getCode(), "下单用户不存在");
        }
        // 处理者
        BdUser bdUser = bdUserService.selectByAccno(traOrderinfom.getAccno());
        if (bdUser != null) {
            dataMap.put("modifyusername", bdUser.getBdusername());
        }
        dataMap.put("orderno", traOrderinfom.getOrderno());
        dataMap.put("paycode", traOrderinfom.getPaycode());
        dataMap.put("orderrealamt", traOrderinfom.getRealamt());
        dataMap.put("orderstatus", traOrderinfom.getOrderstatus());
        dataMap.put("paywechat", traOrderinfom.getPaywechat());
        dataMap.put("createdate", DateUtils.formatDate(traOrderinfom.getCreateTime()));
        dataMap.put("modifydate", DateUtils.formatDate(traOrderinfom.getUpdateTime()));
        dataMap.put("nickname", memBaseinfo.getNickname());
        dataMap.put("realamt", traOrderinfom.getRealamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
        dataMap.put("givepercent", (traOrderinfom.getSumamt().subtract(traOrderinfom.getRealamt())).divide(traOrderinfom.getRealamt(), 3, BigDecimal.ROUND_DOWN).setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
        dataMap.put("rechargegold", traOrderinfom.getRealamt().setScale(3, BigDecimal.ROUND_DOWN));
        dataMap.put("givegold", traOrderinfom.getSumamt().subtract(traOrderinfom.getRealamt()).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
        dataMap.put("allgold", traOrderinfom.getSumamt().doubleValue());

        if ("ALIPAY".equals(traOrderinfom.getPaytype())) {
            dataMap.put("paytypename", "支付宝");
        } else if ("WECHAT".equals(traOrderinfom.getPaytype())) {
            dataMap.put("paytypename", "微信");
        } else if ("NETBANK".equals(traOrderinfom.getPaytype())) {
            dataMap.put("paytypename", "银联");
        }

        dataMap.put("payuser", traOrderinfom.getPayuser());
        dataMap.put("paynote", traOrderinfom.getPaynote());
        // 收款账号
        SysPayaccount sysPayaccount = sysPayAccountMapperService.selectByPrimaryKey(traOrderinfom.getBankid());
        if (sysPayaccount != null) {
            dataMap.put("accountno", sysPayaccount.getAccountno());
            dataMap.put("accountname", sysPayaccount.getAccountname());
        } else {
            dataMap.put("accountno", null);
            dataMap.put("accountname", null);
        }
        // 获取图片
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

        // 商户
        MandatoryDO mandatoryDO = sysPayproviderService.findByOrderno(orderno);
        if (mandatoryDO != null) {
            dataMap.put("provider", mandatoryDO.getProvider());
            dataMap.put("tpayname", mandatoryDO.getTpayname());
        }
        return dataMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized String doRechargeOrderV2(OrderReq req, LoginUser loginUser) {

        if (req.getBeSucceed() == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "状态不能为空");
        }

        if (!req.getBeSucceed() && StringUtils.isBlank(req.getReason())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "失败原因不能为空");
        }

        if (StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "订单号为空");
        }


        // 修改订单 加入轨迹 充值用户播币
        String orderno = req.getOrderno();
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(orderno);
        if (traOrderinfom == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "订单不存在");
        }
        if (!Constants.ORDER_ORD04.equals(traOrderinfom.getOrderstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "订单状态不为待支付");
        }
        if (!req.getBeSucceed()) {
            failedOrder(req, loginUser);
            RedisBusinessUtil.delRechargeUnLineOrder();
            return Constants.SUCCESS_MSG;
        }

        traOrderinfom.setOrderstatus(Constants.ORDER_ORD08);
        traOrderinfom.setUpdateUser(loginUser.getAccno());
        traOrderinfom.setUpdateTime(new Date());
        traOrderinfom.setPaydate(new Date());

        // 每次都要判断 这笔单子是否 有这个支付设定的优惠
        SysPayset sysPayset = sysPaysetService.getOneBySettypeByOrderid(traOrderinfom.getOrderid());
        if (sysPayset == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1103.getCode(), "支付设定不存在");
        }
        double amount = traOrderinfom.getRealamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue();
        Double giftrate = sysPayset.getGiftrate().setScale(3, BigDecimal.ROUND_DOWN).doubleValue();
        Double maxgift = sysPayset.getMaxgift().setScale(3, BigDecimal.ROUND_DOWN).doubleValue();
        MemBaseinfo chongzhiBaseinfo = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
        // 入款优惠频率 1首次充值优惠 2每次充值优惠
        BigDecimal sumamt = null;
        Integer rechargetype = sysPayset.getRechargetype();
        if (rechargetype == 1) {
            double give = Double.parseDouble(String.valueOf(amount)) * giftrate;
            if (chongzhiBaseinfo.getPayAmount().doubleValue() > 0) {
                give = 0d;
            }

            if (maxgift != 0 && give > maxgift) {
                give = maxgift;
            }
            double sum = amount + give;
            sumamt = new BigDecimal(sum);
            sumamt = sumamt.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        } else {
            double give = Double.parseDouble(String.valueOf(amount)) * giftrate;
            if (maxgift != 0 && give > maxgift) {
                give = maxgift;
            }
            double sum = amount + give;
            sumamt = new BigDecimal(sum);
            sumamt = sumamt.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        }

        // 修改
        traOrderinfom.setSumamt(getTradeOffAmount(sumamt));

        int t = traOrderinfomMapperService.doUpdateRechargeOrder(traOrderinfom);
        if (t > 0) {
            // 订单轨迹信息
            TraOrdertracking traOrdertracking = new TraOrdertracking();
            traOrdertracking.setOrderid(traOrderinfom.getOrderid());
            traOrdertracking.setTrackdate(new Date());
            traOrdertracking.setOrderstatus(Constants.ORDER_ORD08);
            traOrdertracking.setOperuse(loginUser.getAccno());
            traOrdertracking.setTrackbody("管理员[" + loginUser.getBdusername() + "]处理线下充值订单");
            // 最后状态
            int tc = traOrdertrackingMapperExt.insertTraOrdertracking(traOrdertracking);
            if (!(tc > 0)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_202.getCode(), "已处理过该订单");
            }

            sysPayAccountMapperService.updateTotalAmount(loginUser.getAccno(), traOrderinfom.getBankid(), traOrderinfom.getRealamt());
            SysPayaccount syspayaccount = sysPayAccountMapperService.selectByPrimaryKey(traOrderinfom.getBankid());
            if (syspayaccount.getTotalAmount().compareTo(syspayaccount.getStopamt()) >= 0) {
                syspayaccount.setSysStatus(false);
                syspayaccount.setUpdateUser(loginUser.getAccno());
                syspayaccount.setUpdateTime(new Date());
                sysPayAccountMapperService.updateByPrimaryKeySelective(syspayaccount);
            }
            // 充值用户

            if (chongzhiBaseinfo != null) {
                // 处理充值用户得乐币
                this.updateMemGoldchangeRechargeOrder(traOrderinfom.getOrderid(), traOrderinfom.getAccno(), traOrderinfom.getSumamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue() - traOrderinfom.getRealamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue(), traOrderinfom.getSumamt().setScale(3, BigDecimal.ROUND_DOWN).doubleValue(), traOrderinfom.getRealamt(), loginUser.getAccno(), chongzhiBaseinfo, traOrderinfom.getSource());
                // 处理充值用户得充值等级
//                memLevelService.buyVIPLevel(chongzhiBaseinfo, traOrderinfom, loginUser);
            } else {
                throw new BusinessException(StatusCode.LIVE_ERROR_1106.getCode(), "充值用户不存在");
            }


            CreditRequest creditRequest = new CreditRequest();
            SysBusparameter sysBusparameter = sysBusParamService.selectByBusparamcode("w_init");
            if (sysBusparameter != null && com.likes.common.util.StringUtils.isNotBlank(sysBusparameter.getBusparamname())) {
                creditRequest.setIntegral(Integer.parseInt(sysBusparameter.getBusparamname()));
            } else {
                creditRequest.setIntegral(0);
            }
            creditRequest.setOperationType(1);
            creditRequest.setMemNo(traOrderinfom.getAccno());
            creditRequest.setSource(CreditChangeEnum.RECHARGE.getName());
            memCreditService.operation(creditRequest);

            //上级代理
            MemRelationship relation = memRelationshipService.findByAccno(traOrderinfom.getAccno());
            if (null != relation) {
                MemBaseinfo refUser = memBaseinfoService.getUserByAccno(relation.getRefaccno());
                if (null != refUser) {
                    CreditRequest creditRequest2 = new CreditRequest();
                    SysBusparameter sysBusparameter2 = sysBusParamService.selectByBusparamcode("t_w_init");
                    if (sysBusparameter2 != null && com.likes.common.util.StringUtils.isNotBlank(sysBusparameter2.getBusparamname())) {
                        creditRequest2.setIntegral(Integer.parseInt(sysBusparameter2.getBusparamname()));
                    } else {
                        creditRequest2.setIntegral(0);
                    }
                    creditRequest2.setOperationType(1);
                    creditRequest2.setMemNo(refUser.getAccno());
                    creditRequest2.setSource(CreditChangeEnum.RECOMMEND_RECHARGE.getName());
                    memCreditService.operation(creditRequest2);
                }
            }

            // 插入操作日志
            SysInfolog sysInfolog = new SysInfolog();
            sysInfolog.setAccno(loginUser.getAccno());
            sysInfolog.setOptcontent("公司入款[" + chongzhiBaseinfo.getUniqueId() + "]金额[" + traOrderinfom.getRealamt() + "]订单号[" + req.getOrderno() + "]入款成功");
            sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
            sysInfolog.setModelname("公司入款");
            sysInfolog.setOrginfo("doRechargeOrder");
            commonService.insertSelective(sysInfolog);
            RedisBusinessUtil.delRechargeUnLineOrder();
            LOGGER.info("{}***{}处理订单{}充值完成", loginUser.getBdusername(), loginUser.getAccno(), traOrderinfom.getAccno());
            return Constants.SUCCESS_MSG;
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_105.getCode(), "该订单已处理");
        }
    }

    /**
     * 失败充值订单
     */
    private void failedOrder(OrderReq req, LoginUser loginUser) {
        TraOrderinfomExample example = new TraOrderinfomExample();
        TraOrderinfomExample.Criteria criteria = example.createCriteria();
        criteria.andOrdernoEqualTo(req.getOrderno());
        TraOrderinfom orderinfom = traOrderinfomMapperService.selectOneByExample(example);
        orderinfom.setUpdateTime(new Date());
        orderinfom.setUpdateUser(loginUser.getAccno());
        orderinfom.setOrderstatus(Constants.ORDER_ORD13);
        orderinfom.setOrdernote(req.getReason());
        traOrderinfomMapperService.updateByPrimaryKeySelective(orderinfom);
        TraOrdertracking traOrdertracking = new TraOrdertracking();
        traOrdertracking.setOrderid(orderinfom.getOrderid());
        traOrdertracking.setTrackdate(new Date());
        traOrdertracking.setOrderstatus(Constants.ORDER_ORD13);
        traOrdertracking.setOperuse(orderinfom.getAccno());
        traOrdertracking.setTrackbody("用户[" + loginUser.getAcclogin() + "]将订单[" + req.getOrderno() + "]失败");
        traOrdertrackingMapperService.insertSelective(traOrdertracking);

        MemBaseinfo chongzhiBaseinfo = memBaseinfoService.getUserByAccno(orderinfom.getAccno());
        // 插入操作日志
        SysInfolog sysInfolog = new SysInfolog();
        sysInfolog.setAccno(loginUser.getAccno());
        sysInfolog.setOptcontent("公司入款[" + chongzhiBaseinfo.getUniqueId() + "]金额[" + orderinfom.getRealamt() + "]订单号[" + req.getOrderno() + "]充值失败");
        sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
        sysInfolog.setModelname("公司入款");
        sysInfolog.setOrginfo("doRechargeOrder");
        commonService.insertSelective(sysInfolog);
    }

    // -------------------------------------------强制入款----------------------------------------------------------
    // java中json字符串去掉转义字符
    // StringEscapeUtils.unescapeJava

    @Override
    public Map<String, Object> getMandatoryOrderDetail(EntryOrderReq req) {
        if (StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10001.getCode(), "订单编号为空");
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String orderno = req.getOrderno();
        // 获取订单信息
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(orderno);
        if (traOrderinfom == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10002.getCode(), "订单不存在");
        }

        if (!Constants.ORDER_ORD04.equals(traOrderinfom.getOrderstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10003.getCode(), "订单状态不为待支付");
        }

        // 查询第三方支付信息
        MandatoryDO mandatoryDO = sysPayproviderService.findByOrderno(orderno);
        if (mandatoryDO == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10002.getCode(), "支付信息不存在");
        }
        Integer issure = 9;
        String providercode = mandatoryDO.getProvidercode();
        Map<String, Object> threeMap = new HashMap<String, Object>();
        if (Constants.GONGZHUFU.equals(providercode)) {
            // 公主付查询
            String info = getGongzhufu(mandatoryDO, traOrderinfom);
            // StringEscapeUtils.unescapeJava(info)
            threeMap.put("source", info);
            threeMap.put("payprovider", "公主付");
            JSONObject jsonObject = JSONObject.parseObject(info);
            String code = jsonObject.getString("code");
            // 订单状态 0 未处理 1 交易成功 2 支付失败 3 关闭交易 4 支付超时
			/*if ("0".equals(code)) {
				threeMap.put("paycode", jsonObject.getString("paysapi_id"));
				threeMap.put("orderno", jsonObject.getString("order_id"));
				threeMap.put("order_amount", jsonObject.getString("real_price"));
				threeMap.put("orderstate", "公主付一定要去商户后台查看，它提供的接口不能反应出该订单是否支付成功,默认为可以入款");
				issure = 9;
			}else*/
            if ("1".equals(code)) {
                threeMap.put("paycode", jsonObject.getString("paysapi_id"));
                threeMap.put("orderno", jsonObject.getString("order_id"));
                threeMap.put("order_amount", jsonObject.getString("real_price"));
                threeMap.put("orderstate", "支付成功");
                issure = 0;
            } else {
                threeMap.put("paycode", jsonObject.getString("paysapi_id"));
                threeMap.put("orderno", jsonObject.getString("order_id"));
                threeMap.put("order_amount", jsonObject.getString("real_price"));
                threeMap.put("orderstate", "支付失败");
                issure = 9;
            }
        } else if (Constants.NIUNIU.equals(providercode)) {
            // 牛牛查询
            String info = getNiuniu(mandatoryDO, traOrderinfom);
            threeMap.put("source", info);
            threeMap.put("payprovider", "牛牛");
            JSONObject jsonObject = JSONObject.parseObject(info);
            String code = jsonObject.getString("code");
            if ("0".equals(code)) {
                threeMap.put("paycode", jsonObject.getString("order_id"));
                threeMap.put("orderno", jsonObject.getString("order_sn"));
                // 实际支付金额
                // threeMap.put("amount", jsonObject.getString("amount"));
                // 订单金额
                threeMap.put("order_amount", jsonObject.getString("order_amount"));
                // 1:支付成功 2:支付失败 3:支付中
                String order_state = jsonObject.getString("order_state");
                if ("1".equals(order_state)) {
                    threeMap.put("orderstate", "支付成功");
                    issure = 0;
                } else if ("3".equals(order_state)) {
                    // 支付中
                    threeMap.put("orderstate", "支付失败");
                } else if ("2".equals(order_state)) {
                    threeMap.put("orderstate", "支付失败");
                }
            } else {
                String msg = jsonObject.getString("msg");
                threeMap.put("msg", msg);
            }
        } else if (Constants.NEWBIAOBAI.equals(providercode)) {
            // 喷射
            String info = getNewbiaobai(mandatoryDO, traOrderinfom);
            threeMap.put("source", info);
            threeMap.put("payprovider", "喷射");
            JSONObject jsonObject = JSONObject.parseObject(info);
            String code = jsonObject.getString("returncode");
            if ("00".equals(code)) {
                threeMap.put("paycode", jsonObject.getString("transaction_id"));
                threeMap.put("orderno", jsonObject.getString("orderid"));
                threeMap.put("order_amount", jsonObject.getString("amount"));
                // NOTPAY-未支付 SUCCESS已支付
                String trade_state = jsonObject.getString("trade_state");
                if ("SUCCESS".equals(trade_state)) {
                    threeMap.put("orderstate", "支付成功");
                    issure = 0;
                } else {
                    threeMap.put("orderstate", "支付失败");
                }
            }
        }

        dataMap.put("issure", issure);
        dataMap.put("orderinfo", traOrderinfom);
        dataMap.put("threeorderinfo", threeMap);
        MemBaseinfo baseinfo = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
        if (null != baseinfo) {
            dataMap.put("nickname", baseinfo.getNickname());
        }
        MemLogin memLogin = memLoginService.findByAccno(traOrderinfom.getAccno());
        if (memLogin != null) {
            dataMap.put("acclogin", memLogin.getAcclogin());
        }
        return dataMap;
    }

    private String getNewbiaobai(MandatoryDO mandatoryDO, TraOrderinfom traOrderinfom) {
        String api_code = mandatoryDO.getAccountno();// 商户号
        String api_key = mandatoryDO.getSecretcode();// 签名秘钥
        Map<String, String> map = new HashMap<String, String>();
        map.put("pay_orderid", traOrderinfom.getOrderno());
        map.put("pay_memberid", api_code);
        String sign = PaySignUtil.getSign(map, api_key);
        map.put("sign", sign);
        map.remove("api_code");
        String queryurl = mandatoryDO.getTorderurl();
        // String posturl = queryurl + "?" + CommonFunction.getMapToString(map);
        try {
            LOGGER.info("newbiaobai posturl: {}", queryurl);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("pay_orderid", map.get("pay_orderid"));
            params.put("pay_memberid", map.get("pay_memberid"));
            params.put("pay_md5sign", map.get("sign"));

            String result = HttpUtils.sendPost(queryurl, params).content;
            LOGGER.info("喷射订单查询: {}", result);
            return result;
        } catch (Exception e) {
            LOGGER.error("getNewbiaobai occur error. params:{}", JSONObject.toJSONString(map), e);
        }
        return null;
    }

    /**
     * 牛牛订单查询 * 返回:
     */
    private String getNiuniu(MandatoryDO mandatoryDO, TraOrderinfom traOrderinfom) {
        String api_code = mandatoryDO.getAccountno();// 商户号
        String api_key = mandatoryDO.getSecretcode();// 签名秘钥
        Map<String, String> map = new HashMap<String, String>();
        map.put("login_id", api_code);
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        // LOGGER.info("timestamp:{}",timestamp);
        map.put("create_time", timestamp);
        map.put("sign_type", "MD5");
        int s = (int) (Math.random() * 9000) + 1000;
        map.put("nonce", String.valueOf(s));
        map.put("order_id", traOrderinfom.getPaycode());
        String sign = PaySignUtil.getNiuNiuSign(map, api_key);
        map.put("sign", sign);

        String queryurl = mandatoryDO.getTorderurl();
        // String posturl = queryurl + "?" + CommonFunction.getMapToString(map);
        try {
            LOGGER.info("niuniu posturl: {}", queryurl);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("login_id", map.get("login_id"));
            params.put("create_time", map.get("create_time"));
            params.put("sign_type", map.get("sign_type"));
            params.put("nonce", map.get("nonce"));
            params.put("order_id", map.get("order_id"));
            params.put("sign", map.get("sign"));
            String result = HttpUtils.sendPost(queryurl, params).content;
            LOGGER.info("牛牛订单查询: {}", result);
            return result;
        } catch (Exception e) {
            LOGGER.error("getNiuniu occur error. params:{}", JSONObject.toJSONString(map), e);
        }
        return null;
    }

    /**
     * 公主付订单查询 返回: messages 数组 否 是 返回提示 code 字符串，长度1 是 是 订单状态判断标准： 0 未处理 1 交易成功 2 支付失败 3 关闭交易 4 支付超时
     * paysapi_id 字符串 是 是 是此订单在Api服务器上的唯一编号 order_id 字符串，最长50位 是 是 是您在发起支付接口传入的您的自定义订单号 is_type 字符串
     * 是 是 必须，支付渠道： all聚合支付，alipay支付宝，wechat微信，alipay1支付宝1，wechat1微信1 ......等等可通过http://ip地址/channel/common/api_query查询，有显示的英文都是可用接口
     * price float，保留2位小数 是 是 是您在发起支付接口传入的订单价格 mark 字符串 是 是 你提交上来的订单备注信息 sign 字符串，最长32位 － 是
     * 必须，一定存在。我们把返回参与签名的参数，按照参数值首字母ASCII升序排序，并以url传参格式拼接在一起，最后加上您的商户秘钥。一起做md5-32位加密，取字符串大写。得到sign。您需要在您的服务端按照同样的算法，自己验证此sign是否正确。只在正确时，执行您自己逻辑中代码。
     */
    private String getGongzhufu(MandatoryDO mandatoryDO, TraOrderinfom traOrderinfom) {
        // Float price = traOrderinfom.getRealamt().setScale(2,
        // BigDecimal.ROUND_HALF_DOWN).floatValue();
        // String pricestr = String.format("%.2f",price);
        // order_id=202003201847005&api_code=38643335&return_type=json&sign=4826ADC6A124191214257328E51CEC14
        // System.out.println(pricestr);
        String api_code = mandatoryDO.getAccountno();// 商户号
        String api_key = mandatoryDO.getSecretcode();// 签名秘钥
        Map<String, String> map = new HashMap<String, String>();
        map.put("order_id", traOrderinfom.getOrderno());
        // map.put("price", pricestr);
        // map.put("is_type", mandatoryDO.getPaycode());
        map.put("return_type", "json");
        map.put("api_code", api_code);
        String sign = PaySignUtil.getSign(map, api_key);
        map.put("sign", sign);

        String queryurl = mandatoryDO.getTorderurl();
        String posturl = queryurl + "?" + CommonFunction.getMapToString(map);
        try {
            LOGGER.info("gongzhufu posturl: {}", posturl);
            // String result = HttpClient.post(posturl);
            // HttpRespons
            String result = HttpUtils.sendPost(posturl).content;
            LOGGER.info("公主付订单查询: {}", result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String doMandatoryOrder(EntryOrderReq req, LoginUser loginUser) {
        if (StringUtils.isEmpty(req.getOrderno())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1100.getCode(), "订单编号为空");
        }
        String orderno = req.getOrderno();
        // 获取订单信息
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.findByOrderno(orderno);
        if (traOrderinfom == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10002.getCode(), "订单不存在");
        }
        if (!Constants.ORDER_ORD04.equals(traOrderinfom.getOrderstatus())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10003.getCode(), "订单状态不为待支付");
        }

        // 查询第三方支付信息
        MandatoryDO mandatoryDO = sysPayproviderService.findByOrderno(orderno);
        if (mandatoryDO == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10002.getCode(), "支付信息不存在");
        }

        payDataOrder(traOrderinfom, loginUser);
        return Constants.SUCCESS_MSG;
    }


    /**
     * 强制入款 公共操作
     */
    @Transactional(rollbackFor = Exception.class)
    public void payDataOrder(TraOrderinfom traOrderinfom, LoginUser loginUser) {
        try {
            SysPayset sysPayset = sysPaysetService.getUseOne(1);
            MemBaseinfo chongzhiBaseinfo = memBaseinfoService.getUserByAccno(traOrderinfom.getAccno());
            double amount = getTradeOffAmount(traOrderinfom.getRealamt()).doubleValue();
            double momey = 0;
            //每次
            if (Constants.PAY_SET_TWO == sysPayset.getRechargetype()) {
                if (sysPayset.getGiftrate().compareTo(BigDecimal.ZERO) == 1) {
                    momey = amount * sysPayset.getGiftrate().doubleValue();
                    if (sysPayset.getMaxgift().compareTo(new BigDecimal(momey)) == -1) {
                        momey = sysPayset.getMaxgift().doubleValue();
                    }
                }
            } else if (Constants.PAY_SET_ONE == sysPayset.getRechargetype()) {
                //首次
                if (chongzhiBaseinfo.getPayAmount().compareTo(BigDecimal.ZERO) < 1) {
                    momey = amount * sysPayset.getGiftrate().doubleValue();
                    if (sysPayset.getMaxgift().compareTo(new BigDecimal(momey)) == -1) {
                        momey = sysPayset.getMaxgift().doubleValue();
                    }
                }
            }
            double amountCount = amount + momey;
            traOrderinfom.setSumamt(getTradeOffAmount(new BigDecimal(amountCount)));
            traOrderinfom.setOrderstatus(Constants.ORDER_ORD08);
            traOrderinfom.setUpdateTime(new Date());
            traOrderinfom.setUpdateUser(loginUser.getAccno());
            int i = traOrderinfomMapperService.updateStatus(traOrderinfom);
            if (i > 0) {

                // 订单轨迹信息
                TraOrdertracking traOrdertracking = new TraOrdertracking();
                traOrdertracking.setTrackdate(new Date());
                traOrdertracking.setOrderstatus(Constants.ORDER_ORD08);
                traOrdertracking.setOperuse(traOrderinfom.getAccno());
                traOrdertracking.setTrackbody("充值回调成功");
                traOrdertracking.setOrderid(traOrderinfom.getOrderid());
                traOrdertrackingMapperService.insertSelective(traOrdertracking);

                // 在更新paymentinfo
                TraPaymentinfo traPaymentinfo = traPaymentinfoMapperService.findByOrderno(traOrderinfom.getOrderno());
                traPaymentinfo.setPaynote("支付成功");
                traPaymentinfo.setPaystatus(Constants.PAYSTATUS0);
                traPaymentinfoMapperService.updateByPrimaryKeySelective(traPaymentinfo);

                // 充值用户

                // 会员等级变更
                //更新会员等级 替换 updateMemLevel
//                memLevelService.buyVIPLevel(chongzhiBaseinfo, traOrderinfom, loginUser);

                // 插入操作日志
                SysInfolog sysInfolog = new SysInfolog();
                sysInfolog.setAccno(loginUser.getAccno());
                sysInfolog.setOptcontent("线上入款[" + chongzhiBaseinfo.getUniqueId() + "]金额[" + getTradeOffAmount(new BigDecimal(amount)) + "]订单号[" + traOrderinfom.getOrderno() + "]入款成功");
                sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
                sysInfolog.setModelname("线上入款");
                sysInfolog.setOrginfo("pay");
                commonService.insertSelective(sysInfolog);


                //用户余额账变
                MemGoldchangeDO dto = new MemGoldchangeDO();
                dto.setAccno(traOrderinfom.getAccno());
                dto.setOpnote("在线充值");
                dto.setQuantity(getTradeOffAmount(new BigDecimal(amount).multiply(new BigDecimal(Constants.CHONGZHIBILIE))));
                dto.setCreatTime(new Date());
                dto.setChangetype(GoldchangeEnum.RECHARGE.getValue());
                dto.setNoWithdrawalAmount(getTradeOffAmount(new BigDecimal(amount).multiply(new BigDecimal(Constants.CHONGZHIBILIE))));
                dto.setPayAmount(getTradeOffAmount(new BigDecimal(amount)));
                dto.setUserId(chongzhiBaseinfo.getMemid().intValue());
                dto.setSource(traOrderinfom.getSource());
                dto.setRefid(traOrderinfom.getOrderid());
                boolean falg = memBaseinfoWriteService.updateUserBalance(dto);
                if (!falg) {
                    LOGGER.info("payDataOrder error parm{}", JSONObject.toJSONString(dto));
                    throw new RuntimeException("订单处理失败！");
                }

                if (momey > 0) {
                    MemGoldchangeDO zsDto = new MemGoldchangeDO();
                    zsDto.setAccno(traOrderinfom.getAccno());
                    zsDto.setOpnote("充值附赠");
                    zsDto.setQuantity(getTradeOffAmount(new BigDecimal(momey).multiply(new BigDecimal(Constants.CHONGZHIBILIE))));
                    zsDto.setChangetype(GoldchangeEnum.RECHARGE_BONUS.getValue());
                    zsDto.setCreatTime(new Date());
                    zsDto.setNoWithdrawalAmount(getTradeOffAmount(new BigDecimal(momey).multiply(new BigDecimal(Constants.CHONGZHIBILIE))));
                    zsDto.setPayAmount(getTradeOffAmount(BigDecimal.ZERO));
                    zsDto.setUserId(chongzhiBaseinfo.getMemid().intValue());
                    zsDto.setRefid(traOrderinfom.getOrderid());
                    zsDto.setSource(traOrderinfom.getSource());
                    boolean falgCheck = memBaseinfoWriteService.updateUserBalance(zsDto);
                    if (!falgCheck) {
                        LOGGER.info("payDataOrder error parm{}", JSONObject.toJSONString(dto));
                        throw new RuntimeException("订单处理失败！");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("订单处理失败！");
        }
    }
}
