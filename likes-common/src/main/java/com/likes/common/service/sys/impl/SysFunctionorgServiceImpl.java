package com.likes.common.service.sys.impl;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.constant.Constants;
import com.likes.common.constant.RedisKeys;
import com.likes.common.model.dto.sys.SysFunctionorgDO;
import com.likes.common.model.dto.sys.SysFunctionorgForRoleDO;
import com.likes.common.model.dto.sys.SysRoleinfoForRoleDO;
import com.likes.common.mybatis.entity.SysFunctionorg;
import com.likes.common.mybatis.mapper.SysFunctionorgMapper;
import com.likes.common.mybatis.mapperext.sys.SysFunctionorgMapperExt;
import com.likes.common.service.sys.SysFunctionorgService;
import com.likes.common.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.likes.common.util.redis.RedisBusinessUtil.deleteFuzzyMatchCache;

/**
 * @author lucien
 * @create 2020/6/19 16:58
 */
@Service
public class SysFunctionorgServiceImpl implements SysFunctionorgService {

    @Autowired
    private SysFunctionorgMapper sysFunctionorgMapper;
    @Autowired
    private SysFunctionorgMapperExt sysFunctionorgMapperExt;
    @Autowired
    private RedisTemplate redisTemplate;



    @Override
    public int insertSelective(SysFunctionorg s) {
        int i = sysFunctionorgMapper.insertSelective(s);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSFUNCTIONORG);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSROLEFUNC);
        return i;
    }

    @Override
    public int updateByPrimaryKeySelective(SysFunctionorg s) {
        int i = sysFunctionorgMapper.updateByPrimaryKeySelective(s);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSFUNCTIONORG);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSROLEFUNC);
        return i;
    }

    @Override
    public SysFunctionorg selectByPrimaryKey(Long sfunid) {
        String key = RedisKeys.LIVE_SYSFUNCTIONORG + sfunid.toString() + "selectByPrimaryKey";
        if (redisTemplate.hasKey(key)) {
            return (SysFunctionorg) redisTemplate.opsForValue().get(key);
        } else {
            SysFunctionorg sysFunctionorg = sysFunctionorgMapper.selectByPrimaryKey(sfunid);
            if (null != sysFunctionorg) {
                redisTemplate.opsForValue().set(key, sysFunctionorg);
                return sysFunctionorg;
            }
        }
        return null;
    }

    @Override
    public List<SysFunctionorgDO> getSysFunctionorgList(Long parsfunid) {
        Long sfunid = null == parsfunid ? Constants.DEFAULT_LONG : parsfunid;
        String key = RedisKeys.LIVE_SYSFUNCTIONORG + sfunid + "getSysFunctionorgList";
        if (redisTemplate.hasKey(key)) {
            return (List<SysFunctionorgDO>) redisTemplate.opsForValue().get(key);
        } else {
            List<SysFunctionorgDO> sysFunctionorgList = sysFunctionorgMapperExt.getAll(parsfunid);
            if (CollectionUtil.isNotEmpty(sysFunctionorgList)) {
                redisTemplate.opsForValue().set(key, sysFunctionorgList);
                return sysFunctionorgList;
            }
        }
        return new ArrayList<SysFunctionorgDO>();
    }

    @Override
    public List<SysFunctionorgDO> getSysFunctionorgTree() {
        String key = RedisKeys.LIVE_SYSFUNCTIONORG + "getSysFunctionorgTree";
        if (redisTemplate.hasKey(key)) {
            return (List<SysFunctionorgDO>) redisTemplate.opsForValue().get(key);
        } else {
            List<SysFunctionorgDO> sysFunctionorgTree = sysFunctionorgMapperExt.getSysFunctionorgTree();
            if (CollectionUtil.isNotEmpty(sysFunctionorgTree)) {
                redisTemplate.opsForValue().set(key, sysFunctionorgTree);
                return sysFunctionorgTree;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public SysFunctionorg getOneSysFunctionorg(SysFunctionorg s) {
//        String key = RedisKeys.LIVE_SYSFUNCTIONORG + JSONObject.toJSONString(s) + "getOneSysFunctionorg";
//        if (redisTemplate.hasKey(key)) {
//            return (SysFunctionorg) redisTemplate.opsForValue().get(key);
//        } else {
            SysFunctionorg repeat = sysFunctionorgMapperExt.repeat(s);
        //                redisTemplate.opsForValue().set(key, repeat);
        return repeat;
//        }
    }

    @Override
    public List<SysFunctionorgDO> getSysFunctionorgTreeByRoleAll(SysFunctionorgDO do1) {
        String key = RedisKeys.LIVE_SYSFUNCTIONORG + JSONObject.toJSONString(do1) + "getSysFunctionorgTreeByRoleAll";
        if (redisTemplate.hasKey(key)) {
            return (List<SysFunctionorgDO>) redisTemplate.opsForValue().get(key);
        } else {
            List<SysFunctionorgDO> sysFunctionorgTreeByRoleAll = sysFunctionorgMapperExt.getSysFunctionorgTreeByRoleAll(do1);
            if (CollectionUtil.isNotEmpty(sysFunctionorgTreeByRoleAll)) {
                redisTemplate.opsForValue().set(key, sysFunctionorgTreeByRoleAll);
                return sysFunctionorgTreeByRoleAll;
            }
        }
        return new ArrayList<SysFunctionorgDO>();
    }

    @Override
    public List<SysFunctionorgForRoleDO> getSysFunctionorgAllList(List<Long> allSfunid) {
        String key = RedisKeys.LIVE_SYSFUNCTIONORG + JSONObject.toJSONString(allSfunid) + "getSysFunctionorgAllList";
        if (redisTemplate.hasKey(key)) {
            return (List<SysFunctionorgForRoleDO>) redisTemplate.opsForValue().get(key);
        } else {
            List<SysFunctionorgForRoleDO> sysFunctionorgList = sysFunctionorgMapperExt.getSysFunctionorgList(allSfunid);
            if (CollectionUtil.isNotEmpty(sysFunctionorgList)) {
                redisTemplate.opsForValue().set(key, sysFunctionorgList);
                return sysFunctionorgList;
            }
        }
        return null;
    }

    @Override
    public List<Long> getParSfunidListNode(Long sfunid) {
        String key = RedisKeys.LIVE_SYSFUNCTIONORG + sfunid + "getParSfunidListNode";
        if (redisTemplate.hasKey(key)) {
            return (List<Long>) redisTemplate.opsForValue().get(key);
        } else {
            List<Long> parSfunidListNode = sysFunctionorgMapperExt.getParSfunidListNode(sfunid);
            if (CollectionUtil.isNotEmpty(parSfunidListNode)) {
                redisTemplate.opsForValue().set(key, parSfunidListNode);
                return parSfunidListNode;
            }
        }
        return new ArrayList<Long>();
    }

    @Override
    public List<SysRoleinfoForRoleDO> getSfunidList(Long sfunid) {
        String key = RedisKeys.LIVE_SYSFUNCTIONORG + sfunid + "getSfunidList";
        if (redisTemplate.hasKey(key)) {
            return (List<SysRoleinfoForRoleDO>) redisTemplate.opsForValue().get(key);
        } else {
            List<SysRoleinfoForRoleDO> sfunidList = sysFunctionorgMapperExt.getSfunidList(sfunid);
            if (CollectionUtil.isNotEmpty(sfunidList)) {
                redisTemplate.opsForValue().set(key, sfunidList);
                return sfunidList;
            }
        }
        return null;
    }
}
