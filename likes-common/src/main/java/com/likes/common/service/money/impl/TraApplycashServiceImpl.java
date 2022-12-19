package com.likes.common.service.money.impl;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.constant.RedisKeys;
import com.likes.common.model.dto.TraApplycashDO;
import com.likes.common.model.request.FamilyIncarnateRequest;
import com.likes.common.model.response.IncarnateRecordResponse;
import com.likes.common.mybatis.entity.TraApplycash;
import com.likes.common.mybatis.entity.TraApplycashExample;
import com.likes.common.mybatis.mapper.TraApplycashMapper;
import com.likes.common.mybatis.mapperext.tra.TraApplycashMapperExt;
import com.likes.common.service.money.TraApplycashService;
import com.likes.common.util.CollectionUtil;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class TraApplycashServiceImpl implements TraApplycashService {

    @Resource
    private TraApplycashMapper traApplycashMapper;
    @Resource
    private TraApplycashMapperExt traApplycashMapperExt;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int countByExample(TraApplycashExample example) {
        return traApplycashMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TraApplycashExample example) {
        return traApplycashMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long apycid) {
        return traApplycashMapper.deleteByPrimaryKey(apycid);
    }

    @Override
    public int insert(TraApplycash record) {
        return traApplycashMapper.insert(record);
    }

    @Override
    public int insertSelective(TraApplycash record) {
        return traApplycashMapper.insertSelective(record);
    }

    @Override
    public TraApplycash selectOneByExample(TraApplycashExample example) {
        return traApplycashMapper.selectOneByExample(example);
    }

    @Override
    public List<TraApplycash> selectByExample(TraApplycashExample example) {
        return traApplycashMapper.selectByExample(example);
    }

    @Override
    public TraApplycash selectByPrimaryKey(Long apycid) {
        return traApplycashMapper.selectByPrimaryKey(apycid);
    }

    @Override
    public int updateByExampleSelective(TraApplycash record, TraApplycashExample example) {
        return traApplycashMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(TraApplycash record, TraApplycashExample example) {
        return traApplycashMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(TraApplycash record) {
        return traApplycashMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TraApplycash record) {
        return traApplycashMapper.updateByPrimaryKey(record);
    }

    @Override
    public TraApplycash findByOrderid(Long orderid) {
        return traApplycashMapperExt.findByOrderid(orderid);
    }

    @Override
    public int doUpdateIncarnateHandleOrder(TraApplycash traApplycash) {
        return traApplycashMapperExt.doUpdateIncarnateHandleOrder(traApplycash);
    }

    @Override
    public int doUpdateIncarnateCancelSureOrder(TraApplycash traApplycash) {
        return traApplycashMapperExt.doUpdateIncarnateCancelSureOrder(traApplycash);
    }

    @Override
    public int updateIncarnateConfirmApplycash(TraApplycash traApplycash) {
        return traApplycashMapperExt.updateIncarnateConfirmApplycash(traApplycash);
    }
    @Override
    public int udunUpdateIncarnateConfirmApplycash(TraApplycash traApplycash) {
        return traApplycashMapperExt.udunUpdateIncarnateConfirmApplycash(traApplycash);
    }

    @Override
    public Page<TraApplycashDO> getTraApplycashList(FamilyIncarnateRequest req, RowBounds rowBounds) {
        String key = RedisKeys.LIVE_FAMILY_WITHDRAWAL_MANAGEMENT + JSONObject.toJSONString(req) + JSONObject.toJSONString(rowBounds);
        if (redisTemplate.hasKey(key)) {
            return (Page<TraApplycashDO>) redisTemplate.opsForValue().get(key);
        } else {
            Page<TraApplycashDO> traApplycashList = traApplycashMapperExt.getTraApplycashList(req, rowBounds);
            if (CollectionUtil.isNotEmpty(traApplycashList)) {
                redisTemplate.opsForValue().set(key + "count", traApplycashList.getTotal());
                redisTemplate.opsForValue().set(key, traApplycashList);
                return traApplycashList;
            }
        }
        return null;
    }

    @Override
    public TraApplycash getLastTraApplycash(String accno) {
        return traApplycashMapperExt.getLastTraApplycash(accno);
    }

    @Override
    public int doInsertIncarnate(TraApplycash traApplycash) {
        return traApplycashMapperExt.doInsertIncarnate(traApplycash);
    }

    @Override
    public Page<IncarnateRecordResponse> getIncarnateRecordList(String accno, RowBounds rowBounds) {
        return traApplycashMapperExt.getIncarnateRecordList(accno, rowBounds);
    }

    @Override
    public Double getIncarnateRecordListTotal(String accno) {
        return traApplycashMapperExt.getIncarnateRecordListTotal(accno);
    }

    @Override
    public TraApplycash findCashByCash(TraApplycash param) {
        return traApplycashMapperExt.findCashByCash(param);
    }

    @Override
    public int updateTraApplycash(TraApplycash traApplycash) {
        return traApplycashMapperExt.updateTraApplycash(traApplycash);
    }

    @Override
    public int doInsert(TraApplycash traApplycash) {
        return traApplycashMapperExt.doInsert(traApplycash);
    }

    @Override
    public TraApplycash findNotInCashByCash(Map<String, Object> timeMap) {
        return traApplycashMapperExt.findNotInCashByCash(timeMap);
    }

    @Override
    public TraApplycash findNotInCashByCashByAccno(String accno) {
        return traApplycashMapperExt.findNotInCashByCashByAccno(accno);
    }

    @Override
    public Double getXianshiIncarnateRecordListTotal(String accno) {
        return traApplycashMapperExt.getXianshiIncarnateRecordListTotal(accno);
    }

    @Override
    public BigDecimal getOrderBetRecord(Long familyid) {
        return traApplycashMapperExt.getOrderBetRecord(familyid);
    }

    @Override
    public BigDecimal getOrderBetRecords(String startDate, String endDate, Long roomId) {
        return traApplycashMapperExt.getOrderBetRecords(startDate, endDate, roomId);
    }

    @Override
    public Page<TraApplycashDO> getTraApplycashMemFamilyList(String accno, RowBounds toRowBounds) {
        return traApplycashMapperExt.getTraApplycashMemFamilyList(accno, toRowBounds);
    }
}
