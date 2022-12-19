package com.likes.common.service.ad.impl;

import com.likes.common.constant.RedisKeys;
import com.likes.common.model.dto.BdBannerseatDO;
import com.likes.common.mybatis.entity.BdBannerseat;
import com.likes.common.mybatis.entity.BdBannerseatExample;
import com.likes.common.mybatis.mapper.BdBannerseatMapper;
import com.likes.common.mybatis.mapperext.BdBannerseatMapperExt;
import com.likes.common.service.ad.BdBannerseatService;
import com.likes.common.util.CollectionUtil;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.likes.common.util.redis.RedisBusinessUtil.deleteFuzzyMatchCache;

@Service
public class BdBannerseatServiceImpl implements BdBannerseatService {
    @Resource
    private BdBannerseatMapperExt bdBannerseatMapperExt;
    @Resource
    private BdBannerseatMapper bdBannerseatMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Page<BdBannerseatDO> getList(BdBannerseat req, RowBounds rowBounds) {
        return bdBannerseatMapperExt.getList(req, rowBounds);
    }

    @Override
    public BdBannerseat getRepeat(BdBannerseat bannerseat) {
        return bdBannerseatMapperExt.getRepeat(bannerseat);
    }

    @Override
    public BdBannerseat selectByPrimaryKey(Long bseatid) {
        return bdBannerseatMapper.selectByPrimaryKey(bseatid);
    }

    @Override
    public int insertSelective(BdBannerseat record) {
        //删除缓存
        deleteFuzzyMatchCache(RedisKeys.LIVE_AD_SLOT);
        return bdBannerseatMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(BdBannerseat record) {
        //删除缓存
        deleteFuzzyMatchCache(RedisKeys.LIVE_AD_SLOT);
        return bdBannerseatMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<BdBannerseat> getBseatId(String seatcode) {
        String key = RedisKeys.LIVE_AD_SLOT + seatcode;
        if (redisTemplate.hasKey(key)) {
            return (List<BdBannerseat>) redisTemplate.opsForValue().get(key);
        } else {
            BdBannerseatExample bdBannerseatExample = new BdBannerseatExample();
            BdBannerseatExample.Criteria criteria = bdBannerseatExample.createCriteria();
            criteria.andIsDeleteEqualTo(false);
            criteria.andIsEnableEqualTo(true);
            criteria.andSeatcodeEqualTo(seatcode);
            List<BdBannerseat> bdBannerseats = bdBannerseatMapper.selectByExample(bdBannerseatExample);
            if (CollectionUtil.isNotEmpty(bdBannerseats)) {
                redisTemplate.opsForValue().set(key, bdBannerseats);
                return bdBannerseats;
            }
        }
        return null;
    }
}
