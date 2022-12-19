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
                // 账号类型 1支付宝 2微信 3银联
                int accounttype = o.getAccounttype();
                o.setAccounttypename(MemBankAccountTypeEnum.valueOf(accounttype).getName());
                if (MemBankAccountTypeEnum.NETBANK.getValue().equals(accounttype)) {
                    String name = o.getAccountno().substring(o.getAccountno().length() - 4);
                    o.setAccountno(name);
                    // 查询银行
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
                        o.setOrdernote("提现失败");
                    }
                }

            });
        }
        return PageResult.getPageResult((int) list.getTotal(), page, list);
    }

    @Override
    public Map<String, Object> anchorBank(LoginUser loginUserAPP) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        // 银行卡信息

        //AnchorMemBankaccount anchorMemBankAccount = anchorMemBankAccountRest.getAnchorMemBankAccount(loginUserAPP.getAccno());

        MemBankaccount memBankaccount = memBankaccountService.findByAccno(loginUserAPP.getAccno());
        if (Objects.nonNull(memBankaccount)) {
            MemBankResponse o = new MemBankResponse();
            BeanUtils.copyProperties(memBankaccount, o);
            o.setReaccountno(memBankaccount.getAccountno());
            // 账号类型 1支付宝 2微信 3银联
            int accounttype = o.getAccounttype();
            if (Constants.ACCOUNTTYPE_ALIPAY == accounttype) {
                o.setAccounttypename("支付宝账户");
                String name = o.getAccountno().substring(0, 3) + "***" + o.getAccountno().substring(o.getAccountno().length() - 4);
                o.setAccountno(name);
            } else if (Constants.ACCOUNTTYPE_WECHAT == accounttype) {
                o.setAccounttypename("微信账户");
                String name = o.getAccountno().substring(0, 3) + "***" + o.getAccountno().substring(o.getAccountno().length() - 4);
                o.setAccountno(name);
            } else if (Constants.ACCOUNTTYPE_UNIONPAY == accounttype) {
                o.setAccounttypename("银联");
                String name = o.getAccountno().substring(0, 3) + "***" + o.getAccountno().substring(o.getAccountno().length() - 4);
                o.setAccountno(name);
                // 查询银行
                SysBusparameter sysBusparameter = sysBusParamService.selectByBusparamcode(o.getBankname());
                if (sysBusparameter != null) {
                    o.setBanknamealias(sysBusparameter.getBusparamname());
                }
            }

            dataMap.put("bank", o);
        } else {
            dataMap.put("bank", null);
        }

        // 是否是申请状态
        // 5.1检查是否已经存在提现订单
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setAccno(loginUserAPP.getAccno());
        List<String> orderstatusList = new ArrayList<String>();
        // ord05提现申请 ord07提现处理中
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
            throw new BusinessException(StatusCode.LIVE_ERROR_11008.getCode(), "请输入正确的钱包地址!");
        }
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setAccno(loginUserAPP.getAccno());
        List<String> orderstatusList = new ArrayList<>();
        // ord05提现申请 ord07提现处理中
        orderstatusList.add(Constants.ORDER_ORD05);
        orderstatusList.add(Constants.ORDER_ORD07);
        orderRequest.setOrderstatusList(orderstatusList);
        TraOrderinfom existOrderinfom = traOrderinfomMapperService.existOrderinfom(orderRequest);
        if (existOrderinfom != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "存在提现订单,不能更改钱包地址");
        }

        Long bankaccid;
        MemBankaccount bankaccount = memBankaccountService.findBankByAccno(loginUserAPP.getAccno());
        if (bankaccount != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_114.getCode(), "已经存在提现账号");
        }
