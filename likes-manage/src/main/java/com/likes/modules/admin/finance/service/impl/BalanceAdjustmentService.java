package com.likes.modules.admin.finance.service.impl;

import com.likes.common.constant.Constants;
import com.likes.common.constant.ModuleConstant;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.LoginUserTypeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.request.FinanceBalanceAdjustmentRequest;
import com.likes.common.model.vo.finance.FinanceMemBaseVo;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.FinanceBalanceAdjustmentMapper;
import com.likes.common.mybatis.mapperext.member.MemLevelMapperExt;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemBaseinfoWriteService;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.service.member.MemRelationshipService;
import com.likes.common.service.money.TraOrderinfomService;
import com.likes.common.util.BeanUtils;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.SnowflakeIdWorker;
import com.likes.common.util.StringUtils;
import com.likes.modules.admin.common.service.CommonService;
import com.github.pagehelper.Page;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

@Service
public class BalanceAdjustmentService {
    private final Logger logger = LogManager.getLogger(getClass());


    @Resource
    private FinanceBalanceAdjustmentMapper financeBalanceAdjustmentMapper;
    @Resource
    private MemBaseinfoWriteService memBaseinfoWriteService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemLoginService memLoginService;
    @Resource
    private MemLevelMapperExt memLevelMapperExt;
    @Resource
    private MemRelationshipService memRelationshipService;
    @Resource
    private CommonService commonService;
    @Resource
    private TraOrderinfomService traOrderinfomMapperService;

    public PageResult finaceMemList(String email, String nickname, String uniqueId, PageBounds toRowBounds) {
        if (StringUtils.isBlank(nickname)) {
            nickname = "";
        }
        nickname = "%" + nickname + "%";
        Page<FinanceMemBaseVo> page = memLoginService.selectMemForFInance(email, nickname, uniqueId, toRowBounds.toRowBounds());
        page.forEach(financeMemBaseVo -> {
            MemLevel memlevel = memLevelMapperExt.selectByAccno(financeMemBaseVo.getAccno());
            if (ObjectUtils.isNotEmpty(memlevel)) {
                financeMemBaseVo.setMemlevel(memlevel.getMemlevel());
            }
            MemRelationship relationship = memRelationshipService.findByAccno(financeMemBaseVo.getAccno());
            if (ObjectUtils.isNotEmpty(relationship)) {
                MemBaseinfo membase = memBaseinfoService.getUserByAccno(relationship.getRefaccno());
                if (ObjectUtils.isNotEmpty(membase)) {
                    financeMemBaseVo.setRelaname(membase.getUniqueId());
                }
            }

        });
        return PageResult.getPageResult(toRowBounds, page);
    }


    public ResultInfo list(String email, String nickname, String uniqueId, PageBounds toRowBounds) {
        return ResultInfo.ok(finaceMemList(email, nickname, uniqueId, toRowBounds));
    }


