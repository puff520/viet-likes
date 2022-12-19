package com.likes.modules.admin.finance.service.impl;

import com.likes.common.constant.ModuleConstant;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.request.FinanceDamaAdjustmentRequest;
import com.likes.common.mybatis.entity.FinanceDamaAdjustment;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.SysInfolog;
import com.likes.common.mybatis.mapper.FinanceDamaAdjustmentMapper;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemBaseinfoWriteService;
import com.likes.common.util.BeanUtils;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.StringUtils;
import com.likes.modules.admin.common.service.CommonService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

@Service
public class DamaAdjustmentService {

    @Resource
    private FinanceDamaAdjustmentMapper financeDamaAdjustmentMapper;
    @Resource
    private MemBaseinfoWriteService memBaseinfoWriteService;
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private CommonService commonService;

    @Transactional
    public void adjustment(FinanceDamaAdjustment financeDamaAdjustment) {
        if (ObjectUtils.isEmpty(financeDamaAdjustment)) {
            throw new BusinessException("参数不能为空");
        }
        if (!GoldchangeEnum.MANUALLY_ADD_DAMALIANG.getValue().equals(financeDamaAdjustment.getType()) &&
                !GoldchangeEnum.MANUALLY_SUB_DAMALIANG.getValue().equals(financeDamaAdjustment.getType())) {
            throw new BusinessException("加减可提类型错误");
        }
        if (financeDamaAdjustment.getDamaliang() == null || financeDamaAdjustment.getDamaliang().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("打码量必须为正整数");
        }
        financeDamaAdjustment.setDamaliang(financeDamaAdjustment.getDamaliang().setScale(3, BigDecimal.ROUND_DOWN));
        if (StringUtils.isBlank(financeDamaAdjustment.getAccno())) {
            throw new BusinessException("账号为空");
        }
        financeDamaAdjustment.setDamaliang(getTradeOffAmount(financeDamaAdjustment.getDamaliang()));
        financeDamaAdjustmentMapper.insertSelective(financeDamaAdjustment);
        updateChangeBalance(financeDamaAdjustment);
    }

    private void updateChangeBalance(FinanceDamaAdjustment financeDamaAdjustment) {
        MemBaseinfo mem = memBaseinfoService.getUserByAccno(financeDamaAdjustment.getAccno());
        MemGoldchangeDO memGoldchangeDO = new MemGoldchangeDO();
        memGoldchangeDO.setRefid(financeDamaAdjustment.getId());
        memGoldchangeDO.setUserId(mem.getMemid().intValue());
        BigDecimal tradeOffAmount = getTradeOffAmount(null);
        memGoldchangeDO.setShowChange(tradeOffAmount);
        memGoldchangeDO.setQuantity(tradeOffAmount);
        memGoldchangeDO.setAmount(tradeOffAmount);
        memGoldchangeDO.setOpnote(financeDamaAdjustment.getRemark());
        memGoldchangeDO.setCreateUser(financeDamaAdjustment.getCreateUser());
        memGoldchangeDO.setUpdateUser(financeDamaAdjustment.getUpdateUser());
        if (GoldchangeEnum.MANUALLY_SUB_DAMALIANG.getValue().equals(financeDamaAdjustment.getType())) {
            memGoldchangeDO.setNoWithdrawalAmount(getTradeOffAmount(financeDamaAdjustment.getDamaliang().multiply(BigDecimal.valueOf(-1))));
        } else {
            memGoldchangeDO.setNoWithdrawalAmount(getTradeOffAmount(financeDamaAdjustment.getDamaliang()));
        }
        memGoldchangeDO.setChangetype(financeDamaAdjustment.getType());
        memBaseinfoWriteService.updateUserBalance(memGoldchangeDO);

        // 会员加减款日志
        String content = null;
        String modelname = null;
        if (GoldchangeEnum.MANUALLY_SUB_DAMALIANG.getValue().equals(financeDamaAdjustment.getType())) {
            content = "会员减打码[" + mem.getUniqueId() + "]打码量[" + financeDamaAdjustment.getDamaliang() + "]";
            modelname = "会员减打码";
        } else if (GoldchangeEnum.MANUALLY_ADD_DAMALIANG.getValue().equals(financeDamaAdjustment.getType())) {
            content = "会员加打码[" + mem.getUniqueId() + "]打码量[" + financeDamaAdjustment.getDamaliang() + "]";
            modelname = "会员加打码";
        }
        SysInfolog sysInfolog = new SysInfolog();
        sysInfolog.setAccno(financeDamaAdjustment.getCreateUser());
        sysInfolog.setOptcontent(content);
        sysInfolog.setSystemname(ModuleConstant.LIVE_MANAGE);
        sysInfolog.setModelname(modelname);
        sysInfolog.setOrginfo("adjustment");
        commonService.insertSelective(sysInfolog);
    }

    @Transactional
    public void batchAdjustment(FinanceDamaAdjustmentRequest financeDamaAdjustmentRequest) {
        if (ObjectUtils.isEmpty(financeDamaAdjustmentRequest) || CollectionUtil.isEmpty(financeDamaAdjustmentRequest.getUniqueIdAndDamaliangs())) {
            throw new BusinessException("选择的会员为空");
        }
        financeDamaAdjustmentRequest.getUniqueIdAndDamaliangs().forEach(uniqueIdAndDamaliang -> {
            if (null == uniqueIdAndDamaliang.getDamaliang()) {
                throw new BusinessException("打码量不能为空");
            }
            if (null == uniqueIdAndDamaliang.getUniqueId()) {
                throw new BusinessException("会员ID 不能为空");
            }
            FinanceDamaAdjustment financeBalanceAdjustment = new FinanceDamaAdjustment();
            BeanUtils.copyProperties(financeDamaAdjustmentRequest, financeBalanceAdjustment);
            MemBaseinfo baseinfo = memBaseinfoService.getUserByUniqueId(uniqueIdAndDamaliang.getUniqueId());
            if (ObjectUtils.isEmpty(baseinfo)) {
                throw new BusinessException("没找到会员ID[" + uniqueIdAndDamaliang.getUniqueId() + "] 的会员");
            }
            financeBalanceAdjustment.setAccno(baseinfo.getAccno());
            financeBalanceAdjustment.setDamaliang(uniqueIdAndDamaliang.getDamaliang());
            adjustment(financeBalanceAdjustment);
        });
    }
}
