package com.likes.common.service.sys.impl;

import com.likes.common.constant.RedisKeys;
import com.likes.common.mybatis.entity.SysRolefunc;
import com.likes.common.mybatis.entity.SysRolefuncExample;
import com.likes.common.mybatis.mapper.SysRolefuncMapper;
import com.likes.common.mybatis.mapperext.sys.SysRolefuncMapperExt;
import com.likes.common.service.sys.SysRolefuncService;
import com.likes.common.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.likes.common.util.redis.RedisBusinessUtil.deleteFuzzyMatchCache;

/**
 * @author lucien
 * @create 2020/6/19 17:01
 */
@Service
public class SysRolefuncServiceImpl implements SysRolefuncService {

    @Autowired
    private SysRolefuncMapper sysRolefuncMapper;
    @Autowired
    private SysRolefuncMapperExt sysRolefuncMapperExt;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<SysRolefunc> getSysRolefuncList(Long sysroleid, String accno) {
        String key = RedisKeys.LIVE_SYSROLEFUNC + sysroleid.toString() + accno;
        if (redisTemplate.hasKey(key)) {
            return (List<SysRolefunc>) redisTemplate.opsForValue().get(key);
        } else {
            SysRolefuncExample sysRolefuncExample = new SysRolefuncExample();
            SysRolefuncExample.Criteria criteria = sysRolefuncExample.createCriteria();
            criteria.andSysroleidEqualTo(sysroleid);
            criteria.andUpdateUserEqualTo(accno);
            List<SysRolefunc> sysRolefuncs = sysRolefuncMapper.selectByExample(sysRolefuncExample);
            if (CollectionUtil.isNotEmpty(sysRolefuncs)) {
                redisTemplate.opsForValue().set(key, sysRolefuncs);
                return sysRolefuncs;
            }
        }
        return new ArrayList<SysRolefunc>();
    }

    @Override
    public List<Long> getRoleSfunidList(Long sysroleid) {
        String key = RedisKeys.LIVE_SYSROLEFUNC + sysroleid.toString();
        if (redisTemplate.hasKey(key)) {
            return (List<Long>) redisTemplate.opsForValue().get(key);
        } else {
            List<Long> roleSfunidList = sysRolefuncMapperExt.getRoleSfunidList(sysroleid);
            if (CollectionUtil.isNotEmpty(roleSfunidList)) {
                redisTemplate.opsForValue().set(key, roleSfunidList);
                return roleSfunidList;
            }
        }
        return null;
    }

    @Override
    public void delRoleFunctionorg(SysRolefunc sysRolefunc) {
        sysRolefuncMapperExt.delRoleFunctionorg(sysRolefunc);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSROLEFUNC);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSFUNCTIONORG);
    }

    @Override
    public int insertSysRolefuncOne(SysRolefunc s) {
        int i = sysRolefuncMapperExt.insertSysRolefuncOne(s);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSROLEFUNC);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSFUNCTIONORG);
        return i;
    }

    @Override
    public Integer insertList(List<SysRolefunc> list) {
        int i = sysRolefuncMapperExt.insertList(list);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSFUNCTIONORG);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSROLEFUNC);
        return i;
    }

    @Override
    public void insertSysRolefuncList(List<SysRolefunc> sysRolefuncList) {
        sysRolefuncMapperExt.insertSysRolefuncList(sysRolefuncList);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSFUNCTIONORG);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSROLEFUNC);
    }
}