    @Transactional
    public void adjustment(FinanceBalanceAdjustment financeBalanceAdjustment) {
        if (ObjectUtils.isEmpty(financeBalanceAdjustment)) {
            throw new BusinessException("??????????????????");
        }
        if (!GoldchangeEnum.MANUALLY_OUTGO_MONEY.getValue().equals(financeBalanceAdjustment.getType()) && !GoldchangeEnum.MANUALLY_INCOME_MONEY.getValue().equals(financeBalanceAdjustment.getType())) {
            throw new BusinessException("?????????????????????");
        }
        if (financeBalanceAdjustment.getAmount() == null || financeBalanceAdjustment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("????????????????????????");
        }


        if (StringUtils.isBlank(financeBalanceAdjustment.getAccno())) {
            throw new BusinessException("????????????");
        }
        financeBalanceAdjustment.setAmount(financeBalanceAdjustment.getAmount().setScale(3, BigDecimal.ROUND_DOWN));

        MemBaseinfo membaseinfo = memBaseinfoService.getUserByAccno(financeBalanceAdjustment.getAccno());
        if (GoldchangeEnum.MANUALLY_OUTGO_MONEY.getValue().equals(financeBalanceAdjustment.getType())) {
            if (membaseinfo.getGoldnum().compareTo(financeBalanceAdjustment.getAmount()) == -1) {
                throw new BusinessException("??????[" + membaseinfo.getUniqueId() + "]??????????????????");
            }
        }
        financeBalanceAdjustment.setAmount(getTradeOffAmount(financeBalanceAdjustment.getAmount()));
        financeBalanceAdjustmentMapper.insertSelective(financeBalanceAdjustment);

        TraOrderinfom traOrderinfom = new TraOrderinfom();
        //???????????????
        traOrderinfom.setOrdertype(Constants.ORDERTYPE1);
        traOrderinfom.setOrderno(SnowflakeIdWorker.generateShortId());
        traOrderinfom.setAccno(membaseinfo.getAccno());
        traOrderinfom.setOrderdate(new Date());


        //??????????????????????????????????????????
        traOrderinfom.setSumamt(financeBalanceAdjustment.getAmount());
        traOrderinfom.setRealamt(financeBalanceAdjustment.getAmount());

        traOrderinfom.setIsinvoice(9);
        traOrderinfom.setOrderstatus(Constants.ORDER_ORD08);
        traOrderinfom.setAccountstatus(Constants.ORDER_ACC08);
        traOrderinfom.setOrdernote("??????[" + membaseinfo.getNickname() + "]????????????: ");
        if (GoldchangeEnum.MANUALLY_OUTGO_MONEY.getValue().equals(financeBalanceAdjustment.getType())) {
            traOrderinfom.setOrdertype(Constants.ORDERTYPE9);
            traOrderinfom.setOrderstatus(Constants.ORDER_ORD081);
            traOrderinfom.setOrdernote("??????[" + membaseinfo.getNickname() + "]????????????: ");
        }
        traOrderinfom.setPaydate(new Date());
        int i = traOrderinfomMapperService.insertOrder(traOrderinfom);
        if (i < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10004.getCode(), "?????? ????????????");
        }
        updateChangeBalance(financeBalanceAdjustment);

    }
    @Transactional
    public void adJackpot(FinanceBalanceAdjustment financeBalanceAdjustment) {
        if (financeBalanceAdjustment.getAmount() == null || financeBalanceAdjustment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("????????????????????????");
        }
        if (StringUtils.isBlank(financeBalanceAdjustment.getAccno())) {
            throw new BusinessException("????????????");
        }
        financeBalanceAdjustment.setAmount(financeBalanceAdjustment.getAmount().setScale(3, BigDecimal.ROUND_DOWN));

        MemBaseinfo membaseinfo = memBaseinfoService.getUserByAccno(financeBalanceAdjustment.getAccno());
        financeBalanceAdjustmentMapper.insertSelective(financeBalanceAdjustment);

        TraOrderinfom traOrderinfom = new TraOrderinfom();
        //???????????????
        traOrderinfom.setOrdertype(Constants.ORDERTYPE5);
        traOrderinfom.setOrderno(SnowflakeIdWorker.generateShortId());
        traOrderinfom.setAccno(membaseinfo.getAccno());
        traOrderinfom.setOrderdate(new Date());

        //??????????????????????????????????????????
        traOrderinfom.setSumamt(financeBalanceAdjustment.getAmount());
        traOrderinfom.setRealamt(financeBalanceAdjustment.getAmount());

        traOrderinfom.setIsinvoice(9);
        traOrderinfom.setOrderstatus(Constants.ORDER_ORD30);
        traOrderinfom.setAccountstatus(Constants.ORDER_ACC08);
        traOrderinfom.setOrdernote("??????[" + membaseinfo.getNickname() + "]????????????: ");
        traOrderinfom.setPaydate(new Date());
        int i = traOrderinfomMapperService.insertOrder(traOrderinfom);
        if (i < 0) {
            throw new BusinessException(StatusCode.LIVE_ERROR_10004.getCode(), "??????????????????");
        }
        MemGoldchangeDO memGoldchangeDO = new MemGoldchangeDO();
        memGoldchangeDO.setRefid(financeBalanceAdjustment.getId());
        memGoldchangeDO.setUserId(membaseinfo.getMemid().intValue());
        memGoldchangeDO.setShowChange(getTradeOffAmount(financeBalanceAdjustment.getAmount()));
        memGoldchangeDO.setOpnote(financeBalanceAdjustment.getRemark());
        memGoldchangeDO.setCreateUser(membaseinfo.getAccno());
        memGoldchangeDO.setUpdateUser(membaseinfo.getAccno());

        memGoldchangeDO.setQuantity(financeBalanceAdjustment.getAmount());
        memGoldchangeDO.setAmount(getTradeOffAmount(financeBalanceAdjustment.getAmount()));
        memGoldchangeDO.setChangetype(GoldchangeEnum.JACKPOT.getValue());
        memBaseinfoWriteService.updateUserBalance(memGoldchangeDO);

    }


    private void updateChangeBalance(FinanceBalanceAdjustment financeBalanceAdjustment) {
        if (GoldchangeEnum.MANUALLY_OUTGO_MONEY.getValue().equals(financeBalanceAdjustment.getType())) {
            financeBalanceAdjustment.setDama(false);
        }
        MemBaseinfo mem = memBaseinfoService.getUserByAccno(financeBalanceAdjustment.getAccno());
        MemGoldchangeDO memGoldchangeDO = new MemGoldchangeDO();
        memGoldchangeDO.setRefid(financeBalanceAdjustment.getId());
        memGoldchangeDO.setUserId(mem.getMemid().intValue());
        memGoldchangeDO.setShowChange(getTradeOffAmount(financeBalanceAdjustment.getAmount()));
        memGoldchangeDO.setOpnote(financeBalanceAdjustment.getRemark());
        memGoldchangeDO.setCreateUser(mem.getAccno());
        memGoldchangeDO.setUpdateUser(mem.getAccno());
        if (financeBalanceAdjustment.getDama() != null && financeBalanceAdjustment.getDama()) {
            memGoldchangeDO.setNoWithdrawalAmount(getTradeOffAmount(financeBalanceAdjustment.getAmount().multiply(financeBalanceAdjustment.getDamaRatio())));
            if (GoldchangeEnum.MANUALLY_OUTGO_MONEY.getValue().equals(financeBalanceAdjustment.getType())) {
                memGoldchangeDO.setNoWithdrawalAmount(getTradeOffAmount(null));
            }
        }

        memGoldchangeDO.setQuantity(getTradeOffAmount(financeBalanceAdjustment.getAmount().multiply(new BigDecimal(Constants.CHONGZHIBILIE))));
        memGoldchangeDO.setAmount(getTradeOffAmount(financeBalanceAdjustment.getAmount()));
        if (GoldchangeEnum.MANUALLY_OUTGO_MONEY.getValue().equals(financeBalanceAdjustment.getType())) {
            BigDecimal tradeOffAmount = getTradeOffAmount(financeBalanceAdjustment.getAmount().multiply(BigDecimal.valueOf(-1)));
            memGoldchangeDO.setQuantity(tradeOffAmount);
            memGoldchangeDO.setAmount(tradeOffAmount);

        }
        if (GoldchangeEnum.MANUALLY_INCOME_MONEY.getValue().equals(financeBalanceAdjustment.getType())) {
            memGoldchangeDO.setPayAmount(getTradeOffAmount(financeBalanceAdjustment.getAmount()));
        }
        memGoldchangeDO.setChangetype(financeBalanceAdjustment.getType());
        memBaseinfoWriteService.updateUserBalance(memGoldchangeDO);

        // ?????????????????????
        String content = null;
        String modelname = null;
        if (GoldchangeEnum.MANUALLY_OUTGO_MONEY.getValue().equals(financeBalanceAdjustment.getType())) {
            content = "????????????[" + mem.getUniqueId() + "]??????[" + memGoldchangeDO.getQuantity().abs() + "]";
            modelname = "????????????";
        } else if (GoldchangeEnum.MANUALLY_INCOME_MONEY.getValue().equals(financeBalanceAdjustment.getType())) {
            content = "????????????[" + mem.getUniqueId() + "]??????[" + memGoldchangeDO.getQuantity().abs() + "]";
            modelname = "????????????";
        }
        SysInfolog sysInfolog = new SysInfolog();
        sysInfolog.setAccno(financeBalanceAdjustment.getCreateUser());
        sysInfolog.setOptcontent(content);
        sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
        sysInfolog.setModelname(modelname);
        sysInfolog.setOrginfo("adjustment");
        commonService.insertSelective(sysInfolog);
    }

    @Transactional
    public void batchAdjustment(FinanceBalanceAdjustmentRequest financeBalanceAdjustmentRequest) {

        if (financeBalanceAdjustmentRequest == null || CollectionUtil.isEmpty(financeBalanceAdjustmentRequest.getUniqueIdAndAmounts())) {
            throw new BusinessException("?????????????????????");
        }
        financeBalanceAdjustmentRequest.getUniqueIdAndAmounts().forEach(uniqueIdAndAmount -> {
            if (null == uniqueIdAndAmount.getAmount()) {
                throw new BusinessException("??????????????????");
            }
            if (null == uniqueIdAndAmount.getUniqueId()) {
                throw new BusinessException("??????ID ????????????");
            }
            FinanceBalanceAdjustment financeBalanceAdjustment = new FinanceBalanceAdjustmentRequest();
            BeanUtils.copyProperties(financeBalanceAdjustmentRequest, financeBalanceAdjustment);
            MemBaseinfo baseinfo = memBaseinfoService.getUserByUniqueId(uniqueIdAndAmount.getUniqueId());
            if (ObjectUtils.isEmpty(baseinfo)) {
                throw new BusinessException("???????????????ID[" + uniqueIdAndAmount.getUniqueId() + "] ?????????");
            }
            financeBalanceAdjustment.setAccno(baseinfo.getAccno());
            financeBalanceAdjustment.setAmount(uniqueIdAndAmount.getAmount());
            adjustment(financeBalanceAdjustment);
        });
    }
}
