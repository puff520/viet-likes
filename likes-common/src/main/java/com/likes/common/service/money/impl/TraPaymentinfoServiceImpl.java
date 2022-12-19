package com.likes.common.service.money.impl;

import com.likes.common.mybatis.entity.TraPaymentinfo;
import com.likes.common.mybatis.entity.TraPaymentinfoExample;
import com.likes.common.mybatis.mapper.TraPaymentinfoMapper;
import com.likes.common.mybatis.mapperext.tra.TraPaymentinfoMapperExt;
import com.likes.common.service.money.TraPaymentinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TraPaymentinfoServiceImpl implements TraPaymentinfoService {

    @Resource
    private TraPaymentinfoMapper traPaymentinfoMapper;
    @Resource
    private TraPaymentinfoMapperExt traPaymentinfoMapperExt;

    @Override
    public int countByExample(TraPaymentinfoExample example) {
        return traPaymentinfoMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TraPaymentinfoExample example) {
        return traPaymentinfoMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long payid) {
        return traPaymentinfoMapper.deleteByPrimaryKey(payid);
    }

    @Override
    public int insert(TraPaymentinfo record) {
        return traPaymentinfoMapper.insert(record);
    }

    @Override
    public int insertSelective(TraPaymentinfo record) {
        return traPaymentinfoMapper.insertSelective(record);
    }

    @Override
    public TraPaymentinfo selectOneByExample(TraPaymentinfoExample example) {
        return traPaymentinfoMapper.selectOneByExample(example);
    }

    @Override
    public List<TraPaymentinfo> selectByExample(TraPaymentinfoExample example) {
        return traPaymentinfoMapper.selectByExample(example);
    }

    @Override
    public TraPaymentinfo selectByPrimaryKey(Long payid) {
        return traPaymentinfoMapper.selectByPrimaryKey(payid);
    }

    @Override
    public int updateByExampleSelective(TraPaymentinfo record, TraPaymentinfoExample example) {
        return traPaymentinfoMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(TraPaymentinfo record, TraPaymentinfoExample example) {
        return traPaymentinfoMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(TraPaymentinfo record) {
        return traPaymentinfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TraPaymentinfo record) {
        return traPaymentinfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public TraPaymentinfo findByOrderno(String orderid) {
        return traPaymentinfoMapperExt.findByOrderno(orderid);
    }
}
