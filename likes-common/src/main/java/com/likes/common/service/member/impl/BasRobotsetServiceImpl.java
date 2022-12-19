package com.likes.common.service.member.impl;

import com.likes.common.constant.RedisKeys;
import com.likes.common.mybatis.entity.BasRobotset;
import com.likes.common.mybatis.entity.BasRobotsetExample;
import com.likes.common.mybatis.mapper.BasRobotsetMapper;
import com.likes.common.service.member.BasRobotsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lucien
 * @create 2020/8/19 19:52
 */
@Service
public class BasRobotsetServiceImpl implements BasRobotsetService {

    @Autowired
    private BasRobotsetMapper basRobotsetMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public BasRobotset detail() {
        if (redisTemplate.hasKey(RedisKeys.LIVE_ROBOT)) {
            return (BasRobotset) redisTemplate.opsForValue().get(RedisKeys.LIVE_ROBOT);
        } else {
            BasRobotsetExample basRobotsetExample = new BasRobotsetExample();
            BasRobotsetExample.Criteria criteria = basRobotsetExample.createCriteria();
            criteria.andIsDeleteEqualTo(false);
            basRobotsetExample.setOrderByClause("create_time desc");
            BasRobotset basRobotset = basRobotsetMapper.selectOneByExample(basRobotsetExample);
            if (null != basRobotset) {
                redisTemplate.opsForValue().set(RedisKeys.LIVE_ROBOT, basRobotset);
                return basRobotset;
            }
        }
        return null;
    }

    @Override
    public int insertSelective(BasRobotset b) {
        int i = basRobotsetMapper.insertSelective(b);
        redisTemplate.delete(RedisKeys.LIVE_ROBOT);
        return i;
    }

    @Override
    public int updateByPrimaryKey(BasRobotset basRobotset) {
        int i = basRobotsetMapper.updateByPrimaryKey(basRobotset);
        redisTemplate.delete(RedisKeys.LIVE_ROBOT);
        return i;
    }
}
