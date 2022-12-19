package com.likes.common.service.money.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.model.dto.member.MemGoldchangeDTO;
import com.likes.common.model.dto.report.DepositStatisticsDO;
import com.likes.common.model.request.FamilyIncarnateRequest;
import com.likes.common.model.request.FamilyStatisticsRequest;
import com.likes.common.model.request.UserReq;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.model.response.FamilyIncomeAndExpensesResponse;
import com.likes.common.model.response.FamilyMemGoldchangeResponse;
import com.likes.common.model.response.FamilyMemIncarnateResponse;
import com.likes.common.model.response.UserResp;
import com.likes.common.mybatis.entity.MemGoldchange;
import com.likes.common.mybatis.entity.MemGoldchangeExample;
import com.likes.common.mybatis.mapper.MemGoldchangeMapper;
import com.likes.common.mybatis.mapperext.member.MemGoldchangeMapperExt;
import com.likes.common.service.money.MemGoldchangeService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lucien
 * @create 2020/6/20 14:53
 */
@Service
public class MemGoldchangeServiceImpl implements MemGoldchangeService {

    @Autowired
    private MemGoldchangeMapper memGoldchangeMapper;
    @Autowired
    private MemGoldchangeMapperExt memGoldchangeMapperExt;

    @Override
    public Integer getcountbyrmtype(Map<String, Object> param) {
        return memGoldchangeMapperExt.getcountbyrmtype(param);
    }

    @Override
    public int insertSelectiveMemGoldchange(MemGoldchange memGoldchange) {
        return memGoldchangeMapperExt.insertSelectiveMemGoldchange(memGoldchange);
    }

    @Override
    public int updateZhuboTixian(MemGoldchange paramMemGoldchange) {
        return memGoldchangeMapperExt.updateZhuboTixian(paramMemGoldchange);
    }

    @Override
    public int insertSelective(MemGoldchange goldchange) {
        return memGoldchangeMapper.insertSelective(goldchange);
    }

    @Override
    public Integer countByExample(MemGoldchangeExample example) {
        return memGoldchangeMapper.countByExample(example);
    }

