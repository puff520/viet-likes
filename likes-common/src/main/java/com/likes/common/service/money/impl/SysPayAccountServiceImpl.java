package com.likes.common.service.money.impl;

import com.likes.common.model.dto.sys.SysPayaccountDO;
import com.likes.common.mybatis.entity.SysPayaccount;
import com.likes.common.mybatis.entity.SysPayaccountExample;
import com.likes.common.mybatis.mapper.SysPayaccountMapper;
import com.likes.common.mybatis.mapperext.sys.SysPayaccountMapperExt;
import com.likes.common.service.money.SysPayAccountService;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class SysPayAccountServiceImpl implements SysPayAccountService {

    @Resource
    private SysPayaccountMapper sysPayaccountMapper;
    @Resource
    private SysPayaccountMapperExt sysPayaccountMapperExt;

    @Override
    public int countByExample(SysPayaccountExample example) {
        return sysPayaccountMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(SysPayaccountExample example) {
        int result = sysPayaccountMapper.deleteByExample(example);
        RedisBusinessUtil.delBanksCacheByLevel();
        return result;
    }

    @Override
    public int deleteByPrimaryKey(Long bankid) {
        int result = sysPayaccountMapper.deleteByPrimaryKey(bankid);
        RedisBusinessUtil.delBanksCacheByLevel();
        return result;
    }

    @Override
    public int insert(SysPayaccount record) {
        int result = sysPayaccountMapper.insert(record);
        RedisBusinessUtil.delBanksCacheByLevel();
        return result;
    }

    @Override
    public int insertSelective(SysPayaccount record) {
        int result = sysPayaccountMapper.insertSelective(record);
        RedisBusinessUtil.delBanksCacheByLevel();
        return result;
    }

    @Override
    public SysPayaccount selectOneByExample(SysPayaccountExample example) {
        return sysPayaccountMapper.selectOneByExample(example);
    }

    @Override
    public List<SysPayaccount> selectByExample(SysPayaccountExample example) {
        return sysPayaccountMapper.selectByExample(example);
    }

    @Override
    public SysPayaccount selectByPrimaryKey(Long bankid) {
        return sysPayaccountMapper.selectByPrimaryKey(bankid);
    }

    @Override
    public int updateByExampleSelective(SysPayaccount record, SysPayaccountExample example) {
        int result = sysPayaccountMapper.updateByExampleSelective(record,example);
        RedisBusinessUtil.delBanksCacheByLevel();
        return result;
    }

    @Override
    public int updateByExample(SysPayaccount record, SysPayaccountExample example) {
        int result = sysPayaccountMapper.updateByExample(record,example);
        RedisBusinessUtil.delBanksCacheByLevel();
        return result;
    }

    @Override
    public int updateByPrimaryKeySelective(SysPayaccount record) {
        int result = sysPayaccountMapper.updateByPrimaryKeySelective(record);
        RedisBusinessUtil.delBanksCacheByLevel();
        return result;
    }

    @Override
    public int updateByPrimaryKey(SysPayaccount record) {
        int result = sysPayaccountMapper.updateByPrimaryKey(record);
        RedisBusinessUtil.delBanksCacheByLevel();
        return result;
    }

    @Override
    public List<SysPayaccountDO> getPayInfo(Integer accounttype, Integer memlevel) {
        return sysPayaccountMapperExt.getPayInfo(accounttype,memlevel);
    }

    @Override
    public Page<SysPayaccountDO> getList(SysPayaccount sysPayaccount, RowBounds rowBounds) {
        return sysPayaccountMapperExt.getList(sysPayaccount,rowBounds);
    }

    @Override
    public SysPayaccount getRepeat(SysPayaccount memBankaccount) {
        return sysPayaccountMapperExt.getRepeat(memBankaccount);
    }

    @Override
    public void updateTotalAmount(String accno, Long bankid, BigDecimal amount) {
        sysPayaccountMapperExt.updateTotalAmount(accno,bankid,amount);
        RedisBusinessUtil.delBanksCacheByLevel();
    }

    @Override
    public void clearTotalAmount(String accno, Long bankid) {
        sysPayaccountMapperExt.clearTotalAmount(accno,bankid);
        RedisBusinessUtil.delBanksCacheByLevel();
    }
}
