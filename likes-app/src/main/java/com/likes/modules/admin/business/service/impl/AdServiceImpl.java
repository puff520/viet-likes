package com.likes.modules.admin.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.request.AdRequest;
import com.likes.common.model.response.AdResponse;
import com.likes.common.mybatis.entity.BdBannerseat;
import com.likes.common.service.BaseServiceImpl;
import com.likes.common.service.ad.BdBannerpicinfoService;
import com.likes.common.service.ad.BdBannerseatService;
import com.likes.common.util.CollectionUtil;
import com.likes.modules.admin.business.service.AdService;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.likes.common.util.DateUtils.getDateTime;
import static com.likes.common.util.redis.RedisBusinessUtil.deleteFuzzyMatchCache;

/**
 * 广告
 *
 * @author bjkj
 */
@Service
public class AdServiceImpl extends BaseServiceImpl implements AdService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BdBannerpicinfoService bdBannerpicinfoService;

    @Autowired
    private BdBannerseatService bdBannerseatService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageResult adList(AdRequest req, PageBounds page) {
        if (StringUtils.isEmpty(req.getSeatcode())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_101.getCode(), "位置为空");
        }
//        String key = RedisKeys.LIVE_AD_SLOT + req.getSeatcode() + JSONObject.toJSONString(page);
//        if (redisTemplate.hasKey(key)) {
//            Page<AdResponse> AdResponses = (Page<AdResponse>) redisTemplate.opsForValue().get(key);
//
//            return PageResult.getPageResult(page, AdResponses);
//        } else {
            //清除缓存操作
            deleteFuzzyMatchCache(RedisKeys.LIVE_AD_SLOT);
            List<BdBannerseat> bdBannerseats = bdBannerseatService.getBseatId(req.getSeatcode());
            if (CollectionUtil.isNotEmpty(bdBannerseats)) {
                List<Long> idList = new ArrayList<>();
                req.setDateTime(getDateTime());
                for (BdBannerseat vo : bdBannerseats) {
                    idList.add(vo.getBseatid());
                }
                req.setList(idList);
                Page<AdResponse> list = bdBannerpicinfoService.adList(req, page.toRowBounds());
                if (CollectionUtil.isNotEmpty(list)) {
//                    redisTemplate.opsForValue().set(key, list);
                    return PageResult.getPageResult(page, list);
                }
            }
//        }
        return PageResult.getPageResult(page, new ArrayList());
    }

}
