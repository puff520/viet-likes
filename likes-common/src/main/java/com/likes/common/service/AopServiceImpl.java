package com.likes.common.service;


import com.likes.common.constant.Constants;
import com.likes.common.constant.RedisKeys;
import com.likes.common.util.DirectoryUtil;
import com.likes.common.util.TimeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: admin
 * @Description: aop业务类
 * @Version: 1.0.0 @Date; 2018/5/26 026 17:11
 */
@Service
public class AopServiceImpl implements AopService {
    private final static Logger logger = LoggerFactory.getLogger(AopServiceImpl.class);

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public void saveMemberOnline(String source, String userId) {
        if (source.equalsIgnoreCase(Constants.ANDROID_STRING)) {
            // 安卓端用户
            redisTemplate.opsForHash().put(RedisKeys.ANDROIDONLINE, userId, String.valueOf(TimeHelper.time()));
        } else if (source.equalsIgnoreCase(Constants.IOS_STRING)) {
            // ios端用户
            redisTemplate.opsForHash().put(RedisKeys.IOSONLINE, userId, String.valueOf(TimeHelper.time()));
        } else if (source.equalsIgnoreCase(Constants.H5_STRING)) {
            // h5端用户
            redisTemplate.opsForHash().put(RedisKeys.H5ONLINE, userId, String.valueOf(TimeHelper.time()));
        } else {
            // WEB端用户
            redisTemplate.opsForHash().put(RedisKeys.WEBONLINE, userId, String.valueOf(TimeHelper.time()));
        }
    }

    @Override
    public void saveMemberYouke(String agent, String source, String userId) {
        if (DirectoryUtil.ipMap.get(userId) != null) {
            DirectoryUtil.ipMap.put(userId, String.valueOf(TimeHelper.time()));
            return;
        }
        if (source.equalsIgnoreCase(Constants.ANDROID_STRING)) {
            // 安卓端用户
            redisTemplate.opsForHash().put(RedisKeys.ANDROIDYOUKE, userId, String.valueOf(TimeHelper.time()));
        } else if (source.equalsIgnoreCase(Constants.IOS_STRING)) {
            // ios端用户
            redisTemplate.opsForHash().put(RedisKeys.IOSYOUKE, userId, String.valueOf(TimeHelper.time()));
        } else if (source.equalsIgnoreCase(Constants.H5_STRING)) {
            // ios端用户
            redisTemplate.opsForHash().put(RedisKeys.H5YOUKE, userId, String.valueOf(TimeHelper.time()));
        } else {
            // WEB端用户
            redisTemplate.opsForHash().put(RedisKeys.WEBYOUKE, userId, String.valueOf(TimeHelper.time()));
        }
        DirectoryUtil.ipMap.put(userId, String.valueOf(TimeHelper.time()));
//        jedisConnectionFactory.setDatabase(1);
//        redisTemplate.setConnectionFactory(jedisConnectionFactory);
    }

    @Override
    public void saveDeviceIdYouke(String deviceInfo) {
//        ios:FCF7E265-B6BD-41C1-A795-5FB0F89E9989
        try {
            String[] array = deviceInfo.split(":");
            String type = array[0];
            String deviceId = array[1];
            if (type.equalsIgnoreCase(Constants.ANDROID_STRING)) {
                // 安卓端用户
                redisTemplate.opsForHash().put(RedisKeys.ANDROIDDEVICE, deviceId, String.valueOf(TimeHelper.time()));
            } else if (type.equalsIgnoreCase(Constants.IOS_STRING)) {
                // ios端用户
                redisTemplate.opsForHash().put(RedisKeys.IOSDEVICE, deviceId, String.valueOf(TimeHelper.time()));
            } else if (type.equalsIgnoreCase(Constants.H5_STRING)) {
                // ios端用户
                redisTemplate.opsForHash().put(RedisKeys.H5DEVICE, deviceId, String.valueOf(TimeHelper.time()));
            } else {
                // WEB端用户
                redisTemplate.opsForHash().put(RedisKeys.WEBDEVICE, deviceId, String.valueOf(TimeHelper.time()));
            }
        } catch (Exception e) {
            logger.error("saveDeviceIdYouke error", e);

        }
    }

}
