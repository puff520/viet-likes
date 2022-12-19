package com.likes.common.service.money.impl;

import com.likes.common.mybatis.entity.TraRechargeaudit;
import com.likes.common.mybatis.entity.TraRechargeauditExample;
import com.likes.common.mybatis.mapper.TraRechargeauditMapper;
import com.likes.common.service.money.TraRechargeauditService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TraRechargeauditServiceImpl implements TraRechargeauditService {

    @Resource
    private TraRechargeauditMapper traRechargeauditMapper;

    @Override
    public int countByExample(TraRechargeauditExample example) {
        return traRechargeauditMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TraRechargeauditExample example) {
        return traRechargeauditMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long recauditid) {
        return traRechargeauditMapper.deleteByPrimaryKey(recauditid);
    }

    @Override
    public int insert(TraRechargeaudit record) {
        return traRechargeauditMapper.insert(record);
    }

    @Override
    public int insertSelective(TraRechargeaudit record) {
        return traRechargeauditMapper.insertSelective(record);
    }

    @Override
    public TraRechargeaudit selectOneByExample(TraRechargeauditExample example) {
        return traRechargeauditMapper.selectOneByExample(example);
    }

    @Override
    public List<TraRechargeaudit> selectByExample(TraRechargeauditExample example) {
        return traRechargeauditMapper.selectByExample(example);
    }

    @Override
    public TraRechargeaudit selectByPrimaryKey(Long recauditid) {
        return traRechargeauditMapper.selectByPrimaryKey(recauditid);
    }

    @Override
    public int updateByExampleSelective(TraRechargeaudit record, TraRechargeauditExample example) {
        return traRechargeauditMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(TraRechargeaudit record, TraRechargeauditExample example) {
        return traRechargeauditMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(TraRechargeaudit record) {
        return traRechargeauditMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TraRechargeaudit record) {
        return traRechargeauditMapper.updateByPrimaryKey(record);
    }
}
