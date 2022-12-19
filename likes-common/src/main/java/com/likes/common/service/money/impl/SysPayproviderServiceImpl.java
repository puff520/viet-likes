package com.likes.common.service.money.impl;

import com.likes.common.constant.RedisKeys;
import com.likes.common.model.dto.MandatoryDO;
import com.likes.common.model.response.ThirdProviderNameResponse;
import com.likes.common.mybatis.entity.SysPayprovider;
import com.likes.common.mybatis.entity.SysPayproviderExample;
import com.likes.common.mybatis.mapper.SysPayproviderMapper;
import com.likes.common.mybatis.mapperext.sys.SysPayproviderMapperExt;
import com.likes.common.service.money.SysPayproviderService;
import com.likes.common.util.StringUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysPayproviderServiceImpl implements SysPayproviderService {

    @Resource
    private SysPayproviderMapper sysPayproviderMapper;

    @Resource
    private SysPayproviderMapperExt sysPayproviderMapperExt;

    @Override
    public int countByExample(SysPayproviderExample example) {
        return sysPayproviderMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SysPayproviderExample example) {
        return sysPayproviderMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long providerid) {
        return sysPayproviderMapper.deleteByPrimaryKey(providerid);
    }

    @Override
    public int insert(SysPayprovider record) {
        return sysPayproviderMapper.insert(record);
    }

    @Override
    public int insertSelective(SysPayprovider record) {
        return sysPayproviderMapper.insertSelective(record);
    }

    @Override
    public SysPayprovider selectOneByExample(SysPayproviderExample example) {
        return sysPayproviderMapper.selectOneByExample(example);
    }

    @Override
    public List<SysPayprovider> selectByExample(SysPayproviderExample example) {
        return sysPayproviderMapper.selectByExample(example);
    }

    @Override
    public SysPayprovider selectByPrimaryKey(Long providerid) {
        return sysPayproviderMapper.selectByPrimaryKey(providerid);
    }

    @Override
    public int updateByExampleSelective(SysPayprovider record, SysPayproviderExample example) {
        return sysPayproviderMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(SysPayprovider record, SysPayproviderExample example) {
        return sysPayproviderMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(SysPayprovider record) {
        return sysPayproviderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysPayprovider record) {
        return sysPayproviderMapper.updateByPrimaryKey(record);
    }

    @Override
    public Page<SysPayprovider> getList(SysPayprovider sysPayprovider, RowBounds rowBounds) {
        return sysPayproviderMapperExt.getList(sysPayprovider, rowBounds);
    }

    @Override
    public SysPayprovider getRepeate(SysPayprovider sysPayprovider) {
        return sysPayproviderMapperExt.getRepeate(sysPayprovider);
    }

    @Override
    public MandatoryDO findByOrderno(String orderno) {
        return sysPayproviderMapperExt.findByOrderno(orderno);
    }

    @Override
    public List<SysPayprovider> getAllsysPayproviderlist(SysPayprovider sysPayprovider) {
        return sysPayproviderMapperExt.getAllsysPayproviderlist(sysPayprovider);
    }

    @Override
    public String selectByTpaySetId(Long tpaysetid) {
        String payprovider = RedisBusinessUtil.get(RedisKeys.LIVE_SYS_PAYPROVIDER_BY_TPAYSETID + tpaysetid);
        if (StringUtils.isEmpty(payprovider)) {
            payprovider = sysPayproviderMapperExt.selectByTpaySetId(tpaysetid);
            RedisBusinessUtil.set(RedisKeys.LIVE_SYS_PAYPROVIDER_BY_TPAYSETID + tpaysetid, payprovider);
        }
        return payprovider;
    }

    @Override
    public List<ThirdProviderNameResponse> getIdAndName() {
        return sysPayproviderMapperExt.getIdAndName();
    }

    @Override
    public Long getProviderId(String provider) {
        return sysPayproviderMapperExt.getProviderId( provider);
    }
}
