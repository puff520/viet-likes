package com.likes.common.service.money.impl;

import com.likes.common.mybatis.entity.TraOrdertracking;
import com.likes.common.mybatis.entity.TraOrdertrackingExample;
import com.likes.common.mybatis.mapper.TraOrdertrackingMapper;
import com.likes.common.mybatis.mapperext.tra.TraOrdertrackingMapperExt;
import com.likes.common.service.money.TraOrdertrackingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TraOrdertrackingServiceImpl implements TraOrdertrackingService {

    @Resource
    private TraOrdertrackingMapper traOrdertrackingMapper;
    @Resource
    private TraOrdertrackingMapperExt traOrdertrackingMapperExt;

    @Override
    public int countByExample(TraOrdertrackingExample example) {
        return traOrdertrackingMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TraOrdertrackingExample example) {
        return traOrdertrackingMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long trackid) {
        return traOrdertrackingMapper.deleteByPrimaryKey(trackid);
    }

    @Override
    public int insert(TraOrdertracking record) {
        return traOrdertrackingMapper.insert(record);
    }

    @Override
    public int insertSelective(TraOrdertracking record) {
        return traOrdertrackingMapper.insertSelective(record);
    }

    @Override
    public TraOrdertracking selectOneByExample(TraOrdertrackingExample example) {
        return traOrdertrackingMapper.selectOneByExample(example);
    }

    @Override
    public List<TraOrdertracking> selectByExample(TraOrdertrackingExample example) {
        return traOrdertrackingMapper.selectByExample(example);
    }

    @Override
    public TraOrdertracking selectByPrimaryKey(Long trackid) {
        return traOrdertrackingMapper.selectByPrimaryKey(trackid);
    }

    @Override
    public int updateByExampleSelective(TraOrdertracking record, TraOrdertrackingExample example) {
        return traOrdertrackingMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(TraOrdertracking record, TraOrdertrackingExample example) {
        return traOrdertrackingMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(TraOrdertracking record) {
        return traOrdertrackingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TraOrdertracking record) {
        return traOrdertrackingMapper.updateByPrimaryKey(record);
    }

    @Override
    public int insertTraOrdertracking(TraOrdertracking traOrdertracking) {
        return traOrdertrackingMapperExt.insertTraOrdertracking(traOrdertracking);
    }
}
