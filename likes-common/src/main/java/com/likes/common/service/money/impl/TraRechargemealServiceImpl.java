package com.likes.common.service.money.impl;

import com.likes.common.model.request.TraRechargemealReq;
import com.likes.common.model.response.TraRechargemealResponse;
import com.likes.common.mybatis.entity.TraRechargemeal;
import com.likes.common.mybatis.entity.TraRechargemealExample;
import com.likes.common.mybatis.mapper.TraRechargemealMapper;
import com.likes.common.mybatis.mapperext.tra.TraRechargemealMapperExt;
import com.likes.common.service.money.TraRechargemealService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TraRechargemealServiceImpl implements TraRechargemealService {

    @Resource
    private TraRechargemealMapper traRechargemealMapper;
    @Resource
    private TraRechargemealMapperExt traRechargemealMapperExt;

    @Override
    public int countByExample(TraRechargemealExample example) {
        return traRechargemealMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TraRechargemealExample example) {
        return traRechargemealMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long mealid) {
        return traRechargemealMapper.deleteByPrimaryKey(mealid);
    }

    @Override
    public int insert(TraRechargemeal record) {
        return traRechargemealMapper.insert(record);
    }

    @Override
    public int insertSelective(TraRechargemeal record) {
        return traRechargemealMapper.insertSelective(record);
    }

    @Override
    public TraRechargemeal selectOneByExample(TraRechargemealExample example) {
        return traRechargemealMapper.selectOneByExample(example);
    }

    @Override
    public List<TraRechargemeal> selectByExample(TraRechargemealExample example) {
        return traRechargemealMapper.selectByExample(example);
    }

    @Override
    public TraRechargemeal selectByPrimaryKey(Long mealid) {
        return traRechargemealMapper.selectByPrimaryKey(mealid);
    }

    @Override
    public int updateByExampleSelective(TraRechargemeal record, TraRechargemealExample example) {
        return traRechargemealMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(TraRechargemeal record, TraRechargemealExample example) {
        return traRechargemealMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(TraRechargemeal record) {
        return traRechargemealMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TraRechargemeal record) {
        return traRechargemealMapper.updateByPrimaryKey(record);
    }

    @Override
    public Page<TraRechargemealResponse> traRechargemealList(TraRechargemealReq req, RowBounds rowBounds) {
        return traRechargemealMapperExt.traRechargemealList(req,rowBounds);
    }

    @Override
    public int findAllTotal() {
        return traRechargemealMapperExt.findAllTotal();
    }

    @Override
    public List<TraRechargemealResponse> getList(Integer num) {
        return traRechargemealMapperExt.getList(num);
    }
}
