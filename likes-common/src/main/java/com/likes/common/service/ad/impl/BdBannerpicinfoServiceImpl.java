package com.likes.common.service.ad.impl;

import com.likes.common.constant.RedisKeys;
import com.likes.common.model.dto.BdBannerpicinfoDO;
import com.likes.common.model.request.AdRequest;
import com.likes.common.model.response.AdResponse;
import com.likes.common.mybatis.entity.BdBannerpicinfo;
import com.likes.common.mybatis.mapper.BdBannerpicinfoMapper;
import com.likes.common.mybatis.mapperext.BdBannerpicinfoMapperExt;
import com.likes.common.service.ad.BdBannerpicinfoService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.likes.common.util.redis.RedisBusinessUtil.deleteFuzzyMatchCache;

@Service
public class BdBannerpicinfoServiceImpl implements BdBannerpicinfoService {
    @Resource
    private BdBannerpicinfoMapper bdBannerpicinfoMapper;
    @Resource
    private BdBannerpicinfoMapperExt bdBannerpicinfoMapperExt;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Page<AdResponse> adList(AdRequest req, RowBounds rowBounds) {
        return bdBannerpicinfoMapperExt.adList(req, rowBounds);
    }

    @Override
    public Page<BdBannerpicinfoDO> adsList(BdBannerpicinfo req, RowBounds rowBounds) {

        return bdBannerpicinfoMapperExt.adsList(req, rowBounds);
    }

    @Override
    public int insertSelective(BdBannerpicinfo record) {
        //清除缓存
        deleteFuzzyMatchCache(RedisKeys.LIVE_AD_SLOT);
        return bdBannerpicinfoMapper.insertSelective(record);
    }

    @Override
    public BdBannerpicinfo selectByPrimaryKey(Long bannerpicid) {
        return bdBannerpicinfoMapper.selectByPrimaryKey(bannerpicid);
    }

    @Override
    public int updateByPrimaryKey(BdBannerpicinfo record) {
        //清除缓存
        deleteFuzzyMatchCache(RedisKeys.LIVE_AD_SLOT);
        return bdBannerpicinfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(BdBannerpicinfo record) {
        //清除缓存
        deleteFuzzyMatchCache(RedisKeys.LIVE_AD_SLOT);
        return bdBannerpicinfoMapper.updateByPrimaryKeySelective(record);
    }
}
