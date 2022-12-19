package com.likes.common.service.money.impl;

import com.likes.common.model.dto.TraApplyauditDO;
import com.likes.common.mybatis.entity.TraApplyaudit;
import com.likes.common.mybatis.entity.TraApplyauditExample;
import com.likes.common.mybatis.mapper.TraApplyauditMapper;
import com.likes.common.mybatis.mapperext.tra.TraApplyauditMapperExt;
import com.likes.common.service.money.TraApplyauditService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TraApplyauditServiceImpl implements TraApplyauditService {

    @Resource
    private TraApplyauditMapper traApplyauditMapper;
    @Resource
    private TraApplyauditMapperExt traApplyauditMapperExt;

    @Override
    public int countByExample(TraApplyauditExample example) {
        return traApplyauditMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TraApplyauditExample example) {
        return traApplyauditMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long apauditid) {
        return traApplyauditMapper.deleteByPrimaryKey(apauditid);
    }

    @Override
    public int insert(TraApplyaudit record) {
        return traApplyauditMapper.insert(record);
    }

    @Override
    public int insertSelective(TraApplyaudit record) {
        return traApplyauditMapper.insertSelective(record);
    }

    @Override
    public TraApplyaudit selectOneByExample(TraApplyauditExample example) {
        return traApplyauditMapper.selectOneByExample(example);
    }

    @Override
    public List<TraApplyaudit> selectByExample(TraApplyauditExample example) {
        return traApplyauditMapper.selectByExample(example);
    }

    @Override
    public TraApplyaudit selectByPrimaryKey(Long apauditid) {
        return traApplyauditMapper.selectByPrimaryKey(apauditid);
    }

    @Override
    public int updateByExampleSelective(TraApplyaudit record, TraApplyauditExample example) {
        return traApplyauditMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(TraApplyaudit record, TraApplyauditExample example) {
        return traApplyauditMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(TraApplyaudit record) {
        return traApplyauditMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TraApplyaudit record) {
        return traApplyauditMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TraApplyauditDO> getByApycid(Long apycid) {
        return traApplyauditMapperExt.getByApycid(apycid);
    }

    @Override
    public List<TraApplyaudit> getListById(Long apyid) {
        return traApplyauditMapperExt.getListById(apyid);
    }

    @Override
    public void doDelJihe(Long apycid) {
        traApplyauditMapperExt.doDelJihe(apycid);
    }
}
