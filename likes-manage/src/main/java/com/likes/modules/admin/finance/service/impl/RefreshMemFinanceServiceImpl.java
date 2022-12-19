package com.likes.modules.admin.finance.service.impl;

import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.mybatis.entity.AeBetOrder;
import com.likes.common.mybatis.entity.AeBetOrderExample;
import com.likes.common.mybatis.entity.AeGameExample;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemBaseinfoExample;
import com.likes.common.mybatis.entity.MemGoldchange;
import com.likes.common.mybatis.entity.MemGoldchangeExample;
import com.likes.common.mybatis.mapper.AeBetOrderMapper;
import com.likes.common.mybatis.mapper.AeGameMapper;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.money.MemGoldchangeService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.StringUtils;
import com.likes.modules.admin.finance.service.RefreshMemFinanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class RefreshMemFinanceServiceImpl implements RefreshMemFinanceService {
    @Resource
    private MemBaseinfoService memBaseinfoService;
    @Resource
    private MemGoldchangeService memGoldchangeService;
    @Resource
    private AeBetOrderMapper aeBetOrderMapper;

    @Override
    public void refreshConsume(String accno) {
        refreshConsume(getMems(accno));
    }

    private List<MemBaseinfo> getMems(String accno) {
        List<MemBaseinfo> mems = new LinkedList<>();
        if (StringUtils.isNotBlank(accno)) {
            MemBaseinfo m = memBaseinfoService.getUserByAccno(accno);
            if (m == null) {
                return mems;
            }
            mems.add(m);
        }
        MemBaseinfoExample example = new MemBaseinfoExample();
        MemBaseinfoExample.Criteria criteria = example.createCriteria();
        criteria.andPayAmountGreaterThan(BigDecimal.ZERO);
        return memBaseinfoService.selectByExample(example);
    }

    private void refreshConsume(List<MemBaseinfo> mems) {
        if (CollectionUtil.isEmpty(mems)) {
            return;
        }
        mems.forEach(memBaseinfo -> {
            memBaseinfo.setConsumeAmount(BigDecimal.ZERO);
            refreshConsumeByGoldChange(memBaseinfo);
            refreshConsumeByAeGame(memBaseinfo);
        });
    }

    private void refreshConsumeByGoldChange(MemBaseinfo mems) {
        MemGoldchangeExample example = new MemGoldchangeExample();
        MemGoldchangeExample.Criteria criteria = example.createCriteria();
        criteria.andChangetypeIn(Arrays.asList(
                GoldchangeEnum.REWARD.getValue(),
                GoldchangeEnum.DELIVER.getValue(),
                GoldchangeEnum.LOTTERY_BETTING.getValue(),
                GoldchangeEnum.GOD_PLAN_REWARD.getValue()
        )).andAccnoEqualTo(mems.getAccno());
        List<MemGoldchange> memGoldchanges = memGoldchangeService.selectByExample(example);
        if (CollectionUtil.isEmpty(memGoldchanges)) {
            return;
        }
        memGoldchanges.forEach(memGoldchange -> {
            mems.setConsumeAmount(
                    mems.getConsumeAmount().add(memGoldchange.getQuantity().abs())
            );
        });
    }

    private void refreshConsumeByAeGame(MemBaseinfo mems) {
        AeBetOrderExample example = new AeBetOrderExample();
        AeBetOrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(mems.getMemid().intValue());
        List<AeBetOrder> list = aeBetOrderMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        list.forEach(aeBetOrder -> {
            mems.setConsumeAmount(
                    mems.getConsumeAmount().add(aeBetOrder.getBet().abs())
            );
        });
    }


}