//        MemBankaccount bankAddress = memBankaccountService.findBankByAddress(memBankaccount.getBankaddress());
//        if (bankAddress != null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_114.getCode(), "该地址已被绑定");
//        }
        // 不存在就新增
        memBankaccount.setAccno(loginUserAPP.getAccno());
        memBankaccount.setCheckstatus(Constants.CHECKSTATUS_8);
        memBankaccount.setCreateUser(loginUserAPP.getAccno());
        memBankaccount.setUpdateUser(loginUserAPP.getAccno());

        int i = memBankaccountService.insertBank(memBankaccount);
        if (i > 0) {
            bankaccid = memBankaccount.getBankaccid();
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_114.getCode(), "已经存在提现账号");
        }
        return bankaccid;
    }

    @Override
    public Long reSetAnchorBank(LoginUser loginUserAPP, MemBankaccount memBankaccount) {
        boolean b = udunClient.checkAddress("195", memBankaccount.getBankaddress());
        if (!b) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11008.getCode(), "请输入正确的钱包地址!");
        }
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setAccno(loginUserAPP.getAccno());
        List<String> orderstatusList = new ArrayList<String>();
        // ord05提现申请 ord07提现处理中
        orderstatusList.add(Constants.ORDER_ORD05);
        orderstatusList.add(Constants.ORDER_ORD07);
        orderRequest.setOrderstatusList(orderstatusList);
        TraOrderinfom existOrderinfom = traOrderinfomMapperService.existOrderinfom(orderRequest);
        if (existOrderinfom != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "存在提现订单,不能更改钱包地址");
        }
        Long bankaccid;
        MemBankaccount bankaccount = memBankaccountService.findBankByAccno(loginUserAPP.getAccno());
        if (bankaccount == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_107.getCode(), "不存在提现账号，请先绑定");
        }
