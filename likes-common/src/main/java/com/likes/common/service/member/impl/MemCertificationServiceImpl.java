package com.likes.common.service.member.impl;

import com.likes.common.mybatis.entity.MemCertification;
import com.likes.common.mybatis.mapper.MemCertificationMapper;
import com.likes.common.mybatis.mapperext.member.MemCertificationMapperExt;
import com.likes.common.service.member.MemCertificationService;
import com.likes.common.util.redis.RedisBusinessUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemCertificationServiceImpl implements MemCertificationService {

    @Resource
    private MemCertificationMapperExt memCertificationMapperExt;
    @Resource
    private MemCertificationMapper memCertificationMapper;

    /**
     * 根据accno查找主播认证表
     */
    @Override
    public MemCertification findByAccno(String accno) {
        return memCertificationMapperExt.findByAccno(accno);
    }

    /**
     * 根据accno查找主播认证表(特殊处理，防穿透。走缓存，getUserInfo使用)
     */
    @Override
    public MemCertification findByAccnoByCache(String accno) {
        MemCertification memCertification = RedisBusinessUtil.getUserCertificationCache(accno);
        if (null == memCertification) {
            memCertification = memCertificationMapperExt.findByAccno(accno);
            if (null == memCertification) {
                RedisBusinessUtil.addUserCertificationCache(accno, new MemCertification());
            } else {
                RedisBusinessUtil.addUserCertificationCache(accno, memCertification);
            }
        } else if (null == memCertification.getCertstatus()) {
            return null;
        }
        return memCertification;
    }

    /**
     * 插入主播认证表
     */
    @Override
    public int insertSelectiveMemCertification(MemCertification mCertification) {
        int n = memCertificationMapperExt.insertSelectiveMemCertification(mCertification);
        RedisBusinessUtil.delUserCertificationCache(mCertification.getAccno());
        return n;
    }

    /**
     * 根据accno查找主播认证表
     */
    @Override
    public MemCertification getMemCertificationOne(String accno) {
        return memCertificationMapperExt.getMemCertificationOne(accno);
    }

    @Override
    public int updateByPrimaryKeySelective(MemCertification record) {
        int n = memCertificationMapper.updateByPrimaryKeySelective(record);
        RedisBusinessUtil.delUserCertificationCache(record.getAccno());
        return n;
    }
}
