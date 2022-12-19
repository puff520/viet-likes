package com.likes.common.service.member.impl;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.constant.RedisKeys;
import com.likes.common.mybatis.entity.BdUser;
import com.likes.common.mybatis.entity.BdUserExample;
import com.likes.common.mybatis.mapper.BdUserMapper;
import com.likes.common.mybatis.mapperext.BdUserMapperExt;
import com.likes.common.service.member.BdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.likes.common.util.redis.RedisBusinessUtil.deleteFuzzyMatchCache;

@Service
public class BdUserServiceImpl implements BdUserService {
    @Resource
    private BdUserMapper bdUserMapper;
    @Resource
    private BdUserMapperExt bdUserMapperExt;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public BdUser selectByAccno(String accno) {
        String key = RedisKeys.LIVE_SYSTEM_USER + "selectByAccno" + accno;
        if (redisTemplate.hasKey(key)) {
            return (BdUser) redisTemplate.opsForValue().get(key);
        } else {
            BdUser bdUserByAccno = bdUserMapperExt.selectByAccno(accno);
            if (null != bdUserByAccno) {
                redisTemplate.opsForValue().set(key, bdUserByAccno);
                return bdUserByAccno;
            }
        }
        return null;
    }

    @Override
    public BdUser getRandWeiChat() {
        return bdUserMapperExt.getRandWeiChat();
    }

    @Override
    public List<String> getKeFuWeiChat(String sysrolename) {
        return bdUserMapperExt.getKeFuWeiChat(sysrolename);
    }

    @Override
    public BdUser getRepeatPhoneno(BdUser bdUserParam) {
        String key = RedisKeys.LIVE_SYSTEM_USER + "getRepeatPhoneno" + JSONObject.toJSONString(bdUserParam);
        if (redisTemplate.hasKey(key)) {
            return (BdUser) redisTemplate.opsForValue().get(key);
        } else {
            BdUser repeatPhoneno = bdUserMapperExt.getRepeatPhoneno(bdUserParam);
            if (null != repeatPhoneno) {
                redisTemplate.opsForValue().set(key, repeatPhoneno);
                return repeatPhoneno;
            }
        }
        return null;
    }

    @Override
    public int insertSelective(BdUser record) {
        int i = bdUserMapper.insertSelective(record);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSTEM_USER);
        return i;
    }

    @Override
    public int updateByPrimaryKeySelective(BdUser record) {
        int i = bdUserMapper.updateByPrimaryKeySelective(record);
        deleteFuzzyMatchCache(RedisKeys.LIVE_SYSTEM_USER);
        return i;
    }

    @Override
    public BdUser getAcclogin(String acclogin) {
//        String key = RedisKeys.LIVE_SYSTEM_USER + "getAcclogin" + acclogin;
//        if (redisTemplate.hasKey(key)) {
//            return (BdUser) redisTemplate.opsForValue().get(key);
//        } else {
            BdUserExample bdUserExample = new BdUserExample();
            BdUserExample.Criteria criteria = bdUserExample.createCriteria();
            criteria.andAccloginEqualTo(acclogin);
            criteria.andIsDeleteEqualTo(false);
            BdUser bdUser = bdUserMapper.selectOneByExample(bdUserExample);
//            if (null != bdUser) {
//                redisTemplate.opsForValue().set(key, bdUser);
                return bdUser;
//            }
//        }
//        return null;
    }

    @Override
    public BdUser selectByAcclogin(String acclogin) {
        String key = RedisKeys.LIVE_SYSTEM_USER + "selectByAcclogin" + acclogin;
        if (redisTemplate.hasKey(key)) {
            return (BdUser) redisTemplate.opsForValue().get(key);
        } else {
            BdUserExample bdUserExample = new BdUserExample();
            BdUserExample.Criteria criteria = bdUserExample.createCriteria();
            criteria.andAccloginEqualTo(acclogin);
            criteria.andIsDeleteEqualTo(false);
            BdUser bdUser = bdUserMapper.selectOneByExample(bdUserExample);
            if (null != bdUser) {
                redisTemplate.opsForValue().set(key, bdUser);
                return bdUser;
            }
        }
        return null;
    }
}