//        MemBankaccount bankAddress = memBankaccountService.findBankByAddress(memBankaccount.getBankaddress());
//        if (bankAddress != null) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_114.getCode(), "该地址已被绑定");
//        }
        // 不存在就新增
        bankaccount.setUpdateUser(loginUserAPP.getAccno());
        bankaccount.setAccountno(memBankaccount.getAccountno());
        bankaccount.setAccounttype(memBankaccount.getAccounttype());
        bankaccount.setBankname(memBankaccount.getBankname());
        bankaccount.setBankaddress(memBankaccount.getBankaddress());
        int i = memBankaccountService.reset(bankaccount);
        if (i > 0) {
            bankaccid = bankaccount.getBankaccid();
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_108.getCode(), "修改提现账号失败");
        }
        return bankaccid;
    }


    private void checkMoney(BigDecimal apycamt) {
        Double apycamtInteger = apycamt.doubleValue();
        if (!(apycamtInteger > 0)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_995.getCode(), "金额必须大于0");
        }
        if ((apycamtInteger % 1) > 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_996.getCode(), "只能输入整数");
        }
    }


    // ------------------------------------------------v2-----------------------------------------------------------------------------------------
    @Override
    public String surep(LoginUser loginUserAPP, IncarnateRequest req) {
        if (StringUtils.isEmpty(req.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11002.getCode(), "资金密码为空");
        }
        // 检查支付密码
        MemLogin memLogin = memLoginService.findByAccno(loginUserAPP.getAccno());
        if (StringUtils.isEmpty(memLogin.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11000.getCode(), "该用户没有设置资金密码");
        }
        if (!req.getPaypassword().equals(memLogin.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11001.getCode(), "资金密码不正确");
        }
        return Constants.SUCCESS_MSG;
    }

    @Override
    //@Transactional(rollbackFor = Exception.class)
    public MemFinanceVO anchorIncarnateDataV2(LoginUser loginUserAPP) {
        // 所有得都能提现
        return tixianshujuallmoney(loginUserAPP);
    }

    /**
     * 提现数据 所有得数据
     *
     * @param loginUserAPP
     * @return
     */
    private MemFinanceVO tixianshujuallmoney(LoginUser loginUserAPP) {
        MemFinanceVO memFinanceVO = new MemFinanceVO();
        memFinanceVO.setIshavepay(0);
        // 查询用户是否存在支付密码
        MemLogin memLogin = memLoginService.findByAccno(loginUserAPP.getAccno());
        if (StringUtils.isEmpty(memLogin.getPaypassword())) {
            memFinanceVO.setIshavepay(9);
        }

        // 公司支付设定
        SysPayset gongsisysPayset = sysPaysetService.getUseOne(2);
        if (gongsisysPayset == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1111.getCode(), "没有设置支付设定");
        }
        memFinanceVO.setMaxchargeamt(gongsisysPayset.getMaxchargeamt());
        memFinanceVO.setMinchargeamt(gongsisysPayset.getMinchargeamt());
        // 1.计算 直播总收入 + 直播间彩票分成 + 彩票收入 + 充值
        // 能提现得收入
        // 累计收益(播币)
        UsersRequest param = new UsersRequest();
        param.setAccno(loginUserAPP.getAccno());
        List<Integer> changetypeList = new ArrayList<Integer>();
        // 充值 + 礼物收入 + 彩票收入
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

        // 全部 收入
        // 累计收益(播币)
        Double allliveincome = memGoldchangeService.getAllQuantityByType(param);
        memFinanceVO.setAllliveincome(getTradeOffAmount(new BigDecimal(allliveincome)));


        //公共字段
        // 免手续费单数 如前3单免手续费
        Integer freechargenums = gongsisysPayset.getFreechargenums();
        // 单笔手续费（元） 0为不要手续费
        BigDecimal servicechargeDouble = gongsisysPayset.getServicecharge().setScale(3, BigDecimal.ROUND_HALF_DOWN);
        memFinanceVO.setShouxufei(BigDecimal.ZERO);

        //// 下面计算手续费
        // 如果提现 当前 是否需要手续费 ， 查询 提现中/已经提现 得单子 是否超多 免单数
        List<TraOrderinfom> yitixianList = getTraOrderinfomsForPay(loginUserAPP);

        if (CollectionUtils.isNotEmpty(yitixianList) && yitixianList.size() < freechargenums) {
            memFinanceVO.setShouxufei(servicechargeDouble);
        }


        // 用户当前拥有的金币 检查用户是否存在
        MemBaseinfo memBaseinfo = memBaseinfoService.getUserByAccno(loginUserAPP.getAccno());

        if (memBaseinfo == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1102.getCode(), "用户不存在");
        }
        // 已提醒金额
        memFinanceVO.setAllincarnate(getTradeOffAmount(memBaseinfo.getWithdrawalAmount()));
        memFinanceVO.setHaixudamaliang(memBaseinfo.getNoWithdrawalAmount());

        // 前台 要把 行政法 + 手续费 加到一起 叫手续费 重新赋值

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
        //每日的免单数
        return traOrderinfomMapperService.findOrderByAccnoAndStatus(yitixianRequest);
    }


    /**
     * 当在事务块中调用不带@Transactional的方法时，父事务将继续使用新方法。
     * 它将使用与父方法(使用@Transactional)相同的连接，并且在被调用方法中引起的任何异常(没有@Transactional)将导致事务按事务定义中配置的方式回滚。
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
            throw new BusinessException(StatusCode.LIVE_ERROR_1104.getCode(), "提现金额为空");
        }
        if (req.getApycamt().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1105.getCode(), "提现金额不能为负数");
        }
        if (StringUtils.isEmpty(req.getPaypassword())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11002.getCode(), "提现密码为空");
        }
        if (new BigDecimal(req.getApycamt().intValue()).compareTo(req.getApycamt()) != 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1107.getCode(), "提现金额不能为小数");
        }
        Integer xyf = memCreditService.selectCreditByMemNo(loginUserAPP.getAccno());
        if (xyf < 60) {
            throw new BusinessException(StatusCode.LIVE_ERROR_160.getCode(), "信誉分太低,不能进行该操作！");
        }
        MemberLevelResponse response = memLevelConfigService.getMemLevelConfig(loginUserAPP.getAccno());
        if (response.equals(null) || response.getLevelSeq() < 1) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1107.getCode(), "vip0无法申请提现");
        }
        MemBankaccount bankaccount = memBankaccountService.findBankByAccno(loginUserAPP.getAccno());
        if (ObjectUtil.isNull(bankaccount) || StringUtils.isBlank(bankaccount.getBankaddress())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_1199.getCode(), "请先绑定钱包地址");
        }
        req.setMoneyAddress(bankaccount.getBankaddress());
        DzCoin dzCoin = memWalletService.getCoinType(req.getCoinName());
        memWalletService.checkAddress(dzCoin.getMainCoinType(), req.getMoneyAddress());
//        SysPayset syspayset = sysPaysetService.getUseOne(2);
//        if (ObjectUtils.isEmpty(syspayset)) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_1103.getCode(), "支付设定不存在");
//        }
//        if (syspayset.getMaxchargeamt() != null && syspayset.getMaxchargeamt().compareTo(BigDecimal.ZERO) == 1 && syspayset.getMaxchargeamt().compareTo(req.getApycamt()) == -1) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_130001.getCode(), "已超出单笔最高限额");
//        }
//        if (syspayset.getMinchargeamt() != null && syspayset.getMinchargeamt().compareTo(BigDecimal.ZERO) == 1 && syspayset.getMinchargeamt().compareTo(req.getApycamt()) == 1) {
//            throw new BusinessException(StatusCode.LIVE_ERROR_130002.getCode(), "未满足单笔最低限额");
//        }

//        Integer integral = memCreditService.selectCreditByMemNo(loginUserAPP.getAccno());
//        if(integral < 50){
//            throw new BusinessException(StatusCode.LIVE_ERROR_130007.getCode(), "您的信用积分小于50，已呗限制提现");
//        }
        // 1.检查由于只能提1元的倍数，零头部分这里不显示
        this.checkMoney(req.getApycamt());
        // 随时都可以提现 ，只要你有钱
        // 2.检查支付密码
        MemLogin memLogin = memLoginService.findByAccno(loginUserAPP.getAccno());
        if (!req.getPaypassword().equals(memLogin.getPasswordmd5())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11001.getCode(), "提现密码不正确");
        }
        // 3.是否存在体现申请
        TraApplycash traApplycash = applycashMapperService.findNotInCashByCashByAccno(loginUserAPP.getAccno());
        if (traApplycash != null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "存在提现订单");
        }
        MemBaseinfo membaseinfo = memBaseinfoService.getUserByAccno(loginUserAPP.getAccno());
        // 体现金额
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

        // 能体现金额等于 账户余额，在打码量为0的情况下 手续费为0,行政费均为0
        double incarnatemoney = membaseinfo.getGoldnum().doubleValue();
        Double haixudamaliang = membaseinfo.getNoWithdrawalAmount().doubleValue();
        // 比较想提现的金额 与 能提现的金额
        int a = (int) incarnatemoney;
        int b = (int) xiangti + sxfInt;
        if (a < b) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11006.getCode(), "余额不足");
        }
        //首次最小提现金额
        SysBusparameter f_min_w = this.sysBusParamService.selectByBusparamcode("f_min_w");
        int todayWnum = traOrderinfomMapperExt.countTodayWithdrawal(loginUserAPP.getAccno());

        if (xiangti < Double.valueOf(f_min_w.getBusparamname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11004.getCode(), "提现金额小于最低提现金额!");
        }
        //每日限提笔数
        SysBusparameter day_w_num = this.sysBusParamService.selectByBusparamcode("day_w_num");
        if (todayWnum >= Integer.parseInt(day_w_num.getBusparamname())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_11007.getCode(), "超过今日限提笔数!");
        }
        Double sxf = Double.valueOf(sxfInt);


        // 创建 订单 / 订单轨迹 / 提现申请/ 提现稽核对账/播币变化 / 更新用户播币
        double sumamt = xiangti;
        String businessId = SnowflakeIdWorker.generateShortId();
        // 创建订单
        Long orderid = doCreateTixianOrder(req, businessId, loginUserAPP, xiangti, sxf, 0);

        // 创建提现申请
        this.doCreateTraApplycashV2(req, loginUserAPP, orderid, sumamt, null, 0D, haixudamaliang);
        // 金币变化
        req.setApycamt(BigDecimal.ZERO.subtract(req.getApycamt()));
        req.setUserId(membaseinfo.getMemid().intValue());
        this.updateMemGoldchangeApplycashIncarnateV2(req, loginUserAPP, orderid, xiangti, sxf, 0);
        RedisBusinessUtil.delIncarnateOrderListCahce();

        SysBusparameter w_audit_amout = this.sysBusParamService.selectByBusparamcode("w_audit_amout");
        boolean udunFLag = new BigDecimal(sumamt).intValue() < Integer.parseInt(w_audit_amout.getBusparamname());
        if (StringUtils.isNotBlank(req.getCoinName()) && udunFLag) {
            boolean flag = memWalletService.submitWithdraw(req.getCoinName(), businessId, new BigDecimal(sumamt), req.getMoneyAddress(), loginUserAPP);
            if (!flag) {
                throw new RuntimeException("发起提现失败");
            }
        }
        return Constants.SUCCESS_MSG;
    }

    private void updateMemGoldchangeApplycashIncarnateV2(IncarnateRequest req, LoginUser loginUserAPP, Long orderid, double xiangti, double shouxufei, double xingzhengfei) {
        MemBaseinfo baseinfo = memBaseinfoService.selectByPrimaryKey(loginUserAPP.getMemid());
        req.setApycamt(req.getApycamt().subtract(new BigDecimal(shouxufei)));
        Jian(req, loginUserAPP, orderid, GoldchangeEnum.WITHDRAWAL_APPLY.getValue(), "提现申请", baseinfo.getNoWithdrawalAmount());
        // 2.
        double allfei = shouxufei + xingzhengfei;
        String opnote = "";
        // 稽核手续费
        if (shouxufei > 0) {
            opnote += "手续费(" + shouxufei + ") ";
        }
        if (xingzhengfei > 0) {
            opnote += "行政费(" + xingzhengfei + ") ";
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

        // 插入
        int i = applycashMapperService.doInsertIncarnate(traApplycash);
        if (!(i > 0)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "存在提现申请");
        }
        return traApplycash.getApycid();
    }


    /**
     * 创建提现订单
     *
     * @param loginUserAPP
     * @param xiangti
     * @param shouxufei
     * @param xingzhengfei
     */
    private Long doCreateTixianOrder(IncarnateRequest req, String businessId, LoginUser loginUserAPP, double xiangti, double shouxufei, double xingzhengfei) {
        // 订单总金额
        double sumamt = xiangti + shouxufei + xingzhengfei;
        // 实付金额
        double realamt = xiangti;

        Date nowDate = new Date();
        // 主表信息
        TraOrderinfom traOrderinfom = new TraOrderinfom();
        traOrderinfom.setMealid(null);
        traOrderinfom.setOrdertype(Constants.ORDERTYPE4);
        traOrderinfom.setOrderno(null);
        traOrderinfom.setAccno(loginUserAPP.getAccno());
        traOrderinfom.setPaycode(null);
        traOrderinfom.setOrderdate(nowDate);
        // 账号类型 1支付宝 2微信 3银联
        // APP--app支付 NETBANK 网银转账 WECHAT 微信收款 ALIPAY 支付宝支付
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
        traOrderinfom.setOrdernote("用户[" + loginUserAPP.getNickname() + "]提现");
        traOrderinfom.setCreateUser(loginUserAPP.getAccno());
        traOrderinfom.setUpdateUser(loginUserAPP.getAccno());
        traOrderinfom.setSource(resolveClientType(request));

        traOrderinfom.setOrderno(businessId);
        // 插入提现订单
        int i = traOrderinfomMapperService.insertIncarateOrder(traOrderinfom);
        if (i > 0) {
            // 加入订单轨迹
            // 订单轨迹信息
            TraOrdertracking traOrdertracking = new TraOrdertracking();
            traOrdertracking.setOrderid(traOrderinfom.getOrderid());
            traOrdertracking.setTrackdate(new Date());
            traOrdertracking.setOrderstatus(Constants.ORDER_ORD05);
            traOrdertracking.setOperuse(loginUserAPP.getAccno());
            traOrdertracking.setTrackbody("用户[" + loginUserAPP.getNickname() + "]申请提现");
            traOrdertrackingMapperService.insertSelective(traOrdertracking);
            return traOrderinfom.getOrderid();
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "您已存在提现订单");
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
        // 先查询 提现申请
        TraApplycash param = new TraApplycash();
        param.setAccno(loginUserAPP.getAccno());
        // param.setApycstatus(Constants.APYCSTATUS1);
        TraApplycash traApplycash = applycashMapperService.findCashByCash(param);
        if (traApplycash == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "您没有提现记录");
        }
        //
        if (Constants.APYCSTATUS1 != traApplycash.getApycstatus()) {
            // throw new BusinessException(106, "状态不为待处理，不能取消");
            throw new BusinessException(StatusCode.LIVE_ERROR_106.getCode(), "提现申请已受理,不能取消");
        }

        // 金币
        // long goldnum = traApplycash.getApycgold();
        // long goldnum = traApplycash.getApycgold().longValue();
        long orderid = traApplycash.getOrderid();
        TraOrderinfom traOrderinfom = traOrderinfomMapperService.selectByPrimaryKey(orderid);
        if (traOrderinfom == null || traOrderinfom.getIsDelete()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_102.getCode(), "提现订单不存在");
        }

        if (!traOrderinfom.getOrderstatus().equals(Constants.ORDER_ORD05)) {
            throw new BusinessException(StatusCode.LIVE_ERROR_109.getCode(), "提现订单状态不为提现申请");
        }

        // 更新 提现申请
        traApplycash.setApycstatus(Constants.APYCSTATUS9);
        traApplycash.setUpdateUser(loginUserAPP.getAccno());
        int i = applycashMapperService.updateTraApplycash(traApplycash);
        if (i > 0) {
            // 更新订单
            traOrderinfom.setOrderstatus(Constants.ORDER_ORD06);
            traOrderinfom.setUpdateUser(loginUserAPP.getAccno());
            traOrderinfomMapperService.updateByPrimaryKeySelective(traOrderinfom);

            // 订单轨迹信息
            TraOrdertracking traOrdertracking = new TraOrdertracking();
            traOrdertracking.setOrderid(traOrderinfom.getOrderid());
            traOrdertracking.setTrackdate(new Date());
            traOrdertracking.setOrderstatus(Constants.ORDER_ORD06);
            traOrdertracking.setOperuse(loginUserAPP.getAccno());
            traOrdertracking.setTrackbody("用户[" + loginUserAPP.getNickname() + "]取消申请提现");
            traOrdertrackingMapperService.insertSelective(traOrdertracking);

            // 给用户返回金币
            this.updateMemGoldchangeCancelIncarnateV2(traApplycash, loginUserAPP, orderid);

            // 删除 稽核记录
            this.doDelJihe(traApplycash.getApycid());
        } else {
            throw new BusinessException(StatusCode.LIVE_ERROR_110.getCode(), "取消提现申请失败");
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

        // 插入体现取消
        jia(apycamt, loginUserAPP, orderid, GoldchangeEnum.WITHDRAWAL_CANCLE.getValue(), "提现取消返回播币");
        if (allfei > 0) {
            // 返回行政费 17稽核手续费取消 稽核手续费取消返回播币
//            jia(allfei, loginUserAPP, orderid, GoldchangeEnum.AUDIT_FEE_CANCLE.getValue(), "手续费取消返回播币");
        }
    }

    private void jia(double goldnums, LoginUser loginUserAPP, long orderid, Integer changetype, String opnote) {
        Double goldnum = (new BigDecimal(goldnums)).doubleValue();
        // 更新金币参数
        UsersRequest usersRequest = new UsersRequest();
        BigDecimal tradeOffAmount = getTradeOffAmount(new BigDecimal(goldnum));
        usersRequest.setGoldnum(tradeOffAmount.doubleValue());
        usersRequest.setAccno(loginUserAPP.getAccno());

        // 插入金币变动数据
        MemGoldchange memGoldchange = new MemGoldchange();
        memGoldchange.setRefid(orderid);
        memGoldchange.setAccno(loginUserAPP.getAccno());
        memGoldchange.setChangetype(changetype);
        memGoldchange.setQuantity(tradeOffAmount);
        memGoldchange.setAmount(tradeOffAmount);
        memGoldchange.setCreateUser(loginUserAPP.getAccno());
        memGoldchange.setUpdateUser(loginUserAPP.getAccno());
        memGoldchange.setOpnote(opnote);
        // 再insert处枷锁
        int i = memGoldchangeService.insertSelectiveMemGoldchange(memGoldchange);
        if (i > 0) {
            memBaseinfoService.updateAddGold(usersRequest);
            logger.info("用户取消，返回播币成功");
        } else {
            logger.info("已经加过金币");
            throw new BusinessException(StatusCode.LIVE_ERROR_2104.getCode(), "已经加过金币");
        }
    }

}