    @Override
    public List<MemGoldchange> selectByExample(MemGoldchangeExample example) {
        return memGoldchangeMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKeySelective(MemGoldchange memGoldchange) {
        return memGoldchangeMapper.updateByPrimaryKeySelective(memGoldchange);
    }

    @Override
    public MemGoldchange selectOneByExample(Long orderid, String accno) {
        MemGoldchangeExample example = new MemGoldchangeExample();
        MemGoldchangeExample.Criteria criteria = example.createCriteria();
        criteria.andRefidEqualTo(orderid);
        criteria.andAccnoEqualTo(accno);
        MemGoldchange memGoldchange = memGoldchangeMapper.selectOneByExample(example);
        return null == memGoldchange ? new MemGoldchange() : memGoldchange;
    }

    @Override
    public List<FamilyMemGoldchangeResponse> isFamilyTiXian(FamilyIncarnateRequest param) {
        return memGoldchangeMapperExt.isFamilyTiXian(param);
    }

    @Override
    public List<FamilyIncomeAndExpensesResponse> familyIncomeAndExpensesList(FamilyIncarnateRequest req) {
        return memGoldchangeMapperExt.familyIncomeAndExpensesList(req);
    }

    @Override
    public MemGoldchange findFamilyIsIncarnate(MemGoldchange memGoldchangeParam) {
        return memGoldchangeMapperExt.findFamilyIsIncarnate(memGoldchangeParam);
    }

    @Override
    public int doFamilyIncarnateMemGoldchange(Map<String, Object> param) {
        return memGoldchangeMapperExt.doFamilyIncarnateMemGoldchange(param);
    }

    @Override
    public List<FamilyMemIncarnateResponse> findFamilyTiXian(FamilyIncarnateRequest req) {
        return memGoldchangeMapperExt.findFamilyTiXian(req);
    }

    @Override
    public Double getZongchongzhi(String accno) {
        return memGoldchangeMapperExt.getZongchongzhi(accno);
    }

    @Override
    public Double getSumQuantity(FamilyStatisticsRequest f) {
        return memGoldchangeMapperExt.getSumQuantity(f);
    }

    @Override
    public int insertAuto(MemGoldchange record) {
        return memGoldchangeMapperExt.insertAuto(record);
    }

    @Override
    public List<UserResp> getTopTenStatistics(UserReq userReq) {
        return memGoldchangeMapperExt.getTopTenStatistics(userReq);
    }

    @Override
    public List<UserResp> getRoomBigMoneyTop(UserReq userReq) {
        return memGoldchangeMapperExt.getRoomBigMoneyTop(userReq);
    }

    @Override
    public double getLiveincome(UserReq dashangReq) {
        return memGoldchangeMapperExt.getLiveincome(dashangReq);
    }

    @Override
    public int insertSelectiveSubtractMemGoldchange(MemGoldchange memGoldchange) {
        return memGoldchangeMapperExt.insertSelectiveSubtractMemGoldchange(memGoldchange);
    }

    @Override
    public Double getAllQuantity(Integer changetype, String accno) {
        return memGoldchangeMapperExt.getAllQuantity(changetype, accno);
    }

    @Override
    public Double getNengtiGoldNum(String accno) {
        return memGoldchangeMapperExt.getNengtiGoldNum(accno);
    }

    @Override
    public List<MemGoldchange> getAllGoldchangeByType(UsersRequest xiaofeiparam) {
        return memGoldchangeMapperExt.getAllGoldchangeByType(xiaofeiparam);
    }

    @Override
    @DS("slave")
    public Page<MemGoldchangeDTO> myIncomeAndExpensesList(UsersRequest req, RowBounds toRowBounds) {
        return memGoldchangeMapperExt.myIncomeAndExpensesList(req, toRowBounds);
    }

    @Override
    public int insertSelectiveFamilyGoldchange(MemGoldchange memGoldchange) {
        return memGoldchangeMapperExt.insertSelectiveFamilyGoldchange(memGoldchange);
    }

    @Override
    public Double getAllQuantityByType(UsersRequest param) {
        return memGoldchangeMapperExt.getAllQuantityByType(param);
    }

    @Override
    public BigDecimal tatolGoldchange(String refaccno, String accno, List<Integer> typeList, String startTime, String endTime) {
        return memGoldchangeMapperExt.tatolGoldchange(refaccno, accno, typeList, startTime, endTime);
    }

    @Override
    public List<Map<String, Object>> statisticsAllType(String startTime, String endTime) {
        return memGoldchangeMapperExt.statisticsAllType(startTime, endTime);
    }

    @Override
    public List<Map<String, Object>> statisticsIncomeRecharge(String startTime, String endTime) {
        return memGoldchangeMapperExt.statisticsIncomeRecharge(startTime, endTime);
    }

    @Override
    public List<Map<String, Object>> getTotalGoldChangeByAccno(List<Map<String, Object>> accnoTimeList) {
        List<Map<String, Object>> tgcList = new ArrayList<Map<String, Object>>();
        List<Integer> typeList = new ArrayList<Integer>();
        typeList.add(2);
        for (Map<String, Object> at : accnoTimeList) {
            Map<String, Object> tgc = new HashMap<String, Object>();
            BigDecimal mgc = memGoldchangeMapperExt.tatolGoldchange((String) at.get("accno"), null, typeList, (String) at.get("startTime"), (String) at.get("endTime"));
            tgc.put("index", at.get("index"));
            tgc.put("quantity", null == mgc ? new Double(0.00D) : mgc.doubleValue());
            tgcList.add(tgc);
        }
        return tgcList;
    }

    @Override
    public List<Map<String, Object>> getTotalGoldsByAccnos(List<String> accnoList) {
        return memGoldchangeMapperExt.getTotalGoldsByAccnos(accnoList);
    }

    @Override
    public List<Map<String, Object>> getTotalYesterdayGoldsByAccnos(List<String> accnoList, String startTime, String endTime) {
        return memGoldchangeMapperExt.getTotalYesterdayGoldsByAccnos(accnoList, startTime, endTime);
    }

    @Override
    public DepositStatisticsDO selectSumPeople(String startTime, String endTime, Integer value) {
        return memGoldchangeMapperExt.selectSumPeople(startTime, endTime, value);
    }
}
